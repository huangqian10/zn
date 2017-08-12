/**
 * 
 */
package zn.timer;

import javax.annotation.Resource;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import zn.service.GetDateService;

/**
 * @author hq
 *
 */
@Component
public class GetDateTimer {
	
	@Resource
	private GetDateService getDateService;
	
	 @Scheduled(cron ="0 0 2 * * ?") //每天凌晨两点执行
     public    void doSomethingWith(){
		 try {

			 getDateService.getDate();
			
		} catch (Exception e) {
			e.printStackTrace();
		}

		
	 }

}
