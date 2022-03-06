<%-- 
    Document   : viewTeacherCourses
    Created on : Apr 23, 2021, 1:32:33 PM
    Author     : junta
--%>

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
    String username = (String) session.getAttribute("username");
    User u = userDao.getUserByUsername(username);
    ArrayList<User> usList = userDao.getAllUsers();
    TeacherDetailsDao teacherDetailsDao = new TeacherDetailsDao("digiclass");
    TeacherSubjectsDao teacherSubDao = new TeacherSubjectsDao("digiclass");
    SubjectDao subDao = new SubjectDao("digiclass");
    JoinDao joinDao = new JoinDao("digiclass");
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
                            TeacherDetails tDetails = teacherDetailsDao.getTeacherDetailsByUserId(uList.getID());
                            ArrayList<Course> teacherCourses = joinDao.getAllCoursesByTeacherId(tDetails.getTeacher_id());

                %>
                <div class="tab-pane fade show text-white p-3 verify-tab" id="list-verify-<%=j%>" role="tabpanel" aria-labelledby="list-home-list">
                    <%if (!(teacherCourses.isEmpty())) { %>
                    <%for (Course c : teacherCourses) {%>
                    <div class="row">
                        <label>Course ID: </label>
                        <p class="d-inline-block"> <%=c.getCourse_id()%></p>
                    </div>
                    <div class="row">
                        <label>Subject: </label>
                        <p class="d-inline-block"> <%=subDao.getSubjectNamebyId(teacherSubDao.getSubjectIdByTeacherSubjectId(c.getTeacher_subject_id()))%> (<%=teacherSubDao.getTeacherSubjectById(teacherSubDao.getTeacherSubjectId(tDetails.getTeacher_id(), teacherSubDao.getSubjectIdByTeacherSubjectId(c.getTeacher_subject_id()))).getSubject_level()%>)</p>
                    </div>
                    <div class="row">
                        <label>Start Date: </label>
                        <p class="d-inline-block"> <%=c.getStart_date()%></p>
                    </div>
                    <div class="row">
                        <label>End Date: </label>
                        <p class="d-inline-block"> <%=c.getEnd_date()%></p>
                    </div>
                    <div class="row">
                        <label>Description: </label>
                        <p class="d-inline-block"><%=c.getCourse_description()%></p>
                    </div>
                    <hr>
                    <%}%>
                    <%} else { %>
                    <h5>[No Available Courses]</h5>
                    <% } %>
                </div>
                <%}
                        j++;

                    }%>
            </div>
        </div>
    </div>
</div>
<jsp:include page="footer.jsp" />  
