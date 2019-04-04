$("#loader").hide();

function adduser() {
    $("#loader").show();
    // var fd = new FormData($("#upload-file-form")[0]);
    var url = getUrl();
    var storage = window.localStorage;
    var id = storage["adminUsername"];
    var myDate = new Date();
    var date = myDate.toLocaleDateString();
    var image = "";
    var el = $('#image')[0];
    var formData = new FormData();
    if (!el.files[0]) {
        return;
    }
    formData.append('image', el.files[0]);
    $.ajax({
        url: url + "/courseGroup/uploadImage",
        type: "POST",
        data: formData,
        enctype: 'multipart/form-data',
        processData: false,
        contentType: false,
        cache: false,
        success: function (data) {
            if (data != "") {
                image = data;
            }

            $("#loader").hide();
            var data1 = JSON.stringify({
                "title": $("#title").val(),
                "content": $("#content").val(),
                "image": image,
                // "writerName": writerName,
                "id": id,
                "date": date
            })
            $.ajax(
                {
                    url: url + "/courseGroup/add",
                    type: "POST",
                    dataType: "json",
                    contentType: "application/json",
                    data: data1,
                    async: false,
                    success: function (data) {
                        console.log(data + '=================')
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