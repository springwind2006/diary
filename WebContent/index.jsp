<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>首页</title>
<script type="text/javascript" src="http://www.h928.com/statics/home/flycloud/js/jquery.min.js"></script>
<script type="text/javascript">
$(function(){
	$("#login").click(function(){
		var url=$(this).attr("data-url");
		$.get(url,function(res){
			$("#result").val(res);
		});
	});
});
</script>
</head>
<body>
	<h3>Demo</h3>
	<a href="demo/login">login</a>
	<a href="demo/register">register</a>
	<a href="demo/findPassword">findPassword</a>
	<h3>Diary</h3>
	<a href="diary/list/1">list</a>
	<a href="diary/save">save</a>
	<a href="diary/sync">sync</a>
	<h3>User</h3>
	<a href="user/login">login</a>
	<a href="javascript:" data-url="user/login" id="login">login(ajax)</a>
	<a href="user/register">register</a>
	<a href="user/findPassword">findPassword</a>
	<p>
		<textarea id="result" style="width:60%;height:120px;"></textarea>
	</p>
</body>
</html>