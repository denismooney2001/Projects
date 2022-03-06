<%-- 
    Document   : dropOutCourse
    Created on : Mar 13, 2021, 5:57:52 PM
    Author     : junta
--%>
<%@page import="java.util.ArrayList"%>
<%@page import="Business.*"%>
<%@page import="Daos.*"%>
<%  UserDao userDao = new UserDao("digiclass");
    String username = (String) session.getAttribute("username");
    User u = userDao.getUserByUsername(username);
    JoinDao joinDao = new JoinDao("digiclass");
    SubjectDao sDao = new SubjectDao("digiclass");
    TeacherSubjectsDao teacherSubjectsDao = new TeacherSubjectsDao("digiclass");
    ArrayList<Course> sCList = joinDao.getStudentsOngoingCourses(u.getID());
    ArrayList<Subject> sub = sDao.getAllSubjects();
    ArrayList<Integer> tsList = new ArrayList<>();
    ArrayList<Integer> subId = new ArrayList<>();
    for (Course c : sCList) {
        tsList.add(c.getTeacher_subject_id());
    }
    for (int i = 0; i < tsList.size(); i++) {
        subId.add(teacherSubjectsDao.getSubjectIdByTeacherSubjectId(tsList.get(i)));
    }
    int i = 0;
%>
<jsp:include page="header.jsp" />  
<jsp:include page="navbar.jsp" />  
<p id="userType" class="d-none"><%=u.getUser_type()%></p>
<div class="container mb-5"> 
    <div class="text-left mb-5 mt-5 p-5 font">
        <img class="w-100" src="image/dropout.jpg"/>
        <h3 class="mt-3">Dropout Course</h3>
        <% if (subId.size() > 0) {%>
        <div class="form-group">
            <label for="subject">Subject</label>
            <select id="subject" name="subject" class="form-control">
                <option value="0" selected>Select Subject...</option>
                <%
                    for (Course c : sCList) {
                        for (Subject s : sub) {
                            if (subId.get(i) == s.getSubject_id()) {
                %> 
                <option value="<%=c.getCourse_id()%>"><%= s.getSubject_name()%></option>
                <%
                                session.setAttribute("courseId", c.getCourse_id());
                            }

                        }
                        i++;
                    }
                %>
            </select>
        </div>
        <button type="submit" class="btn btn-primary btn-block rounded-pill" data-toggle="modal" data-target="#dropOut">Drop Out</button>
        <div class="modal fade" id="dropOut" tabindex="-1" role="dialog" aria-labelledby="dropOutLabel" aria-hidden="true">
            <div class="modal-dialog" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title" id="dropOutLabel">Dropout</h5>
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>
                    <div class="modal-body">
                        <p>Confirm to Dropout ?</p>
                        <form action="controller" method="post" autocomplete="off" >
                            <input name="action" value="dropOutOfCourse" type="hidden"/>
                            <button class="btn btn-danger btn-block" name="subject" value="<%=(int) session.getAttribute("courseId")%>">Yes</button>  
                        </form>
                        <button class="btn btn-primary btn-block mt-3" onClick="window.location.reload();">No</button>  
                    </div>                   
                </div>
            </div>
        </div>
        <% } else {%>
        <p>No On-Going Course or not Sign up For any course yet.</p>
        <%}%>
    </div>
</div>
<jsp:include page="footer.jsp" />  
