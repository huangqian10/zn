package zn.until.upush;


public class AndroidListCast extends AndroidNotification {
	public AndroidListCast(String appkey,String appMasterSecret) throws Exception {
			setAppMasterSecret(appMasterSecret);
			setPredefinedKeyValue("appkey", appkey);
			this.setPredefinedKeyValue("type", "listcast");	
			setPredefinedKeyValue("activity","com.sfbr.dormitory.Controller.Alarm.AlarmManageActivity");
	}
	
	public void setDeviceTokes(String deviceTokens) throws Exception {
		setPredefinedKeyValue("device_tokens", deviceTokens);	
	}
}