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
            document.getElementById("agencyName").innerText=data.feed.agencyName;
            document.getElementById("phone").innerText=data.feed.phone;
            document.getElementById("projectInfo").innerText=data.feed.projectInfo;
            document.getElementById("linkMan").innerText=data.feed.linkMan;
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

            document.getElementById("projectRef").innerText=data.feed.projectRef;
            document.getElementById("id").innerText=data.feed.id;
            document.getElementById("writerOpenid").innerText=data.feed.writerOpenid;
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