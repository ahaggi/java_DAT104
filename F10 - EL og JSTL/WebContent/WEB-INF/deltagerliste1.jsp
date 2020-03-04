<%-- deltagerliste1.jsp : En l�sning UTEN EL og JSTL --%>

<%@ page import="java.util.Arrays"%>
<%@ page import="java.util.List"%>
<%@ page import="lph_package.Deltager"%>

<%-- Putter dummy controller-kode inn her, s� har vi alt p� ett sted.
Disse tingene skal selvsagt gj�res i Servlet ... --%>
<%
Deltager innloggetDeltager = new Deltager("77665544", "Carl", "Cool", "m", true);
session.setAttribute("innloggetDeltager", innloggetDeltager);  

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
        
<%  List<Deltager> deltagere = (List<Deltager>) request.getAttribute("deltagere");
    Deltager innlogget = (Deltager) session.getAttribute("innloggetDeltager");
    for (Deltager d : deltagere) { 
        if (innlogget.equals(d)) {
            if (d.isBetalt()) { %>
                <tr bgcolor="#aaffaa">
         <% } else { %>
                <tr bgcolor="#ffaaaa">
         <% } 
        } else { %>
                <tr>
     <% } %>
        <td align="center"><%= d.getKjonnsymbol() %></td>
        <td><%= d.getFulltNavn() %></td></tr>
 <% }
%>

</table>
<p><a href="ferdig.html">Ferdig</a></p>
</body>
</html>