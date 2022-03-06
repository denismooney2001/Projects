<%-- 
    Document   : cancelModal
    Created on : Mar 16, 2021, 9:15:22 AM
    Author     : junta
--%>
<%@page import="java.util.HashMap"%>
<%@page import="Business.*"%>
<%@page import="Daos.*"%>
<%@page import="java.util.ArrayList"%>


<input type="hidden" value="" id="courseID"/>
<button type="button" class="btn btn-danger btn-block" data-toggle="modal" data-target="#cancelClass">
    <i class="fa fa-times" aria-hidden="true"></i>
</button>
<div class="modal fade" id="cancelClass" tabindex="-1" role="dialog" aria-labelledby="cancelClass" aria-hidden="true">
    <div class="modal-dialog modal-dialog-centered" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="cancelClass">Cancel Class</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body">
                <form action="controller" method="post" autocomplete="off" >
                    <input name="action" value="cancelClass" type="hidden"/>
                    <input class="form-control" id="datepic" name="date" type="text" required/>
                    <input type="hidden" name="timetableID" id="setID" value=""/>     
                    <input type="hidden" name="sDate" id="sDate" value=""/>     
                    <input type="hidden" name="eDate" id="eDate" value=""/>                         
                    <button type="button" onclick="cancelClassValidationCheck()" id="cancelBtn" class="mt-2 btn btn-danger btn-block rounded-pill">Cancel Class</button>
                </form>
            </div>
        </div>
    </div>
</div>
