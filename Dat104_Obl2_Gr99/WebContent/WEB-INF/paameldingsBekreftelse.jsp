<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
    
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>


<h2>P&#229meldingsbekreftelse</h2>
<p>P&#229meldingen er mottatt for</p>
<p>${paameldte.fornavn} ${paameldte.etternavn}</p>
<p>Mobil nr: ${paameldte.mobil}</p>
<p>kj&#248nn   : ${paameldte.kjonn}</p>
 
<c:if test="${not paameldte.betalingStatus}">
	<p><b>NB! Husk &#229 betale til kassereren f&#248r festen!</b></p>
</c:if>
<a href="deltagerliste">G� </a>


</body>
</html>