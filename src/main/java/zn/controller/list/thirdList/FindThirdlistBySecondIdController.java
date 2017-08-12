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
public class FindThirdlistBySecondIdController {
	@Resource
	private MonThirdListService  monThirdListService;
	
	@RequestMapping("/findBySecondId")
	@ResponseBody
	public NoteResult execute(Integer secondListId){
		NoteResult note= monThirdListService.findThirdlistBySecondId(secondListId);
		return note;
		
	}
}
