function loadData(){
var jsonData;
$.get('Profile', {
}, function(responseText){
    console.log(responseText);

    jsonData=JSON.parse(responseText)
  var data  = jsonData["Player_Details"];
   console.log(data);
  for(i=0;i<data.length;i++){
      console.log(data);
      var x = $.parseHTML(create_template(data[i]));
      console.log(x);
      var img=$.parseHTML('<img class="profileImg" src="profileImg.jpg">');
       document.getElementById('profile').append(img[0]);
      document.getElementById('profile').append(x[0]);
      document.getElementById('profile').append(x[2]);
      document.getElementById('profile').append(x[4]);
      document.getElementById('profile').append(x[6]);
  }
});
}

function create_template(data){
    const template = `<div class="profileDetails">Name:${data.Name}</div>
        <div class="profileDetails">Id:${data.UserId}</div>
        <div class="profileDetails">Email:${data.EmailId}</div>
        <div class="profileDetails">Skills:${data.Skills}</div>`;

     return template;
};