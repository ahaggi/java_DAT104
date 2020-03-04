<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Resultat</title>
<link rel="stylesheet" href="./css/furtive.min.css">
<link rel="stylesheet" href="./css/main.css">
</head>
<body>
	<section class="measure p2">
		<h3>Resultat</h3>
		<c:forEach var="q" items="${survey.getQuestions()}">
			<fieldset>
				<legend>${q.beskrivelse}</legend>
				<c:forEach var="a" items="${q.alternatives}">
					<p>${a.beskrivelse}:${a.antallStemmer}</p>
				</c:forEach>
			</fieldset>
		</c:forEach>
	</section>
</body>
</html>