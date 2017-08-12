/**
 * 
 */
package zn.entity;

/**
 * @author hq
 *
 */
public class Monitor {
	private int monId;                              //设备id
	private String monAlias;						//设备别名
	private String monInstall;						//设备安装位置
	private String monIP;							//设备IP
	private String monModel;						//设备型号
	private String monName;							//设备名称
	private String monPlace;						//设备归属地
	private String monPlaceName;					//设备归属地级别
	private String monType;							//设备类型
	private String monNumber;                       //设备编号
	private int monRoute;                           //设备路数
    private int monState;                           //设备状态
    private String allAV;
	private String allBV;
	private String allCV;
	private String monInT;
	public String getMonInT() {
		return monInT;
	}
	public void setMonInT(String monInT) {
		this.monInT = monInT;
	}
	
	public String getAllAV() {
		return allAV;
	}
	public void setAllAV(String allAV) {
		this.allAV = allAV;
	}
	public String getAllBV() {
		return allBV;
	}
	public void setAllBV(String allBV) {
		this.allBV = allBV;
	}
	public String getAllCV() {
		return allCV;
	}
	public void setAllCV(String allCV) {
		this.allCV = allCV;
	}
	public int getMonState() {
		return monState;
	}
	public void setMonState(int monState) {
		this.monState = monState;
	}
	public int getMonRoute() {
		return monRoute;
	}
	public void setMonRoute(int monRoute) {
		this.monRoute = monRoute;
	}
	public String getMonNumber() {
		return monNumber;
	}
	public void setMonNumber(String monNumber) {
		this.monNumber = monNumber;
	}
	public int getMonId() {
		return monId;
	}
	public void setMonId(int monId) {
		this.monId = monId;
	}
	public String getMonAlias() {
		return monAlias;
	}
	public void setMonAlias(String monAlias) {
		this.monAlias = monAlias;
	}
	public String getMonInstall() {
		return monInstall;
	}
	public void setMonInstall(String monInstall) {
		this.monInstall = monInstall;
	}
	public String getMonIP() {
		return monIP;
	}
	public void setMonIP(String monIP) {
		this.monIP = monIP;
	}
	public String getMonModel() {
		return monModel;
	}
	public void setMonModel(String monModel) {
		this.monModel = monModel;
	}
	public String getMonName() {
		return monName;
	}
	public void setMonName(String monName) {
		this.monName = monName;
	}
	public String getMonPlace() {
		return monPlace;
	}
	public void setMonPlace(String monPlace) {
		this.monPlace = monPlace;
	}
	public String getMonType() {
		return monType;
	}
	public void setMonType(String monType) {
		this.monType = monType;
	}

	public String getMonPlaceName() {
		return monPlaceName;
	}
	public void setMonPlaceName(String monPlaceName) {
		this.monPlaceName = monPlaceName;
	}
	@Override
	public String toString() {
		return "Monitor [monId=" + monId + ", monAlias=" + monAlias + ", monInstall=" + monInstall + ", monIP=" + monIP
				+ ", monModel=" + monModel + ", monName=" + monName + ", monPlace=" + monPlace + ", monPlaceName="
				+ monPlaceName + ", monType=" + monType + ", monNumber=" + monNumber + ", monRoute=" + monRoute
				+ ", monState=" + monState + ", allAV=" + allAV + ", allBV=" + allBV + ", allCV=" + allCV + ", monInT="
				+ monInT + "]";
	}
	

	

	
	

}
