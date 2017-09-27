package cn.stwms.action;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
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

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import cn.stwms.service.UserService;

public class Action  extends ActionSupport 
					implements 	ApplicationAware,
								ServletContextAware,
								ServletRequestAware,
								ServletResponseAware,
								SessionAware,
								CookiesAware
{
	private static final long serialVersionUID = 1L;
	protected Map<String, Object> application;
	protected ServletContext context;
	protected HttpServletRequest request;
	protected HttpServletResponse response;
	protected Map<String, Object> session;
	protected Map<String, String> cookies;
	protected String baseUrl=null;
	protected String method=null;
	protected UserService userService;
	private HashMap<String, Object> message=new HashMap<String, Object>(){{
		put("errcode", 0); 
		put("errmsg", "ok");
		put("data", null);
	}};

	public void setUserService(UserService userService) {
		this.userService = userService;
	}
	public UserService getUserService() {
		return userService;
	}
	
	public HashMap<String, Object> getMessage(){
		return message;
	}
	
	public String success(){
		message.put("errcode", 0); 
		message.put("errmsg", "ok");
		return SUCCESS;
	}
	
	public String success(Object data){
		message.put("errcode", 0); 
		message.put("errmsg", "ok");
		message.put("data", data);
		return SUCCESS;
	}
	
	public String error(){
		message.put("errcode", 1); 
		message.put("errmsg", ERROR);
		return SUCCESS;
	}
	public String error(String msg){
		message.put("errcode", 1); 
		message.put("errmsg", msg);
		return SUCCESS;
	}
	public String error(String msg,int code){
		message.put("errcode", code); 
		message.put("errmsg", msg);
		return SUCCESS;
	}
	

	@Override
	public String execute() throws Exception {
		return SUCCESS;
	}

	@Override
	public void setServletContext(ServletContext context) {
		this.context=context;
	}

	@Override
	public void setServletRequest(HttpServletRequest request) {
		this.request=request;
		baseUrl=request.getContextPath();
		method=request.getMethod();
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
	
	public String getBaseUrl(){
		return baseUrl;
	}
	public String getMethod(){
		return method;
	}
	
	public void setModel(String key,Object value){
		ActionContext.getContext().put(key, value);
	}
	
	public String getPostData(){
		StringBuffer sb = new StringBuffer(); 
		try {
			request.setCharacterEncoding("UTF-8");
	        InputStream is = request.getInputStream();  
	        InputStreamReader isr = new InputStreamReader(is, "UTF-8");  
	        BufferedReader br = new BufferedReader(isr);  
	        String s = "";  
	        while ((s = br.readLine()) != null) {
	            sb.append(s);  
	        }
		} catch (Exception e) {
		}
        return sb.toString(); //次即为接收到微信端发送过来的xml数据
	}
	
}



