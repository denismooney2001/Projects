<%-- 
    Document   : toVerifyList
    Created on : Mar 3, 2021, 6:52:31 PM
    Author     : junta
--%>
<%@page import="java.util.ArrayList"%>
<%@page import="Business.*"%>
<%@page import="Daos.*"%>
<jsp:include page="header.jsp" />  
<jsp:include page="navbar.jsp" />    
<%  UserDao userDao = new UserDao("digiclass");
    UserDetailsDao userDetailsDao = new UserDetailsDao("digiclass");
    String username = (String) session.getAttribute("username");
    User u = userDao.getUserByUsername(username);
    ArrayList<User> usList = userDao.getAllUsers();
    int i = 0;
    int j = 0;
%> 
<p id="userType" class="d-none"><%=u.getUser_type()%></p>
<div class=" container text-left mb-5 mt-5 p-5 font">
    <img class="w-100" src="image/user.jpg"/>
    <div class="mt-3">
        <h3>Users Lists</h3>
    </div>
    <div class="row">
        <div class="col-lg-4 col-12">
            <div class="list-group" id="list-tab" role="tablist">
                <%for (User ulist : usList) {
                            if (ulist.getUser_type() != "ADMIN") {%>
                <a class="list-group-item list-group-item-action" id="list-home-list" data-toggle="list" href="#list-verify-<%=i%>" role="tab" aria-controls="home">
                    <%=ulist.getUsername()%>
                </a>
                <%}
                        i++;
                    }%>
            </div>
        </div>
        <div class="col-lg-8 col-12">
            <div class="tab-content" id="nav-tabContent">
                <%for (User uList : usList) {
                        if (uList.getUser_type() != "ADMIN") {
                            UserDetails ud = userDetailsDao.getUserDetailsByUserId(uList.getID());%>
                <div class="tab-pane fade show text-white p-3 verify-tab" id="list-verify-<%=j%>" role="tabpanel" aria-labelledby="list-home-list">
                    <div class="row">
                        <label>User ID : </label>
                        <p class="d-inline-block"> <%=uList.getID()%></p>
                    </div>
                    <div class="row">
                        <label>Username : </label>
                        <p class="d-inline-block"> <%=uList.getUsername()%></p>
                    </div>
                    <div class="row">
                        <label>Email : </label>
                        <p class="d-inline-block"> <%=uList.getEmail()%></p>
                    </div>
                    <div class="row">
                        <label>User Type : </label>
                        <p class="d-inline-block viewType"> <%=uList.getUser_type()%></p>
                    </div>
                    <div class="row">
                        <label>User Status : </label>
                        <p class="d-inline-block viewStatus"> <%=uList.getUser_status()%></p>
                    </div>
                    <% if (ud != null) {%>
                    <div class="row">
                        <label>Full Name : </label>
                        <p class="d-inline-block"> <%=ud.getFirst_name()%> <%=ud.getLast_name()%></p>
                    </div>
                    <div class="row">
                        <label>DOB : </label>
                        <p class="d-inline-block"> <%=ud.getDate_of_birth()%></p>
                    </div>
                    <div class="row">
                        <label>Address Line 1 : </label>
                        <p class="d-inline-block"> <%=ud.getAddressLine1()%></p>
                    </div>
                    <div class="row">
                        <label>Address Line 2 : </label>
                        <p class="d-inline-block"> <%=ud.getAddressLine2()%></p>
                    </div>
                    <div class="row">
                        <label>Contact Number : </label>
                        <p class="d-inline-block"> <%=ud.getContact_number()%></p>
                    </div>
                    <%}%>
                </div>
                <% }
                        j++;

                    }%>
            </div>
        </div>
    </div>
</div>
<jsp:include page="footer.jsp" />  
