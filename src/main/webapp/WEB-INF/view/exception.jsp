<%@ page contentType="text/html;charset=UTF-8" language="java" isErrorPage="true" %>
<html>
<head>
    <title>异常页面</title>
</head>
<body>
<%-- <% Exception ex = (Exception) request.getAttribute("Exception");%> --%>
<%-- <H2>Exception:<%=ex.getMessage()%> </H2> --%>
<h1>出现异常</h1>
<h2>错误信息</h2>
<h4><%=exception.getMessage() %></h4>
</body>
</html>