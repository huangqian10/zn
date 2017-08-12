/**
 * 
 */
package zn.entity;

/**
 * @author hq
 *
 */
public class MonList {
	private String monListId;
	private String monListName;
	private String superiorListId;
	private int listLevel;
	public String getMonListId() {
		return monListId;
	}
	public void setMonListId(String monListId) {
		this.monListId = monListId;
	}
	public String getMonListName() {
		return monListName;
	}
	public void setMonListName(String monListName) {
		this.monListName = monListName;
	}
	public String getSuperiorListId() {
		return superiorListId;
	}
	public void setSuperiorListId(String superiorListId) {
		this.superiorListId = superiorListId;
	}
	public int getListLevel() {
		return listLevel;
	}
	public void setListLevel(int listLevel) {
		this.listLevel = listLevel;
	}

	

}
