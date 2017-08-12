package zn.controller.user;

import java.util.Map;

import javax.annotation.Resource;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


import javax.servlet.http.HttpSession;


import zn.service.UserService;
import zn.until.NoteResult;

@Controller  
@RequestMapping("/user")  
public class UserLoginController {  
	
	
	@Resource
	private UserService userService;
	

	
	@RequestMapping("/login")
	@ResponseBody
	public NoteResult execute(String telephone,String password,HttpSession httpSession){
		Map<String,Object> map=userService.checkLogin(telephone, password);	
		NoteResult note=(NoteResult) map.get("NoteResult");
		httpSession.setAttribute("telephone", map.get("telephone"));
		httpSession.setAttribute("userId", map.get("userId"));
		return note;	
	}
	
	
	@RequestMapping("/logout")
	public String execute(HttpSession session){		
		session.invalidate(); 
		return "redirect:/user/login.do";	
	}
}

