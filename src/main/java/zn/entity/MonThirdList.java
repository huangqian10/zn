/**
 * 
 */
package zn.entity;

/**
 * @author hq
 *
 */
public class MonThirdList {
	private int thirdListId;
	private int secondListId;
	private String thirdListName;
	
	public int getThirdListId() {
		return thirdListId;
	}
	public void setThirdListId(int thirdListId) {
		this.thirdListId = thirdListId;
	}
	public int getSecondListId() {
		return secondListId;
	}
	public void setSecondListId(int secondListId) {
		this.secondListId = secondListId;
	}
	public String getThirdListName() {
		return thirdListName;
	}
	public void setThirdListName(String thirdListName) {
		this.thirdListName = thirdListName;
	}
	
	@Override
	public String toString() {
		return "MonThirdList [thirdListId=" + thirdListId + ", secondListId=" + secondListId + ", thirdListName="
				+ thirdListName + "]";
	}
	
}
