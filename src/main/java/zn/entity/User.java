package zn.entity;

public class User {
	private int userId;
	private String userNumber;
	private String userName;
	private String telephone;
	private String password;
	private String lastLoadTime;
	private int loadState;
	private int orgId;
	private String orgName;
	private int   limitsId;
	public String getUserNumber() {
		return userNumber;
	}

	public void setUserNumber(String userNumber) {
		this.userNumber = userNumber;
	}

	private String limitsName;
	private String userPicUrl;
	
	public String getUserPicUrl() {
		return userPicUrl;
	}

	public void setUserPicUrl(String userPicUrl) {
		this.userPicUrl = userPicUrl;
	}

	public int getOrgId() {
		return orgId;
	}

	public void setOrgId(int orgId) {
		this.orgId = orgId;
	}

	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

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

	private String information;

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
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

	public String getInformation() {
		return information;
	}

	public void setInformation(String information) {
		this.information = information;
	}


	@Override
	public String toString() {
		return "User [userId=" + userId + ", userNumber=" + userNumber + ", userName=" + userName + ", telephone="
				+ telephone + ", password=" + password + ", lastLoadTime=" + lastLoadTime + ", loadState=" + loadState
				+ ", orgId=" + orgId + ", orgName=" + orgName + ", limitsId=" + limitsId + ", deviceToken="
			    + ", limitsName=" + limitsName + ", userPicUrl=" + userPicUrl + ", information="
				+ information + "]";
	}

	

	


	
}
