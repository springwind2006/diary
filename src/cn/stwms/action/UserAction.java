package cn.stwms.action;

import java.util.HashMap;
import com.opensymphony.xwork2.ModelDriven;

import cn.stwms.model.User;

public class UserAction extends Action implements ModelDriven<User>{

	private static final long serialVersionUID = 1L;
	private User user=new User();

	@Override
	public User getModel() {
		return user;
	}
	
	/**
	 * 注册
	 * */
	public String register() {
		if(method.equals("POST")){
			int errorcode=userService.register(user);
			String[] errormsgs=new String[]{
					"注册成功",
					"用户名或密码为空",
					"用户名已存在",
					"系统繁忙，稍候再试"
			};
			
			return errorcode>0 ? error(errormsgs[errorcode],errorcode) : 
				success(userService.getUserInfo(user));
		}else{
			HashMap<String, Object> vo=new HashMap<>();
			vo.put("username", "test");
			vo.put("password", "test");
			vo.put("email", "test@163.com");
			setModel("vo", vo);
			return success();
		}
	}
	
	/**
	 * 登录
	 * */
	public String login() {
		if(method.equals("POST")){
			int errorcode=userService.login(user);
			String[] errormsgs=new String[]{
					"登录成功",
					"用户名或密码为空",
					"用户名或密码错误",
					"系统繁忙，稍候再试"
			};
			System.out.println(getPostData());
			return errorcode>0 ? error(errormsgs[errorcode],errorcode) : 
				success(userService.getUserInfo(user));
		}else{
			HashMap<String, Object> vo=new HashMap<>();
			vo.put("username", "test");
			vo.put("password", "test");
			setModel("vo", vo);
			return success();
		}
	}

	/**
	 * 找回密码
	 * */
	public String findPassword() {
		return SUCCESS;
	}

}
