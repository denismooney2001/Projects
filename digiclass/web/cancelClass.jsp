<%-- 
    Document   : cancelClass
    Created on : Mar 8, 2021, 2:24:33 PM
    Author     : junta
--%>
<%@page import="java.util.HashMap"%>
<%@page import="Business.*"%>
<%@page import="Daos.*"%>
<%@page import="java.util.ArrayList"%>
<%  UserDao userDao = new UserDao("digiclass");
    TeacherDetailsDao teacherDetailsDao = new TeacherDetailsDao("digiclass");
    SubjectDao subjectDao = new SubjectDao("digiclass");
    TeacherSubjectsDao teacherSubjectDao = new TeacherSubjectsDao("digiclass");
    SubjectDao subDao = new SubjectDao("digiclass");
    JoinDao joinDao = new JoinDao("digiclass");
    TimetableDao timetableDao = new TimetableDao("digiclass");
    CourseDao courseDao = new CourseDao("digiclass");
    String username = (String) session.getAttribute("username");
    User u = userDao.getUserByUsername(username);

    TeacherDetails teacherDetails = teacherDetailsDao.getTeacherDetailsByUserId(u.getID());
    ArrayList<Timetable> timeTableList = joinDao.getOngoingTeacherTimeTables(teacherDetails.getTeacher_id());
    HashMap<Integer, String> mondaySubjects = new HashMap<>();
    HashMap<Integer, String> tuesdaySubjects = new HashMap<>();
    HashMap<Integer, String> wednesdaySubjects = new HashMap<>();
    HashMap<Integer, String> thursdaySubjects = new HashMap<>();
    HashMap<Integer, String> fridaySubjects = new HashMap<>();
    HashMap<Integer, String> saturdaySubjects = new HashMap<>();
    HashMap<Integer, String> sundaySubjects = new HashMap<>();

    ArrayList<Integer> mondayTime = new ArrayList<>();
    ArrayList<Integer> tuesdayTime = new ArrayList<>();
    ArrayList<Integer> wednesdayTime = new ArrayList<>();
    ArrayList<Integer> thursdayTime = new ArrayList<>();
    ArrayList<Integer> fridayTime = new ArrayList<>();
    ArrayList<Integer> saturdayTime = new ArrayList<>();
    ArrayList<Integer> sundayTime = new ArrayList<>();

    ArrayList<Integer> mondayID = new ArrayList<>();
    ArrayList<Integer> tuesdayID = new ArrayList<>();
    ArrayList<Integer> wednesdayID = new ArrayList<>();
    ArrayList<Integer> thursdayID = new ArrayList<>();
    ArrayList<Integer> fridayID = new ArrayList<>();
    ArrayList<Integer> saturdayID = new ArrayList<>();
    ArrayList<Integer> sundayID = new ArrayList<>();

    for (Timetable t : timeTableList) {
        if ("Monday".equals(t.getDay())) {
            mondayID.add(t.getTimetable_id());
            mondayTime.add(t.getTime());
            mondaySubjects.put(t.getTime(), subjectDao.getSubjectNamebyId(joinDao.getSubjectIdByCourseId(t.getCourse_id())));
        } else if ("Tuesday".equals(t.getDay())) {
            tuesdayID.add(t.getTimetable_id());
            tuesdayTime.add(t.getTime());
            tuesdaySubjects.put(t.getTime(), subjectDao.getSubjectNamebyId(joinDao.getSubjectIdByCourseId(t.getCourse_id())));
        } else if ("Wednesday".equals(t.getDay())) {
            wednesdayID.add(t.getTimetable_id());
            wednesdayTime.add(t.getTime());
            wednesdaySubjects.put(t.getTime(), subjectDao.getSubjectNamebyId(joinDao.getSubjectIdByCourseId(t.getCourse_id())));
        } else if ("Thursday".equals(t.getDay())) {
            thursdayID.add(t.getTimetable_id());
            thursdayTime.add(t.getTime());
            thursdaySubjects.put(t.getTime(), subjectDao.getSubjectNamebyId(joinDao.getSubjectIdByCourseId(t.getCourse_id())));
        } else if ("Friday".equals(t.getDay())) {
            fridayID.add(t.getTimetable_id());
            fridayTime.add(t.getTime());
            fridaySubjects.put(t.getTime(), subjectDao.getSubjectNamebyId(joinDao.getSubjectIdByCourseId(t.getCourse_id())));
        } else if ("Saturday".equals(t.getDay())) {
            saturdayID.add(t.getTimetable_id());
            saturdayTime.add(t.getTime());
            saturdaySubjects.put(t.getTime(), subjectDao.getSubjectNamebyId(joinDao.getSubjectIdByCourseId(t.getCourse_id())));
        } else if ("Sunday".equals(t.getDay())) {
            sundayID.add(t.getTimetable_id());
            sundayTime.add(t.getTime());
            sundaySubjects.put(t.getTime(), subjectDao.getSubjectNamebyId(joinDao.getSubjectIdByCourseId(t.getCourse_id())));
        }
    }

    ArrayList<Course> ongoingCourses = joinDao.getAllOngoingCoursesByTeacherId(teacherDetails.getTeacher_id());

%> 
<jsp:include page="header.jsp" />  
<jsp:include page="navbar.jsp" />  
<p id="userType" class="d-none"><%=u.getUser_type()%></p>
<div class="container text-left mb-5 mt-5 p-5 font">
    <img class="w-100" src="image/timetable.jpg"/>
    <h2 class="text-center mt-3">Your Timetable</h2>
    <%for(Course c: ongoingCourses){ %>
    <p><b><%=subDao.getSubjectNamebyId(teacherSubjectDao.getSubjectIdByTeacherSubjectId(c.getTeacher_subject_id()))%></b> (<%=teacherSubjectDao.getTeacherSubjectById(teacherSubjectDao.getTeacherSubjectId(teacherDetails.getTeacher_id() , teacherSubjectDao.getSubjectIdByTeacherSubjectId(c.getTeacher_subject_id()))).getSubject_level()  %>) &#8594; Start Date: <b><%=c.getStart_date()%></b>, End Date: <b><%=c.getEnd_date()%></b></p>
    <br>
    <% }%>
    <div class="table-responsive">
        <table class="table table-bordered table-dark">
            <thead class="thead-dark">
                <tr>
                    <th scope="col">#</th>
                    <th scope="col">9-10</th>
                    <th scope="col">10-11</th>
                    <th scope="col">11-12</th>
                    <th scope="col">12-13</th>
                    <th scope="col">13-14</th>
                    <th scope="col">14-15</th>
                    <th scope="col">15-16</th>
                    <th scope="col">16-17</th>
                    <th scope="col">17-18</th>
                    <th scope="col">18-19</th>
                    <th scope="col">19-20</th>
                    <th scope="col">20-21</th>
                    <th scope="col">21-22</th>
                    <th scope="col">22-23</th>
                    <th scope="col"></th>
                </tr>
            </thead>
            <tbody>
                <tr>
                    <th scope="row" class="bg-dark text-white">Monday</th>
                        <% int monCheck = 0;
                            if (mondayTime.size() > 0) {
                                for (int i = 9; i < 23; i++) {
                                    if (mondayTime.get(monCheck) == i) {%>
                    <td class="bg-light-green text-dark" onclick="getDate()">
                        <%=mondaySubjects.get(i)%>
                        <input type="hidden" id="timetableId1" value="<%=mondayID.get(monCheck)%>"/>
                        <%Course c = courseDao.getCourseByID(timetableDao.getCourseIdByTid(mondayID.get(monCheck)));%>
                        <input type="hidden" id="startDate1" value="<%=c.getStart_date()%>"/>
                        <input type="hidden" id="endDate1" value="<%=c.getEnd_date()%>"/>
                        <jsp:include page="cancelModal.jsp"/>
                    </td>
                    <%
                        if (monCheck < mondaySubjects.size() - 1) {
                            monCheck++;
                        }
                    } else {%>
                    <td></td>
                    <%}
                        }
                    } else {
                        for (int j = 9; j < 23; j++) {%>
                    <td></td>
                    <%}
                        }
                    %>
                </tr>
                <tr>
                    <th scope="row" class="bg-dark text-white">Tuesday</th>
                        <% int tueCheck = 0;
                            if (tuesdayTime.size() > 0) {
                                for (int i = 9; i < 23; i++) {
                                    if (tuesdayTime.get(tueCheck) == i) {%>
                    <td class="bg-light-green text-dark" onclick="getDate2()">
                        <%=tuesdaySubjects.get(i)%>
                        <input type="hidden" id="timetableId2" value="<%=tuesdayID.get(tueCheck)%>"/>                     
                        <jsp:include page="cancelModal.jsp"/>
                    </td>
                    <%if (tueCheck < tuesdayTime.size() - 1) {
                            tueCheck++;
                        }
                    } else {%>
                    <td></td>
                    <%}
                        }
                    } else {
                        for (int j = 9; j < 23; j++) {%>
                    <td></td>
                    <%}
                        }
                    %>
                </tr>
                <tr>
                    <th scope="row" class="bg-dark text-white">Wednesday</th>
                        <% int wedCheck = 0;
                            if (wednesdayTime.size() > 0) {
                                for (int i = 9; i < 23; i++) {
                                    if (wednesdayTime.get(wedCheck) == i) {%>
                    <td class="bg-light-green text-dark" onclick="getDate3()">
                        <%=wednesdaySubjects.get(i)%> 
                        <input type="hidden" id="timetableId3" value="<%=wednesdayID.get(wedCheck)%>"/>
                        <jsp:include page="cancelModal.jsp"/>
                    </td>
                    <%if (wedCheck < wednesdayTime.size() - 1) {
                            wedCheck++;
                        }
                    } else {%>
                    <td></td>
                    <%}
                        }
                    } else {
                        for (int j = 9; j < 23; j++) {%>
                    <td></td>
                    <%}
                        }
                    %>
                </tr>
                <tr>
                    <th scope="row" class="bg-dark text-white">Thursday</th>
                        <% int thuCheck = 0;
                            if (thursdayTime.size() > 0) {
                                for (int i = 9; i < 23; i++) {
                                    if (thursdayTime.get(thuCheck) == i) {%>
                    <td class="bg-light-green text-dark" onclick="getDate4()">
                        <%=thursdaySubjects.get(i)%>
                        <input type="hidden" id="timetableId4" value="<%=thursdayID.get(thuCheck)%>"/>
                        <jsp:include page="cancelModal.jsp"/>
                    </td>
                    <%if (thuCheck < thursdayTime.size() - 1) {
                            thuCheck++;
                        }
                    } else {%>
                    <td></td>
                    <%}
                        }
                    } else {
                        for (int j = 9; j < 23; j++) {%>
                    <td></td>
                    <%}
                        }
                    %>
                </tr>
                <tr>
                    <th scope="row" class="bg-dark text-white">Friday</th>
                        <% int friCheck = 0;
                            if (fridayTime.size() > 0) {
                                for (int i = 9; i < 23; i++) {
                                    if (fridayTime.get(friCheck) == i) {%>
                    <td class="bg-light-green text-dark"  onclick="getDate5()">
                        <%=fridaySubjects.get(i)%>
                        <input type="hidden" id="timetableId5" value="<%=fridayID.get(friCheck)%>"/>
                        <jsp:include page="cancelModal.jsp"/>
                    </td>
                    <%if (friCheck < fridayTime.size() - 1) {
                            friCheck++;
                        }
                    } else {%>
                    <td></td>
                    <%}
                        }
                    } else {
                        for (int j = 9; j < 23; j++) {%>
                    <td></td>
                    <%}
                        }
                    %>
                </tr>
                <tr>
                    <th scope="row" class="bg-dark text-white">Saturday</th>
                        <% int satCheck = 0;
                            if (saturdayTime.size() > 0) {
                                for (int i = 9; i < 23; i++) {
                                    if (saturdayTime.get(satCheck) == i) {%>
                    <td class="bg-light-green text-dark" onclick="getDate6()">
                        <%=saturdaySubjects.get(i)%>
                        <input type="hidden" id="timetableId6" value="<%=saturdayID.get(satCheck)%>"/>
                        <jsp:include page="cancelModal.jsp"/>
                    </td>
                    <%if (satCheck < saturdayTime.size() - 1) {
                            satCheck++;
                        }
                    } else {%>
                    <td></td>
                    <%}
                        }
                    } else {
                        for (int j = 9; j < 23; j++) {%>
                    <td></td>
                    <%}
                        }
                    %>
                </tr>
                <tr>
                    <th scope="row" class="bg-dark text-white">Sunday</th>
                        <% int sunCheck = 0;
                            if (sundayTime.size() > 0) {
                                for (int i = 9; i < 23; i++) {
                                    if (sundayTime.get(sunCheck) == i) {%>
                    <td class="bg-light-green text-dark" onclick="getDate7()">
                        <%=sundaySubjects.get(i)%>
                        <input type="hidden" id="timetableId7" value="<%=sundayID.get(sunCheck)%>"/>
                        <jsp:include page="cancelModal.jsp"/>
                    </td>
                    <%if (sunCheck < sundayTime.size() - 1) {
                            sunCheck++;
                        }
                    } else {%>
                    <td></td>
                    <%}
                        }
                    } else {
                        for (int j = 9; j < 23; j++) {%>
                    <td></td>
                    <%}
                        }

                    %>
                </tr>
            </tbody>
        </table>
    </div>
</div>

<jsp:include page="footer.jsp" />  