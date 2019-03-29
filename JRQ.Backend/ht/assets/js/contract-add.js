$("#loader").hide();
function checkRate(input) {
    var re = /^[0-9]+.?[0-9]*$/; //判断字符串是否为数字 //判断正整数 /^[1-9]+[0-9]*]*$/
    var nubmer = document.getElementById(input).value;

    if (!re.test(nubmer)) {
        alert(input+"请输入数字");
        document.getElementById(input).value = "";
        return false;
    }
    return true;
}
function adduser() {
    if(checkRate("price")) {
        $("#loader").show();
        var fd = new FormData($("#upload-file-form")[0]);
        var url = getUrl();
        var fd2 = new FormData($("#upload-video-form")[0]);
        var storage = window.localStorage;
        var id = storage["adminUsername"];
        var myDate = new Date();
        var date = myDate.toLocaleDateString();
        var image="";
        var attachment="";
        $.ajax({
            url: url + "/documentImage",
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
                $.ajax({
                    url: url + "/uploadDocument",
                    type: "POST",
                    data: fd2,
                    enctype: 'multipart/form-data',
                    processData: false,
                    contentType: false,
                    cache: false,
                    success: function (data) {
                        $("#loader").hide();
                        if(data!="") {
                            attachment = data;
                        }
                        $.ajax(
                            {
                                url: url + "/addContract",
                                data: {
                                    title: $("#title").val(),
                                    id:id,
                                    writerName:writerName,
                                    attachment: attachment,
                                    date: date,
                                    price: parseInt($("#price").val()),
                                    image:image,
                                    attachment:attachment
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