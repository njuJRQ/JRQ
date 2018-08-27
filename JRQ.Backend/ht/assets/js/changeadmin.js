var lastnumber;
$.get("assets/php/getthisquestion.php" ,async =false,function (data){
    var temp=data.split("\n");
    var temp1=temp[temp.length-1];
    var question=temp1.split(" ");
    document.getElementById("name").value=question[1];
    document.getElementById("password").value=question[2];
    lastnumber=question[0];
})
document.getElementById("ad").onclick=function() {
    $.post("assets/php/deleteadmin.php", {number:lastnumber} ,function (data){

    })
    var name = $("#name").val();
    var pass = $("#password").val();
    var myDate = new Date();
    var date = myDate.toLocaleDateString();
    var limits = "";
    if (document.getElementById("c1").checked) {
        limits = limits + "1" + "...";
    }
    if (document.getElementById("c2").checked) {
        limits = limits + "2" + "...";
    }
    if (document.getElementById("c3").checked) {
        limits = limits + "3" + "...";
    }
    if (document.getElementById("c4").checked) {
        limits = limits + "4" + "...";
    }
    if (document.getElementById("c5").checked) {
        limits = limits + "5" + "...";
    }
    if (document.getElementById("c6").checked) {
        limits = limits + "6" + "...";
    }
    if (document.getElementById("c7").checked) {
        limits = limits + "7" + "...";
    }
    if (document.getElementById("c8").checked) {
        limits = limits + "8" + "...";
    }
    if (document.getElementById("c9").checked) {
        limits = limits + "9" + "...";
    }
    if (document.getElementById("c10").checked) {
        limits = limits + "10" + "...";
    }
    if (document.getElementById("c11").checked) {
        limits = limits + "11" + "...";
    }
    if (document.getElementById("c12").checked) {
        limits = limits + "12" + "...";
    }
    if (document.getElementById("c13").checked) {
        limits = limits + "13" + "...";
    }
    if (document.getElementById("c14").checked) {
        limits = limits + "14";
    }
    $.post("assets/php/addadmin.php", {name: name, pass: pass, limits: limits, date: date}, function (data) {
        var result = data.substring(data.length - 1);
        if (result == "1") {
            alert("添加成功");
            window.location.href = "admin-list.html";
        }
    })
}