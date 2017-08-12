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
public class UserfindByIdController {
	@Resource
	private UserService userService;
	

	
	@RequestMapping("/findById")
	@ResponseBody
	public NoteResult execute(Integer userId){
		NoteResult note=userService.selectUserById(userId);	
		return note;	
	}
}
