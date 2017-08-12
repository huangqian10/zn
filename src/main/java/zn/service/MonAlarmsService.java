/**
 * 
 */
package zn.service;



import zn.until.NoteResult;

/**
 * @author hq
 *
 */

public interface MonAlarmsService {
	
	public NoteResult selectAllMonAlarms(Integer userId);
	public NoteResult deleteMonAlarmsByMonId(Integer monId);
	public NoteResult deleteMonAlarmsById(Integer alarmsId);
	public NoteResult selectMonAlarmsById(Integer monId);
	public NoteResult changeMonAlarmsStatus(Integer alarmsId,Integer userId);
	

}
