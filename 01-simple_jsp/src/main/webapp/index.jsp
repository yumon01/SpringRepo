<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Simple JSP</title>
</head>
<body>
	<%
		String name=request.getParameter("firstName");
		if(name!=null && !name.trim().equals("") ){
		
	%>
	Hello <%=name %>
	Welcome to the Page.
	
	<%
		}
		else{
	%>
	<form action="welcome.jsp" method="get">
		Type your name..
		<input type="text" name="firstName" placeholder="Enter your first name">
		<input type="submit" value="submit">
	</form>
	<%
		}
	%>
</body>
</html>