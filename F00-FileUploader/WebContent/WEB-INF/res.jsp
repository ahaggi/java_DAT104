<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"    pageEncoding="ISO-8859-1"%>
     
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

<p><font color="red">${feilMelding}</font></p>

<c:forEach var="filnavn" items="${uploadedFiles}">
	<p>${filnavn}</p>
</c:forEach>

<form action="upload" method="get">
<p><input type="submit" value="Return" /></p>
</form>

</body>
</html>