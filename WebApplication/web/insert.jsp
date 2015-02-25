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
    session.removeAttribute("logged");
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
    <% if (session.getAttribute("user") != null) {%>
    <object type="application/x-java-applet" height="600" width="550" id="appletSell">
        <param name="code" value="asw.imp.Applet1" />
        <param name="archive" value="Applet1.jar,Lib1.jar" />
        <param name="java_arguments" value="-Djnlp.packEnabled=true"/>
        <param name="user" value="<%= session.getAttribute("user")%>"/>
        Applet failed to run.  No Java plug-in was found.
    </object>
    <% } else { %>
    <jsp:include page="WEB-INF/error.jsp"/>
    <% }%>
    <script type="text/javascript">
        var app = document.getElementById("appletSell");
        app.parentNode.style.textAlign = "center";
        if (window.innerWidth < 910)
            app.width = window.innerWidth - 60;
        if (window.innerHeight > 700)
            app.height = window.innerHeight - 200;
    </script>
</div>
<jsp:include page="WEB-INF/jspf/rightcol.jspf"/>
<jsp:include page="WEB-INF/jspf/footer.jspf">
    <jsp:param name="logged" value="<%=logged%>"/>
</jsp:include>