<%-- 
    Document   : uploadQual
    Created on : Mar 18, 2021, 8:45:35 AM
    Author     : denis
--%>

<%@ page import="com.oreilly.servlet.MultipartRequest" %>  
<%
    MultipartRequest m = new MultipartRequest(request, application.getRealPath("/") + "/image/teachersQualifications");
%> 
<meta http-equiv = "refresh" content = "0; url = controller?action=uploadQualification&image_name=<%=m.getFile("qualificationImage").toString()%>" />