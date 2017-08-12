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
public class SeleteUserByMonIdController {
	@Resource
	private UserService userService;
	
	@RequestMapping("/seleteUserByMonId")
	@ResponseBody
	public NoteResult execute(Integer monId){
		NoteResult note=userService.seleteUserByMonId(monId);	
		return note;	
	}

}
