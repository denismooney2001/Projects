<%-- 
    Document   : payOngoingStudentFees
    Created on : Apr 7, 2021, 11:09:55 PM
    Author     : denis
--%>

<%@page import="java.util.ArrayList"%>
<%@page import="Business.*"%>
<%@page import="Daos.*"%>
<%   UserDao userDao = new UserDao("digiclass");

    String username = (String) session.getAttribute("username");
    User u = userDao.getUserByUsername(username);
    TeacherDetailsDao teacherDetailsDao = new TeacherDetailsDao("digiclass");
    CourseStudentsDao courseStudentDao = new CourseStudentsDao("digiclass");
    
    JoinDao joinDao = new JoinDao("digiclass");
    ArrayList<Course> ongoingCourses = joinDao.getAllOngoingCoursesByTeacherId(teacherDetailsDao.getTeacherDetailsByUserId(u.getID()).getTeacher_id());
    ArrayList<Course> coursesWithoutStudents = new ArrayList<>();
    
    for(Course c: ongoingCourses){
        if(courseStudentDao.getCourseCount(c.getCourse_id()) == 0){
            coursesWithoutStudents.add(c);
            
        }
    }
    TeacherSubjectsDao teacherSubDao = new TeacherSubjectsDao("digiclass");
    SubjectDao subjectDao = new SubjectDao("digiclass");

    UserPaymentDao userPaymentDao = new UserPaymentDao("digiclass");

%>
<jsp:include page="header.jsp" />  
<jsp:include page="navbar.jsp" />  
<div class="container text-left mb-5 mt-5 p-5 font">
    
    <img class="w-100" src="image/deleteTrashCan.jpg"/>
    <h2>Disable Courses</h2>
    <%if(!(coursesWithoutStudents.isEmpty())){ %>
    <h5 class="text-danger font-weight-bold">** You can Disable Courses as long as nobody is signed up for them so far!</h5>
    <p id="userType" class="d-none"><%=u.getUser_type()%></p>
    
    <br>
    <div id="container">
        <table class="table table-hover">
            <thead class="thead-dark">
                <tr>
                    <th>Course ID</th>
                    <th>Subject</th>
                    <th>Level</th>
                    <th>Start Date</th>
                    <th>End Date</th>
                    <th>Disable Course</th>  
                </tr> 
            </thead>
            <tbody>
                <%for (Course c : coursesWithoutStudents) {%>
            <form action="controller" method="post" autocomplete="off">
                <input name="action" value="disableCourse" type="hidden"/>
                <input name="courseId" value="<%=c.getCourse_id()%>" type="hidden"/>
                <tr>
                    <td><%=c.getCourse_id()%></td>
                    <td><%=subjectDao.getSubjectNamebyId(teacherSubDao.getSubjectIdByTeacherSubjectId(c.getTeacher_subject_id()))%></td>
                    <td>(<%=teacherSubDao.getTeacherSubjectById(c.getTeacher_subject_id()).getSubject_level()%>)</td>
                    <td><%=c.getStart_date()%></td>
                    <td><%=c.getEnd_date()%></td>
                    <td><input value="Disable" type="submit" class="btn btn-primary rounded-sm"></td> 
                </tr>    
            </form>
            
            <% }%>  
            </tbody>


        </table> 
    </div>
            <%}else{%>
            <h5 class="text-danger font-weight-bold">*** Unfortunately, All of your course(s) contain student members making it impossible to Disable theses courses. </h5>
<%}%>

</div>
<jsp:include page="footer.jsp" />  