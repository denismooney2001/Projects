<%-- 
    Document   : viewTimetable
    Created on : Mar 8, 2021, 2:25:09 PM
    Author     : junta
--%>

<%@page import="java.io.File"%>
<%@page import="java.util.Collections"%>
<%@page import="java.util.Arrays"%>
<%@page import="java.util.HashMap"%>
<%@page import="java.util.ArrayList"%>
<%@page import="Business.*"%>
<%@page import="Daos.*"%>

<jsp:include page="header.jsp" />  
<jsp:include page="navbar.jsp" />  
<%  UserDao userDao = new UserDao("digiclass");
    TeacherDetailsDao teacherDetailsDao = new TeacherDetailsDao("digiclass");
    SubjectDao subjectDao = new SubjectDao("digiclass");
    JoinDao joinDao = new JoinDao("digiclass");
    TeacherSubjectsDao teacherSubjectDao = new TeacherSubjectsDao("digiclass");
    UserDetailsDao userDetailsDao = new UserDetailsDao("digiclass");
    boolean login = (boolean) session.getAttribute("login");
    String username = (String) session.getAttribute("username");
    User u = userDao.getUserByUsername(username);
    UserDetails userDetails = userDetailsDao.getUserDetailsByUserId(u.getID());
    ArrayList<Subject> subjectList = subjectDao.getRandomSubjects();
    ArrayList<Integer> teacherIds = joinDao.getUserIdsOfTeacherByStudent(u.getID());
    Collections.sort(teacherIds);
    ArrayList<Integer> test = new ArrayList<>();
    if (teacherIds.size() > 0 && teacherIds.size() < 2) {
        test.add(teacherIds.get(0));
    } else if (teacherIds.size() > 0) {
        for (int i = 0; i < teacherIds.size(); i++) {
            for (int j = i + 1; j < teacherIds.size(); j++) {
                if (teacherIds.get(i) != teacherIds.get(j)) {
                    test.add(teacherIds.get(i));
                    test.add(teacherIds.get(j));
                }
            }
        }
    }
    for (int i = 0; i < teacherIds.size() - 1; i++) {
        if (teacherIds.get(i) == teacherIds.get(i + 1)) {
            test.add(teacherIds.get(i));
        }
    }
    HashMap<Integer, String> mondaySubjects = new HashMap<>();
    HashMap<Integer, String> tuesdaySubjects = new HashMap<>();
    HashMap<Integer, String> wednesdaySubjects = new HashMap<>();
    HashMap<Integer, String> thursdaySubjects = new HashMap<>();
    HashMap<Integer, String> fridaySubjects = new HashMap<>();
    HashMap<Integer, String> saturdaySubjects = new HashMap<>();
    HashMap<Integer, String> sundaySubjects = new HashMap<>();
    ArrayList<Timetable> timeTableList = new ArrayList();
    TeacherDetails teacherDetails = new TeacherDetails();
    if (u.getUser_type().equals("TEACHER")) {
        teacherDetails = teacherDetailsDao.getTeacherDetailsByUserId(u.getID());
        if (teacherDetails != null) {
            timeTableList = joinDao.getOngoingTeacherTimeTables(teacherDetails.getTeacher_id());
        } else {
            teacherDetails = new TeacherDetails(0, 0, "", "", false, false);
        }
    } else if (u.getUser_type().equals("STUDENT")) {
        timeTableList = joinDao.getStudentTimeTablesForOngoingCourses(u.getID());
    }
    ArrayList<Integer> mondayTime = new ArrayList<>();
    ArrayList<Integer> tuesdayTime = new ArrayList<>();
    ArrayList<Integer> wednesdayTime = new ArrayList<>();
    ArrayList<Integer> thursdayTime = new ArrayList<>();
    ArrayList<Integer> fridayTime = new ArrayList<>();
    ArrayList<Integer> saturdayTime = new ArrayList<>();
    ArrayList<Integer> sundayTime = new ArrayList<>();
    HashMap<Integer, Integer> mondayCourseIds = new HashMap<>();
    HashMap<Integer, Integer> tuesdayCourseIds = new HashMap<>();
    HashMap<Integer, Integer> wednesdayCourseIds = new HashMap<>();
    HashMap<Integer, Integer> thursdayCourseIds = new HashMap<>();
    HashMap<Integer, Integer> fridayCourseIds = new HashMap<>();
    HashMap<Integer, Integer> saturdayCourseIds = new HashMap<>();
    HashMap<Integer, Integer> sundayCourseIds = new HashMap<>();
    if (timeTableList != null) {
        for (Timetable t : timeTableList) {
            if ("Monday".equals(t.getDay())) {
                mondayTime.add(t.getTime());
                mondaySubjects.put(t.getTime(), subjectDao.getSubjectNamebyId(joinDao.getSubjectIdByCourseId(t.getCourse_id())));
                mondayCourseIds.put(t.getTime(), t.getCourse_id());
            } else if ("Tuesday".equals(t.getDay())) {
                tuesdayTime.add(t.getTime());
                tuesdaySubjects.put(t.getTime(), subjectDao.getSubjectNamebyId(joinDao.getSubjectIdByCourseId(t.getCourse_id())));
                tuesdayCourseIds.put(t.getTime(), t.getCourse_id());
            } else if ("Wednesday".equals(t.getDay())) {
                wednesdayTime.add(t.getTime());
                wednesdaySubjects.put(t.getTime(), subjectDao.getSubjectNamebyId(joinDao.getSubjectIdByCourseId(t.getCourse_id())));
                wednesdayCourseIds.put(t.getTime(), t.getCourse_id());
            } else if ("Thursday".equals(t.getDay())) {
                thursdayTime.add(t.getTime());
                thursdaySubjects.put(t.getTime(), subjectDao.getSubjectNamebyId(joinDao.getSubjectIdByCourseId(t.getCourse_id())));
                thursdayCourseIds.put(t.getTime(), t.getCourse_id());
            } else if ("Friday".equals(t.getDay())) {
                fridayTime.add(t.getTime());
                fridaySubjects.put(t.getTime(), subjectDao.getSubjectNamebyId(joinDao.getSubjectIdByCourseId(t.getCourse_id())));
                fridayCourseIds.put(t.getTime(), t.getCourse_id());
            } else if ("Saturday".equals(t.getDay())) {
                saturdayTime.add(t.getTime());
                saturdaySubjects.put(t.getTime(), subjectDao.getSubjectNamebyId(joinDao.getSubjectIdByCourseId(t.getCourse_id())));
                saturdayCourseIds.put(t.getTime(), t.getCourse_id());
            } else if ("Sunday".equals(t.getDay())) {
                sundayTime.add(t.getTime());
                sundaySubjects.put(t.getTime(), subjectDao.getSubjectNamebyId(joinDao.getSubjectIdByCourseId(t.getCourse_id())));
                sundayCourseIds.put(t.getTime(), t.getCourse_id());
            }
        }
    }
%>
<%if (login == true) {%>
<p id="userType" class="d-none"><%=u.getUser_type()%></p>
<p class="d-none" id="timetableList"><%=timeTableList%></p>
<div class="container-fluid">
    <section class="font">
        <% if (u.getUser_type().equals("STUDENT") && !(timeTableList.isEmpty()) || (u.getUser_type().equals("TEACHER") && teacherDetails.getUser_id() != 0 && teacherDetails.isVerify() == true && !(timeTableList.isEmpty()))) {%>
        <div class="home-timetable pt-3">
            <%if (userDetails != null) {%>
            <h3 class="pl-2" id="timetableHeading"><%=userDetails.getFirst_name()%>'s Timetable</h3>
            <%}%>
        </div>
        <div class="table-responsive">
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
                        <th scope="row" class="bg-dark text-white">Monday</th>
                            <% int monCheck = 0;
                                if (mondayTime.size() > 0) {
                                    for (int i = 9; i < 23; i++) {
                                        if (mondayTime.get(monCheck) == i) {%>
                        <td class="bg-light-green text-dark"><a class="dropdown-item" href = "controller?action=viewCourse&viewCourseId=<%=mondayCourseIds.get(i)%>"><%=mondaySubjects.get(i)%></a></td>
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
                        <td class="bg-light-green text-dark"><a class="dropdown-item" href = "controller?action=viewCourse&viewCourseId=<%=tuesdayCourseIds.get(i)%>"><%=tuesdaySubjects.get(i)%></a></td>
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
                        <td class="bg-light-green text-dark"><a class="dropdown-item" href = "controller?action=viewCourse&viewCourseId=<%=wednesdayCourseIds.get(i)%>"><%=wednesdaySubjects.get(i)%> </a> </td>
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
                        <td class="bg-light-green text-dark"><a class="dropdown-item" href = "controller?action=viewCourse&viewCourseId=<%=thursdayCourseIds.get(i)%>"><%=thursdaySubjects.get(i)%></a></td>
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
                        <td class="bg-light-green text-dark"><a class="dropdown-item" href = "controller?action=viewCourse&viewCourseId=<%=fridayCourseIds.get(i)%>"><%=fridaySubjects.get(i)%></a></td>
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
                        <td class="bg-light-green text-dark"><a class="dropdown-item" href = "controller?action=viewCourse&viewCourseId=<%=saturdayCourseIds.get(i)%>"><%=saturdaySubjects.get(i)%></a></td>
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
                        <td class="bg-light-green text-dark"><a class="dropdown-item" href = "controller?action=viewCourse&viewCourseId=<%=sundayCourseIds.get(i)%>"><%=sundaySubjects.get(i)%></a></td>
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
        <%if (u.getUser_type().equals("STUDENT")) {%>
        <div class="bg-white pt-3">
            <h3 class="pl-2" id="timetableHeading">Upcoming Class Cancellations</h3>
            <%
                ArrayList<Course> studentCourses;
                studentCourses = joinDao.getStudentsOngoingCourses(u.getID());
                String subjectName;
                for (Course c : studentCourses) {
                    ArrayList<TimetableCancellations> tcList = joinDao.getAllUpcomingCancellationsByCourse(c.getCourse_id());
                    subjectName = subjectDao.getSubjectNamebyId(teacherSubjectDao.getSubjectIdByTeacherSubjectId(c.getTeacher_subject_id()));
                    if (!(tcList.isEmpty())) {
            %> 
            <br>
            <ul>
                <li class="h4 text-danger"><%=subjectName%></li>
                <%
                    }
                    for (TimetableCancellations t : tcList) {
                %> 
                <ul>
                    <li class="h5 text-primary">
                        <%=t.getDate()%>
                    </li>
                </ul>
            </ul>
            <%
                    }
                }
            %>
        </div>
        <%}%>
        <%}%>
    </section>
    <section class="font">
        <br>
    </section>
    <% if (u.getUser_type().equals("STUDENT")) {
            if (!(timeTableList.isEmpty())) {
    %>  
    <section class="container mb-3 mt-3 text-center">
        <h3 id="timetableHeading">YOUR TEACHERS</h3>
        <div class="row">
            <%  for (int i = 0; i < test.size(); i++) {
                    int userId = teacherIds.get(i);
                    UserDetails ud = userDetailsDao.getUserDetailsByUserId(userId);
            %>
            <div class="col-12 col-lg-2 m-1">
                <div class="card w-100 h-100 yourTeacher">
                    <%if (ud.getProfile_picture() == null || ud.getProfile_picture().equals("")) {%>
                    <img class="card-img-top" src="image/defaultProPic.png" alt="Card image cap">
                    <%} else {%>
                    <img class="card-img-top" src="image/profilePictures/<%=ud.getProfile_picture()%>" alt="Card image cap">
                    <%}%>
                    <div class="card-body">
                        <h5 class="card-title"><%=ud.getFirst_name()%> <%=ud.getLast_name()%></h5>
                    </div>
                </div>
            </div>
            <br>
            <% }%>
        </div>
    </section>
    <%} else if (timeTableList.isEmpty()) { %>
    <section class="font">
        <div>
            <br><br><br><br><br>
        </div>
        <div class="container mt-5 mb-5 p-5">
            <h1 class="text-primary text-center">Welcome to DigiClass!</h1>
            <h3 class="text-secondary text-center">You are not enrolled in any Courses at the moment.</h3> 
            <h5 class="text-secondary text-center"><a href="searchForCourse.jsp">Enroll in a Course</a> Today!</h5>
        </div>
        <div>
            <br><br><br><br><br>
        </div>
    </section>
    <%} else if (userDetails == null) { %>
    <section class="font">
        <div>
            <br><br><br><br><br>
        </div>
        <div class="container mt-5 mb-5 p-5">
            <h1 class="text-primary text-center">Welcome to DigiClass!</h1>
            <h3 class="text-secondary text-center">Please Enter in your <span class="text-success">User Details</span>.</h3> 
        </div>
        <div>
            <br><br><br><br><br>
        </div>
    </section>
    <%}
        }%>
    <%if (u.getUser_type().equals("TEACHER")) {
            if (teacherDetails.getUser_id() == 0) { %>
    <section class="font">
        <div>
            <br><br><br><br><br>
        </div>
        <div class="container p-5">
            <h1 class="text-primary text-center">Welcome to DigiClass!</h1>
            <h3 class="text-danger text-center">Register your User & Teacher Details on your Profile Page!</h3>
            <h5 class="text-danger text-center">(i.e. It's on the top right corner of the site)</h5>
        </div>
        <div>
            <br><br><br><br><br>
        </div>
    </section>
    <%} else if (teacherDetails.isVerify() == true && timeTableList.isEmpty()) { %> 
    <section class="font">
        <div>
            <br><br><br><br><br>
        </div>
        <div class="container p-5">
            <h1 class="text-primary text-center">Welcome to DigiClass!</h1>
            <h3 class="text-secondary text-center">You have no Teaching Courses on at the moment.</h3> 
            <h5 class="text-secondary text-center"><a href="startCourse.jsp">Start A Course</a> Today!</h5>
        </div>
        <div>
            <br><br><br><br><br>
        </div>
    </section>
    <%} else if (teacherDetails.getUser_id() != 0 && teacherDetails.getTeacher_id() != 0 && teacherDetails.isVerify() == false) {%>
    <section class="font">
        <div>
            <br><br><br><br><br>
        </div>
        <div class="container p-5">
            <h1 class="text-primary text-center">Welcome to DigiClass!</h1>
            <h3 class="text-secondary text-center">Verify Still in Process.</h3> 
            <h5 class="text-secondary text-center">Contact Us for more Information.</h5>
        </div>
        <div>
            <br><br><br><br><br>
        </div>
    </section>
    <%}
        }%>
    <section class="font text-center">
        <%if (!(u.getUser_type().equals("ADMIN"))) {%>
        <h4 class="pt-4">Subjects on this site include:</h4>
        <div class="row text-center p-5">
            <% int i = 0;
                for (Subject s : subjectList) {
                    if (i < 9) {
            %>
            <div class="col-12 col-lg-4">
                <div class="border rounded p-3 mb-2 bg-dark text-white"><%=s.getSubject_name()%></div>
            </div>
            <%   }
                    i++;
                }%>
        </div>
        <%} else {%>
        <div>
            <br><br><br><br><br><br><br><br><br>
        </div>
        <jsp:include page="adminPage.jsp"/>
        <div>
            <br><br><br><br><br><br><br><br><br>
        </div>
        <%}%>
    </section>
</div>
<%}%>
<jsp:include page="footer.jsp" />     

