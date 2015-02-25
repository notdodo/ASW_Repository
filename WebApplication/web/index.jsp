<%@page contentType="text/html" pageEncoding="UTF-8"%>
<% boolean logged = false;
    boolean loginResult;
    if (request.getCookies() != null) {
        Cookie[] cookie = request.getCookies();
        for (Cookie cook : cookie) {
            if (cook.getName().equals("logged")) {
                logged = cook.getValue().equals("1") ? true : false;
            }
            if (cook.getName().equals("user")) {
                session.setAttribute("user", cook.getValue());
                session.setAttribute("logged", "true");
            }
        }
    }
    if (session.getAttribute("logged") != null) {
        loginResult = (((String) session.getAttribute("logged")).equals("true")) ? true : false;
    } else {
        loginResult = true;
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
<div class="left-column" id="main">
    <% if (!loginResult) { %>
    <jsp:include page="WEB-INF/jspf/userError.jspf"/>
    <%} else { %>
    <script>send("<showCars></showCars>", "main");</script>
    <% }%>
</div>
<jsp:include page="WEB-INF/jspf/rightcol.jspf"/>
<jsp:include page="WEB-INF/jspf/footer.jspf">
    <jsp:param name="logged" value="<%=logged%>"/>
</jsp:include>