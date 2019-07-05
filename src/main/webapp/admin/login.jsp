<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" isELIgnored="false" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:include page="../common/include.jsp"/>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <title>【猿来入此在线考试系统】后台登录</title>

    <link href='${path }/images/admin/admin_index.png' rel='shortcut icon' type='image/x-icon'>
    <link href="${path}/css/bootstrap/bootstrap.min.css" rel="stylesheet" />

    <style type="text/css">
        body {
            background: url("../images/admin/login_bg.jpg")
            transparent;
            background-size: 100%;
        }
        .container {
            width: 300px;
            height: 200px;
            margin-top: 100px;
        }
        .error-msg {
            color: red;
            font-size: 12px;
            display: none;
        }
    </style>
</head>
<body>
<div class="container">
    <form class="form-signin" action="../teacher/login.action" method="post" id="adminLogin">
        <h2 class="form-signin-heading" style="color:white;" align="center">后台登录</h2>
        <label for="inputEmail" class="sr-only">Email address</label>
        <input id="username" type=text name="teacherAccount" class="form-control" placeholder="用户名" />
        <label class="error-msg error-msg-name">用户名不存在</label>
        <br />
        <label for="inputPassword" class="sr-only">Password</label>
        <input id="pwd" type="password" name="teacherPwd" class="form-control" placeholder="密  码" />
        <label class="error-msg error-msg-pwd">密码错误</label>
        <br />
        <input onclick="teacherLogin()" class="btn btn-lg btn-primary btn-block" type="button" value="登录" id="adminSign"></input>
    </form>
</div>
<!-- js引入 -->

<script src="${path}/js/jquery.js"></script>
<script src="${path}/js/bootstrap/bootstrap.min.js"></script>
<script src="${path}/js/layer/layer.js"></script>
<script src="${path}/js/login.js"></script>
<script src="${path}/js/Validform_v5.3.2_min.js"></script>
</body>
</html>