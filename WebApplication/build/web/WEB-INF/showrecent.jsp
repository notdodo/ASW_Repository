<%@page import="java.util.Map, java.util.Date, java.text.SimpleDateFormat"%>
<%
    Map<Date, String> date = (Map<Date, String>) request.getAttribute("date");
    String[] car = new String[3];
    SimpleDateFormat ft = new SimpleDateFormat("dd.MM.yyyy");
    Integer count = 0;
%>
<div class="show">
    <h3>Ultimi Arrivi</h3>
    <% for (Map.Entry entry : date.entrySet()) {
        if (count <= 4) {
            count++;
            car = entry.getValue().toString().split("\\.");%>
            <p><a href="infocar.jsp?car=<%=entry.getValue()%>"><%=car[1]%>&nbsp;<%=car[2]%></a> 
            <h6>(<%=car[0]%>) - <%=ft.format(entry.getKey())%></h6></p><br/>
        <%}
    }%>
</div>
