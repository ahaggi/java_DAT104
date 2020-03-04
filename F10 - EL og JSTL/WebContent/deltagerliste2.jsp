<%-- deltagerliste2.jsp : En l�sning med EL og JSTL --%>

<%@ page import="java.util.Arrays"%>
<%@ page import="java.util.List"%>
<%@ page import="lph_package.Deltager"%>

<%-- Putter dummy controller-kode inn her, s� har vi alt p� ett sted.
Disse tingene skal selvsagt gj�res i Servlet og hjelpeklasser ... --%>
<%
Deltager innloggetDeltager = new Deltager("77665544", "Carl", "Cool", "m", true);
session.setAttribute("innloggedDeltager", innloggetDeltager);  

List<Deltager> dummyListe = Arrays.asList(
		new Deltager("99887766", "Arne", "And", "m", false),
        new Deltager("88776655", "Brit", "Bl�", "k", false),
        innloggetDeltager,
        new Deltager("11998877", "Dora", "Dill", "k", true),
        new Deltager("22776633", "Erna", "Sol", "k", false));

request.setAttribute("deltagere", dummyListe);
%>
<%-- Slutt p� controller-kode. Da later vi som om vi har gjort en forward.
N� kommer JSP-koden --%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Deltagerliste</title>
</head>
<body>
<h2>Deltagerliste</h2>
<table border="1">
    <tr bgcolor="#cccccc">
        <th>Kj�nn</th><th align="left">Navn</th></tr>

    <c:forEach var="d" items="${deltagere}">
        <c:if test="${innloggedDeltager eq d}">
        
            <%--  EL sin conditional operator er meget nyttig. Legg merke til at
                  output m� v�re i g�se�yne og at man m� mikse enkle og doble 
                  quotes n�r de er n�stet --%>
            ${d.betalt ? "<tr bgcolor='#aaffaa'>" : "<tr bgcolor='#ffaaaa'>"}
            
        </c:if>
        <c:if test="${innloggedDeltager ne d}">
            <tr>
        </c:if>
        <td align="center">${d.kjonnsymbol}</td>
        <td>${d.fulltNavn}</td></tr>
    </c:forEach>

</table>
<p><a href="ferdig.html">Ferdig</a></p>
</body>
</html>