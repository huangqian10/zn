/**
 * 
 */
package zn.controller.date;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import zn.service.GetDateService;
import zn.until.NoteResult;



/**
 * @author hq
 *
 */
@Controller
@RequestMapping("/date")
public class GetDateController {
	@Resource
	private GetDateService getDateService;
	
	@RequestMapping("/getDate")
	@ResponseBody
	public NoteResult execute(){
		NoteResult note=getDateService.getDate();	
		return note;
		
	}
	
}
