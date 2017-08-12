/**
 * 
 */
package zn.controller.picture;

import java.io.File;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import zn.service.PictureService;
import zn.service.UserService;
import zn.until.NoteResult;

/**
 * @author hq
 *
 */
@Controller  
@RequestMapping("/uploadPictures")  
public class UploadPicturesController {
	@Resource
	private PictureService pictureService;
	

	
	@RequestMapping(value = "/load",method = RequestMethod.POST)	
	@ResponseBody
	public NoteResult execute(HttpServletRequest request,@RequestParam(value = "file1") MultipartFile file){
	 	String pathUrl =request.getContextPath()+ File.separator+"picture"+File.separator +request.getSession().getAttribute("userId");
	 	String realPath=request.getSession().getServletContext().getRealPath("")+"picture"+File.separator +request.getSession().getAttribute("userId");
	 	int userId=Integer.parseInt((String)request.getSession().getAttribute("userId"));
	 
	 	
	 	String fileName = file.getOriginalFilename();
		NoteResult note=pictureService.uploadPictures(file,pathUrl,fileName,userId,realPath);	

		return note;	
	}
}
