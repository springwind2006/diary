package cn.stwms.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import cn.stwms.model.User;
import cn.stwms.service.UserService;

@Controller
@RequestMapping("/user")
public class UserController{
	
	@Autowired
	private UserService userService;

	@RequestMapping(value="/register",method = RequestMethod.GET)
	public String register(HttpServletRequest request,ModelMap model){
		int result=0;
		User user=new User();
		user.setUsername("lirui");
		user.setPassword("12345");
		user.setEmail("lirui@163.com");
		result=userService.save(user);
		model.addAttribute("message", result>0?"success":"failed");
		return "user/register";	
	}
	
	@RequestMapping(value="/login",method = RequestMethod.GET)
	public String login(HttpServletRequest request,ModelMap model){
		HttpSession session=request.getSession();
		User user=userService.find(11);
		System.out.println(user.getId());
		session.setAttribute("user", user);
		
		model.addAttribute("message", "handleRequest");	
		System.out.println(request.getAttribute("isAjax"));
		return "user/login";	
	}
	
	@RequestMapping(value="/findPassword",method = RequestMethod.GET)
	public String findPassword(ModelMap model){
		model.addAttribute("message", "handleRequest");	
		return "user/findPassword";	
	}
	
	
}
