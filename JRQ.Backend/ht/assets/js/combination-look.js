var url=getUrl();
var storage = window.localStorage;
var id=storage["thisCourse"];
var path;
$.ajax(
    {
        url: url+"/courseGroup/findById",
        type:'POST',
        data: {
            id:id
        },
        async:false,     
        success: function (data) {
            document.getElementById("id").innerText=data.courseGroupItem.id;
            document.getElementById("title").innerText=data.courseGroupItem.title;
            document.getElementById("image").src="../"+data.courseGroupItem.image;
            document.getElementById("price").innerText=data.courseGroupItem.price;
            document.getElementById("courseList").innerText=data.courseGroupItem.courseList;
        },
        error: function (xhr) {
            alert('动态页有问题噶！\n\n' + xhr.responseText);
        },
        traditional: true,
    }
)

function attatchment(){
    var $eleForm = $("<form method='get'></form>");

    $eleForm.attr("target",'');
    $eleForm.attr("action",path);
    $(document.body).append($eleForm);
    $eleForm.submit();   //提交表单，实现下载
}
