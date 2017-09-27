package cn.stwms.interceptor;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.MethodFilterInterceptor;


import cn.stwms.model.User;
import cn.stwms.service.UserService;

public class LoginInterceptor extends MethodFilterInterceptor {
	private static final long serialVersionUID = 1L;
	private String name;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@Override
	protected String doIntercept(ActionInvocation invocation) throws Exception {
		ServletContext  servletContext=ServletActionContext.getServletContext();
		HttpServletRequest request=ServletActionContext.getRequest();
	    ActionContext actionContext=invocation.getInvocationContext();
	    Map<String, Object> session=actionContext.getSession();
	    boolean isApi=actionContext.getName().startsWith("api/");
	    HashMap<String, Object> message=(HashMap<String, Object>)actionContext.getValueStack().findValue("message");
	    UserService userService=(UserService)actionContext.getValueStack().findValue("userService");
	    
	    User user=(User)session.get("user");
	    String userId="";
	    
	    System.out.println(userService);
	    
	    try {
	    	userId=String.valueOf(user.getId());
		} catch (Exception e) {
			message.put("errcode", 1);
			message.put("errmsg", "notLogin");
			return isApi?"api":"login";
		}

	    //单点登录
	    try {
	    	Map<String, String> userList=(Map<String, String>)servletContext.getAttribute("userList");
		    String sessionIdLast=userList.get(userId);
		    String sessionIdNow=request.getSession().getId();
	    	if(!sessionIdLast.trim().equals(sessionIdNow)){
	    		message.put("errcode", 2);
				message.put("errmsg", "hasLogined");
		    	return isApi?"api":"failed";
		    }
		} catch (Exception e) {
			message.put("errcode", 3);
			message.put("errmsg", "errorLogin");
			return isApi?"api":"failed";
		}
		return invocation.invoke();
	}
}
