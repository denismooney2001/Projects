<%-- 
    Document   : payOngoingStudentFees
    Created on : Apr 7, 2021, 11:09:55 PM
    Author     : denis
--%>

<%@page import="java.util.Map"%>
<%@page import="java.util.HashMap"%>
<%@page import="java.util.ArrayList"%>
<%@page import="Business.*"%>
<%@page import="Daos.*"%>
<%   UserDao userDao = new UserDao("digiclass");

    String username = (String) session.getAttribute("username");
    User u = userDao.getUserByUsername(username);
    UserDetailsDao userDetDao = new UserDetailsDao("digiclass");
    
    JoinDao joinDao = new JoinDao("digiclass");
    ArrayList<Course> unPaidCourses = joinDao.getOverdueCourses(u.getID());
    TeacherSubjectsDao teacherSubDao = new TeacherSubjectsDao("digiclass");
    SubjectDao subjectDao = new SubjectDao("digiclass");

    UserPaymentDao userPaymentDao = new UserPaymentDao("digiclass");
    ReviewsDao revDao = new ReviewsDao("digiclass");
    
    TeacherDetailsDao teacherDetDao = new TeacherDetailsDao("digiclass");
    
    ArrayList<Integer> studentTeacherUserIds = joinDao.getUserIdsOfTeacherByStudent(u.getID());
    
    HashMap<Integer, String> teachers = new HashMap<>();
    
    String teacherName = "";
    for(int i = 0; i < studentTeacherUserIds.size(); i++){
        teacherName = userDetDao.getUserDetailsByUserId(studentTeacherUserIds.get(i)).getFirst_name() + " " + userDetDao.getUserDetailsByUserId(studentTeacherUserIds.get(i)).getLast_name();
        teachers.put(teacherDetDao.getTeacherDetailsByUserId(studentTeacherUserIds.get(i)).getTeacher_id(), teacherName);
        
    }
    
%>
<jsp:include page="header.jsp" />  
<jsp:include page="navbar.jsp" />  
<div class="container text-left mb-5 mt-5 p-5 font">
    
    <img class="w-100" src="image/reviewSystemPic.jpg"/>
    <h2>Insert a Review</h2>
    <br>
    <form action="controller" method="post" autocomplete="off">
        <input type="hidden" name="action" value="insertReview"/>
        <input type="hidden" name="userId" value="<%=u.getID()%>"/>
        <div class="form-group row">
            <label class="col-sm-2 col-form-label" for="teacherId">Select Teacher :</label>
            <div class="col-sm-9">
                <select name="teacherId" class="custom-select" required >
                    <option selected>Select Teacher...</option>
                    <%for(Map.Entry<Integer, String> t : teachers.entrySet()){ %>
                    <option value="<%=t.getKey()%>"><%=t.getValue() %></option>
                    <%}%>
                </select>
            </div>
        </div>
        <div class="form-group row">
            <label class="col-sm-2 col-form-label" for="rating">Rating (out of 10) :</label>
            <div class="col-sm-2">
                <input type="number" min=0 max=10 class="form-control" id="cardNumber" name="rating" placeholder="e.g. 9/10"required>
            </div>
        </div>

        <div class="form-group row">
            <label class="col-sm-2 col-form-label" for="description">Description  :</label>
            <div class="col-sm-9">
                <textarea style="resize:none" class="form-control" rows="4" cols="50" name="description" placeholder="Enter Description ...">
                </textarea>
            </div>
        </div>
                
        <div class='text-center mb-2'>
            <button class="btn btn-primary text-center">Submit</button>
        </div>
    </form>   

</div>
<jsp:include page="footer.jsp" />  