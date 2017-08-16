package zn.until.upush;

import java.util.Arrays;
import java.util.List;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

public class UPushClient {
	private String appkey;
	private String appMasterSecret;
	private PushClient client = new PushClient();

	public UPushClient(String appkey, String appMasterSecret) {
		this.appkey = appkey;
		this.appMasterSecret = appMasterSecret;
	}

	// 广播,面向所有Android用户
	public void sendAndroidBroadcast(String ticker, String title, String text) throws Exception {
		AndroidBroadcast broadcast = new AndroidBroadcast(appkey, appMasterSecret);
		/*
		 * broadcast.setTicker( "测试提示文字"); broadcast.setTitle( "测试标题");
		 * broadcast.setText( "测试文字描述");
		 */

		broadcast.setTicker(ticker);
		broadcast.setTitle(title);
		broadcast.setText(text);

		broadcast.goAppAfterOpen();
		broadcast.setDisplayType(AndroidNotification.DisplayType.NOTIFICATION);
		// TODO Set 'production_mode' to 'false' if it's a test device.
		// For how to register a test device, please see the developer doc.
		broadcast.setProductionMode();
		// Set customized fields
		broadcast.setExtraField("test", "helloworld");
		client.send(broadcast);
	}
    
	//列播,面向一批Android用户
	public void sendAndroidListcast(List<String> deviceTokens, String alertInfo) throws Exception {
		
		AndroidListCast listcast = new AndroidListCast(appkey, appMasterSecret);
		//拼接device_token字符串
		String deviceTokenStr=null;
		StringBuilder stringBuilder=new StringBuilder();
		for(String deviceToken:deviceTokens) {
			stringBuilder.append(deviceToken);
			stringBuilder.append(",");
		}
		deviceTokenStr=stringBuilder.toString();
		deviceTokenStr=deviceTokenStr.substring(0,deviceTokenStr.length()-1);
		//System.out.println("device_token:"+deviceTokenStr+","+deviceTokenStr.length());//
		//设置参数
		listcast.setDeviceTokes(deviceTokenStr);
		listcast.setTicker("SFBR");
		listcast.setTitle("新的报警信息");
		listcast.setText(alertInfo);
     
		listcast.goCustomAfterOpen("{'handle':'warn'}");
		listcast.setDisplayType(AndroidNotification.DisplayType.NOTIFICATION);
		// TODO Set 'production_mode' to 'false' if it's a test device.
		// For how to register a test device, please see the developer doc.
		listcast.setProductionMode();
		// Set customized fields
		//broadcast.setExtraField("test", "helloworld");
		client.send(listcast);
	}
	
	// 列播，面向android
	public void sendAndroidCustomizedcast() throws Exception {
		AndroidCustomizedcast customizedcast = new AndroidCustomizedcast(appkey, appMasterSecret);
		// TODO Set your alias here, and use comma to split them if there are multiple
		// alias.
		// And if you have many alias, you can also upload a file containing these
		// alias, then
		// use file_id to send customized notification.
		customizedcast.setAlias("alias", "alias_type");
		customizedcast.setTicker("Android customizedcast ticker");
		customizedcast.setTitle("涓枃鐨則itle");
		customizedcast.setText("Android customizedcast text");
		customizedcast.goAppAfterOpen();
		customizedcast.setDisplayType(AndroidNotification.DisplayType.NOTIFICATION);
		// TODO Set 'production_mode' to 'false' if it's a test device.
		// For how to register a test device, please see the developer doc.
		customizedcast.setProductionMode();
		client.send(customizedcast);
	}

	// 组播，android
	public void sendAndroidGroupcast(List<String> tags, String message) throws Exception {
		AndroidGroupcast groupcast = new AndroidGroupcast(appkey, appMasterSecret);
		/*
		 * TODO Construct the filter condition: "where": { "and": [ {"tag":"test"},
		 * {"tag":"Test"} ] }
		 */
		JSONObject filterJson = new JSONObject();
		JSONObject whereJson = new JSONObject();
		JSONArray tagArray = new JSONArray();
		JSONObject testTag = new JSONObject();
		JSONObject TestTag = new JSONObject();
		for (String tag : tags) {
			testTag.put("tag", tag);
			tagArray.add(testTag);
		}
		/*
		 * testTag.put("tag", "test"); TestTag.put("tag", "Test");
		 * tagArray.add(testTag); tagArray.add(TestTag);
		 */
		whereJson.put("and", tagArray);
		filterJson.put("where", whereJson);
		System.out.println(filterJson.toString());

		groupcast.setFilter(filterJson);
		groupcast.setTicker("APP提示");// 提示
		groupcast.setTitle("SFBR");// 标题
		groupcast.setText(message);// 内容
		groupcast.goAppAfterOpen();
		groupcast.setDisplayType(AndroidNotification.DisplayType.NOTIFICATION);
		// TODO Set 'production_mode' to 'false' if it's a test device.
		// For how to register a test device, please see the developer doc.
		groupcast.setProductionMode();
		client.send(groupcast);
	}

	// 广播,面向所有IOS用户
	public void sendIOSBroadcast(String alertInfo) throws Exception {
		IOSBroadcast broadcast = new IOSBroadcast(appkey, appMasterSecret);

		broadcast.setAlert("IOS 广播");
		broadcast.setBadge(0);
		broadcast.setSound("default");
		// TODO set 'production_mode' to 'true' if your app is under production mode
		broadcast.setTestMode();
		// Set customized fields
		broadcast.setCustomizedField("test", "helloworld");
		client.send(broadcast);
	}
    
	// 列播,面向一批IOS用户
	public void sendIOSListcast(List<String> deviceTokens,String alertInfo) throws Exception {
			IOSListCast listcast = new IOSListCast(appkey, appMasterSecret);
			String deviceTokenStr=null;
			StringBuilder stringBuilder=new StringBuilder();
			for(String deviceToken:deviceTokens) {
				stringBuilder.append(deviceToken);
				stringBuilder.append(",");
			}
			deviceTokenStr=stringBuilder.toString();
			deviceTokenStr=deviceTokenStr.substring(0,deviceTokenStr.length()-1);
			System.out.println("device_token:"+deviceTokenStr+","+deviceTokenStr.length());
			
            listcast.setDeviceTokens(deviceTokenStr);
			listcast.setAlert(alertInfo);
			listcast.setBadge(0);
			listcast.setSound("default");
			// TODO set 'production_mode' to 'true' if your app is under production mode
			listcast.setProductionMode();
			//listcast.setTestMode();
			// Set customized fields
			//broadcast.setCustomizedField("test", "helloworld");
			client.send(listcast);
		}
	
	// 自定义播，ios
	public void sendIOSCustomizedcast() throws Exception {
		IOSCustomizedcast customizedcast = new IOSCustomizedcast(appkey, appMasterSecret);
		// TODO Set your alias and alias_type here, and use comma to split them if there
		// are multiple alias.
		// And if you have many alias, you can also upload a file containing these
		// alias, then
		// use file_id to send customized notification.
		customizedcast.setAlias("alias", "alias_type");
		customizedcast.setAlert("IOS 涓�у寲娴嬭瘯");
		customizedcast.setBadge(0);
		customizedcast.setSound("default");
		// TODO set 'production_mode' to 'true' if your app is under production mode
		customizedcast.setTestMode();
		client.send(customizedcast);
	}

	// 组播，ios
	public void sendIOSGroupcast(List<String> tags, String message) throws Exception {
		IOSGroupcast groupcast = new IOSGroupcast(appkey, appMasterSecret);
		/*
		 * TODO Construct the filter condition: "where": { "and": [ {"tag":"iostest"} ]
		 * }
		 */
		JSONObject filterJson = new JSONObject();
		JSONObject whereJson = new JSONObject();
		JSONArray tagArray = new JSONArray();
		JSONObject testTag = new JSONObject();
		for (String tag : tags) {
			testTag.put("tag", tag);
			tagArray.add(testTag);
		}
		/*
		 * testTag.put("tag", "iostest"); tagArray.add(testTag);
		 */
		whereJson.put("and", tagArray);
		filterJson.put("where", whereJson);
		System.out.println(filterJson.toString());

		// Set filter condition into rootJson
		groupcast.setFilter(filterJson);
		groupcast.setAlert(message);
		groupcast.setBadge(0);
		groupcast.setSound("default");
		// TODO set 'production_mode' to 'true' if your app is under production mode
		groupcast.setProductionMode();
		// groupcast.setTestMode();
		client.send(groupcast);
	}

	//测试   
	public static void main(String[] args) {
		UPushClient androidClient = new UPushClient(UPushConfig.appkey_android, UPushConfig.appMasterSecret_android);
		List<String> androidDevices=Arrays.asList("Aqhhsgy8K89fe9IXHl_U0Cit457dkrFv5OvFVtPPVNBj","Au9aStHNh3fMLWCQjSeG5tCnkiGW751H_V39dRAmWBdN");
		try {
			if(!androidDevices.isEmpty()) {
			     androidClient.sendAndroidListcast(androidDevices, "SFBR推送android测试信息");
			}
		} catch (Exception e) {
			System.out.println("send android alarm error");
			e.printStackTrace();
		}
		
		
		/*UPushClient iosClient = new UPushClient(UPushConfig.appkey_ios, UPushConfig.appMasterSecret_ios);
		List<String> iosDevices=Arrays.asList("","");
		try {
			if(!iosDevices.isEmpty()) {
			     iosClient.sendIOSListcast(iosDevices, "SFBR推送ios测试信息");
			}
		} catch (Exception e) {
			System.out.println("send ios alarm error");
			e.printStackTrace();
		}*/
	}

}
