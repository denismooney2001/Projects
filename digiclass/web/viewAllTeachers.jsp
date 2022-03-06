<%-- 
    Document   : viewAllTeachers
    Created on : Mar 4, 2021, 1:16:58 PM
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
    TeacherDetailsDao teacherDetailsDao = new TeacherDetailsDao("digiclass");
    int i = 0;
    int j = 0;
%> 
<p id="userType" class="d-none"><%=u.getUser_type()%></p>
<div class=" container text-left mb-5 mt-5 p-5 font">
    <img class="w-100" src="image/viewTeachers.jpg"/>
    <div class="mt-3">
        <h3>Teachers Lists</h3>
    </div>
    <div class="row">
        <div class="col-lg-4 col-12">
            <div class="list-group" id="list-tab" role="tablist">
                <%for (User ulist : usList) {
                        if (ulist.getUser_type().equals("TEACHER")) {%>
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
                        if (uList.getUser_type().equals("TEACHER")) {
                            UserDetails ud = userDetailsDao.getUserDetailsByUserId(uList.getID());
                            TeacherDetails tDetails = teacherDetailsDao.getTeacherDetailsByUserId(uList.getID());
                %>
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
                        <p id="viewType" class="d-inline-block"> <%=uList.getUser_type()%></p>
                    </div>
                    <div class="row">
                        <label>User Status : </label>
                        <p id="viewStatus" class="d-inline-block"> <%=uList.getUser_status()%></p>
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
                    <div class="row">
                        <label>Qualifications : </label>
                        <p class="d-inline-block"> <% if (!(tDetails.getQualifications().equals("") || tDetails.getQualifications() == null)) {%>
                            <img class="img-thumbnail w-75 p-3" src='image/teachersQualifications/<%=tDetails.getQualifications()%>'>
                            <% } else { %>
                        <p>[No Photo Available]</p>
                        <% }%></p>
                    </div>
                    <div class="row">
                        <label>About Me :  </label>
                        <p class="d-inline-block"> <%=tDetails.getAbout_me()%> </p>
                    </div>
                </div>
                <%}
                        j++;

                    }%>
            </div>
        </div>
    </div>
</div>
<jsp:include page="footer.jsp" />  
