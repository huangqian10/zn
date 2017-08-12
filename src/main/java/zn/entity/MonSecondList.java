/**
 * 
 */
package zn.entity;

/**
 * @author hq
 *
 */
public class MonSecondList {
	private int secondListId;
	private int firstListId;
	private String secondListName;
	public int getSecondListId() {
		return secondListId;
	}
	public void setSecondListId(int secondListId) {
		this.secondListId = secondListId;
	}
	public int getFirstListId() {
		return firstListId;
	}
	public void setFirstListId(int firstListId) {
		this.firstListId = firstListId;
	}
	public String getSecondListName() {
		return secondListName;
	}
	public void setSecondListName(String secondListName) {
		this.secondListName = secondListName;
	}
	@Override
	public String toString() {
		return "MonSecondList [secondListId=" + secondListId + ", firstListId=" + firstListId + ", secondListName="
				+ secondListName + "]";
	}

	
	
}
