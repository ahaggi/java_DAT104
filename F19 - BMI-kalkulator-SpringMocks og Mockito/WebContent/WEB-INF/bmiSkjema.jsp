<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page contentType="text/html; charset=ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
    <title>BMI-kalkulator</title>
</head>
<body>
    <h1>BMI-kalkulator</h1>
    <p><b><font color="red"><span id="feilmelding">${feilmelding}</span></font></b></p>
    <form action="bmiResultat"><fieldset>
        <table>
            <tr><td>Høyde</td>
                <td><input type="text" name="hoyde" /></td><td>cm</td></tr>
            <tr><td>Vekt</td>
                <td><input type="text" name="vekt" /></td><td>kg</td></tr>
        </table>
        <p><input type="submit" value="Beregn" /></p>
    </fieldset></form>
</body>
</html>

<!-- <span id="feilmelding">${feilmelding}</span> -->