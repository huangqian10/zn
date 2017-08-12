/**
 * 
 */
package zn.controller.picture;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import zn.service.PictureService;
import zn.until.NoteResult;

/**
 * @author hq
 *
 */
@Controller  
@RequestMapping("/uploadPictures")
public class SelectUserPicController {
	@Resource
	private PictureService pictureService;
	
	@RequestMapping(value = "/selectUserPic",method = RequestMethod.GET)	
	@ResponseBody
	public void execute(HttpServletRequest request,HttpServletResponse response){
		String path=pictureService.findUserPicture(Integer.parseInt((String)request.getSession().getAttribute("userId")));
		File file=new File(path);
		if(!file.exists()){
			return ;
		}
		OutputStream output = null;
		FileInputStream fis = null;
		try{
		output = response.getOutputStream();
		fis = new FileInputStream(file);
		byte[] b = new byte[1024];
		int i = 0;

		while((i = fis.read(b))!=-1){
			output.write(b, 0, i);
		}
			output.write(b, 0, b.length);
			output.flush();
			response.flushBuffer();
		}
		catch(Exception e){
			System.out.println("Error!");
			e.printStackTrace();
		}finally{
			if(fis != null){
				try {
					fis.close();
				} catch (IOException e) {
					
					e.printStackTrace();
				}
				fis = null;
		}
			if(output != null){
				try {
					output.close();
				} catch (IOException e) {
					
					e.printStackTrace();
				}
				output = null;
			}
		}
	
	
	}
}