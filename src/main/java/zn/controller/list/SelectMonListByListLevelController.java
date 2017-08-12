/**
 * 
 */
package zn.controller.list;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import zn.service.MonListService;
import zn.until.NoteResult;



/**
 * @author hq
 *
 */
@Controller
@RequestMapping("/monList")
public class SelectMonListByListLevelController {
	
	@Resource
	private MonListService  monListService;
	
	@RequestMapping("/selectMonListByListLevel")
	@ResponseBody
	public NoteResult execute(Integer listLevel){
		NoteResult note= monListService.selectMonListByListLevel(listLevel);
		return note;
		
	}

}
