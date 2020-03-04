<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"  pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

<form action="paamelding" method="post">
  <fieldset>
    <legend>Personlige data</legend>
    
    <p>Fornavn: <input type="text" name="fornavn" value="${ps.fornavn}" />    <font color="red">${ps.fornavnFeilmelding}</font></p>
    <p>Etternavn: <input type="text" name="etternavn"  value="${ps.etternavn}" />    <font color="red">${ps.etternavnFeilmelding}</font></p>
    <p>Mobil (8 siffer): <input type="text" name="mobil"  value="${ps.mobil}" />    <font color="red">${ps.mobilFeilmelding}</font></p>
 
            <c:set var="mannChecked" value="${ps.kjonn eq 'mann' ? 'checked' : ''}" />
            <c:set var="kvinneChecked" value="${ps.kjonn eq 'kvinne' ? 'checked' : ''}" />

 
    <p>Kjønn: <input type="radio" name="kjonn" value="mann"    ${mannChecked}  />mann
              <input type="radio" name="kjonn" value= "kvinne" ${kvinneChecked}  />kvinne <font color="red">${ps.kjonnFeilmelding}</font></p>
        
    <p><input type="submit" value="Meld meg på" /></p>
  </fieldset>
	
</form>

</body>
</html>