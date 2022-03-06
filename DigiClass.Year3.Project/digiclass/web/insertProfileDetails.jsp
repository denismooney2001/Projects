<%-- 
    Document   : editProfile
    Created on : Feb 7, 2021, 3:58:51 PM
    Author     : junta
--%>
<%@page import="Business.*"%>
<%@page import="Daos.*"%>
<%
    UserDao userDao = new UserDao("digiclass");
    String username = (String) session.getAttribute("username");
    User u = userDao.getUserByUsername(username);
%>
<form action="controller" method="post" autocomplete="off">
    <input name="action" value="insertProfileDetails" type="hidden">
    <div class="form-row">
        <div class="form-group col-md-6">
            <input type="hidden" name="userType" value="<%=u.getUser_type()%>" id="userType">

            <label for="firstName">First Name</label>
            <input type="text" class="form-control" name="firstName" id="firstName" placeholder="First Name" required>
        </div>
        <div class="form-group col-md-6">
            <label for="lastName">Last Name</label>
            <input type="text" class="form-control" name="lastName" id="lastName" placeholder="Last Name" required>
        </div>
    </div>
    <div class="form-group">
        <label for="number">Contact Number</label>
        <input type="text" class="form-control" name="number" id="number" placeholder="Phone No" required>
    </div>
    <div class="form-row">
        <div class="form-group col-md-6">
            <label for="addressLine1">Address Line 1</label>
            <input type="text" class="form-control" name="addressLine1" id="addressLine1" placeholder="Address Line 1" required>
        </div>
        <div class="form-group col-md-6">
            <label for="addressLine2">Address Line 2</label>
            <input type="text" class="form-control" name="addressLine2" id="addressLine2" placeholder="Address Line 2">
        </div>
    </div>
    <div class="form-group">
        <label for="dateOfBirth">Birth Date</label>
        <input type="date" class="form-control d-none" name="dateOfBirthTeacher" onclick="setMinimumTeacherAgeDate()" id="dateOfBirthTeacher" required>
        <input type="date" class="form-control d-none" name="dateOfBirthStudent" onclick="setMinimumStudentAgeDate()" id="dateOfBirthStudent" required>
    </div>
    <button type="submit" class="btn btn-primary btn-block rounded-pill">Submit</button>
</form>
