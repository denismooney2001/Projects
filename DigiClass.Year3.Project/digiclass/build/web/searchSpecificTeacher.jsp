<%@page import="Business.*"%>
<%@page import="Daos.*"%>
<%@page import="java.util.ArrayList"%>
<%  UserDao userDao = new UserDao("digiclass");
    UserDetailsDao userDetailsDao = new UserDetailsDao("digiclass");
    SubjectDao subjectDao = new SubjectDao("digiclass");
    TeacherSubjectsDao teacherSubjectDao = new TeacherSubjectsDao("digiclass");
    CourseDao courseDao = new CourseDao("digiclass");
    CourseStudentsDao courseStudentDao = new CourseStudentsDao("digiclass");
    TeacherDetailsDao teacherDetailsDao = new TeacherDetailsDao("digiclass");
    String username = (String) session.getAttribute("username");
    User u = userDao.getUserByUsername(username);
    int tid = (int) session.getAttribute("selectedTeacher");
    TeacherDetails td = teacherDetailsDao.getTeacherDetailsByTeacherId(tid);
    UserDetails tu = userDetailsDao.getUserDetailsByUserId(td.getUser_id());
    ArrayList<TeacherSubjects> ts = teacherSubjectDao.getAllTeacherSubjectsById(tid);
    ArrayList<Subject> slist = subjectDao.getAllSubjects();
    ArrayList<Course> cList = courseDao.getCoursesNotYetStarted(tid);
    ArrayList<Course> onList = courseDao.getStartedOngoingCourses(tid);
    CourseWaitingListDao courseWaitingListDao = new CourseWaitingListDao("digiclass");
%> 
<jsp:include page="header.jsp" />  
<jsp:include page="navbar.jsp" />  
<p id="userType" class="d-none"><%=u.getUser_type()%></p>
<div class="container mt-3 mb-3 p-5 font text-center">
    <h3>Teacher Details</h3>
    <div class="container">
        <div class="row text-center mb-2">
            <img class="w-100" src="image/profilePictures/<%=tu.getProfile_picture()%>"/>
        </div>
        <div class="row">
            <div class="col-12 col-lg-2">
                <h5>Teacher Name</h5>
            </div>
            <div class="col-12 col-lg-10">
                <p class="form-control"><%=tu.getFirst_name() + " " + tu.getLast_name()%> </p>
            </div>
        </div>
        <div class="row">
            <div class="col-12 col-lg-2">
                <h5>Contact Number</h5>
            </div>
            <div class="col-12 col-lg-10">
                <p class="form-control"><%=tu.getContact_number()%> </p>
            </div>
        </div>
        <div class="row">
            <div class="col-12 col-lg-2">
                <h5>Teaching Subjects</h5>
            </div>
            <% for (TeacherSubjects t : ts) {
                    for (Subject s : slist) {
                        if (t.getSubject_id() == s.getSubject_id()) {
            %>
            <div class="col-12 col-lg-4 col-md-auto">
                <p class="form-control"><%=s.getSubject_name()%>(<%=t.getSubject_level()%>)</p>
            </div>
            <%}
                    }
                }%>
        </div>
        <div class="row">
            <div class="col-12 col-lg-2">
                <h5>On Going Course</h5>
            </div>
            <%if (onList.size() > 0) {
                    for (Course c : onList) {
                        for (TeacherSubjects t : ts) {
                            for (Subject s : slist) {
                                if (c.getTeacher_subject_id() == t.getTeacherSubjectId()) {
                                    if (t.getSubject_id() == s.getSubject_id()) {

            %>  
            <div class="bg-light col-lg-3 m-1 border border-dark"><strong>Course Subject</strong> : <%=s.getSubject_name()%>(<%=t.getSubject_level()%>) <strong>Course Places</strong> : <%=c.getPlaces()%>  <strong>Start Date</strong> : <%=c.getStart_date()%> <strong>End Date</strong>: <%=c.getEnd_date()%></div>
            <%}
                            }
                        }
                    }
                }
            } else {%>
            <p>No On Going Course</p>
            <%}%>
        </div>
        <div class="row">
            <div class="col-12 col-lg-2">
                <h5>Course to be Start</h5>
            </div>
            <div class="col-12 col-lg-10">
                <%if (cList.size() > 0) {
                        for (Course c : cList) {
                            for (TeacherSubjects t : ts) {
                                for (Subject s : slist) {
                                    if (c.getTeacher_subject_id() == t.getTeacherSubjectId()) {
                                        if (t.getSubject_id() == s.getSubject_id()) {

                %>  
                <div class="row mb-3">
                    <form class="w-100" action="controller" method="post" autocomplete="off" >
                        <input name="action" value="toSignUpCourse" type="hidden"/>
                        <input name="courseId" value="<%=c.getCourse_id()%>" type="hidden"/>
                        <div class="bg-light p-2 border border-dark"><strong>Course Subject</strong> : <%=s.getSubject_name()%>(<%=t.getSubject_level()%>) <strong>Course Places</strong> : <%=c.getPlaces()%>  <strong>Start Date</strong> : <%=c.getStart_date()%> <strong>End Date</strong>: <%=c.getEnd_date()%></div>
                        <% if (courseStudentDao.studentEnrolledInCourse(c.getCourse_id(), u.getID()) == true) {%>
                        <h5 class="text-danger"> *** You are Already Registered to this course!</h5>
                        <%} else if (courseWaitingListDao.studentInCourseWaitingList(c.getCourse_id(), u.getID()) == true) { %>
                        <h5 class="text-primary"> *** You are in a Waiting List for this course!</h5>
                        <%} else { %>
                        <button class="btn btn-primary btn-block mt-1">Register Course</button>
                        <%}%>
                    </form>
                </div>
                <%}
                                }
                            }
                        }
                    }
                } else {%>
                <p>No On Going Course</p>
                <%}%>
            </div>
        </div>
        <div class="row mb-2">
            <div class="col-12 col-lg-2">
                <h5>Qualification</h5>
            </div>
            <div class="col-12 col-lg-10">
                <img class="w-100" src="image/teachersQualifications/<%=td.getQualifications()%>"/>
            </div>
        </div>
        <div class="row">
            <div class="col-12 col-lg-2">
                <h5>About Me</h5>
            </div>
            <div class="col-12 col-lg-10">
                <p class="form-control"><%=td.getAbout_me()%> </p>
            </div>
        </div>
    </div> 
</div>
<jsp:include page="footer.jsp" />  