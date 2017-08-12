/**
 * 
 */
package zn.controller.list.secondList;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import zn.service.MonSecondListService;
import zn.until.NoteResult;

/**
 * @author hq
 *
 */
@Controller
@RequestMapping("/secondList")
public class ChangeSecondListController {
	@Resource
	private MonSecondListService monSecondListService;
	
	@RequestMapping("/change")
	@ResponseBody
	public NoteResult execute(String secondListName,Integer secondListId){
		NoteResult note=monSecondListService.changeSecondList(secondListName,  secondListId);	
		return note;
		
	}
}
