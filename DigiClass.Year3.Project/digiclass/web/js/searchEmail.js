/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


async function checkEmail(email) {
    var bodyFormData = new FormData();
    bodyFormData.append('email', email);
    return await fetch("controller?action=resetCheckEmail",
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
    $('#checkEmail').on('click', async function () {
        let value = $('#emailToCheck').val();

        let responseText = await checkEmail(value);
        let response = JSON.parse(responseText);

        if (response === "Email Not Found") {
            document.getElementById("checkEmailForm").classList.add("d-none");
            document.getElementById("notFound").classList.remove("d-none");
        } else {
            document.getElementById("checkEmailForm").classList.add("d-none");
            document.getElementById("found").classList.remove("d-none");
        }
    });
}
);

        