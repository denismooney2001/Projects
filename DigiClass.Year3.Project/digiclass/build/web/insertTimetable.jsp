<%-- 
    Document   : insertTimetable
    Created on : Feb 25, 2021, 5:37:20 PM
    Author     : junta
--%>

<%@page import="java.util.HashMap"%>
<%@page import="java.util.ArrayList"%>
<%@page import="Business.*"%>
<%@page import="Daos.*"%>
<%@page import="java.util.ResourceBundle"%>
<%  UserDao userDao = new UserDao("digiclass");
    String username = (String) session.getAttribute("username");
    User u = userDao.getUserByUsername(username);
    // Get the error message variable out of the session
    Object msg = session.getAttribute("courseErrorMessage");
    // Cast it to a String so we can use it
    String error = (String) msg;
    // Display the message
    JoinDao joinDao = new JoinDao("digiclass");
    TeacherDetailsDao teacherDetailsDao = new TeacherDetailsDao("digiclass");
    TeacherDetails td = teacherDetailsDao.getTeacherDetailsByUserId(u.getID());

    ArrayList<Timetable> timeTableList = joinDao.getOngoingTeacherTimeTables(td.getTeacher_id());

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

    SubjectDao subjectDao = new SubjectDao("digiclass");

    if (timeTableList != null) {
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
    }
%> 
<jsp:include page="header.jsp" />  
<jsp:include page="navbar.jsp" />    
<div class="container text-left mb-5 mt-5 p-5 font">
    <p id="userType" class="d-none"><%=u.getUser_type()%></p>
    <br>
    <form  action="controller" method="post" autocomplete="off">
        <input name="action" value="insertTimetable" type="hidden"/>
        <h3>Insert Timetable</h3>
        <p class="text-danger">You can have <b>MINIMUM 2 Hours </b>Per Week, and <b>MAXIMUM 6 Hours </b>Per Week.</p>
        <p>(i.e. You can check below to see if your entered hours clashes with your ongoing timetable.)</p>
        <%if (error != null) {%>
        <h5 class="text-primary"><%=error%></h5>
        <% } %>
        <div class="form-row mt-3">
            <div class="col-12 col-lg-4">
                <label for="date">Day</label>
                <select id="teachingDay1" class="form-control" name="teachingDay1" required>
                    <option selected value="Select Day">Select Day</option>
                    <option value="Monday">Monday</option>
                    <option value="Tuesday">Tuesday</option>
                    <option value="Wednesday">Wednesday</option>
                    <option value="Thursday">Thursday</option>
                    <option value="Friday">Friday</option>
                    <option value="Saturday">Saturday</option>
                    <option value="Sunday">Sunday</option>
                </select>
            </div>
            <div class="col-12 col-lg-4">
                <label for="startTime">Start Time</label>
                <input id="teachingStartTime1" class="form-control" type="number" min="9" max="22" name="startTime1" required/>  
            </div>
        </div>
        <hr>
        <div id="teachingDay2Overrall" class="form-row">
            <div class="col-12 col-lg-4">
                <label for="date">Day</label>
                <select id="teachingDay2" class="form-control" name="teachingDay2" required>
                    <option selected value="Select Day">Select Day</option>
                    <option value="Monday">Monday</option>
                    <option value="Tuesday">Tuesday</option>
                    <option value="Wednesday">Wednesday</option>
                    <option value="Thursday">Thursday</option>
                    <option value="Friday">Friday</option>
                    <option value="Saturday">Saturday</option>
                    <option value="Sunday">Sunday</option>
                </select>
            </div>
            <div class="col-12 col-lg-4">
                <label for="startTime">Start Time</label>
                <input id="teachingStartTime2" class="form-control" type="number" min="9" max="22" name="startTime2" required/>  
            </div>
        </div>
        <hr>
        <% for (int i = 3; i < 7; i++) {%>
        <div class="form-row">
            <div class="col-12 col-lg-4">
                <label for="date">Day</label>
                <select class="form-control" name="teachingDay<%=i%>">
                    <option value="Select Day" selected>Select Day</option>
                    <option value="Monday">Monday</option>
                    <option value="Tuesday">Tuesday</option>
                    <option value="Wednesday">Wednesday</option>
                    <option value="Thursday">Thursday</option>
                    <option value="Friday">Friday</option>
                    <option value="Saturday">Saturday</option>
                    <option value="Sunday">Sunday</option>
                </select>
            </div>
            <div class="col-12 col-lg-4">
                <label for="startTime">Start Time</label>
                <input class="form-control otherStartTimes" type="number" min="9" max="22" name="startTime<%=i%>"/>  
            </div>
        </div>
        <hr>
        <%}%>
        <button id="submitBtn" onclick="validationCheckTimetable()" type="button" class="btn btn-primary mt-1">Insert</button>
    </form>
    <br>
    <div class="table-responsive">
        <h4>Your Timetable & Courses</h4>
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
                    <td class="bg-light-green text-dark monday"><%=mondaySubjects.get(i)%></td>
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
                    <td class="bg-light-green text-dark tuesday"><%=tuesdaySubjects.get(i)%></td>
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
                    <td class="bg-light-green text-dark"><%=wednesdaySubjects.get(i)%> </td>
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
                    <td class="bg-light-green text-dark"><%=thursdaySubjects.get(i)%></td>
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
                            if (saturdayTime.size() > 0) {
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
                            if (sundayTime.size() > 0) {
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
    <%        // We have finished with the results of this action
        // so now we can remove the value from the session
        session.removeAttribute("courseErrorMessage");
        // This makes sure that old error messages 
        // don't mistakenly get printed out later
    %> 
</div>
<jsp:include page="footer.jsp" /> 