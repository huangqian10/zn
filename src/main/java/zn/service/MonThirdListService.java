/**
 * 
 */
package zn.service;

import zn.until.NoteResult;

/**
 * @author hq
 *
 */
public interface MonThirdListService {
	public NoteResult findAllThirdList();

	public NoteResult addThirdList(String thirdListName, Integer secondListId);

	public NoteResult deleteThirdList(Integer thirdListId);

	public NoteResult changeThirdList(String thirdListName, Integer thirdListId);

	public NoteResult findThirdlistBySecondId(Integer secondListId);

	public NoteResult findThirdListAndMon(Integer secondListId);

	public NoteResult findFourthListAndMon(Integer thirdListId);
}
