<%-- 
    Document   : updateTeacherSubjects
    Created on : Mar 21, 2021, 7:16:44 PM
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
    JoinDao joinDao = new JoinDao("digiclass");
    ArrayList<Course> existingList = courseDao.getAllExistingCoursesByTeacherId(teacherDetails.getTeacher_id());
    ArrayList<TeacherSubjects> tsList = teacherSubjectsDao.getAllPresentTeacherSubjectsById(teacherDetails.getTeacher_id());
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
    int j = 0;
%>

<p id="userType" class="d-none"><%=u.getUser_type()%></p>
<div id="updateTeacherSubjects" class="container text-left mb-5 mt-5 p-5 font">
    <img class="w-100" src="image/upSub.jpg"/>
    <h3 class="mt-2">Update Your Teaching Subjects</h3>
    <div class="row mt-3">
        <div class="col-12 col-lg-4">
            <div class="list-group" id="list-tab" role="tablist">
                <%
                    for (Integer teacherSubjectID : teacherSubjectIDList) {
                        if (!(courseSubjectIDList.contains(teacherSubjectID))) {
                %>
                <a class="list-group-item list-group-item-action" id="list-home-list" data-toggle="list" href="#list-verify-<%=i%>" role="tab" aria-controls="teacherSubject">
                    <%=selectedSub.get(i)%>
                </a>
                <% } else {%>
                <a class="list-group-item list-group-item-action disabled bg-warning" id="list-home-list" data-toggle="list" href="#list-verify-<%=i%>" role="tab" aria-controls="teacherSubject" >
                    <%=selectedSub.get(i)%>
                </a>
                <%}
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
                        <input name="action" value="updateTeacherSubjects" type="hidden"/>
                        <input type="hidden" name="tsId" value="<%=tsList.get(j).getTeacherSubjectId()%>"/>
                        <div class="form-row">
                            <div class="form-group col-6">

                                <label for="subject">Subject</label>
                                <select id="subject" name="subject" class="form-control" required>                                       
                                    <%
                                        for (Subject sb : sub) {
                                            if (s.equals(sb.getSubject_name())) {
                                    %> 
                                    <option value="<%=sb.getSubject_id()%>" selected><%=s%></option>
                                    <%} else if (!(s.equals(sb.getSubject_name()))) {%>
                                    <option value="<%= sb.getSubject_id()%>"><%= sb.getSubject_name()%></option>
                                    <%} else {%>
                                    <option class="d-none" value="<%= sb.getSubject_id()%>"><%= sb.getSubject_name()%></option>
                                    <%}
                                        }%>
                                </select>
                            </div>
                            <div class="form-group col-3">
                                <label for="level">Course Level</label>
                                <select id="level" name="level" class="form-control" required>
                                    <%if (tsList.get(j).getSubject_level().equals("OL")) {%>
                                    <option value="OL" selected>OL</option>
                                    <option value="HL" >HL</option>
                                    <%} else {%>
                                    <option value="HL" selected>HL</option>
                                    <option value="OL" >OL</option>
                                    <%
                                        }%>
                                </select>
                            </div>
                        </div>
                        <button id="updateButton" class="btn btn-primary d-block">Update</button>
                    </form>

                </div>
                <%j++;
                    }%>
            </div>
        </div>
    </div>
    <%if (tsList.size()
                < 3) {%>
    <div class='row d-flex justify-content-center'>
        <div class="p-2">
            <button type="button" class="btn btn-primary" data-toggle="modal" data-target="#addSubjects">
                Add Subjects
            </button>
            <div class="modal fade" id="addSubjects" tabindex="-1" role="dialog" aria-labelledby="addSubjects">
                <div class="modal-dialog modal-dialog-centered" role="document">
                    <div class="modal-content">
                        <div class="modal-header">
                            <h5 class="modal-title" id="addSub">Add Subjects</h5>
                            <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                <span aria-hidden="true">&times;</span>
                            </button>
                        </div>
                        <div class="modal-body">
                            <jsp:include page="addSubjects.jsp" />
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <%}%>
</div>

<jsp:include page="footer.jsp" />  