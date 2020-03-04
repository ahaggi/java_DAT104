<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="ISO-8859-1">
    <title>Editor</title>
    <meta name="description" content="">
    <meta name="author" content="">

    <meta name="viewport" content="width=device-width, initial-scale=1">

    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.6.3/css/font-awesome.min.css">
    <link rel="stylesheet" href="./css/bulma.css">
</head>
<body>
	<section class="hero">
        <div class="hero-body">
            <div class="container">
                <p class="title">Editor</p>
                <p class="subtitle">Modify your survey.</p>
            </div>
        </div>
    </section>
    
    <form action="editor" method="post" role="form">
        <!-- SURVEY FIELDS -->
        <section class="section">
            <div class="container">
                <label class="label">Name</label>
                <p class="control <c:if test="${not empty errorName}">has-icon has-icon-right</c:if>">
                    <input class="input <c:if test="${not empty errorName}">is-danger</c:if>" type="text" name="name" placeholder="My survey" value="${survey.name}">
                   	<c:if test="${not empty errorName}">
                    	<i class="fa fa-warning"></i>
  						<span class="help is-danger">${errorName}</span>
  					</c:if>
                </p>

                <label class="label">Description</label>
                <p class="control">
                    <textarea class="textarea <c:if test="${not empty errorDescription}">is-danger</c:if>" name="description">${survey.description}</textarea>
  					<span class="help is-danger">${errorDescription}</span>
                </p>

                <label class="label">Duration (hh:mm)(--,-- and 00:00 is infinite)</label>
                <p class="control">
                    <input type="time" name="length" value="${survey.fetchFormatedLength()}">
                </p>

                <label class="label">Repeatable (Participants can take the survey several times)</label>
                <p class="control">
                     <label class="checkbox">
                         <input class="checkbox" type="checkbox" name="repeatable" value="repeatable" <c:if test="${survey.repeatable}">checked</c:if> />
                         Repeatable
                     </label>
                </p>

                <label class="label">Traversable (Participants can alter previous questions)</label>
                <p class="control">
                     <label class="checkbox">
                         <input type="checkbox" name="traversable" value="traversable" <c:if test="${survey.traversable}">checked</c:if> />
                         Traversable
                     </label>
                </p>
            </div>
        </section>
    
        <!-- QUESTIONS -->
        <section class="section">
            <div class="container">
                <h1 class="title">Questions</h1>
                <hr>

                <c:forEach var="question" items="${survey.questions}" varStatus="loop">
                    <c:if test="${question.getType() eq 'mc'}">
                        <div class="box">
                            <nav class="level">
                                <!-- Left side -->
                                <div class="level-left">
                                    <div class="level-item">
                                        <h1 class="title is-4">Question ${loop.index + 1}</h1>
                                        <br>
                                        <label class="label">Single Answer (Participants can only choose one answer)</label>
               							<p class="control">
                     						<label class="checkbox">
                         						<input type="checkbox" name="singleAnswer" value="singleAnswer" <c:if test="${question.singleAnswer}">checked</c:if> />
                         						Single Answer
                     						</label>
                						</p>
                                    </div>
                                </div>

                                <!-- Right side -->
                                <div class="level-right">
                                    <div class="level-item control">
                                        <button class="button is-danger" type="submit" name="removeQuestion" formaction="RemoveQuestion" value="Remove Question${loop.index + 1}">
		                                    <span>Remove</span>
		                                </button>
                                    </div>
                                </div>
                            </nav>
                            <label class="label">Text</label>
                            <c:set var="eText" value="errorText${loop.index}" />
                            <p class="control <c:if test="${not empty sessionScope[eText]}">has-icon has-icon-right</c:if>">
                                <input class="input <c:if test="${not empty sessionScope[eText]}">is-danger</c:if>" type="text" name="text${loop.index}" placeholder="Is you a dog?" value="${question.text}" <c:if test="${loop.last && autoFocus eq -1 || autoFocus eq loop.index && question.options.isEmpty()}">autofocus</c:if> />
                                <c:if test="${not empty sessionScope[eText]}">
                    				<i class="fa fa-warning"></i>
  									<span class="help is-danger">${sessionScope[eText]}</span>
  								</c:if>
                            </p>
                            <nav class="level">
                                <!-- Left side -->
                                <div class="level-left">
                                    <div class="level-item">
                                        <h1 class="title is-4">Options</h1>
                                    </div>
                                </div>

                                <!-- Right side -->
                                <div class="level-right">
                                    <div class="level-item control">
                                        <button class="button" type="submit" name="addOption" formaction="AddOption" value="Add option to question ${loop.index + 1}">
		                                    <span>Add option</span>
		                                </button>
                                    </div>
                                </div>
                            </nav>
                            <c:forEach var="option" items="${question.options}" varStatus="loop2">
                                <label class="label">Option ${loop2.index + 1}</label>
                                <c:set var="eOption" value="errorOption${loop2.index}${loop.index}" />
                                <p class="control has-addons">
                                    <input class="input <c:if test="${not empty sessionScope[eOption]}">is-danger</c:if>" type="text" name="option${loop2.index}${loop.index}" value="${option}" <c:if test="${loop2.last && autoFocus eq loop.index}">autofocus</c:if> />
                                    
                                    <button class="button is-danger" type="submit" name="removeOption" formaction="RemoveOption" value="${loop2.index + 1} ${loop.index + 1}">
                                        <span>Remove</span>
                                    </button>
                                </p>
                                <c:if test="${not empty sessionScope[eOption]}">
  									<span class="help is-danger">${sessionScope[eOption]}</span>
  								</c:if>
                            </c:forEach>
                        </div>
                    </c:if>

                    <c:if test="${question.getType() eq 'tq'}">
                    <div class="box">
                            <nav class="level">
                                <!-- Left side -->
                                <div class="level-left">
                                    <div class="level-item">
                                        <h1 class="title is-4">Question ${loop.index + 1}</h1>
                                    </div>
                                </div>

                                <!-- Right side -->
                                <div class="level-right">
                                    <div class="level-item control">
                                        <button class="button is-danger" type="submit" name="removeQuestion" formaction="RemoveQuestion" value="Remove Question${loop.index + 1}">
		                                    <span>Remove</span>
		                                </button>
                                    </div>
                                </div>
                            </nav>
                            <label class="label">Text</label>
                            <c:set var="eText" value="errorText${loop.index}" />
                            <p class="control <c:if test="${not empty sessionScope[eText]}">has-icon has-icon-right</c:if>">
                                <input class="input <c:if test="${not empty sessionScope[eText]}">is-danger</c:if>" type="text" name="text${loop.index}" placeholder="Is you a dog?" value="${question.text}" <c:if test="${loop.last && autoFocus eq -1 || autoFocus eq loop.index && question.options.isEmpty()}">autofocus</c:if> />
                                <c:if test="${not empty sessionScope[eText]}">
                    				<i class="fa fa-warning"></i>
  									<span class="help is-danger">${sessionScope[eText]}</span>
  								</c:if>
                            </p>
                        </div>
                    </c:if>
                </c:forEach>

                <h1 class="title is-4">Add Question</h1>
                <p class="control has-addons">
					<span class="select">
						<select name="questionType">
					  		<option value="mcQuestion" selected="selected">Multiple choice</option>
					        <option value="textQuestion">Text</option>
						</select>
					</span>
					<input class="button" type="submit" value="Add question" formaction="AddQuestion" />
				</p>
				
				<p class="control">
	                <input class="button is-primary" type="submit" value="Save" />
	                <a class="button" href="./dashboard" onclick="return confirm('Are you sure?')">Cancel</a>
                </p>
            </div>
        </section>
	</form>
	
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