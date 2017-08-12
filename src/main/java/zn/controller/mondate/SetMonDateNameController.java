/**
 * 
 */
package zn.controller.mondate;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import zn.service.MonDateService;
import zn.until.NoteResult;

/**
 * @author hq
 *
 */
@Controller
@RequestMapping("/monDate")
public class SetMonDateNameController {
	@Resource
	private MonDateService monDateService;
	
	@RequestMapping("/setMonDateName")
	@ResponseBody
	public NoteResult execute(String monDateName,Integer route,Integer monId){
		NoteResult note=monDateService.setMonDateName(monDateName, route, monId);	
		return note;
		
	}
}
