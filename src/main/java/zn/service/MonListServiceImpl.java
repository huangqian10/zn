/**
 * 
 */
package zn.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import zn.dao.MonListDao;
import zn.dao.MonitorDao;
import zn.dao.UserDao;
import zn.entity.MonList;
import zn.entity.Monitor;
import zn.entity.XUser;
import zn.until.NoteResult;

/**
 * @author hq
 *
 */
@Service("monListService")
@Transactional
public class MonListServiceImpl implements MonListService {

	@Resource // 注入
	private MonitorDao monitorDao;

	@Resource // 注入
	private MonListDao monListDao;
	
	@Resource // 注入
	private UserDao userDao;

	@Override
	public NoteResult selectMonListByList(String superiorListId,Integer userId) {
		NoteResult note = new NoteResult();
		if (superiorListId == null) {
			note.setStatus(1);
			note.setMsg("参数不能为空");
			note.setData("");
		} else {
			
			List<MonList> list = monListDao.selectMonListByList(superiorListId);
			XUser user = userDao.selectXUserById(userId);
			List<Monitor> monList=new ArrayList<>();
			if ("e142fa89-4a1f-48a6-9735-a065fee512dc".equals(user.getUserNumber())) {
			 monList = monitorDao.findMonByMonList(superiorListId);	
			}else{
				 monList = monitorDao.findMonByMonListAndUserId(superiorListId,userId);
			}
			
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("monList", monList);
			map.put("list", list);
			note.setStatus(0);
			note.setMsg("查询成功");
			note.setData(map);
		}
		return note;
	}

	@Override
	public NoteResult selectMonListByListLevel(Integer listLevel) {
		NoteResult note = new NoteResult();
		if (listLevel == null) {
			note.setStatus(1);
			note.setMsg("参数不能为空");
			note.setData("");
		} else {
			List<MonList> list = monListDao.selectMonListByListLevel(listLevel);
			note.setStatus(0);
			note.setMsg("查询成功");
			note.setData(list);
		}
		return note;
	}

	@Override
	public NoteResult selectAllMonList() {
		NoteResult note = new NoteResult();
		List<MonList> list = monListDao.selectAllMonList();
		note.setStatus(0);
		note.setMsg("查询成功");
		note.setData(list);
		return note;
	}

}
