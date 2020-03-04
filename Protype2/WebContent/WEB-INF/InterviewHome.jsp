<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Start</title>
<link rel="stylesheet" href="./css/furtive.min.css">
<link rel="stylesheet" href="./css/main.css">
</head>
<body>
	<section class="measure p2">
		<form action="InterviewStart" method="get" role="form" class="my2">
			<fieldset>
				<legend>${survey.getNavn()}</legend>
				<p class="h4">A description might be useful.. Should we include
					one? This is just some dummy text. Is this just a dummy sentence?
					Wow.. It really is a dummy sentence.</p>
				<div class="my2">
					<input type="submit" value="Start" class="btn--green" />
				</div>
			</fieldset>
		</form>
	</section>
</body>
</html>