$(document).ready(function () {
    var user = $.cookie("user");
    console.log(user);
    console.log(user.length);
    if(user){
        if(user.length == 11){
            $("#logOrReg").hide();
            $("#user").text(user);
            $("#user").show();
        } else {

        }
    }
});
$(document).ready(function () {
    $("#menu").mouseover(function () {
        $(this).children("ul").show();
    });
    $("#menu").mouseout(function () {
        $(this).children("ul").hide();
    })
    $("#settings").mouseover(function () {
        $(this).children("ul").show();
    });
    $("#settings").mouseout(function () {
        $(this).children("ul").hide();
    })
});