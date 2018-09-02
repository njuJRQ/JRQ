document.getElementById("ad").onclick=function() {
    var name = $("#name").val();
    var cardLimit = $("#cardLimit").val();
    var price = $("#price").val();
    var url=getUrl();
    $.ajax(
        {
            url: url+"/addLevel",
            data: {
                name:name,
                cardLimit:cardLimit,
                price:price
            },
            success: function (data) {
                alert("添加成功");
                window.location.href = "level.html";
            },
            error: function (xhr) {
                alert('动态页有问题噶！\n\n' + xhr.responseText);
            },
            traditional: true,
        }
    )


}