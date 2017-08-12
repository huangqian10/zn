/**
 * 
 */
package zn.controller.mondate;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import zn.service.MonAlarmsService;
import zn.service.MonDateService;
import zn.until.NoteResult;

/**
 * @author hq
 *
 */
@Controller
@RequestMapping("/monDate")
public class SelectMonDateByIdController {
	
	@Resource
	private MonDateService monDateService;
	
	@RequestMapping("/selectMonDateById")
	@ResponseBody
	public NoteResult execute(Integer monId){
		NoteResult note=monDateService.selectMonDateById(monId);	
		return note;
		 
	}

}
