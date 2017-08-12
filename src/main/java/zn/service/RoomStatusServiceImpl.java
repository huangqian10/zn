package zn.service;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import zn.dao.MonitorDao;
import zn.dao.RoomStatusDao;
import zn.entity.Monitor;
import zn.entity.RoomStatus;
import zn.until.NoteResult;
import zn.until.WebService;

/**
 * @author hl
 *
 */
@Service("roomStatusSerice")
public class RoomStatusServiceImpl implements RoomStatusService{
    
	@Resource
	private RoomStatusDao roomStatusDao;
	@Resource 
	private MonitorDao monitorDao;
	@Override
	public NoteResult getAllRoomStatus(Integer monId) {
		List<RoomStatus> roomStatusList=new ArrayList<>();
		NoteResult result=new NoteResult();
		
		result.setStatus(1);
		if(monId==null){
			result.setMsg("参数不能为空");
		}
		//设备信息
		Monitor mon=monitorDao.findMonById(monId);
		if(mon==null){
			result.setMsg("参数错误");
		}
		//发送webService请求获取相关信息，并将结果转化为json格式字符串
		String xmlListStr="";
		//通过webservice获取json格式的数据
		xmlListStr=new WebService().RoomStatusInfoGetForJson(mon.getMonNumber());
		//未获取到则从数据库取
		if(xmlListStr==null||"".equals(xmlListStr)||"[]".equals(xmlListStr)){
			   roomStatusList=roomStatusDao.getByMonId(monId);
		}else{
			   //json转换成对象
			   roomStatusList=jsonToRoomStatusList(xmlListStr);
			   //更新数据
			   if(roomStatusList!=null){
			       updateRoomStatus(monId,roomStatusList);
			   }   
		}
        result.setData(roomStatusList);
        result.setMsg("查询成功");
		return result;
	}

	//json转对象数组
	public List<RoomStatus> jsonToRoomStatusList(String jsonStr) {
		List<RoomStatus> result=null;
		JSONArray jsonArray=JSONArray.parseArray(jsonStr);
		JSONObject jsonObject;
		if(jsonArray.size()>0){
			result=new ArrayList<>();
			for(int i=0;i<jsonArray.size();i++){
				jsonObject=jsonArray.getJSONObject(i);
				if(jsonObject.containsKey("SBBH")){
					RoomStatus roomStatus=new RoomStatus();
					roomStatus.setMonNum(jsonObject.getString("SBBH"));
					roomStatus.setTDMC(jsonObject.getString("TDMC"));
					roomStatus.setTDHM(jsonObject.getString("TDHM"));
					roomStatus.setDY(jsonObject.getString("DY"));
					roomStatus.setWD(jsonObject.getString("WD"));
					roomStatus.setDL(jsonObject.getString("DL"));
					roomStatus.setLD(jsonObject.getString("LD"));
					roomStatus.setTStatus(jsonObject.getString("WDZT"));
					roomStatus.setSmogStatus(jsonObject.getString("YWZT"));
					roomStatus.setAStatus(jsonObject.getString("DLZT"));
					roomStatus.setAirSwitchStatus(jsonObject.getString("TDZT"));
					result.add(roomStatus);
				}
			}
		}
		return result;
	}

	//更新数据库记录
	@Transactional
	public void updateRoomStatus(Integer monId,List<RoomStatus> roomStatusList) {
		List<RoomStatus>  oldRoomStatusList=roomStatusDao.getByMonId(monId);
		List<RoomStatus>  addList=new ArrayList<>();
		List<Integer>  deleteList=new ArrayList<>();
		RoomStatus oldRoomStatus = null;
		for(RoomStatus roomStatus:roomStatusList){
			roomStatus.setMonId(monId);
			if(roomStatus!=null){
				//通过设备编号和设备节点判断数据库是否有相应记录
				List<RoomStatus>  statusList=roomStatusDao.getByMonIdAndNode(monId,roomStatus.getTDHM());
				if(statusList.size()>0){
					oldRoomStatus=statusList.get(0);
				}
				if(oldRoomStatus!=null){
					//存在则更新记录（先删除再添加）
					deleteList.add(oldRoomStatus.getId());
				}
				addList.add(roomStatus);
			}
		}
		int id;
		for(RoomStatus roomStatus:oldRoomStatusList){
			id=roomStatus.getId();
			//判断是否存在于此次更新列表,不存在则删除
			if(!deleteList.contains(id)){
				deleteList.add(id);
			}
		}
		if(deleteList.size()>0)
		   roomStatusDao.deleteAll(deleteList);
		if(addList.size()>0)
		   roomStatusDao.saveAll(addList);
	}
	

	@Override
	public NoteResult changeAirSwitchStatus(Integer monId, String nodeNum, Integer code) {
		NoteResult result=new NoteResult();
		if(monId==null||nodeNum==null||code==null){
			result.setStatus(1);
			result.setMsg("参数不能为空");
			return result;
		}
		Monitor mon=monitorDao.findMonById(monId);
		if(mon==null){
			result.setStatus(1);
			result.setMsg("参数错误");
			return result;
		}
		String xmlStr=new WebService().ChangeRoomAirSwitchStatus(mon.getMonNumber(),nodeNum, code);
		//String xmlStr="true";
		if(xmlStr!=null && "true".equals(xmlStr)){
			if(code==0)
			   roomStatusDao.updateAirSwitchStatus(monId,nodeNum,"关");
			else if(code==1)
			   roomStatusDao.updateAirSwitchStatus(monId,nodeNum,"开");
			result.setStatus(0);
			result.setMsg("控制开关成功");
		}
		else{
			result.setStatus(1);
			result.setMsg("控制开关失败");
		}
		return result;
	}

	@Override
	public NoteResult changeNodeSmokeState(Integer monId, String nodeNum) {
		NoteResult result=new NoteResult();
		if(monId==null||nodeNum==null||"".equals(nodeNum)){
			result.setStatus(1);
			result.setMsg("参数不能为空");
			return result;
		}
		Monitor mon=monitorDao.findMonById(monId);
		if(mon==null){
			result.setStatus(1);
			result.setMsg("参数错误");
			return result;
		}
		String xmlStr=new WebService().ChangeNodeSmokeState(mon.getMonNumber(),nodeNum);
		//String xmlStr="true";
		if(xmlStr!=null){
			result.setStatus(0);
		    result.setMsg("执行成功");
			result.setData(xmlStr);
		}
		else{
			result.setStatus(1);
			result.setMsg("执行失败");
		}
		return result;
	}
	
	/*public static void main(String args[]) {
		String jsonStr="[{'SBBH':'946954ch-sasd-addff','TDMC':'开关1','TDHM':1,'DY':'0','WD':'20','DL':'0','LD':'0','WDZT':'正常','YWZT':'正常','DLZT':'正常','TDZT':'开'}]";
		List<RoomStatus> list=new RoomStatusServiceImpl().jsonToRoomStatusList(jsonStr);
		list.forEach(System.out::println);
	}*/
}
