/**
 * 
 */
package zn.service;

import zn.until.NoteResult;

/**
 * @author hq
 *
 */
public interface MonListService {
	public NoteResult selectMonListByList(String superiorListId,Integer userId);
	public NoteResult selectMonListByListLevel(Integer listLevel);
	public NoteResult selectAllMonList();
	

}
