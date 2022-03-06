<%-- 
    Document   : searchForCourse
    Created on : Mar 3, 2021, 2:46:59 PM
    Author     : junta
--%>
<%@page import="java.util.ArrayList"%>
<%@page import="Business.*"%>
<%@page import="Daos.*"%>
<%   UserDao userDao = new UserDao("digiclass");
    TeacherDetailsDao teacherDetailsDao = new TeacherDetailsDao("digiclass");
    TeacherSubjectsDao teacherSubjectsDao = new TeacherSubjectsDao("digiclass");   
    String username = (String) session.getAttribute("username");
    User u = userDao.getUserByUsername(username);  
    ArrayList<TeacherDetails> tsList = teacherDetailsDao.getAllVerifiedTeachers();
    UserDetailsDao userDetailsDao = new UserDetailsDao("digiclass");
    ArrayList<TeacherSubjects> teacherSubjectList = new ArrayList<>();
    ArrayList<String> udList = new ArrayList<>();
    for (TeacherDetails verified : tsList) {
        teacherSubjectList = teacherSubjectsDao.getAllTeacherSubjectsById(verified.getTeacher_id());
        udList.add(userDetailsDao.getUserDetailsByUserId(verified.getUser_id()).getFirst_name());
    }

%>
<jsp:include page="header.jsp" />  
<jsp:include page="navbar.jsp" />  

<p id="userType" class="d-none"><%=u.getUser_type()%></p>
<div class="container text-left mb-5 mt-5 p-5 font">
    <img class="w-100" src="image/test.jpg"/>
    <h1>Search</h1>
    <label for="teacher">Search By Teachers : </label>
    <select id="submitValue" name="teacher" class="form-control" required>
        <%for (TeacherDetails verified : tsList) {
                UserDetails ud = userDetailsDao.getUserDetailsByUserId(verified.getUser_id());%>
        <option value="<%=verified.getTeacher_id()%>"><%=ud.getFirst_name() + " " + ud.getLast_name()%></option>
        <%}%>
    </select>
    <button type="submit" onclick="search()" id="searchCourse" class="btn btn-primary mt-3 rounded-pill">Search...</button>
    <div class="d-none border mt-5 text-left" id="result">
        <hr>
        <div class="row">
            <h5 class="col-lg-2 col">Teacher Name</h5>
            <p id="teacherName" class="col-lg-10"></p>
        </div>
        <div class="row">
            <h5 class="col-lg-2 col">Subject</h5>
            <p id="subjectName" class="col-lg-10"></p>
        </div>

        <div class="row">
            <h5 class="col-lg-2 col">About Me</h5>
            <p id="aboutMe" class="col-lg-10"></p>
        </div>
        <a class="btn btn-primary" href="searchSpecificTeacher.jsp">Select</a>
    </div>
</div>
<br>
<br>
<br>
<br>
<jsp:include page="footer.jsp" /> 