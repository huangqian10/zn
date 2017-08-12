/**
 * 
 */
package zn.controller.list.fourthList;

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
@RequestMapping("/fourthList")
public class FindFourthListAndMonController {
	@Resource
	private MonThirdListService  monThirdListService;
	
	@RequestMapping("/findFourthListAndMon")
	@ResponseBody
	public NoteResult execute(Integer thirdListId){
		NoteResult note= monThirdListService.findFourthListAndMon(thirdListId);
		return note;
		
	}
}


