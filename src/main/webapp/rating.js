function loadPlayers(){
var jsonData;
$.get('Rating', {}, function(responseText){
    console.log(responseText);

     jsonData=JSON.parse(responseText)
     var data  = jsonData["Players_Details"];
     console.log(data);

     const app = document.createElement("div");
     app.setAttribute('id','root');
     document.body.appendChild(app);


 for(i=0;i<data.length;i++){
       var x = $.parseHTML(create_template(data[i]));
       app.appendChild(x[0]);
   }
});

}

function create_template(data){
    const template = `<div class="card">
        <img class="profileImg" src="profileImg.jpg">
        <div class="details" style="float:right;text-align:center;margin-top:-8px;margin-right:150px;">
        <br><div>Id: ${data.UserId}</div>
        <br><div>Name: ${data.Name}</div>
        <br><div>Skills: ${data.Skills}</div>
        </div>

        <button id="sendData" onclick="cli('${data.UserId}')">

         <div class="stars" id="${data.UserId}">
             <form class="rating" method="POST" action="Rating">
               <label>
                 <input type="radio" name="stars" value="1" />
                 <span class="icon">★</span>
               </label>
               <label>
                 <input type="radio" name="stars" value="2" />
                 <span class="icon">★</span>
                 <span class="icon">★</span>
               </label>
               <label>
                 <input type="radio" name="stars" value="3" />
                 <span class="icon">★</span>
                 <span class="icon">★</span>
                 <span class="icon">★</span>
               </label>
               <label>
                 <input type="radio" name="stars" value="4" />
                 <span class="icon">★</span>
                 <span class="icon">★</span>
                 <span class="icon">★</span>
                 <span class="icon">★</span>
               </label>
               <label>
                 <input type="radio" name="stars" value="5" />
                 <span class="icon">★</span>
                 <span class="icon">★</span>
                 <span class="icon">★</span>
                 <span class="icon">★</span>
                 <span class="icon">★</span>
               </label>
             </form>
         </div>
         <div>
         <input type="submit" value="Update Rating" class="registerButton"/>
         </div>
      </div>`;

     return template;
};

function cli(userId){
    console(userId)
}