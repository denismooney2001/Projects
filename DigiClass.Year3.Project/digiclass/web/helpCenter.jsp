<%-- 
    Document   : helpCenter
    Created on : May 6, 2021, 1:58:20 PM
    Author     : denis
--%>
<%@page import="java.text.DecimalFormat"%>
<%@page import="java.time.Period"%>
<%@page import="java.time.LocalDate"%>
<%@page import="java.util.ArrayList"%>
<%@page import="Business.*"%>
<%@page import="Daos.*"%>
<%   UserDao userDao = new UserDao("digiclass");

    String username = (String) session.getAttribute("username");
    User u = userDao.getUserByUsername(username);
    String userType = u.getUser_type();
%>

<jsp:include page="header.jsp" />  
<jsp:include page="navbar.jsp" />  
<div class="container text-left mb-5 mt-5 p-5 font">
    <p id="userType" class="d-none"><%=u.getUser_type()%></p>
    <h1 class="text-center">Help Center</h1>
    <br>
    <h3>Ask for Help!</h3>
    <p>If you are experiencing any problems with a Student/Teacher i.e, not showing up for classes; Contact <b>digiclassdtc@gmail.com</b></p>
    <br>
    <h3>Frequently Asked Questions (FAQ's)</h3>
    <br>
    <%if(userType.equals("TEACHER")){%>
    <h2>Teachers:</h2>
    
    <br>
    <p class="text-danger">Q: How long do Courses usually last?</p>
    <p class="text-primary">A: Any Leaving Cert. grinds course can last anywhere between 3-12 months.</p>
    <br>
    <p class="text-danger">Q: How do I know if I have been paid?</p>
    <p class="text-primary">A: The last Thursday of each month.</p>
    <%}else if(userType.equals("STUDENT")){ %>
    <h2>Students: </h2>
    <br>
    <p class="text-danger">Q: Is it worth signing up for a Course on the site?</p>
    <p class="text-primary">A: Yes! There has been an increased in higher grades for students who have applied for grinds courses on DigiClass!</p>
    <br>
    <p class="text-danger">Q: How long does each class last?</p>
    <p class="text-primary">A: 1 Hour. Unless the Teacher has another class booked right after that class.</p>
    <br>
    <%} %>
</div>
<jsp:include page="footer.jsp" />  
