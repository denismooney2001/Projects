<%-- 
    Document   : error
    Created on : Dec 26, 2020, 10:23:24 PM
    Author     : denis
--%>

<%@page import="java.util.ResourceBundle"%>
<jsp:include page="header.jsp" />  
<jsp:include page="navbar.jsp" />  
<div id="startCourse" class="container text-left mb-5 mt-5 font">
    <div class="container mt-5 mb-5 p-5 text-center">
        <h1>Something went wrong</h1>
        <%
            // Get the error message variable out of the session
            Object msg = session.getAttribute("errorMessage");
            // Cast it to a String so we can use it
            String error = (String) msg;
            // Display the message
        %>

        <h5 class="text-danger">Error: *** <%=error%> </h5>
        <%
            // We have finished with the results of this action
            // so now we can remove the value from the session
            session.removeAttribute("errorMessage");
            // This makes sure that old error messages 
            // don't mistakenly get printed out later
%> 
<br>
<button onclick="window.history.back()" class="btn btn-danger">Go back</button>
    </div>
</div>
<jsp:include page="footer.jsp" />  
