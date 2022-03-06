/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


async function searchResultTeacher(teacherID) {
    var bodyFormData = new FormData();
    bodyFormData.append('teacherID', teacherID);
    return await fetch("controller?action=searchResultTeacher",
            {
                method: "POST",
                body: bodyFormData
            }
    ).then((response) => response.text())
            .then(data => {
                return data
            });
}

$(document).ready(function () {
    $('#searchCourse').on('click', async function () {
        let value = $('#submitValue').val();

        let responseText = await searchResultTeacher(value);
        console.log(responseText);
        let response = JSON.parse(responseText);

        let obj1;
        let obj2;
        let obj3;

        let splitobj1 = [];
        let splitobj2 = [];
        let splitobj3 = [];


        for (let i = 0; i < response.length; i++) {
            obj1 = response[0];
            obj2 = response[1];
            obj3 = response[2];
        }
        if (obj1) {
            splitobj1 = obj1.toString().split(',');
        }
        if (obj2) {
            splitobj2 = obj2.toString().split(',');
        }
        if (obj3) {
            splitobj3 = obj3.toString().split(',');
        }

        let teacherName = '';
        let subjectname = "";
        let subjectLevel = "";
        let aboutMe = "";

        if (obj1 && obj2 && obj3) {
            for (let i = 0; i < splitobj1.length; i++) {
                teacherName = splitobj1[0] + splitobj1[1];
                subjectname = splitobj1[2] + " (" + splitobj1[3] + ") " + splitobj2[2] + " (" + splitobj2[3] + ")" + splitobj3[2] + "(" + splitobj3[3] + ")";
                aboutMe = splitobj1[4];
            }
        } else if (obj1 && obj2) {
            for (let i = 0; i < splitobj1.length; i++) {
                teacherName = splitobj1[0] + splitobj1[1];
                subjectname = splitobj1[2] + " (" + splitobj1[3] + ") " + splitobj2[2] + " (" + splitobj2[3] + ")";
                aboutMe = splitobj1[4];
            }
        } else if (obj1) {
            for (let i = 0; i < splitobj1.length; i++) {
                teacherName = splitobj1[0] + splitobj1[1];
                subjectname = splitobj1[2] + " (" + splitobj1[3] + ") ";
                aboutMe = splitobj1[4];
            }
        }

        $('#teacherName').text(teacherName);
        $('#subjectName').text(subjectname);
        $('#subjectLevel').text(subjectLevel);
        $('#aboutMe').text(aboutMe);

    });
}

);


