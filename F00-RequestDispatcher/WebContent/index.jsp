<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Forms</title>
</head>
<body>
	<form action="register" method="get">
		<fieldset>
			<legend>Persondata </legend>
			<p>
				Fornavn: <input type="text" name="fornavn" />
			</p>
			<p>
				Etternavn: <input type="text" name="etternavn" />
			</p>
			<p>
				<input type="submit" value="Send data" />
			</p>
		</fieldset>
	</form>

	<%
		for (int i = 0; i < 5; i++) {
			out.println("Hello </br>");
		}
	%>



	<%-- <% skriv kode her%> , men det er ønskelig pga  compilator,,, les mer
		jsp legger inn i WEB-INF siden alle som legger der er ikke tilgjenglig for brukeren,, så brukeren ikke skriver url direkte.
		det finns ferdig laget variables som out.printlnl , req ,resp ,,, ogv
--%>


</body>
</html>
