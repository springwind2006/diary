package cn.stwms.action;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.interceptor.ApplicationAware;
import org.apache.struts2.interceptor.CookiesAware;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.apache.struts2.interceptor.SessionAware;
import org.apache.struts2.util.ServletContextAware;

import com.opensymphony.xwork2.ActionSupport;

public class Action  extends ActionSupport 
					implements 	ApplicationAware,
								ServletContextAware,
								ServletRequestAware,
								ServletResponseAware,
								SessionAware,
								CookiesAware
{
	private static final long serialVersionUID = 1L;
	Map<String, Object> application;
	protected ServletContext context;
	protected HttpServletRequest request;
	protected HttpServletResponse response;
	protected Map<String, Object> session;
	Map<String, String> cookies;

	@Override
	public String execute() throws Exception {
		return "welcome";
	}

	@Override
	public void setServletContext(ServletContext context) {
		this.context=context;
	}

	@Override
	public void setServletRequest(HttpServletRequest request) {
		this.request=request;
	}

	@Override
	public void setServletResponse(HttpServletResponse response) {
		this.response=response;
	}

	@Override
	public void setSession(Map<String, Object> session) {
		this.session=session;
	}

	@Override
	public void setCookiesMap(Map<String, String> cookies) {
		this.cookies=cookies;
	}

	@Override
	public void setApplication(Map<String, Object> application) {
		this.application=application;
	}
	
}


