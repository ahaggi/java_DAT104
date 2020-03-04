<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
     
<%@ page import="modell.UrlMappings" %>
 <!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

<p><font color="red">${feilMelding}</font></p>
<h2>Logg inn</h2>
<p>Det er kun registrerte deltagere som f&#229r se deltagerlisten.
Logg inn ved &#229 gi mobil-nummeret ditt.</p>
<form action="${LOGGINN_DELTAGERE_SERVLET}" method ="post">
  <fieldset>
    <legend>Logg inn</legend>
    <p>Mobil: <input type="password" name="mobil" /></p>
    <p><input type="submit" value="Logg inn" /></p>
  </fieldset>
</form>


</body>
</html>