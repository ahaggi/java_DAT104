<%-- Direktiver --%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.Date" %>

<!DOCTYPE html>
<html>
<body>

<%-- JSP-kommentarer ser slik ut --%>

<%-- Uttrykk --%>
<p><%= new Date() %></p>

<%-- Scriptlets --%>
<% for (int i=1; i<=5; i++) {
    out.println("Hallo<br/>");
} %>

<%-- Uten out.println() --%>
<% for (int i=1; i<=5; i++) { %>
Hallaien <br/>
<% } %>

<%-- Hvis det er forward() fra en Servlet, og denne har lagret en melding,
    så kan den hentes frem slik. Rett fra jsp-en blir det null. --%>
<%= request.getAttribute("melding") %>

</body>
</html>





