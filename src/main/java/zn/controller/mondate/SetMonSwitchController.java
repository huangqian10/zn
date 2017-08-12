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
public class SetMonSwitchController {
	@Resource
	private MonDateService monDateService;
	
	@RequestMapping("/setMonSwitch")
	@ResponseBody
	public NoteResult execute(String switchState,Integer way, String password,Integer monId,Integer userId){
		NoteResult note=monDateService.setMonSwitch(switchState, way, password, monId, userId);	
		return note;
		
	}
}
