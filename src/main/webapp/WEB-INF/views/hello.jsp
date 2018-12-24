<%@ page import="com.example.demo.dto.User" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Hello</title>
</head>
<body>
<%
    Object o = request.getAttribute("user");
    User user = (User)o;
%>
user.toString:<%=user%>
</body>
</html>
