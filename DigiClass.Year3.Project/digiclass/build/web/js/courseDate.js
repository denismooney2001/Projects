$(document).ready(function () {
    var newDate = new Date();
    newDate.setDate(newDate.getDate() + (1 + 7 - newDate.getDay()) % 7);
    newDate.setDate(newDate.getDate() + 7);
    var ndd = newDate.getDate() + 7;
    var nmm = newDate.getMonth() + 1;
    var nyyyy = newDate.getFullYear();
    if (ndd > 28) {
        ndd -= 7;
    }

    if (ndd < 10) {
        ndd = '0' + ndd;
    }
    if (nmm < 10) {
        nmm = '0' + nmm;
    }

    newDate1 = nyyyy + '-' + nmm + '-' + ndd;

    $("#datefield").datepicker({
        numberOfMonths: 1,
        dateFormat: "yy-mm-dd",
        minDate: newDate1,
        onSelect: function (selectDate) {
            var newDate = new Date(selectDate);
            newDate.setDate(newDate.getDate() + 1);

            let date = $('input[name="startDate"]').val();


            if (Date.parse(date)) {
                $("#selectEndDateDiv").show();

            } else {
                $("#selectEndDateDiv").hide();
            }

            newDate.setDate(newDate.getDate() + (1 + 7 - newDate.getDay()) % 7);
            newDate.setDate(newDate.getDate() + 7);
            var ndd = newDate.getDate() + 7;
            var nmm = newDate.getMonth() + 4;
            var nyyyy = newDate.getFullYear();
            if (ndd > 28) {
                ndd -= 7;
            }

            if (ndd < 10) {
                ndd = '0' + ndd;
            }
            if (nmm < 10) {
                nmm = '0' + nmm;
            }

            newDate = nyyyy + '-' + nmm + '-' + ndd;

            $("#endDate").datepicker("option", "minDate", newDate);
            // $("#datefield").val($.datepicker("option", "minDate", newDate));
        }
    });

    $("#endDate").datepicker({
        numberOfMonths: 1,
        dateFormat: "yy-mm-dd",
        onSelect: function (selectDate) {
            var dt = new Date(selectDate);
            dt.setDate(dt.getDate() - 1);
            $("#datefield").datepicker("option", "maxDate", dt);
            
            let date = $('input[name="startDate"]').val();


            if (Date.parse(date)) {
                $("#selectEndDateDiv").show();
                let endDate = $('input[name="endDate"]').val();
                
                if(Date.parse(endDate)){
                    $('#startCourseButton').removeAttr('disabled');
                    document.getElementById("startCourseButton").classList.remove("d-none");
                }

            } else {
                $("#selectEndDateDiv").hide();
            }
        }
    });

//    $('input[name="startDate"]').blur(function () {
//        let date = $('input[name="startDate"]').val();
//
//
//        if (Date.parse(date)) {
//            $("#selectEndDateDiv").show();
//
//        } else {
//            $("#selectEndDateDiv").hide();
//            $("#startCourse").prop("disabled", true);
//        }
//    });
});