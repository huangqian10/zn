package zn.service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONException;

import zn.dao.LoginDao;
import zn.dao.UserDao;
import zn.entity.User;
import zn.entity.XUser;
import zn.until.NoteResult;
import zn.until.NoteUtil;
import zn.until.WebService;

@Service("userService")
@Transactional
public class UserServiceImpl implements UserService{

	@Resource//注入
	private UserDao userDao;
	

	@Resource
	private LoginDao loginDao;
	
	/**
	 * 用户登陆
	 */
	public Map<String,Object> checkLogin(String telephone,String password){
		SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Map<String,Object> map=new HashMap<String,Object>();
//		Date date=new Date((new Date()).getTime()-2*60*60*1000);
//		Date date2=new Date(new Date().getTime());
//		String nowTime=format.format(date2);
//		String agoTime=format.format(date);
//		loginDao.deleteOutModedCount(agoTime);//清楚超过2小时的登陆记录
		NoteResult note=new NoteResult();
		int state=userDao.checkTel(telephone);	
		if(state==0){          					//检查用户的账号是否正确    
		note.setStatus(1);
		note.setMsg("账号错误");
		note.setData("");
		}else{ 
			int userState=userDao.checkLogin(telephone, password);  
			int userId=userDao.selectIdByTel(telephone);
			if(userState==0){
//				Integer loginState=loginDao.countIsExist(userId);
//				if(loginState==null){
//					loginDao.addCount(userId);
					note.setStatus(2);
					note.setMsg("密码错误");
					note.setData("");
//				}else if(loginState>=4){
//					note.setStatus(3);
//					note.setMsg("密码错误超过5次");
//					note.setData("");		
//				}else{
//				loginDao.autoAddCount(userId);
//				note.setStatus(2);
//				note.setMsg("密码错误");
//				note.setData("");	
//				}
			}else{
//				loginDao.deleteCountByuserId(userId);
				String lastLoadTime=format.format(new Date());
				
				userDao.changeUserState(userId,lastLoadTime);
				
				note.setStatus(0);
				note.setMsg("登陆成功");
				note.setData(userId);
				map.put("telephone", telephone);
				map.put("userId", userId+"");
			
			}
		}		
		
		map.put("NoteResult", note);
		
		return map;
	}
	
	  
	/**
	 * 检验账号是否存在
	 */
	public NoteResult checkTel(String checkTel) {
		NoteResult note=new NoteResult();
		int state=userDao.checkTel(checkTel);	
		if(state!=0){
		note.setStatus(1);
		note.setMsg("账号存在");
		note.setData("");
		}else{
			note.setStatus(0);
			note.setMsg("账号不存在");
			note.setData("");	

		}	
	  return note;
	}


	/**
	 * 创建账号
	 */
	public NoteResult creatUser(String userStr,Integer userId) {
		NoteResult note=new NoteResult();
	    String a= 	userDao.seleteUserLimitsById(userId);
		if(userId==null){
			note.setStatus(5);
			note.setMsg("参数为空");
			note.setData("");
			return note;
		}else if(!a.equals("e142fa89-4a1f-48a6-9735-a065fee512dc")||a==null){
			note.setStatus(6);
			note.setMsg("权限不足");
			note.setData("");
			return note;
		}
		try {
		User user=JSON.parseObject(userStr, User.class);
		if(user==null){
			note.setStatus(5);
			note.setMsg("参数为空");
			note.setData("");
			return note;
		}
		String telephone=user.getTelephone();
		String password=user.getPassword();
		String userName=user.getUserName();
		Integer limitsId=user.getLimitsId();
		if(telephone==null){
			note.setStatus(1);
			note.setMsg("手机号不能为空");
			note.setData("");	
		}else if(!telephone.matches("^[1]\\d{10}$")){
			note.setStatus(1);
			note.setMsg("手机号格式不正确");
			note.setData("");	
		}else if(userDao.checkTel(telephone)!=0){
			note.setStatus(1);
			note.setMsg("手机号已被注册");
			note.setData("");	
		}else if(password==null){
			note.setStatus(2);
			note.setMsg("密码不能为空");
			note.setData("");	
		}else if(!password.matches("^\\w{6,16}$")){
			note.setStatus(2);
			note.setMsg("密码格式不对,密码为大于6位小于16位的纯数字或字母");
			note.setData("");		
		}else if(limitsId==0){
			note.setStatus(5);
			note.setMsg("权限id不能为空");
			note.setData("");	
		}else if(userName==null){
			note.setStatus(3);
			note.setMsg("用户名不能为空");
			note.setData("");	
		}else{
				
			user.setPassword(NoteUtil.md5(password));
			userDao.addUserAndGetId(user);
			userDao.addUserAndOrg(user);
			userDao.userDeleteLimits(user.getUserId());
			userDao.userAddLimits(user.getLimitsId(), user.getUserId());
			note.setStatus(0);
			note.setMsg("创建用户成功");
			note.setData("");
		}	
		
		} catch (JSONException e) {
			note.setStatus(4);
			note.setMsg("参数格式错误");
			note.setData("");
		}catch(ClassCastException c){
			note.setStatus(4);
			note.setMsg("参数格式错误");
			note.setData("");
		}
		return note;
	}


	/**
	 * 更改用户信息.
	 */
	public NoteResult changeUserInfo( String information, String userName,Integer userId) {
		NoteResult note=new NoteResult();
		if(userName==null||userId==null){
			note.setStatus(1);
			note.setMsg("参数不能为空");
			note.setData("");	
		}else{
//		User user=new User();
//		user.setUserId(userId);
//		if(orgId!=null){
//		user.setOrgId(orgId);
//		userDao.deleteUserAndOrg(userId);
//		userDao.addUserAndOrg(user);
//		}
		
		XUser  user=userDao.selectUserById(userId);
		String result=new  WebService().ModifyUserName(user.getTelephone(),userName);
		
		if("true".equals(result)){
		userDao.changeUserInfo(information, userName,userId);
		note.setStatus(0);
		note.setMsg("更改成功");
		note.setData("");
		}else {
			note.setStatus(2);
			note.setMsg("更改失败");
			note.setData("");
		}
		}
		return note;
	}


	/**
	 * 根据组织查询用户
	 */
	public NoteResult selectUserByOrg(Integer orgId) {
		NoteResult note=new NoteResult();
		if(orgId==0){
			note.setStatus(1);
			note.setMsg("参数不能为空");
			note.setData("");	
		}else{
		List<XUser> list=userDao.selectUserByOrg(orgId);
			note.setStatus(0);
			note.setMsg("查询成功");
			note.setData(list);	
		}
		return note;
	}
	
	public NoteResult selectAllUser(){
		NoteResult note=new NoteResult();
		List<XUser> list=userDao.selectAllUser();
		note.setStatus(0);
		note.setMsg("查询成功");
		note.setData(list);	
		return note;
	}


	/**
	 * 根据用户id查询用户
	 */
	public NoteResult selectUserById(Integer userId) {
		NoteResult note=new NoteResult();
		if(userId==null){
			note.setStatus(1);
			note.setMsg("参数不能为空");
			note.setData("");	
		}else{
			XUser list=userDao.selectUserById(userId);
			note.setStatus(0);
			note.setMsg("查询成功");
			note.setData(list);	
		}
		return note;
	}


	/**
	 * 更改用户密码
	 */
	public NoteResult changePassword(String oldPassword,String nowFirstPassword,String nowTwoPassword,Integer userId) {
		NoteResult note=new NoteResult();
		XUser  user=userDao.selectUserById(userId);
		if(oldPassword==null||userId==null||nowFirstPassword==null||nowTwoPassword==null){
				note.setStatus(1);
				note.setMsg("参数不能为空");
				note.setData("");	
		}else if(!user.getUserNumber().equals("e142fa89-4a1f-48a6-9735-a065fee512dc")){
			note.setStatus(6);
			note.setMsg("权限不足");
			note.setData("");
		}else if(!user.getPassword().equals(oldPassword)){
			note.setStatus(3);
			note.setMsg("原密码输入错误");
			note.setData("");	
		
		}else if(!nowFirstPassword.equals(nowTwoPassword)){
			note.setStatus(4);
			note.setMsg("两次输入密码不一致");
			note.setData("");
		}else if(oldPassword.equals(nowTwoPassword)){
			note.setStatus(5);
			note.setMsg("修改密码和初始密码相同");
			note.setData("");
		}
		else{
			String result=new WebService().ModifyUserPass(user.getTelephone(),nowFirstPassword);
			System.out.println(result);
			if("true".equals(result)){
			userDao.changePassword(nowFirstPassword, userId);
			note.setStatus(0);
			note.setMsg("更改密码成功");
			note.setData("");
			}else {
				note.setStatus(2);
				note.setMsg("更改密码失败");
				note.setData("");	
			}
		}
		return note;
	}
	
	
	public NoteResult changeOthersPassword(String password,Integer userId){
		NoteResult note=new NoteResult();
		if(password==null||userId==null){
			note.setStatus(1);
			note.setMsg("参数不能为空");
			note.setData("");	
		}else if(!password.matches("^\\w{6,16}$")){
			note.setStatus(3);
			note.setMsg("密码格式不对,密码为大于6位小于16位的纯数字或字母");
			note.setData("");	
			
		}else {
		userDao.changePassword(password, userId);
		note.setStatus(0);
		note.setMsg("更改密码成功");
		note.setData("");
	}
		return note;
	}
	


	/**
	 * 根据id删除用户
	 */
	public NoteResult deleteUser(Integer userId,Integer ownId) {
		NoteResult note=new NoteResult();
		if(userId==null){
			note.setStatus(1);
			note.setMsg("参数不能为空");
			note.setData("");	
		}else if(ownId==userId){
			note.setStatus(2);
			note.setMsg("不能删除自己的账号");
			note.setData("");	
		}else{
			userDao.deleteUser(userId);
			note.setStatus(0);
			note.setMsg("删除用户成功");
			note.setData("");	
		}
		return note;
	}


	/**
	 * 用户添加设备关联
	 */
	public NoteResult userAddMon(String jsonStr) {
		NoteResult note=new NoteResult();
		if(jsonStr==null||"".equals(jsonStr)){
			note.setStatus(1);
			note.setMsg("参数不能为空");
			note.setData("");
			return note;
		}
		try {
			Map<String,Object> map=JSON.parseObject(jsonStr);
		if(map==null||map.isEmpty()){				
			note.setStatus(1);
			note.setMsg("参数不能为空");
			note.setData("");
			return note;
		}else if(map.get("userId")==null){
			note.setStatus(2);
			note.setMsg("用户id为空");
			note.setData("");
					
		}else if(map.get("monList")==null||"".equals(map.get("monList"))||"[]".equals(map.get("monList"))){
			userDao.userDelteMon((Integer)map.get("userId"));
			note.setStatus(0);
			note.setMsg("清空用户设备列表成功");
			note.setData("");
			
		}else{
			userDao.userDelteMon((Integer)map.get("userId"));
			userDao.userAddMon(map);
			note.setStatus(0);
			note.setMsg("操作成功");
			note.setData("");
		}
		} catch (JSONException e) {
			note.setStatus(4);
			note.setMsg("参数格式错误");
			note.setData("");
		}catch(ClassCastException c){
			note.setStatus(4);
			note.setMsg("参数格式错误");
			note.setData("");
		}
		return note;
	}


	/**
	 * 查询指定设备下的所有用户
	 */
	public NoteResult seleteUserByMonId(Integer monId) {
		NoteResult note=new NoteResult();

		
		if(monId==null){
			note.setStatus(1);
			note.setMsg("参数不能为空");
			note.setData("");
			
		}else{
			
		List<User> list=userDao.seleteUserByMonId(monId);
			note.setStatus(0);
			note.setMsg("操作成功");
			note.setData(list);
		}
		return note;
	}

	public NoteResult   seleteUserListByMonId(Integer monId){
	
		NoteResult note=new NoteResult();

		
		if(monId==null){
			note.setStatus(1);
			note.setMsg("参数不能为空");
			note.setData("");
			
		}else{
			List<Map<String,Object>> list=userDao.seleteUserListByMonId(monId);;
			note.setStatus(0);
			note.setMsg("操作成功");
			note.setData(list);
		}
		return note;

	

	}


	@Override
	public NoteResult setUserDeviceToken(Integer userId, String deviceToken,Integer phoneType) {
		NoteResult result=new NoteResult();
		if(userId==null||phoneType==null||deviceToken==null||"".equals(deviceToken)) {
		   result.setStatus(1);
		   result.setMsg("参数错误");
		   return result;
		}else {
		   userDao.setUserDeviceToken(userId,deviceToken,phoneType);
		   result.setStatus(0);
		   result.setMsg("设置用户终端信息成功");
		}
		return result;
	}
	
	
}
