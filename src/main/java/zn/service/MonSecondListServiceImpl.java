/**
 * 
 */
package zn.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import zn.dao.MonFirstListDao;
import zn.dao.MonSecondListDao;
import zn.dao.MonitorDao;
import zn.entity.MonSecondList;
import zn.entity.Monitor;
import zn.until.NoteResult;

/**
 * @author hq
 *
 */
@Service("monSecondListService")
@Transactional
public class MonSecondListServiceImpl implements MonSecondListService {
	@Resource // 注入
	private MonitorDao monitorDao;

	@Resource // 注入
	private MonSecondListDao monSecondListDao;

	@Resource
	private MonFirstListDao monFirstListDao;

	/**
	 * 添加二级列表
	 */
	public NoteResult addSecondList(String secondListName, Integer firstListId) {
		NoteResult note = new NoteResult();

		if (secondListName == null || firstListId == null) {
			note.setStatus(1);
			note.setMsg("参数不能为空");
			note.setData("");
		} else if (monFirstListDao.selectIsExistById(firstListId) == 0) {
			note.setStatus(3);
			note.setMsg("上级列表不存在");
			note.setData("");
		} else if (monSecondListDao.selectIsExist(secondListName) != 0) {
			note.setStatus(2);
			note.setMsg("列表已存在");
			note.setData("");
		} else {
			monSecondListDao.addSecondList(secondListName, firstListId);
			note.setStatus(0);
			note.setMsg("添加列表成功");
			note.setData("");
		}
		return note;

	}

	/**
	 * 删除二级列表
	 */
	public NoteResult deleteSecondList(Integer secondListId) {
		NoteResult note = new NoteResult();
		if (secondListId == null) {
			note.setStatus(1);
			note.setMsg("参数不能为空");
			note.setData("");
		} else {
			MonSecondList mon = monSecondListDao.selectListById(secondListId);
			if (mon != null) {
				monitorDao.deleteMonPlace(mon.getSecondListName(), 2);
			}
			monSecondListDao.deleteSecondList(secondListId);
			note.setStatus(0);
			note.setMsg("删除成功");
			note.setData("");
		}
		return note;

	}

	/**
	 * 更改二级列表
	 */
	public NoteResult changeSecondList(String secondListName, Integer secondListId) {
		NoteResult note = new NoteResult();

		if (secondListName == null || secondListId == null) {
			note.setStatus(1);
			note.setMsg("参数不能为空");
			note.setData("");
		} else if (monSecondListDao.selectIsExist(secondListName) != 0) {
			note.setStatus(2);
			note.setMsg("列表已存在");
			note.setData("");
		} else {
			MonSecondList mon = monSecondListDao.selectListById(secondListId);
			if (mon != null) {
				monitorDao.changeMonPlace(mon.getSecondListName(), secondListName, 2);
			}

			monSecondListDao.changeSecondList(secondListName, mon.getFirstListId(), secondListId);
			note.setStatus(0);
			note.setMsg("更改成功");
			note.setData("");
		}
		return note;
	}

	/**
	 * 查询所有二级列表
	 */
	public NoteResult findAllSecondList() {
		NoteResult note = new NoteResult();
		List<MonSecondList> list = monSecondListDao.findAllSecondlist();
		note.setStatus(0);
		note.setMsg("查询成功");
		note.setData(list);
		return note;
	}

	public NoteResult findSecondlistByFirstId(Integer firstListId) {
		NoteResult note = new NoteResult();

		if (firstListId == null) {
			note.setStatus(1);
			note.setMsg("参数不能为空");
			note.setData("");
		} else {
			List<MonSecondList> list = monSecondListDao.findSecondlistByFirstId(firstListId);
			note.setStatus(0);
			note.setMsg("查询成功");
			note.setData(list);
		}
		return note;
	}

	public NoteResult findSecondListAndMon(Integer firstListId) {
		NoteResult note = new NoteResult();

		if (firstListId == null) {
			note.setStatus(1);
			note.setMsg("参数不能为空");
			note.setData("");
		} else {
			List<MonSecondList> list = monSecondListDao.findSecondlistByFirstId(firstListId);

			List<Monitor> monList = monitorDao
					.findMonByList(monFirstListDao.selectListById(firstListId).getFirstListName(), 1);
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("monList", monList);
			map.put("list", list);
			note.setStatus(0);
			note.setMsg("查询成功");
			note.setData(map);
		}
		return note;
	}

}
