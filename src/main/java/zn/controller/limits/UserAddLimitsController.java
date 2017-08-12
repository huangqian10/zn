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
public class UserAddLimitsController {
	@Resource
	private LimitsService limitsService;
	
	@RequestMapping("/userAddLimits")
	@ResponseBody
	public NoteResult execute(Integer limitsId,Integer userId){
		NoteResult note=limitsService.userAddLimits(limitsId, userId);	
		return note;
		
	}

}
