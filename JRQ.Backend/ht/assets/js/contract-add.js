$("#loader").hide();
function checkRate(input) {
    var re = /^[0-9]+.?[0-9]*$/; //判断字符串是否为数字 //判断正整数 /^[1-9]+[0-9]*]*$/
    var nubmer = document.getElementById(input).value;

    if (!re.test(nubmer)) {
        alert(input + "请输入数字");
        document.getElementById(input).value = "";
        return false;
    }
    return true;
}
function adduser() {
    if (checkRate("price")) {
        $("#loader").show();
        // var fd = new FormData($("#upload-file-form")[0]);
        var url = getUrl();
        var fd2 = new FormData($("#upload-video-form")[0]);
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
        // --------------------------------------------
        var attachment = "";
        var e = $('#attachment')[0];
        var form = new FormData();
        if (!e.files[0]) {
            return;
        }
        form.append('attachment', e.files[0]);
        $.ajax({
            url: url + "/documentImage",
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
                $.ajax({
                    url: url + "/uploadDocument",
                    type: "POST",
                    data: form,
                    enctype: 'multipart/form-data',
                    processData: false,
                    contentType: false,
                    cache: false,
                    success: function (data) {
                        $("#loader").hide();
                        if (data != "") {
                            attachment = data;
                        }
                        $.ajax(
                            {
                                url: url + "/addContract",
                                data: {
                                    title: $("#title").val(),
                                    id: id,
                                    writerName: $("#writerName").val(),
                                    content: $("#content").val(),
                                    attachment: attachment,
                                    date: date,
                                    price: parseInt($("#price").val()),
                                    image: image,
                                    attachment: attachment
                                },
                                async: false,
                                success: function (data) {
                                    alert("添加成功");
                                    window.location.href = "contract.html";
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
                    }
                });
            },
            error: function (xhr) {
                $("#loader").hide();
            }
        });
    }
}