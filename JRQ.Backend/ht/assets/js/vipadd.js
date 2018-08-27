var date="";
function okdate(d){
    date=d;
}
document.getElementById("addsure").onclick=function() {
    var username=$("#username").val();
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
    var pass=$("#pass").val();
    var passagain=$("#passagain").val();
    var myDate = new Date();
    var redate=myDate.toLocaleDateString();
    var number =Date.parse(new Date()).toString();
    $.post("assets/php/addvip.php", { number:number,username: username, name:name,date:date,sex:sex,xueli:xueli,minzu:minzu,pass:pass,redate:redate } ,function (data){
            alert("添加成功");
            window.location.href="vip-list.html";
    })



}