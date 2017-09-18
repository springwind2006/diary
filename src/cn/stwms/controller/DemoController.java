package cn.stwms.controller;

import java.io.IOException;
import java.io.Writer;
import java.util.Enumeration;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

import cn.stwms.model.User;

@Controller
@RequestMapping("/demo")
@SessionAttributes (value={"welcome", "day", "user1" })
public class DemoController{

	@ResponseBody
	@RequestMapping(value="/list")
    public ModelMap List(){
		ModelMap result =new ModelMap();
		result.put("name", "tianlan");
		result.put("age", 30);
    	return result;
    }

	@RequestMapping(value="/login",method = RequestMethod.GET)
	public void login(
			@ModelAttribute ( "welcome" ) String welcome, 
			@ModelAttribute ( "day" ) int day, 
			@ModelAttribute ( "user1" ) User user, 
			Writer writer, 
			HttpSession session,
			ModelMap model)  throws IOException
	{
		writer.write( "Hello " + welcome + " , Hello " + user.getUsername() + ",last day:" + day);
		writer.write( "\r" );
		Enumeration<?> enume = session.getAttributeNames();
		while (enume.hasMoreElements()){
			writer.write(enume.nextElement() + "\r" );
		}
	
		model.addAttribute("message", "handleRequest");		
	}
	
	@RequestMapping(value="/login2",method = RequestMethod.GET)
	public void login2(
			@RequestParam("cid") String cid, //此时必须传入cid参数
			Map<String, Object> map,
			Writer writer, 
			HttpServletRequest request,
			ModelMap model)  throws IOException
	{
		//输出@ModelAttribute装配的属性
		String hello=(String)map.get("welcome");
		User user=(User)map.get("user1");
		int day=(int)map.get("day");
		writer.write( "id:"+cid+",Hello " + hello + " , Hello " + user.getUsername() + ",last day:" + day);
		writer.write( "\r" );
		
		//获取session对象属性
		HttpSession session=request.getSession();
		Enumeration<?> enume = session.getAttributeNames();
		String session_name;
		while (enume.hasMoreElements()){
			session_name=(String) enume.nextElement();
			writer.write("session name:"+session_name + " value:"+session.getAttribute(session_name)+"\r" );
		}
	
		model.addAttribute("message", "handleRequest");		
	}
	
	
	@ModelAttribute("welcome")
    public String getWelcome() {
       System. out .println( "set attr:-------------Hello---------" );
       return "world" ;
    }

    @ModelAttribute("day")
    public int getDay() {
       System. out .println( "set attr:-------------year----------" );
       return 10;
    }
	
	@ModelAttribute("user1")
    public User getUser(){
       System. out .println( "set attr:---------getUser-------------" );
       User user=new User();
       user.setUsername("spring12");
       user.setId(11);
       return user;
    }

}
