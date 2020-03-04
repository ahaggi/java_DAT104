<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

	<h2>Betalingsoversikt</h2>
	<table border="1">
		<tr bgcolor="#cccccc">
			<th align="left">Navn</th>
			<th>Mobil</th>
			<th>Betalingsstatus</th>
		</tr>


		<c:forEach var="p" items="${personerListe}" first="q">
			<tr>
				<td>${p.fornavn}</td>
				<td>${p.mobil}</td>

				<td><c:if test="${not p.betalingStatus}">

						<form action="betalingsOversikt" method="post">
							<input type="hidden" name="mobilNr" value="${p.mobil}" /> <input
								type="submit" value="Registrer betaling" />
						</form>

					</c:if> <c:if test="${ p.betalingStatus}">
Betaling mottatt
</c:if></td>

			</tr>
		</c:forEach>




	</table>
	<p>
		<a href="loggut">Ferdig</a>
	</p>



</body>
</html>