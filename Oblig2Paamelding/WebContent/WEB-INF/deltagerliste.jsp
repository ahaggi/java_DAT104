<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

<h2>Deltagerliste</h2>
<table border="1">
<tr bgcolor="#cccccc"><th>Kj&#248nn</th><th align="left">Navn</th></tr>

    <c:forEach var="p" items="${personerListe}">

			<c:set var="bgfarge" value="#ffffff" />
			<%-- RESET FARGEN FOR HVER DELTAGER --%>

			<c:if test="${innloggetBruker eq p.mobil}">
				<c:set var="bgfarge" value="${p.betalingStatus ? '#aaffaa' : '#ffaaaa'}" />
			</c:if>


			<tr bgcolor="${bgfarge}">
				<td align="center">${p.kjonnsymbol}</td>
				<td>${p.fornavn} ${p.etternavn} </td>
			</tr>
			
		</c:forEach>

</table>
<p><a href="loggut">Ferdig</a></p>


</body>
</html>