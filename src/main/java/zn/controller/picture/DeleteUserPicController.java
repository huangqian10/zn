/**
 * 
 */
package zn.controller.picture;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import zn.service.PictureService;
import zn.until.NoteResult;

/**
 * @author hq
 *
 */
@Controller  
@RequestMapping("/uploadPictures")
public class DeleteUserPicController {
	@Resource
	private PictureService pictureService;
	
	@RequestMapping("/deleteUserPic")	
	@ResponseBody
	public NoteResult execute(Integer userId){
		NoteResult note=pictureService.deletePictures(userId);	
		return note;	
	}
	

}
