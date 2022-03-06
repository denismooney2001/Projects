<%@page import="Business.*"%>
<%@page import="Daos.*"%>
<%@page import="java.util.ArrayList"%>
<%  UserDao userDao = new UserDao("digiclass");
    UserDetailsDao userDetailsDao = new UserDetailsDao("digiclass");
    SubjectDao subjectDao = new SubjectDao("digiclass");
    TeacherSubjectsDao teacherSubjectDao = new TeacherSubjectsDao("digiclass");
    TeacherDetailsDao teacherDetailsDao = new TeacherDetailsDao("digiclass");
    String username = (String) session.getAttribute("username");
    User u = userDao.getUserByUsername(username);
    UserDetails ud = userDetailsDao.getUserDetailsByUserId(u.getID());
    if (ud == null) {
        ud = new UserDetails(0, 0, "", "", "", "", "", "", "");
    }
    TeacherDetails teacherDetails = null;
    if(ud != null){
    teacherDetails = teacherDetailsDao.getTeacherDetailsByUserId(u.getID());
    }
    
%> 
<jsp:include page="header.jsp" />  
<jsp:include page="navbar.jsp" />  
<p id="userType" class="d-none"><%=u.getUser_type()%></p>
<div id="profile" class="container emp-profile pb-5 font">
    <h3>My Profile</h3>
    <%if (teacherDetails != null) {
            if (teacherDetails.isVerify() == false) { %>
    <h5 class="text-danger">**You are awaiting to be Verified! We wish you the best of luck! <i class="fa fa-user-circle" aria-hidden="true"></i> </h5>
    <%} else {%>
    <h5 class="text-success">&#10004; Verified </h5>
    <% }
        } %>
    <div class="row">
        <div class='col-lg-4 col-12 p-0 order-lg-0 order-2'>    
            <div >
                <% if (!(ud.getProfile_picture().equals(""))) {%>
                <img class="img-thumbnail w-100 p-2" src='image/profilePictures/<%=ud.getProfile_picture()%>'>
                <% } else {%>
                <img class="img-thumbnail w-100 p-2" src='image/defaultProPic.png'>
                <%}%>
            </div>
        </div>
        <div class="col-lg-8 col-12 bg-dark text-light p-5 order-lg-1 order-0 ">
            <div class="profile-head">
                <h5 class="d-inline-block text-white"><%=ud.getFirst_name()%></h5>
                <h5 class="d-inline-block text-white"><%=ud.getLast_name()%></h5>
                <div>
                    <% if (teacherDetails != null) {
                            ArrayList<TeacherSubjects> teachingList = teacherSubjectDao.getAllTeacherSubjectsById(teacherDetails.getTeacher_id());
                            ArrayList<Subject> subjectList = subjectDao.getAllSubjects();
                            for (int i = 0; i < subjectList.size(); i++) {
                                for (int j = 0; j < teachingList.size(); j++) {
                                    if (subjectList.get(i).getSubject_id() == teachingList.get(j).getSubject_id()) {
                                        if (j == teachingList.size() - 1) {%>
                    <h6 class="d-inline-block text-primary"><%=subjectList.get(i).getSubject_name()%> (<%=teachingList.get(j).getSubject_level()%>)</h6>
                    <%} else {%>
                    <h6 class="d-inline-block text-primary"><%=subjectList.get(i).getSubject_name()%> (<%=teachingList.get(j).getSubject_level()%>), </h6>
                    <%}
                                    }
                                }
                            }
                        }%>
                </div>
                <div class="nav nav-tabs" id="myTab" role="tablist">
                    <a class="nav-item nav-link active bg-primary text-light" id="home-tab" data-toggle="tab" href="#home" role="tab" aria-controls="home" aria-selected="true">Home</a>
                </div>
            </div>
        </div>
    </div>
    <div class="row">
        <div class="col-lg-4 col-12 bg-light p-5 rounded order-lg-0 order-1">
            <%if (u.getUser_type().equals("TEACHER")) {
                    if (ud.getUser_id() == 0 || teacherDetails == null) {%>
            <jsp:include page="sideNavProfile.jsp"/>
            <%} else {%>
            <button type="button" class="btn btn-primary mb-2 btn-block" id="editProfile" onclick="changeToInput()">
                Update Personal Details
            </button>
            <% if (!(ud.getUser_id() == 0)) {%>            
            <jsp:include page="uploadImage.jsp"/>
            <%}%>
            <%if (teacherDetails.getQualifications().equals("") || teacherDetails.getQualifications() == null) {%>
            <jsp:include page="uploadQualification.jsp"/>
            <%}
                }
            } else if (u.getUser_type().equals("STUDENT")) {
                if (ud.getUser_id() == 0) {%>
            <jsp:include page="sideNavProfile.jsp"/>
            <%} else {%>
            <button type="button" class="btn btn-primary mb-2 btn-block" id="editProfile" onclick="changeToInput()">
                Update Personal Details
            </button>
            <% if (!(ud.getUser_id() == 0)) {%>            
            <jsp:include page="uploadImage.jsp"/>
            <%}
                    }
                }
            %>
            <form action="controller" method="post" autocomplete="off">
                <input name="action" value="applyTwoAuth" type="hidden">
                <button class="btn btn-primary btn-block">Apply for two-factor Authenthication</button>
            </form>
        </div>
        <div class="col-lg-8 col-12 bg-dark text-light p-5 rounded order-lg-1 order-3">
            <div class="tab-content" id="nav-tabContent">
                <div class="tab-pane fade show active" id="home" role="tabpanel" aria-labelledby="home-tab">
                    <% if (ud.getUser_id() != 0) {%>
                    <jsp:include page="updateUserDetails.jsp"/>
                    <%}%>
                </div>
            </div>
        </div>
    </div>
</div>
<%if (u.getUser_type().equals("STUDENT")) {
        if (ud.getUser_id() == 0) {%>
<br><br><br><br><br><br>
<%}
    }%>
<jsp:include page="footer.jsp" />  