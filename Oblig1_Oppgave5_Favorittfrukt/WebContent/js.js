	
 function check() {	
	 var radios = document.getElementsByName('valg'); 
	 var flag=0; 
	 for (var i = 0; i < radios.length && flag==0; i++) {
		 if (radios[i].checked)	 flag=1;
		 }
	 document.getElementById('stem').disabled=(flag==0)?true:false;}
