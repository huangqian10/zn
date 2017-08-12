/**
 * 
 */
package zn.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import org.springframework.web.multipart.MultipartFile;

import zn.dao.UserDao;
import zn.until.NoteResult;
import zn.until.ReadFile;

/**
 * @author hq
 *
 */
@Service("pictureService")
public class PictureServiceImpl implements PictureService {
	@Resource//注入
	private UserDao userDao;

	
	@Override
	public NoteResult deletePictures(Integer userId) {
		NoteResult note=new NoteResult(); 
		 if(userId==null){
			 	note.setStatus(1);
				note.setMsg("参数不能为空");
				note.setData("");
		 }else{
		String path=userDao.selectUserPic(userId);
		userDao.changePicUrl(userId, "");
	
		File filePath=new File(path);
 		ReadFile.deleteFile(filePath);
 	
		note.setStatus(0);
		note.setMsg("操作成功");
		note.setData("");
	}
		 return note;
	}

	@Override
	public String findUserPicture(int userId) {
		
		
		String path=userDao.selectUserPic(userId);
		return path;
	
	}

	
	@Override
	public NoteResult uploadPictures(MultipartFile file, String pathUrl, String fileName,int userId,String realPath) {
		NoteResult note=new NoteResult(); 
	 
	   
	        
	    if (null == fileName || 0 == fileName.length()) {
	    	note.setStatus(1);
	        note.setMsg("必须输入文件");
	        note.setData("");
	 	// 文件后缀判断
	 	} else{
	 			
	 	File upLoadFile = new File(realPath+File.separator+fileName);
	 	userDao.changePicUrl(userId, pathUrl+File.separator+fileName);
	 	try {
	 		File filePath=new File(realPath);
	 		ReadFile.deleteFile(filePath);
	 		
	 		filePath.mkdirs();
	 	
	 	
			file.transferTo(upLoadFile);
		} catch (IllegalStateException | IOException e) {
			
	
			
			e.printStackTrace();
		} 
			note.setStatus(0);
	 		note.setMsg("操作成功");
	 		note.setData("");
	 	}
	 
			return note;
	}

	
	

	
	
	
	
}
