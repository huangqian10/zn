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
public class SeleteMonListByUserIdController {
	@Resource
	private MonitorService monitorService;
	
	@RequestMapping("/seleteMonListByUserId")
	@ResponseBody
	public NoteResult execute(Integer userId){
		NoteResult note=monitorService.seleteUserListByMonId(userId);	
		return note;	
	}

}
