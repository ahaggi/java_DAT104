<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Spørsmål</title>
<link rel="stylesheet" href="./css/furtive.min.css">
<link rel="stylesheet" href="./css/main.css">
</head>
<body>
	<section class="measure p2">
		<c:set var="qNr" value="${questionNr}" />
		<c:set var="q" value="${survey.getQuestions().get(qNr)}" />
		<form action="InterviewStart" method="post" role="form" class="my2">
			<h3>${survey.getNavn()}</h3>
			<p class="h4">A description might be useful.. Should we include
				one? This is just some dummy text. Is this just a dummy sentence?
				Wow.. It really is a dummy sentence.</p>
			<c:set var="i" value="0" scope="page" />
			<div class="question">
				<h5>${q.beskrivelse}</h5>
				<c:forEach var="a" items="${q.alternatives}">

					<input type="radio" name="stemme" value="${i}" />${a.beskrivelse}<br>
				
				<c:set var="i" value="${i + 1}" scope="page" />
				</c:forEach>
			</div>
			<div class="my2">
				<input type="submit" value="Neste" class="btn--green" />
			</div>
		</form>
	</section>
</body>
</html>