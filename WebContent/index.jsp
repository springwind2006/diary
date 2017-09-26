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
<hr/>api/Diary<br/>
<s:a action="api/diary/save?id=12">api/save</s:a>
<s:a action="api/diary/list">api/list</s:a>
<s:a action="api/diary/sync">api/sync</s:a>
<hr/>User<br/>
<s:a action="user/register">register</s:a>
<s:a action="user/login">login</s:a>
<s:a action="user/findPassword">findPassword</s:a>
<hr/>api/User<br/>
<s:a action="api/user/register">api/register</s:a>
<s:a action="api/user/login">api/login</s:a>
<s:a action="api/user/findPassword">api/findPassword</s:a>
<hr/>Other<br/>
<s:a action="123">123</s:a>
<s:a action="api/123">api/123</s:a>
</body>
</html>