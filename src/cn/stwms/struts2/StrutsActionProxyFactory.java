package cn.stwms.struts2;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.ActionProxy;
import com.opensymphony.xwork2.DefaultActionProxyFactory;

public class StrutsActionProxyFactory extends DefaultActionProxyFactory{
    @Override
    public ActionProxy createActionProxy(ActionInvocation inv, String namespace, String actionName, String methodName, boolean executeResult, boolean cleanupContext) {
    	int len=actionName.split("/").length;
    	actionName=len>1 && len<4 ? capitalFirst(actionName,(len>2 ? actionName.indexOf("/")+1 : 0)) : "User/login";
    	StringBuilder className=new StringBuilder("cn.stwms.action.");
    	className.append(actionName.substring(len==3?actionName.indexOf("/")+1:0,actionName.lastIndexOf("/")));
    	className.append("Action");
    	try{
    		Class.forName(className.toString());
    	}catch (Exception e) {
    		actionName=(len==3?"api/User/login":"User/login");
		}
    	className=null;
    	
        StrutsActionProxy proxy = new StrutsActionProxy(inv, namespace, actionName, methodName, executeResult, cleanupContext);
        container.inject(proxy);
        proxy.prepare();
        return proxy;
    }
    
    private String capitalFirst(String str,int index){
    	char[] cs=str.toCharArray();
    	if(cs[index]>=97 && cs[index]<=122){
    		cs[index]-=32;
    	}
        return String.valueOf(cs);
    }
}
