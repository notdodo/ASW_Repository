<%@page import="java.util.HashMap"%>
<div class="show" id="auction">
    <% HashMap<String, String> info = (HashMap<String, String>) request.getAttribute("info");
        String file = info.get("utente") + "." + info.get("marca") + "." + info.get("modello");
        String prezzo = info.get("prezzo");
    %>
    <h1><%= info.get("marca")%> <%= info.get("modello")%></h1>
    <p><label><b>Utente</b>: <a href="user.jsp?userInfo=<%=info.get("utente")%>"><%=info.get("utente")%></a></label></p>
    <p><label><b>Marca</b>: <%=info.get("marca")%></label></p>
    <p><label><b>Modello</b>: <%=info.get("modello")%></label></p>
    <p><label><b>Km</b>: <%=info.get("km")%></label></p>
    <p><label><b>Anno</b>: <%=info.get("anno")%></label></p>
    <p><label><b>Carburante</b>: <%=info.get("carburante")%></label>
        <label><b>Potenza</b>: <%=info.get("potenza")%></label>
    </p>
    <p><label><b>Descrizione</b>: <%=info.get("note")%></label></p>
    <p id='offer'><label><b>Prezzo</b>: <%=prezzo%></label></p>
    <% if (session.getAttribute("user") != null) {
        if (!info.get("ultimaOfferta").equals("nullUser") && info.get("utente").equals(session.getAttribute("user"))) { %>
            <p><button name="" onclick="sellCar('<%=file%>')">Vendi</button></p>
            <%} else if(!info.get("utente").equals(session.getAttribute("user"))) {%>
            <p id="sendAuction">
                <label><input id="makeOffer" type="hidden" name="newOffer" value="<%=prezzo%>" min="<%=prezzo%>"/></label>
            </p>
            <p><button name="offer" onclick="showOffer('<%=file%>', '<%=prezzo%>');">Fai un'offerta</button></p>
        <%} else { %>
            <h6>Attendi che facciano delle offerte.</h6>
          <%}
        }%>
        <img src='data:image/png;base64,<%= info.get("img")%>'>
</div>
