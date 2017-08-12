/**
 * 
 */
package zn.service;

import javax.servlet.http.HttpServletResponse;

import org.springframework.web.multipart.MultipartFile;

import zn.until.NoteResult;

/**
 * @author hq
 *
 */
public interface PictureService {
	public NoteResult uploadPictures(MultipartFile file, String pathUrl, String fileName, int userId, String realPath);

	public NoteResult deletePictures(Integer userId);

	public String findUserPicture(int userId);
}
