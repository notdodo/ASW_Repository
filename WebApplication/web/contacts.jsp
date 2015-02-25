<%@page contentType="text/html" pageEncoding="UTF-8"%>
<% boolean logged = false;
    // Controllo se i cookies sono settati
    if (request.getCookies() != null) {
        Cookie[] cookie = request.getCookies();
        for (Cookie cook : cookie) {
            if (cook.getName().equals("logged")) {
                logged = cook.getValue().equals("1") ? true : false;
            }
            if (cook.getName().equals("user")) {
                session.setAttribute("user", cook.getValue());
            }
        }
    }
    if (session.getAttribute("user") != null) {
        logged = true;
    } else {
        logged = false;
    }
%>
<jsp:include page="WEB-INF/jspf/header.jspf">
    <jsp:param name="logged" value="<%=logged%>"/>
</jsp:include>
<div class="left-column">
    <h1>Contatti</h1>
    <p>iCar? ti permette di trovare l'auto usata dei tuoi sogni in un vastissimo <a href="index.jsp">parco auto</a>.<br/>
        Potrai trovare ogni tipo di auto a qualsiasi prezzo e facendo un'offerta, tramite la nostra asta, potrai acquistarla.</p> 
    <p>Se invece stai cercando un portale per vendere la tua auto usata hai trovato il servizio su misura per te: 
        <a href="insert.jsp">carica</a> la tua auto, aspetta le offerte e scegli la migliore.</p>
    <br/>
    <p>Puoi contattarci personalmente inviando una mail a:</p>
    <p><a href="mailto:roberto.delia3@studio.unibo.it">Roberto D'Elia</a></p>
    <p><a href="mailto:edoardo.rosa@studio.unibo.it">Edoardo Rosa</a></p>
    <p><a href="mailto:federico.torsello@studio.unibo.it">Federico Torsello</a></p>
</div>
<jsp:include page="WEB-INF/jspf/rightcol.jspf"/>
<jsp:include page="WEB-INF/jspf/footer.jspf">
    <jsp:param name="logged" value="<%=logged%>"/>
</jsp:include>
