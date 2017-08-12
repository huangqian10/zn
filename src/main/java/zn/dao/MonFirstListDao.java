/**
 * 
 */
package zn.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import zn.entity.MonFirstList;

/**
 * @author hq
 *
 */
public interface MonFirstListDao {
	public       int    selectIsExist(String firstListName);
	public    int   addFirstList(String firstListName);
	public int deleteFirstList(int firstListId);
	public int changeFirstList(@Param("firstListName")String firstListName,@Param("firstListId")int firstListId);
	public  List<MonFirstList> findAllFirstlist();
	public int selectIsExistById(int firstListId);
	public MonFirstList selectListById(int firstListId);
}
