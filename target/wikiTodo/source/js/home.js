
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
                // console.log(data.length);
                if(data.length == 9){
                    alert("添加成功");
                    window.location.reload();
                } else {
                    alert("添加失败");
                }
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
            console.log(data);
            for(var i=0;i<data.length;i++){
                appendTodo(data[i],i+1);
            }
            $("[src='source/image/hook.png']").mouseover(function () {
                this.src = "source/image/hook-blue.png"
            });
            $("[src='source/image/hook.png']").mouseout(function () {
                this.src = "source/image/hook.png"
            });
            $("[src='source/image/fork.png']").mouseover(function () {
                this.src = "source/image/fork-blue.png"
            });
            $("[src='source/image/fork.png']").mouseout(function () {
                this.src = "source/image/fork.png"
            });
        }
    )
}

function appendTodo(todo,idx) {
    $(".mainContainer").append("<div class='todoItem' id='todo-"+idx+"'></div>");
    $("#todo-"+idx).append("<div class='id-level-"+todo.level+"'>"+idx+"</div>");
    $("#todo-"+idx).append("<div class='content'>"+todo.content+"</div>");
    $("#todo-"+idx).append("<div class='time'>"+todo.time+"</div>");
    $("#todo-"+idx).append("<div class='command' id='command-"+idx+"'></div>");
    $("#command-"+idx).append("<img src=\"source/image/hook.png\" onclick='finish("+todo.id+","+idx+")'>");
    $("#command-"+idx).append("<img src=\"source/image/fork.png\" onclick='remove("+todo.id+","+idx+")'>");
}

function finish(id,idx) {
    $.post(
        "http://localhost:8080/kiwiTodo_war_exploded/todo",
        {
            type:"finish",
            id:id
        },
        function (data,status) {
            if(data.length == 9){
                $("#todo-"+idx).fadeOut(1000,function () {
                    this.remove()
                });
            } else {
                alert("更改状态失败");
            }
        }
    )
}
function remove(id,idx){
    $.post(
        "http://localhost:8080/kiwiTodo_war_exploded/todo",
        {
            type:"remove",
            id:id
        },
        function (data,status) {
            if(data.length == 9){
                $("#todo-"+idx).fadeOut(1000,function () {
                    this.remove()
                });
            } else {
                alert("移除失败");
            }
        }
    )
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








