<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE HTML>
<html>
<head>
	<meta charset="ISO-8859-1">
    <title>Results</title>
    <meta name="description" content="">
    <meta name="author" content="">

    <meta name="viewport" content="width=device-width, initial-scale=1">
    
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.6.3/css/font-awesome.min.css">
    <link rel="stylesheet" href="./css/bulma.css">
	
	<script type="text/javascript" src="https://ajax.googleapis.com/ajax/libs/jquery/1.8.2/jquery.min.js"></script>
	<script src="https://code.highcharts.com/highcharts.js"></script>
	<script src="https://code.highcharts.com/highcharts-more.js"></script>
	<script src="https://code.highcharts.com/modules/data.js"></script>
	<script src="https://code.highcharts.com/modules/exporting.js"></script>
	
	<style type="text/css">${demo.css}</style>

<c:forEach var="q" items="${surveyResult.getQuestions()}">
	<c:if test="${q.getType() eq 'mc'}">
		<script type="text/javascript">
		$(function () {
    	$('#container${q.id}').highcharts({
        chart: {
            plotBackgroundColor: null,
            plotBorderWidth: null,
            plotShadow: false,
            type: 'pie'
        },
        title: {
            text: '${q.text}'
        },
        subtitle: {
            text: '<p> Number of answers: ${q.answers.size()}</p>'
        },
        tooltip: {
            pointFormat: '{point.y} : <b>{point.percentage:.1f}%</b>'
        },
        plotOptions: {
            pie: {
                allowPointSelect: true,
                cursor: 'pointer',
                dataLabels: {
                    enabled: true,
                    format: '<b>{point.name}</b>: {point.percentage:.1f} %',
                    style: {
                        color: (Highcharts.theme && Highcharts.theme.contrastTextColor) || 'black'
                    }
                }
            }
        },
        series: [{
            name: '${q.text}', 
            colorByPoint: true,  
            data: [
            	<c:forEach var="map" items="${q.generateHashMap()}"> //items skal være alternativene til spørsmålet
            	{
            	name: '${map.key}', //alternativet
                y: ${map.value} //hvor mange som har stemt på det alternativet
            }, 
            	</c:forEach>
            ]
        }]
    });
});
		</script>
		
		
	</c:if>
	
		<c:if test="${q.getType() eq 'tq'}">
				<script type="text/javascript">
		
		$(function () {
    Highcharts.chart('container${q.id}', {

        chart: {
            type: 'bubble',
            plotBorderWidth: 1,
            zoomType: 'xy'
        },

        legend: {
            enabled: false
        },

        title: {
            text: '${q.text}'
        },

        subtitle: {
            text: '<p> Number of answers: ${q.answers.size()}</p>'
        },

        xAxis: {
            gridLineWidth: 1,
            title: {
                text: 'Frequency of a word'
            },
            labels: {
                format: '{value} times'
            },
            plotLines: [{
                color: 'black',
                dashStyle: 'dot',
                width: 2,
                value: 0,
                label: {
                    rotation: 0,
                    y: 15,
                    style: {
                        fontStyle: 'italic'
                    },
                    text: 'kansje typisk density'
                },
                zIndex: 3
            }]
        },

        yAxis: {
            startOnTick: false,
            endOnTick: false,
            title: {
                text: 'Unique appearance per answer'
            },
            labels: {
                format: '{value} times'
            },
            maxPadding: 0.2,
            plotLines: [{
                color: 'black',
                dashStyle: 'dot',
                width: 2,
                value: 0,
                label: {
                    align: 'right',
                    style: {
                        fontStyle: 'italic'
                    },
                    text: 'xxxxxxxxxxxxxxx per student',
                    x: -10
                },
                zIndex: 3
            }]
        },

        tooltip: {
            useHTML: true,
            headerFormat: '<table>',
            pointFormat: '<tr><th colspan="2"><h3>{point.name}</h3></th></tr>' +
                '<tr><th>Frequency:</th><td>{point.x}</td></tr>' +
                '<tr><th>Unique appearance:</th><td>{point.y}</td></tr>' +
                '<tr><th>Density:</th><td>{point.z}%</td></tr>',
            footerFormat: '</table>',
            followPointer: true
        },

        plotOptions: {
            series: {
                dataLabels: {
                    enabled: true,
                    format: '{point.name}'
                }
            }
        },

        series: [{
            data: [
                   
               	<c:forEach var="word" items="${bca_allQuestions.getTableSortedByFrequency(q.id)}"> 
	                { x: ${word.frq}, y: ${word.uniqueAppearances}, z: ${((word.frq + word.uniqueAppearances) % bca_allQuestions.getNrOfWords(q.id))}, name: '${word.text}' },
               	</c:forEach>

            ]
        }]

    });
});
		
		
		$(function () {
		    Highcharts.chart('container${q.id}_2', {
		        data: {
		            table: 'datatable'
		        },
		        chart: {
		            type: 'column'
		        },
		        title: {
		            text: '${q.text}'
		        },
		        yAxis: {
		            allowDecimals: false,
		            title: {
		                text: 'Units'
		            }
		        },
		        tooltip: {
		            formatter: function () {
		                return '<b>' + this.series.name + '</b><br/>' +
		                    this.point.y + ' ' + this.point.name.toLowerCase();
		            }
		        }
		    });
		});

		</script>

		</c:if>
	
	
	
</c:forEach>
</head>
<body>
	<section class="section">
		<div class="container">
			<div class="columns is-vcentered">
				<div class="column is-8 is-offset-2">
					<h1 class="title">Results</h1>
					<h1 class="subtitle">${surveyResult.name}</h1>
					
					<c:forEach var="q" items="${surveyResult.getQuestions()}" varStatus="qIndex">
						<div class="box">
							<h1 class="title is-4">Question ${qIndex.index + 1}</h1>
							<h1 class="subtitle is-5">${q.text}</h1>

							<c:if test="${q.getType() eq 'mc'}">
								<div id="container${q.id}"	style="min-width: 310px; height: 400px; max-width: 600px; margin: 0 auto"></div>
							</c:if>
							
							<c:if test="${q.getType() eq 'tq'}">
							
								<table class="table">
								  <thead>
								    <tr>
								      <th>Nr</th>
								      <th>Answer</th>
								    </tr>
								  </thead>
								  <tbody>
								  	  <c:forEach var="answer" items="${q.getAnswers()}" varStatus="index"> 
									 	  <tr>
									      	<td>${index.index + 1}</td>
									      	<td>${answer.text}</td>
									      </tr>
								      </c:forEach>
								  </tbody>
								</table>
								
								<div id="container${q.id}" style="height: 400px; min-width: 310px; max-width: 600px; margin: 0 auto"></div>
							</c:if>
							
							<!-- eller -->
							
<%-- 							<c:if test="${q.getType() eq 'tq'}"> --%>
<%-- 								<div id="container${q.id}_2" style="min-width: 310px; height: 400px; margin: 0 auto"></div> --%>
				
<!-- 								<table id="datatable" style="display:none"> -->
<!-- 								    <thead> -->
<!-- 								        <tr> -->
<!-- 								            <th></th> -->
<!-- 								            <th>Frequency</th> -->
<!-- 								            <th>Unique appearance per answer</th> -->
<!-- 								        </tr> -->
<!-- 								    </thead> -->
<!-- 								    <tbody> -->
<%-- 									<c:forEach var="word" items="${bca_allQuestions.getTableSortedByFrequency(q.id)}">  --%>
<!-- 										<tr> -->
<%-- 								 			<th>${word.text}</th> --%>
<%-- 								            <td>${word.frq}</td> --%>
<%-- 								            <td>${word.uniqueAppearances}</td> --%>
<!-- 								        </tr> -->
<%-- 									</c:forEach> --%>
<!-- 								    </tbody> -->
<!-- 								</table> -->
<%-- 							</c:if> --%>
						</div>
					</c:forEach>
					<p class="control">
						<a class="button is-primary" href="dashboard">Back</a>
					</p>
				</div>
			</div>
		</div>
	</section>
</body>
</html>

