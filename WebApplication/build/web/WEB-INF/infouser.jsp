<%@page import="java.util.HashMap"%>
<div class="show">
    <% HashMap<String, String> info = (HashMap<String, String>) request.getAttribute("infoUser");%>
    <h1><%= info.get("nome")%> <%= info.get("cognome")%></h1>
    <p><label><b>Nome</b>: <%=info.get("nome")%></label></p>
    <p><label><b>Cognome</b>: <%=info.get("cognome")%></label></p>
    <p><label><b>Nickname</b>: <%=info.get("nickname")%></label></p>
    <p><label><b>Email</b>: <%=info.get("email")%></label></p>
    <p><label><b>Indirizzo</b>: <%=info.get("indirizzo")%></label></p>
</div>

