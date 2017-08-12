/**
 * 
 */
package zn.dao;

import org.apache.ibatis.annotations.Param;

/**
 * @author hq
 *
 */
public interface LoginDao {
	public int addCount(int userId);
	public int autoAddCount(@Param("userId")int userId);
	public int deleteOutModedCount(@Param("agoTime")String agoTime);
	public Integer countIsExist(int userId);
	public int deleteCountByuserId(int userId);
}
