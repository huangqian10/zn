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

import zn.dao.MonSecondListDao;
import zn.dao.MonThirdListDao;
import zn.dao.MonitorDao;
import zn.entity.MonThirdList;
import zn.entity.Monitor;
import zn.until.NoteResult;

/**
 * @author hq
 *
 */
@Service("monThirdListService")
@Transactional
public class MonThirdListServiceImpl implements MonThirdListService {

	@Resource // 注入
	private MonitorDao monitorDao;

	@Resource // 注入
	private MonSecondListDao monSecondListDao;

	@Resource // 注入
	private MonThirdListDao monThirdListDao;

	/**
	 * 查询所有三级列表
	 */
	public NoteResult findAllThirdList() {

		NoteResult note = new NoteResult();
		List<MonThirdList> list = monThirdListDao.findAllThirdlist();
		note.setStatus(0);
		note.setMsg("查询成功");
		note.setData(list);
		return note;

	}

	/**
	 * 查询指定二级列表下的三级列表
	 */
	public NoteResult findThirdlistBySecondId(Integer secondListId) {

		NoteResult note = new NoteResult();

		if (secondListId == null) {
			note.setStatus(1);
			note.setMsg("参数不能为空");
			note.setData("");
		} else {
			List<MonThirdList> list = monThirdListDao.findThirdlistBySecondId(secondListId);
			note.setStatus(0);
			note.setMsg("查询成功");
			note.setData(list);
		}
		return note;

	}

	/**
	 * 查询指定二级列表下的三级列表和设备
	 */
	@Override
	public NoteResult findThirdListAndMon(Integer secondListId) {
		NoteResult note = new NoteResult();

		if (secondListId == null) {
			note.setStatus(1);
			note.setMsg("参数不能为空");
			note.setData("");
		} else {
			List<MonThirdList> list = monThirdListDao.findThirdlistBySecondId(secondListId);
			List<Monitor> monList = monitorDao
					.findMonByList(monSecondListDao.selectListById(secondListId).getSecondListName(), 2);
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("list", list);
			map.put("monList", monList);
			note.setStatus(0);
			note.setMsg("查询成功");
			note.setData(map);
		}
		return note;
	}

	/**
	 * 查询三级列表下的设备
	 */
	public NoteResult findFourthListAndMon(Integer thirdListId) {
		NoteResult note = new NoteResult();

		if (thirdListId == null) {
			note.setStatus(1);
			note.setMsg("参数不能为空");
			note.setData("");
		} else {

			List<Monitor> monList = monitorDao
					.findMonByList(monThirdListDao.selectListById(thirdListId).getThirdListName(), 3);

			note.setStatus(0);
			note.setMsg("查询成功");
			note.setData(monList);
		}
		return note;
	}

	/**
	 * 添加三级列表
	 */
	public NoteResult addThirdList(String thirdListName, Integer secondListId) {

		NoteResult note = new NoteResult();

		if (thirdListName == null || secondListId == null) {
			note.setStatus(1);
			note.setMsg("参数不能为空");
			note.setData("");
		} else if (monSecondListDao.selectIsExistById(secondListId) == 0) {
			note.setStatus(3);
			note.setMsg("上级列表不存在");
			note.setData("");
		} else if (monThirdListDao.selectIsExist(thirdListName) != 0) {
			note.setStatus(2);
			note.setMsg("列表已存在");
			note.setData("");
		} else {
			monThirdListDao.addThirdList(thirdListName, secondListId);
			note.setStatus(0);
			note.setMsg("添加列表成功");
			note.setData("");
		}
		return note;
	}

	/**
	 * 删除三级列表
	 */
	public NoteResult deleteThirdList(Integer thirdListId) {

		NoteResult note = new NoteResult();
		if (thirdListId == null) {
			note.setStatus(1);
			note.setMsg("参数不能为空");
			note.setData("");
		} else {
			MonThirdList mon = monThirdListDao.selectListById(thirdListId);
			if (mon != null) {
				monitorDao.deleteMonPlace(mon.getThirdListName(), 3);
			}
			monThirdListDao.deleteThirdList(thirdListId);
			note.setStatus(0);
			note.setMsg("删除成功");
			note.setData("");
		}
		return note;

	}

	/**
	 * 更改三级列表
	 */
	public NoteResult changeThirdList(String thirdListName, Integer thirdListId) {

		NoteResult note = new NoteResult();

		if (thirdListName == null || thirdListId == null) {
			note.setStatus(1);
			note.setMsg("参数不能为空");
			note.setData("");
		} else if (monThirdListDao.selectIsExist(thirdListName) != 0) {
			note.setStatus(2);
			note.setMsg("列表已存在");
			note.setData("");
		} else {
			MonThirdList mon = monThirdListDao.selectListById(thirdListId);
			if (mon != null) {
				monitorDao.changeMonPlace(mon.getThirdListName(), thirdListName, 3);
			}
			monThirdListDao.changeThirdList(thirdListName, mon.getSecondListId(), thirdListId);
			note.setStatus(0);
			note.setMsg("更改成功");
			note.setData("");
		}
		return note;

	}

}
