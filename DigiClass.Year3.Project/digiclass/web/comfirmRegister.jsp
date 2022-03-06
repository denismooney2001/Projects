<%-- 
    Document   : comfirmRegister
    Created on : Mar 24, 2021, 5:34:49 PM
    Author     : junta
--%>


<%@page import="java.util.HashMap"%>
<%@page import="Business.*"%>
<%@page import="Daos.*"%>
<%@page import="java.util.ArrayList"%>

<% UserDao userDao = new UserDao("digiclass");
    TeacherSubjectsDao teacherSubjectsDao = new TeacherSubjectsDao("digiclass");
    SubjectDao subjectDao = new SubjectDao("digiclass");
    CourseDao courseDao = new CourseDao("digiclass");
    JoinDao joinDao = new JoinDao("digiclass");
    String username = (String) session.getAttribute("username");
    CourseStudentsDao courseStudentsDao = new CourseStudentsDao("digiclass");
    User u = userDao.getUserByUsername(username);
    int cId = (int) session.getAttribute("courseID");
    Course c = courseDao.getCourseByID(cId);
    int coursePlaces = c.getPlaces();
    int placesTookUp = courseStudentsDao.getCourseCount(c.getCourse_id());
    int placesAvailable = coursePlaces - placesTookUp;
    ArrayList<Subject> sub = subjectDao.getAllSubjects();
    int tid = (int) session.getAttribute("selectedTeacher");
    ArrayList<TeacherSubjects> ts = teacherSubjectsDao.getAllTeacherSubjectsById(tid);
    ArrayList<Timetable> timeTableList = joinDao.getTimeTablesbyCourse(cId);
    int count = courseStudentsDao.getCourseCount(cId);
    ArrayList<Timetable> studentTimeTable = joinDao.getStudentTimeTablesForOngoingCourses(u.getID());
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
    for (Timetable t : timeTableList) {
        if ("Monday".equals(t.getDay())) {
            mondayTime.add(t.getTime());
            mondaySubjects.put(t.getTime(), subjectDao.getSubjectNamebyId(joinDao.getSubjectIdByCourseId(t.getCourse_id())));
        } else if ("Tuesday".equals(t.getDay())) {
            tuesdayTime.add(t.getTime());
            tuesdaySubjects.put(t.getTime(), subjectDao.getSubjectNamebyId(joinDao.getSubjectIdByCourseId(t.getCourse_id())));
        } else if ("Wednesday".equals(t.getDay())) {
            wednesdayTime.add(t.getTime());
            wednesdaySubjects.put(t.getTime(), subjectDao.getSubjectNamebyId(joinDao.getSubjectIdByCourseId(t.getCourse_id())));
        } else if ("Thursday".equals(t.getDay())) {
            thursdayTime.add(t.getTime());
            thursdaySubjects.put(t.getTime(), subjectDao.getSubjectNamebyId(joinDao.getSubjectIdByCourseId(t.getCourse_id())));
        } else if ("Friday".equals(t.getDay())) {
            fridayTime.add(t.getTime());
            fridaySubjects.put(t.getTime(), subjectDao.getSubjectNamebyId(joinDao.getSubjectIdByCourseId(t.getCourse_id())));
        } else if ("Saturday".equals(t.getDay())) {
            saturdayTime.add(t.getTime());
            saturdaySubjects.put(t.getTime(), subjectDao.getSubjectNamebyId(joinDao.getSubjectIdByCourseId(t.getCourse_id())));
        } else if ("Sunday".equals(t.getDay())) {
            sundayTime.add(t.getTime());
            sundaySubjects.put(t.getTime(), subjectDao.getSubjectNamebyId(joinDao.getSubjectIdByCourseId(t.getCourse_id())));
        }
    }
    ArrayList<Timetable> conflictTable = new ArrayList<>();
    for (Timetable studTable : studentTimeTable) {
        for (Timetable courseTable : timeTableList) {
            if (courseTable.getDay().equals(studTable.getDay()) && courseTable.getTime() == studTable.getTime()) {
                conflictTable.add(studTable);
            }
        }
    }
    HashMap<Integer, String> mondayConflictSubjects = new HashMap<>();
    HashMap<Integer, String> tuesdayConflictSubjects = new HashMap<>();
    HashMap<Integer, String> wednesdayConflictSubjects = new HashMap<>();
    HashMap<Integer, String> thursdayConflictSubjects = new HashMap<>();
    HashMap<Integer, String> fridayConflictSubjects = new HashMap<>();
    HashMap<Integer, String> saturdayConflictSubjects = new HashMap<>();
    HashMap<Integer, String> sundayConflictSubjects = new HashMap<>();
    ArrayList<Integer> mondayConflictTime = new ArrayList<>();
    ArrayList<Integer> tuesdayConflictTime = new ArrayList<>();
    ArrayList<Integer> wednesdayConflictTime = new ArrayList<>();
    ArrayList<Integer> thursdayConflictTime = new ArrayList<>();
    ArrayList<Integer> fridayConflictTime = new ArrayList<>();
    ArrayList<Integer> saturdayConflictTime = new ArrayList<>();
    ArrayList<Integer> sundayConflictTime = new ArrayList<>();
    for (Timetable t : studentTimeTable) {
        if ("Monday".equals(t.getDay())) {
            mondayConflictTime.add(t.getTime());
            mondayConflictSubjects.put(t.getTime(), subjectDao.getSubjectNamebyId(joinDao.getSubjectIdByCourseId(t.getCourse_id())));
        } else if ("Tuesday".equals(t.getDay())) {
            tuesdayConflictTime.add(t.getTime());
            tuesdayConflictSubjects.put(t.getTime(), subjectDao.getSubjectNamebyId(joinDao.getSubjectIdByCourseId(t.getCourse_id())));
        } else if ("Wednesday".equals(t.getDay())) {
            wednesdayConflictTime.add(t.getTime());
            wednesdayConflictSubjects.put(t.getTime(), subjectDao.getSubjectNamebyId(joinDao.getSubjectIdByCourseId(t.getCourse_id())));
        } else if ("Thursday".equals(t.getDay())) {
            thursdayConflictTime.add(t.getTime());
            thursdayConflictSubjects.put(t.getTime(), subjectDao.getSubjectNamebyId(joinDao.getSubjectIdByCourseId(t.getCourse_id())));
        } else if ("Friday".equals(t.getDay())) {
            fridayConflictTime.add(t.getTime());
            fridayConflictSubjects.put(t.getTime(), subjectDao.getSubjectNamebyId(joinDao.getSubjectIdByCourseId(t.getCourse_id())));
        } else if ("Saturday".equals(t.getDay())) {
            saturdayConflictTime.add(t.getTime());
            saturdayConflictSubjects.put(t.getTime(), subjectDao.getSubjectNamebyId(joinDao.getSubjectIdByCourseId(t.getCourse_id())));
        } else if ("Sunday".equals(t.getDay())) {
            sundayConflictTime.add(t.getTime());
            sundayConflictSubjects.put(t.getTime(), subjectDao.getSubjectNamebyId(joinDao.getSubjectIdByCourseId(t.getCourse_id())));
        }
    }

%>
<jsp:include page="header.jsp" />  
<jsp:include page="navbar.jsp" />  
<p id="userType" class="d-none"><%=u.getUser_type()%></p>
<div class="container mt-5 mb-5 p-5 font">
    <div class="table-responsive">
        <h4>Timetable for the course</h4>
        <table class="table table-bordered table-dark" border="0" cellspacing="0" cellpadding="0">
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
                </tr>
            </thead>
            <tbody>
                <tr>
                    <th scope="row" class="bg-dark text-light">Monday</th>
                        <% int monCheck = 0;
                            if (mondayTime.size() > 0) {
                                for (int i = 9; i < 23; i++) {
                                    if (mondayTime.get(monCheck) == i) {%>
                    <td class="bg-light-green text-dark"><%=mondaySubjects.get(i)%></td>
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
                            if (tuesdayConflictTime.size() > 0) {
                                for (int i = 9; i < 23; i++) {
                                    if (tuesdayTime.get(tueCheck) == i && tuesdayConflictTime.get(tueCheck) != tuesdayTime.get(tueCheck)) {%>
                    <td class="bg-light-green text-dark"><%=tuesdaySubjects.get(i)%></td>
                    <%
                    } else if (tuesdayConflictTime.get(tueCheck) == i && tuesdayConflictTime.get(tueCheck) == tuesdayTime.get(tueCheck)) {%>
                    <td class="bg-danger"><%=tuesdaySubjects.get(i)%>    <%=tuesdayConflictSubjects.get(i)%></td>
                    <%} else if (tuesdayConflictTime.get(tueCheck) == i && tuesdayConflictTime.get(tueCheck) != tuesdayTime.get(tueCheck)) {%>
                    <td class="bg-dark text-white"><%=tuesdayConflictSubjects.get(i)%></td>
                    <%} else {%>
                    <td></td>
                    <% }
                            if (tueCheck < tuesdayTime.size() - 1) {
                                tueCheck++;
                            }
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
                    <td class="bg-light-green text-dark"><%=wednesdaySubjects.get(i)%> </td>
                    <%
                    } else {%>
                    <td></td>
                    <%}
                            if (wedCheck < wednesdayTime.size() - 1) {
                                wedCheck++;
                            }
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

                            if (thursdayTime.size()
                                    > 0) {
                                for (int i = 9; i < 23; i++) {
                                    if (thursdayTime.get(thuCheck) == i) {%>
                    <td class="bg-light-green text-dark"><%=thursdaySubjects.get(i)%></td>
                    <%
                    } else {%>
                    <td></td>
                    <%}
                            if (thuCheck < thursdayTime.size() - 1) {
                                thuCheck++;
                            }
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

                            if (fridayTime.size()
                                    > 0) {
                                for (int i = 9; i < 23; i++) {
                                    if (fridayTime.get(friCheck) == i) {%>
                    <td class="bg-light-green text-dark"><%=fridaySubjects.get(i)%></td>
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

                            if (saturdayTime.size()
                                    > 0) {
                                for (int i = 9; i < 23; i++) {
                                    if (saturdayTime.get(satCheck) == i) {%>
                    <td class="bg-light-green text-dark"><%=saturdaySubjects.get(i)%></td>
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

                            if (sundayTime.size()
                                    > 0) {
                                for (int i = 9; i < 23; i++) {
                                    if (sundayTime.get(sunCheck) == i) {%>
                    <td class="bg-light-green text-dark"><%=sundaySubjects.get(i)%></td>
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
    <div>
        <h4>Course Details</h4>
        <%for (TeacherSubjects t : ts) {
                for (Subject s : sub) {
                    if (t.getTeacherSubjectId() == c.getTeacher_subject_id()) {
                        if (t.getSubject_id() == s.getSubject_id()) {
        %>
        <p>Course Name : <%=s.getSubject_name()%></p>
        <p>Course Description : <%=c.getCourse_description()%></p>
        <p>Start Date : <%=c.getStart_date()%></p>
        <p>End Date :  <%=c.getEnd_date()%></p>
        <p class="font-weight-bold">Available Places : <%=c.getPlaces() - count%></p>
        <%}
                    }
                }
            }%>
        <%if (placesAvailable
                    <= 0) {%>
        <h5 class="text-danger">Sorry! There are no available places to this course.</h5>
        <%} else if (conflictTable.size()
                > 0) { %>
        <h5 class="text-danger">This course conflicts with one of your existing courses!</h5>
        <button disabled type="submit" class="btn btn-primary btn-block rounded-pill" data-toggle="modal" data-target="#exampleModal">Register to this Course</button>
        <% } else {%>
        <button type="submit" class="btn btn-primary btn-block rounded-pill" data-toggle="modal" data-target="#exampleModal">Register to this Course</button>
        <div class="modal fade" id="exampleModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
            <div class="modal-dialog" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title" id="exampleModalLabel">Payment</h5>
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>
                    <div class="modal-body">
                        <form action="controller" method="post">
                            <input name="action" value="signupCourse" type="hidden"/>
                            <input type="hidden" name="courseId" value="<%=cId%>"/>
                            <button class="btn btn-primary" name="paid" value="TRUE">Pay Now</button>  
                            <button class="btn btn-danger" name="paid" value="FALSE">Pay Later</button> 
                        </form>
                    </div>                  
                </div>
            </div>
        </div>
        <%}%>
        <%if (placesAvailable
                    <= 0) {%>
        <form action="controller" method="post">
            <input name="action" value="signUpCourseWaitingList" type="hidden"/>
            <input type="hidden" name="courseId" value="<%=cId%>"/>
            <input type="hidden" name="userId" value="<%=u.getID()%>"/>
            <br>
            <button type="submit" class="btn btn-warning btn-block rounded-pill">Enter a Waiting List</button>
        </form>
        <%}%> 
    </div>
</div>
<jsp:include page="footer.jsp" />  