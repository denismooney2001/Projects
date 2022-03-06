<%-- 
    Document   : viewAllMessages
    Created on : Mar 6, 2021, 5:39:35 PM
    Author     : junta
--%>

<%@page import="java.util.ArrayList"%>
<%@page import="Business.*"%>
<%@page import="Daos.*"%>
<jsp:include page="header.jsp" />  
<jsp:include page="navbar.jsp" />    
<%  UserDao userDao = new UserDao("digiclass");
    EnquiriesDao enquiriesDao = new EnquiriesDao("digiclass");
    String username = (String) session.getAttribute("username");
    User u = userDao.getUserByUsername(username);
    ArrayList<Enquiries> eList = enquiriesDao.getAllUnseenEnquiries();
    int i = 0;
    int j = 0;
%> 
<p id="userType" class="d-none"><%=u.getUser_type()%></p>
<div class=" container text-left mb-5 mt-5 p-5 font">
    <img class="w-100" src="image/verify.jpg"/>
    <div class="mt-3">
        <h3>Enquiries List</h3>
    </div>
    <div class="row">
        <div class="col-lg-4 col-12">
            <div class="list-group" id="list-tab" role="tablist">
                <% for (Enquiries e : eList) {%>
                <a class="list-group-item list-group-item-action" id="list-message-list" data-toggle="list" href="#list-verify-<%=i%>" role="tab" aria-controls="message">
                    <%=e.getEmail()%>
                </a>
                <%
                        i++;
                    }%>
            </div>
        </div>
        <div class="col-lg-8 col-12">
            <div class="tab-content" id="nav-tabContent">
                <% for (Enquiries e : eList) {%>
                <div class="tab-pane fade show text-white p-3 verify-tab" id="list-verify-<%=j%>" role="tabpanel" aria-labelledby="list-message-list">
                    <div class="row">
                        <label> Email : </label>
                        <p> <%=e.getEmail()%></p>
                    </div>
                    <div class="row">
                        <label> Message : </label>
                        <p><%=e.getMessage()%></p>
                    </div>
                </div>
                <%j++;
                        }%>
            </div>
        </div>
    </div>
</div>
<jsp:include page="footer.jsp" />  