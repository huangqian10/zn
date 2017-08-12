/**
 * 
 */
package zn.until;

import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.binary.Hex;

/**
 * @author hq
 *
 */
public class EncodeUtils {
	 private static final String DEFAULT_URL_ENCODING = "UTF-8";
	 
	   public static String convertStringToHex(String str){  
		   
		      char[] chars = str.toCharArray();  
		  
		      StringBuffer hex = new StringBuffer();  
		      for(int i = 0; i < chars.length; i++){  
		        hex.append(Integer.toHexString((int)chars[i]));  
		      }  
		  
		      return hex.toString();  
		      }  
		  
		      public   static String convertHexToString(String hex){  
		    	  
		      StringBuilder sb = new StringBuilder();  
		     
		  
		     
		      for( int i=0; i<hex.length()-1; i+=2 ){  
		  
		          //grab the hex in pairs  
		          String output = hex.substring(i, (i + 2));  
		          //convert hex to decimal  
		          int decimal = Integer.parseInt(output, 16);  
		          //convert the decimal to character  
		          sb.append((char)decimal);  
		  
		     
		      }  
		  
		      return sb.toString();  
		      }  
	 
	 
	 
	 
	 
	 /**
	  * Hex编码.
	  */
	 public static String hexEncode(byte[] input) {
	  return Hex.encodeHexString(input);
	 }
	 /**
	  * Hex解码.
	  */
	 public static byte[] hexDecode(String input) {
	  try {
	   return Hex.decodeHex(input.toCharArray());
	  } catch (DecoderException e) {
	   throw new IllegalStateException("Hex Decoder exception", e);
	  }
	 }
	 
	 
		/**
		 * 将byte转换为八位二进制字符串
		 * @Title: byte2bits 
		 * @Description: TODO
		 * @param b
		 * @return   
		 * @throws
		 */
		public  static   String byte2bits(byte b) {
			  int z = b; z |= 256;		
			  String str = Integer.toBinaryString(z);
			  int len = str.length(); 			
			  return str.substring(len-8, len);
			}

	 /** 
	  * 浮点转换为字节 
	  *  
	  * @param f 
	  * @return 
	  */  
	 public static byte[] float2byte(float f) {  
	       
	     // 把float转换为byte[]  
	     int fbit = Float.floatToIntBits(f);  
	       
	     byte[] b = new byte[4];    
	     for (int i = 0; i < 4; i++) {    
	         b[i] = (byte) (fbit >> (24 - i * 8));    
	     }   
	       
	     // 翻转数组  
	     int len = b.length;  
	     // 建立一个与源数组元素类型相同的数组  
	     byte[] dest = new byte[len];  
	     // 为了防止修改源数组，将源数组拷贝一份副本  
	     System.arraycopy(b, 0, dest, 0, len);  
	     byte temp;  
	     // 将顺位第i个与倒数第i个交换  
	     for (int i = 0; i < len / 2; ++i) {  
	         temp = dest[i];  
	         dest[i] = dest[len - i - 1];  
	         dest[len - i - 1] = temp;  
	     }  	       
	     return dest;         
	 }  
	   
	 /** 
	  * 字节转换为浮点 
	  *  
	  * @param b 字节（至少4个字节） 
	  * @param index 开始位置 
	  * @return 
	  */  
	 public static float byte2float(byte[] b, int index) {    
	     int l;                                             
	     l = b[index + 0];                                  
	     l &= 0xff;                                         
	     l |= ((long) b[index + 1] << 8);                   
	     l &= 0xffff;                                       
	     l |= ((long) b[index + 2] << 16);                  
	     l &= 0xffffff;                                     
	     l |= ((long) b[index + 3] << 24);                  
	     return Float.intBitsToFloat(l);                    
	 }  
	 
	
	 /**
	  * 将C/C++的无符号 DWORD类型转换为java的long型
	  * @Title: getLong 
	  * @Description: TODO
	  * @param buf
	  * @param index
	  * @return   
	  * @throws
	  */
	 public  static long getLong(byte buf[], int index) {

		 int firstByte = (0x000000FF & ((int) buf[index]));
		 
		 int secondByte = (0x000000FF & ((int) buf[index + 1]));
		
		 int thirdByte = (0x000000FF & ((int) buf[index + 2]));
		 System.out.println();
		 int fourthByte = (0x000000FF & ((int) buf[index + 3]));

		 long unsignedLong = ((long) (firstByte | secondByte << 8 | thirdByte << 16 | fourthByte << 24)) & 0xFFFFFFFFL;

		 return unsignedLong;
		 }
		 

}
