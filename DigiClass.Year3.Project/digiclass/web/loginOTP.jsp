<%-- 
    Document   : resetPassword
    Created on : Apr 21, 2021, 2:14:42 PM
    Author     : junta
--%>
<%@page import="Business.*"%>
<%@page import="Daos.*"%>

<jsp:include page="header.jsp" />  
<jsp:include page="navbar.jsp" />    
<%
    UserDao userDao = new UserDao("digiclass");
    response.setIntHeader("Refresh", 30);
    boolean login = (boolean) session.getAttribute("login");
    String username = (String) session.getAttribute("username");
    User u = userDao.getUserByUsername(username);
%>

<%if(login == true){%>
<p id="userType" class="d-none"><%=u.getUser_type()%></p>
<%}else{%>
<p id="userType" class="d-none"></p>
<%}%>
<div id="startCourse" class="container text-left mb-5 mt-5 font">
<div class="container mt-5 mb-5 p-5 text-center">
    <h5 class="text-center">One More Step ....</h5>
    <form action="controller" method="post" autocomplete="off">
        <input name="action" value="2-auth" type="hidden"/>
        <div class="form-group">
            <label for="OTP">Open the Google Authenticator App</label>
            <input type="text" class="form-control" name="OTP" placeholder="Enter 6 digit verification code" pattern="\d{6}" required>
            <button class="btn btn-primary mt-3">Submit</button>
        </div>
    </form>
</div>
</div>
<jsp:include page="footer.jsp" />  