<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<% 
String abc="hellodd";
for(int i=0;i<10;i++){
	out.println(i);
}
%>
<s:a action="Test/show">show</s:a>
<s:a action="Test/list">list</s:a>
<s:a action="index.php?c=spring&a=show&id=100&name=spring">跳转</s:a>
</body>
</html>