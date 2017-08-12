/**
 * 
 */
package zn.entity;

/**
 * @author hq
 *
 */
public class Alarms {
	private int alarmsId;
	private int monId;
	private String monName;
	private int monAlarmsStatus;
	private String monPlaceName;

	public String getMonPlaceName() {
		return monPlaceName;
	}
	public void setMonPlaceName(String monPlaceName) {
		this.monPlaceName = monPlaceName;
	}
	public String getMonName() {
		return monName;
	}
	public void setMonName(String monName) {
		this.monName = monName;
	}
	private String monPlace;
	
	
	public String getMonPlace() {
		return monPlace;
	}
	public void setMonPlace(String monPlace) {
		this.monPlace = monPlace;
	}
	public int getAlarmsId() {
		return alarmsId;
	}
	public void setAlarmsId(int alarmsId) {
		this.alarmsId = alarmsId;
	}
	private String monAlarmsType;
	private String monAlarmsTM;
	private String monAlarmsInfo;
	public int getMonId() {
		return monId;
	}
	public void setMonId(int monId) {
		this.monId = monId;
	}

	public String getMonAlarmsType() {
		return monAlarmsType;
	}
	public void setMonAlarmsType(String monAlarmsType) {
		this.monAlarmsType = monAlarmsType;
	}
	public String getMonAlarmsTM() {
		return monAlarmsTM;
	}
	public void setMonAlarmsTM(String monAlarmsTM) {
		this.monAlarmsTM = monAlarmsTM;
	}
	public String getMonAlarmsInfo() {
		return monAlarmsInfo;
	}
	public void setMonAlarmsInfo(String monAlarmsInfo) {
		this.monAlarmsInfo = monAlarmsInfo;
	}
	public int getMonAlarmsStatus() {
		return monAlarmsStatus;
	}
	public void setMonAlarmsStatus(int monAlarmsStatus) {
		this.monAlarmsStatus = monAlarmsStatus;
	}
	@Override
	public String toString() {
		return "Alarms [alarmsId=" + alarmsId + ", monId=" + monId + ", monName=" + monName + ", monAlarmsStatus="
				+ monAlarmsStatus + ", monPlace=" + monPlace + ", monAlarmsType=" + monAlarmsType + ", monAlarmsTM="
				+ monAlarmsTM + ", monAlarmsInfo=" + monAlarmsInfo + "]";
	}


	

}
