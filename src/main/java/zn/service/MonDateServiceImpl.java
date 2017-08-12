/**
 * 
 */
package zn.service;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.CopyOnWriteArrayList;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import zn.dao.MonDateDao;
import zn.dao.MonitorDao;
import zn.dao.UserDao;
import zn.entity.MonDate;
import zn.entity.MonShow;
import zn.entity.MonSon;
import zn.entity.MonT;
import zn.entity.Monitor;
import zn.entity.User;
import zn.entity.XUser;
import zn.entity.XmlMonSon;
import zn.listener.AnalysisInfoListener;
import zn.until.NoteResult;
import zn.until.NoteUtil;
import zn.until.UdpClientSocket;
import zn.until.WebService;

/**
 * @author hq
 *
 */
@Service("monDateService")
@Transactional
public class MonDateServiceImpl implements MonDateService {

	@Resource // 注入
	private MonDateDao monDateDao;

	@Resource // 注入
	private MonitorDao monitorDao;

	@Resource // 注入
	private UserDao userDao;

	public NoteResult selectMonDateById(Integer monId) {
		NoteResult note = new NoteResult();
		if (monId == null) {
			note.setStatus(1);
			note.setMsg("参数不能为空");
			note.setData("");
		} else {
			MonDate mon = monDateDao.selectMonDateById(monId);
			Monitor monitor = monitorDao.findMonById(monId);
			MonShow monShow = new MonShow();
			List<MonSon> monsonList = new CopyOnWriteArrayList<MonSon>();
			List<MonSon> monsonListAll = new CopyOnWriteArrayList<MonSon>();
			int monRoute = monitor.getMonRoute();
			String xmlListStr=new WebService().ITPCDeviceChannelInfoGetByIDForJson(monitor.getMonNumber());
			 List<XmlMonSon> xmlMonList=(List<XmlMonSon>)JSONArray.parseArray(xmlListStr,XmlMonSon.class);
			List<Integer> td=new ArrayList<Integer>(); 
			 for(XmlMonSon xmlMonSon:xmlMonList){
				td.add(xmlMonSon.getTDHM());
			}
	
			 if (mon != null) {
				 monsonList = addMonSonList(monsonList, mon, monRoute);

			}
			
			for(MonSon monson33:monsonList){
				if(td.contains(monson33.getWay())){
					monsonListAll.add(monson33);
				}
			}
			
			monShow.setAllAA(mon.getAllAA());
			monShow.setAllBA(mon.getAllBA());
			monShow.setAllCA(mon.getAllCA());
			monShow.setAllAV(mon.getAllAV());
			monShow.setAllBV(mon.getAllBV());
			monShow.setAllCV(mon.getAllCV());
			monShow.setHumidity(mon.getHumidity());
			monShow.setList(monsonListAll);
			monShow.setMonAlias(monitor.getMonAlias());
			monShow.setMonId(monitor.getMonId());
			monShow.setMonInstall(monitor.getMonInstall());
			monShow.setMonIP(monitor.getMonIP());
			monShow.setMonModel(monitor.getMonModel());
			monShow.setMonName(monitor.getMonName());
			monShow.setMonNumber(monitor.getMonNumber());
			monShow.setMonPlace(monitor.getMonPlace());
			monShow.setMonPlaceName(monitor.getMonPlaceName());
			monShow.setMonRoute(monitor.getMonRoute());
			monShow.setMonType(monitor.getMonType());
			monShow.setMonInT(mon.getMonInT());
			monShow.setMonState(monitor.getMonState());
			note.setStatus(0);
			note.setMsg("查询成功");
			note.setData(monShow);
		}
		return note;
	}

	/**
	 * 添加设备的子列表 @Title: addMonSonList @Description: TODO @param
	 * monsonList @param mon @param monRoute @return @throws
	 */
	public List<MonSon> addMonSonList(List<MonSon> monsonList, MonDate mon, int monRoute) {

		// 第一路
		MonSon monson1 = new MonSon();
		monson1.setWay(1);
		monson1.setName(mon.getName1());
		String A1 = mon.getA1();
		if (A1 != null) {
			monson1.setAA(A1.split(",")[0]);
			monson1.setBA(A1.split(",")[1]);
			monson1.setCA(A1.split(",")[2]);
			monson1.setELA(A1.split(",")[3]);
		}
		if (mon.getMonSwitch() != null) {
			monson1.setState(mon.getMonSwitch().substring(11, 12));
		}
		if (mon.getT1() != null && !"".equals(mon.getT1())) {
			monson1.setT(JSON.parseObject("{" + mon.getT1().substring(0, mon.getT1().length() - 1) + "}", MonT.class));
		}
		monsonList.add(monson1);
		if (monRoute == 1) {
			return monsonList;
		}
		// 第二路
		MonSon monson2 = new MonSon();
		monson2.setWay(2);
		monson2.setName(mon.getName2());
		String A2 = mon.getA2();
		if (A2 != null) {
			monson2.setAA(A2.split(",")[0]);
			monson2.setBA(A2.split(",")[1]);
			monson2.setCA(A2.split(",")[2]);
			monson2.setELA(A2.split(",")[3]);
		}
		if (mon.getMonSwitch() != null) {
			monson2.setState(mon.getMonSwitch().substring(10, 11));
		}
		if (mon.getT2() != null && !"".equals(mon.getT2())) {
			monson2.setT(JSON.parseObject("{" + mon.getT2().substring(0, mon.getT2().length() - 1) + "}", MonT.class));
		}
		monsonList.add(monson2);
		// 第三路
		MonSon monson3 = new MonSon();
		monson3.setWay(3);
		monson3.setName(mon.getName3());
		String A3 = mon.getA3();
		if (A3 != null) {
			monson3.setAA(A3.split(",")[0]);
			monson3.setBA(A3.split(",")[1]);
			monson3.setCA(A3.split(",")[2]);
			monson3.setELA(A3.split(",")[3]);
		}
		if (mon.getMonSwitch() != null) {
			monson3.setState(mon.getMonSwitch().substring(9, 10));
		}
		if (mon.getT3() != null && !"".equals(mon.getT3())) {
			monson3.setT(JSON.parseObject("{" + mon.getT3().substring(0, mon.getT3().length() - 1) + "}", MonT.class));
		}
		monsonList.add(monson3);
		// 第四路
		MonSon monson4 = new MonSon();
		monson4.setWay(4);
		monson4.setName(mon.getName4());
		String A4 = mon.getA4();
		if (A4 != null) {
			monson4.setAA(A4.split(",")[0]);
			monson4.setBA(A4.split(",")[1]);
			monson4.setCA(A4.split(",")[2]);
			monson4.setELA(A4.split(",")[3]);
		}
		if (mon.getMonSwitch() != null) {
			monson4.setState(mon.getMonSwitch().substring(8, 9));
		}
		if (mon.getT4() != null && !"".equals(mon.getT4())) {
			monson4.setT(JSON.parseObject("{" + mon.getT4().substring(0, mon.getT4().length() - 1) + "}", MonT.class));
		}
		monsonList.add(monson4);
		// 第五路
		MonSon monson5 = new MonSon();
		monson5.setWay(5);
		monson5.setName(mon.getName5());
		String A5 = mon.getA5();
		if (A5 != null) {
			monson5.setAA(A5.split(",")[0]);
			monson5.setBA(A5.split(",")[1]);
			monson5.setCA(A5.split(",")[2]);
			monson5.setELA(A5.split(",")[3]);
		}
		if (mon.getMonSwitch() != null) {
			monson5.setState(mon.getMonSwitch().substring(7, 8));
		}
		if (mon.getT5() != null && !"".equals(mon.getT5())) {
			monson5.setT(JSON.parseObject("{" + mon.getT5().substring(0, mon.getT5().length() - 1) + "}", MonT.class));
		}
		monsonList.add(monson5);
		// 第六路
		MonSon monson6 = new MonSon();
		monson6.setWay(6);
		monson6.setName(mon.getName6());
		String A6 = mon.getA6();
		if (A6 != null) {
			monson6.setAA(A6.split(",")[0]);
			monson6.setBA(A6.split(",")[1]);
			monson6.setCA(A6.split(",")[2]);
			monson6.setELA(A6.split(",")[3]);
		}
		if (mon.getMonSwitch() != null) {
			monson6.setState(mon.getMonSwitch().substring(6, 7));
		}
		if (mon.getT6() != null && !"".equals(mon.getT6())) {
			monson6.setT(JSON.parseObject("{" + mon.getT6().substring(0, mon.getT6().length() - 1) + "}", MonT.class));
		}
		monsonList.add(monson6);
		if (monRoute == 6) {
			return monsonList;
		}
		// 第七路
		MonSon monson7 = new MonSon();
		monson7.setWay(7);
		monson7.setName(mon.getName7());
		String A7 = mon.getA7();
		if (A7 != null) {
			monson7.setAA(A7.split(",")[0]);
			monson7.setBA(A7.split(",")[1]);
			monson7.setCA(A7.split(",")[2]);
			monson7.setELA(A7.split(",")[3]);
		}
		if (mon.getMonSwitch() != null) {
			monson7.setState(mon.getMonSwitch().substring(5, 6));
		}
		if (mon.getT7() != null && !"".equals(mon.getT7())) {
			monson7.setT(JSON.parseObject("{" + mon.getT7().substring(0, mon.getT7().length() - 1) + "}", MonT.class));
		}
		monsonList.add(monson7);
		// 第八路
		MonSon monson8 = new MonSon();
		monson8.setWay(8);
		monson8.setName(mon.getName8());
		String A8 = mon.getA8();
		if (A8 != null) {
			monson8.setAA(A8.split(",")[0]);
			monson8.setBA(A8.split(",")[1]);
			monson8.setCA(A8.split(",")[2]);
			monson8.setELA(A8.split(",")[3]);
		}
		if (mon.getMonSwitch() != null) {
			monson8.setState(mon.getMonSwitch().substring(4, 5));
		}
		if (mon.getT8() != null && !"".equals(mon.getT8())) {
			monson8.setT(JSON.parseObject("{" + mon.getT8().substring(0, mon.getT8().length() - 1) + "}", MonT.class));
		}
		monsonList.add(monson8);
		// 第九路
		MonSon monson9 = new MonSon();
		monson9.setWay(9);
		monson9.setName(mon.getName9());
		String A9 = mon.getA9();
		if (A9 != null) {
			monson9.setAA(A9.split(",")[0]);
			monson9.setBA(A9.split(",")[1]);
			monson9.setCA(A9.split(",")[2]);
			monson9.setELA(A9.split(",")[3]);
		}
		if (mon.getMonSwitch() != null) {
			monson9.setState(mon.getMonSwitch().substring(3, 4));
		}
		if (mon.getT9() != null && !"".equals(mon.getT9())) {
			monson9.setT(JSON.parseObject("{" + mon.getT9().substring(0, mon.getT9().length() - 1) + "}", MonT.class));
		}
		monsonList.add(monson9);
		// 第十路
		MonSon monson10 = new MonSon();
		monson10.setWay(10);
		monson10.setName(mon.getName10());
		String A10 = mon.getA10();
		if (A10 != null) {
			monson10.setAA(A10.split(",")[0]);
			monson10.setBA(A10.split(",")[1]);
			monson10.setCA(A10.split(",")[2]);
			monson10.setELA(A10.split(",")[3]);
		}
		if (mon.getMonSwitch() != null) {
			monson10.setState(mon.getMonSwitch().substring(2, 3));
		}
		if (mon.getT10() != null && !"".equals(mon.getT10())) {
			monson10.setT(
					JSON.parseObject("{" + mon.getT10().substring(0, mon.getT10().length() - 1) + "}", MonT.class));
		}
		monsonList.add(monson10);
		// 第十一路
		MonSon monson11 = new MonSon();
		monson11.setWay(11);
		monson11.setName(mon.getName11());
		String A11 = mon.getA11();
		if (A11 != null) {
			monson11.setAA(A11.split(",")[0]);
			monson11.setBA(A11.split(",")[1]);
			monson11.setCA(A11.split(",")[2]);
			monson11.setELA(A11.split(",")[3]);
		}
		if (mon.getMonSwitch() != null) {
			monson11.setState(mon.getMonSwitch().substring(1, 2));
		}
		if (mon.getT11() != null && !"".equals(mon.getT11())) {
			monson11.setT(
					JSON.parseObject("{" + mon.getT11().substring(0, mon.getT11().length() - 1) + "}", MonT.class));
		}
		monsonList.add(monson11);
		// 第十二路
		MonSon monson12 = new MonSon();
		monson12.setWay(12);
		monson12.setName(mon.getName12());
		String A12 = mon.getA12();
		if (A12 != null) {
			monson12.setAA(A12.split(",")[0]);
			monson12.setBA(A12.split(",")[1]);
			monson12.setCA(A12.split(",")[2]);
			monson12.setELA(A12.split(",")[3]);
		}
		if (mon.getMonSwitch() != null) {
			monson12.setState(mon.getMonSwitch().substring(0, 1));
		}
		if (mon.getT12() != null && !"".equals(mon.getT12())) {
			monson12.setT(
					JSON.parseObject("{" + mon.getT12().substring(0, mon.getT12().length() - 1) + "}", MonT.class));
		}
		monsonList.add(monson12);
		return monsonList;
	}

	/**
	 * 设置设备开关
	 */
	public NoteResult setMonSwitch(String switchState, Integer way, String password, Integer monId, Integer userId) {
		NoteResult note = new NoteResult();
		XUser user = userDao.selectUserById(userId);
		if (monId == null || switchState == null || password == null || userId == null || way == null) {
			note.setStatus(1);
			note.setMsg("参数不能为空");
			note.setData("");
		} else if (!password.equals(user.getPassword())) {
			note.setStatus(2);
			note.setMsg("密码错误");
			note.setData("");
		} else if (!switchState.matches("^[01]$")) {
			note.setStatus(3);
			note.setMsg("参数错误");
			note.setData("");
		} else {
			Map<String, Object> map = monDateDao.selectSwitchAndNumber(monId);
			StringBuilder monSwitch = new StringBuilder((String) map.get("monSwitch"));
		
			String json = "[{\"monNumber\":\"" + map.get("monNumber") + "\",\"way\":" + way + ",\"switchState\":"
					+ switchState + "}]";
			try {
				InputStream inStream = AnalysisInfoListener.class.getClassLoader()
						.getResourceAsStream("./udp.properties");
			
				Properties prop = new Properties();
				prop.load(inStream);
				int serverPort = Integer.valueOf(prop.getProperty("serverPort"));
				String serverHost = prop.getProperty("serverHost");
				UdpClientSocket client = new UdpClientSocket();
				client.send(serverHost, serverPort, (json).getBytes());
			} catch (Exception e) {
				note.setStatus(4);
				note.setMsg("操作失败");
				note.setData("");
				e.printStackTrace();
				return note;
			}
		
			monDateDao.changeMonSwitch(monSwitch.replace(12 - way, 12-way+1 , switchState).toString(), monId);
			note.setStatus(0);
			note.setMsg("操作成功");
			note.setData("");
		}

		return note;
	}

	/**
	 * 修改子设备的名字
	 */
	public NoteResult setMonDateName(String monDateName, Integer route, Integer monId) {
		NoteResult note = new NoteResult();
		if (monId == null || monDateName == null || route == null) {
			note.setStatus(1);
			note.setMsg("参数不能为空");
			note.setData("");
		} else {
			MonDate mondate = new MonDate();
			mondate.setMonId(monId);
			if (route == 1) {
				mondate.setName1(monDateName);
			} else if (route == 2) {
				mondate.setName2(monDateName);
			} else if (route == 3) {
				mondate.setName3(monDateName);
			} else if (route == 4) {
				mondate.setName4(monDateName);
			} else if (route == 5) {
				mondate.setName5(monDateName);
			} else if (route == 6) {
				mondate.setName6(monDateName);
			} else if (route == 7) {
				mondate.setName7(monDateName);
			} else if (route == 8) {
				mondate.setName8(monDateName);
			} else if (route == 9) {
				mondate.setName9(monDateName);
			} else if (route == 10) {
				mondate.setName10(monDateName);
			} else if (route == 11) {
				mondate.setName11(monDateName);
			} else if (route == 12) {
				mondate.setName12(monDateName);
			}
			monDateDao.changeMonDate(mondate);
			note.setStatus(0);
			note.setMsg("更改成功");
			note.setData("");
		}
		return note;
	}

}
