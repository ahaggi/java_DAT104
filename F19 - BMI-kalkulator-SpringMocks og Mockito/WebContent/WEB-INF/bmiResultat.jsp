<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page contentType="text/html; charset=ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
    <title>BMI-kalkulator resultat</title>
</head>
<body>
    <h1>BMI-kalkulator resultat</h1>
    <p><b>Din BMI er <span id="bmi">${bmi}</span></b> (${param.hoyde} cm, ${param.vekt} kg)</p>
    <p>Dette regnes av WHO som <b> ${vektklasse.toString()}.</b></p>
    <p><a href="bmiSkjema">Beregn en gang til</a></p>
</body>
</html>

<!-- <span id="bmi">${bmi}</span> -->
<!-- <span id="vektklasse">${vektklasse.toString()}</span> -->