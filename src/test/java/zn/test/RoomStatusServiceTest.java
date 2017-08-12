package zn.test;

import java.util.List;

import org.junit.Test;

import zn.entity.RoomStatus;
import zn.service.RoomStatusServiceImpl;
import zn.until.WebService;

public class RoomStatusServiceTest {
	@Test
	public void testjsonToRoomStatusList(){
			String json="[]";	
			RoomStatusServiceImpl service=new RoomStatusServiceImpl();
			List<RoomStatus> list=service.jsonToRoomStatusList(json);
			System.out.println(list.size());
			for(RoomStatus roomStatus:list){
				System.out.println(roomStatus.toString());
			}
	}
     
	@Test
	public void testWebService(){
		WebService ws=new WebService();
		String str=ws.RoomStatusInfoGetForJson("91a54281-d355-4268-9f3b-d50b3cf8f376");
		System.out.println(str);
	}

}
