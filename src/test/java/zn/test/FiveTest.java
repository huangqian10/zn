/**
 * 
 */
package zn.test;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.web.servlet.view.velocity.VelocityConfig;

import com.alibaba.fastjson.JSONArray;

import zn.dao.MonitorDao;
import zn.dao.UserDao;
import zn.entity.XmlMonitor;
import zn.until.EncodeUtils;
import zn.until.UdpClientSocket;

/**
 * @author hq
 *
 */
public class FiveTest {
	
	@Test
	public void test() throws UnsupportedEncodingException{
//	     ApplicationContext    ac = new ClassPathXmlApplicationContext("spring-mybatis.xml");  
//	     UserDao userDao = ac.getBean("userDao",UserDao.class);
//	     MonitorDao monitorDao = ac.getBean("monitorDao",MonitorDao.class);
//	     long time=System.currentTimeMillis();
//	 	userDao.deleteUserMonitor();
//	 	List<XmlMonitor> xmlList = new ArrayList<XmlMonitor>();
//	 	XmlMonitor xmlMonitor=new XmlMonitor();
//	 	xmlMonitor.setSBBH("866ab426-bd8e-4377-aee9-694a688a239d");
//	 	XmlMonitor xmlMonitor2=new XmlMonitor();
//	 	xmlMonitor2.setSBBH("309046f6-2050-477c-9e10-f0f3ccb24b50");
//	 	XmlMonitor xmlMonitor3=new XmlMonitor();
//	 	xmlMonitor3.setSBBH("d7204362-ea8e-453e-8f21-db3b831e59d1");
//	 	XmlMonitor xmlMonitor4=new XmlMonitor();
//	 	xmlMonitor4.setSBBH("47f396bf-e8ac-43d5-a065-1fdb33355a23");
//	 	XmlMonitor xmlMonitor5=new XmlMonitor();
//	 	xmlMonitor5.setSBBH("23b2040b-2a9f-4e12-96d3-e7abfc15bf3b");
//	 	XmlMonitor xmlMonitor6=new XmlMonitor();
//	 	xmlMonitor6.setSBBH("c4fa06cd-5bfc-42d1-b9ec-366929057569");
//	 	xmlList.add(xmlMonitor);
//	 	xmlList.add(xmlMonitor2);
//	 	xmlList.add(xmlMonitor3);
//	 	xmlList.add(xmlMonitor4);
//	 	xmlList.add(xmlMonitor5);
//	 	xmlList.add(xmlMonitor6);
//		List<String> wen = new ArrayList<String>();
//		for (XmlMonitor x : xmlList) {
//			wen.add(x.getSBBH());
//		}
//		if(!wen.isEmpty()){
//		List<Integer> monIdList= monitorDao.selectMonIdByMonNumber(wen);
//		Map<String, Object> map=new HashMap<String, Object>();
//		map.put("userId",1);
//		map.put("monList",monIdList);
//		
//		userDao.userAddMon(map);
//		}
//	
//	    long time2=System.currentTimeMillis();
//	    System.out.println(time2-time);
	}
	
	@Test
	public void  test2() throws Exception{
		String hex1="53464252   01 00 00 00 0a 00 00 00 05 00 05 00  01 00 10 00 75 7d 83 41 01 00 11 00 75 7d 83 41 01 00 12 00 75 7d 83 41 01 00 13 00 6d d6 83 41 02 00 00 01 cd cc c8 41  c4fa06cd5bfc42d1b9ec366929057569";
		String hex2="53 46 42 52  00 00 00 00 1e 00 00 00  13 00 00 00 df 07 00 00 07 00 00 00 0f 00 00 00 0b 00 00 00 07 00 00 00 1a 00 00 00 01 00 00 00  11 00 00 00  12 00 00 00   00 00 00 00 00 b4 4c 413f221e162b304a5dad4825290a6c3f2c";
		String hex3="53 46 42 52  00 00 00 00 1e 00 00 00    15 00 00 00 df 07 00 00 07 00 00 00 0f 00 00 00 0a 00 00 00 0f 00 00 00 0c 00 00 00 00 00 00 00 00 00 00 00  10 00 00 00    01 00 00 00 60 26 62 433f221e162b304a5dad4825290a6c3f2c";
		String hex4="53 46 42 52  00 00 00 00   1e 00 00 00     14 00 00 00 df 07 00 00 07 00 00 00 0f 00 00 00 0a 00 00 00 31 00 00 00 20 00 00 00  00 00 00 00 00 00 00 00  11 00 00 00   00 00 00 00 ff ad 81 423f221e162b304a5dad4825290a6c3f2c";
		String hex5="53 46 42 52   00 00 00 00  1e 00 00 00  16 00 00 00 df 07 00 00 07 00 00 00 0f 00 00 00 09 00 00 00 17 00 00 00 17 00 00 00  00 00 00 00 00 00 00 00  10 00 00 00     00 00 00 00 e0 1d 63 433f221e162b304a5dad4825290a6c3f2c";
		String hex6="53 46 42 52  00 00 00 00  1e 00 00 00   18 00 00 00 df 07 00 00 07 00 00 00 0f 00 00 00 0a 00 00 00 13 00 00 00 07 00 00 00  00 00 00 00 00 00 00 00 00 00 00 00 01 00 00 00 00 80 0b 423f221e162b304a5dad4825290a6c3f2c";
		String hex7="53 46 42 52  00 00 00 00  1e 00 00 00  31 00 00 00 df 07 00 00 07 00 00 00 0f 00 00 00 0a 00 00 00 13 00 00 00 07 00 00 00   00 00 00 00 00 00 00 00 00 00 00 00 01 00 00 00";
		String hex8="53 46 42 52  00 00 00 00 1e 00 00 00 19 00 00 00 df 07 00 00 07 00 00 00 0f 00 00 00 0b 00 00 00 07 00 00 00 1a 00 00 00 01 00 00 00 11 00 00 00 12 00 00 00   00 00 00 00 00 b4 4c 413f221e162b304a5dad4825290a6c3f2c";
		String hex9="53 46 42 52  00 00 00 00 1e 00 00 00  20 00 00 00 df 07 00 00 07 00 00 00 0f 00 00 00 0b 00 00 00 07 00 00 00 1a 00 00 00 01 00 00 00  11 00 00 00 12 00 00 00 00 00 00 00 00 b4 4c 413f221e162b304a5dad4825290a6c3f2c";
		String hex10="53 46 42 52  00 00 00 00  1e 00 00 00  21 00 00 00 df 07 00 00 07 00 00 00 0f 00 00 00 0b 00 00 00 1c 00 00 00 0a 00 00 00 01 00 00 00 11 00 00 00 12 00 00 00   01 00 00 003f221e162b304a5dad4825290a6c3f2c";
		String hex11="53 46 42 52  00 00 00 00 1e 00 00 00  22 00 00 00 df 07 00 00 07 00 00 00 0f 00 00 00 0b 00 00 00 07 00 00 00 1a 00 00 00 01 00 00 00  11 00 00 00  12 00 00 00  00 00 00 00 00 b4 4c 41 309046f62050477c9e10f0f3ccb24b50";
		String hex12="53 46 42 52  00 00 00 00 1e 00 00 00  23 00 00 00 df 07 00 00 07 00 00 00 0f 00 00 00 0b 00 00 00 07 00 00 00 1a 00 00 00 01 00 00 00 11 00 00 00  12 00 00 00  00 00 00 00  00 b4 4c 413f221e162b304a5dad4825290a6c3f2c";
		//System.out.println(hex1.replaceAll("\\s", ""));
		byte[] hex=EncodeUtils.hexDecode(hex1.replaceAll("\\s", ""));
		UdpClientSocket client = new UdpClientSocket(); 
	    String serverHost = "192.168.11.210";    
        int serverPort = 5003;   
		   client.send(serverHost, serverPort, hex); 
	}
	
	
	

	
	

}
