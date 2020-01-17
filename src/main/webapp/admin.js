function loadData(){
$.get('registerTeam', {
}, function(responseText){
    console.log(responseText);
});

}