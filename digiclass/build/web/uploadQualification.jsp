<%-- 
    Document   : uploadQualification
    Created on : Mar 17, 2021, 6:27:16 PM
    Author     : denis
--%>

<form action="uploadQual.jsp" method="post" enctype="multipart/form-data">  
    <h6 class="font-weight-bold">Insert Qualification:</h6>
    <h6 >(E.g. a photo of your Award)</h6>
    <input onclick="displayQualification()" id="uploadQualification" type="file" name="qualificationImage" class="btn btn-warning btn-block" required/>
    <button id="displayQualificationBtn" class="btn btn-warning d-none m-2">Insert</button>
</form>  