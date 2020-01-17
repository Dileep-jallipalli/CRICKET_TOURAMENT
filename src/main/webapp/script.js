$(document).ready(function(){
$("#buttonId").on("click", function(){
console.log("hi");
var email = $('#emailId').val();
var password = $('#passwordId').val();
$.get('register', {
emailId : email,
passwordId : password
}, function(responseText) {
if(responseText==="admin"){
window.location.replace("admin.html");
}
else if(responseText=="Owner"){
window.location.replace("ownerLogin.html");
}
else {
      alert(responseText);
     }
},);
});
});

