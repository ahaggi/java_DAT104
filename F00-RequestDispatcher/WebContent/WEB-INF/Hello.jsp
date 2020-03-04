

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>


	<%
		for (int i = 0; i < 5; i++) {
			out.println("Hello </br>");
		}
	%>


	<c:set var="x" value="${1}" />
	<c:set var="y" value="${2}" />
	<c:set var="z" value="${3}" />
	<c:set var="sum" value="${(x + y)*z}" />
	<p>${sum}</p>

</body>
</html>