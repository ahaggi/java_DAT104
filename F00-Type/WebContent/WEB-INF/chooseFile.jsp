<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<!DOCTYPE html>
<html>
<head>

<title>choose a lesson</title>
<script type="text/javascript">
function check() {	
	 var radios = document.getElementsByName('fileName'); 
	 var b=true; 
	 for (var i = 0; i < radios.length && b; i++) {
		 if (radios[i].checked)	 b=false ;
		 }
	 document.getElementById('submit').disabled=b;
	 }

</script>  
 </head>
<body onload="check()">
	<h3>Select a file:</h3>
 	<br />
	<div>
	<p><font color="red">${feilMelding}</font></p>
	
 		<form action="chooseFile" method="post">
			<fieldset>

				<c:forEach var="file" items="${filesList}" begin="0" varStatus="ind">
 						<input type ="radio" name="fileName" value="${file}"  onclick="check()"/>${ind.index+1} - ${file}<br/>
   				</c:forEach>

				<input type="submit" id="submit" value="Start"	/>
				<input type="submit" value="upload more files"	formaction="upload" formmethod="get" />
			</fieldset>
		</form>

	</div>
</body>
</html>
