/**
 * 
 */
package zn.service;

import org.apache.ibatis.annotations.Param;

import zn.until.NoteResult;

/**
 * @author hq
 *
 */
public interface LimitsService {
	  public NoteResult userAddLimits(Integer limitsId,Integer userId);
	  public NoteResult selectUserByLimitsId(Integer limitsId);
	  public NoteResult selectLimitsList();
}
