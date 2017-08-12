/**
 * 
 */
package zn.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import zn.entity.MonSecondList;

/**
 * @author hq
 *
 */
public interface MonSecondListDao {
	public int selectIsExist(String secondListName);
	public int addSecondList(@Param("secondListName")String secondListName,@Param("firstListId")int  firstListId);
	public int  deleteSecondList(int  secondListId);
	public int changeSecondList(@Param("secondListName")String secondListName,@Param("firstListId")int  firstListId,@Param("secondListId")int secondListId);
	public List<MonSecondList>  findAllSecondlist();
	public int selectIsExistById(int secondListId);
	public   MonSecondList    selectListById(int secondListId);
	
	public List<MonSecondList>  findSecondlistByFirstId(int firstListId);
}
