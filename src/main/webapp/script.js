$(document).ready(function(){
$("#buttonId").on("click", function(){
console.log("hi");
var email = $('#emailId').val();
var password = $('#passwordId').val();
$.get('register', {
emailId : email,
passwordId : password
}, function(responseText) {
if(responseText=="1"){
window.location.assign("Cricketapp/index.jsp");
}
else{
      $('#ajaxGetUserServletResponse').text(responseText);
     }
},);
});
});

