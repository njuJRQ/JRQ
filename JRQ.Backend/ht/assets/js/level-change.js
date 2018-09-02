
var storage=window.localStorage;
var thisname=storage["thisLevelName"];
var thisCardLimit=storage["thisLevelCardLimit"];
var thisPrice=storage["thisLevelPrice"];

var url=getUrl();
document.getElementById("name").value=thisname;
document.getElementById("cardLimit").value=thisCardLimit;
document.getElementById("price").value=thisPrice;

document.getElementById("ad").onclick=function() {
    var name = $("#name").val();
    var cardLimit = $("#cardLimit").val();
    var price = $("#price").val();
    var url=getUrl();
    $.ajax(
        {
            url: url+"/updateLevel",
            data: {
                name:name,
                cardLimit:cardLimit,
                price:price
            },
            success: function (data) {
                alert("修改成功");
                window.location.href = "level.html";
            },
            error: function (xhr) {
                alert('动态页有问题噶！\n\n' + xhr.responseText);
            },
            traditional: true,
        }
    )


}