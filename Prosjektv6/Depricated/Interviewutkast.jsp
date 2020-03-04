<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Survey</title>
<link rel="stylesheet" href="./css/furtive.min.css">
<link rel="stylesheet" href="./css/main.css">
</head>
<body>
	<section class="measure p2">
		<c:set var="qNr" value="${questionNr}" />
		<c:set var="q" value="${survey.getQuestions().get(qNr)}" />
		<form action="InterviewStart" method="post" role="form" class="my2">
			<h3>${survey.name}</h3>
			<div class="question">
				<h5>${q.text}</h5>
				<c:choose>
					<c:when test="${q.getType() eq 'mc'}">
						<c:set var="i" value="0" scope="page" />
						<c:forEach var="a" items="${q.answers}">
							<input type="radio" name="answer" value="${i}" />${a.beskrivelse}<br>			
							<c:set var="i" value="${i + 1}" scope="page" />
						</c:forEach>
					</c:when>
					<c:otherwise>
						<textarea name="answer" cols="40" rows="5"></textarea>
					</c:otherwise>
				</c:choose>
			</div>
			<c:if test="${questionNr ne 0 and survey.isTraversable()}">
				<div class="my2">
					<input type="submit" name="submit" value="previous" class="btn--green" />
				</div>
			</c:if>
			<div class="my2">
				<input type="submit" name="submit" value="next" class="btn--green" />
			</div>
		</form>
	</section>
</body>
</html>