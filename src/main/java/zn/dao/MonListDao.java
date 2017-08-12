/**
 * 
 */
package zn.dao;

import java.util.List;

import zn.entity.MonList;

/**
 * @author hq
 *
 */
public interface MonListDao {
	public int deleteAllMonList();
	public int addAllMonList(List<MonList> list);
	public List<MonList> selectMonListByListLevel(int listLevel);
	public List<MonList> selectMonListByList(String superiorListId);
	public List<MonList> selectAllMonList();

}
