package cn.stwms.struts2;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.ActionProxy;
import com.opensymphony.xwork2.DefaultActionProxyFactory;

public class StrutsActionProxyFactory extends DefaultActionProxyFactory{
    @Override
    public ActionProxy createActionProxy(ActionInvocation inv, String namespace, String actionName, String methodName, boolean executeResult, boolean cleanupContext) {
    	int len=actionName.split("/").length;
    	actionName=len>1?capitalFirst(actionName,(len>2 ? actionName.indexOf("/")+1 : 0)):"User/login";
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
