package cn.stwms.interceptor;

import java.util.Map;

import javax.servlet.ServletContext;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.MethodFilterInterceptor;

import cn.stwms.model.User;

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
	    ActionContext actionContext=invocation.getInvocationContext();
	    Map<String, Object> session=actionContext.getSession();
	    User user=(User)session.get("user");
	    String userId="";
	    
	    try {
	    	userId=String.valueOf(user.getId());
		} catch (Exception e) {
			System.out.println(actionContext.getName()+":未登录");
			actionContext.getValueStack().setValue("data", 121);
			return "login";
		}

	    //单点登录
	    try {
	    	Map<String, String> userList=(Map<String, String>)servletContext.getAttribute("userList");
		    String sessionIdLast=userList.get(userId);
		    String sessionIdNow=ServletActionContext.getRequest().getSession().getId();
	    	if(!sessionIdLast.trim().equals(sessionIdNow)){
		    	System.out.println("此账号已经登录");
		    	return "hasLogined";
		    }
		} catch (Exception e) {
			return "failed";
		}
	   
	    
		return invocation.invoke();
	}
}
