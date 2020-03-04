<%@ page import="static no.hib.dat104.lph.UrlMappings.LOGIN_URL"%>
<%@ page contentType="text/html; charset=ISO-8859-1"%>
<%@ page session="false"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Login</title>
</head>
<body>
<p><font color="red"><%= request.getAttribute("redirectErrorMessage") %></font></p>
<form action="<%= LOGIN_URL %>" method="post">
  <fieldset>
    <legend>Login</legend>
    <p>Navn: <input type="text" name="username" /></p>
    <p><input type="submit" value="Logg inn" /></p>
  </fieldset>
</form>

</body>
</html>