package cn.stwms.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;


public class DefaultInterceptor extends HandlerInterceptorAdapter {
	
	/**
	 * 判断是否为ajax（API调用请求）
	 * @param request 请求对象
	 * 判断调节：	1、ajax请求； 2、请求参数中带有ajax，且值为1； 3、以"/api/"开头的请求路径
	 * */
	private boolean isAjaxRequest(HttpServletRequest request) {
		return (request.getHeader("X-Requested-With") != null  && 
							"XMLHttpRequest".equals(request.getHeader("X-Requested-With").toString()))
					|| (request.getParameter("ajax")!=null && "1".equals(request.getParameter("ajax"))) 
					|| request.getRequestURI().startsWith("/api/");
	}
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		request.setAttribute("isAjax", this.isAjaxRequest(request));
		return true;
	}

}
