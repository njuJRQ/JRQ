var url=getUrl();
var storage = window.localStorage;
var id=storage["thisAd"];
$.ajax(
    {
        url: url+"/business/findImageById",
        data: {
            id:id
        },
        async:false,
        success: function (data) {
            document.getElementById("id").innerText=data.businessImageItem.id;
            document.getElementById("link").value=data.businessImageItem.link;
            if(data.businessImageItem.position=="index"){
                document.getElementById("showPlace").value="首页";
            }
            else if(data.businessImageItem.showPlace=="service"){
                document.getElementById("showPlace").value="业务";
            }
            else if(data.businessImageItem.showPlace=="jump"){
                document.getElementById("showPlace").value="弹窗";
            }
        },
        error: function (xhr) {
            alert('动态页有问题噶！\n\n' + xhr.responseText);
        },
        traditional: true,
    }
)
function adduser() {
    var url=getUrl();
    $.ajax(
        {
            url: url+"/deleteAd",
            data: {
                id:id
            },
            async:false,
            success: function (data) {
            },
            error: function (xhr) {
                alert('动态页有问题噶！\n\n' + xhr.responseText);
            },
            traditional: true,
        }
    )
    var fd = new FormData($("#upload-file-form")[0]);
    var obj1 = document.getElementById("showPlace"); //定位id
    var index1 = obj1.selectedIndex; // 选中索引
    var showPlace = obj1.options[index1].text; // 选中文本
    if(showPlace=="首页"){
        showPlace="index";
    }
    else if(showPlace=="业务"){
        showPlace="service";
    }
    else if(showPlace=="弹窗"){
        showPlace="jump";
    }
    $.ajax({
        url: url + "/uploadAd",
        type: "POST",
        data: fd,
        enctype: 'multipart/form-data',
        processData: false,
        contentType: false,
        cache: false,
        async: false,
        success: function () {
            $.ajax(
                {
                    url: url + "/business/updateImage",
                    data: {
                        link:$("#link").val(),
                        showPlace:showPlace
                    },
                    async: false,
                    success: function (data) {
                        alert("修改成功");
                        window.location.href="ads.html";
                    },
                    error: function (xhr) {
                        //alert('动态页有问题噶！\n\n' + xhr.responseText);
                    },
                    traditional: true,
                }
            )
            // Handle upload success
            // ...
        },
        error: function (xhr) {
            //alert(xhr.responseText);
            // Handle upload error
            // ...
        }
    });
}