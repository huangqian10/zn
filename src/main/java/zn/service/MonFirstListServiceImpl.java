/**
 * 
 */
package zn.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import zn.dao.MonFirstListDao;
import zn.dao.MonitorDao;
import zn.entity.MonFirstList;
import zn.until.NoteResult;

/**
 * @author hq
 *
 */

@Service("monFirstListService")
@Transactional
public class MonFirstListServiceImpl implements MonFirstListService {
	
	@Resource//注入
	private MonFirstListDao  monFirstListDao; 
	
	@Resource//注入
	private MonitorDao monitorDao;
	
	/**
	 * 添加一级列表
	 */
	public NoteResult addFirstList(String firstListName) {
			NoteResult note=new NoteResult();
		
			 if(firstListName==null){
				 	note.setStatus(1);
					note.setMsg("参数不能为空");
					note.setData("");
			 } else if(monFirstListDao.selectIsExist(firstListName)!=0){
				 	note.setStatus(2);
					note.setMsg("列表已存在");
					note.setData("");
			 }else{
				 	monFirstListDao.addFirstList(firstListName);
				 	note.setStatus(0);
					note.setMsg("添加列表成功");
					note.setData("");
			 }
		return note;
	}

	
	/**
	 * 更改一级列表
	 */
	public NoteResult changeFirstList(String firstListName, Integer firstListId) {
		NoteResult note=new NoteResult();
		
		 if(firstListName==null||firstListId==null){
			 	note.setStatus(1);
				note.setMsg("参数不能为空");
				note.setData("");
		 }else if(monFirstListDao.selectIsExist(firstListName)!=0){
			 	note.setStatus(2);
				note.setMsg("列表已存在");
				note.setData("");
		 }else{
			 MonFirstList mon=monFirstListDao.selectListById(firstListId);
			 	if(mon!=null){
				monitorDao.changeMonPlace(mon.getFirstListName(), firstListName, 1);
			 	}
			 	monFirstListDao.changeFirstList(firstListName, firstListId);
			 	note.setStatus(0);
				note.setMsg("更改成功");
				note.setData("");
		 }
		return note;
	}

	/**
	 * 删除一级列表
	 */
	public NoteResult deleteFirstList(Integer firstListId) {
		NoteResult note=new NoteResult();	
		 if(firstListId==null){
			 	note.setStatus(1);
				note.setMsg("参数不能为空");
				note.setData("");
		 }else{
			 MonFirstList mon=monFirstListDao.selectListById(firstListId);
			
			 	if(mon!=null){
			 	monitorDao.deleteMonPlace(mon.getFirstListName(),1);
		 }
			 	monFirstListDao.deleteFirstList(firstListId);
				note.setStatus(0);
				note.setMsg("删除成功");
				note.setData("");
		 }
		return note;
	}

	/**
	 * 查询所有一级列表
	 */
	public NoteResult findAllFirstlist() {
		NoteResult note=new NoteResult();
		List<MonFirstList> list  =monFirstListDao.findAllFirstlist();
		note.setStatus(0);
		note.setMsg("查询成功");
		note.setData(list);
		return note;
	}
	


}
