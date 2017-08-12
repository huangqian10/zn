/**
 * 
 */
package zn.controller.organization;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import zn.service.OrganizationService;
import zn.until.NoteResult;

/**
 * @author hq
 *
 */
@Controller
@RequestMapping("/org")
public class SelectOrganizationController {
	
	@Resource
	private OrganizationService organizationService;
	
	
	@RequestMapping("/select")
	@ResponseBody
	public NoteResult execute(){
		NoteResult note=organizationService.selectOrg();	
		return note;	
	}

}
