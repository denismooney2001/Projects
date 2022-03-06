<%-- 
    Document   : resetPasswordMethod
    Created on : Apr 21, 2021, 3:45:43 PM
    Author     : junta
--%>
<%@page import="Business.*"%>
<jsp:include page="header.jsp" />  
<jsp:include page="navbar.jsp" />   
<div class="container-fluid">
    <div class="container mt-5 mb-5 p-5 border">
        <h3>Forget Password</h3>
        <div class="row mt-3">
            <button class="btn btn-primary" data-toggle="collapse" data-target="#multiCollapseExample1" aria-expanded="false" aria-controls="multiCollapseExample1">I have forgotten my password and want to reset it</button>  
        </div>
        <div class="row d-flex justify-content-center mt-2">
            <div class="collapse multi-collapse" id="multiCollapseExample1">        
                <div id="checkEmailForm">
                    <div class="card p-5 bg-white">
                        <h4 class="border-bottom">Find Your Account</h4>
                        <div class="card-body">
                            <h5 class="card-title text-dark">Please enter your email to search for your account.</h5>
                            <label for="email" class="d-none">Email</label>
                            <input type="text" id="emailToCheck" class="form-control" name="email" placeholder="Email" required>
                            <button type="submit" class="btn btn-primary mt-3" id="checkEmail">Submit</button>
                        </div>
                    </div>
                </div>
                <div id="notFound" class="d-none">
                    <p>Email Not Found.</p>
                </div>
                <div id="found" class="d-none">
                    <p>Please check on your email to get the verification link.</p>                    
                </div>
            </div>
        </div>
    </div>
</div>
<jsp:include page="footer.jsp" />  