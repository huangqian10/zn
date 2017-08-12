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
public class ChangeOrganizationController {
	@Resource
	private OrganizationService organizationService;
	
	
	@RequestMapping("/change")
	@ResponseBody
	public NoteResult execute(String oldOrgName,String nowOrgName){
		NoteResult note=organizationService.changeOrgName(oldOrgName, nowOrgName);	
		return note;	
	}
}
