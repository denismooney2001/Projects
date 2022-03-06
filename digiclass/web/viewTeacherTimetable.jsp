<%-- 
    Document   : viewTeacherTimetable
    Created on : Apr 23, 2021, 1:28:49 PM
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
    JoinDao joinDao = new JoinDao("digiclass");
    SubjectDao subDao = new SubjectDao("digiclass");
    TeacherSubjectsDao teacherSubDao = new TeacherSubjectsDao("digiclass");
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
                            ArrayList<Timetable> ongoingTimetable = joinDao.getOngoingTeacherTimeTables(tDetails.getTeacher_id());
                %>
                <div class="tab-pane fade show text-white p-3 verify-tab" id="list-verify-<%=j%>" role="tabpanel" aria-labelledby="list-home-list">
                    <%if (!(ongoingTimetable.isEmpty())) { %>
                    <ul class="text-left">
                        <%for (Timetable t : ongoingTimetable) {

                                int courseId = t.getCourse_id();
                                CourseDao courseDao = new CourseDao("digiclass");
                                Course c = courseDao.getCourseByID(courseId);

                        %>
                        <li class="h5"><%=subDao.getSubjectNamebyId(teacherSubDao.getSubjectIdByTeacherSubjectId(c.getTeacher_subject_id()))%>  -   <%=t.getDay()%>, <%=t.getTime()%>:00</li>
                            <% } %>
                    </ul>  
                    <% } else {%>
                    <h5>[No Available Timetable]</h5>
                    <%}%>

                </div>
                <%}
                        j++;

                    }%>
            </div>
        </div>
    </div>
</div>
<jsp:include page="footer.jsp" />  
