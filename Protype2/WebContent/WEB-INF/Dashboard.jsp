<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Dashboard</title>
<link rel="stylesheet" href="./css/furtive.min.css">
<link rel="stylesheet" href="./css/main.css">
</head>
<body>
	<section class="measure p2">
		<form action="Create" method="get" role="form" class="my2">
			<div class="my2">
				<input type="submit" value="Create new survey" class="btn--green" />
			</div>
		</form>
		<c:forEach var="surveys" items="${surveys}">
			<fieldset>
				<legend>${surveys.getNavn()}</legend>
				<p>Survey link:
					http://localhost:8080/PrototypeProsjekt/InterviewHome?surveyName=${surveys.getNavn().replaceAll("\\s","+")}</p>
				<form action="InterviewHome" method="get" role="form" class="my2">
					<div class="my2">
						<input type="submit" value="Start survey" class="btn--green" /> <input
							type="hidden" name="surveyName" value="${surveys.getNavn()}" />
					</div>
				</form>
				<form action="SurveyResults" method="get" role="form" class="my2">
					<div class="my2">
						<input type="submit" value="Survey results" class="btn--green" />
						<input type="hidden" name="surveyName"
							value="${surveys.getNavn()}" />
					</div>
				</form>
			</fieldset>
		</c:forEach>
	</section>
</body>
</html>