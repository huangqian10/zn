package zn.service;

import zn.until.NoteResult;

/**
 * @author hl
 *
 */
public interface RoomStatusService {
	/**
	 * 获取设备相关信息，如报警状态
	 * 
	 * @param monId
	 *            设备Id
	 * @return
	 */
	public NoteResult getAllRoomStatus(Integer monId);

	/**
	 * 控制设备某一节点的开启和关闭
	 * 
	 * @param monId
	 * @param nodeNum
	 * @param code
	 *            0:关闭，1:开启
	 * @return
	 */
	public NoteResult changeAirSwitchStatus(Integer monId, String nodeNum, Integer code);

	/**
	 * 获取设备某一节点烟雾状态
	 * @param monId
	 * @param nodeNum
	 * @return
	 */
	public NoteResult changeNodeSmokeState(Integer monId, String nodeNum);

}
