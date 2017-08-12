package zn.test;

import java.io.IOException;
import java.io.InputStream;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.swing.plaf.synth.SynthSpinnerUI;

import org.apache.commons.codec.digest.Md5Crypt;
import org.apache.log4j.Logger;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONException;

import zn.dao.LoginDao;
import zn.dao.MonAlarmsDao;
import zn.dao.MonDateDao;
import zn.dao.MonitorDao;
import zn.dao.OrganizationDao;
import zn.dao.UserDao;
import zn.entity.MonAlarms;
import zn.entity.Monitor;
import zn.entity.Organization;
import zn.entity.User;
import zn.service.UserServiceImpl;
import zn.until.NoteResult;
import zn.until.NoteUtil;


public class newTest {
	
   

   @Test
   public void test3(){

//	     ApplicationContext    ac = new ClassPathXmlApplicationContext("spring-mybatis.xml");  
//	     LoginDao dao = ac.getBean("loginDao",LoginDao.class);
////	     int a=dao.addCount(4);
////	     int b=dao.autoAddCount(3);
//	     Integer c=dao.countIsExist(5);
//	   
////	     int d=dao.deleteOutModedCount("2016-11-17 14:30:00","2016-11-17 15:10:00");
////	     int d=dao.deleteCountByuserId(4);
//	     if(c==null){
//	     System.out.println("a");
//	     }else{
//	    	 System.out.println(c);
//	     }
   }
//	@Test
//	public void test(){
//	
//			NoteResult note=new NoteResult();
//			note.setStatus(1);
//			note.setMsg("检验账号成功");
//			note.setData("");
//			String json=JSON.toJSONString(note);
//			System.out.println(json);
//		
//	}
	
	@Test
	public void test1(){
//	     ApplicationContext    ac = new ClassPathXmlApplicationContext("spring-mybatis.xml");  
//	     UserDao dao = ac.getBean("userDao",UserDao.class);
//	           User user=new User();
//	           user.setInformation("aa");
//	           user.setTelephone("13123456889");
//	           user.setUserName("啊熊");
//	           user.setPassword("as123");
//	           int a=dao.addUser(user);
//	           System.out.println(a);
//	   List<User> user2=   dao.selectUser("13123456889");
//	    System.out.println(user2.toString());
//		System.out.println(!"1312345679".matches("^\\d{11}$"));
	}
	
	@Test
	public void test(){
//		 ApplicationContext    ac = new ClassPathXmlApplicationContext("spring-mybatis.xml");  
//	     UserDao userDao = ac.getBean("userDao",UserDao.class);
//	     MonAlarmsDao monAlarmsDao = ac.getBean("monAlarmsDao",MonAlarmsDao.class);
//	     MonAlarms monAlarms=new MonAlarms();
//	     monAlarms.setMonAlarmsInfo("aaa");
//	     monAlarms.setMonAlarmsType("12");
//	     monAlarms.setMonId(3);
//		monAlarmsDao.addMonAlarms(monAlarms);
//		List<Integer> list=userDao.selectUserIdList(monAlarms.getMonId());
//		List<Map<String , Object>> mapList=new ArrayList<Map<String , Object>>();
//		for(Integer userId:list){
//			Map<String, Object> map=new HashMap<String,Object>();
//			map.put("userId", userId);
//			map.put("alarmsId", monAlarms.getAlarmsId());
//			
//			mapList.add(map);
//		}
//		System.out.println(mapList.toString());
//		monAlarmsDao.userAddAlarm(mapList);
//		
		String a="aaa";
		if(a.contains("a")){
			System.out.println("aa");
		}
		
	
	}
   

//		@Test
//		public void test6(){
//			 ApplicationContext    ac = new ClassPathXmlApplicationContext("spring-mybatis.xml");  
//			 OrganizationDao dao = ac.getBean("organizationDao",OrganizationDao.class);
//			 List<Organization> list=dao.selectOrg();
//			 System.out.println(list.toString());
//			  int a=  dao.addOrg("yyy");
//			  System.out.println(a);
////			 int b=dao.deleteOrg("yyy");
////			 System.out.println(b);
//			 int c=dao.OrgIsExist("yyy");
//			 System.out.println(c);
//			 int d=dao.changeOrgName("yyy","eee");
//		}
   
		@Test
		public void test4(){
			 ApplicationContext    ac = new ClassPathXmlApplicationContext("spring-mybatis.xml");  
			 MonitorDao dao = ac.getBean("monitorDao",MonitorDao.class);
			 Monitor monitor=new Monitor();      
//			 monitor.setMonAlias("whe");
//			 monitor.setMonInstall("北");
//			 monitor.setMonIP("192.195.145");
//			 monitor.setMonModel("8");
//			 monitor.setMonName("啊");
//			 monitor.setMonNumber("12356");
//			 monitor.setMonPlace("福");
//			 monitor.setMonType("4321");
			 monitor.setMonRoute(1);
			
//			 int a=    dao.addMon(monitor);
//			 int f=dao.changeMon(monitor);
//			 System.out.println(a);

//			 System.out.println(list.toString());
//			 System.out.println(dao.monIsExist("123"));
//			 System.out.println(dao.deleteMon(2));
		}
	
	@Test
	public void test7() throws IOException{
//		System.out.println(NoteUtil.md5("123456"));
//		String m="^[01]{12}$";
//		System.out.println("111111111111".matches(m));
//		System.out.println(InetAddress.getLocalHost().getHostAddress());
//		StringBuilder ss=new StringBuilder("1111111111111");
//		  System.out.println(ss.replace(12-3+1, 12-3+2, "0"));
		
//		InputStream inStream =ClassLoader.getSystemResourceAsStream("udp.properties"); 
//		Properties prop = new Properties();    
//		prop.load(inStream);    
//		int localityPort = Integer.valueOf(prop.getProperty("localityPort"));
//		System.out.println(localityPort);
	}
 

}
