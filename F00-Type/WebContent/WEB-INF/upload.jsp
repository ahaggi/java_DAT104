<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<!DOCTYPE html>
<html>
<head>
<title>File Uploading Form</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">

<script type="text/javascript">
	function addElem() {
		//		var elem = document.createElement("INPUT");
		//		elem.setAttribute("type", "file");
		//		elem.setAttribute("size", "50");
		//		eller

		var p_id = document.getElementsByTagName("p").length;
		var elem = document.createElement("p");
		elem.setAttribute("id", p_id);

		elem.innerHTML = " <input type='file' name='file'  size='50' required /> "
						+"<i class='fa fa-remove' onclick='remove("+p_id+")' style='color:red'></i>";
//				 		var par = document.getElementById("sub_button").parentNode;
		var par = document.getElementsByClassName("parent");
		var sub = document.getElementById("sub_button");
		par[0].insertBefore(elem, sub); //getElementsByClassName returns an elements-array
		sub.scrollIntoView();
	}
	function remove(id) {
		var parent = document.getElementById(id).parentNode;
		var child = document.getElementById(id);
		parent.removeChild(child);
	}
</script>
</head>
<body>
	<h3>File Upload:</h3>
	Select a file to upload:
	<br />
	<div >
 		<form action="upload" method="post" enctype="multipart/form-data">
			<fieldset class="parent">
				<input type="file" name="file" size="50" required/> <br /> <input
					type="submit" value="Upload File" id="sub_button" /> <input type="button"
					value="Add more files" onclick="addElem()" />
			</fieldset>
		</form>

	</div>
</body>
</html>
