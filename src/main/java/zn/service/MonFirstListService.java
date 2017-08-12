/**
 * 
 */
package zn.service;

import zn.until.NoteResult;

/**
 * @author hq
 *
 */
public interface MonFirstListService {
		public NoteResult addFirstList(String firstListName);
		public NoteResult changeFirstList(String firstListName,Integer firstListId);
		public NoteResult deleteFirstList(Integer firstListId);
		public NoteResult findAllFirstlist();
}
