package zn.controller.mondate;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import zn.service.RoomStatusService;
import zn.until.NoteResult;

/**
 * @author hl
 * 
 */
@Controller
@RequestMapping("/roomStatus")
public class RoomStatusController {
	@Resource
	private RoomStatusService roomStatusService;
	
    @RequestMapping(value="/getByMonId")
    @ResponseBody
    public NoteResult getAllByMonId(Integer monId){
    	return roomStatusService.getAllRoomStatus(monId);
    }
    
    @RequestMapping(value="/changeAirSwitch")
    @ResponseBody
    public NoteResult changeAirSwitchStatus(Integer monId,String nodeNum,Integer code){
    	return roomStatusService.changeAirSwitchStatus(monId,nodeNum.trim(),code);
    }
    
    @RequestMapping("/changeSmokeState")
    @ResponseBody
    public NoteResult changeNodeSmokeState(Integer monId,String nodeNum){
    	return roomStatusService.changeNodeSmokeState(monId,nodeNum);
    }
}
