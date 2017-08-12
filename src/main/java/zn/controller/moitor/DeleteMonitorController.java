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
public class DeleteMonitorController {
	@Resource
	private MonitorService monitorService;
	
	@RequestMapping("/delete")
	@ResponseBody
	public NoteResult execute(Integer monId){
		NoteResult note=monitorService.deleteMon(monId);	
		return note;
		
	}

}
