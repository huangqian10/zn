package zn.service;

import java.util.Map;

import zn.until.NoteResult;

public interface UserService {
	public NoteResult checkTel(String checkTel);

	public Map<String, Object> checkLogin(String telephone, String password);

	public NoteResult creatUser(String userStr, Integer userId);

	public NoteResult changeUserInfo(String information, String userName, Integer userId);

	public NoteResult selectUserByOrg(Integer orgId);

	public NoteResult selectUserById(Integer userId);

	public NoteResult changePassword(String oldPassword, String nowFirstPassword, String nowTwoPassword,
			Integer userId);

	public NoteResult deleteUser(Integer userId, Integer ownId);

	public NoteResult userAddMon(String jsonStr);

	public NoteResult seleteUserByMonId(Integer monId);

	public NoteResult selectAllUser();

	public NoteResult seleteUserListByMonId(Integer monId);

	public NoteResult changeOthersPassword(String password, Integer userId);
	
	//设置用户设备DeviceToken,用于消息推送    @author hl
	public NoteResult setUserDeviceToken(Integer userId,String deviceToken, Integer phoneType);

}
