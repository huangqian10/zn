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
public class DeleteFirstListController {
	@Resource
	private MonFirstListService monFirstListService;
	
	@RequestMapping("/delete")
	@ResponseBody
	public NoteResult execute(Integer firstListId){
		NoteResult note=monFirstListService.deleteFirstList(firstListId);	
		return note;
		
	}
}
