/**
 * 
 */
package zn.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import zn.entity.Alarms;
import zn.entity.MonAlarms;

/**
 * @author hq
 *
 */
public interface MonAlarmsDao {
	public  List<Alarms> selectAllMonAlarms(int userId);
	public List<Alarms> selectUserAllMonAlarms(int userId);
	public   int selectAlarmsDateIsExist(int monId);
	public    int  addMonAlarms(MonAlarms monAlarms);
	public int changeMonAlarms(MonAlarms monAlarms);
	public int deleteMonAlarmsById(int alarmsId);
	public int deleteMonAlarmsByMonId(int monId);

	public  List<Alarms>	selectMonAlarmsById(int monId);
	public int changeMonAlarmsStatus(@Param("alarmsId")int alarmsId,@Param("userId")int userId);
	
	public int userAddAlarm(List<Map<String , Object>> list);
}
