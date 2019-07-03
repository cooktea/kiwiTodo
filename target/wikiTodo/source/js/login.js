//注册和登陆切换
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
//注册界面的输入合法性验证
$(document).ready(function () {
    $("#phoneNumber").on("input",function () {
        isPhoneNumberok();
    });
    $("#password").on("input",function () {
        isPwdOK();
    });
    $("#confirm").on("input",function () {
        isPwdOK();
    });
    $("#resetRegister").click(function () {
        $("#password").removeAttr("disabled");
        $("#confirm").removeAttr("disabled");
        $("#password").attr("disabled","disabled");
        $("#confirm").attr("disabled","disabled");
        $("#doRegister").removeAttr("disabled");
        $("#doRegister").attr("disabled","disabled");
        $("#infomation").text("");
    })
});
//注册时输入的电话号码是否合法
function isPhoneNumberok() {
    $("#password").val("");
    $("#confirm").val("");
    if($("#phoneNumber").val().length != 11?true:false){
        $("#infomation").text("手机号码不合法");
        return true;
    }
    $.post(
        "http://localhost:8080/kiwiTodo_war_exploded/user",
        {
            type:"getUser",
            phoneNumber:$("#phoneNumber").val()
        },
        function (data,status) {
            //todo 验证是否有用户
            if(status == "success"){
                if(data.length == 10){
                    console.log("用户已存在");
                    $("#password").removeAttr("disabled");
                    $("#confirm").removeAttr("disabled");
                    $("#password").attr("disabled","disabled");
                    $("#confirm").attr("disabled","disabled");
                    $("#doRegister").removeAttr("disabled");
                    $("#doRegister").attr("disabled","disabled");
                    $("#infomation").text("用户已存在");
                }else {
                    console.log("用户不存在");
                    $("#infomation").text("请输入密码");
                    $("#password").removeAttr("disabled");
                    $("#confirm").removeAttr("disabled");
                }
            } else {
                $("#infomation").text("网路故障,请稍后重试");
                $("#doRegister").removeAttr("disabled");
                $("#doRegister").attr("disabled","disabled");
            }
        }
    );
}
//注册时输入的密码是否合法
function isPwdOK() {
    var pwd = $("#password").val();
    var c_pwd = $("#confirm").val();
    if(pwd.length < 8){
        $("#infomation").text("密码长度过短");
        $("#doRegister").removeAttr("disabled");
        $("#doRegister").attr("disabled","disabled");
    } else {
        $("#infomation").text("请确认密码");
        if(pwd == c_pwd){
            $("#infomation").text("可以注册！");
            $("#doRegister").removeAttr("disabled");
        } else {
            $("#infomation").text("两次密码不一致");
            $("#doRegister").removeAttr("disabled");
            $("#doRegister").attr("disabled","disabled");
        }
    }
}
//ajax方式注册
function register() {
    var number = $("#phoneNumber").val();
    var pwd = $("#password").val();
    $.post(
        "http://localhost:8080/kiwiTodo_war_exploded/user",
        {
            type:"register",
            phoneNumber:number,
            password:pwd
        },
        function (data,status) {
            console.log("data-length: "+data.length);
            console.log("data-length: "+data.toString());
            console.log("status: "+status);
            if(status == "success"){
                if(data.length == 9){
                    $("#infomation").text("注册成功,即将跳转至登陆页面");
                    setTimeout(function () {
                        $("#loginPanel").css("display","flex");
                        $("#registerPanel").css("display","none");
                    },3000)
                } else {
                    $("#infomation").text("注册失败");
                }
            } else {
                $("#infomation").text("注册失败");
            }
        }
    )
}



