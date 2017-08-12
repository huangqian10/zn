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
public class UserAddMonController {
	@Resource
	private UserService userService;
	
	@RequestMapping("/userAddMon")
	@ResponseBody
	public NoteResult execute(String jsonStr){
		NoteResult note=userService.userAddMon(jsonStr);	
		return note;	
	}



}
