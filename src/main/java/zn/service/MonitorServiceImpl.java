
package zn.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONException;

import zn.dao.MonDateDao;
import zn.dao.MonitorDao;
import zn.dao.UserDao;
import zn.entity.Monitor;
import zn.entity.User;
import zn.entity.XUser;
import zn.entity.XmlMonSon;
import zn.entity.XmlMonitor;
import zn.until.NoteResult;
import zn.until.WebService;

/**
 * @author hq
 *
 */
@Service("monitorService")
@Transactional
public class MonitorServiceImpl implements MonitorService {

	@Resource // 注入
	private MonitorDao monitorDao;

	@Resource // 注入
	private MonDateDao monDateDao;

	@Resource // 注入
	private UserDao userDao;

	/**
	 * 添加设备
	 */
	public NoteResult addMon(String monStr, int userId) {
		NoteResult note = new NoteResult();

		try {
			Monitor mon = JSON.parseObject(monStr, Monitor.class);
			if (mon == null) {
				note.setStatus(1);
				note.setMsg("参数为空");
				note.setData("");

			} else if (mon.getMonNumber() == null) {
				note.setStatus(2);
				note.setMsg("设备编号为空");
				note.setData("");

			} else if (monitorDao.monIsExist(mon.getMonNumber()) != 0) {
				note.setStatus(3);
				note.setMsg("设备编号已存在");
				note.setData("");

			} else {

				monitorDao.addMon(mon);
				monDateDao.addMonDateId(mon.getMonId());
				Map<String, Object> param = new HashMap<String, Object>();
				param.put("monId", mon.getMonId());
				List<Integer> userList = new ArrayList<Integer>();
				userList.add(userId);
				param.put("userList", userList);
				monitorDao.monAddUser(param);
				note.setStatus(0);
				note.setMsg("添加设备成功");
				note.setData("");
			}

		} catch (JSONException e) {
			note.setStatus(4);
			note.setMsg("参数格式错误");
			note.setData("");
		} catch (ClassCastException c) {
			note.setStatus(4);
			note.setMsg("参数格式错误");
			note.setData("");
		}
		return note;
	}

	/**
	 * 查询所有设备信息
	 */
	public NoteResult findAllMon() {
		NoteResult note = new NoteResult();
		List<Monitor> list = monitorDao.findAllMon();
		note.setStatus(0);
		note.setMsg("查询成功");
		note.setData(list);

		return note;
	}

	/**
	 * 删除设备成功
	 */
	public NoteResult deleteMon(Integer monId) {
		NoteResult note = new NoteResult();
		if (monId == null) {
			note.setStatus(1);
			note.setMsg("参数为空");
			note.setData("");
		} else {

			monitorDao.deleteMon(monId);
			note.setStatus(0);
			note.setMsg("删除成功");
			note.setData("");
		}
		return note;
	}

	/**
	 * 更改设备信息
	 */
	public NoteResult changeMon(String monStr) {
		NoteResult note = new NoteResult();
		try {
			Monitor mon = JSON.parseObject(monStr, Monitor.class);
			if (mon == null) {
				note.setStatus(1);
				note.setMsg("参数为空");
				note.setData("");
			} else if (mon.getMonId() == 0) {
				note.setStatus(2);
				note.setMsg("设备id为空");
				note.setData("");

			} else if (mon.getMonNumber() != null && monitorDao.monIsExist(mon.getMonNumber()) != 0
					&& !monitorDao.findMonById(mon.getMonId()).getMonNumber().equals(mon.getMonNumber())) {
				note.setStatus(3);
				note.setMsg("设备编号已存在");
				note.setData("");

			} else {
				monitorDao.changeMon(mon);
				note.setStatus(0);
				note.setMsg("更改设备信息成功");
				note.setData("");
			}
		} catch (JSONException e) {
			note.setStatus(4);
			note.setMsg("参数格式错误");
			note.setData("");
		} catch (ClassCastException c) {
			note.setStatus(4);
			note.setMsg("参数格式错误");
			note.setData("");
		}
		return note;
	}

	/**
	 * 给指定设备添加用户列表
	 */
	public NoteResult monAddUser(String jsonStr) {
		NoteResult note = new NoteResult();
		if (jsonStr == null || "".equals(jsonStr)) {
			note.setStatus(5);
			note.setMsg("参数为空");
			note.setData("");
			return note;
		}
		try {
			Map<String, Object> map = JSON.parseObject(jsonStr);
			if (map == null || map.isEmpty()) {
				note.setStatus(5);
				note.setMsg("参数为空");
				note.setData("");
				return note;
			} else if (map.get("monId") == null) {
				note.setStatus(2);
				note.setMsg("设备id为空");
				note.setData("");

			} else if (map.get("userList") == null || "".equals(map.get("userList"))
					|| "[]".equals(map.get("userList"))) {
				monitorDao.monDelteUser((Integer) map.get("monId"));
				note.setStatus(0);
				note.setMsg("清空设备用户列表成功");
				note.setData("");

			} else {
				monitorDao.monDelteUser((Integer) map.get("monId"));
				monitorDao.monAddUser(map);
				note.setStatus(0);
				note.setMsg("操作成功");
				note.setData("");
			}
		} catch (JSONException e) {
			note.setStatus(4);
			note.setMsg("参数格式错误");
			note.setData("");
		} catch (ClassCastException c) {
			note.setStatus(4);
			note.setMsg("参数格式错误");
			note.setData("");
		}
		return note;

	}

	/**
	 * 查询指定用户下的所有设备
	 */
	public NoteResult seleteMonByUserId(Integer userId) {

		NoteResult note = new NoteResult();

		if (userId == null) {
			note.setStatus(1);
			note.setMsg("参数为空");
			note.setData("");

		} else {
			XUser user = userDao.selectXUserById(userId);
			String xmlListStr = new WebService().DeviceInfoGetByUserIdForJson(user.getTelephone());
			List<XmlMonitor> xmlList = (List<XmlMonitor>) JSONArray.parseArray(xmlListStr, XmlMonitor.class);
			List<String> wen = new ArrayList<String>();
				for (XmlMonitor x : xmlList) {
					wen.add(x.getSBBH());
				}
			
			if ("e142fa89-4a1f-48a6-9735-a065fee512dc".equals(user.getUserNumber())) {
				List<Monitor> list = monitorDao.findAllMon();
				note.setStatus(0);
				note.setMsg("查询成功");
				note.setData(list);
				
				
			} else {
				List<Monitor> list=new ArrayList<Monitor>();
				if(wen==null||wen.isEmpty()){
					note.setStatus(0);
					note.setMsg("操作成功");
					note.setData(list);
				}else{
					list = monitorDao.selectMonByMonNumber(wen);
					note.setStatus(0);
					note.setMsg("操作成功");
					note.setData(list);
				}
			
			}
		}
		return note;
	}

	/**
	 * 查询指定状态下的设备信息
	 */
	public NoteResult findMonByState(Integer monState,Integer  userId) {
		NoteResult note = new NoteResult();
		if (monState == null) {
			note.setStatus(1);
			note.setMsg("参数为空");
			note.setData("");

		} else {
			List<Monitor> list;
			XUser user = userDao.selectXUserById(userId);
			if ("e142fa89-4a1f-48a6-9735-a065fee512dc".equals(user.getUserNumber())) {
				 list = monitorDao.findMonByState(monState);
				}else{
				 list = monitorDao.findMonByStateAndUserId(monState,userId);
				}
		
			note.setStatus(0);
			note.setMsg("操作成功");
			note.setData(list);
		}
		return note;

	}

	/**
	 * 查询指定状态下的设备数量
	 */
	public NoteResult findMonNumByState(Integer userId) {
		
		NoteResult note = new NoteResult();
		int num1=0;
		int num2=0;
		int num3=0;
		int num4=0;
		XUser user = userDao.selectXUserById(userId);
		if ("e142fa89-4a1f-48a6-9735-a065fee512dc".equals(user.getUserNumber())) {
			 num1 = monitorDao.findMonNumByState(1);
			 num2 = monitorDao.findMonNumByState(2);
			 num3 = monitorDao.findMonNumByState(3);
			 num4 = monitorDao.findMonNumByState(4);
		}else{
		 num1 = monitorDao.findMonNumByStateAndUserId(1,userId);
		 num2 = monitorDao.findMonNumByStateAndUserId(2,userId);
		 num3 = monitorDao.findMonNumByStateAndUserId(3,userId);
		 num4 = monitorDao.findMonNumByStateAndUserId(4,userId);
		}
		Map<String, Object> map1 = new HashMap<String, Object>();
		map1.put("num", num1);
		map1.put("state", 1);
		map1.put("stateName", "正常设备");
		Map<String, Object> map2 = new HashMap<String, Object>();
		map2.put("num", num2);
		map2.put("state", 2);
		map2.put("stateName", "离线设备");
		Map<String, Object> map3 = new HashMap<String, Object>();
		map3.put("num", num3);
		map3.put("state", 3);
		map3.put("stateName", "故障设备");
		Map<String, Object> map4 = new HashMap<String, Object>();
		map4.put("num", num4);
		map4.put("state", 4);
		map4.put("stateName", "报警设备");
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		list.add(map1);
		list.add(map2);
		list.add(map3);
		list.add(map4);

		note.setStatus(0);
		note.setMsg("操作成功");
		note.setData(list);
		return note;
	}

	public NoteResult seleteUserListByMonId(Integer userId) {
		NoteResult note = new NoteResult();
		if (userId == null) {
			note.setStatus(1);
			note.setMsg("参数不能为空");
			note.setData("");

		} else {
			List<Map<String, Object>> list = monitorDao.SeleteMonListByUserId(userId);
			note.setStatus(0);
			note.setMsg("操作成功");
			note.setData(list);
		}
		return note;
	}

}
