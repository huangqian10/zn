package zn.controller.user;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import zn.service.UserService;
import zn.until.NoteResult;

@Controller
@RequestMapping("/user")
public class SetUserDeviceTokenController {
	@Resource
	private UserService userService;

	@RequestMapping("/setDeviceToken")
	@ResponseBody
	public NoteResult setDeviceToken(@RequestParam(required = false) Integer userId, String deviceToken,
			Integer mobileType, HttpSession session) {
		if (userId == null) {
			userId = (Integer) session.getAttribute("userId");
		}
		NoteResult result = userService.setUserDeviceToken(userId, deviceToken, mobileType);
		return result;
	}
}
