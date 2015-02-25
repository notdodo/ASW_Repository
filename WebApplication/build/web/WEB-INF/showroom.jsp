<%
    String[] user = (String[]) request.getAttribute("utente");
    String[] marca = (String[]) request.getAttribute("marca");
    String[] modello = (String[]) request.getAttribute("modello");
    String[] carsImg = (String[]) request.getAttribute("img");
    String[] prezzo = (String[]) request.getAttribute("prezzo");
    String car = "";
%>
<div class="show">
    <h1>Auto Disponibli</h1>
    <% for (int i = 0; i < marca.length; i++) {
        car = user[i] + "." + marca[i] + "." + modello[i];%>
    <a href='infocar.jsp?car=<%= car %>'>
        <div class="grid">
            <p><%= marca[i]%> <%= modello[i]%></p>
            <p><%= prezzo[i]%> &euro;</p>
            <img src="data:image/png;base64,<%= carsImg[i]%>">
        </div>
    </a>
    <% }%>
</div>