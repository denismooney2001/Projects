<%-- 
    Document   : paymentPage
    Created on : Dec 11, 2020, 5:17:29 PM
    Author     : junta
--%>

<%@page import="java.text.DecimalFormat"%>
<%@page import="java.time.Period"%>
<%@page import="java.time.LocalDate"%>
<%@page import="java.util.ArrayList"%>
<%@page import="Business.*"%>
<%@page import="Daos.*"%>
<%   UserDao userDao = new UserDao("digiclass");

    String username = (String) session.getAttribute("username");
    User u = userDao.getUserByUsername(username);
    CourseDao courseDao = new CourseDao("digiclass");
    Course course = null;
    JoinDao joinDao = new JoinDao("digiclass");

    int courseIdPayDue = 0;
    int fixedAmount = 0;
    int courseMonthsLeft = 0;
    int weeksLeftOver = 0;
    double monthlyPaymentOnCourse = 0;

    double displayOwedMoney = 0;
    boolean coursePaymentInProgress = false;
    DecimalFormat df = new DecimalFormat("0.00");
    if (u.getUser_type().equals("STUDENT") && session.getAttribute("courseIdPayCourse") != null) {
        courseIdPayDue = (int) session.getAttribute("courseIdPayCourse");

        coursePaymentInProgress = true;

        fixedAmount = 950;
        monthlyPaymentOnCourse = 900;
        course = courseDao.getCourseByID(courseIdPayDue);
        String courseStartDate = course.getStart_date();
        int courseStartDateYear = Integer.parseInt(courseStartDate.substring(0, 4));
        int courseStartDateMonth = Integer.parseInt(courseStartDate.substring(5, 7));
        int courseStartDateDay = Integer.parseInt(courseStartDate.substring(8, 10));

        String courseEndDate = course.getEnd_date();
        int courseEndDateYear = Integer.parseInt(courseEndDate.substring(0, 4));
        int courseEndDateMonth = Integer.parseInt(courseEndDate.substring(5, 7));
        int courseEndDateDay = Integer.parseInt(courseEndDate.substring(8, 10));

        LocalDate startLocalDate = LocalDate.of(courseStartDateYear, courseStartDateMonth, courseStartDateDay);
        LocalDate endLocalDate = LocalDate.of(courseEndDateYear, courseEndDateMonth, courseEndDateDay);

        Period periodBetween = Period.between(startLocalDate, endLocalDate);
        int days = periodBetween.getDays();
        int months = periodBetween.getMonths();

        monthlyPaymentOnCourse = fixedAmount / months;

        displayOwedMoney = Double.parseDouble(df.format(monthlyPaymentOnCourse));
    }


%>

<jsp:include page="header.jsp" />  
<jsp:include page="navbar.jsp" />  
<div class="container text-left mb-5 mt-5 p-5 font">
    <p id="userType" class="d-none"><%=u.getUser_type()%></p>
    <%if (coursePaymentInProgress = false) { %>
    <h2>Enter Payment Details</h2>
    <%} else { %> 
    <h2>Pay Course Fee</h2>
    <h5>Course ID: <%=courseIdPayDue%></h5>
    <%} %>
    <br>
    <form action="controller" method="post" autocomplete="off">
        <input type="hidden" name="action" value="payOverdueFee"/>
        <input type="hidden" name="userId" value="<%=u.getID()%>"/>
        <input type="hidden" name="courseId" value="<%=courseIdPayDue%>"/>
        <div class="form-group row">
            <label class="col-sm-2 col-form-label" for="cardHolderName">Name  :</label>
            <div class="col-sm-10">
                <input type="text" class="form-control" id="cardHolderName" name="cardHolderName" placeholder="Barry Hughes" required>
            </div>
        </div>
        <div class="form-group row">
            <label class="col-sm-2 col-form-label" for="cardType">Card Type :</label>
            <div class="col-sm-10">
                <select name="cardType" class="custom-select" required >
                    <option selected>Select Card Type...</option>
                    <option value="Mastercard">Mastercard</option>
                    <option value="Visa">Visa</option>
                </select>
            </div>
        </div>
        <div class="form-group row">
            <label class="col-sm-2 col-form-label" for="cardNumber">Card Number :</label>
            <div class="col-sm-10">
                <input type="text" class="form-control" id="cardNumber" name="cardNumber" pattern="^4[0-9]{12}(?:[0-9]{3})" placeholder="XXXX-XXXX-XXXX-XXXX" required>
            </div>
        </div>

        <div class="form-group row">
            <label class="col-sm-2 col-form-label" for="expireDate">Expire Date  :</label>
            <div class="col-sm-2">
                <input type="text" class="form-control" id="expireDate" name="expireDate"  pattern="\d{4}" placeholder="MM/YY"  required>
            </div>
            <label class="col-sm-1 col-form-label" for="cvc">CVC  :</label>
            <div class="col-sm-2">
                <input type="text" class="form-control" id="cvc" name="cvc" placeholder="123" pattern="\d{3}" required>
            </div>
        </div>

        <div class="form-group row">
            <label class="col-sm-2 col-form-label" for="billingAddressLine1">Address Line 1:</label>
            <div class="col-sm-10">
                <input type="text" class="form-control" id="billingAddressLine1" name="billingAddressLine1" placeholder="Address Line 1" required>
            </div>
        </div>

        <div class="form-group row">
            <label class="col-sm-2 col-form-label" for="billingAddressLine2">Address Line 2:</label>
            <div class="col-sm-10">
                <input type="text" class="form-control" id="billingAddressLine2" name="billingAddressLine2" placeholder="Address Line 2" required>
            </div>
        </div>

        <div class="form-group row">
            <label class="col-sm-2 col-form-label" for="amount">Amount: (EUR.)</label>
            <div class="col-sm-10">
                <input type="text" class="form-control" id="amount" name="amount" value="<%=displayOwedMoney%>" readonly>
            </div>
        </div>

        <div class='text-center mb-2'>
            <button class="btn btn-danger text-center"><i class="fas fa-lock"> Pay</i></button>
        </div>
    </form>   
</div>
<jsp:include page="footer.jsp" />  
