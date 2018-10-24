var url=getUrl();
var storage = window.localStorage;
var id=storage["thisDocument"];
$.ajax(
    {
        url: url+"/getDocument",
        headers :{
            'Authorization': 'Bearer ' + getToken(),
            'content-type': 'application/x-www-form-urlencoded'
        },
        data: {
            id:id
        },
        async:false,
        success: function (data) {
            document.getElementById("id").innerText=data.document.id;
            document.getElementById("title").value=data.document.title;
            document.getElementById("content").value=data.document.content;
            document.getElementById("writerName").value=data.document.writerName;
            document.getElementById("date").value=data.document.date;
            document.getElementById("likeNum").value=data.document.likeNum;
        },
        error: function (xhr) {
            alert('动态页有问题噶！\n\n' + xhr.responseText);
        },
        traditional: true,
    }
)

function checkRate(input) {
    var re = /^[0-9]+.?[0-9]*$/; //判断字符串是否为数字 //判断正整数 /^[1-9]+[0-9]*]*$/
    var nubmer = document.getElementById(input).value;

    if (!re.test(nubmer)) {
        alert(input+"请输入数字");
        document.getElementById(input).value = "";
        return false;
    }
    return true;
}
function changeFile(){
    if(checkRate("likeNum")) {
        $.ajax(
            {
                url: url + "/updateDocument",
                headers :{
                    'Authorization': 'Bearer ' + getToken(),
                    'content-type': 'application/x-www-form-urlencoded'
                },
                data: {
                    id: id,
                    title: $("#title").val(),
                    content: $("#content").val(),
                    writerName: $("#writerName").val(),
                    date: $("#date").val(),
                    likeNum: $("#likeNum").val()
                },
                async: false,
                success: function (data) {
                    alert("修改成功");
                    window.location.href = "file.html";
                },
                error: function (xhr) {
                    alert('动态页有问题噶！\n\n' + xhr.responseText);
                },
                traditional: true,
            }
        )
    }
}
