$(document).ready(function(){
$("#registerId").on("click", function(){
console.log("hi");
var email = $('#emailId').val();
var password = $('#passwordId').val();
$.get('registerCaptain', {
emailId : email,
passwordId : password
}, function(responseText) {
if(responseText=="1"){
window.location.replace("index.html");
}
else{
      alert(responseText);
     }
},);
});
});