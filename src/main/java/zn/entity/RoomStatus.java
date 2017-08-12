package zn.entity;

import java.util.Date;

/**
 * @author hl
 * 房间状态
 */
public class RoomStatus {
   private int id;
   
   private int monId;
   private String monNum;
   private String TDMC;
   private String TDHM;
   
   private String DY;
   private String WD;
   private String DL;
   private String LD;
   
   private String TStatus;
   private String smogStatus;
   private String AStatus;
   private String airSwitchStatus;
   
   private Date createTime;
   
   public RoomStatus(){
	   this.createTime=new Date();
   }

public int getId() {
	return id;
}

public void setId(int id) {
	this.id = id;
}

public int getMonId() {
	return monId;
}

public void setMonId(int monId) {
	this.monId = monId;
}

public String getMonNum() {
	return monNum;
}

public void setMonNum(String monNum) {
	this.monNum = monNum;
}

public String getTDMC() {
	return TDMC;
}

public void setTDMC(String tDMC) {
	TDMC = tDMC;
}

public String getTDHM() {
	return TDHM;
}

public void setTDHM(String tDHM) {
	TDHM = tDHM;
}

public String getDY() {
	return DY;
}

public void setDY(String dY) {
	DY = dY;
}

public String getWD() {
	return WD;
}

public void setWD(String wD) {
	WD = wD;
}

public String getDL() {
	return DL;
}

public void setDL(String dL) {
	DL = dL;
}

public String getLD() {
	return LD;
}

public void setLD(String lD) {
	LD = lD;
}

public String getTStatus() {
	return TStatus;
}

public void setTStatus(String tStatus) {
	TStatus = tStatus;
}

public String getSmogStatus() {
	return smogStatus;
}

public void setSmogStatus(String smogStatus) {
	this.smogStatus = smogStatus;
}

public String getAStatus() {
	return AStatus;
}

public void setAStatus(String aStatus) {
	AStatus = aStatus;
}

public String getAirSwitchStatus() {
	return airSwitchStatus;
}

public void setAirSwitchStatus(String airSwitchStatus) {
	this.airSwitchStatus = airSwitchStatus;
}

public Date getCreateTime() {
	return createTime;
}

public void setCreateTime(Date createTime) {
	this.createTime = createTime;
}

@Override
public String toString() {
	return "RoomStatus [id=" + id + ", monId=" + monId + ", monNum=" + monNum + ", TDMC=" + TDMC + ", TDHM=" + TDHM
			+ ", DY=" + DY + ", WD=" + WD + ", DL=" + DL + ", LD=" + LD + ", TStatus=" + TStatus + ", smogStatus="
			+ smogStatus + ", AStatus=" + AStatus + ", airSwitchStatus=" + airSwitchStatus + "]";
}



   

   
}
