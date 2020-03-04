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

						<!-- Error! -->
						<c:if test="${not empty errorMsg}">
							<div class="notification is-danger">${errorMsg}</div>
						</c:if>

						<!-- We have an interview -->

						<form action="interview" method="post">

							<h1 class="subtitle">Question
								${processAnswerFilter.questionNr + 1} of
								${processAnswerFilter.getAmountOfQuestions()}</h1>
							<h1 class="title">${question.text}</h1>

							<c:if test="${question.getType() eq 'mc'}">
								<c:forEach var="option" items="${question.options}"
									varStatus="loop">
									<c:set var="ckd"
										value="${processAnswerFilter.isMCAnswercontain(loop.index)  ? 'checked' : ''}" />
									<p class="control">
										<label class="checkbox"> <c:set var="optionType"
												value="${question.singleAnswer ? 'radio' :'checkbox'}" /> <input
											class="" type="${optionType}" name="options_answer"
											value="${loop.index}" ${ckd } /> ${option}
										</label>
									</p>
								</c:forEach>
							</c:if>

							<c:if test="${question.getType() eq 'tq'}">
								<p class="control">
									<textarea class="textarea" name="text_answer" maxlength="450"
										required><c:out	value="${processAnswerFilter.getTAnswerInhold()}"	escapeXml="false" /></textarea>
									Max length 450 characters
								</p>
							</c:if>

							<p class="control">
								<input class="button" type="submit" name="Action"
									value="Previous"
									${processAnswerFilter.hasPrevious_Question() && processAnswerFilter.isTraversable() ? '' : 'disabled' } />
								<input class="button is-primary" type="submit" name="Action"
									value="Next" />
								<span class="control is-pulled-right" id="timer"></span>
							</p>
						</form>
						<%--  	            <c:set var="optionType" value="${question.singleAnswer ? 'radio' : 'checkbox'}" /> --%>
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
	
	
	<!-- Scripts -->
	<script type="text/javascript" src="./js/countdowntimer.js"></script>
</body>
</html>