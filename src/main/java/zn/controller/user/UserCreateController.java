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
public class UserCreateController {
	@Resource
	private UserService userService;
	
	@RequestMapping("/create")
	@ResponseBody
	public NoteResult execute(String userStr,Integer userId){
		NoteResult note=userService.creatUser(userStr,userId);	
		return note;	
	}

}
