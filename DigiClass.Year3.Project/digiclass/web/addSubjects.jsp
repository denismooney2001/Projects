<%-- 
    Document   : addSubjects
    Created on : Mar 14, 2021, 4:33:57 PM
    Author     : junta
--%>


<%@page import="Business.*"%>
<%@page import="Daos.*"%>
<%@page import="java.util.ArrayList"%>
<% UserDao userDao = new UserDao("digiclass");
    TeacherDetailsDao teacherDetailsDao = new TeacherDetailsDao("digiclass");
    TeacherSubjectsDao teacherSubjectsDao = new TeacherSubjectsDao("digiclass");
    String username = (String) session.getAttribute("username");
    User u = userDao.getUserByUsername(username);
    TeacherDetails teacherDetails = teacherDetailsDao.getTeacherDetailsByUserId(u.getID());
    SubjectDao sDao = new SubjectDao("digiclass");
    ArrayList<TeacherSubjects> tsList = teacherSubjectsDao.getAllPresentTeacherSubjectsById(teacherDetails.getTeacher_id());
    ArrayList<Subject> sub = sDao.getAllSubjects();
%>
<%  if (tsList.size() != 1) {%>
<form action="controller" method="post" autocomplete="off" >
    <input name="action" value="addSubjects" type="hidden"/>
    <input name="subject1" value="0" type="hidden"/>
    <input name="level1" value="none" type="hidden"/>
    <div class="form-row">
        <div class="form-group col-5">
            <label for="subject0">Subject</label>
            <select id="subject0" name="subject0" class="form-control">
                <option value="0" selected>Select Subject...</option>
                <%
                    for (Subject s : sub) {
                %> 
                <option value="<%= s.getSubject_id()%>"><%= s.getSubject_name()%></option>
                <%}%>
            </select>
        </div>
        <div class="form-group col">
            <label for="level0">Course Level</label>
            <select id="level0" name="level0" class="form-control">
                <option value="none" selected>Select Level...</option>
                <option value="HL">Higher Level</option>
                <option value="OL">Ordinary Level</option>
            </select>
        </div>
    </div>
    <button type="submit" class="btn btn-primary btn-block rounded-pill">Submit</button>
</form>
<%
} else {%>
<form action="controller" method="post" autocomplete="off" >
    <input name="action" value="addSubjects" type="hidden"/>
    <%
        for (int i = 0; i < 3 - tsList.size(); i++) {%>
    <div class="form-row">
        <div class="form-group col-5">
            <label for="subject<%=i%>">Subject</label>
            <select id="subject<%=i%>" name="subject<%=i%>" class="form-control">
                <option value="0" selected>Select Subject...</option>
                <%
                    for (Subject s : sub) {
                %> 
                <option value="<%= s.getSubject_id()%>"><%= s.getSubject_name()%></option>
                <%}%>
            </select>
        </div>
        <div class="form-group col">
            <label for="level<%=i%>">Course Level</label>
            <select id="level<%=i%>" name="level<%=i%>" class="form-control">
                <option value="none" selected>Select Level...</option>
                <option value="HL">Higher Level</option>
                <option value="OL">Ordinary Level</option>
            </select>
        </div>
    </div>
    <%}%>
    <button type="submit" class="btn btn-primary btn-block rounded-pill">Submit</button>
</form>
<%}%>

