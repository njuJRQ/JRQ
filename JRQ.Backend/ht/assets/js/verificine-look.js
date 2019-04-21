var url=getUrl();
var storage = window.localStorage;
var id=storage["thisCourse"];
var video;
$.ajax(
    {
        url: url+"/getCourse",
        type: "POST",
        data: {
            id:id
        },
        async:false,
        success: function (data) {
            document.getElementById("id").innerText=data.course.id;
            document.getElementById("title").innerText=data.course.title;
            document.getElementById("details").innerText=data.course.detail;
            document.getElementById("image").src="../"+data.course.image;
            document.getElementById("writerName").innerText=data.course.writerName;
            document.getElementById("date").innerText=data.course.date;
            document.getElementById("likeNum").innerText=data.course.likeNum;
            document.getElementById("price").innerText=data.course.price;
            text=" <div class=\"am-u-sm-4 am-u-md-2 am-text-right\">课程视频</div>"

            for(i in data.course.videos){
                text=text+"<div class=\"am-u-sm-8 am-u-md-10\"><button type=\"button\" onclick=\"look('"+data.course.videos[i]+"')\">点击查看课程视频</button></div>"
            }
            document.getElementById("videos").innerHTML=text
            video=data.course.video;

        },
        error: function (xhr) {
            alert('动态页有问题噶！\n\n' + xhr.responseText);
        },
        traditional: true,
    }
)

function look(video1){
    storage["thisVideo"]=video1;
    window.location.href="tv.html";

}