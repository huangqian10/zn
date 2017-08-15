/**
 * 
 */
package zn.controller.monalarms;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import zn.service.MonAlarmsService;
import zn.until.NoteResult;

/**
 * @author hq
 *
 */
@Controller
@RequestMapping("/monAlarms")
public class SelectAllMonAlarmsController {
	@Resource
	private MonAlarmsService monAlarmsService;
	
	@RequestMapping("/selectAllMonAlarms")
	@ResponseBody
	public NoteResult execute(HttpSession session){
		int userId=Integer.parseInt((String) session.getAttribute("userId"));
		NoteResult note=monAlarmsService.selectAllMonAlarms(userId);	
		return note;
		
	}
	
	
	@RequestMapping("selectAllMonAlarmsPaging")
	@ResponseBody
	public NoteResult executes(HttpSession session,Integer pageIndex, Integer pageSize){
		int userId=Integer.parseInt((String) session.getAttribute("userId"));
		NoteResult note=monAlarmsService.selectAllMonAlarmsPaging(userId,pageIndex,pageSize);	
		return note;
		
	}
}
