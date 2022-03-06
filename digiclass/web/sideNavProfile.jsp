<%-- 
    Document   : sideNavProfile
    Created on : Feb 16, 2021, 6:10:34 PM
    Author     : junta
--%>
<%@page import="Business.*"%>
<%@page import="Daos.*"%>
<%  UserDao userDao = new UserDao("digiclass");
    UserDetailsDao userDetailsDao = new UserDetailsDao("digiclass");
    TeacherDetailsDao teacherDetailsDao = new TeacherDetailsDao("digiclass");
    String username = (String) session.getAttribute("username");
    User u = userDao.getUserByUsername(username);
    UserDetails ud = userDetailsDao.getUserDetailsByUserId(u.getID());
    if (ud == null) {
        ud = new UserDetails(0, 0, "", "", "", "", "", "", "");
    }
    TeacherDetails teacherDetails = teacherDetailsDao.getTeacherDetailsByUserId(u.getID());
%>

<% if (ud.getUser_id() == 0) {%>
<div class="row d-flex justify-content-center">
    <div class="p-2">
        <button type="button" class="btn btn-primary btn-block" data-toggle="modal" data-target="#editProfile">
            Insert Profile Details
        </button>
        <div class="modal fade" id="editProfile" tabindex="-1" role="dialog" aria-labelledby="editProfile" aria-hidden="true">
            <div class="modal-dialog modal-dialog-centered" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title" id="editDetails">Edit Details</h5>
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>
                    <div class="modal-body">
                        <jsp:include page="insertProfileDetails.jsp" />
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<%}%>              

<%if (u.getUser_type().equals("TEACHER") && teacherDetails == null) {%>
<div class='row d-flex justify-content-center'>
    <div class="p-2">
        <button type="button" class="btn btn-primary" data-toggle="modal" data-target="#editTeacherDetails">
            Insert Teacher Details
        </button>
        <div class="modal fade" id="editTeacherDetails" tabindex="-1" role="dialog" aria-labelledby="editTeacherDetails" aria-hidden="true">
            <div class="modal-dialog modal-dialog-centered" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title" id="teacherDetails">Edit Teacher Details</h5>
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>
                    <div class="modal-body">
                        <jsp:include page="teacherRegisterDetails.jsp" />
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<%}%>

