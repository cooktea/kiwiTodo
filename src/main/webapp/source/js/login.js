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
    $("#password").change(function () {
        var This = this.value;
        var That = $("#confirm").val();
        if(That == ""){
            return
        }
        console.log("xxx");
        console.log(That);
        console.log(This);
        if(That != This){
            alert("两次输入的密码不一致");
            $("#password").val("");
            $("#confirm").val("");
        }
    });
    $("#confirm").change(function () {
        var This = this.value;
        var That = $("#password").val();
        if(That == ""){
            return
        }
        console.log("xxx");
        console.log(That);
        console.log(This);
        if(That != This){
            alert("两次输入的密码不一致");
            $("#password").val("");
            $("#confirm").val("");
        }
    })
    $("#doRegister").click(function () {
        var number = $("#phoneNumber").val();
        var password = $("#password").val();
        if (number.length == 0){
            alert("请输入手机号码");
            return;
        }
        if (number.length != 11){
            alert("手机号码不合法");
            return;
        }
        if(password.length == 0){
            alert("请输入密码");
            return;
        }
        if(password.length < 8){
            alert("密码太短");
            $("#password").val("");
            $("#confirm").val("");
            return;
        }
        console.log("number:"+number);
        console.log("password:"+password);
    })
    
    
})