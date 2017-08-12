/**
 * 
 */
package zn.entity;

/**
 * @author hq
 *
 */
public class XmlMonitor {
		   private String SBBH;
		   private String SBMC;
		   private String SBIP;
		   private String SBQY;
		   private String SBQYMC;
		   private String SBLX;
		   private String SBLXMC;
		   private String SBZT;
		   private int HLSL;
		   private String AZWZ;
	
		public int getHLSL() {
			return HLSL;
		}
		public void setHLSL(int hLSL) {
			HLSL = hLSL;
		}
		public String getAZWZ() {
			return AZWZ;
		}
		public void setAZWZ(String aZWZ) {
			AZWZ = aZWZ;
		}
		public String getSBBH() {
			return SBBH;
		}
		public void setSBBH(String sBBH) {
			SBBH = sBBH;
		}
		public String getSBMC() {
			return SBMC;
		}
		public void setSBMC(String sBMC) {
			SBMC = sBMC;
		}
		public String getSBIP() {
			return SBIP;
		}
		public void setSBIP(String sBIP) {
			SBIP = sBIP;
		}
		public String getSBQY() {
			return SBQY;
		}
		public void setSBQY(String sBQY) {
			SBQY = sBQY;
		}
		public String getSBQYMC() {
			return SBQYMC;
		}
		public void setSBQYMC(String sBQYMC) {
			SBQYMC = sBQYMC;
		}
		public String getSBLX() {
			return SBLX;
		}
		public void setSBLX(String sBLX) {
			SBLX = sBLX;
		}
		public String getSBLXMC() {
			return SBLXMC;
		}
		public void setSBLXMC(String sBLXMC) {
			SBLXMC = sBLXMC;
		}
		public String getSBZT() {
			return SBZT;
		}
		public void setSBZT(String sBZT) {
			SBZT = sBZT;
		}
		
		@Override
		public String toString() {
			return "XmlMonitor [SBBH=" + SBBH + ", SBMC=" + SBMC + ", SBIP=" + SBIP + ", SBQY=" + SBQY + ", SBQYMC="
					+ SBQYMC + ", SBLX=" + SBLX + ", SBLXMC=" + SBLXMC + ", SBZT=" + SBZT + ", HLSL=" + HLSL + ", AZWZ="
					+ AZWZ + "]";
		}   
	
   
}
