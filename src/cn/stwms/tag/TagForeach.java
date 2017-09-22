package cn.stwms.tag;


import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.jsp.JspContext;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.JspFragment;
import javax.servlet.jsp.tagext.SimpleTagSupport;

public class TagForeach extends SimpleTagSupport {
	// 标签属性，用于指定需要被迭代的集合
	private String name;
	// 标签属性，指定迭代集合元素，为集合元素指定的名称
	private String item;
	// 标签属性，指定迭代集合元素，为集合元素指定的键名
	private String key;

	// collection属性的setter和getter方法
	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return this.name;
	}

	// item属性的setter和getter方法
	public void setItem(String item) {
		this.item = item;
	}

	public String getItem() {
		return this.item;
	}
	
	// key属性的setter和getter方法
	public void setKey(String key) {
		this.key = key;
	}

	public String getKey() {
		return this.key;
	}

	// 标签的处理方法，简单标签处理类只需要重写doTag方法
	public void doTag() throws JspException, IOException {
		// 从page scope中获取属性名为collection的集合
		JspContext context=this.getJspContext();
		JspFragment body=this.getJspBody();
		Object obj = context.getAttribute(name);
		// 遍历集合
		if(obj!=null){
			if(obj instanceof ArrayList ){
				Collection<?> itemList = (Collection<?>) obj;
				for (Object s : itemList) {
					// 将集合的元素设置到page 范围
					context.setAttribute(item, s);
					// 输出标签体
					body.invoke(null);
				}
			}else if(obj instanceof Map){
				Map<?, ?> map = (Map<?, ?>) obj;
				Iterator<?> iter = map.entrySet().iterator();
				while (iter.hasNext()) {
					Map.Entry<?, ?> entry = (Map.Entry<?, ?>) iter.next();
					if(this.key!=null){
						context.setAttribute(this.key, entry.getKey());
					}
					context.setAttribute(this.item, entry.getValue());
					body.invoke(null);
				}
			}
		}
	}
}
