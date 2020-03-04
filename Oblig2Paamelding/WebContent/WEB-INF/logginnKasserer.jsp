<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>


<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

<p><font color="red">${feilMelding}</font></p>
<h2>Kasserer login</h2>
<form action="logginnKasserer" method ="post">
  <fieldset>
    <legend>Kasserer login</legend>
    
    <p>Passord: <input type="password" name="password" /></p>
    <p><input type="submit" value="Logg inn" /></p>
  </fieldset>
</form>


</body>
</html>