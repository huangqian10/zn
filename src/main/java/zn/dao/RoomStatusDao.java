package zn.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import zn.entity.RoomStatus;

/**
 * @author hl
 *
 */
public interface RoomStatusDao {
   public void save(RoomStatus info);

   public List<RoomStatus> getByMonId(Integer monId);

   public void saveAll(List<RoomStatus> roomStatusList);

   public void deleteAll(List<Integer> idList);

   public List<RoomStatus> getByMonIdAndNode(@Param("monId")Integer monId,@Param("nodeNum") String nodeNum);

   public void updateAirSwitchStatus(@Param("monId")Integer monId,@Param("nodeNum") String nodeNum,@Param("airSwitchStatus") String staus);
}
