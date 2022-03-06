<%-- 
    Document   : notification
    Created on : Apr 14, 2021, 1:03:45 PM
    Author     : junta
--%>

<%@page import="java.util.ArrayList"%>
<%@page import="Business.*"%>
<%@page import="Daos.*"%>
<%  int uId = (int) session.getAttribute("userId");
    NotificationDao notificationDao = new NotificationDao("digiclass");
    ArrayList<Notification> notificationList = notificationDao.getNotificationById(uId);

%>
<div class="ml-2">
    <p class="text-white"> NOTIFICATIONS</p>
    <%if (notificationList.size() > 0) {
            for (Notification noti : notificationList) {%>
    <div class="row">
        <% if (noti.isSeen() == false) {%>
        <div class="col-sm-10 col-lg-7" >
            <a class="text-decoration-none text-white" href="controller?action=updateNotification&id=<%=noti.getId()%>"><%=noti.getDescription()%></a>
        </div>
        <div class="col-sm-1 col-lg-2 pt-1">
            <i class="fas fa-circle"></i>
        </div>
        <%} else {%>
        <div class="col-sm-12 col-lg-8" >
            <a class="text-decoration-none text-white"><%=noti.getDescription()%></a>
        </div>
        <%}%>
        <div class="col-sm-12 col-lg-2">
            <button class="btn btn-sm"><a class="text-decoration-none text-white" href="controller?action=deleteNotification&id=<%=noti.getId()%>"><i class="fa fa-trash-o" aria-hidden="true"></i></a></button>
        </div>
    </div>
    <%}
    } else {%>
    <a>No Notification </a>
    <%}%>
</div>

