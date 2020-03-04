<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

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
						<!-- Error! -->
						<c:if test="${not empty error}">
							<div class="notification is-danger">
								${error}
							</div>
						</c:if>
						
						<!-- We have a survey! -->
						<c:if test="${not empty processAnswerFilter}">
							<h1 class="title">${processAnswerFilter.name}</h1>
							<h2 class="subtitle">${processAnswerFilter.description}</h2>
							<p class="control">
								<form action="interviewstart" method="post">
									<button class="button is-primary" type="submit" value="Start" >Start</button>
								</form>
							</p>
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
