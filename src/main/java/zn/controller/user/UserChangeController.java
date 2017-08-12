/**
 * 
 */
package zn.controller.user;

import javax.annotation.Resource;

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
public class UserChangeController {
	@Resource
	private UserService userService;
	
	@RequestMapping("/change")
	@ResponseBody
	public NoteResult execute(String information,String userName,Integer userId){
		NoteResult note=userService.changeUserInfo(information, userName,userId);	
		return note;	
	}

}
