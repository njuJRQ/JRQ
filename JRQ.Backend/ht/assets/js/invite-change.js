$("#loader").hide();
var url=getUrl();
var storage = window.localStorage;
var id=storage["thisJobCart"];
$.ajax(
    {
        url: url+"/jobCard/findById",
        data: {
            id:id
        },
        async:false,
        success: function (data) {
            document.getElementById("id").innerText=data.jobCardItem.id;
            document.getElementById("expectPosition").innerText=data.jobCardItem.expectPosition;
            document.getElementById("expectWage").innerText=data.jobCardItem.expectWage;
            document.getElementById("degree").innerText=data.jobCardItem.degree;
            document.getElementById("introduction").innerText=data.jobCardItem.introduction;
            if(data.jobCardItem.isFresh){
                data.jobCardItem.isFresh="是";
            }
            else{
                data.jobCardItem.isFresh="否";
            }

            document.getElementById("isFresh").innerText=data.jobCardItem.isFresh;
            document.getElementById("enterprise").innerText=data.jobCardItem.enterprise;
            document.getElementById("advantage").innerText=data.jobCardItem.advantage;
            document.getElementById("city").innerText=data.jobCardItem.city;
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
function changeFile(){
        $("#loader").show();
        var fd = new FormData($("#upload-file-form")[0]);

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
                $("#loader").hide();
                $.ajax(
                    {
                        url: url + "/updateDocument",
                        data: {
                            id: id,
                            title: $("#title").val(),
                            content: $("#content").val(),
                            attachment:attachment,
                            writerName: $("#writerName").val(),
                            date: $("#date").val(),
                            likeNum: $("#likeNum").val()
                        },
                        async: false,
                        success: function (data) {
                            alert("修改成功");
                            window.location.href = "file.html";
                        },
                        error: function (xhr) {
                            alert('动态页有问题噶！\n\n' + xhr.responseText);
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
