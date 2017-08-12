/**
 * 
 */
package zn.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.axis2.deployment.resolver.WarBasedWSDLLocator;
import org.springframework.context.annotation.Lazy;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.ctc.wstx.dtd.DFAState;

import zn.dao.MonDateDao;
import zn.dao.MonListDao;
import zn.dao.MonitorDao;
import zn.dao.UserDao;
import zn.entity.MonDate;
import zn.entity.MonList;
import zn.entity.MonSon;
import zn.entity.Monitor;
import zn.entity.User;
import zn.entity.XUser;
import zn.entity.XmlMonList;
import zn.entity.XmlMonSon;
import zn.entity.XmlMonitor;
import zn.entity.XmlUser;
import zn.until.NoteResult;
import zn.until.WebService;

/**
 * @author hq
 *
 */

@Service("getDateService")
@Transactional
public class GetDateServiceImpl implements GetDateService {

	@Resource // 注入
	private MonitorDao monitorDao;

	@Resource // 注入
	private MonDateDao monDateDao;

	@Resource // 注入
	private MonListDao monListdDao;

	@Resource // 注入
	private UserDao userDao;

	/**
	 * 同步数据 @Title: getDate @Description: TODO @return @throws
	 */
	public synchronized NoteResult getDate() {
		long time = System.currentTimeMillis();
		NoteResult note = new NoteResult();
		DeviceInfoGetForJson();
		RegionInfoGetForJson();
		ITPCDeviceChannelInfoGetByIDForJson();
		UserInfoGetForJson();
		DeviceInfoGetByUserIdForJson();
		long time2 = System.currentTimeMillis();
		System.out.println(time2 - time);
		note.setStatus(0);
		note.setMsg("同步成功");
		note.setData("");

		return note;
	}

	/**
	 * 获取指定设备下所有通道信息 @Title: ITPCDeviceChannelInfoGetByIDForJson @Description:
	 * TODO @throws
	 */
	public void ITPCDeviceChannelInfoGetByIDForJson() {
		List<String> monNumberList = monitorDao.selectAllmonNumber();

		if (monNumberList.isEmpty()) {
			return;
		}

		for (String monNumber : monNumberList) {

			String xmlListStr = new WebService().ITPCDeviceChannelInfoGetByIDForJson(monNumber);

			if ("".equals(xmlListStr) || xmlListStr == null) {
				break;
			}
			List<XmlMonSon> xmlMonList = (List<XmlMonSon>) JSONArray.parseArray(xmlListStr, XmlMonSon.class);

			if (!xmlMonList.isEmpty()) {
				MonDate monDate = new MonDate();
				for (XmlMonSon monSon : xmlMonList) {

					monDate.setMonId(monitorDao.selectMonIdByNum(monNumber).getMonId());

					if (monSon.getTDHM() == 1) {
						monDate.setName1(monSon.getTDMC());
					} else if (monSon.getTDHM() == 2) {
						monDate.setName2(monSon.getTDMC());
					} else if (monSon.getTDHM() == 3) {
						monDate.setName3(monSon.getTDMC());
					} else if (monSon.getTDHM() == 4) {
						monDate.setName4(monSon.getTDMC());
					} else if (monSon.getTDHM() == 5) {
						monDate.setName5(monSon.getTDMC());
					} else if (monSon.getTDHM() == 6) {
						monDate.setName6(monSon.getTDMC());
					} else if (monSon.getTDHM() == 7) {
						monDate.setName7(monSon.getTDMC());
					} else if (monSon.getTDHM() == 8) {
						monDate.setName8(monSon.getTDMC());
					} else if (monSon.getTDHM() == 9) {
						monDate.setName9(monSon.getTDMC());
					} else if (monSon.getTDHM() == 10) {
						monDate.setName10(monSon.getTDMC());
					} else if (monSon.getTDHM() == 11) {
						monDate.setName11(monSon.getTDMC());
					} else if (monSon.getTDHM() == 12) {
						monDate.setName12(monSon.getTDMC());
					}

					monDateDao.changeMonDate(monDate);
				}
			}
		}
	}

	/**
	 * 获取所有列表信息 @Title: RegionInfoGetForJson @Description: TODO @throws
	 */
	public void RegionInfoGetForJson() {
		String xmlListStr = new WebService().RegionInfoGetForJson();
		if ("".equals(xmlListStr) || xmlListStr == null) {
			return;
		}
		List<XmlMonList> xmlMonList = (List<XmlMonList>) JSONArray.parseArray(xmlListStr, XmlMonList.class);
		List<MonList> monList = new ArrayList<MonList>();

		monListdDao.deleteAllMonList();
		if (!xmlMonList.isEmpty()) {
			for (XmlMonList x : xmlMonList) {
				MonList mon = new MonList();
				mon.setListLevel(x.getQYPX());
				mon.setMonListId(x.getQYBH());
				mon.setMonListName(x.getQYMC());
				mon.setSuperiorListId(x.getSJQY());
				monList.add(mon);
			}
			monListdDao.addAllMonList(monList);

		}
	}

	/**
	 * 获取所有设备信息 @Title: DeviceInfoGetForJson @Description: TODO @throws
	 */
	public void DeviceInfoGetForJson() {
		String xmlListStr = new WebService().DeviceInfoGetForJson();
		if ("".equals(xmlListStr) || xmlListStr == null) {
			return;
		}

		List<XmlMonitor> xmlList = (List<XmlMonitor>) JSONArray.parseArray(xmlListStr, XmlMonitor.class);
		List<String> wen = new ArrayList<String>();
		for (XmlMonitor x : xmlList) {
			wen.add(x.getSBBH());
		}
		List<String> wen2 = new ArrayList<String>(wen);
		List<String> wen3 = new ArrayList<String>(wen);

		List<String> wen4 = monitorDao.selectAllmonNumber();
		List<Monitor> list3 = new ArrayList<Monitor>();
		wen.retainAll(wen4);
		wen2.removeAll(wen4);
		wen4.removeAll(wen3);

		for (XmlMonitor x : xmlList) {
			if (wen.contains(x.getSBBH())) {
				Monitor monitor = new Monitor();
				monitor.setMonNumber(x.getSBBH());
				monitor.setMonName(x.getSBMC());
				monitor.setMonIP(x.getSBIP());
				monitor.setMonPlace(x.getSBQY());
				monitor.setMonType(x.getSBLX());
				monitor.setMonInstall(x.getAZWZ());
				monitor.setMonPlaceName(x.getSBQYMC());
				monitor.setMonRoute(x.getHLSL());
				if (x.getSBZT() != null) {
					monitor.setMonState(getMonState(x.getSBZT()));
				}
				monitorDao.changeMonByMonNumber(monitor);
			}
			if (wen2.contains(x.getSBBH())) {
				Monitor monitor = new Monitor();
				monitor.setMonNumber(x.getSBBH());
				monitor.setMonName(x.getSBMC());
				monitor.setMonIP(x.getSBIP());
				monitor.setMonPlace(x.getSBQY());
				monitor.setMonType(x.getSBLX());
				monitor.setMonInstall(x.getAZWZ());
				monitor.setMonPlaceName(x.getSBQYMC());
				monitor.setMonRoute(x.getHLSL());
				if (x.getSBZT() != null) {

					monitor.setMonState(getMonState(x.getSBZT()));

				}
				if (x.getSBLX().contains("1") || x.getSBLX().contains("6") || x.getSBLX().contains("12")) {
					list3.add(monitor);
				}
			}
		}
		if (!list3.isEmpty()) {
			monitorDao.addMoreMon(list3);
			List<Integer> list4 = monitorDao.selectMonIdByMonNumber(wen2);
			if (!list4.isEmpty()) {
				monDateDao.addMonDateIdList(list4);
			}
		}
		if (!wen4.isEmpty()) {
			monitorDao.deleteMonByMonNumber(wen4);
		}
	}

	/**
	 * 获取所有用户信息 @Title: UserInfoGetForJson @Description: TODO @throws
	 */
	public void UserInfoGetForJson() {
		String xmlListStr = new WebService().UserInfoGetForJson();
		if ("".equals(xmlListStr) || xmlListStr == null) {
			return;
		}

		List<XmlUser> xmlList = (List<XmlUser>) JSONArray.parseArray(xmlListStr, XmlUser.class);
		List<String> wen = new ArrayList<String>();
		for (XmlUser x : xmlList) {
			wen.add(x.getYHBH());
		}
		List<String> wen2 = new ArrayList<String>(wen);
		List<String> wen3 = new ArrayList<String>(wen);

		List<String> wen4 = userDao.seleteAllUserNumber();
		List<XUser> list3 = new ArrayList<XUser>();
		wen.retainAll(wen4);
		wen2.removeAll(wen4);
		wen4.removeAll(wen3);

		for (XmlUser xmlUser : xmlList) {
			if (wen.contains(xmlUser.getYHBH())) {
				XUser user = new XUser();
				user.setLimitsId(xmlUser.getYHJS());
				user.setLimitsName(xmlUser.getYHJSMC());
				user.setOrgId(xmlUser.getBMBH());
				user.setOrgName(xmlUser.getBMMC());
				user.setPassword(xmlUser.getYHMM());
				user.setUserNumber(xmlUser.getYHBH());
				user.setTelephone(xmlUser.getYHBM());
				user.setUserName(xmlUser.getYHMC());

				userDao.changeUser(user);
			}
			if (wen2.contains(xmlUser.getYHBH())) {
				XUser user = new XUser();
				user.setLimitsId(xmlUser.getYHJS());
				user.setLimitsName(xmlUser.getYHJSMC());
				user.setOrgId(xmlUser.getBMBH());
				user.setOrgName(xmlUser.getBMMC());
				user.setPassword(xmlUser.getYHMM());
				user.setUserNumber(xmlUser.getYHBH());
				user.setTelephone(xmlUser.getYHBM());
				user.setUserName(xmlUser.getYHMC());

				list3.add(user);

			}
		}
		if (!list3.isEmpty()) {
			userDao.addMoreUser(list3);

		}
		if (!wen4.isEmpty()) {
			userDao.deleteMoreUser(wen4);
		}
	}

	/**
	 * 获取人员和设备的关系 @Title: DeviceInfoGetByUserIdForJson @Description: TODO @throws
	 */
	public void DeviceInfoGetByUserIdForJson() {
		List<XUser> userlist = userDao.selectAllUser();
		userDao.deleteUserMonitor();
		for (XUser user : userlist) {
			if (!user.getUserNumber().equals("e142fa89-4a1f-48a6-9735-a065fee512dc")) {
				String xmlListStr = new WebService().DeviceInfoGetByUserIdForJson(user.getTelephone());
				List<XmlMonitor> xmlList = (List<XmlMonitor>) JSONArray.parseArray(xmlListStr, XmlMonitor.class);
				List<String> wen = new ArrayList<String>();
				for (XmlMonitor x : xmlList) {
					wen.add(x.getSBBH());
				}
				if (!wen.isEmpty()) {
					List<Integer> monIdList = monitorDao.selectMonIdByMonNumber(wen);
					if (!monIdList.isEmpty()) {
						Map<String, Object> map = new HashMap<String, Object>();
						map.put("userId", user.getUserId());
						map.put("monList", monIdList);

						userDao.userAddMon(map);
					}
				}
			}
		}
	}

	public int getMonState(String monState) {
		if ("在线".equals(monState)) {
			return 1;
		} else if ("离线".equals(monState)) {
			return 2;
		} else if ("故障".equals(monState)) {
			return 3;
		} else if ("报警".equals(monState)) {
			return 4;
		}
		return 0;
	}
}
