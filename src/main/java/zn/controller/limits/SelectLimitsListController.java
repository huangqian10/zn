/**
 * 
 */
package zn.controller.limits;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import zn.service.LimitsService;
import zn.service.MonitorService;
import zn.until.NoteResult;

/**
 * @author hq
 * 
 */
@Controller
@RequestMapping("/limits")
public class SelectLimitsListController {
	@Resource
	private LimitsService limitsService;
	
	@RequestMapping("/selectLimitsList")
	@ResponseBody
	public NoteResult execute(){
		NoteResult note=limitsService.selectLimitsList();	
		return note;
		
	}

}
