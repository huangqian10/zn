package zn.until.upush;

public class IOSListCast extends IOSNotification {
	public IOSListCast(String appkey,String appMasterSecret) throws Exception {
			setAppMasterSecret(appMasterSecret);
			setPredefinedKeyValue("appkey", appkey);
			this.setPredefinedKeyValue("type", "listcast");	
	}
	public void setDeviceTokens(String deviceTokens) throws Exception {
		this.setPredefinedKeyValue("device_tokens", deviceTokens);
	}
}
