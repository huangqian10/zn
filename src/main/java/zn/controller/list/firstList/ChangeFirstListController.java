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
public class ChangeFirstListController {
	@Resource
	private MonFirstListService monFirstListService;
	
	@RequestMapping("/change")
	@ResponseBody
	public NoteResult execute(String firstListName,Integer firstListId){
		NoteResult note=monFirstListService.changeFirstList(firstListName, firstListId);	
		return note;
		
	}
}
