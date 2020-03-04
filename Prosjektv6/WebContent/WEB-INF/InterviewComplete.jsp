<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="ISO-8859-1">
        <title>${not empty processAnswerFilter.name ? processAnswerFilter.name : 'Survey'}</title>
        <meta name="description" content="">
        <meta name="author" content="">

        <meta name="viewport" content="width=device-width, initial-scale=1">
        
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.6.3/css/font-awesome.min.css">
        <link rel="stylesheet" href="./css/bulma.css">
	</head>
	<body>
		<section class="hero is-fullheight">
		  	<div class="hero-body">
				<div class="container">
					<div class="columns is-vcentered">
				        <div class="column is-8 is-offset-2">
				        	
					<%-- 					
					<c:forEach var="question" items="${survey.questions}" > 
						<p>${question.text}</p>
							<c:forEach var="answer" items="${question.answers}" >
							
									<c:if test="${question.getType() eq 'tq'}">
										<p>${answer.text}</p>
									</c:if>
								
									<c:if test="${question.getType() eq 'mc'}">
										<c:forEach var="answeredoption" items="${answer.options}">
											<p>${question.options.get(answeredoption)}</p>
										</c:forEach>
										
									</c:if>


							</c:forEach>


					</c:forEach>
					--%>
						
						<!-- Status! -->
						<c:choose>
						    <c:when test="${status eq true}">
						        <div class="notification is-success">
									Your response has been saved!
								</div>
						    </c:when>
						    <c:when test="${status eq false}">
						        <div class="notification is-danger">
									An error occured. Your response may not have been saved.
								</div>
						    </c:when>
						    <c:when test="${not empty processAnswerFilter}">
						        <h1 class="title">Submit</h1>
						        <h1 class="subtitle">You have completed the survey. Click submit to send your response.</h1>
						    </c:when>
						    <c:otherwise>
						        <div class="notification is-warning">
									An error occured. You have not completed a survey.
								</div>
						    </c:otherwise>
						</c:choose>
						
						<c:if test="${not empty processAnswerFilter}">
							<form action="interviewcomplete" method="post" role="form">
								<p class="control">
									<button class="button" type="submit" name="Action" formaction="interview" value="Back" ${processAnswerFilter.hasPrevious_Question() ? '' : 'disabled' }>Back</button>
									<button class="button is-primary" type="submit" value="Submit">Submit</button>
								</p>
							</form>
						</c:if>
					</div>
				</div>
			</div>
		</div>
	</section>
	
	<!-- FOOTER -->
	<footer class="footer">
	    <div class="container">
	        <div class="content has-text-centered">
	            <p>
	                Get <strong>RUP</strong>'d&trade;
	            </p>
	        </div>
	    </div>
	</footer>
</body>
</html>
