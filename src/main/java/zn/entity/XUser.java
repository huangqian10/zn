/**
 * 
 */
package zn.entity;

/**
 * @author hq
 *
 */
public class XUser {
	private int userId;   //用户id
	private String userNumber;   //用户编号
	private String userName;    //用户名称
	private String telephone;   //登陆账号
	private String password;   //登陆密码
	private String lastLoadTime;   //上次登录的时间
	private int loadState;        //登录状态
	private String orgId;        //组织编号
	private String orgName;      //组织名称
	private String    limitsId;  //权限编号
	private String limitsName;   //权限名称
	private String userPicUrl;
	public String getUserPicUrl() {
		return userPicUrl;
	}
	public void setUserPicUrl(String userPicUrl) {
		this.userPicUrl = userPicUrl;
	}
	public String getLimitsName() {
		return limitsName;
	}
	public void setLimitsName(String limitsName) {
		this.limitsName = limitsName;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getUserNumber() {
		return userNumber;
	}
	public void setUserNumber(String userNumber) {
		this.userNumber = userNumber;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getTelephone() {
		return telephone;
	}
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getLastLoadTime() {
		return lastLoadTime;
	}
	public void setLastLoadTime(String lastLoadTime) {
		this.lastLoadTime = lastLoadTime;
	}
	public int getLoadState() {
		return loadState;
	}
	public void setLoadState(int loadState) {
		this.loadState = loadState;
	}
	public String getOrgId() {
		return orgId;
	}
	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}
	public String getOrgName() {
		return orgName;
	}
	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}
	public String getLimitsId() {
		return limitsId;
	}
	public void setLimitsId(String limitsId) {
		this.limitsId = limitsId;
	}
	
	
	

}
