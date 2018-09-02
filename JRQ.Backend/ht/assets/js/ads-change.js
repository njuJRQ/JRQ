var url=getUrl();
var storage = window.localStorage;
var id=storage["thisAd"];
$.ajax(
    {
        url: url+"/getAd",
        data: {
            id:id
        },
        async:false,
        success: function (data) {
            document.getElementById("id").innerText=data.ad.id;
            document.getElementById("link").value=data.ad.link;
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
                    url: url + "/addAd",
                    data: {
                        link:$("#link").val()
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