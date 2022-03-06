<%-- 
    Document   : resetPassLoggedIn
    Created on : May 2, 2021, 12:29:09 AM
    Author     : junta
--%>
<%@page import="Business.User"%>
<%@page import="Daos.*"%>
<%  UserDao userDao = new UserDao("digiclass");
    String username = (String) session.getAttribute("username");
    User u = userDao.getUserByUsername(username);
%>
<jsp:include page="header.jsp" />  
<jsp:include page="navbar.jsp" /> 

<p id="userType" class="d-none"><%=u.getUser_type()%></p>
<div class="container-fluid">
    <div class="container mt-5 mb-5 p-5 border">
        <h3>Reset Password</h3>
        <div class="row mt-3">
            <div>
                <button class="btn btn-primary" data-toggle="collapse" data-target="#multiCollapseExample2" aria-expanded="false" aria-controls="multiCollapseExample2">I know my password and i want to reset.</button>
            </div>
        </div>
        <div class="row d-flex justify-content-center mt-2">
            <div class="collapse multi-collapse" id="multiCollapseExample2">
                <div class="card p-5 bg-white">
                    <h4 class="border-bottom">Reset Password</h4>
                    <div class="card-body">
                        <h5 class="card-title text-dark">	
                            It's a good idea to use a strong password that you don't use elsewhere</h5>
                        <div id="resetForm" class="form-group row">
                            <p id="notMatch" class="d-none">Password does not match pattern</p>
                            <label for="newPassword" class="col-sm-2 col-form-label"> Current Password</label>
                            <div class="col-sm-10">
                                <input type="password" id="current" class="form-control" pattern="(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#$%^&+=]).{8,}" name="current" required>
                                <small id="currPass" class="d-none">Password Incorrect</small>
                            </div>
                        </div>
                        <div class="form-group row">
                            <label for="newPassword" class="col-sm-2 col-form-label"> New</label>
                            <div class="col-sm-10">
                                <input type="password" id="newPass" class="form-control" pattern="(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#$%^&+=]).{8,}" name="newPassword" required>
                                <small class="d-none matching">Password does not match</small>
                            </div>
                        </div>
                        <div class="form-group row">
                            <label for="reNewPass" class="col-sm-2 col-form-label">Retype New</label>
                            <div class="col-sm-10">
                                <input type="password" id="conPass" class="form-control" pattern="(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#$%^&+=]).{8,}" name="reNewPass" required>
                                <small class="d-none matching">Password does not match</small>
                            </div>
                        </div>
                        <button type="submit" class="btn btn-primary mt-3" id="resetPass">Submit</button>
                    </div>
                </div>
                <div class="d-none" id="success">
                    <p>Password Reset Successful.</p>
                </div>
            </div>
        </div>
    </div>
</div>
<jsp:include page="footer.jsp" />  