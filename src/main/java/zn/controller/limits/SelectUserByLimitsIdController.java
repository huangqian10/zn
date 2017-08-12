/**
 * 
 */
package zn.controller.limits;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import zn.service.LimitsService;
import zn.until.NoteResult;

/**
 * @author hq
 *
 */
@Controller
@RequestMapping("/limits")
public class SelectUserByLimitsIdController {
	@Resource
	private LimitsService limitsService;
	
	@RequestMapping("/selectUserByLimitsId")
	@ResponseBody
	public NoteResult execute(Integer limitsId){
		NoteResult note=limitsService.selectUserByLimitsId(limitsId);	
		return note;
		
	}

}
