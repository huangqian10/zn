package zn.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import zn.entity.User;
import zn.entity.XUser;



public interface UserDao {
	public int checkTel(String telephone);

	public int checkLogin(@Param("telephone")String telephone,@Param("password")String password);

	public int changeUserState(@Param("userId")int userId,@Param("lastLoadTime")String lastLoadTime);
	public   int   selectIdByTel(String telephone);  
	public int addUserAndGetId(User user);
	
	public int  addUserAndOrg(User user);
	
	public    XUser selectUserById(Integer userId);
	public int  deleteUser(Integer userId);
	
	public int changeUserInfo(@Param("information")String information,@Param("userName")String userName,@Param("userId")int userId);
	
	public List<XUser> selectUserByOrg(int orgId);
	public List<XUser> selectAllUser();
	
	
	public int changePassword(@Param("password")String password,@Param("userId")int userId);
	
	public int changePicUrl(@Param("userId")int userId,@Param("userPicUrl")String userPicUrl);
	public String selectUserPic(int userId);
	
	public  int  userAddMon(Map<String,Object>     param);
	public int userDelteMon(int userId);
	public   List<User>   seleteUserByMonId(int monId);
	public int  deleteUserAndOrg(int userId);
	
	public int userAddLimits(@Param("limitsId")int limitsId,@Param("userId")int userId);
	public int userDeleteLimits(int userId);
	public String seleteUserLimitsById(Integer userId);
	
	public List<Map<String,Object>> seleteUserListByMonId(int monId);
	
	
	public List<Integer> selectUserIdList(int  monId);
	//获取App运行终端信息
	public List<Map<String,Object>> selectDeviceTokenListByMonId(int monId);
	
	public List<String> seleteAllUserNumber();
	public  int   addMoreUser(List<XUser> list  );
	public int deleteMoreUser(List<String> list);
	public int changeUser(XUser xUser);
	public XUser selectXUserById(int userId);
	
	public int deleteUserMonitor();

	public void setUserDeviceToken(@Param("userId")Integer userId,@Param("deviceToken") String deviceToken,@Param("phoneType")Integer phoneType);
	
}
