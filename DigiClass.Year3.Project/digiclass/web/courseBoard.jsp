<%-- 
    Document   : courseBoard
    Created on : Apr 14, 2021, 4:00:53 PM
    Author     : denis
--%>
<%@page import="java.util.ArrayList"%>
<%@page import="Business.*"%>
<%@page import="Daos.*"%>
<jsp:include page="header.jsp" />  
<jsp:include page="navbar.jsp" /> 
<%   UserDao userDao = new UserDao("digiclass");
    UserDetailsDao userDetailsDao = new UserDetailsDao("digiclass");
    TeacherDetailsDao teacherDetailsDao = new TeacherDetailsDao("digiclass");
    TeacherSubjectsDao teacherSubjectsDao = new TeacherSubjectsDao("digiclass");
    String username = (String) session.getAttribute("username");
    User u = new User();
    u = userDao.getUserByUsername(username);
    UserDetails userDetails = userDetailsDao.getUserDetailsByUserId(u.getID());
    int courseId = (int) session.getAttribute("viewCourseId");
    JoinDao jDao = new JoinDao("digiclass");
    boolean coursePayDue = false;
    if (u.getUser_type().equals("STUDENT")) {
        ArrayList<Course> unPaidCourses = jDao.getOverdueCourses(u.getID());

        for (Course c : unPaidCourses) {
            if (c.getCourse_id() == courseId) {
                coursePayDue = true;
            }
        }
    }

    CourseDao courseDao = new CourseDao("digiclass");
    CourseStudentsDao courseStudentsDao = new CourseStudentsDao("digiclass");
    Course course = courseDao.getCourseByID(courseId);
    TeacherSubjects teacherSubjects = teacherSubjectsDao.getTeacherSubjectById(course.getTeacher_subject_id());
    SubjectDao subDao = new SubjectDao("digiclass");
    int teacherId = teacherSubjects.getTeacher_id();
    CourseLinkDao cLinkDao = new CourseLinkDao("digiclass");
    String courseLink = cLinkDao.getLink(course.getCourse_id());

    String subjectName = subDao.getSubjectNamebyId(teacherSubjectsDao.getSubjectIdByTeacherSubjectId(course.getTeacher_subject_id()));
    String subjectLevel = teacherSubjects.getSubject_level();
    TeacherDetails tDetails = teacherDetailsDao.getTeacherDetailsByTeacherId(teacherId);
    UserDetails teacherDetails1 = userDetailsDao.getUserDetailsByUserId(tDetails.getUser_id());

    ArrayList<Integer> studentIds = courseStudentsDao.getStudentIdsInCourse(courseId);
    ArrayList<Timetable> timeTableList = jDao.getTimeTablesbyCourse(courseId);
    CourseMessageDao courseMessageDao = new CourseMessageDao("digiclass");
    ArrayList<CourseMessage> courseMessageList = courseMessageDao.getAllMessagesByPublishDateCourseId(courseId);
%>

<p id="userType" class="d-none"><%=u.getUser_type()%></p>
<div class="container-fluid font">
    <%if (u.getUser_type().equals("STUDENT") && coursePayDue == true) {%>
    <div class="container mt-5 mb-5 p-5 text-center">
        <h1>Payment Overdue!</h1>
        <br>
        <div class="border border-5 border-info  p-1">
            <h2 class="text-center"><%=teacherDetails1.getFirst_name()%> <%=teacherDetails1.getLast_name()%> - <b><%=subjectName%> (<%=subjectLevel%>)</b></h2><br/>
            <h5 class="text-center">Start Date: <%=course.getStart_date()%> &nbsp;&nbsp;&nbsp;End Date: <%=course.getEnd_date()%></h5><br>
        </div>
        <p>In order to access this course you must be up to date with your monthly course payment.</p>
        <form action="controller" method="post" autocomplete="off">
            <input name="action" value="goToPayCourseFee" type="hidden"/>
            <input name="courseId" value="<%=courseId%>" type="hidden"/>
            <input value="Pay Now" type="submit" class="btn btn-primary rounded-sm">
        </form>
    </div>
    <%} else { %>
    <div class="row">
        <div class="col-lg-2 col-12 bg-info order-lg-0 order-1 mb-5 mt-3 p-5 text-white text-center">
            <h5>Class List (Teacher And Students)</h5>
            <ul>
                <%for (int studentId : studentIds) {%>
                <li><%=userDetailsDao.getUserDetailsByUserId(studentId).getFirst_name()%> <%=userDetailsDao.getUserDetailsByUserId(studentId).getLast_name()%></li>
                    <% }%>
            </ul>
        </div>

        <div class="col-lg-8 col-12 order-lg-1 order-0 bg-white container text-left mb-5 mt-3 p-5">
            <h3 class="text-center"><%=teacherDetails1.getFirst_name()%> <%=teacherDetails1.getLast_name()%> - <b><%=subjectName%> (<%=subjectLevel%>)</b></h3>
            <h5 class="text-center">Start Date: <%=course.getStart_date()%> &nbsp;&nbsp;&nbsp;End Date: <%=course.getEnd_date()%></h5>
            <%if (courseLink == "") {%>
            <h5>Link to Online Class: [No Course Link Available]</h5> 
            <%} else {%>
            <h5>Link to Online Class: <a href="<%=courseLink%>" target="_blank"><%=courseLink%></a></h5> 
            <%}%>             
            
            <%if (u.getUser_type().equals("TEACHER")) {%>
            <input type="button" id="editCourseLink" class="btn btn-primary mb-2" onclick="hideShowCourseLink()" value="Edit">
            <div id="pasteCourseLink" class="d-none">
                <form action="controller" method="post" autocomplete="off">
                    <input name="action" value="updateCourseLink" type="hidden"/>
                    <input type="hidden" value="<%=courseId%>" name="courseId" />
                    <input class="form-control mb-2" type="text" name="courseLink" />
                    <input type="submit" class="btn btn-success" />
                </form>
            </div>
            <%} %>
            
            <h2 class="text-center">Message Board &#709;</h2>
            <br>
            <%for (CourseMessage courseM : courseMessageList) {%>
            <div class="border border-info p-2">
                <h5><b><%=courseM.getDate()%></b></h5>  
                <p> Message: <%=courseM.getMessage()%></p>  
            </div>
            <br>
            <% }%>

</div>
        <div class="col-lg-2 col-12 order-lg-2 order-2 bg-warning mb-5 mt-3 p-5 text-center">
            <h5>Class Times</h5>
            <br>
            <%if (timeTableList != null) { %>
            <ul class="text-left">
                <%for (Timetable t : timeTableList) {%>
                <li class="h5"><%=t.getDay()%>  <%=t.getTime()%>:00</li>
                    <% } %>
            </ul>  
            <% }%>
        </div>
    </div>
    <%}%>
</div>
<jsp:include page="footer.jsp" />  
