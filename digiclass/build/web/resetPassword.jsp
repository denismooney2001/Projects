<%-- 
    Document   : resetPassword
    Created on : Apr 21, 2021, 5:18:56 PM
    Author     : junta
--%>
<jsp:include page="header.jsp" />  
<jsp:include page="navbar.jsp" /> 
<div class="container-fluid">
    <div class="container mt-5 mb-5 p-5 border">
        <h3>Reset Password</h3>
        <div class="row mt-3">
            <div>
                <button class="btn btn-primary" data-toggle="collapse" data-target="#multiCollapseExample2" aria-expanded="false" aria-controls="multiCollapseExample2">Reset</button>
            </div>
        </div>
        <div class="row d-flex justify-content-center mt-2">

            <div class="collapse multi-collapse" id="multiCollapseExample2">
                <form action="controller" method="post" autocomplete="off">
                    <input name="action" value="forgetPassword" type="hidden"/>
                    <div class="card p-5 bg-white">
                        <h4 class="border-bottom">Reset Password</h4>
                        <div class="card-body">
                            <h5 class="card-title text-dark">	
                                It's a good idea to use a strong password that you don't use elsewhere</h5>
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