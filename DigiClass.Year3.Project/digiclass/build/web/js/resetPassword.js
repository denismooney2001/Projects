/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


async function resetPassword(current, newPass, conPass) {
    var bodyFormData = new FormData();
    bodyFormData.append('current', current);
    bodyFormData.append('newPassword', newPass);
    bodyFormData.append('reNewPass', conPass);
    return await fetch("controller?action=resetPassword",
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
    $('#resetPass').on('click', async function () {
        let value1 = $('#current').val();
        let value2 = $('#newPass').val();
        let value3 = $('#conPass').val();
        let responseText = await resetPassword(value1, value2, value3);
        let response = JSON.parse(responseText);
        var elems = document.querySelectorAll(".matching");


        if (response === "Password not matches") {
            [].forEach.call(elems, function (el) {
                el.classList.remove("d-none");
            });
            document.getElementById("currPass").classList.add("d-none");
        } else if (response === "Password Incorrect") {
            document.getElementById("currPass").classList.remove("d-none");
            [].forEach.call(elems, function (el) {
                el.classList.add("d-none");
            });
        } else if (response === "Password does not match Pattern") {
            [].forEach.call(elems, function (el) {
                el.classList.add("d-none");
            });
            document.getElementById("currPass").classList.add("d-none");
            document.getElementById("notMatch").classList.remove("d-none");
            document.getElementById("notMatch").classList.add("d-block");
        } else {
            [].forEach.call(elems, function (el) {
                el.classList.add("d-none");
            });
            document.getElementById("currPass").classList.add("d-none");
            document.getElementById("success").classList.remove("d-none");
            document.getElementById("resetForm").classList.add("d-none");
        }
    });
}
);

        