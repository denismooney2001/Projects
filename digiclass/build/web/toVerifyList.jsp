<%-- 
    Document   : toVerifyList
    Created on : Feb 15, 2021, 6:52:31 PM
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
    TeacherDetailsDao teacherDetailsDao = new TeacherDetailsDao("digiclass");
    ArrayList<TeacherDetails> tdList = teacherDetailsDao.getAllNonVerifiedTeachers();
    int i = 0;
    int j = 0;
%> 
<p id="userType" class="d-none"><%=u.getUser_type()%></p>
<div class=" container text-left mb-5 mt-5 p-5 font">
    <img class="w-100" src="image/verify.jpg"/>
    <div class="mt-3">
        <h3>Teachers to Verify</h3>
    </div>
    <div class="row">
        <div class="col-lg-4 col-12">
            <div class="list-group" id="list-tab" role="tablist">
                <%for (TeacherDetails td : tdList) {
                        UserDetails userDetails = userDetailsDao.getUserDetailsByUserId(td.getUser_id());
                %>
                <a class="list-group-item list-group-item-action" id="list-home-list" data-toggle="list" href="#list-verify-<%=i%>" role="tab" aria-controls="home">
                    <%=userDetails.getFirst_name() + userDetails.getLast_name()%>
                </a>
                <%
                        i++;
                    }%>
            </div>
        </div>
        <div class="col-lg-8 col-12">
            <div class="tab-content" id="nav-tabContent">
                <% for (TeacherDetails td : tdList) {
                        UserDetails userDetails = userDetailsDao.getUserDetailsByUserId(td.getUser_id());
                %>
                <div class="tab-pane fade show text-white p-3 verify-tab" id="list-verify-<%=j%>" role="tabpanel" aria-labelledby="list-home-list">
                    <form action="controller" method="post" autocomplete="off">
                        <input type="hidden" name="userId" value="<%=userDetails.getUser_id()%>"/>
                        <h2>User Information</h2>
                        <p>Full Name : <%=userDetails.getFirst_name() + " " + userDetails.getLast_name()%></p>
                        <p>Date Of Birth : <%=userDetails.getDate_of_birth()%></p>
                        <p>Address Line 1 : <%=userDetails.getAddressLine1()%></p>
                        <p>Address Line 2 : <%=userDetails.getAddressLine2()%></p>
                        <p>Contact Number : <%=userDetails.getContact_number()%></p>
                        <p>Qualifications : 
                            <% if (!(td.getQualifications().equals("") || td.getQualifications() == null)) {%>
                            <img class="img-thumbnail w-75 p-3" src='image/teachersQualifications/<%=td.getQualifications()%>'>
                            <% } else { %>
                            [No Photo Available]
                            <% }%>
                        </p>
                        <p>About Me : <%=td.getAbout_me()%></p>
                        <div>
                            <button class="btn btn-primary" name="action" value="verify">Verify</button>  
                            <button class="btn btn-danger" name="action" value="nonVerify">Non-Verify</button>                              
                        </div>
                    </form>
                </div>
                <%j++;
                    }%>
            </div>
        </div>
    </div>
</div>
<br><br><br><br><br><br><br><br>
<jsp:include page="footer.jsp" />  
