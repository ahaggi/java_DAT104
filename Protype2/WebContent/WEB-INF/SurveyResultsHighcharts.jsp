<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE HTML>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
		<title>Resultat</title>

		<script type="text/javascript" src="https://ajax.googleapis.com/ajax/libs/jquery/1.8.2/jquery.min.js"></script>
		<style type="text/css">
${demo.css}
		</style>
		<c:forEach var="q" items="${survey.getQuestions()}">
		<script type="text/javascript">
$(function () {
    $('#container${q.beskrivelse}').highcharts({
        chart: {
            plotBackgroundColor: null,
            plotBorderWidth: null,
            plotShadow: false,
            type: 'pie'
        },
        title: {
            text: '${q.beskrivelse}'
        },
        tooltip: {
            pointFormat: '{series.name}: <b>{point.percentage:.1f}%</b>'
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
            name: '${q.beskrivelse}',
            colorByPoint: true,
            data: [
            	<c:forEach var="a" items="${q.alternatives}">
            	{
            	name: '${a.beskrivelse}',
                y: ${a.antallStemmer}
            },
            	</c:forEach>
            ]
        }]
    });
});
		</script>
		</c:forEach>
	</head>
	<body>
	<c:forEach var="q" items="${survey.getQuestions()}">
<script src="https://code.highcharts.com/highcharts.js"></script>
<script src="https://code.highcharts.com/modules/exporting.js"></script>

<div id="container${q.beskrivelse}" style="min-width: 310px; height: 400px; max-width: 600px; margin: 0 auto"></div>
</c:forEach>

	</body>
</html>
