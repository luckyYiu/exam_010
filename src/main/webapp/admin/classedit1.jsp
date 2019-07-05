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
	<title>班级信息编辑</title>

 	<link href="${path }/css/bootstrap/bootstrap.min.css" rel="stylesheet" />
 	<link href="${path }/css/form-public.css" rel="stylesheet" />
 	<link rel="stylesheet" type="text/css" href="${path }/js/zeroModal/zeroModal.css" />
</head>
<body>
	<div style="text-align: center;">
		<form action="class" method="POST">
			<!-- 修改情况下才显示编号 -->
			<c:if test="${clazz.classId != null }">
				<b class="form-title">班级编号：</b><input class="ipt" id="classId" name="classId" value="${clazz.classId}" readonly="readonly" unselectable='on' onfocus='this.blur()' />
				<input type="hidden" value="PUT" name="_method" />
				<br /><br />
			</c:if>
			<b class="form-title">班级名称：</b><input id="className" value="${clazz.className}" name="className" path="className" class="ipt" />
			<br /><br />
			<b class="form-title">所属年级：</b><select id="gradeId" name="gradeId" data-live-search="true" class="sel">
				<c:forEach items="${grades }" var="grades">
					<option value="${grades.gradeId }">${grades.gradeName }</option>
				</c:forEach>
			</select>
			<br /><br />
			<b class="form-title">班 主&nbsp;&nbsp;任：</b><select id="teacherId" name="teacherId" data-live-search="true" class="sel">
				<c:forEach items="${teachers }" var="teacher">
					<option value="${teacher.teacherId }">${teacher.teacherName }</option>
				</c:forEach>
			</select>
			<br /><br />
			<!-- 存放上一个班主任编号 -->
			<input type="hidden" value="${editClass.teacher.teacherId }" name="lastTeacher" />
			<input onclick="updateClass()" class="sub" type="button" value="提交"/>
		</form>
	</div>


	<!-- js引入 -->
    <script src="${path }/js/jquery.js"></script>
    <script src="${path }/js/bootstrap/bootstrap.min.js"></script>
    <script src="${path }/js/form-public.js"></script>
	<script src="${path }/js/layer/layer.js"></script>
	<script src="${path }/js/class.js"></script>
</body>
</html>