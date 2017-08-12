/**
 * 
 */
package zn.test;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

import javax.xml.crypto.NodeSetData;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.junit.Test;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import com.alibaba.fastjson.JSON;

import zn.dao.MonDateDao;
import zn.dao.MonListDao;
import zn.dao.MonitorDao;
import zn.entity.MonList;
import zn.entity.Monitor;
import zn.entity.XmlMonitor;

/**
 * @author hq
 *
 */
public class SixTest {

	@Test
	public void test(){
		ApplicationContext    ac = new ClassPathXmlApplicationContext("spring-mybatis.xml");  
		List<String> wen=new ArrayList<String>();
		MonDateDao dao = ac.getBean("monDateDao",MonDateDao.class);
		MonitorDao dao2 = ac.getBean("monitorDao",MonitorDao.class);
		List<XmlMonitor> xmlList=new ArrayList<XmlMonitor>();
		XmlMonitor xml1=new XmlMonitor();
		xml1.setSBBH("9493b109f5d94e73a29ae4698268b63c");
		XmlMonitor xml2=new XmlMonitor();
		xml2.setSBBH("89221e162b304a5dad4825290a6ca92c");
		XmlMonitor xml3=new XmlMonitor();
		xml3.setSBBH("e2222cf1f89a415ba45ec5bf0e7a5fb7");
		XmlMonitor xml4=new XmlMonitor();
		xml4.setSBBH("682b3f94ce1a46bcaf8cb93f0e334a1b");
		
		xml4.setSBIP("192.168.111");
		xml4.setHLSL(6);
		XmlMonitor xml5=new XmlMonitor();
		xml5.setSBBH("1111");
		xml5.setHLSL(6);
		xmlList.add(xml5);
		xmlList.add(xml4);
		xmlList.add(xml3);
		xmlList.add(xml2);
		xmlList.add(xml1);
		for(XmlMonitor x:xmlList){
			wen.add(x.getSBBH());
		}
		
		List<String> wen2=new ArrayList<String>(wen);
		List<String> wen3=new ArrayList<String>(wen);
		
		List<String>  wen4=dao2.selectAllmonNumber();
		List<Monitor>  list3=new ArrayList<Monitor>();
		
//		Monitor monitor=new Monitor();
//		monitor.setMonNumber("aaa");
//		
//		Monitor monitor2=new Monitor();
//		monitor2.setMonNumber("2222");
//		List<Monitor> list=new ArrayList<Monitor>();
//		list.add(monitor);
//		list.add(monitor2);
//		dao2.addMoreMon(list);
		wen.retainAll(wen4);
		
		wen2.removeAll(wen4);
		
		wen4.removeAll(wen3);
	
		for(XmlMonitor x:xmlList){
			if(wen.contains(x.getSBBH())){
				Monitor monitor=new Monitor();
				monitor.setMonNumber(x.getSBBH());
				monitor.setMonName(x.getSBMC());
				monitor.setMonIP(x.getSBIP());
				monitor.setMonPlace(x.getSBQY());
				monitor.setMonType(x.getSBLX());
				monitor.setMonInstall(x.getAZWZ());
				monitor.setMonRoute(x.getHLSL());
				if(x.getSBZT()!=null){
					if ("在线".equals(x.getSBZT())) {
					monitor.setMonState(1);	
					}else if("离线".equals(x.getSBZT())){
					monitor.setMonState(2);		
					}else if("故障".equals(x.getSBZT())){
					monitor.setMonState(3);		
					}else if("报警".equals(x.getSBZT())){
					monitor.setMonState(4);		
					}
				}
				dao2.changeMonByMonNumber(monitor);
			}
			if(wen2.contains(x.getSBBH())){
				Monitor monitor=new Monitor();
				monitor.setMonNumber(x.getSBBH());
				monitor.setMonName(x.getSBMC());
				monitor.setMonIP(x.getSBIP());
				monitor.setMonPlace(x.getSBQY());
				monitor.setMonType(x.getSBLX());
				monitor.setMonInstall(x.getAZWZ());
				monitor.setMonRoute(x.getHLSL());
				if(x.getSBZT()!=null){
					if ("在线".equals(x.getSBZT())) {
					monitor.setMonState(1);	
					}else if("离线".equals(x.getSBZT())){
					monitor.setMonState(2);		
					}else if("故障".equals(x.getSBZT())){
					monitor.setMonState(3);		
					}else if("报警".equals(x.getSBZT())){
					monitor.setMonState(4);		
					}			
				}
				if(x.getHLSL()==1||x.getHLSL()==6||x.getHLSL()==12){
					list3.add(monitor);
					}
			}
		}
		if(!list3.isEmpty()){
		dao2.addMoreMon(list3);
		List<Integer> list4  =dao2.selectMonIdByMonNumber(wen2);
		if(!list4.isEmpty()){
		dao.addMonDateIdList(list4);
		}
		}
		if(!wen4.isEmpty()){
		dao2.deleteMonByMonNumber(wen4);
		}
	}
	
	@Test
	public void test1(){
		ApplicationContext    ac = new ClassPathXmlApplicationContext("spring-mybatis.xml");
		MonListDao dao = ac.getBean("monListDao",MonListDao.class);
		
		
		
//		dao.deleteAllMonList();
//		List<MonList> list=new ArrayList<MonList>();
//		MonList mon=new MonList();
//		mon.setListLevel(1);
//		mon.setMonListId("111");
//		mon.setMonListName("111");
//		mon.setSuperiorListId("");
//		MonList mon2=new MonList();
//		mon2.setListLevel(2);
//		mon2.setMonListId("222");
//		mon2.setMonListName("222");
//		mon2.setSuperiorListId("111");
//		MonList mon3=new MonList();
//		mon3.setListLevel(3);
//		mon3.setMonListId("333");
//		mon3.setMonListName("333");
//		mon3.setSuperiorListId("222");
//		list.add(mon);
//		list.add(mon2);
//		list.add(mon3);
//		if(!list.isEmpty()){
//		dao.addAllMonList(list);
//		}
//		List<MonList> list1=dao.selectMonListByListLevel(1);
//		List<MonList> list2=dao.selectMonListByList("111");
//		System.out.println(JSON.toJSONString(list1).toString());
//		System.out.println(JSON.toJSONString(list2).toString());
	}
	
	
	@Test
	public void test3(){
		 String text ="<DeviceInfoGetForJsonResponse xmlns=\"SFBR_Web_API\"><DeviceInfoGetForJsonResult>[{\"SBBH\":\"bd686476-f81c-4a09-88a8-0b9ac334befa\",\"SBMC\":\"农行演示配电箱\",\"SBIP\":\"\",\"SBQY\":\"62d76f3c-59d5-40bd-a3a0-a7a4bf56a69d\",\"SBQYMC\":\"上海市\",\"SBLX\":\"ITPC_BOX\" ,\"SBLXMC\":\"智能配电箱\",\"SBZT\":\"在线\",\"HLSL\":10,\"AZWZ\":\"\"}]</DeviceInfoGetForJsonResult></DeviceInfoGetForJsonResponse>";  
		            
		         long begin = System.currentTimeMillis();   
		          parse( text );   
		         long after = System.currentTimeMillis();   
		          System.out.println("DOM用时"+(after-begin)+"毫秒");   
	}
			
	 public  void parse(String protocolXML) {   
         
	        try {   
	             DocumentBuilderFactory factory = DocumentBuilderFactory   
	                     .newInstance();   
	             DocumentBuilder builder = factory.newDocumentBuilder();   
	             Document doc = builder   
	                     .parse(new InputSource(new StringReader(protocolXML)));   
	  
	             Element root = doc.getDocumentElement();   
	             NodeList books = root.getChildNodes();  
	             Node boo=books.item(0);
	         
	             System.out.println(boo.getFirstChild().getNodeValue());
//	            if (books != null) {   
//	                for (int i = 0; i < books.getLength(); i++) {   
//	                     Node book = books.item(i);   
//	                     System.out.println("节点=" + book.getNodeName() + "\ttext="  
//	                             + book.getFirstChild().getNodeValue());   
//	                 }   
//	             }   
	         } catch (Exception e) {   
	             e.printStackTrace();   
	         }  
	        
	        
	        
	     }   
	  
 

}
