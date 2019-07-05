<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" isELIgnored="false" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<jsp:include page="../common/include.jsp"/>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	<title>Insert title here</title>

 	<link href="${path }/css/bootstrap/bootstrap.min.css" rel="stylesheet" />
 	<link href="${path }/css/form-public.css" rel="stylesheet" />
</head>
<body>

	<div style="text-align: center;">
		<form action="${path}../grade/insertGrade.action" method="POST">
			<br /><br />
			年级名称：<input id="gradeName" name="gradeName" class="ipt" />
			<br /><br />
			<input type="button" onclick="addGrade();" value="提交" class="sub" />
		</form>
	</div>


	<!-- js引入 -->
    <script src="${path }/js/jquery.js"></script>
    <script src="${path }/js/bootstrap/bootstrap.min.js"></script>
    <script src="${path }/js/form-public.js"></script>
    <script src="${path }/js/layer/layer.js"></script>
    <script src="${path }/js/grade.js"></script>
</body>
</html>