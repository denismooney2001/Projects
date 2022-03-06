<%-- 
    Document   : startCourse
    Created on : Feb 24, 2021, 7:29:35 PM
    Author     : junta
--%>
<%@page import="java.util.ArrayList"%>
<%@page import="Business.*"%>
<%@page import="Daos.*"%>
<%   UserDao userDao = new UserDao("digiclass");
    TeacherDetailsDao teacherDetailsDao = new TeacherDetailsDao("digiclass");
    String username = (String) session.getAttribute("username");
    User u = userDao.getUserByUsername(username);
    TeacherDetails teacherDetails = teacherDetailsDao.getTeacherDetailsByUserId(u.getID());
    TeacherSubjectsDao teacherSubjectsDao = new TeacherSubjectsDao("digiclass");
    CourseDao courseDao = new CourseDao("digiclass");
    ArrayList<TeacherSubjects> tsList = teacherSubjectsDao.getAllPresentTeacherSubjectsById(teacherDetails.getTeacher_id());
    ArrayList<Course> existingList = courseDao.getAllExistingCoursesByTeacherId(teacherDetails.getTeacher_id());
    SubjectDao sDao = new SubjectDao("digiclass");
    ArrayList<Subject> sub = sDao.getAllSubjects();
    ArrayList<String> selectedSub = new ArrayList<>();
    ArrayList<String> level = new ArrayList<>();
    for (Subject s : sub) {
        for (TeacherSubjects ts : tsList) {
            if (ts.getSubject_id() == s.getSubject_id()) {
                selectedSub.add(s.getSubject_name());
                level.add(ts.getSubject_level());
            }
        }
    }
    ArrayList<Integer> teacherSubjectIDList = new ArrayList<>();
    for (TeacherSubjects ts : tsList) {
        teacherSubjectIDList.add(ts.getTeacherSubjectId());
    }
    ArrayList<Integer> courseSubjectIDList = new ArrayList<>();
    for (Course c : existingList) {
        courseSubjectIDList.add(c.getTeacher_subject_id());
    }
    int i = 0;
    int counter = 0;
    for (Integer teacherSubjectID : teacherSubjectIDList) {
        if (!(courseSubjectIDList.contains(teacherSubjectID))) {
            counter++;
        }
    }
    boolean empty = false;
    if (counter == 0) {
        empty = true;
    }
%>
<jsp:include page="header.jsp" />  
<jsp:include page="navbar.jsp" /> 
<p id="userType" class="d-none"><%=u.getUser_type()%></p>
<div id="startCourse" class="container text-left mb-5 mt-5 p-5 font">
    <img class="w-100" src="image/startCourse.jpg"/>
    <% if (!empty) {%>
    <form action="controller" method="post" autocomplete="off">
        <h3>Start A Course</h3>
        <input name="action" value="startCourse" type="hidden"/>
        <div class="form-group">
            <label for="teacherSubjectId">Select Subject:</label>
            <select name="teacherSubjectId" class="form-control" required>
                <%
                    for (Integer teacherSubjectID : teacherSubjectIDList) {
                        if (!(courseSubjectIDList.contains(teacherSubjectID))) {%>
                <option value="<%=teacherSubjectID%>"><%=selectedSub.get(i)%> (<%=level.get(i)%>)</option>
                <%  }
                        i++;
                    }
                %>
            </select>
        </div>
        <div class="form-group">
            <label for="description">Description</label>   
            <textarea name="description" class="form-control" required/></textarea>
        </div>
        <div class="form-row">
            <div class="form-group col-md-6">
                <label for="startDate">Select Start Date:</label>
                <input type="text" class="form-control" id="datefield" name="startDate" onclick="testFunction()" onkeyup="myFunction()" required/>
            </div>
            <div id="selectEndDateDiv" class="form-group col-md-6">
                <label for="endDate">Select End Date:</label>
                <input type="text" class="form-control" id="endDate" min="2021-01-01" name="endDate" onclick="setMinimumCourseStartDate()" step=7 required/>
            </div>
        </div>
        <div class="form-group">
            <label for="places">Capacity</label>   
            <input max="25" min="10" class="form-control"  type="number" name="places" required/>
        </div>
        <button type="submit" id="startCourseButton" class="btn btn-primary btn-block rounded-pill" disabled>Start Course</button>
    </form>
    <%} else {%>
    <div class="container mt-5 mb-5 p-5">
        <h1 class="text-primary text-center">You Cannot Start a Course!</h1>
        <h3 class="text-secondary text-center">All your selected subjects have ongoing courses</h3> 
    </div>
    <% }%>
</div>
<jsp:include page="footer.jsp" />  