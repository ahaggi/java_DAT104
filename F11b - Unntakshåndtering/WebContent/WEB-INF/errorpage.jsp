<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Error page</title>
</head>
<body>

    <h1>Ooops! Something went wrong ...</h1>
    <strong>Status Code</strong>:${pageContext.errorData.statusCode}<br>
    <strong>Requested URI</strong>:${pageContext.errorData.requestURI}<br>
    <strong>Exception</strong>:${pageContext.exception}<br><br>
    <p><a href="index.html">Back to front page</a></p>    
</body>
</html>