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
            document.getElementById("title").innerText=data.document.title;
            document.getElementById("content").innerText=data.document.content;
            document.getElementById("writerName").innerText=data.document.writerName;
            document.getElementById("date").innerText=data.document.date;
            document.getElementById("likeNum").innerText=data.document.likeNum;
        },
        error: function (xhr) {
            alert('动态页有问题噶！\n\n' + xhr.responseText);
        },
        traditional: true,
    }
)
