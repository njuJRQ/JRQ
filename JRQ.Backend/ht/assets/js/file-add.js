
function adduser() {
    var url=getUrl();
    var storage = window.localStorage;
    var id=storage["adminUsername"];
    var myDate = new Date();
    var date = myDate.toLocaleDateString();
    $.ajax(
        {
            url: url + "/addDocument",
            data: {
                title:$("#title").val(),
                content:$("#content").val(),
                writerName:id,
                date:date
            },
            async: false,
            success: function (data) {
                alert("添加成功");
                window.location.href="file.html";
            },
            error: function (xhr) {
                //alert('动态页有问题噶！\n\n' + xhr.responseText);
            },
            traditional: true,
        }
    )
}