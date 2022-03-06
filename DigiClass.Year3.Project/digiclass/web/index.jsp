<%-- 
    Document   : index
    Created on : Feb 4, 2021, 9:27:15 AM
    Author     : junta
--%>
<%@page import="Business.*"%>
<jsp:include page="header.jsp" /> 
<jsp:include page="navbar.jsp" />   
<div class="container-fluid" data-bs-spy="scroll" data-bs-target="#navbar" data-bs-offset="0" tabindex="0">
    <section id="loginRegisterContainer" class="page-section text-center">
        <div id="loginRegister" class="container text-white">
            <div class="row">
                <jsp:include page="login.jsp"/>
                <jsp:include page="register.jsp"/>
            </div>
        </div>
    </section>

    <section id="feature" class="page-section text-center bg-dark">
        <div class="container bg-secondary p-5">
            <h1 class="mb-5 text-white">Features</h1>
            <div class="row">
                <div class="col-12 col-lg-3">
                    <div class="card h-100" id="card-1">
                        <div class="card-img-top"><i class="fa fa-book fa-7x" aria-hidden="true"></i></div>
                        <div class="card-body">
                            <h5 class="card-title">E-Learning</h5>
                            <p class="card-text">Quick Delivery Of Lessons</p>
                        </div>
                    </div>
                </div>
                <div class="col-12 col-lg-3">
                    <div class="card h-100">
                        <div class="card-img-top"><i class="fa fa-graduation-cap fa-7x" aria-hidden="true"></i></div>
                        <div class="card-body">
                            <h5 class="card-title">Leaving Certificate</h5>
                            <p class="card-text">The Best Place to get Leaving Cert Grinds</p>
                        </div>
                    </div>
                </div>
                <div class="col-12 col-lg-3">
                    <div class="card h-100">
                        <div class="card-img-top"><i class="fa fa-line-chart fa-7x" aria-hidden="true"></i></div>
                        <div class="card-body">
                            <h5 class="card-title">Success</h5>
                            <p class="card-text">High rate of Success from our Qualified Teachers</p>
                        </div>
                    </div>
                </div>
                <div class="col-12 col-lg-3">
                    <div class="card h-100">
                        <div class="card-img-top"><i class="fa fa-cc-mastercard fa-7x" aria-hidden="true"></i></div>
                        <div class="card-body">
                            <h5 class="card-title">Price</h5>
                            <p class="card-text">Pay a Small Price and enroll in a High-End Course!</p>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </section>

    <section id="contactUs" class="page-section text-center">

        <div class="container pt-5">
            <div class="row">
                <div class="col-12 p-0">
                    <h1 id="contactH1" class="bg-dark text-white">Contact Us</h1>
                </div>
            </div>
            <div class="row">
                <div class="col col-lg-4 col-12 text-left bg-info text-white p-5 rounded border-right">
                    <h4>Information</h4>
                    <p>Contact us to get more information about any question you have. Just simply fill in the form and leave the message, we will get back to you as soon as possible. We provide 24-Hours Online Service.</p>
                    <p><i class="fa fa-phone" aria-hidden="true"></i> +353 777 7777</p>
                    <p><i class="fa fa-map-marker" aria-hidden="true"></i> Dundalk Co.Louth</p>
                    <p><i class="fa fa-envelope" aria-hidden="true"></i> digiclassdtc@gmail.com</p>
                    <p><i class="fa fa-clock-o" aria-hidden="true"></i> Monday-Friday 24 Hours</p>
                </div>
                <div class="col col-lg-8 col-12 text-left bg-info text-white p-5 rounded border-left">
                    <h3>Contact Form</h3>
                    <p>
                        Leave your message here...    
                        <i class="fa fa-question-circle" aria-hidden="true"></i>
                    </p>
                    <form action="controller" method="post" autocomplete="off" >
                        <input name="action" value="sendInquiry" type="hidden"/>
                        <div class="form-row text-left">
                            <div class="form-group col-12 col-lg-6">
                                <label for="name">Full Name *</label>
                                <input type="text" class="form-control" id="name" name="name" required>
                            </div>
                            <div class="form-group col-12 col-lg-6">
                                <label for="email">Email *</label>
                                <input type="email" class="form-control" id="password" name="email" required>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="message">Message</label><br>
                            <textarea  name="message" class="w-100" required>
                            </textarea>
                        </div>
                        <div class="form-group text-center">
                            <button class="btn btn-primary">Submit</button>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </section>
</div>       
<jsp:include page="footer.jsp" />  