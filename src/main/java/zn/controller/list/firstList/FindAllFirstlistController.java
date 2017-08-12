/**
 * 
 */
package zn.controller.list.firstList;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import zn.service.MonFirstListService;
import zn.until.NoteResult;

/**
 * @author hq
 *
 */
@Controller
@RequestMapping("/firstList")
public class FindAllFirstlistController {
	@Resource
	private MonFirstListService monFirstListService;
	
	@RequestMapping("/findAll")
	@ResponseBody
	public NoteResult execute(){
		NoteResult note=monFirstListService.findAllFirstlist();	
		return note;
		
	}
}
