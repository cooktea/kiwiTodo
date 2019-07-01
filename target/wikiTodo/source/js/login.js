$(document).ready(function () {
    $("#login").click(function () {
        console.log("xxx");
        $("#loginPanel").css("display","flex");
        $("#registerPanel").css("display","none");
    });
    $("#register").click(function () {
        console.log("xxx");
        $("#loginPanel").css("display","none");
        $("#registerPanel").css("display","flex");
    });
});

$(document).ready(function () {
    $("#phoneNumber").on("input",function () {
        var phoneNumber = $("#phoneNumber").val();
       if(phoneNumber.length != 11){
            $("#infomation").text("手机号码不合法");
           console.log("xx")
       }else {
           $("#infomation").text("");
       }
        isOk();
    });
    $("#password").on("input",function () {
        var pwd = $("#password").val();
        if(pwd.length <8 ){
            $("#infomation").text("密码长度过短");
        } else {
            $("#infomation").text("");
        }
        isOk();
    });
    $("#confirm").on("input",function () {
        var p1 = $("#confirm").val();
        var p = $("#password").val();
        if(p1 != p){
            $("#infomation").text("两次密码不一致")
        } else {
            $("#infomation").text("")
        }
        isOk();
    });
    function isOk() {
        var flag1 = $("#phoneNumber").val().length == 11 ? true:false;
        var flag2 = ($("#password").val() == $("#confirm").val() && $("#password").val().length > 0) ? true:false;
        if (flag1){
            var havaUser = false;
            // $.post(
            //     "http://localhost:8080/kiwiTodo_war_exploded/user",
            //     {
            //         type:"getUser",
            //         phoneNumber:$("#phoneNumber").val()
            //     },
            //     function () {
            //         //todo 验证是否有用户
            //     }
            // );
            if(havaUser){
                $("#infomation").text("用户已存在");
                return;
            }
        }
        if(flag1 && flag2){
            $("#infomation").text("可以注册！");
            $("#doRegister").removeAttr("disabled");
        } else {
            $("#doRegister").removeAttr("disabled");
            $("#doRegister").attr("disabled","disabled");
        }
    }
});