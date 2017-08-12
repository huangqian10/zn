/**
 * 
 */
package zn.entity;

/**
 * @author hq
 *
 */
public class MonAlarms {
	private int alarmsId;
	private int monId;
	
	public int getAlarmsId() {
		return alarmsId;
	}
	public void setAlarmsId(int alarmsId) {
		this.alarmsId = alarmsId;
	}
	private String monAlarmsType;
	private String monAlarmsTM;
	private String monAlarmsInfo;
	private int monAlarmsStatus;

	@Override
	public String toString() {
		return "MonAlarms [alarmsId=" + alarmsId + ", monId=" + monId + ", monAlarmsType=" + monAlarmsType
				+ ", monAlarmsTM=" + monAlarmsTM + ", monAlarmsInfo=" + monAlarmsInfo + ", monAlarmsStatus="
				+ monAlarmsStatus + "]";
	}
	public int getMonAlarmsStatus() {
		return monAlarmsStatus;
	}
	public void setMonAlarmsStatus(int monAlarmsStatus) {
		this.monAlarmsStatus = monAlarmsStatus;
	}
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

	

	
}
