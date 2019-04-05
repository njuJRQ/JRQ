$("#loader").hide();

function adduser() {
    $("#loader").show();
     var fd = new FormData($("#upload-file-form")[0]);
    var fd1 = new FormData($("#upload-file-form1")[0]);
    var url = getUrl();
    var storage = window.localStorage;
    var id = storage["adminUsername"];
    var myDate = new Date();
    var date = myDate.toLocaleDateString();
    var attachment="";
    var image="";



    $.ajax({
        url: url + "/uploadDocument",
        type: "POST",
        data: fd,
        enctype: 'multipart/form-data',
        processData: false,
        contentType: false,
        cache: false,
        success: function (data) {
            if(data!="") {
                attachment = data;
            }

            $.ajax({
                url: url + "/documentImage",
                type: "POST",
                data: fd1,
                enctype: 'multipart/form-data',
                processData: false,
                contentType: false,
                cache: false,
                success: function (d) {
                    if(d!="") {
                        image = d;
                    }
                    $("#loader").hide();
                    $.ajax(
                        {
                            url: url + "/addDocument",
                            data: {
                                title: $("#title").val(),
                                content: $("#content").val(),
                                attachment:attachment,
                                image:image,
                                writerName: id,
                                price: $("#price").val()
                            },
                            async: false,
                            success: function (data) {

                                alert("添加成功");
                                window.location.href = "file.html";
                            },
                            error: function (xhr) {
                                //alert('动态页有问题噶！\n\n' + xhr.responseText);
                            },
                            traditional: true,
                        }
                    )

                },
                error: function (xhr) {
                    $("#loader").hide();

                    //alert(xhr.responseText);
                    // Handle upload error
                    // ...
                }
            });




        },
        error: function (xhr) {
            $("#loader").hide();
            //alert(xhr.responseText);
            // Handle upload error
            // ...
        }
    });



}