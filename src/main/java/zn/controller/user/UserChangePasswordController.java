/**
 * 
 */
package zn.controller.user;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import zn.service.UserService;
import zn.until.NoteResult;

/**
 * @author hq
 *
 */
@Controller  
@RequestMapping("/user") 
public class UserChangePasswordController {
	@Resource
	private UserService userService;
	
	@RequestMapping("/changePassword")
	@ResponseBody
	public NoteResult execute(String oldPassword,String nowFirstPassword,String nowTwoPassword,Integer userId){
		NoteResult note=userService.changePassword(oldPassword, nowFirstPassword, nowTwoPassword, userId);
		return note;	
	}
}
