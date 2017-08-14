/**
 * 
 */
package zn.service;

import java.io.InputStream;
import java.net.InetAddress;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.annotation.Resource;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import zn.dao.MonAlarmsDao;
import zn.dao.MonDateDao;
import zn.dao.MonitorDao;
import zn.dao.RoomStatusDao;
import zn.dao.UserDao;
import zn.entity.MonAlarms;
import zn.entity.MonDate;
import zn.entity.Monitor;
import zn.entity.RoomStatus;
import zn.listener.AnalysisInfoListener;
import zn.test.newTest;
import zn.until.EncodeUtils;
import zn.until.UdpServerSocket;
import zn.until.upush.UPushClient;
import zn.until.upush.UPushConfig;

/**
 * @author hq
 *
 */
@Service("analysisService")
public class AnalysisServiceImpl implements AnalysisService {

	@Resource // 注入
	private MonAlarmsDao monAlarmsDao;

	@Resource // 注入
	private MonDateDao monDateDao;

	@Resource // 注入
	private MonitorDao monitorDao;
	
	@Resource
	private RoomStatusDao roomStatusDao;

	@Resource
	private UserDao userDao;

	@Async
	public void analysisMon() {

		try {

			InputStream inStream = AnalysisInfoListener.class.getClassLoader().getResourceAsStream("./udp.properties");

			Properties prop = new Properties();
			prop.load(inStream);
			int localityPort = Integer.valueOf(prop.getProperty("localityPort"));

			String localityHost = prop.getProperty("localityHost");
			System.out.println(localityHost);
			UdpServerSocket udpServerSocket = new UdpServerSocket(InetAddress.getLocalHost().getHostAddress(),
					localityPort);
			System.out.println("port" + InetAddress.getLocalHost().getHostAddress());
			while (true) {

				byte[] hex = udpServerSocket.receive();

				String aex = EncodeUtils.hexEncode(hex);
				System.out.println(aex);
				String monNumber = aex.substring(aex.length() - 32, aex.length());
				if("4f".equals(aex.substring(16, 18))||"4F".equals(aex.substring(16, 18))){
				
					analysisWarningAex(aex,monNumber);
				}else{
			
				

				analysisHex(hex, monNumber);
				}

			}

		} catch (Exception e) {

			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		String ad="53464252010000004F00000030333033423231364252421d6e788b3d38470fa3a27df6d7d6d932";
		String ass=ad.substring(24, ad.length()-32);
//		String asscii	=EncodeUtils.asciiToString("30,31");
//		System.out.println(asscii);

		 ass=EncodeUtils.convertHexToString(ass);
	

		System.out.println(ass.substring(4,6));

	}
	
	

	@Async
	public void analysisWarningAex(String aex,String  monNumber){
		Monitor monit = monitorDao.selectMonByNum(monNumber.toUpperCase());
		if (monit == null) {
			return;
		}
		aex=aex.substring(24, aex.length()-32);
		aex=EncodeUtils.convertHexToString(aex).toLowerCase();
		MonAlarms monAlarms = new MonAlarms();
		int monId=monit.getMonId();
		List<RoomStatus>	roomList= roomStatusDao.getByMonIdAndNode(monId,aex.substring(0,2));
		if(roomList.isEmpty()){
			return;
		}
		String info=monit.getMonName()+roomList.get(0).getTDMC();
		String monAlarmsType=aex.substring(4,6);
		switch (monAlarmsType) {
		case "b1":
			
			info+="发生了温度报警";
			break;
		case "b2":
			info+="发生了烟感报警";
			break;
		case "b3":
			info+="发生了电流上限报警";
			break;
		default:
			return;
		}
		monAlarms.setMonAlarmsType(monAlarmsType);
		monAlarms.setMonAlarmsInfo(info);
		SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
		monAlarms.setMonAlarmsTM(simpleDateFormat.format(new Date()));

	
		if (monAlarms.getMonAlarmsInfo() == null || "".equals(monAlarms.getMonAlarmsInfo())) {
			return;
		}
		monAlarms.setMonId(monId);

		monAlarmsDao.addMonAlarms(monAlarms);
		List<Integer> list = userDao.selectUserIdList(monId);
		List<Map<String, Object>> mapList = new ArrayList<Map<String, Object>>();
		for (Integer userId : list) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("userId", userId);
			map.put("alarmsId", monAlarms.getAlarmsId());
			mapList.add(map);
		}
		monAlarmsDao.userAddAlarm(mapList);
        
		return;
	}

	
	
	
	
	/**
	 * 解析并储存hex文件 @Title: analysisHex @Description: TODO @param hex @return @throws
	 */
	@Async
	public void analysisHex(byte[] hex, String monNumber) {
		try {
			MonDate mon = new MonDate();
			byte[] mes = hex;

			Monitor monit = monitorDao.selectMonByNum(monNumber.toUpperCase());
			if (monit == null) {
				return;
			}
			mon.setMonId(monit.getMonId());

			// 读取电压电流实时值
			if (mes[8] == 3) {
				if (mes[12] == 6) { // 单路或者6路
					// A相总电压
					mon.setAllAV(EncodeUtils.byte2float(mes, 20) + "");
					// B相总电压
					mon.setAllBV(EncodeUtils.byte2float(mes, 24) + "");
					// C相总电压
					mon.setAllCV(EncodeUtils.byte2float(mes, 28) + "");
					// A相总电流
					mon.setAllAA(EncodeUtils.byte2float(mes, 32) + "");
					// B相总电流
					mon.setAllBA(EncodeUtils.byte2float(mes, 36) + "");
					// C相总电流
					mon.setAllCA(EncodeUtils.byte2float(mes, 40) + "");
					// 湿度值
					mon.setHumidity(EncodeUtils.byte2float(mes, 44) + "");
					// 第一路电流
					mon.setA1(EncodeUtils.byte2float(mes, 48) + "," + EncodeUtils.byte2float(mes, 52) + ","
							+ EncodeUtils.byte2float(mes, 56) + "," + EncodeUtils.byte2float(mes, 120));
					// 第二路电流
					mon.setA2(EncodeUtils.byte2float(mes, 60) + "," + EncodeUtils.byte2float(mes, 64) + ","
							+ EncodeUtils.byte2float(mes, 68) + "," + EncodeUtils.byte2float(mes, 124));
					// 第三路电流
					mon.setA3(EncodeUtils.byte2float(mes, 72) + "," + EncodeUtils.byte2float(mes, 76) + ","
							+ EncodeUtils.byte2float(mes, 80) + "," + EncodeUtils.byte2float(mes, 128));
					// 第四路电流
					mon.setA4(EncodeUtils.byte2float(mes, 84) + "," + EncodeUtils.byte2float(mes, 88) + ","
							+ EncodeUtils.byte2float(mes, 92) + "," + EncodeUtils.byte2float(mes, 132));
					// 第五路电流
					mon.setA5(EncodeUtils.byte2float(mes, 96) + "," + EncodeUtils.byte2float(mes, 100) + ","
							+ EncodeUtils.byte2float(mes, 104) + "," + EncodeUtils.byte2float(mes, 136));
					// 第六路电流
					mon.setA6(EncodeUtils.byte2float(mes, 108) + "," + EncodeUtils.byte2float(mes, 112) + ","
							+ EncodeUtils.byte2float(mes, 116) + "," + EncodeUtils.byte2float(mes, 140));
				} else if (mes[12] == 12) { // 12路
					if (mes.length == 160) {
						// A相总电压
						mon.setAllAV(EncodeUtils.byte2float(mes, 20) + "");
						// B相总电压
						mon.setAllBV(EncodeUtils.byte2float(mes, 24) + "");
						// C相总电压
						mon.setAllCV(EncodeUtils.byte2float(mes, 28) + "");
						// A相总电流
						mon.setAllAA(EncodeUtils.byte2float(mes, 32) + "");
						// B相总电流
						mon.setAllBA(EncodeUtils.byte2float(mes, 36) + "");
						// C相总电流
						mon.setAllCA(EncodeUtils.byte2float(mes, 40) + "");
						// 湿度值
						mon.setHumidity(EncodeUtils.byte2float(mes, 44) + "");
						// 第一路电流
						mon.setA1(EncodeUtils.byte2float(mes, 48) + "," + EncodeUtils.byte2float(mes, 52) + ","
								+ EncodeUtils.byte2float(mes, 56) + "," + EncodeUtils.byte2float(mes, 120));
						// 第二路电流
						mon.setA2(EncodeUtils.byte2float(mes, 60) + "," + EncodeUtils.byte2float(mes, 64) + ","
								+ EncodeUtils.byte2float(mes, 68) + "," + EncodeUtils.byte2float(mes, 124));
						// 第三路电流
						mon.setA3(EncodeUtils.byte2float(mes, 72) + "," + EncodeUtils.byte2float(mes, 76) + ","
								+ EncodeUtils.byte2float(mes, 80) + "," + EncodeUtils.byte2float(mes, 128));
						// 第四路电流
						mon.setA4(EncodeUtils.byte2float(mes, 84) + "," + EncodeUtils.byte2float(mes, 88) + ","
								+ EncodeUtils.byte2float(mes, 92) + "," + EncodeUtils.byte2float(mes, 132));
						// 第五路电流
						mon.setA5(EncodeUtils.byte2float(mes, 96) + "," + EncodeUtils.byte2float(mes, 100) + ","
								+ EncodeUtils.byte2float(mes, 104) + "," + EncodeUtils.byte2float(mes, 136));
						// 第六路电流
						mon.setA6(EncodeUtils.byte2float(mes, 108) + "," + EncodeUtils.byte2float(mes, 112) + ","
								+ EncodeUtils.byte2float(mes, 116) + "," + EncodeUtils.byte2float(mes, 140));
					} else if (mes.length == 132) {
						// 第七路电流
						mon.setA7(EncodeUtils.byte2float(mes, 20) + "," + EncodeUtils.byte2float(mes, 24) + ","
								+ EncodeUtils.byte2float(mes, 28) + "," + EncodeUtils.byte2float(mes, 92));
						// 第八电流
						mon.setA8(EncodeUtils.byte2float(mes, 32) + "," + EncodeUtils.byte2float(mes, 36) + ","
								+ EncodeUtils.byte2float(mes, 40) + "," + EncodeUtils.byte2float(mes, 96));
						// 第九路电流
						mon.setA9(EncodeUtils.byte2float(mes, 44) + "," + EncodeUtils.byte2float(mes, 48) + ","
								+ EncodeUtils.byte2float(mes, 52) + "," + EncodeUtils.byte2float(mes, 100));
						// 第十路电流
						mon.setA10(EncodeUtils.byte2float(mes, 56) + "," + EncodeUtils.byte2float(mes, 60) + ","
								+ EncodeUtils.byte2float(mes, 64) + "," + EncodeUtils.byte2float(mes, 104));
						// 第十一路电流
						mon.setA11(EncodeUtils.byte2float(mes, 68) + "," + EncodeUtils.byte2float(mes, 72) + ","
								+ EncodeUtils.byte2float(mes, 76) + "," + EncodeUtils.byte2float(mes, 108));
						// 第十二路电流
						mon.setA12(EncodeUtils.byte2float(mes, 80) + "," + EncodeUtils.byte2float(mes, 84) + ","
								+ EncodeUtils.byte2float(mes, 88) + "," + EncodeUtils.byte2float(mes, 112));
					}

				}
			} else if (mes[8] == 10) {
				String T1 = "";
				String T2 = "";
				String T3 = "";
				String T4 = "";
				String T5 = "";
				String T6 = "";
				String T7 = "";
				String T8 = "";
				String T9 = "";
				String T10 = "";
				String T11 = "";
				String T12 = "";
				for (int i = 16; i < mes.length - 16; i = i + 8) {
					if (mes[i] == 1 && mes[i + 3] == 0) {
						if (mes[i + 1] == 0) {
							T1 = T1 + pathT(mes[i + 2]) + EncodeUtils.byte2float(mes, i + 4) + ",";
						} else if (mes[i + 1] == 1) {
							T2 = T2 + pathT(mes[i + 2]) + EncodeUtils.byte2float(mes, i + 4) + ",";
						} else if (mes[i + 1] == 2) {
							T3 = T3 + pathT(mes[i + 2]) + EncodeUtils.byte2float(mes, i + 4) + ",";
						} else if (mes[i + 1] == 3) {
							T4 = T4 + pathT(mes[i + 2]) + EncodeUtils.byte2float(mes, i + 4) + ",";
						} else if (mes[i + 1] == 4) {
							T5 = T5 + pathT(mes[i + 2]) + EncodeUtils.byte2float(mes, i + 4) + ",";
						} else if (mes[i + 1] == 5) {
							T6 = T6 + pathT(mes[i + 2]) + EncodeUtils.byte2float(mes, i + 4) + ",";
						} else if (mes[i + 1] == 6) {
							T7 = T7 + pathT(mes[i + 2]) + EncodeUtils.byte2float(mes, i + 4) + ",";
						} else if (mes[i + 1] == 7) {
							T8 = T8 + pathT(mes[i + 2]) + EncodeUtils.byte2float(mes, i + 4) + ",";
						} else if (mes[i + 1] == 8) {
							T9 = T9 + pathT(mes[i + 2]) + EncodeUtils.byte2float(mes, i + 4) + ",";
						} else if (mes[i + 1] == 9) {
							T10 = T10 + pathT(mes[i + 2]) + EncodeUtils.byte2float(mes, i + 4) + ",";
						} else if (mes[i + 1] == 10) {
							T11 = T11 + pathT(mes[i + 2]) + EncodeUtils.byte2float(mes, i + 4) + ",";
						} else if (mes[i + 1] == 11) {
							T12 = T12 + pathT(mes[i + 2]) + EncodeUtils.byte2float(mes, i + 4) + ",";
						}

					} else if (mes[i] == 2 && mes[i + 3] == 0) {
						mon.setMonInT(EncodeUtils.byte2float(mes, i + 4) + "");
					}
				}

				mon.setT1(T1);
				mon.setT2(T2);
				mon.setT3(T3);
				mon.setT4(T4);
				mon.setT5(T5);
				mon.setT6(T6);
				mon.setT7(T7);
				mon.setT8(T8);
				mon.setT9(T9);
				mon.setT10(T10);
				mon.setT11(T11);
				mon.setT12(T12);

			} else if (mes[8] == 2) {
				String states = EncodeUtils.byte2bits(mes[18]) + EncodeUtils.byte2bits(mes[19]);

				mon.setMonSwitch(states.substring(states.length() - 12, states.length()));

			} else if (mes[8] == 30) {
				MonAlarms monAlarms = analysisWarningHex(mes);
				if (monAlarms.getMonAlarmsInfo() == null || "".equals(monAlarms.getMonAlarmsInfo())) {
					return;
				}
				monAlarms.setMonId(mon.getMonId());

				monAlarmsDao.addMonAlarms(monAlarms);
				List<Integer> list = userDao.selectUserIdList(mon.getMonId());
				List<Map<String, Object>> mapList = new ArrayList<Map<String, Object>>();
				for (Integer userId : list) {
					Map<String, Object> map = new HashMap<String, Object>();
					map.put("userId", userId);
					map.put("alarmsId", monAlarms.getAlarmsId());
					mapList.add(map);
				}
				monAlarmsDao.userAddAlarm(mapList);
                
				return;
			}

			if (monDateDao.selectMonDateIsExist(monit.getMonId()) == 0) {

				monDateDao.addMonDate(mon);
			} else {
				monDateDao.changeMonDate(mon);
			}
		} catch (Exception e) {

			e.printStackTrace();
		}

	}

	public String pathT(int path) {
		if (path == 3) {
			return "\"singleT\":";
		} else if (path == 16) {
			return "\"AT\":";
		} else if (path == 17) {
			return "\"BT\":";
		} else if (path == 18) {
			return "\"CT\":";
		} else if (path == 19) {
			return "\"NT\":";
		}
		return "";

	}

	/**
	 * 分析报警信息 @Title: analysisWarningHex @Description: TODO @param byte[]数组 @return
	 * MonAlarms @throws
	 */
	@Async
	public MonAlarms analysisWarningHex(byte[] mes) {
		DecimalFormat dFormat = new DecimalFormat("#.0");
		MonAlarms mon = new MonAlarms();
		try {

			String monAlarmsTM = EncodeUtils.getLong(mes, 16) + "-" + EncodeUtils.getLong(mes, 20) + "-"
					+ EncodeUtils.getLong(mes, 24) + " " + EncodeUtils.getLong(mes, 28) + ":"
					+ EncodeUtils.getLong(mes, 32) + ":" + EncodeUtils.getLong(mes, 36);
			mon.setMonAlarmsTM(monAlarmsTM);
			mon.setMonAlarmsType(Long.toHexString(EncodeUtils.getLong(mes, 12)));
			// 主动上传温度告警信息
			if (mes[12] == 16) {
				mon.setMonAlarmsInfo("箱内温度过高报警,报警值为" + dFormat.format(EncodeUtils.byte2float(mes, 56)));
			} else if (mes[12] == 19) { // 电流过载信息
				long monV = EncodeUtils.getLong(mes, 44);
				long p = EncodeUtils.getLong(mes, 48);
				String mov = "";
				if (monV == 16) {
					mov = "220V";
				} else if (monV == 17) {
					mov = "380V";
				}
				String pv = "";
				if (p == 16) {
					pv = "A";
				} else if (p == 17) {
					pv = "B";
				} else if (p == 18) {
					pv = "C";
				}
				mon.setMonAlarmsInfo("第" + (EncodeUtils.getLong(mes, 40) + 1) + "回路" + pv + "相电流过载报警, 当前报警值为"
						+ dFormat.format(EncodeUtils.byte2float(mes, 56)));
			} else if (mes[12] == 21) {
				String pv = "";
				long p = EncodeUtils.getLong(mes, 48);
				if (p == 16) {
					pv = "A";
				} else if (p == 17) {
					pv = "B";
				} else if (p == 18) {
					pv = "C";
				}
				mon.setMonAlarmsInfo("总电压过高报警," + pv + "相,当前报警值 为" + dFormat.format(EncodeUtils.byte2float(mes, 56)));
			} else if (mes[12] == 20) {
				String pv = "";
				long p = EncodeUtils.getLong(mes, 48);
				if (p == 16) {
					pv = "A";
				} else if (p == 17) {
					pv = "B";
				} else if (p == 18) {
					pv = "C";
				}
				mon.setMonAlarmsInfo("总电流过载报警," + pv + "相,当前报警值为" + dFormat.format(EncodeUtils.byte2float(mes, 56)));
			} else if (mes[12] == 22) {
				String pv = "";
				long p = EncodeUtils.getLong(mes, 48);
				if (p == 16) {
					pv = "A";
				} else if (p == 17) {
					pv = "B";
				} else if (p == 18) {
					pv = "C";
				}
				mon.setMonAlarmsInfo("总电压欠压报警," + pv + "相,当前报警值为" + dFormat.format(EncodeUtils.byte2float(mes, 56)));
			} else if (mes[12] == 24) {
				mon.setMonAlarmsInfo("湿度过高报警,当前报警值为" + dFormat.format(EncodeUtils.byte2float(mes, 56)));
			} else if (mes[12] == 49) {
				mon.setMonAlarmsInfo("设备停电报警");
			} else if (mes[12] == 25) {

				mon.setMonAlarmsInfo("第" + (EncodeUtils.getLong(mes, 40) + 1) + "回路漏电跳闸报警,当前报警值为"
						+ dFormat.format(EncodeUtils.byte2float(mes, 56)));
			} else if (mes[12] == 32) {
				// long monV = EncodeUtils.getLong(mes, 44);
				long p = EncodeUtils.getLong(mes, 48);
				long mT = EncodeUtils.getLong(mes, 40) + 1;

				String pv = "";
				if (p == 16) {
					pv = "A相,";
				} else if (p == 17) {
					pv = "B相,";
				} else if (p == 18) {
					pv = "C相,";
				} else if (p == 19) {
					pv = "零线,";
				}
				String T = "";
				if (mT == 4294967295L) {
					T = "输入线";
				} else {
					T = "第" + mT;
				}
				mon.setMonAlarmsInfo(T + "回路" + pv + "温度过高报警,当前报警值为" + dFormat.format(EncodeUtils.byte2float(mes, 56)));
			} else if (mes[12] == 33) {

				long p = EncodeUtils.getLong(mes, 48);

				String pv = "";
				if (p == 16) {
					pv = "A";
				} else if (p == 17) {
					pv = "B";
				} else if (p == 18) {
					pv = "C";
				}
				mon.setMonAlarmsInfo("第" + (EncodeUtils.getLong(mes, 40) + 1) + "回路" + pv + "相打火报警");
			} else if (mes[12] == 34) {
				// long monV = EncodeUtils.getLong(mes, 44);
				long p = EncodeUtils.getLong(mes, 48);
				// String mov = "";
				// if (monV == 16) {
				// mov = "220V";
				// } else if (monV == 17) {
				// mov = "380V";
				// }
				String pv = "";
				if (p == 16) {
					pv = "A";
				} else if (p == 17) {
					pv = "B";
				} else if (p == 18) {
					pv = "C";
				}
				mon.setMonAlarmsInfo("第" + (EncodeUtils.getLong(mes, 40) + 1) + "回路" + pv + "相电流值超过预警值报警,当前报警值为"
						+ dFormat.format(EncodeUtils.byte2float(mes, 56)));

			}
			// if(mon.getMonAlarmsInfo()!=null){
			// JPushClientExample.jpush(mon.getMonAlarmsInfo());
			// }
            //推送报警信息给相关用户
			List<Map<String,Object>> deviceInfoList=userDao.selectDeviceTokenListByMonId(mon.getMonId());
			sendAlarmInfoToUser(mon.getMonAlarmsInfo(),deviceInfoList);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return mon;
	}

	// 推送报警信息给app客户端
	public void sendAlarmInfoToUser(String alarmInfo,List<Map<String, Object>> deviceInfoList) {
		
		UPushClient androidClient = new UPushClient(UPushConfig.appkey_android, UPushConfig.appMasterSecret_android);
		UPushClient iosClient = new UPushClient(UPushConfig.appkey_ios, UPushConfig.appMasterSecret_ios);
		
		List<String> androidDevices=new ArrayList<String>();
		List<String> iosDevices=new ArrayList<String>();
		//给手机设备分类
		for(Map<String,Object> map:deviceInfoList) {
			if((int)map.get("phoneType")==1)
				androidDevices.add((String) map.get("deviceToken"));
			else if((int)map.get("phoneType")==2)
				iosDevices.add((String)map.get("deviceToken"));
		}
		//向android推送信息
		try {
			if(!androidDevices.isEmpty()) {
			     androidClient.sendAndroidListcast(androidDevices, alarmInfo);
			}
		} catch (Exception e) {
			System.out.println("send android alarm error");
			e.printStackTrace();
		}
        //向ios推送信息
		try {
			if(!iosDevices.isEmpty()) {
			     iosClient.sendIOSListcast(iosDevices, alarmInfo);
			}
		} catch (Exception e) {
			System.out.println("send ios alarm error");
			e.printStackTrace();
		}
	}
	
	
	
}
