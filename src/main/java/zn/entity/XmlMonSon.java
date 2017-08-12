/**
 * 
 */
package zn.entity;

/**
 * @author hq
 *
 */
public class XmlMonSon {
	private  String TDMC;
	private  int TDHM;
	private String TDZT;
	public String getTDMC() {
		return TDMC;
	}
	public void setTDMC(String tDMC) {
		TDMC = tDMC;
	}
	
	public int getTDHM() {
		return TDHM;
	}
	public void setTDHM(int tDHM) {
		TDHM = tDHM;
	}
	public String getTDZT() {
		return TDZT;
	}
	public void setTDZT(String tDZT) {
		TDZT = tDZT;
	}
	@Override
	public String toString() {
		return "XmlMonSon [TDMC=" + TDMC + ", TDHM=" + TDHM + ", TDZT=" + TDZT + "]";
	}
	
	
	
}
