/**
 * 
 */
package zn.controller.monalarms;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import zn.service.MonAlarmsService;
import zn.service.MonitorService;
import zn.until.NoteResult;

/**
 * @author hq
 *
 */
@Controller
@RequestMapping("/monAlarms")
public class DeleteMonAlarmsByMonIdController {
	@Resource
	private MonAlarmsService monAlarmsService;
	
	@RequestMapping("/deleteMonAlarmsByMonId")
	@ResponseBody
	public NoteResult execute(Integer monId){
		NoteResult note=monAlarmsService.deleteMonAlarmsByMonId(monId);	
		return note;
		
	}

}
