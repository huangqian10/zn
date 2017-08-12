/**
 * 
 */
package zn.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import zn.entity.Limits;
import zn.entity.User;

/**
 * @author hq
 *
 */
public interface LimitsDao {
	public int userAddLimits(@Param("limitsId")int limitsId,@Param("userId")int userId);
	public int userDeleteLimits(int userId);
	public List<User> selectUserByLimitsId(int limitsId);
	public List<Limits> selectLimitsList();
}
