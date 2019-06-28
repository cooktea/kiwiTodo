function beSelected(item) {
    level1 = "background-color:blue";
    level2 = "background-color:red";
    level3 = "background-color:#41555d";
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

function overItem(item) {

}

function outItem(item) {

}

function test() {
    console.log("xxx");
}