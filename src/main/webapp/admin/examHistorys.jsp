<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:include page="../common/include.jsp"/>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	<title>考试历史信息</title>

 	<link href="${path }/css/bootstrap/bootstrap.min.css" rel="stylesheet" />
 	<link rel="stylesheet" type="text/css" href="${path }/js/zeroModal/zeroModal.css" />
</head>
<body>

	<div>
		<table class="table table-striped table-hover table-condensed">
			<thead>
				<tr>
					<th>试卷名称</th>
					<th>试卷总分</th>
					<th>试题数量</th>
					<th>考试得分</th>
					<th>考试人</th>
					<th>操作</th>
				</tr>
			</thead>
			<tbody>
				<c:choose>
					<c:when test="${not empty examHistoryTeacher }">
						<c:forEach items="${examHistoryTeacher }" var="history">
							<tr>
								<td>${history.examPaperInfo.examPaperName }</td>
								<td>${history.examPaperInfo.examPaperScore }</td>
								<td>${history.examPaperInfo.subjectNum }</td>
								<td>
									<c:if test="${history.examScore < history.examPaperInfo.examPaperScore*0.6 }">
										<span style="color: red;"><b>${history.examScore }</b></span>
									</c:if>
									<c:if test="${history.examScore > history.examPaperInfo.examPaperScore*0.6 }">
										<span style="color: green;"><b>${history.examScore }</b></span>
									</c:if>
								</td>
								<td>${history.studentInfo.studentName }</td>
								<td>
									<div class="btn-group">
										<a href="${path}student/review.action?studentId=${history.studentInfo.studentId }&examPaperId=${history.examPaperInfo.examPaperId }&score=${history.examPaperInfo.examPaperScore}&examPaperName=${history.examPaperInfo.examPaperName }&studentName=${history.studentInfo.studentName }" class="btn btn-info btn-sm">详情</a>
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
    <script src="${path }/js/zeroModal/zeroModal.min.js"></script>
    <script src="${path }/js/bootstrap/bootstrap.min.js"></script>
    <script src="${path }/js/handle.js"></script>
    <script src="${path }/js/add-update.js"></script>
	<script src="${path }/js/layer/layer.js"></script>
</body>
</html>