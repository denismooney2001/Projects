<%-- 
    Document   : teacherRegisterDetails
    Created on : Feb 10, 2021, 11:04:31 AM
    Author     : junta
--%>

<%@page import="Business.*"%>
<%@page import="Daos.*"%>
<%@page import="java.util.ArrayList"%>

<form action="controller" method="post" autocomplete="off" >
    <input name="action" value="registerTeacherDetails" type="hidden"/>
    <small>Maximum up to 3 subject</small>
    <div class="form-row">
        <div class="form-group col-5">
            <label for="subject1">Subject</label>
            <select id="subject1" name="subject1" class="form-control" onchange="displaySubject2()" required>
                <option value="0" selected>Select Subject...</option>
                <% SubjectDao sDao = new SubjectDao("digiclass");
                    ArrayList<Subject> sub = sDao.getAllSubjects();
                    for (Subject s : sub) {
                %> 
                <option value="<%= s.getSubject_id()%>"><%= s.getSubject_name()%></option>
                <%}%>
            </select>
        </div>
        <div class="form-group col">
            <label for="level">Course Level</label>
            <select id="level" name="level" class="form-control" required>
                <option value="none" selected>Select Level...</option>
                <option value="HL">Higher Level</option>
                <option value="OL">Ordinary Level</option>
            </select>
        </div>
    </div>
    <div id="subject2Div" class="form-row d-none">
        <div class="form-group col-5">
            <label for="subject2">Subject</label>
            <select id="subject2" name="subject2" class="form-control" onchange="displaySubject3()"> 
                <option value="0" selected>Select Subject...</option>
                <%
                    for (Subject s : sub) {
                %> 
                <option value="<%= s.getSubject_id()%>"><%= s.getSubject_name()%></option>
                <%}%>
            </select>
        </div>
        <div class="form-group col">
            <label for="level2">Course Level</label>
            <select id="level2" name="level2" class="form-control">
                <option value="none" selected>Select Level...</option>
                <option value="HL">Higher Level</option>
                <option value="OL">Ordinary Level</option>
            </select>
        </div>
    </div>
    <div id="subject3Div" class="form-row d-none">
        <div class="form-group col-5">
            <label for="subject3">Subject</label>
            <select id="subject3" name="subject3" class="form-control">
                <option value="0" selected>Select Subject...</option>
                <%
                    for (Subject s : sub) {
                %> 
                <option value="<%= s.getSubject_id()%>"><%= s.getSubject_name()%></option>
                <%}%>
            </select>
        </div>
        <div class="form-group col">
            <label for="level3">Course Level</label>
            <select id="level3" name="level3" class="form-control">
                <option value="none" selected>Select Level...</option>
                <option value="HL">Higher Level</option>
                <option value="OL">Ordinary Level</option>

            </select>
        </div>
    </div>
    <div class="form-group">
        <label for="aboutMe">About Me</label>
        <br>
        <textarea name="aboutMe" class="w-100" required></textarea>
    </div>
    <button type="submit" class="btn btn-primary btn-block rounded-pill">Submit</button>
</form>
