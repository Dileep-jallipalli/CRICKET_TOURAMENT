$(document).ready(function(){
$("#loginBtn").on("click", function(){
console.log("hi");
var email = $('#emailId').val();
var password = $('#passwordId').val();
$.get('register', {
email : email,
password : password
}, function(responseText) {
alert(responseText);
});
});
});