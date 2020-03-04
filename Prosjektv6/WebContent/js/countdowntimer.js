var enddate = readCookie("ENDDATE");
var timeleft = enddate - Date.now();
var seconds = timeleft / 1000;
var label = "Remaining time: ";
var timerElement = document.getElementById('timer');

function timer() {
    var days        = Math.floor(seconds/24/60/60);
    var hoursLeft   = Math.floor((seconds) - (days*86400));
    var hours       = Math.floor(hoursLeft/3600);
    var minutesLeft = Math.floor((hoursLeft) - (hours*3600));
    var minutes     = Math.floor(minutesLeft/60);
    var remainingSeconds = seconds % 60;
    if (remainingSeconds < 10) {
        remainingSeconds = "0" + remainingSeconds; 
    }
    if(days == 0){
    	if(hours == 0){
    		if(minutes == 0){
    			timerElement.innerHTML = label + Math.floor(remainingSeconds) + " seconds";
    		}
    		else{
    			timerElement.innerHTML = label + minutes + " minutes, " + Math.floor(remainingSeconds) + " seconds";
    		}
    	}
    	else{
    		timerElement.innerHTML = label + hours + " hours, " + minutes + " minutes, " + Math.floor(remainingSeconds) + " seconds";
    	}
    }
    else{
    	timerElement.innerHTML = label + days + " days, " + hours + " hours, " + minutes + " minutes, " + Math.floor(remainingSeconds) + " seconds";
    }
    if (seconds <= 0) {
        clearInterval(countdownTimer);
        timerElement.innerHTML = "Time is up!";
    } else {
        seconds--;
    }
}
var countdownTimer = setInterval(timer, 1000);

function readCookie(name) {
    var nameEQ = name + "=";
    var ca = document.cookie.split(';');
    for(var i=0;i < ca.length;i++) {
        var c = ca[i];
        while (c.charAt(0)==' ') c = c.substring(1,c.length);
        if (c.indexOf(nameEQ) == 0) return c.substring(nameEQ.length,c.length);
    }
    return null;
}