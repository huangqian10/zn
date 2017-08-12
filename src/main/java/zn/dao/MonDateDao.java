/**
 * 
 */
package zn.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import zn.entity.MonDate;

/**
 * @author hq
 *
 */
public interface MonDateDao {
	public MonDate selectMonDateById(int monId);
	public  int  addMonDate(MonDate mondate);
	public int changeMonDate(MonDate mondate);
	public int selectMonDateIsExist(int monId);
	public int addMonDateId(int monId);
	public int changeMonSwitch(@Param("monSwitch")String monSwitch,@Param("monId")Integer monId );
	
	public Map<String,Object> selectSwitchAndNumber(int monId);
	
	
	public int addMonDateIdList(List<Integer> list);

}
