/**
 * 
 */
package zn.test;

import org.junit.Test;

import zn.entity.MonAlarms;
import zn.until.EncodeUtils;


/**
 * @author hq
 *
 */
public class FourTest {
			@Test()	
			public void test(){
				String hex="53 46 42 52  00 00 00 00 1e 00 00 00 10 00 00 00 df 07 00 00 07 00 00 00 0f 00 00 00 0a 00 00 00 13 00 00 00 07 00 00 00  00 00 00 00 00 00 00 00 00 00 00 00 01 00 00 00 00 80 0b 42";
				String hex2="53 46 42 52  00 00 00 00 1e 00 00 00  13 00 00 00 df 07 00 00 07 00 00 00 0f 00 00 00 0b 00 00 00 07 00 00 00 1a 00 00 00 01 00 00 00  11 00 00 00  12 00 00 00   00 00 00 00 00 b4 4c 41 ";
				String hex3="53 46 42 52  00 00 00 00 1e 00 00 00    15 00 00 00 df 07 00 00 07 00 00 00 0f 00 00 00 0a 00 00 00 0f 00 00 00 0c 00 00 00 00 00 00 00 00 00 00 00  10 00 00 00    01 00 00 00 60 26 62 43";
				String hex4="53 46 42 52  00 00 00 00   1e 00 00 00     14 00 00 00 df 07 00 00 07 00 00 00 0f 00 00 00 0a 00 00 00 31 00 00 00 20 00 00 00  00 00 00 00 00 00 00 00  11 00 00 00   00 00 00 00 ff ad 81 42";
				String hex5="53 46 42 52   00 00 00 00  1e 00 00 00  16 00 00 00 df 07 00 00 07 00 00 00 0f 00 00 00 09 00 00 00 17 00 00 00 17 00 00 00  00 00 00 00 00 00 00 00  10 00 00 00     00 00 00 00 e0 1d 63 43";
				String hex6="53 46 42 52  00 00 00 00  1e 00 00 00   18 00 00 00 df 07 00 00 07 00 00 00 0f 00 00 00 0a 00 00 00 13 00 00 00 07 00 00 00  00 00 00 00 00 00 00 00 00 00 00 00 01 00 00 00 00 80 0b 42";
				String hex7="53 46 42 52  00 00 00 00  1e 00 00 00  31 00 00 00 df 07 00 00 07 00 00 00 0f 00 00 00 0a 00 00 00 13 00 00 00 07 00 00 00   00 00 00 00 00 00 00 00 00 00 00 00 01 00 00 00";
				String hex8="53 46 42 52  00 00 00 00 1e 00 00 00 19 00 00 00 df 07 00 00 07 00 00 00 0f 00 00 00 0b 00 00 00 07 00 00 00 1a 00 00 00 01 00 00 00 11 00 00 00 12 00 00 00   00 00 00 00 00 b4 4c 41";
				String hex9="53 46 42 52  00 00 00 00 1e 00 00 00  20 00 00 00 df 07 00 00 07 00 00 00 0f 00 00 00 0b 00 00 00 07 00 00 00 1a 00 00 00 01 00 00 00  11 00 00 00 12 00 00 00 00 00 00 00 00 b4 4c 41";
				String hex10="53 46 42 52  00 00 00 00  1e 00 00 00  21 00 00 00 df 07 00 00 07 00 00 00 0f 00 00 00 0b 00 00 00 1c 00 00 00 0a 00 00 00 01 00 00 00 11 00 00 00 12 00 00 00   01 00 00 00";
				String hex11="53 46 42 52  00 00 00 00 1e 00 00 00  22 00 00 00 df 07 00 00 07 00 00 00 0f 00 00 00 0b 00 00 00 07 00 00 00 1a 00 00 00 01 00 00 00  11 00 00 00  12 00 00 00  00 00 00 00 00 b4 4c 41 ";
				String hex12="53 46 42 52  00 00 00 00 1e 00 00 00  23 00 00 00 df 07 00 00 07 00 00 00 0f 00 00 00 0b 00 00 00 07 00 00 00 1a 00 00 00 01 00 00 00 11 00 00 00  12 00 00 00  00 00 00 00  00 b4 4c 41";
				//				byte[] mes=EncodeUtils.hexDecode(hex.replaceAll("\\s", "")); 
//				System.out.println(analysisWarningHex(mes));
//				System.out.println(analysisWarningHex(EncodeUtils.hexDecode(hex2.replaceAll("\\s", ""))));	
//				System.out.println(analysisWarningHex(EncodeUtils.hexDecode(hex3.replaceAll("\\s", ""))));	
//				System.out.println(analysisWarningHex(EncodeUtils.hexDecode(hex4.replaceAll("\\s", ""))));		
//				System.out.println(analysisWarningHex(EncodeUtils.hexDecode(hex5.replaceAll("\\s", ""))));	
//				System.out.println(analysisWarningHex(EncodeUtils.hexDecode(hex6.replaceAll("\\s", ""))));	
//				System.out.println(analysisWarningHex(EncodeUtils.hexDecode(hex7.replaceAll("\\s", ""))));	
//				System.out.println(analysisWarningHex(EncodeUtils.hexDecode(hex8.replaceAll("\\s", ""))));	
//				System.out.println(analysisWarningHex(EncodeUtils.hexDecode(hex9.replaceAll("\\s", ""))));	
//				System.out.println(analysisWarningHex(EncodeUtils.hexDecode(hex10.replaceAll("\\s", ""))));	
//				System.out.println(analysisWarningHex(EncodeUtils.hexDecode(hex11.replaceAll("\\s", ""))));	
				System.out.println(analysisWarningHex(EncodeUtils.hexDecode(hex12.replaceAll("\\s", ""))));	
			}
			
			public MonAlarms  analysisWarningHex(byte[] mes){
				MonAlarms mon=new MonAlarms();
				String monAlarmsTM=EncodeUtils.getLong(mes, 16)+"-"+EncodeUtils.getLong(mes, 20)+"-"+EncodeUtils.getLong(mes, 24)+" "+EncodeUtils.getLong(mes, 28)+":"+EncodeUtils.getLong(mes, 32)+":"+EncodeUtils.getLong(mes, 36);
				mon.setMonAlarmsTM(monAlarmsTM);
				mon.setMonAlarmsType(Long.toHexString(EncodeUtils.getLong(mes, 12)));
				//主动上传温度告警信息
				if(mes[12]==16){	
					mon.setMonAlarmsInfo("主动上传温度告警,温度值为"+EncodeUtils.byte2float(mes, 56));					
				}else if(mes[12]==19){             //电流过载信息				
					long monV=EncodeUtils.getLong(mes, 44);
					long p=EncodeUtils.getLong(mes, 48);
					String mov="";
					if(monV==16){
						mov="220V";
					}else if(monV==17){
						mov="380V";
					}
					String pv="";
					if(p==16){
						pv="A";
					}else if(p==17){
						pv="B";					
					}else if(p==18){
						pv="C";
					}	
					mon.setMonAlarmsInfo("通道"+EncodeUtils.getLong(mes, 40)+"主动上传电流过载报警,该路电压为"+mov+","+pv+"相,电流值为"+EncodeUtils.byte2float(mes, 56));			
				}else if(mes[12]==21){
					String pv="";
					long p=EncodeUtils.getLong(mes, 48);
					if(p==16){
						pv="A";
					}else if(p==17){
						pv="B";					
					}else if(p==18){
						pv="C";
					}
					mon.setMonAlarmsInfo("主动上传总电压过高告警,"+pv+"相,电压值为"+EncodeUtils.byte2float(mes, 56));
				}else if(mes[12]==20){
					String pv="";
					long p=EncodeUtils.getLong(mes, 48);
					if(p==16){
						pv="A";
					}else if(p==17){
						pv="B";					
					}else if(p==18){
						pv="C";
					}
					mon.setMonAlarmsInfo("主动上传总电流过载告警,"+pv+"相,电流值为"+EncodeUtils.byte2float(mes, 56));
				}else if(mes[12]==22){
					String pv="";
					long p=EncodeUtils.getLong(mes, 48);
					if(p==16){
						pv="A";
					}else if(p==17){
						pv="B";					
					}else if(p==18){
						pv="C";
					}
					mon.setMonAlarmsInfo("主动上传总电压过低告警,"+pv+"相,电压值为"+EncodeUtils.byte2float(mes, 56));
				}else if(mes[12]==24){
					mon.setMonAlarmsInfo("主动上传湿度过高告警,湿度值为"+EncodeUtils.byte2float(mes, 56));
				}else if(mes[12]==55){
					mon.setMonAlarmsInfo("停电报警");				
				}else if(mes[12]==25){
					long monV=EncodeUtils.getLong(mes, 44);
					long p=EncodeUtils.getLong(mes, 48);
					String mov="";
					if(monV==16){
						mov="220V";
					}else if(monV==17){
						mov="380V";
					}
					String pv="";
					if(p==16){
						pv="A";
					}else if(p==17){
						pv="B";					
					}else if(p==18){
						pv="C";
					}	
					mon.setMonAlarmsInfo("通道"+EncodeUtils.getLong(mes, 40)+"主动上传单路漏电报警,该路电压为"+mov+","+pv+"相,漏电流值为"+EncodeUtils.byte2float(mes, 56));			
				}else if(mes[12]==32){
					long monV=EncodeUtils.getLong(mes, 44);
					long p=EncodeUtils.getLong(mes, 48);
					long mT=EncodeUtils.getLong(mes, 40);
					String mov="";
					if(monV==16){
						mov="单相";
					}else if(monV==17){
						mov="三相";
					}
					String pv="";
					if(p==16){
						pv="A相,";
					}else if(p==17){
						pv="B相,";					
					}else if(p==18){
						pv="C相,";
					}else if(p==19){
						pv="零线,";
					}
					String T="";
					if(mT==4294967295L){
						T="输入线";
					}else{
						T="通道"+mT;
					}
					mon.setMonAlarmsInfo("通道"+T+"主动上传线路上温度过高报警,该路为"+mov+","+pv+"温度值为"+EncodeUtils.byte2float(mes, 56));	
				}else if(mes[12]==33){
					long monV=EncodeUtils.getLong(mes, 44);
					long p=EncodeUtils.getLong(mes, 48);
					String mov="";
					if(monV==16){
						mov="220V";
					}else if(monV==17){
						mov="380V";
					}
					String pv="";
					if(p==16){
						pv="A";
					}else if(p==17){
						pv="B";					
					}else if(p==18){
						pv="C";
					}	
					mon.setMonAlarmsInfo("通道"+EncodeUtils.getLong(mes, 40)+"打火报警,该路电压为"+mov+","+pv+"相");			
				}else if(mes[12]==34){
					long monV=EncodeUtils.getLong(mes, 44);
					long p=EncodeUtils.getLong(mes, 48);
					String mov="";
					if(monV==16){
						mov="220V";
					}else if(monV==17){
						mov="380V";
					}
					String pv="";
					if(p==16){
						pv="A";
					}else if(p==17){
						pv="B";					
					}else if(p==18){
						pv="C";
					}	
					mon.setMonAlarmsInfo("通道"+EncodeUtils.getLong(mes, 40)+"单路电流过高报警,该路电压为"+mov+","+pv+"相,电流值为"+EncodeUtils.byte2float(mes, 56));			
					
				}else if(mes[12]==35){
					long monV=EncodeUtils.getLong(mes, 44);
					long p=EncodeUtils.getLong(mes, 48);
					long mT=EncodeUtils.getLong(mes, 40);
					String mov="";
					if(monV==16){
						mov="单相";
					}else if(monV==17){
						mov="三相";
					}
					String pv="";
					if(p==16){
						pv="A相,";
					}else if(p==17){
						pv="B相,";					
					}else if(p==18){
						pv="C相,";
					}else if(p==19){
						pv="零线,";
					}
					String T="";
					if(mT==4294967295L){
						T="输入线";
					}else{
						T="通道"+mT;
					}
					mon.setMonAlarmsInfo(T+"线路上温度过高报警,该路为"+mov+","+pv+"温度值为"+EncodeUtils.byte2float(mes, 56));	
					
				}
				
				return mon;
			}
}
