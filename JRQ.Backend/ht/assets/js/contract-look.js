var url=getUrl();
var storage = window.localStorage;
var id=storage["thisDocument"];
var path;
$.ajax(
    {
        url: url+"/getDocument",
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
            document.getElementById("price").innerText=data.document.price;
            document.getElementById("detail").innerText=data.document.detail;
            paths=[]
            for(i in data.document.attachments ){
                paths.push("../"+data.document.attachments[i]);
            }
            //path="../"+data.document.attachment;
            document.getElementById("image").src="../"+data.document.image;
            text=" <div class=\"am-u-sm-4 am-u-md-2 am-text-right\">附件路径</div>"

            for( i in paths){
                text=text+"<div class=\"am-u-sm-8 am-u-md-10\"><button type=\"button\" onclick=\"attatchment('"+paths[i]+"')\">点击查看项目附件</button></div>"
            }
            document.getElementById("files").innerHTML=text;
        },
        error: function (xhr) {
            alert('动态页有问题噶！\n\n' + xhr.responseText);
        },
        traditional: true,
    }
)

function attatchment(path_tem){
    var $eleForm = $("<form method='get'></form>");

    $eleForm.attr("target",'');
    $eleForm.attr("action",path_tem);
    $(document.body).append($eleForm);
    $eleForm.submit();   //提交表单，实现下载
}
