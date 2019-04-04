$("#loader").hide();

function adduser() {
    $("#loader").show();
    // var fd = new FormData($("#upload-file-form")[0]);
    var url = getUrl();
    var storage = window.localStorage;
    var id = storage["adminUsername"];
    var myDate = new Date();
    var date = myDate.toLocaleDateString();
    var attachment="";
    var el = $('#attachment')[0];
    var formData = new FormData();
    if (!el.files[0]) {
        return;
    }
    formData.append('attachment', el.files[0]);
    $.ajax({
        url: url + "/uploadDocument",
        type: "POST",
        data: formData,
        enctype: 'multipart/form-data',
        processData: false,
        contentType: false,
        cache: false,
        success: function (data) {
            if(data!="") {
                attachment = data;
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
                        date: date
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



}