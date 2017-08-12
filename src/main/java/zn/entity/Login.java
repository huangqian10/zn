/**
 * 
 */
package zn.entity;

/**
 * @author hq
 *
 */
public class Login {
	private int id;
	private String loadTime;
	private int count;
	private int userId;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getLoadTime() {
		return loadTime;
	}
	public void setLoadTime(String loadTime) {
		this.loadTime = loadTime;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	@Override
	public String toString() {
		return "Login [id=" + id + ", loadTime=" + loadTime + ", count=" + count + ", userId=" + userId + "]";
	}
	
	
	
}
