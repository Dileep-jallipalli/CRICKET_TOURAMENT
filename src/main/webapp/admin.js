function loadData(){
var jsonData;
$.get('registerTeam', {
}, function(responseText){
    console.log(responseText);

    jsonData=JSON.parse(responseText)

    const app = document.createElement("div");
      app.setAttribute('id','root');
      document.body.appendChild(app);

  var data  = jsonData["Teams_data"];
   console.log(data);
  for(i=0;i<data.length;i++){
      console.log(data);
      var x = $.parseHTML(create_template(data[i]));
      app.appendChild(x[0]);
  }
});


}

function create_template(data){
    const template = `<div class="card">
        <div class="teamName">${data.TeamName}</div>
        <div class="userId">${data.UserId}</div>
        <div class="city">${data.city}</div>
        <label></label>
        <input></input>
      </div>`;

     return template;
};