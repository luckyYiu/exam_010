<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" isELIgnored="false" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:include page="../common/include.jsp"/>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	<title>年级信息</title>

 	<link href="${path }/css/bootstrap/bootstrap.min.css" rel="stylesheet" />
 	<link href="${path }/css/public.css" rel="stylesheet" />
</head>
<body>
	<c:set var="power" value="${sessionScope.adminPower }"></c:set>
	<div>
		<table class="table table-striped table-hover table-condensed">
			<thead>
				<tr>
					<th>年级编号</th>
					<th>年级名称</th>
						<th>操作
							&emsp;
							<a href="javascript:toaddGrade();" class="btn btn-xs btn-info">添加</a>
						</th>
				</tr>
			</thead>
			<tbody>
				<c:choose>
					<c:when test="${not empty grades }">
						<c:forEach items="${grades }" var="grade">
							<tr>
								<td>${grade.gradeId }</td>
								<td id="g"${grade.gradeId }>${grade.gradeName }</td>
								<td>
									<div class="btn-group">
										<button type="button" class="btn btn-info btn-sm" onclick="toupdateGrade('${grade.gradeId}')">修改</button>
										<button type="button" class="btn btn-danger btn-sm" onclick="deleteGrade('${grade.gradeId }')">删除</button>
										<button type="button" class="btn btn-primary btn-sm lookclass" id="c${grade.gradeId }">班级</button>
									</div>
								</td>
							</tr>
						</c:forEach>
					</c:when>
				</c:choose>
			</tbody>
		</table>
		<form action="class" method="post">
			<input type="hidden" value="DELETE" name="_method" />
		</form>
	</div>


	<!-- js引入 -->
    <script src="${path }/js/jquery.js"></script>
    <script src="${path }/js/bootstrap/bootstrap.min.js"></script>
    <script src="${path }/js/handle.js"></script>
    <script src="${path }/js/view-link.js"></script>
	<script src="${path }/js/layer/layer.js"></script>
	<script src="${path }/js/grade.js"></script>
</body>
</html>