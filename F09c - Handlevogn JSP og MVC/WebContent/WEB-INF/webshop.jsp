<%@ page import="static no.hib.dat104.lph.UrlMappings.LOGOUT_URL"%>
<%@ page import="static no.hib.dat104.lph.UrlMappings.WEBSHOP_URL"%>
<%@ page import="no.hib.dat104.lph.Cart"%>
<%@ page import="no.hib.dat104.lph.CartItem"%>
<%@ page contentType="text/html; charset=ISO-8859-1"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Web Shop</title>
</head>
<body>
<p>Du er innlogget som <%= session.getAttribute("username") %></p>
Din handlekurv:<br />
<table border="1">
    <tr><th>Vare</th><th>Pris</th></tr>
    
<% Cart cart = (Cart) session.getAttribute("cart");
for (CartItem item : cart.getItems()) { %>
    <tr><td> <%= item.getName() %> </td>
    <td align="right"> <%= item.getPrice() %> </td></tr>
<% } %>

</table><br/>
<form action="<%= WEBSHOP_URL %>" method="post">
    <fieldset>
        <legend>Handle</legend>
        <input type="checkbox" name="bukse" />Bukse<br/>
        <input type="checkbox" name="genser" />Genser<br/>
        <p><input type="submit" value="Legg i handlekurv" /></p>
    </fieldset>
</form>
<form action="<%= LOGOUT_URL %>" method="get">
    <fieldset>
        <p><input type="submit" value="Logg ut" /></p>
    </fieldset>
</form>
</body>
</html>
