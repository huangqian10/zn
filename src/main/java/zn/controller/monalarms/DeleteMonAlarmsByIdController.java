/**
 * 
 */
package zn.controller.monalarms;

import javax.annotation.Resource;

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
public class DeleteMonAlarmsByIdController {
	@Resource
	private MonAlarmsService monAlarmsService;
	
	@RequestMapping("/deleteMonAlarmsById")
	@ResponseBody
	public NoteResult execute(Integer alarmsId){
		NoteResult note=monAlarmsService.deleteMonAlarmsById(alarmsId);	
		return note;
		
	}


}
