<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" isELIgnored="false" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:include page="../common/include.jsp"/>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	<title>教师信息</title>

 	<link href="${path }/css/bootstrap/bootstrap.min.css" rel="stylesheet" />
 	<link rel="stylesheet" type="text/css" href="${path }/js/zeroModal/zeroModal.css" />
 	<style type="text/css">
 		.title {
 			font-size: 12px;
 		}
 		.label-val {
 			cursor: pointer;
 		}
 	</style>
</head>
<body>
	<div style="width: 400px">
		<label style="display: none;" id="teacherId">${teacher.teacherId }</label>
		<span class="title">姓&emsp;名: </span>
		<label class="label-val">${teacher.teacherName }</label>
		<br />
		<span class="title">后台账户: </span>
		<label class="label-val">${teacher.teacherAccount }</label>
		<br />
		<span class="title">登录密码: </span>
		<label class="label-val" val="${teacher.teacherPwd }" id="teacherPwd">******</label>
		<br />
		<span class="title">后台权限: </span>
		<c:if test="${teacher.adminPower == 0 }">
			<label class="label-val">普通教职工</label>
		</c:if>
		<c:if test="${teacher.adminPower == 1 }">
			<label class="label-val">管理员</label>
		</c:if>
		<br />
		<span class="title">班主任: </span>
		<label class="label-val">${teacher.classInfo.className }</label>
		<br/>
	</div>	

	<!-- js引入 -->
    <script src="${path }/js/jquery.js"></script>
    <script src="${path }/js/zeroModal/zeroModal.min.js"></script>
    <script src="${path }/js/bootstrap/bootstrap.min.js"></script>
    <script type="text/javascript">
    	$(function() {
    		$("#sutdentPwd").mouseover(function() {
    			$(this).text($(this).attr("val"));
    		}).mouseout(function() {
    			$(this).text("******");    			
    		}).click(function() {
    			$(this).replaceWith("<input onblur='resetPwd(this)' id='resetPwd' type='text'></input>");
    		});
    	});
    	
    	function resetPwd(t) {
    		$.ajax({
    			type: "POST",
    			url: "reset/"+$(t).val()+"/"+$("#stuId").text(),
    			success: function(data) {
    				if(data == "t") {
    					zeroModal.show({
    						title: "提示",
    						content: "密码已重置!",
    						width : '200px',
    						height : '130px',
    						overlay : false,
    						ok : true
    					});
    					$("#resetPwd").replaceWith('<label class="label-val" id="sutdentPwd">••••••</label>');
    					$("#sutdentPwd").text($(t).val());
    				} else {
    					alert("修改失败");
    				}
    			}
    		});
    	}
    </script>
</body>
</html>