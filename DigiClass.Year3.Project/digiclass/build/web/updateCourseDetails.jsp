<%-- 
    Document   : updateTeacherDetails
    Created on : Feb 17, 2021, 9:13:53 PM
    Author     : junta
--%>

<%@page import="Business.*"%>
<%@page import="Daos.*"%>
<%@page import="java.util.ArrayList"%>
<jsp:include page="header.jsp" />  
<jsp:include page="navbar.jsp" />   
<%   UserDao userDao = new UserDao("digiclass");
    TeacherDetailsDao teacherDetailsDao = new TeacherDetailsDao("digiclass");
    String username = (String) session.getAttribute("username");
    TeacherSubjectsDao teacherSubjectsDao = new TeacherSubjectsDao("digiclass");
    User u = userDao.getUserByUsername(username);
    TeacherDetails teacherDetails = teacherDetailsDao.getTeacherDetailsByUserId(u.getID());
    CourseDao courseDao = new CourseDao("digiclass");
    ArrayList<Course> courseList = courseDao.getCoursesNotYetStarted(teacherDetails.getTeacher_id());
    ArrayList<TeacherSubjects> tsList = teacherSubjectsDao.getAllPresentTeacherSubjectsById(teacherDetails.getTeacher_id());
    SubjectDao sDao = new SubjectDao("digiclass");
    ArrayList<Subject> sub = sDao.getAllSubjects();
    ArrayList<String> selectedSub = new ArrayList<>();
    ArrayList<Integer> tsID = new ArrayList<>();
    for (Course cs : courseList) {
        tsID.add(cs.getTeacher_subject_id());
    }
    for (TeacherSubjects ts : tsList) {
        for (Integer tId : tsID) {
            for (Subject s : sub) {
                if (ts.getTeacherSubjectId() == tId) {
                    if (ts.getSubject_id() == s.getSubject_id()) {
                        selectedSub.add(s.getSubject_name());
                    }
                }
            }
        }
    }
    int i = 0;
    int j = 0;
%>
<p id="userType" class="d-none"><%=u.getUser_type()%></p>
<div class="container text-left mb-5 mt-5 p-5 font">
    <img class="w-100" src="image/upCourse.jpg"/>
    <h3>Update Courses Details</h3>
    <div class="row">
        <div class="col-lg-4 col-12">
            <div class="list-group" id="list-tab" role="tablist">
                <% for (String s : selectedSub) {%>
                <a class="list-group-item list-group-item-action" id="list-home-list" data-toggle="list" href="#list-verify-<%=i%>" role="tab" aria-controls="teacherSubject">
                    <%=s%>
                </a>
                <%
                        i++;
                    }%>
            </div>
        </div>
        <div class="col-lg-8 col-12">
            <div class="tab-content" id="nav-tabContent">  
                <%for (String s : selectedSub) {
                %>
                <div class="tab-pane fade show text-white p-3 verify-tab" id="list-verify-<%=j%>" role="tabpanel" aria-labelledby="list-teacherSubject-list">
                    <form action="controller" method="post" autocomplete="off">
                        <input name="action" value="updateCourseDetails" type="hidden"/>
                        <div class="form-row">                               
                            <input type="hidden" name="tsId" value="<%=tsList.get(j).getTeacherSubjectId()%>"/>
                            <div class="form-group col-lg-4 col-12">
                                <label for="capacity">Course Capacity</label>
                                <input type="number" name="capacity" class="form-control" value="<%=courseList.get(j).getPlaces()%>" id="capacity" required>
                            </div> 
                            <div class="form-group col-lg-4 col-12">
                                <label for="capacity">Start Date</label>
                                <input type="text" class="form-control" value="<%=courseList.get(j).getStart_date()%>" id="datefield" name="startDate" onclick="testFunction()" onkeyup="myFunction()" required/>
                            </div>
                            <div class="form-group col-lg-4 col-12">
                                <label for="capacity">End Date</label>
                                <input type="text" name="endDate" class="form-control" value="<%=courseList.get(j).getEnd_date()%>" id="endDate" required>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="description">Description</label><br>
                            <textarea name="description" class="w-100"><%=courseList.get(j).getCourse_description()%></textarea>
                        </div>
                        <button id="updateButton1" class="btn btn-primary">Update</button>
                    </form>
                </div>
                <%j++;
                    }%>
            </div>
        </div>
    </div>

</div>
<jsp:include page="footer.jsp" />  