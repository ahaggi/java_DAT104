<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Create</title>
<link rel="stylesheet" href="./css/furtive.min.css">
<link rel="stylesheet" href="./css/main.css">
</head>
<body>
	<section class="measure p2">
		<form action="Create" method="post" role="form" class="my2">
		<c:if test="${empty survey.navn}">
			<p>
				Navn på survey: <input type="text" name="navn" required />
			</p>
		</c:if>
			<fieldset>
				<legend>Spørsmål 1</legend>
				<p>
					Spørsmål: <input type="text" name="sporsmaal" />
				</p>
				<p>
					Alternativ 1: <input type="text" name="alternativ1" />
				</p>
				<p>
					Alternativ 2: <input type="text" name="alternativ2" />
				</p>
				<div class="my2">
					<input type="submit" value="Legg til spørsmål" formaction="AddQuestion" />
				</div>
				<div class="my2">
					<input type="submit" value="Ferdig" />
				</div>
			</fieldset>
		</form>
	</section>
</body>
</html>