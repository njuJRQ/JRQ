var lastnumber;
var date="";
function okdate(d){
    date=d;
}
$.get("assets/php/getthisvip.php" ,async =false,function (data){
    var temp=data.split("\n");
    var temp1=temp[temp.length-1];
    var question=temp1.split(" ");
    document.getElementById("number").innerText=question[0];
    document.getElementById("username").innerText=question[1];
    document.getElementById("xueli").value=question[2];
    document.getElementById("name").value=question[3];
    document.getElementById("minzu").value=question[4];
    document.getElementById("sex").value=question[5];
    document.getElementById("dateinfo").value=question[6];
    date=document.getElementById("dateinfo").value;
    lastnumber=question[0];
})
document.getElementById("changevip").onclick=function() {
    var name=$("#name").val();
    var obj = document.getElementById("sex"); //定位id
    var index = obj.selectedIndex; // 选中索引
    var sex = obj.options[index].text; // 选中文本
    var obj1 = document.getElementById("xueli"); //定位id
    var index1 = obj1.selectedIndex; // 选中索引
    var xueli = obj1.options[index1].text; // 选中文本
    var obj2 = document.getElementById("minzu"); //定位id
    var index2 = obj2.selectedIndex; // 选中索引
    var minzu = obj2.options[index2].text; // 选中文本
    $.post("assets/php/updatevip.php", { number:lastnumber,name:name,date:date,sex:sex,xueli:xueli,minzu:minzu} ,function (data){
            alert("修改成功");
            window.location.href="vip-list.html";

    })
}