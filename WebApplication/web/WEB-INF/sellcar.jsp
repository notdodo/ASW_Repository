<%@page import="java.util.HashMap"%>
<div class="show">
<% HashMap<String, String> info = (HashMap<String, String>) request.getAttribute("info");
   String buyer = (String) request.getAttribute("buyer");
%>
   <h1><%= info.get("marca")%> <%= info.get("modello")%></h1>
   <p>Complimenti hai venduto la tua auto all'utente <a href="user.jsp?userInfo=<%=buyer%>"><%=buyer%></a> al prezzo di <%=info.get("prezzo")%> &euro;!</p>
   <img src='data:image/png;base64,<%= info.get("img")%>'>
</div>
