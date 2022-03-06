<%-- 
    Document   : upload
    Created on : Mar 15, 2021, 5:59:20 PM
    Author     : junta
--%>


<%@page import="java.io.File"%>
<%@ page import="com.oreilly.servlet.MultipartRequest" %>  
<%
    String test2 = getServletContext().getRealPath("/");
    File f = new File(test2);
    String location = f.getParentFile().getParentFile().toString();
    MultipartRequest m = new MultipartRequest(request, location + "/web/image/profilePictures");
%> 
<meta http-equiv = "refresh" content = "0; url = controller?action=uploadImage&image_name=<%=m.getFile("fname").toString()%>" />
