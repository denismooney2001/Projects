<%-- 
    Document   : uploadImage
    Created on : Mar 15, 2021, 3:34:33 PM
    Author     : junta
--%>

<form action="upload.jsp" class="mb-2" method="post" enctype="multipart/form-data">  
    <h6 class="font-weight-bold">Update Profile Picture:</h6>
    <input onclick="displayBtn()" id="uploadImage" type="file" name="fname" class="btn btn-primary btn-block" required/>
    <button id="uploadImgBtn" class="btn btn-primary d-none m-2">Upload</button>
</form>  