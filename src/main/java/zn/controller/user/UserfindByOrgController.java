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
public class UserfindByOrgController {
	@Resource
	private UserService userService;
	

	
	@RequestMapping("/findByOrg")
	@ResponseBody
	public NoteResult execute(Integer orgId){
		NoteResult note=userService.selectUserByOrg(orgId);	
		return note;	
	}
}
