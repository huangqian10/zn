/**
 * 
 */
package zn.service;



import zn.until.NoteResult;

/**
 * @author hq
 *
 */
public interface MonSecondListService {
	public  NoteResult   findAllSecondList();
	public  NoteResult   addSecondList(String secondListName,Integer firstListId);
	public  NoteResult   deleteSecondList(Integer secondListId);
	public  NoteResult   changeSecondList(String secondListName,Integer secondListId);
	public NoteResult findSecondlistByFirstId(Integer firstListId);
	public NoteResult findSecondListAndMon(Integer firstListId);     
	
}
