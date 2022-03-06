$(document).ready(function() {
   $("input").change(function (){
       var result=0;
       $("input[class=day]").each(function() {
           result = result + parseInt($(this).val());
       });
       $("input[id=result]").val(result);
                   
       });
  
});

$(document).ready(function() {
   $("input").change(function (){
       var result=0;
       $("input[class=day1]").each(function() {
           result = result + parseInt($(this).val());
       });
       $("input[id=result1]").val(result);
                   
       });
  
});