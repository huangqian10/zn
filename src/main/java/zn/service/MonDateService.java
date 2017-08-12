/**
 * 
 */
package zn.service;

import zn.until.NoteResult;

/**
 * @author hq
 *
 */
public interface MonDateService {
	public NoteResult  selectMonDateById(Integer monId);
	public NoteResult  setMonSwitch(String  switchState,Integer way,String password,Integer monId,Integer userId);
	public NoteResult setMonDateName(String monDateName,Integer route,Integer monId);
}
