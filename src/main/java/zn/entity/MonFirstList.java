/**
 * 
 */
package zn.entity;

/**
 * @author hq
 *
 */
public class MonFirstList {
	private int firstListId;
	private String firstListName;
	public int getFirstListId() {
		return firstListId;
	}
	public void setFirstListId(int firstListId) {
		this.firstListId = firstListId;
	}
	public String getFirstListName() {
		return firstListName;
	}
	public void setFirstListName(String firstListName) {
		this.firstListName = firstListName;
	}
	@Override
	public String toString() {
		return "MonFirstList [firstListId=" + firstListId + ", firstListName=" + firstListName + "]";
	}
	
	
	

}
