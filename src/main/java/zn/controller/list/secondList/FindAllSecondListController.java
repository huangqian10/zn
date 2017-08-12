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
public class FindAllSecondListController {
	@Resource
	private MonSecondListService monSecondListService;
	
	@RequestMapping("/findAll")
	@ResponseBody
	public NoteResult execute(){
		NoteResult note=monSecondListService.findAllSecondList();
		return note;
		
	}

}
