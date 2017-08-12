/**
 * 
 */
package zn.controller.moitor;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import zn.service.MonitorService;
import zn.until.NoteResult;

/**
 * @author hq
 *
 */
@Controller  
@RequestMapping("/monitor") 
public class FindMonNumByStateController {
	@Resource
	private MonitorService monitorService;
	
	@RequestMapping("/findMonNumByState")
	@ResponseBody
	public NoteResult execute(HttpSession session){
		String userId= (String) session.getAttribute("userId");
		
		int user=Integer.parseInt(userId);
		NoteResult note=monitorService.findMonNumByState(user);	
		return note;
		
	}
}
