<%-- 
    Document   : changeUserStatus
    Created on : Feb 16, 2021, 1:04:28 PM
    Author     : junta
--%>

<%@page import="Business.UserDetails"%>
<%@page import="Daos.UserDetailsDao"%>
<%@page import="java.util.ArrayList"%>
<%@page import="Business.User"%>
<%@page import="Daos.UserDao"%>
<jsp:include page="header.jsp" />  
<jsp:include page="navbar.jsp" />    
<%  UserDao userDao = new UserDao("digiclass");
    UserDetailsDao userDetailsDao = new UserDetailsDao("digiclass");
    String username = (String) session.getAttribute("username");
    User u = userDao.getUserByUsername(username);
    ArrayList<User> uList = userDao.getAllUsers();
    int i = 0;
    int j = 0;
%> 
<p id="userType" class="d-none"><%=u.getUser_type()%></p>
<div class=" container text-left mb-5 mt-5 p-5 font">
    <img class="w-100" src="image/changeState.jpg"/>
    <div class="mt-3">
        <h3>Activate/Disabled Users</h3>
    </div>
    <div class="row">
        <div class="col-lg-4 col-12">
            <div class="list-group" id="list-tab" role="tablist">
                <%for (User user : uList) {
                        UserDetails userDetails = userDetailsDao.getUserDetailsByUserId(user.getID());
                        if (userDetails != null) {
                %>
                <a class="list-group-item list-group-item-action" id="list-home-list" data-toggle="list" href="#list-verify-<%=i%>" role="tab" aria-controls="home"><%=userDetails.getFirst_name() + " " + userDetails.getLast_name()%></a>
                <% } else {%>
                <a class="list-group-item list-group-item-action" id = "list-home-list" data-toggle = "list" href ="#list-verify-<%=i%>" role="tab" aria-controls="home">User has not registered details</a>
                <% }
                        i++;
                    }%>
            </div>
        </div>
        <div class="col-lg-8 col-12">
            <div class="tab-content" id="nav-tabContent">
                <% for (User user : uList) {
                %>
                <div class="tab-pane fade show text-white p-3 verify-tab" id="list-verify-<%=j%>" role="tabpanel" aria-labelledby="list-home-list">
                    <form action="controller" method="post" autocomplete="off">
                        <input name="action" value="changeUserStauts" type="hidden">
                        <input type="hidden" name="userId" value="<%=user.getID()%>"/>
                        <h2>User Status</h2>
                        <p>Username : <%=user.getUsername()%></p>
                        <p>Email : <%=user.getEmail()%></p>
                        <p>Status : <%=user.getUser_status()%></p>
                        <%if (!(user.getUser_status().equals("DISABLED"))) {%>
                        <input type="hidden" name="status" value="DISABLED"/>
                        <button class="btn btn-danger">DISABLE</button>
                        <%} else {%>
                        <input type="hidden" name="status" value="ACTIVE"/>
                        <button class="btn btn-primary">ACTIVATE</button>
                        <%}%>
                    </form>
                </div>
                <%j++;
                    }%>
            </div>
        </div>
    </div>
</div>
<jsp:include page="footer.jsp" />  
