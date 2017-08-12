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
public class AddSecondListController {
	@Resource
	private MonSecondListService monSecondListService;
	
	@RequestMapping("/add")
	@ResponseBody
	public NoteResult execute(String secondListName,Integer firstListId){
		NoteResult note=monSecondListService.addSecondList(secondListName, firstListId);	
		return note;
		
	}

}
