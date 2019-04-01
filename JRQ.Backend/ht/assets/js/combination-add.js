$("#loader").hide();

function adduser() {
    $("#loader").show();
    var fd = new FormData($("#upload-file-form")[0]);
    console.log($("#image")+'==============')
    var url = getUrl();
    var storage = window.localStorage;
    var id = storage["adminUsername"];
    var myDate = new Date();
    var date = myDate.toLocaleDateString();
    var image="";
    $.ajax({
        url: url + "/courseGroup/uploadImage",
        type: "POST",
        data: fd,
        enctype: 'multipart/form-data',
        processData: false,
        contentType: false,
        cache: false,
        success: function (data) {
            
            if(data!="") {
                image = data;
            }
            
            $("#loader").hide();
            $.ajax(
                {
                    url: url + "/courseGroup/add",
                    data: {
                        title: $("#title").val(),
                        content: $("#content").val(),
                        image:image,
                        writerName:writerName,
                        id: id,
                        date: date
                    },
                    async: false,
                    success: function (data) {

                        alert("添加成功");
                        window.location.href = "combination.html";
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



}