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
public class AddMonitorController {
	@Resource
	private MonitorService monitorService;
	
	@RequestMapping("/add")
	@ResponseBody
	public NoteResult execute(String monStr,HttpSession session){
		int userId=Integer.parseInt((String)session.getAttribute("userId"));

		NoteResult note=monitorService.addMon(monStr,userId);	
	
		return note;
		
	}


}
