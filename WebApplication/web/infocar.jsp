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
<% String car = request.getParameter("car");%>
<div class="left-column" id="main">
    <% if (car == null) { %>
        <jsp:include page="WEB-INF/jspf/notfound.jsp"/>
    <% } else {%>
        <script>infoCar('<%= car%>');</script>
    <% }%>
</div>
<jsp:include page="WEB-INF/jspf/rightcol.jspf"/>
<jsp:include page="WEB-INF/jspf/footer.jspf">
    <jsp:param name="logged" value="<%=logged%>"/>
</jsp:include>