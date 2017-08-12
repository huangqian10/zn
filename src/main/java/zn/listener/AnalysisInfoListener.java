/**
 * 
 */
package zn.listener;

import java.io.InputStream;
import java.net.InetAddress;
import java.util.Properties;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.springframework.web.context.support.WebApplicationContextUtils;

import zn.service.AnalysisService;
import zn.until.UdpServerSocket;

/**
 * @author hq
 *
 */
public class AnalysisInfoListener implements ServletContextListener{
	
	/* (non-Javadoc)
	 * @see javax.servlet.ServletContextListener#contextDestroyed(javax.servlet.ServletContextEvent)
	 */
	public void contextDestroyed(ServletContextEvent arg0) {
		
		
	}

	/* (non-Javadoc)
	 * @see javax.servlet.ServletContextListener#contextInitialized(javax.servlet.ServletContextEvent)
	 */
	public void contextInitialized(ServletContextEvent arg0) {
		AnalysisService analysisService=WebApplicationContextUtils.getWebApplicationContext(arg0.getServletContext()).getBean(AnalysisService.class);
	

	
		
		 try {
			 
		
//			InputStream inStream =AnalysisInfoListener.class.getClassLoader() .getResourceAsStream( "./udp.properties" );  
//		
//			Properties prop = new Properties();    
//			prop.load(inStream);    
//				int localityPort = Integer.valueOf(prop.getProperty("localityPort"));
//			udpServerSocket = new UdpServerSocket(InetAddress.getLocalHost().getHostAddress(), localityPort);
//			if(udpServerSocket!=null){
			analysisService.analysisMon( );
//			}
		 } catch (Exception e) {
		
			e.printStackTrace();
		}
		
	}

}
