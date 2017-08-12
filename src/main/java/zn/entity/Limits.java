/**
 * 
 */
package zn.entity;

/**
 * @author hq
 *
 */
public class Limits {
	private int limitsId;
	private String limitsName;
	public int getLimitsId() {
		return limitsId;
	}
	public void setLimitsId(int limitsId) {
		this.limitsId = limitsId;
	}
	public String getLimitsName() {
		return limitsName;
	}
	public void setLimitsName(String limitsName) {
		this.limitsName = limitsName;
	}
	@Override
	public String toString() {
		return "Limits [limitsId=" + limitsId + ", limitsName=" + limitsName + "]";
	}
}
