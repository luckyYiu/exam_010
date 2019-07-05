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
 	<link rel="stylesheet" href="${path }/js/zeroModal/zeroModal.css" />
	<link rel="stylesheet" href="${path }/js/layui/css/layui.css" media="all">
</head>
<body>

	<div style="text-align: center;">
		<table class="table table-striped table-hover table-condensed">
			<thead>
				<tr>
					<th>教师编号</th>
					<th>教师姓名</th>
					<th>后台账户</th>
					<th>后台密码</th>
					<th>后台权限</th>
					<th>班主任</th>
					<%--<c:if test="${sessionScope.adminPower == 1 }">--%>
						<th>操作
							&emsp;
							<button onclick="toaddTeacher()" type="button" class="btn btn-xs btn-info">添加</button>
						</th>
					<%--</c:if>--%>
				</tr>
			</thead>
			<tbody id="tbdata">
				<%--<c:choose>--%>
					<%--<c:when test="${not empty teacherList }">--%>
						<c:forEach items="${teacherList.result }" var="teacher">
							<tr>
								<td>${teacher.teacherId }</td>
								<td>${teacher.teacherName }</td>
								<td>${teacher.teacherAccount }</td>
								<td>
									<!-- 只有真正后台管理员才能查看教师后台密码，且重置 -->

										<span id="hidePwd">******</span>
										<span id="showPwd" style="display: none">${teacher.teacherPwd }</span>
										<button type="button" class="btn btn-info btn-xs viewPwd">查看</button>

									<%--<c:if test="${sessionScope.adminPower != 1 }">
										******
									</c:if>--%>
								</td>
								<td>
									<c:if test="${teacher.adminPower == 0 }">
										普通教职工
									</c:if>
									<c:if test="${teacher.adminPower == 1 }">
										<span style="color: red;">管理员</span>
									</c:if>
								</td>
								<td>
									<c:if test="${teacher.isWork == 0 }">
										否
									</c:if>
									<c:if test="${teacher.isWork == 1 }">
										<a href="${path}/class/clazz.action?classId=${teacher.classId }">${teacher.className }</a>
									</c:if>
								</td>
								<td>
										<div class="btn-group">
											<c:set value="0" var="flag"></c:set>
											<%--<c:if test="${sessionScope.loginTeacher.teacherName == teacher.teacherName or sessionScope.adminPower == 1 }">--%>
												<c:set value="1" var="flag"></c:set>
												<button type="button" class="btn btn-info btn-sm" onclick="toupdateTeacher('${teacher.teacherId }')">修改</button>
											<%--</c:if>
											<c:if test="${sessionScope.adminPower == 1}">--%>
												<button type="button" class="btn btn-danger btn-sm" onclick="deleteTeacher('${teacher.teacherId }')">删除</button>
											<%--</c:if>--%>
										</div>
								</td>
							</tr>
						</c:forEach>
					<%--</c:when>--%>
				<%--</c:choose>--%>
			</tbody>
		</table>
		<form action="class" method="post">
			<input type="hidden" value="DELETE" name="_method" />
		</form>
		<div>
			<ul class="pagination">
				<c:if test="${teacherList.pageCount > 1 }">
					<ul class="pagination">
						<li><a href="${path}/teacher/teacherList.action?page=1">首页</a></li>
						<c:if test="${teacherList.pageNow-1 > 0 }">
							<li><a href="${path}/teacher/teacherList.action?page=${teacherList.pageNow-1 }">上一页</a></li>
						</c:if>
						<c:forEach begin="${teacherList.pageNow }" end="${teacherList.pageNow+4 }" var="subPage">
							<c:if test="${subPage-5 > 0 }">
								<li><a href="${path}/teacher/teacherList.action?page=${subPage-5 }">${subPage-5 }</a></li>
							</c:if>
						</c:forEach>
						<c:forEach begin="${teacherList.pageNow }" end="${teacherList.pageNow+5 }" step="1" var="pageNo">
							<c:if test="${pageNo <= teacherList.pageCount }">
								<c:if test="${teacherList.pageNow == pageNo }">
									<li class="active"><a href="${path}/teacher/teacherList.action?page=${pageNo }">${pageNo }</a></li>
								</c:if>
								<c:if test="${teacherList.pageNow != pageNo }">
									<li><a href="${path}/teacher/teacherList.action?page=${pageNo }" class="pageLink">${pageNo }</a></li>
								</c:if>
							</c:if>
						</c:forEach>
						<c:if test="${teacherList.pageNow+1 <= teacherList.pageCount }">
							<li><a href="${path}/teacher/teacherList.action?page=${teacherList.pageNow+1 }">下一页</a></li>
						</c:if>
						<li><a href="${path}/teacher/teacherList.action?page=${teacherList.pageCount }">尾页</a></li>
						<li>
							<a>${teacherList.pageNow }/${teacherList.pageCount }</a>
						</li>
						<li>
							<div style="width:-1%; height:100%;float:right;">
								<form action="${path}/teacher/teacherList.action" id="scannerPageForm">
									<input id="scannerPage" type="text" name="page" style="width: 40px; height: 30px; border: 1px solid gray; border-radius: 4px;" />
									<input class="btn btn-default goPage" type="submit" value="Go" style="margin-left: -4px; height: 30px;" />
								</form>
							</div>
						</li>
					</ul>
				</c:if>
			</ul>
		</div>
		<%--<div id="demo2"></div>--%>
	</div>


	<!-- js引入 -->
    <script src="${path }/js/jquery.js"></script>
    <script src="${path }/js/bootstrap/bootstrap.min.js"></script>
    <script src="${path }/js/zeroModal/zeroModal.min.js"></script>
   	<script src="${path }/js/add-update.js"></script>
   	<script src="${path }/js/handle.js"></script>
	<script src="${path }/js/layer/layer.js"></script>
	<script src="${path }/js/layui/layui.js"></script>
	<script src="${path }/js/teacher.js"></script>
   	<script type="text/javascript">
   		$(function() {
   			//管理员点击查看密码
   			$(".viewPwd").click(function() {
   				var pwd0 = $(this).siblings("#hidePwd").text();
   				if (pwd0.indexOf("*") != -1) {
   					var pwd = $(this).siblings("#showPwd").text();
   					$(this).siblings("#hidePwd").text(pwd);
   					return;
   				} else {
   					$(this).siblings("#hidePwd").text("******");
   				}
   			});
   		});

        //分页
        layui.use(['laypage', 'layer'], function() {
            var laypage = layui.laypage
                , layer = layui.layer;

            //自定义样式
            laypage.render({
                elem: 'demo2'
                , count: "${teacherList.teacherCount}"
				, limit: 12
				, curr:'${teacherList.pageNow}'
                , theme: '#1E9FFF'
				, jump: function (obj,first) {
                    /*if(!first){
                        location.href = path+"/teacher/teacherList.action?page="+obj.curr;
                    }*/
                    /*load(obj,curr);*/
                }
            });
        });
        function load(page) {
			$.ajax({
				type:"POST",
				url:path+"teacher/teacherList.action",
				data:{"page":page},
				success:function (data) {
					var html="";
					var tlist=data.result;
					for(var i=0; i < tlist.length; i++){
					    html+="<tr>";
					    html+="<td>"+tlist[i].teacherId+"</td>";
                        html+="<td>"+tlist[i].teacherName+"</td>";
                        html+="<td>"+tlist[i].teacherAccount+"</td>";
                        html+="<td>"+tlist[i].teacherPwd+"</td>";
                        html+="<td>"+tlist[i].adminPower+"</td>";
                        html+="<td>"+tlist[i].isWork+"</td>";
                        html+="</tr>";
					}
					$("#tbdata").html(html);
                }
			});
        }
   	</script>

</body>
</html>