package cn.stwms.interceptor;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import cn.stwms.model.User;

public class LoginInterceptor extends HandlerInterceptorAdapter {
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		HttpSession session=request.getSession();
		ServletContext servletContext=session.getServletContext();
		User user=(User)session.getAttribute("user");
		String userId="";
		boolean isLogin=false;
		Map<String, Object> result=new LinkedHashMap<>();
		
	    //单点登录
	    try {
	    	userId=String.valueOf(user.getId());
	    	Map<String, String> userList=(Map<String, String>)servletContext.getAttribute("userList");
		    String sessionIdLast=userList.get(userId);
		    String sessionIdNow=session.getId();
	    	if(!sessionIdLast.trim().equals(sessionIdNow)){
	    		result.put("errcode", 2);
	    		result.put("errmsg", "logined");
		    }else{
		    	isLogin=true;
		    }
		} catch (Exception e) {
			result.put("errcode", 1);
    		result.put("errmsg", "not logined");
		}
	    
	    if(!isLogin){
	    	ObjectMapper json=new ObjectMapper();
			response.getWriter().print(json.writeValueAsString(result));
	    }
	    result=null;
		return isLogin;
	}

}
