<%@ page contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Personskjema</title>
</head>
<body>
<form method="post">
<fieldset><legend>Personlige data</legend>
<p>Navn: <input type="text" name="navn"
    value="${ps.navn}" />
    <font color="red">${ps.navnFeilmelding}</font>
</p>
<p>Postnummer: <input type="text" name="postnr"
    value="${ps.postnr}" />
    <font color="red">${ps.postnrFeilmelding}</font>
</p>
<p><input type="submit" value="Submit" /></p>
</fieldset>
</form>
</body>
</html>