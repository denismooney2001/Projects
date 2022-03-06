<%-- 
    Document   : changePassword
    Created on : Apr 21, 2021, 5:06:39 PM
    Author     : junta
--%>
<jsp:include page="header.jsp" />  
<jsp:include page="navbar.jsp" /> 
<div class="container-fluid">
    <div class="container mt-5 mb-5 p-5 border">
        <div class="row d-flex justify-content-center mt-2">
            <div class="collapse multi-collapse" id="multiCollapseExample2">
                <form action="controller" method="post" autocomplete="off">
                    <input name="action" value="resetPassword" type="hidden"/>
                    <div class="card p-5 bg-white">
                        <h4 class="border-bottom">Reset Password</h4>
                        <div class="card-body">
                            <h5 class="card-title text-dark">	
                                It's a good idea to use a strong password that you don't use elsewhere</h5>
                            <div class="form-group row">
                                <label for="current" class="col-sm-2 col-form-label">Current</label>
                                <div class="col-sm-10">
                                    <input type="password" class="form-control" name="current" required>
                                </div>
                            </div>
                            <div class="form-group row">
                                <label for="newPassword" class="col-sm-2 col-form-label"> New</label>
                                <div class="col-sm-10">
                                    <input type="password" class="form-control" name="newPassword" required>
                                </div>
                            </div>
                            <div class="form-group row">
                                <label for="reNewPass" class="col-sm-2 col-form-label">Retype New</label>
                                <div class="col-sm-10">
                                    <input type="password" class="form-control" name="reNewPass" required>
                                </div>
                            </div>
                            <button class="btn btn-primary">Submit</button>
                        </div>
                    </div>
                </form>
            </div>

        </div>
    </div>
</div>
<jsp:include page="footer.jsp" />  