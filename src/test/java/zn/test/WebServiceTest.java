/**
 * 
 */
package zn.test;

import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;
import java.rmi.RemoteException;
import java.util.Iterator;
import java.util.Properties;

import javax.xml.namespace.QName;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.apache.axiom.om.OMAbstractFactory;
import org.apache.axiom.om.OMElement;
import org.apache.axiom.om.OMFactory;
import org.apache.axiom.om.OMNamespace;
import org.apache.axiom.soap.SOAP11Constants;
import org.apache.axiom.soap.SOAP12Constants;
import org.apache.axis2.AxisFault;
import org.apache.axis2.Constants;
import org.apache.axis2.addressing.EndpointReference;
import org.apache.axis2.client.Options;
import org.apache.axis2.client.ServiceClient;
import org.apache.axis2.rpc.client.RPCServiceClient;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import zn.listener.AnalysisInfoListener;



/**
 * @author hq
 *
 */
public class WebServiceTest {
	
	public static  String loadAddress(){
		InputStream inStream = AnalysisInfoListener.class.getClassLoader().getResourceAsStream("./udp.properties");
		Properties prop = new Properties();
		try {
			prop.load(inStream);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String  address= prop.getProperty("WebService");
		System.out.println(address);
		return address;
	}
	
    private static EndpointReference targetEPR = new EndpointReference(
    		loadAddress());

	public String te(){
		String json="";
		try {
	OMFactory fac = OMAbstractFactory.getOMFactory();

    OMNamespace omNs = fac.createOMNamespace(
            "SFBR_Web_API", "tns");// 命名空间

    // 请求参数设置
    Options options = new Options();
    options.setTo(targetEPR);// 设定webservice地址
    options.setTransportInProtocol(Constants.TRANSPORT_HTTP);// 设定传输协议
    options.setSoapVersionURI(SOAP12Constants.SOAP_ENVELOPE_NAMESPACE_URI);// 设定SOAP版本soap1.2

    // 客户端绑定参数设置
    ServiceClient sender = new ServiceClient();
    sender.setOptions(options);

    // 设定访问的接口方法

    OMElement method = fac.createOMElement("DeviceInfoGetForJsonResponse", omNs);// 要调用的接口方法名称
    
//    OMElement value1 = fac.createOMElement("strDeviceId", omNs);// 方法的第一个参数名称
//    value1.addChild(fac.createOMText(value1, "f0f690d6-7147-49fa-9bdb-8e037f1d5444"));// 设定参数的值
//    method.addChild(value1);// 方法设置参数
    
//    OMElement value2 = fac.createOMElement("CountryName", omNs);// 方法的第一个参数名称
//    value2.addChild(fac.createOMText(value2, "China"));// 设定参数的值
//    method.addChild(value2);// 方法设置参数

    OMElement result = sender.sendReceive(method);// 调用接口方法
//    Iterator iterator = result.getChildrenWithLocalName("AuthorizationResult");
//    System.out.println("guid="+((OMElement)iterator.next()).getText());
    json= result.toString();
	}catch (Exception e) {
    e.printStackTrace();
	}
		return json;
	}
	
 
	 public  String parse(String protocolXML) {   
      	String json="[]";
	        try {   
	             DocumentBuilderFactory factory = DocumentBuilderFactory   
	                     .newInstance();   
	             DocumentBuilder builder = factory.newDocumentBuilder();   
	             Document doc = builder   
	                     .parse(new InputSource(new StringReader(protocolXML)));   
	  
	             Element root = doc.getDocumentElement();   
	             NodeList books = root.getChildNodes();  
	             Node boo=books.item(0);
	            
	             
	             json=boo.getFirstChild().getNodeValue();
	             System.out.println(json);
	             return json;
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
			return json;  
	        
	        
	        
	     }   

	public static void main(String[] args) {
		WebServiceTest t = new WebServiceTest();
		System.out.println(t.te());
//		t.parse(t.te());
		
		
	}
}
			
  


	
