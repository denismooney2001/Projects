/*  ==========================================
 DISPLAY SELECT OPTION IN THE FORM
 * ========================================== */
function displaySubject2() {
    let subject1 = document.getElementById("subject1");
    let subject2Div = document.getElementById("subject2Div");
    if (subject1 !== null) {
        subject2Div.classList.remove("d-none");
    }
}

function displaySubject3() {
    let subject2 = document.getElementById("subject2");
    let subject3Div = document.getElementById("subject3Div");
    if (subject2 !== null) {
        subject3Div.classList.remove("d-none");
    }
}

/*  ===================================================
 TO HIDE THE SELECTED OPTION ON THE NEXT SELECT OPTION
 * ==================================================== */
$(document).on('change', '#subject1', function () {
    let selected1 = $(this).find(":selected").val();
    $("#subject2 option[value=" + selected1 + "]").hide();
});
$(document).on('change', '#subject1', function () {
    let selected1 = $(this).find(":selected").val();
    $("#subject3 option[value=" + selected1 + "]").hide();
});
$(document).on('change', '#subject2', function () {
    let selected2 = $(this).find(":selected").val();
    $("#subject3 option[value=" + selected2 + "]").hide();
    $("#subject1 option[value=" + selected2 + "]").hide();
});
$(document).on('change', '#subject3', function () {
    let selected2 = $(this).find(":selected").val();
    $("#subject2 option[value=" + selected2 + "]").hide();
    $("#subject1 option[value=" + selected2 + "]").hide();
});
/*  ===================================================
 HIDE AND SHOW ITEM ON NAVBAR
 * ==================================================== */
window.addEventListener('DOMContentLoaded', function () {
    let type = document.getElementById("userType").textContent;
    if (type === "ADMIN") {
        document.getElementById("contact-tab").classList.add("d-none");
        document.getElementById("feature-tab").classList.add("d-none");
        document.getElementById("profile-tab").classList.add("d-none");
        document.getElementById("admin-tab").classList.remove("d-none");
        document.getElementById("dropdownNavSection").classList.remove("d-none");
    } else if (type === "TEACHER") {
        document.getElementById("teacher-tab").classList.remove("d-none");
        document.getElementById("contact-tab").classList.add("d-none");
        document.getElementById("feature-tab").classList.add("d-none");
        document.getElementById("message-tab").classList.remove("d-none");
        document.getElementById("dropdownNavSection").classList.remove("d-none");
    } else if (type === "STUDENT") {
        document.getElementById("student-tab").classList.remove("d-none");
        document.getElementById("contact-tab").classList.add("d-none");
        document.getElementById("feature-tab").classList.add("d-none");
        document.getElementById("message-tab").classList.remove("d-none");
        document.getElementById("dropdownNavSection").classList.remove("d-none");
    }
}, true);
/*  ===================================================
 * TO DISPLAY OR HIDE THE P TAGE AND INPUT TAG
 * ==================================================== */
function changeToInput() {
    let hometab = document.getElementById("home");
    let profileTab = document.getElementById("profileTabs");
    let type = document.getElementById("userType").textContent;
    if (hometab.classList.contains("active")) {
        document.getElementById("contactNumber").classList.toggle("d-none");
        document.getElementById("updateNumber").classList.toggle("d-none");
        document.getElementById("address1").classList.toggle("d-none");
        document.getElementById("updateAddress1").classList.toggle("d-none");
        document.getElementById("address2").classList.toggle("d-none");
        document.getElementById("updateAddress2").classList.toggle("d-none");
        document.getElementById("dob").classList.toggle("d-none");
        if (type === "TEACHER") {
            document.getElementById("updateTeacherDOB").classList.toggle("d-none");
        } else if (type === "STUDENT") {
            document.getElementById("updateStudentDOB").classList.toggle("d-none");
        }
        document.getElementById("updateButton").classList.toggle("d-none");
    }
}


function deleteSubjects() {
    document.getElementById("updateForm").classList.toggle("d-none");
    document.getElementById("deleteForm").classList.toggle("d-none");
}

function setMinimumCourseStartDate() {
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

    newDate = nyyyy + '-' + nmm + '-' + ndd;
    document.getElementById("datefield").setAttribute("value", newDate);
    return newDate;
}

function setMinimumTeacherAgeDate() {
    var newDate = new Date();
    var ndd = newDate.getDate();
    var nmm = newDate.getMonth() + 1;
    var nyyyy = newDate.getFullYear() - 19;
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
    document.getElementById("dateOfBirthTeacher").setAttribute("max", newDate);
    return newDate;
}
function setMinimumTeacherAgeDate1() {
    var newDate = new Date();
    var ndd = newDate.getDate();
    var nmm = newDate.getMonth() + 1;
    var nyyyy = newDate.getFullYear() - 19;
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
    document.getElementById("updateTeacherDOB").setAttribute("max", newDate);
    return newDate;
}
$(document).ready(function () {
    let userType = $('#userType').text();
    if (userType === "TEACHER") {
        $('#dateOfBirthStudent').removeAttr('required');
        document.getElementById("dateOfBirthTeacher").classList.remove("d-none");
    } else if (userType === "STUDENT") {
        $('#dateOfBirthTeacher').removeAttr('required');
        document.getElementById("dateOfBirthStudent").classList.remove("d-none");
    }
});
$(document).ready(function () {
    $('#endDate').keyup(function () {
        if ($(this).val().length !== 0) {
            $('#startCourse').removeAttr('disabled');
        }
    });
});
$(document).ready(function () {  //This is your function to sort the day and times

    let teachingDay1 = $('#teachingDay1').text();
    let teachingStartTime1 = $('#teachingStartTime1').val();
    if (teachingDay1 != "" && teachingStartTime1 != null) {
        document.getElementById("startCourseButton").classList.remove("d-none");
    }

});
function setMinimumStudentAgeDate() {
    var newDate = new Date();
    var ndd = newDate.getDate();
    var nmm = newDate.getMonth() + 1;
    var nyyyy = newDate.getFullYear() - 15;
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
    document.getElementById("dateOfBirthStudent").setAttribute("max", newDate);
    return newDate;
}
function setMinimumStudentAgeDate1() {
    var newDate = new Date();
    var ndd = newDate.getDate();
    var nmm = newDate.getMonth() + 1;
    var nyyyy = newDate.getFullYear() - 15;
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
    document.getElementById("updateStudentDOB").setAttribute("max", newDate);
    return newDate;
}
function getDate() {
    $("#datepic").datepicker({
        dateFormat: "yy-mm-dd",
        beforeShowDay: function (date) {
            var day = date.getDay();
            return [day === 1, ""];
        }});
    document.getElementById("setID").value = document.getElementById("timetableId1").value;
    document.getElementById("sDate").value = document.getElementById("startDate1").value;
    document.getElementById("eDate").value = document.getElementById("endDate1").value;
}

function getDate2() {
    $("#datepic").datepicker({
        dateFormat: "yy-mm-dd",
        beforeShowDay: function (date) {
            var day = date.getDay();
            return [day === 2, ""];
        }
    });
    document.getElementById("setID").value = document.getElementById("timetableId2").value;
}
function getDate3() {
    $("#datepic").datepicker({
        dateFormat: "yy-mm-dd",
        beforeShowDay: function (date) {
            var day = date.getDay();
            return [day === 3, ""];
        }
    });
    document.getElementById("setID").value = document.getElementById("timetableId3").value;
}
function getDate4() {
    $("#datepic").datepicker({
        dateFormat: "yy-mm-dd",
        beforeShowDay: function (date) {
            var day = date.getDay();
            return [day === 4, ""];
        }
    });
    document.getElementById("setID").value = document.getElementById("timetableId4").value;
}
function getDate5() {
    $("#datepic").datepicker({
        dateFormat: "yy-mm-dd",
        beforeShowDay: function (date) {
            var day = date.getDay();
            return [day === 6, ""];
        }
    });
    document.getElementById("setID").value = document.getElementById("timetableId5").value;
}
function getDate6() {
    dateFormat: "yy-mm-dd",
            $("#datepic").datepicker({
        beforeShowDay: function (date) {
            var day = date.getDay();
            return [day === 6, ""];
        }
    });
    document.getElementById("setID").value = document.getElementById("timetableId6").value;
}
function getDate7() {
    $("#datepic").datepicker({
        dateFormat: "yy-mm-dd",
        beforeShowDay: function (date) {
            var day = date.getDay();
            return [day === 0, ""];
        }
    });
    document.getElementById("setID").value = document.getElementById("timetableId7").value;
}


$(document).ready(function () {
    let type = document.querySelectorAll(".viewType");
    let status = document.querySelectorAll(".viewStatus");
    for (let i = 0; i < type.length; i++) {
        if (type[i].textContent === "STUDENT") {
            type[i].classList.add("text-success");
        } else if (type[i].textContent === "TEACHER") {
            type[i].classList.add("text-primary");
        }
    }

    for (let j = 0; j < status.length; j++) {
        if (status[j].textContent === "ACTIVE") {
            status[j].classList.add("text-primary");
        } else if (status[j].textContent === "DISABLED") {
            status[j].classList.add("text-danger");
        }
    }
});
$(document).ready(function () {
    $("#myInput").on("keyup", function () {
        var value = $(this).val().toLowerCase();
        $("#myTable tr").filter(function () {
            $(this).toggle($(this).text().toLowerCase().indexOf(value) > -1)
        });
    });


});

function hideShowCourseLink() {
    var x = document.getElementById("pasteCourseLink");

    if (document.getElementById("pasteCourseLink").classList === "d-none") {
        document.getElementById("pasteCourseLink").classList.remove("d-none");
    } else {
        document.getElementById("pasteCourseLink").classList.toggle("d-none");
    }
}

function hideShowInsertMessage() {
    var x = document.getElementById("insertMessage");

    if (document.getElementById("insertMessage").classList === "d-none") {
        document.getElementById("insertMessage").classList.remove("d-none");
    } else {
        document.getElementById("insertMessage").classList.toggle("d-none");
    }
}
function displayBtn() {
    document.getElementById("uploadImgBtn").classList.remove("d-none");
}

function displayQualification() {
    document.getElementById("displayQualificationBtn").classList.remove("d-none");
}

function search() {
    document.getElementById("result").classList.remove("d-none");
}

$(document).ready(function () {
    $('#cancelClass').on('hide.bs.modal', function () {
        document.location.reload();
    });
});

function validationCheckTimetable() {
    var selects = document.getElementsByTagName('select');
    var values = [];
    for (i = 0; i < selects.length; i++) {
        var select = selects[i];
        values.push(select.value);
    }

    var input = document.getElementsByTagName('input');
    var inputs = [];
    for (i = 0; i < input.length; i++) {
        var timeSelect = input[i];
        if (timeSelect.type.toLowerCase() === "number") {
            inputs.push(timeSelect.value);
        }
    }

    var result = [], i = -1;
    while (values[++i]) {
        if (values[i] !== "Select Day" && inputs[i] != "") {
            result.push([values[i], inputs[i]]);
        }
    }
    if (find_keys_of_dupl(result).length > 0) {
        alert("Duplicate Result Found");
    } else {
        alert("Insert Timetable Successful");
        document.getElementById("submitBtn").setAttribute('type', 'submit');
    }
}

function find_keys_of_dupl(a) {
    var k = [];
    for (var i in a) {
        for (var j in a) {
            if (i != j && JSON.stringify(a[i]) == JSON.stringify(a[j])) {
                if (k.indexOf(i) < 0) {
                    k.push(i);
                }
            }
        }
    }
    return k;
}

function cancelClassValidationCheck() {
    let startDate = document.getElementById("sDate").value;
    let endDate = document.getElementById("eDate").value;
    let selectDate = document.getElementById("datepic").value;
    if (selectDate > startDate && selectDate < endDate) {
        alert("Cancel Class Success")
        document.getElementById("cancelBtn").setAttribute('type', 'submit');
    } else if (selectDate < startDate) {
        alert("Selected Date must greater than the course Start Date");
    } else {
        alert("Selected Date must smaller than the course End Date");

    }
}
