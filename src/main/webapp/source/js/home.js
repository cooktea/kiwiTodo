var sortTime = "up";
var sortLevel = "up";
var todos = undefined;

$(document).ready(function () {
    $("#sortLevel").click(function () {
        sortByLevel(sortLevel);
    });
    $("#sortTime").click(function () {
        sortByDate(sortTime);
    });
    $("#sortLevel").mouseover(function () {
        $(this).children("img").attr("src","source/image/arrow-up-down-blue.png")
    });
    $("#sortLevel").mouseout(function () {
        $(this).children("img").attr("src","source/image/arrow-up-down-white.png")
    });
    $("#sortTime").mouseover(function () {
        $(this).children("img").attr("src","source/image/arrow-up-down-blue.png")
    });
    $("#sortTime").mouseout(function () {
        $(this).children("img").attr("src","source/image/arrow-up-down-white.png")
    });

    $("[src='source/image/arrow-up-down-white.png']").mouseover(function () {
        console.log("xxx");
        this.src = "source/image/arrow-up-down-blue.png"
    });
    $("[src='source/image/arrow-up-down-white.png']").mouseout(function () {
        this.src = "source/image/arrow-up-down-white.png"
    });
})

$(document).ready(function () {
    $("#pushTodo").mouseover(function () {
        this.src = "source/image/commit-blue.png"
    })
    $("#pushTodo").mouseout(function () {
        this.src = "source/image/commit-white.png"
    });
    $("#pushTodo").click(function () {
        var level = $("#level").val();
        var content = $("#todoContent").val();
        if(content.length == 0){
            alert("请输入内容");
            return;
        }
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
            todos = data;
            console.log(data);
            console.log(todos);
            for(var i=0;i<data.length;i++){
                appendTodo(data[i],i+1);
            }
            $("[src='source/image/finish-white.png']").mouseover(function () {
                this.src = "source/image/finish-blue.png"
            });
            $("[src='source/image/finish-white.png']").mouseout(function () {
                this.src = "source/image/finish-white.png"
            });
            $("[src='source/image/delete-white.png']").mouseover(function () {
                this.src = "source/image/delete-blue.png"
            });
            $("[src='source/image/delete-white.png']").mouseout(function () {
                this.src = "source/image/delete-white.png"
            });
        }
    )
}

function appendTodo(todo,idx) {
    $("#todosContinaer").append("<div class='todoItem' id='todo-"+idx+"'></div>");
    $("#todo-"+idx).append("<div class='id-level-"+todo.level+"'>"+idx+"</div>");
    $("#todo-"+idx).append("<div class='content'>"+todo.content+"</div>");
    $("#todo-"+idx).append("<div class='time'>"+todo.time+"</div>");
    $("#todo-"+idx).append("<div class='command' id='command-"+idx+"'></div>");
    $("#command-"+idx).append("<img src=\"source/image/finish-white.png\" onclick='finish("+todo.id+","+idx+")'>");
    $("#command-"+idx).append("<img src=\"source/image/delete-white.png\" onclick='remove("+todo.id+","+idx+")'>");
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

function sortByDate(type) {
    for(var i=0;i<todos.length-1;i++){
        for(var j=i+1;j<todos.length;j++){
            if(new Date(todos[i].time.replace(/-/,"/")) > new Date(todos[j].time.replace(/-/,"/")) ){
                var temp = todos[i];
                todos[i] = todos[j];
                todos[j] = temp;
            }
        }
    }
    $("#todosContinaer").empty();
    if(type == "up"){
        for(var i=0;i<todos.length;i++){
            appendTodo(todos[i],i+1);
        }
        sortTime = "down";
    } else if (type == "down") {
        for(var i=todos.length-1;i>=0;i--){
            appendTodo(todos[i],i+1);
        }
        sortTime = "up";
    }
}

function sortByLevel(type) {
    for(var i=0;i<todos.length-1;i++){
        for(var j=i+1;j<todos.length;j++){
            if(todos[i].level > todos[j].level){
                var temp = todos[i];
                todos[i] = todos[j];
                todos[j] = temp;
            }
        }
    }
    $("#todosContinaer").empty();
    if(type == "up"){
        for(var i=0;i<todos.length;i++){
            appendTodo(todos[i],i+1);
        }
        sortLevel = "down";
    } else if (type == "down") {
        for(var i=todos.length-1;i>=0;i--){
            appendTodo(todos[i],i+1);
        }
        sortLevel = "up";
    }
}







