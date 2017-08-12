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
public class FindSecondlistByFirstIdController {
	@Resource
	private MonSecondListService monSecondListService;
	
	@RequestMapping("/findByFirst")
	@ResponseBody
	public NoteResult execute(Integer firstListId){
		NoteResult note=monSecondListService.findSecondlistByFirstId(firstListId);
		return note;
		
	}
}
