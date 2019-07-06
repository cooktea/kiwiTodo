
$(document).ready(function () {
    $("#pushTodo").mouseover(function () {
        this.src = "source/image/cross-red.png"
    })
    $("#pushTodo").mouseout(function () {
        this.src = "source/image/cross-blue.png"
    });
    $("#pushTodo").click(function () {
        var level = $("#level").val();
        var content = $("#todoContent").val();
        console.log("level:"+level);
        console.log("content:"+content);
        $.post(
            //todo 修改url
            "http://localhost:8080/kiwiTodo_war_exploded/todo",
            {
                level:level,
                content:content,
                type:"pushTodo"
            },
            function (data,status) {
                console.log(status);
                console.log(data);
            }
        )
    })
});
$(document).ready(function () {
    var user = $.cookie("user");
    console.log(user);
    console.log(user.length);
    if(user.length == 11){
        $("#user").text(user);
        $("#user").attr("href","home.html");
    } else {

    }

});

$(document).ready(function () {
    getTodos();
});

function getTodos() {
    $.getJSON(
        //todo 修改url
        "http://localhost:8080/kiwiTodo_war_exploded/todo",
        {
            type:"getTodo"
        },
        function (data,status) {
            console.log(status);
            console.log(data.length);
            for(var i=0;i<data.length;i++){
                appendTodo(data[i]);
                console.log("\n");
            }
            $(".command").append("<img src=\"source/image/hook.png\">");
            $(".command").append("<img src=\"source/image/fork.png\">");
        }
    )
}

function appendTodo(todo) {
    console.log(todo);
    $(".mainContainer").append("<div class='todoItem' id='todo-"+todo.id+"'></div>");
    $("#todo-"+todo.id).append("<div class='id-level-"+todo.level+"'>"+todo.id+"</div>");
    $("#todo-"+todo.id).append("<div class='content'>"+todo.content+"</div>");
    $("#todo-"+todo.id).append("<div class='time'>"+todo.time+"</div>");
    $("#todo-"+todo.id).append("<div class='command'></div>");
}

function beSelected(item) {
    level1 = "background-color:#BF242A";
    level2 = "background-color:#F5B1AA";
    level3 = "background-color:#A0D8EF";
    if(item.value == "1"){
        item.setAttribute("style",level1);
    }else if(item.value == "2"){
        item.setAttribute("style",level2);
    }else {
        item.setAttribute("style",level3);
    }
}

function avaiNumber(input) {
    console.log("xxx");
    var number = input.value.length;
    document.getElementById("charNumber").innerText = 70-number;
}








