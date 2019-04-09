var url=getUrl();
var storage = window.localStorage;
var id=storage["thisCourse"];
$.ajax(
    {
        url: url+"/courseGroup/findById",
        data: {
            id:id
        },
        async:false,     
        success: function (data) {
            document.getElementById("id").innerText=data.courseGroupItem.id;
            document.getElementById("title").innerText=data.courseGroupItem.title;
            document.getElementById("image").src="../"+data.courseGroupItem.image;
            document.getElementById("price").innerText=data.courseGroupItem.price;
            var courseList="";
            for(var i=0;i<data.courseGroupItem.courseList.length;i++){
                courseList=courseList+" "+data.courseGroupItem.courseList[i].title;
            }
            document.getElementById("courseList").innerText=courseList;
        },
        error: function (xhr) {
            alert('动态页有问题噶！\n\n' + xhr.responseText);
        },
        traditional: true,
    }
)
