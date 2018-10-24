
var url=getUrl();
var storage = window.localStorage;
var id=storage["thisCourse"];
$.ajax(
    {
        url: url+"/getCourse",
        headers :{
            'Authorization': 'Bearer ' + getToken(),
            'content-type': 'application/x-www-form-urlencoded'
        },
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
    if(checkRate("likeNum")&&checkRate("price")) {
        var fd = new FormData($("#upload-file-form")[0]);
        var url = getUrl();
        var fd2 = new FormData($("#upload-video-form")[0]);
        $.ajax({
            url: url + "/courseImage",
            headers :{
                'Authorization': 'Bearer ' + getToken(),
                'content-type': 'application/x-www-form-urlencoded'
            },
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
                    headers :{
                        'Authorization': 'Bearer ' + getToken(),
                        'content-type': 'application/x-www-form-urlencoded'
                    },
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
                                headers :{
                                    'Authorization': 'Bearer ' + getToken(),
                                    'content-type': 'application/x-www-form-urlencoded'
                                },
                                data: {
                                    id: id,
                                    title: $("#title").val(),
                                    writerName: $("#writerName").val(),
                                    date: $("#date").val(),
                                    likeNum: $("#likeNum").val(),
                                    price: parseInt($("#price").val())
                                },
                                async: false,
                                success: function (data) {
                                    alert("修改成功");
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

                    }
                });

            },
            error: function (xhr) {

            }
        });
    }
}