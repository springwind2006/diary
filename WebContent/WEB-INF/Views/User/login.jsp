<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="zh-cmn-Hans">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=no">
    <title>登录-日记本</title>
    <meta name="apple-mobile-web-app-capable" content="yes" />
    <meta name="apple-mobile-web-app-title" content="日记本" />
    <link rel="stylesheet" href="${baseUrl}/resx/css/weui.min.css?v=1.0"/>
    <link rel="stylesheet" href="${baseUrl}/resx/css/common.css"/>
    <script src="${baseUrl}/resx/js/zepto.min.js"></script>
    <script src="${baseUrl}/resx/js/CipherUtils.js"></script>
</head>
<body>
    <div class="container" id="container">
	    <div class="page" id="showInput">
	    	<form id="doform" name="doform" action="${baseUrl}/api/user/login" >
			    <div class="page__bd">
			    	<div class="weui-cells weui-cells_form">
			    		<div class="weui-cell">
			                <div class="weui-cell__hd"><label class="weui-label">用户名</label></div>
			                <div class="weui-cell__bd">
			                    <input class="weui-input" name="username" id="username" value="${vo.username}" type="text" placeholder="请输入用户名"/>
			                </div>
			            </div>
			    		<div class="weui-cell">
			                <div class="weui-cell__hd"><label class="weui-label">密 码</label></div>
			                <div class="weui-cell__bd">
			                    <input class="weui-input" name="password" id="password" value="${vo.password}" type="password" placeholder="请输入密码"/>
			                </div>
			            </div>
			    	</div>
			        <div class="weui-btn-area">
			            <a class="weui-btn weui-btn_primary" href="javascript:" id="submit_btn">登 录</a>
			        </div>
			        <p>${vo.sessionid}</p>
			    </div>
		    </form>
		</div>
		<script src="${baseUrl}/resx/js/diary.js?v=1.4"></script>
    </div>
    <script src="${baseUrl}/resx/js/weui.min.js"></script>
</body>
</html>
