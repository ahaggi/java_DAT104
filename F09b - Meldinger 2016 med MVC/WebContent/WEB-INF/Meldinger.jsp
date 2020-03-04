<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<%-- Graps start --%>
<%@ page import="static org.apache.commons.lang3.StringEscapeUtils.escapeHtml4" %>  
<%@ page import="java.util.List" %>    
<%@ page import="no.hib.dat104.Melding" %>  
<%@ page import="no.hib.dat104.CookieUtil" %>  
  
<% List<Melding> meldinger = (List<Melding>) request.getAttribute("meldinger"); %>
<% String avsenderCookie = CookieUtil.getAvsenderCookie(request);  %>
<%-- Graps slutt --%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Meldinger</title>
</head>
<body>
    <h2>Meldinger</h2>
    
    <% for (Melding m : meldinger) { %>
        <p><b>(#<%= m.getId() %>)
            <%=m.getFormatertTidsstempel()%> skrev
            <%=escapeHtml4(m.getAvsender())%>:</b></p>
            <blockquote><i><%=escapeHtml4(m.getTekst())%></i></blockquote>
    <% } %>
    
    <form method="post">
        <fieldset><legend>Ny melding</legend>
        <p>Skriv melding:<br />
        <textarea name="melding" rows="2" cols="45"></textarea></p>
        <p>Avsender:<br />
        <input type="text" name="avsender" value ="<%=escapeHtml4(avsenderCookie)%>" />
        <input type="submit" value="Send melding" />
        <input type="reset" value="Nullstill" /></p>
        </fieldset>
    </form>
</body>
</html>
