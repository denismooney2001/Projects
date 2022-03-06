<%-- 
    Document   : updateUserDetails
    Created on : Feb 17, 2021, 2:47:13 PM
    Author     : junta
--%>

<%@page import="Daos.*"%>
<%@page import="Business.*"%>
<%
    UserDao userDao = new UserDao("digiclass");
    UserDetailsDao userDetailsDao = new UserDetailsDao("digiclass");
    String username = (String) session.getAttribute("username");
    User u = userDao.getUserByUsername(username);
    String base32Secret = u.getSecret_key();
    UserDetails ud = userDetailsDao.getUserDetailsByUserId(u.getID());
%>
<form action="controller" method="post" autocomplete="off">
    <input name="action" value="updateUserDetails" type="hidden"/>
    <div class="row">
        <div class="col-md-6">
            <label class="font-weight-bold">Name:</label>
        </div>
        <div class="col-md-6">
            <p class="toCheck d-inline-block"><%=ud.getFirst_name()%></p>
            <p class="toCheck d-inline-block"><%=ud.getLast_name()%></p>
            <input type="hidden" value="<%=ud.getFirst_name()%>" name="firstName"/>
            <input type="hidden" value="<%=ud.getLast_name()%>" name="lastName"/>
        </div>
    </div>
    <div class="row">
        <div class="col-md-6">
            <label class="font-weight-bold">Email:</label>
        </div>
        <div class="col-md-6">
            <p><%=u.getEmail()%></p>
        </div>
    </div>
    <div class="row">
        <div class="col-md-6">
            <label class="font-weight-bold">Phone:</label>
        </div>
        <div class="col-md-6">
            <p id="contactNumber" class="toCheck"><%=ud.getContact_number()%></p>
            <input type="text" name="number" class="d-none form-control" id="updateNumber" value="<%=ud.getContact_number()%>"/>
        </div>
    </div>
    <div class="row">
        <div class="col-md-6">
            <label class="font-weight-bold">Address Line 1:</label>
        </div>
        <div class="col-md-6">
            <p id="address1" class="toCheck"><%=ud.getAddressLine1()%></p>
            <input type="text" name="addressLine1" class="d-none form-control" id="updateAddress1" value="<%=ud.getAddressLine1()%>"/>
        </div>
    </div>
    <div class="row">
        <div class="col-md-6">
            <label class="font-weight-bold">Address Line 2:</label>
        </div>
        <div class="col-md-6">
            <p id="address2" class="toCheck"><%=ud.getAddressLine2()%></p>
            <input type="text" name="addressLine2" class="d-none form-control" id="updateAddress2" value="<%=ud.getAddressLine2()%>"/>
        </div>
    </div>
    <div class="row">
        <div class="col-md-6">
            <label class="font-weight-bold">Date of Birth test:</label>
        </div>
        <div class="col-md-6">
            <p id="dob" class="toCheck"><%=ud.getDate_of_birth()%></p> 
            <input type="date" name="dateOfBirthTeacher" onclick="setMinimumTeacherAgeDate1()" class="d-none form-control" id="updateTeacherDOB" value="<%=ud.getDate_of_birth()%>"/>
            <input type="date" name="dateOfBirthStudent" onclick="setMinimumStudentAgeDate1()" class="d-none form-control" id="updateStudentDOB" value="<%=ud.getDate_of_birth()%>"/>
        </div>
    </div>
    <button class="btn btn-primary d-none" id="updateButton">Update</button>
</form>

<% if (!(u.getSecret_key() == null || u.getSecret_key().equals(""))) {%>
<p>Please use Google Authenticator App to Scan the QR-Code</p>
<img src="<%=TwoFactorAuthenticator.qrImageUrl(username, base32Secret)%>"/>
<%}%>