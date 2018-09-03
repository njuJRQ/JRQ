
function adduser() {
    var fd = new FormData($("#upload-file-form")[0]);
    var url = getUrl();
    var fd2=new FormData($("#upload-video-form")[0]);
    var storage = window.localStorage;
    var id=storage["adminUsername"];
    var myDate = new Date();
    var date = myDate.toLocaleDateString();
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
                            url: url + "/addCourse",
                            data: {
                                title:$("#title").val(),
                                writerName:id,
                                date:date,
                                price:parseInt($("#price").val())
                            },
                            async: false,
                            success: function (data) {
                                alert("添加成功");
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