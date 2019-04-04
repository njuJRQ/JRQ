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
        var url = getUrl();
        var storage = window.localStorage;
        var id = storage["adminUsername"];
        var myDate = new Date();
        var date = myDate.toLocaleDateString();
        // 上传图片
        var image="";
        var el = $('#image')[0];
        var formData = new FormData();
        if (!el.files[0]) {
            return;
        }
        formData.append('image', el.files[0]);
        // 上传视频
        var video="";
        var els = $('#video')[0];
        var form = new FormData();
        if (!els.files[0]) {
            return;
        }
        form.append('video', els.files[0]);
        $.ajax({
            url: url + "/courseImage",
            type: "POST",
            data: formData,
            enctype: 'multipart/form-data',
            processData: false,
            contentType: false,
            cache: false,
            success: function (data) {
                if(data!="") {
                    image = data;
                }
                $.ajax({
                    url: url + "/courseVideo",
                    type: "POST",
                    data: form,
                    enctype: 'multipart/form-data',
                    processData: false,
                    contentType: false,
                    cache: false,
                    success: function (data) {
                        $("#loader").hide();
                        if(data!="") {
                            video = data;
                        }
                        $.ajax(
                            {
                                url: url + "/addCourse",
                                data: {
                                    title: $("#title").val(),
                                    writerName: id,
                                    date: date,
                                    price: parseInt($("#price").val()),
                                    image:image,
                                    video:video,
                                    isTextualResearchCourse:false
                                },
                                async: false,
                                success: function (data) {
                                    alert("添加成功");
                                    window.location.href = "course.html";
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