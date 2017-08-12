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
public class FindSecondListAndMonController {
	@Resource
	private MonSecondListService monSecondListService;
	
	@RequestMapping("/findSecondListAndMon")
	@ResponseBody
	public NoteResult execute(Integer firstListId){
		NoteResult note=monSecondListService.findSecondListAndMon(firstListId);
		return note;
		
	}
}
