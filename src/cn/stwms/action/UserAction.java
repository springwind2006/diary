package cn.stwms.action;

import cn.stwms.service.UserService;

public class UserAction extends Action {

	private static final long serialVersionUID = 1L;
	private String errcode="0";
	private String errmsg="ok";
	private Object data=null;
	private UserService userService;
	
	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	public String getErrcode() {
		return errcode;
	}

	public void setErrcode(String errcode) {
		this.errcode = errcode;
	}

	public String getErrmsg() {
		return errmsg;
	}

	public void setErrmsg(String errmsg) {
		this.errmsg = errmsg;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	/**
	 * 注册
	 * */
	public String register() {
		String kw=request.getParameter("kw")==null?"n":request.getParameter("kw");
		data=userService.list(kw);
		return SUCCESS;
	}
	
	/**
	 * 登录
	 * */
	public String login() {
		data=request.getParameterMap();
		return SUCCESS;
	}

	/**
	 * 找回密码
	 * */
	public String findPassword() {
		return SUCCESS;
	}
}
