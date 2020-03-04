<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="ISO-8859-1">
	<title>Error ${pageContext.errorData.statusCode}</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    
    <link rel="stylesheet" href="./css/bulma.css">
</head>
<body>
    <h1 class="title">Ooops! Something went wrong ...</h1>
    <p><strong>Status Code</strong>:${pageContext.errorData.statusCode}</p>
    <p><strong>Requested URI</strong>:${pageContext.errorData.requestURI}</p>
    <p><strong>Exception</strong>:${pageContext.exception}</p>  
</body>
</html>