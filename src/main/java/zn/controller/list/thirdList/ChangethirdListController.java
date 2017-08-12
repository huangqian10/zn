/**
 * 
 */
package zn.controller.list.thirdList;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import zn.service.MonThirdListService;
import zn.until.NoteResult;

/**
 * @author hq
 *
 */
@Controller
@RequestMapping("/thirdList")
public class ChangethirdListController {
	@Resource
	private MonThirdListService  monThirdListService;
	
	@RequestMapping("/change")
	@ResponseBody
	public NoteResult execute(String thirdListName,Integer thirdListId){
		NoteResult note= monThirdListService.changeThirdList(thirdListName,  thirdListId);	
		return note;
		
	}

}
