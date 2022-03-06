<%-- 
    Document   : payOngoingStudentFees
    Created on : Apr 7, 2021, 11:09:55 PM
    Author     : denis
--%>

<%@page import="java.util.Map"%>
<%@page import="java.util.HashMap"%>
<%@page import="java.util.ArrayList"%>
<%@page import="Business.*"%>
<%@page import="Daos.*"%>
<%   UserDao userDao = new UserDao("digiclass");

    String username = (String) session.getAttribute("username");
    User u = userDao.getUserByUsername(username);
    UserDetailsDao userDetDao = new UserDetailsDao("digiclass");

    JoinDao joinDao = new JoinDao("digiclass");

    TeacherDetailsDao teacherDetDao = new TeacherDetailsDao("digiclass");
    ReviewsDao revDao = new ReviewsDao("digiclass");
    ArrayList<Reviews> getReviews = revDao.getAllReviewsTeacherId(teacherDetDao.getTeacherDetailsByUserId(u.getID()).getTeacher_id());

    String studentName = "";
%>
<jsp:include page="header.jsp" />  
<jsp:include page="navbar.jsp" />  
<p id="userType" class="d-none"><%=u.getUser_type()%></p>
<div class="container text-left mb-5 mt-5 p-5 font">
    <img class="w-100" src="image/teacherPeformance.jpg"/>
    <h2>View Reviews</h2>
    <br>
    <%if(getReviews != null){ %>
    <%for(Reviews r: getReviews){ 
    studentName = userDetDao.getUserDetailsByUserId(r.getStudent_id()).getFirst_name() + " " +userDetDao.getUserDetailsByUserId(r.getStudent_id()).getLast_name();
    %>
    <div class="border border-info p-2 mb-2 bg-info text-white">
        <h6><b>Student: </b><%=studentName%></h6> 
        <h6><b>Date: </b><%=r.getDate()%></h6> 
        <p><b>Rating: </b><%=r.getRatingOutOfTen()%>/10 </p>
        <p> Message: <%=r.getDescription() %></p>  
    </div>
    <br>
    <%} %>
    <%}else{ %>
    <h4 class="text-danger">*** Unfortunately, there are no reviews for you yet. When Students eventually do submit a review this is where it will be displayed.</h4>
    <%} %>
</div>
<jsp:include page="footer.jsp" />  