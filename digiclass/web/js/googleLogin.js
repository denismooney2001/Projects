/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


function onSignIn(googleUser) {
    var profile = googleUser.getBasicProfile();
    var idtoken = profile.getId(); // Do not send to your backend! Use an ID token instead.
    var id_token = googleUser.getAuthResponse().id_token;
    console.log('Name: ' + profile.getName());
    console.log('Image URL: ' + profile.getImageUrl());
    console.log('Email: ' + profile.getEmail()); // This is null if the 'email' scope is not present.
    console.log(id_token);
    console.log(idtoken);
}

function signOut() {
    var auth2 = gapi.auth2.getAuthInstance();
    auth2.signOut().then(function () {
        console.log('User signed out.');
        window.location.href= "index.jsp";
    });
}