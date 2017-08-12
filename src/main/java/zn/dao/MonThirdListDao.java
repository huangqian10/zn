/**
 * 
 */
package zn.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;


import zn.entity.MonThirdList;

/**
 * @author hq
 *
 */
public interface MonThirdListDao {
	public int selectIsExist(String thirdListName);
	public int addThirdList(@Param("thirdListName")String thirdListName,@Param("secondListId")int  secondListId);
	public int  deleteThirdList(int  thirdListId);
	public int changeThirdList(@Param("thirdListName")String thirdListName,@Param("secondListId")int secondListId,@Param("thirdListId")int  thirdListId);
	public List<MonThirdList>  findAllThirdlist();
	public int selectIsExistById(int thirdListId);
	public   MonThirdList      selectListById(int thirdListId);
	public  List<MonThirdList> findThirdlistBySecondId(int secondListId);
}
