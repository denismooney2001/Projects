<%-- 
    Document   : displayAlmostStartCourse
    Created on : Mar 29, 2021, 5:39:42 PM
    Author     : junta
--%>

<%@page import="Business.Subject"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.ArrayList"%>
<%@page import="Daos.SubjectDao"%>
<%@page import="Business.TeacherSubjects"%>
<%@page import="Daos.TeacherSubjectsDao"%>
<%@page import="Business.Course"%>
<%@page import="Daos.CourseDao"%>
<%@page import="Daos.JoinDao"%>
<%
    JoinDao joinDao = new JoinDao("digiclass");
    CourseDao courseDao = new CourseDao("digiclass");
    TeacherSubjectsDao teacherSubjectsDao = new TeacherSubjectsDao("digiclass");
    SubjectDao subjectDao = new SubjectDao("digiclass");
    int userId = (int) session.getAttribute("userId");
    int courseId = joinDao.getCourseAlmostStart(userId);
    Course c = courseDao.getCourseByID(courseId);
    int subjectId = teacherSubjectsDao.getSubjectIdByTeacherSubjectId(c.getTeacher_subject_id());
    ArrayList<Subject> subjectList = subjectDao.getAllSubjects();
%>
<div>
    <%for (Subject s : subjectList) {
            if (s.getSubject_id() == subjectId) {%>
    <p><%=s.getSubject_name()%> will be start in 10 minutes!</p>
    <% }
        }%>

</div>