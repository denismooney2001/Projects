<%-- 
    Document   : navbar
    Created on : Feb 4, 2021, 9:36:48 AM
    Author     : junta
--%>
<%@page import="Daos.*"%>
<%@page import="Business.*"%>
<%@page import="java.util.ArrayList"%>
<%
    UserDao userDao = new UserDao("digiclass");
    UserDetailsDao userDetailsDao = new UserDetailsDao("digiclass");
    TeacherDetailsDao teacherDetailsDao = new TeacherDetailsDao("digiclass");
    TeacherSubjectsDao teacherSubjectsDao = new TeacherSubjectsDao("digiclass");
    JoinDao joinDao = new JoinDao("digiclass");
    CourseDao courseDao = new CourseDao("digiclass");
    SubjectDao sDao = new SubjectDao("digiclass");
    String username = (String) session.getAttribute("username");
    User u = null;
    TeacherDetails teacherDetails = null;
    UserDetails userDetails = null;
    int verifyCount = 0;
    ArrayList<Course> courseList = new ArrayList<>();
    ArrayList<Timetable> timetableList = new ArrayList<>();
    ArrayList<TeacherSubjects> tsList = new ArrayList<>();
    ArrayList<Course> cList = new ArrayList<>();
    ArrayList<String> selectedSub = new ArrayList<>();
    ArrayList<Integer> sID = new ArrayList<>();
    ArrayList<Subject> sub = sDao.getAllSubjects();
    ArrayList<Integer> cID = new ArrayList<>();
    if (!(session.getAttribute("userId") == null || session.getAttribute("userId").equals(""))) {
        u = userDao.getUserByUsername(username);
        if (u.getUser_type().equals("TEACHER")) {
            teacherDetails = teacherDetailsDao.getTeacherDetailsByUserId(u.getID());
            if (teacherDetails != null) {
                courseList = joinDao.getAllOngoingCoursesByTeacherId(teacherDetails.getTeacher_id());
                timetableList = joinDao.getOngoingTeacherTimeTables(teacherDetails.getTeacher_id());
                tsList = teacherSubjectsDao.getAllPresentTeacherSubjectsById(teacherDetails.getTeacher_id());
                for (Course cs : courseList) {
                    sID.add(cs.getTeacher_subject_id());
                }
                for (TeacherSubjects ts : tsList) {
                    for (Integer tId : sID) {
                        for (Subject s : sub) {
                            if (ts.getTeacherSubjectId() == tId && ts.getSubject_id() == s.getSubject_id()) {
                                selectedSub.add(s.getSubject_name() + " (" + ts.getSubject_level() + ")");

                            }
                        }
                    }
                }
            }
        } else if (u.getUser_type().equals("STUDENT")) {
            userDetails = userDetailsDao.getUserDetailsByUserId(u.getID());
            cList = joinDao.getStudentsOngoingCourses(u.getID());
            tsList = teacherSubjectsDao.getAll();
            if (cList.size() > 0) {
                for (Course c : cList) {
                    cID.add(c.getCourse_id());
                }
                for (Integer cId : cID) {
                    Course c = courseDao.getCourseByID(cId);
                    sID.add(c.getTeacher_subject_id());
                }
                for (TeacherSubjects ts : tsList) {
                    for (Integer tId : sID) {
                        for (Subject s : sub) {
                            if (ts.getTeacherSubjectId() == tId) {
                                if (ts.getSubject_id() == s.getSubject_id()) {
                                    selectedSub.add(s.getSubject_name() + " (" + ts.getSubject_level() + ")");
                                }
                            }
                        }
                    }
                }
            }
        } else {
            verifyCount = teacherDetailsDao.getAllNonVerifiedTeachers().size();
        }
    }


%> 
<nav id="navbar" class="navbar navbar-expand-lg navbar-light bg-dark">
    <div class="container">
        <% if (session.getAttribute("userId") == null || session.getAttribute("userId").equals("") || (boolean) session.getAttribute("login") == false) {%>
        <a id="title" class="navbar-brand" href="index.jsp"><img src="./image/flag.png"/> DigiClass</a>
            <%} else {%>
        <a id="title" class="navbar-brand" href="home.jsp"><img src="./image/flag.png"/> DigiClass</a>
            <%}%>
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarNav">
            <ul class="nav navbar-nav ml-auto mr-5">
                <li class="nav-item ">
                    <% if (session.getAttribute("userId") == null || session.getAttribute("userId").equals("")) {%>
                    <a class="nav-link text-light" href="#loginRegisterContainer">Home <span class="sr-only">(current)</span></a>
                    <%} else if ((boolean) session.getAttribute("login") == false || session.getAttribute("userId") == null || session.getAttribute("userId").equals("")) {%>
                    <a class="nav-link text-light" href="index.jsp">Home <span class="sr-only">(current)</span></a>
                    <%} else {%>
                    <a class="nav-link text-light" href="home.jsp">Home <span class="sr-only">(current)</span></a>
                    <%}%>
                </li>
                <li id="feature-tab" class="nav-item">
                    <a class="nav-link  text-light" href="#feature">Features</a>
                </li>
                <li id="contact-tab" class="nav-item">
                    <a class="text-light nav-link" href="#contactUs">Contact Us</a>
                </li>
                <li id="teacher-tab" class="nav-item d-none">
                    <div class="dropdown">
                        <button class="i-dropdown" type="button" id="dropdownMenuButton" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                            <a class="nav-link text-light">Teacher</a>
                        </button>
                        <div class="dropdown-menu" aria-labelledby="dropdownMenuButton">
                            <%if (teacherDetails != null) {
                                    if (teacherDetails.isVerify()) {%>
                                    <a class="dropdown-item" href="startCourse.jsp"><b>Start A Course</b></a>
                            <%if (courseList.size() > 0) {%>
                            <a class="dropdown-item" href="disableCourses.jsp">Disable Courses</a>
                            <a class="dropdown-item" href="updateCourseDetails.jsp">Update Courses Details</a>
                            <%}
                                if (tsList.size() > 0) {%>
                            <a class="dropdown-item" href="updateTeacherSubjects.jsp">Update Teacher Subjects</a>
                            <%}
                                if (timetableList.size() > 0) {%>
                            <a class="dropdown-item" href="cancelClass.jsp">View Timetable</a>
                            <%} %>
                            <a class="dropdown-item" href="viewReviews.jsp">Reviews</a>
                            <%} else {%>
                            <a class="dropdown-item" href="profile.jsp">Please Wait For Admin to Verify</a>    
                            <%}
                            } else {%>
                            <a class="dropdown-item" href="profile.jsp">Please Fill in Your Teacher Details</a>    
                            <%}%>
                        </div>
                    </div>
                </li>
                <li id="admin-tab" class="nav-item d-none">
                    <div class="dropdown mt-1">
                        <button class="i-dropdown text-light" type="button" id="dropdownMenuButton" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                            Admin
                        </button>
                        <div class="dropdown-menu" aria-labelledby="dropdownMenuButton">
                            <a class="dropdown-item" href="toVerifyList.jsp">Verify Teacher <span class="badge badge-primary badge-pill"><%=verifyCount%></span></a>
                            <a class="dropdown-item" href="changeUserStatus.jsp">Change User Status</a>
                            <a class="dropdown-item" href="viewAllUser.jsp">View All User</a>
                            <a class="dropdown-item" href="viewAllTeachers.jsp">View All Teachers</a>
                            <a class="dropdown-item" href="viewAllMessages.jsp">View All Messages</a>
                        </div>
                    </div>
                </li>
                <li id="student-tab" class="nav-item d-none">
                    <div class="dropdown">
                        <button class="i-dropdown" type="button" id="dropdownMenuButton" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                            <a class="nav-link text-light">Student</a>
                        </button>
                        <div class="dropdown-menu" aria-labelledby="dropdownMenuButton">
                            <a class="dropdown-item" href = "searchForCourse.jsp">Search For Course</a>
                            <%if (cList.size()
                                        > 0) {%>
                            <a class="dropdown-item" href = "dropOutCourse.jsp" > Dropout Course</a> 
                            <a class="dropdown-item" href = "payOngoingStudentFees.jsp" > Pay Course Fees </a> 
                            <a class="dropdown-item" href = "reviewTeacher.jsp" > Enter Review </a> 
                            <%}%>
                        </div>
                    </div>
                </li>
                <li id="message-tab" class="nav-item d-none">
                    <div class="dropdown">
                        <button class="i-dropdown" type="button" id="dropdownMenuButton" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                            <a class="nav-link text-light">Message Board</a>
                        </button>
                        <div class="dropdown-menu" aria-labelledby="dropdownMenuButton">
                            <%if (!(session.getAttribute(
                                        "userId") == null || session.getAttribute("userId").equals(""))) {
                                    if (u.getUser_type().equals("TEACHER")) {
                                        for (int k = 0; k < selectedSub.size(); k++) {
                            %>
                            <a class="dropdown-item" href = "controller?action=viewCourse&viewCourseId=<%=courseList.get(k).getCourse_id()%>"><%=selectedSub.get(k)%></a> 
                            <%
                                }
                            } else if (u.getUser_type().equals("STUDENT")) {
                                for (int x = 0; x < cList.size(); x++) {%>
                            <a class="dropdown-item" href = "controller?action=viewCourse&viewCourseId=<%=cList.get(x).getCourse_id()%>"><%=selectedSub.get(x)%></a> 
                            <%}
                                    }
                                }%>
                        </div>
                    </div>
                </li>
                
                <% if (!(session.getAttribute("userId") == null || session.getAttribute("userId").equals("")) && (boolean) session.getAttribute("login") == true) {
                        String profileLogo = u.getUsername().substring(0, 1);
                %>
                <li id="help-tab" class="nav-item">
                    <a href="helpCenter.jsp" class="nav-link text-light">Help</a>
                </li>
                <li id="profile-tab" class="nav-item">
                    <a class="nav-link text-uppercase" href="profile.jsp"><span class="i-circle"><%=profileLogo%></span></a>
                </li>
                <li id="notification-tab" class="nav-item">
                    <div class="dropdown">
                        <button class="i-dropdown" type="button" id="notification" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                            <a class="nav-link text-light"><i class="fa fa-bell" aria-hidden="true"></i></a>
                        </button>
                        <div id="notiDrop" class="dropdown-menu bg-secondary" aria-labelledby="notification">
                            <jsp:include page="notification.jsp"/>
                        </div>
                    </div>
                </li>         
                <li id="dropdownNavSection" class="d-none">
                    <div class="dropdown">
                        <button class="i-logout" type="button" id="dropdownMenuButton" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                            <i class="fas fa-sort-down fa-xs text-white" aria-hidden="true"></i>
                        </button>
                        <div class="dropdown-menu" aria-labelledby="dropdownMenuButton">
                            <a class="dropdown-item" href="resetPassLoggedIn.jsp">Reset Password</a>
                            <a class="dropdown-item" href="controller?action=logOut">Log Out</a>
                        </div>
                    </div>
                </li>
                <%}%>
            </ul>
        </div>
    </div>
</nav>