<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%String test = request.getParameter("test");
    test = (String)request.getAttribute("test");
    System.out.print(test);
    %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>

</head>
<body>
<input type="text" value="<%=test %>"/><a href="demo?search=hello"></a>
<div><%=test %></div>

</body>
</html>

<!-- <div><script>alert()</script></div> -->