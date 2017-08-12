/**
 * 
 */
package zn.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import zn.dao.LimitsDao;
import zn.entity.Limits;
import zn.entity.User;
import zn.until.NoteResult;

/**
 * @author hq
 *
 */
@Service("limitsListService")
@Transactional
public class LimitsServiceImpl implements LimitsService {
	
	
	@Resource//注入
	private LimitsDao  limitsDao;  
	
	/**
	 * 为用户添加权限
	 */
	public NoteResult userAddLimits(Integer limitsId, Integer userId) {
		NoteResult note=new NoteResult();
		if(limitsId==null||userId==null){
			note.setStatus(1);
			note.setMsg("参数不能为空");
			note.setData("");
		
		}else{
			limitsDao.userDeleteLimits(userId);
			limitsDao.userAddLimits(limitsId, userId);
			note.setStatus(0);
			note.setMsg("操作成功");
			note.setData("");
		}
			return note;
	}

	
	/**
	 * 查询指定权限下的用户信息
	 */
	public NoteResult selectUserByLimitsId(Integer limitsId) {
		NoteResult note=new NoteResult();
		if(limitsId==null){
			note.setStatus(1);
			note.setMsg("参数不能为空");
			note.setData("");
		
		}else{
			List<User> list=  limitsDao.selectUserByLimitsId(limitsId);
			note.setStatus(0);
			note.setMsg("操作成功");
			note.setData(list);
		}
		return note;
	}

	/**
	 * 获取权限列表
	 */
	public NoteResult selectLimitsList() {
		NoteResult note=new NoteResult();
	
			List<Limits> list=  limitsDao.selectLimitsList();
			note.setStatus(0);
			note.setMsg("操作成功");
			note.setData(list);
		
		return note;
	
	}

}
