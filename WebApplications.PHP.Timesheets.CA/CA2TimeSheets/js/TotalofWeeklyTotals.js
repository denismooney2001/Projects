$(document).ready(function() {
   $("input").change(function (){
       var result=0;
       $("input[class=TotalColumn]").each(function() {
           result = result + parseInt($(this).val());
       });
       
       $("input[class=resultofTotalColumns]").val(result);
                   
       });
  
});


