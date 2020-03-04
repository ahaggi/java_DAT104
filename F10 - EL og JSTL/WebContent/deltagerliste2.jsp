<%-- deltagerliste2.jsp : En løsning med EL og JSTL --%>

<%@ page import="java.util.Arrays"%>
<%@ page import="java.util.List"%>
<%@ page import="lph_package.Deltager"%>

<%-- Putter dummy controller-kode inn her, så har vi alt på ett sted.
Disse tingene skal selvsagt gjøres i Servlet og hjelpeklasser ... --%>
<%
Deltager innloggetDeltager = new Deltager("77665544", "Carl", "Cool", "m", true);
session.setAttribute("innloggedDeltager", innloggetDeltager);  

List<Deltager> dummyListe = Arrays.asList(
		new Deltager("99887766", "Arne", "And", "m", false),
        new Deltager("88776655", "Brit", "Blå", "k", false),
        innloggetDeltager,
        new Deltager("11998877", "Dora", "Dill", "k", true),
        new Deltager("22776633", "Erna", "Sol", "k", false));

request.setAttribute("deltagere", dummyListe);
%>
<%-- Slutt på controller-kode. Da later vi som om vi har gjort en forward.
Nå kommer JSP-koden --%>

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
        <th>Kjønn</th><th align="left">Navn</th></tr>

    <c:forEach var="d" items="${deltagere}">
        <c:if test="${innloggedDeltager eq d}">
        
            <%--  EL sin conditional operator er meget nyttig. Legg merke til at
                  output må være i gåseøyne og at man må mikse enkle og doble 
                  quotes når de er nøstet --%>
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