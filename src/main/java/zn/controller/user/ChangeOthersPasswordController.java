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
public class ChangeOthersPasswordController {
	@Resource
	private UserService userService;
	
	@RequestMapping("/changeOthersPassword")
	@ResponseBody
	public NoteResult execute(String password,Integer userId){
		NoteResult note=userService.changeOthersPassword(password, userId);	
		return note;	
	}

}
