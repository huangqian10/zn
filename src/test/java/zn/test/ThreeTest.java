/**
 * 
 */
package zn.test;

import javax.annotation.Resource;

import org.junit.Test;

import zn.dao.LimitsDao;
import zn.dao.MonDateDao;
import zn.entity.MonAlarms;
import zn.entity.MonDate;
import zn.until.EncodeUtils;

/**
 * @author hq
 *
 */
public class ThreeTest {
	
	
			@Test
			public void test(){
				String input="53  46  42  52  01  00  00  00  03  00  00  00  06  00  00  00  00  00  00  00  91  50  64  43  00  00  00  00  00  00  00  00 00  00  00  00  00  00 00  00  00  00  00  00  00  00  DC  3D 00  00  00  00  00  00  00  00  00  00  00  00  00  00  00  0000  00 00  00  00  00  00  00  00  00  00  00  00  00  00  00 00  00  00  00  00  00  00  00  00  00  00  00  00  00  00  00 00 00  00  00  00  00  00  00  00  00  00  00  00  00  00  00 00  00  00  00  00  00  00  00  00  00  00  00  00  00  00  0000  00  00  00  00  00  00  00  00  00  00  00  00  00  00  00";
				String input2="53 46 42 52  01 00 00 00  02 00 00 00  0c 00 00 00 00 00 02 10";
			
				String input3="53 46 42 52   01 00 00 00   0a 00 00 00  01 00 00 00 01 02 10 00 00 00 00 40 01 02 11 00 00 00 00 40 01 02 03 00 00 00 00 40 01 01 13 00 00 00 00 40";			
				String input4="53 46 42 52 01 00 00 00 03 00 00 00 0C 00 00 00 01 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 A9 32 82 B9 C8 58 48 7A AE 68 CD 6D FD 12 34 B0";
				String input5="53 46 42 52 01 00 00 00 03 00 00 00 0C 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 0000 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00";
//				System.out.println(analysisHex(input));
//				System.out.println(analysisHex(input2));
//				System.out.println(analysisHex(input3));
				System.out.println(analysisHex(input4));
//				System.out.println(analysisHex(input5));
				byte[] mes=EncodeUtils.hexDecode(input4.replaceAll("\\s", ""));
			}
		
			
			
			public Object analysisHex(String hex){
				MonDate mon=new MonDate();
				byte[] mes=EncodeUtils.hexDecode(hex.replaceAll("\\s", "")); 
			
			    
		
			//读取电压电流实时值
			if(mes[8]==3){	
				if(mes[12]==6 ){	//单路或者6路
					//A相总电压
					mon.setAllAV(EncodeUtils.byte2float(mes, 16)+"");
					//B相总电压
					mon.setAllBV(EncodeUtils.byte2float(mes, 20)+"");
					//C相总电压
					mon.setAllCV(EncodeUtils.byte2float(mes, 24)+"");
					//A相总电流
					mon.setAllAA(EncodeUtils.byte2float(mes, 28)+"");
					//B相总电流
					mon.setAllBA(EncodeUtils.byte2float(mes, 32)+"");
					//C相总电流
					mon.setAllCA(EncodeUtils.byte2float(mes, 36)+"");
					//湿度值
					mon.setHumidity(EncodeUtils.byte2float(mes, 40)+"");
					//第一路电流
					mon.setA1(EncodeUtils.byte2float(mes, 44)+","+EncodeUtils.byte2float(mes, 48)+","+EncodeUtils.byte2float(mes, 52)+","+EncodeUtils.byte2float(mes, 116));
					//第二路电流
					mon.setA2(EncodeUtils.byte2float(mes, 56)+","+EncodeUtils.byte2float(mes, 60)+","+EncodeUtils.byte2float(mes, 64)+","+EncodeUtils.byte2float(mes, 120));
					//第三路电流
					mon.setA3(EncodeUtils.byte2float(mes, 68)+","+EncodeUtils.byte2float(mes, 72)+","+EncodeUtils.byte2float(mes, 76)+","+EncodeUtils.byte2float(mes, 124));
					//第四路电流
					mon.setA4(EncodeUtils.byte2float(mes, 80)+","+EncodeUtils.byte2float(mes, 84)+","+EncodeUtils.byte2float(mes, 88)+","+EncodeUtils.byte2float(mes, 128));
					//第五路电流
					mon.setA5(EncodeUtils.byte2float(mes, 92)+","+EncodeUtils.byte2float(mes, 96)+","+EncodeUtils.byte2float(mes, 100)+","+EncodeUtils.byte2float(mes, 132));
					//第六路电流
					mon.setA6(EncodeUtils.byte2float(mes, 104)+","+EncodeUtils.byte2float(mes, 108)+","+EncodeUtils.byte2float(mes, 112)+","+EncodeUtils.byte2float(mes, 136));				
				}else if(mes[12]==12){       //12路
					//第七路电流
					mon.setA7(EncodeUtils.byte2float(mes, 20)+","+EncodeUtils.byte2float(mes, 24)+","+EncodeUtils.byte2float(mes, 28)+","+EncodeUtils.byte2float(mes, 92));
					//第八电流
					mon.setA8(EncodeUtils.byte2float(mes, 32)+","+EncodeUtils.byte2float(mes, 36)+","+EncodeUtils.byte2float(mes, 40)+","+EncodeUtils.byte2float(mes, 96));
					//第九路电流
					mon.setA9(EncodeUtils.byte2float(mes, 44)+","+EncodeUtils.byte2float(mes, 48)+","+EncodeUtils.byte2float(mes, 52)+","+EncodeUtils.byte2float(mes, 100));
					//第十路电流
					mon.setA10(EncodeUtils.byte2float(mes, 56)+","+EncodeUtils.byte2float(mes, 60)+","+EncodeUtils.byte2float(mes, 64)+","+EncodeUtils.byte2float(mes, 104));
					//第十一路电流
					mon.setA11(EncodeUtils.byte2float(mes, 68)+","+EncodeUtils.byte2float(mes, 72)+","+EncodeUtils.byte2float(mes, 76)+","+EncodeUtils.byte2float(mes, 108));
					//第十二路电流
					mon.setA12(EncodeUtils.byte2float(mes, 80)+","+EncodeUtils.byte2float(mes, 84)+","+EncodeUtils.byte2float(mes, 88)+","+EncodeUtils.byte2float(mes, 112));			
				}
				
			}else if(mes[8]==10){
				 String T1=new String();
		           String T2=new String();
		           String T3=new String();
		           String T4=new String(); 
		           String T5=new String();
		           String T6=new String();
		           String T7=new String();
		           String T8=new String();
		           String T9=new String();
		           String T10=new String();
		           String T11=new String();
		           String T12=new String();  
		         
		           for(int i=16;i<mes.length-32;i=i+8){
		        	   if(mes[i]==1&&mes[i+3]==0){
		        		   if(mes[i+1]==1){
		        			  
		        			   T1=T1+pathT(mes[i+2])+EncodeUtils.byte2float(mes, i+4)+",";
		        		   }else if(mes[i+1]==2){
		        			
		        			   T2=T2+pathT(mes[i+2])+EncodeUtils.byte2float(mes, i+4)+",";
		        			
		        		   }else if(mes[i+1]==3){
		        			   T3=T3+pathT(mes[i+2])+EncodeUtils.byte2float(mes, i+4)+",";
		        		   }else if(mes[i+1]==4){
		        			   T4=T4+pathT(mes[i+2])+EncodeUtils.byte2float(mes, i+4)+",";
		        		   }else if(mes[i+1]==5){
		        			   T5=T5+pathT(mes[i+2])+EncodeUtils.byte2float(mes, i+4)+",";
		        		   }else if(mes[i+1]==6){
		        			   T6=T6+pathT(mes[i+2])+EncodeUtils.byte2float(mes, i+4)+",";
		        		   }else if(mes[i+1]==7){
		        			   T7=T7+pathT(mes[i+2])+EncodeUtils.byte2float(mes, i+4)+",";
		        		   }else if(mes[i+1]==8){
		        			   T8=T8+pathT(mes[i+2])+EncodeUtils.byte2float(mes, i+4)+",";
		        		   }else if(mes[i+1]==9){
		        			   T9=T9+pathT(mes[i+2])+EncodeUtils.byte2float(mes, i+4)+",";
		        		   }else if(mes[i+1]==10){
		        			   T10=T10+pathT(mes[i+2])+EncodeUtils.byte2float(mes, i+4)+",";
		        		   }else if(mes[i+1]==11){
		        			   T11=T11+pathT(mes[i+2])+EncodeUtils.byte2float(mes, i+4)+",";
		        		   }else if(mes[i+1]==12){
		        			   T12=T12+pathT(mes[i+2])+EncodeUtils.byte2float(mes, i+4)+",";
		        		   }
				  
		        	   }else if(mes[i]==2&&mes[i+3]==0){
		        		   mon.setMonInT(EncodeUtils.byte2float(mes, i+4)+"");
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
			}else if(mes[8]==2){
			 String  states=EncodeUtils.byte2bits(mes[18])+EncodeUtils.byte2bits(mes[19]);
			 
			 mon.setMonSwitch(states.substring(states.length()-12, states.length()));
				
			}else if(mes[8]==30){
				MonAlarms monAlarms=analysisWarningHex(mes);
				return monAlarms;
			}
			return  mon;
			}
			
			
			public String pathT(int path){
				String  ff=new String();
				if(path==3){
					ff= "\"singleT\":";
				}else if(path==16){
					ff= "\"AT\":";
				}else if(path==17){
					ff= "\"BT\":";
				}else if(path==18){
					ff= "\"CT\":";
				}else if(path==19){
					ff= "\"NT\":";
				}
				return ff;
			}
			
			
			/**
			 * 分析报警信息
			 * @Title: analysisWarningHex 
			 * @Description: TODO
			 * @param  byte[]数组
			 * @return   MonAlarms
			 * @throws
			 */
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
