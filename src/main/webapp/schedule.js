function LoadTodaysMatchTeams(){
    $.get('Schedule',{},function(responseText){
        console.log(responseText);
    });
};