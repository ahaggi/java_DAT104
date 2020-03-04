<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<style>
div.write {
	position: relative;
	width: 48%;
	height: 500px;
	border: 3px solid blue;
	float: right;
	right: 10px;
	overflow: scroll;
}

div.read {
	position: relative;
	width: 48%;
	height: 500px;
	border: 3px solid blue;
	left: 10px;
	overflow: scroll;
}
#timer{
	text-align:center;font-size:30px
}
</style>

<script>
function bg_color_focus(x){
	var write_elem = document.getElementById(x);
	var read_elem = document.getElementById("b"+x);
	
	write_elem.style.background = "yellow";
	write_elem.scrollIntoView();
	
	read_elem.style.background = "yellow";
	read_elem.scrollIntoView();
}

function bg_color_blur(x){
	var write_elem = document.getElementById(x).style.background = "none";
	var read_elem = document.getElementById("b"+x).style.background = "none";
	check(x);
}

function check(x){
	//TODO sjekk om EOF
	var write_element = document.getElementById(x);
	var read_element = document.getElementById("b"+x);
	
	var write_txt = write_element.innerHTML.replace(/&nbsp;/gi,'').trim();
	var read_txt = read_element.innerHTML.replace(/&nbsp;/gi,'').trim();

	if (write_txt != read_txt) {
		write_element.style.color = "red";
		read_element.style.color = "red";
		return false;
	}else{
		write_element.style.color = "black";
		read_element.style.color = "black";
		return true;
	}
	
}
	

function next_elem(e, x) {
	var flag = false;

	if (e.which === 13) {
		e.preventDefault(); 
// 			var aaa = document.getElementById("b" + x).textContent;
//  			if (aaa.match(/"↷"/g)) {
			var elem = document.getElementById(x);
			elem.innerHTML = "&#8631;"

			var br_elem = document.createElement("br");
			var par = document.getElementById(x).parentNode;
			par.appendChild(br_elem);
			flag = true;
// 			}
	}

	if (e.which === 32 || flag) {
		var nextElem = document.getElementById(x + 1);
		if (nextElem === null) {
			var nextElem = document.createElement("span");
			var id = x + 1;
			nextElem.setAttribute("contentEditable", "true");
			nextElem.setAttribute("onkeypress", "next_elem(event," + id
					+ ")");
			nextElem.setAttribute("id", id);
			nextElem.setAttribute("background-color", "yellow");
			nextElem.setAttribute("onfocus", "bg_color_focus(" + id + ")");
			nextElem.setAttribute("onblur", "bg_color_blur(" + id + ")");

			var par = document.getElementById(x).parentNode;
			par.appendChild(nextElem);
		}
		
		nextElem.focus();

	}

}


//Set the startime global var
var start = new Date().getTime();
setInterval(timer , 1000);

function timer() {
    var now = new Date().getTime();
    
    var cnt = now - start ; //OBS cnt er i ms
    
    // Time calculations for days, hours, minutes and seconds
    var minutes = Math.floor((cnt % (1000 * 60 * 60)) / (1000 * 60));
    var seconds = Math.floor((cnt % (1000 * 60)) / 1000);
    
    // Output the result in an element with id="demo"
    document.getElementById("timer").innerHTML =   minutes + "m " + seconds + "s ";
 
}

</script>

</head>
<body>

	<p>
		<font color="red">${feilMelding}</font>
	</p>

	<div id="write" class="write">
		<span contentEditable="true" onkeypress="next_elem(event,1)"
			onfocus="bg_color_focus(1)" onblur="bg_color_blur(1)" id="1">e</span>
	</div>


	<div id="read" class="read">
		<c:set var="ind" value="1" />
		<c:forEach var="setning" items="${leksjon.setninger}">
			<c:forEach var="ord" items="${setning.ord}">
				<c:if test="${not empty ord}">
					<span id="b${ind}"><c:out value="${ord}"/></span>
					<c:set var="ind" value="${ind+1}" />
				</c:if>
			</c:forEach>
			<span id="b${ind}">&#8631;</span>
			<c:set var="ind" value="${ind+1}" />

			<br />
		</c:forEach>

	</div>



	<form action="chooseFile" method="get">
		<p>
			<input type="submit" value="Return" />
		</p>
	</form>
	<p id="timer" >0m 0s</p>
	
	

</body>
</html>