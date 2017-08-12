/**
 * 
 */
package zn.controller.list.thirdList;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import zn.service.MonSecondListService;
import zn.service.MonThirdListService;
import zn.until.NoteResult;

/**
 * @author hq
 *
 */
@Controller
@RequestMapping("/thirdList")
public class AddthirdListController {
	@Resource
	private MonThirdListService  monThirdListService;
	
	@RequestMapping("/add")
	@ResponseBody
	public NoteResult execute(String thirdListName,Integer secondListId){
		NoteResult note= monThirdListService.addThirdList(thirdListName, secondListId);	
		return note;
		
	}


}
