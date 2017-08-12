/**
 * 
 */
package zn.controller.moitor;

import javax.annotation.Resource;

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
public class ChangeMonitorController {
	@Resource
	private MonitorService monitorService;
	
	@RequestMapping("/change")
	@ResponseBody
	public NoteResult execute(String monStr){
		NoteResult note=monitorService.changeMon(monStr);	
		return note;
		
	}

}
