<%@ page import="java.util.Date" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%--<%@ page import="java.lang.String" %>--%>
<html>
<head>
    <title><%=pageTitle%></title>
</head>
<body>
<%!
    int i = 1;
    String pageTitle = "错误";
%>
<%
    String username = (String)request.getAttribute("username");
    String pass = (String)request.getAttribute("password");
    System.out.println();
%>
<table border="1">
    <thead>
        <tr>
            <td>
                type
            </td>
            <td>
                username
            </td>
            <td>
                password
            </td>
        </tr>
    </thead>
    <tbody>
        <tr>
            <td>
                from Request
            </td>
            <td>
                <%
                    request.getAttribute("username");
                %>
            </td>
            <td>
                <%
                    request.getAttribute("password");
                %>
            </td>
        </tr>
        <tr>
            <td>
                from <\%= %>
            </td>
            <td>
                <%=username%>
            </td>
            <td>
                <%=pass%>
            </td>
        </tr>
    </tbody>
</table>
<h4>
    time:<%=(new Date().toInstant().toEpochMilli())%>
</h4>
</body>
</html>
