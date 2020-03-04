<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<!doctype html>
<html lang="en">
    <head>
        <meta charset="ISO-8859-1">
        <title>Dashboard</title>
        <meta name="description" content="">
        <meta name="author" content="">

        <meta name="viewport" content="width=device-width, initial-scale=1">
        
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.6.3/css/font-awesome.min.css">
        <link rel="stylesheet" href="./css/bulma.css">
        <script src="js/countdown.js"></script>
    </head>
    <body>
    	<!-- TOP MENU -->
        <section class="hero">
            <div class="hero-body">
                <div class="container">
                    <p class="title">
                        <span>Dashboard</span>
                        <span class="control has-addons is-grouped is-pulled-right">
	                    	<a class="button" href="register">Register new user</a>
	                    	<a class="button" href="logout">
	                    		<span class="icon">
						    		<i class="fa fa-sign-out"></i>
								</span>
								<span>Logout</span>
							</a>
	                    </span>
                    </p>
                    
                    <p class="subtitle">Create and manage your surveys.</p>
                    <a class="button is-primary" href="editor">New survey</a>
                </div>
            </div>
        </section>
        
        <!-- NOT PUBLISHED SECTION -->
        <section class="section">
            <div class="container">
                <h1 class="title is-5">Not published</h1>
                
                <c:forEach var="survey" items="${surveys}">
					<c:if test="${survey.isPublished() eq false}">
		                <div class="box">
		                    <nav class="level">
		                        <!-- Left side -->
		                        <div class="level-left">
		                            <div class="level-item">
		                                ${survey.name}
		                            </div>
		                        </div>
		
		                        <!-- Right side -->
		                        <div class="level-right">
		                            <form method="post" class="level-item control">
		                                <input type="hidden" name="surveyId" value="${survey.id}">
		                                
		                                <span class="control">
		                                	<input class="input is-primary" type="url" onfocus="this.select()" readonly value="${interviewHomeUrl}${survey.getEncryptedSurveyId()}" style="width: auto" />
		                                </span>
		                                
		                                <button class="button is-primary" type="submit" formaction="publish">
		                                    <span>Publish</span>
		                                </button>
		                                
		                                <button class="button" type="submit" formaction="edit">
		                                    <span>Edit</span>
		                                </button>
		                                
		                                <button class="button" type="submit" formaction="duplicate">
		                                    <span>Duplicate</span>
		                                </button>
		                                
		                                <button class="button is-danger is-outlined" type="submit" onclick="return confirm('Are you sure?')" formaction="delete">
		                                    <span>Delete</span>
		                                    <span class="icon">
		                                        <i class="fa fa-times"></i>
		                                    </span>
		                                </button>
		                            </form>
		                        </div>
		                    </nav>
		                </div>
	                </c:if>
				</c:forEach>
            </div>
        </section>
        
        <!-- ONGOING SECTION -->
        <section class="section">
            <div class="container">
                <h1 class="title is-5">Ongoing</h1>
                
                <c:forEach var="survey" items="${surveys}">
					<c:if test="${survey.isOpen() eq true}">
		                <div class="box">
		                    <nav class="level">
		                        <!-- Left side -->
		                        <div class="level-left">
		                            <div class="level-item">
		                                ${survey.name}
		                            </div>
		                        </div>
		
		                        <!-- Right side -->
		                        <div class="level-right">
		                        	<div class="level-item">
		                                Closing in: <span id="countdown${survey.id}"></span>
									</div>
		                            <form method="post" class="level-item control">
		                                <input type="hidden" name="surveyId" value="${survey.id}"/>
		
										<span class="control">
		                                	<input class="input is-primary" type="url" onfocus="this.select()" readonly value="${interviewHomeUrl}${survey.getEncryptedSurveyId()}" style="width: auto" />
										</span>
										
		                                <button class="button" type="submit" formaction="results" formmethod="get">
		                                    <span>Results</span>
		                                </button>
		
		                                <button class="button" type="submit" formaction="duplicate">
		                                    <span>Duplicate</span>
		                                </button>
		
		                                <button class="button is-warning" type="submit" onclick="return confirm('Are you sure?')" formaction="close">
		                                    <span>Close</span>
		                                </button>
		                            </form>
		                        </div>
		                    </nav>
		                </div>
		        	</c:if>
				</c:forEach>
            </div>
        </section>
        
        <!-- CLOSED SECTION -->
        <section class="section">
            <div class="container">
                <h1 class="title is-5">Closed</h1>
                
                <c:forEach var="survey" items="${surveys}">
					<c:if test="${survey.isPublished() eq true && survey.isOpen() eq false}">
		                <div class="box">
		                    <nav class="level">
		                        <!-- Left side -->
		                        <div class="level-left">
		                            <div class="level-item">
		                                ${survey.name}
		                            </div>
		                        </div>
		
		                        <!-- Right side -->
		                        <div class="level-right">
		                            <form method="post" class="level-item control">
		                                <input type="hidden" name="surveyId" value="${survey.id}"/>
		
		                                <button class="button" type="submit" formaction="results" formmethod="get">
		                                    <span>Results</span>
		                                </button>
		
		                                <button class="button" type="submit" formaction="duplicate">
		                                    <span>Duplicate</span>
		                                </button>
		
		                                <button class="button is-danger is-outlined" type="submit" onclick="return confirm('Are you sure?')" formaction="delete">
		                                    <span>Delete</span>
		                                    <span class="icon">
		                                        <i class="fa fa-times"></i>
		                                    </span>
		                                </button>
		                            </form>
		                        </div>
		                    </nav>
		                </div>
					</c:if>
				</c:forEach>
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
        <script>
        	<c:forEach var="survey" items="${surveys}">
				<c:if test="${survey.isOpen() eq true}">
    				CountDownTimer('${survey.getFormatedEnddate()}', 'countdown${survey.id}');
    			</c:if>
			</c:forEach>
		</script>
    </body>
</html>