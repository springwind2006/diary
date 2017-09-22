<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%@taglib prefix="st" uri="http://www.stwms.cn/jsptag"%>
<%@page import="java.util.*,java.security.*,java.math.*,java.sql.*"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>首页</title>
</head>
<body>
<hr/>Diary<br/>
<s:a action="Diary/save?id=12">save</s:a>
<s:a action="Diary/list">list</s:a>
<s:a action="Diary/sync">sync</s:a>
<p>${param.id}</p>
<hr/>User<br/>
<s:a action="User/register">register</s:a>
<s:a action="User/login">login</s:a>
<s:a action="User/findPassword">findPassword</s:a>
</body>
</html>