<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta  http-equiv="Refresh" content="5;url=home.html">
<title>Insert title here</title>
</head>
<body>

<h1>Ferdig</h1>
<p>Du er n&#229 logget ut.</p>

<p>Du kan logg inn p� nytt ved &#229 g&#229 <a href="${omdirigerTil}">hit</a>.</p>
<br/>
<p>Eller du kommer til � bli sendt til home automatisk..</p>
<%-- //Kunne brukt <c:redirect url="/home.html"/> --%>

</body>
</html>

