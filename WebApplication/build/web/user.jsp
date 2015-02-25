<%@page contentType="text/html" pageEncoding="UTF-8"%>
<% boolean logged = false;
    if (session.getAttribute("user") != null) {
        logged = true;
    } else {
        logged = false;
    }
%>
<jsp:include page="WEB-INF/jspf/header.jspf">
    <jsp:param name="logged" value="<%=logged%>"/>
</jsp:include>
<% String userInfo = request.getParameter("userInfo");%>
<div class="left-column" id="main">
    <% if (userInfo == null) { %>
        <jsp:include page="WEB-INF/notfound.jsp"/>
    <% } else {%>
        <script>infoUser('<%= userInfo%>');</script>
    <% }%>
</div>
<jsp:include page="WEB-INF/jspf/rightcol.jspf"/>
<jsp:include page="WEB-INF/jspf/footer.jspf">
    <jsp:param name="logged" value="<%=logged%>"/>
</jsp:include>