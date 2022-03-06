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
    JoinDao joinDao = new JoinDao("digiclass");
    ArrayList<Course> unPaidCourses = joinDao.getOverdueCourses(u.getID());
    TeacherSubjectsDao teacherSubDao = new TeacherSubjectsDao("digiclass");
    SubjectDao subjectDao = new SubjectDao("digiclass");
    UserPaymentDao userPaymentDao = new UserPaymentDao("digiclass");
%>
<jsp:include page="header.jsp" />  
<jsp:include page="navbar.jsp" />  
<div class="container text-left mb-5 mt-5 p-5 font">
    <img class="w-100" src="image/paymentOverdue.jpg"/>
    <%if (!(unPaidCourses.isEmpty())) {%>
    <h2>Pay Unpaid Course Fees</h2>
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
                    <th>Pay</th>  
                </tr> 
            </thead>
            <tbody>
                <%for (Course c : unPaidCourses) {%>
            <form action="controller" method="post" autocomplete="off">
                <input name="action" value="goToPayCourseFee" type="hidden"/>
                <input name="courseId" value="<%=c.getCourse_id()%>" type="hidden"/>
                <tr>
                    <td><%=c.getCourse_id()%></td>
                    <td><%=subjectDao.getSubjectNamebyId(teacherSubDao.getSubjectIdByTeacherSubjectId(c.getTeacher_subject_id()))%></td>
                    <td>(<%=teacherSubDao.getTeacherSubjectById(c.getTeacher_subject_id()).getSubject_level()%>)</td>
                    <td><%=c.getStart_date()%></td>
                    <td><%=c.getEnd_date()%></td>
                    <td><input value="Pay Now" type="submit" class="btn btn-primary rounded-sm"></td> 
                </tr>    
            </form>
            <% }%>  
            </tbody>
        </table> 
    </div>
    <%} else {%>
    <h2>You are Up-To-Date with your Payments!</h2>
    <%}%>
</div>
<jsp:include page="footer.jsp" />  