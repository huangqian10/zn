/**
 * 
 */
package zn.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import zn.entity.Monitor;

/**
 * @author hq
 *
 */
public interface MonitorDao {
	      public List<Monitor> findAllMon();
	      public int  monIsExist(String monNumber);
	      public int  addMon(Monitor monitor);
	      public int  deleteMon(int monId);
	      public int changeMon(Monitor monitor);
	      public int deleteMonPlace(@Param("monPlace")String monPlace,@Param("monPlaceLevel")int monPlaceLevel);
	      public int  changeMonPlace(@Param("monOldPlace")String monOldPlace,@Param("monNewPlace")String monNewPlace,@Param("monPlaceLevel")int monPlaceLevel);
	      public    Monitor       findMonById(int monId);
	      public int monAddUser(Map<String,Object> param);
	      public int monDelteUser(int monId);
	      public Monitor selectMonByNum(String monNumber);
	      public Monitor selectMonIdByNum(String monNumber);
	      public List<Monitor> seleteMonByUserId(int userId);
	  	  public List<Monitor> findMonByState(int monState);
	  	public List<Monitor> findMonByStateAndUserId(@Param("monState")int monState,@Param("userId")int userId);
	  	  public List<Monitor> findMonByList(@Param("monPlace")String monPlace,@Param("monPlaceLevel")int monPlaceLevel);
	  	  public int findMonNumByStateAndUserId(@Param("monState")int monState,@Param("userId")int userId);
	  	  public int findMonNumByState(int monState);
	  	  
		  public   List<Map<String,Object>>  SeleteMonListByUserId(int userId);	  
		  
		  public int  addMoreMon(List<Monitor> list);
		  
		  public List<String> selectAllmonNumber();
		  public  int deleteMonByMonNumber(List<String> list);
		  public int changeMonByMonNumber(Monitor monitor);
		  public int addMonDateIdList(List<Integer> list);
		  public List<Integer> selectMonIdByMonNumber(List<String> list);
		  
		  public List<Monitor> findMonByMonList(String monPlace);
		  public List<Monitor> findMonByMonListAndUserId(@Param("monPlace")String monPlace,@Param("userId")int userId);
		  public List<Monitor> selectMonByMonNumber(List<String> list);
 }

