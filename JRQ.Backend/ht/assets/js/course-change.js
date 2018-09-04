
var url=getUrl();
var storage = window.localStorage;
var id=storage["thisCourse"];
$.ajax(
    {
        url: url+"/getCourse",
        data: {
            id:id
        },
        async:false,
        success: function (data) {
            document.getElementById("id").innerText=data.course.id;
            document.getElementById("title").value=data.course.title;
            document.getElementById("writerName").value=data.course.writerName;
            document.getElementById("date").value=data.course.date;
            document.getElementById("likeNum").value=data.course.likeNum;
            document.getElementById("price").value=data.course.price;
        },
        error: function (xhr) {
            alert('动态页有问题噶！\n\n' + xhr.responseText);
        },
        traditional: true,
    }
)
function adduser() {
    var fd = new FormData($("#upload-file-form")[0]);
    var url = getUrl();
    var fd2=new FormData($("#upload-video-form")[0]);
    $.ajax({
        url: url + "/courseImage",
        type: "POST",
        data: fd,
        enctype: 'multipart/form-data',
        processData: false,
        contentType: false,
        cache: false,
        async: false,
        success: function () {
            $.ajax({
                url: url + "/courseVideo",
                type: "POST",
                data: fd2,
                enctype: 'multipart/form-data',
                processData: false,
                contentType: false,
                cache: false,
                async: false,
                success: function () {

                    $.ajax(
                        {
                            url: url + "/updateCourse",
                            data: {
                                id:id,
                                title:$("#title").val(),
                                writerName:$("#writerName").val(),
                                date:$("#date").val(),
                                likeNum:$("#likeNum").val(),
                                price:parseInt($("#price").val())
                            },
                            async: false,
                            success: function (data) {
                                alert("修改成功");
                                window.location.href="course.html";
                            },
                            error: function (xhr) {
                                //alert('动态页有问题噶！\n\n' + xhr.responseText);
                            },
                            traditional: true,
                        }
                    )

                },
                error: function (xhr) {

                }
            });

        },
        error: function (xhr) {

        }
    });
}