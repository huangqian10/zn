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
public class SeleteMonByUserIdController {
	@Resource
	private MonitorService monitorService;
	
	@RequestMapping("/seleteMonByUserId")
	@ResponseBody
	public NoteResult execute(Integer userId){
		NoteResult note=monitorService.seleteMonByUserId(userId);	
		return note;
		
	}

}
