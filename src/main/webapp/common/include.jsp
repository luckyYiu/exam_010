<%--
  Created by IntelliJ IDEA.
  User: Lenovo
  Date: 2019/4/11
  Time: 11:12
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" isELIgnored="false" language="java" %>
<%
    String path = request.getContextPath();
// 获得项目完全路径（假设你的项目叫MyApp，那么获得到的地址就是 http://localhost:8080/MyApp/）:
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
    request.setAttribute("path",basePath);
%>
<script language="JavaScript" type="application/javascript">
    var path = '${path}';
</script>
