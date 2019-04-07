
var imageList=new Array();

var url=getUrl();
var storage = window.localStorage;
var id=storage["thisFeed"];
$.ajax(
    {
        url: url+"/getFeed",
        type:"POST",
        data: {
            id:id
        },
        async:false,
        success: function (data) {
            document.getElementById("agencyName").value=data.feed.agencyName;
            document.getElementById("phone").value=data.feed.phone;
            document.getElementById("projectInfo").value=data.feed.projectInfo;
            document.getElementById("linkMan").value=data.feed.linkMan;
            if(data.feed.projectRef =="ACTUAL_CONTROLLER"){
                data.feed.projectRef="实控人";
            }
            else if(data.feed.projectRef =="CORE_OF_SHAREHOLDERS"){
                data.feed.projectRef="核心股东";
            }
            else if(data.feed.projectRef =="EMPLOYEE"){
                data.feed.projectRef="雇员";
            }
            else if(data.feed.projectRef =="THIRD_PARTY"){
                data.feed.projectRef="一手第三方";
            }

            document.getElementById("projectRef").value=data.feed.projectRef;
            document.getElementById("id").innerText=data.feed.id;
            document.getElementById("writerOpenid").innerText=data.feed.writerOpenid;
            document.getElementById("content").innerText=data.feed.content;
            document.getElementById("date").innerText=data.feed.date;
            document.getElementById("likeNum").innerText=data.feed.likeNum;
            for(var i=0;i<data.feed.images.length;i++){
                imageList.push(data.feed.images[i]);
                $("#imageList").append("<img src='"+"../"+data.feed.images[i]+"' style=\"width: 10rem;height: 10rem;\">")
            }
        },
        error: function (xhr) {
            alert('动态页有问题噶！\n\n' + xhr.responseText);
        },
        traditional: true,
    }
)
function fileSelected() {
    var fd = new FormData($("#upload-file-form")[0]);
    var url = getUrl();
    $.ajax({
        url: url + "/uploadFeed",
        type: "POST",
        data: fd,
        enctype: 'multipart/form-data',
        processData: false,
        contentType: false,
        cache: false,
        async: false,
        success: function (data) {
            imageList.push(data);
            $("#imageList").append("<img src='"+"../"+imageList[imageList.length-1]+"' style=\"width: 10rem;height: 10rem;\">")
        },
        error: function (xhr) {
            alert("上传图片失败！")
            // Handle upload error
            // ...
        }
    });

}

function fileSelect() {
    document.getElementById("image").click();
}

function clearImage(){
    imageList.length=0;
    document.getElementById("imageList").innerText="";
}



function addUser(){
    var url = getUrl();

    var id=document.getElementById("id").innerText;
    if(imageList.length==0){
        imageList.push("");
    }
    $.ajax(
        {
            url: url + "/updateFeed",
            data: {
                id:id,
                linkMan: $("#linkMan").val(),
                phone: $("#phone").val(),
                agencyName: $("#agencyName").val(),
                projectRef:$("#projectRef option:selected").val(),
                projectInfo:$("#projectInfo").val(),
                images:imageList
            },
            async: false,
            success: function (data) {
                alert("修改成功");
                window.location.href = "feed.html";
            },
            error: function (xhr) {
                alert('动态页有问题噶！\n\n' + xhr.responseText);
            },
            traditional: true,
        }
    )

}