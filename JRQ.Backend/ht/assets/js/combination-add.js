$("#loader").hide();
var goodsLength=0;
var goodsList=new Array();
function addCourse() {
    var storage = window.localStorage;
    var restaurantId=storage["restaurantId"];
    goodsLength=goodsLength+1;
    $("#goods").append("<select id='goodsIdList"+goodsLength+"'</select>");
    var list=new Array();
    var url=getUrl();
    $.ajax(
        {
            url: url+"/getCourseList",
            data: {
            },
            async:false,
            success: function (data) {
                goodsList=new Array();
                for(var i=0;i<data.courseList.length;i++){
                    list.push(data.courseList[i]);
                    goodsList.push(data.courseList[i]);
                }
                for(var i=0;i<list.length;i++){
                    $("#goodsIdList"+goodsLength).append("<option value=''>"+list[i].title+"</option>");
                }
            },
            error: function (xhr) {
                alert('动态页有问题噶！\n\n' + xhr.responseText);
            },
            traditional: true,
        }
    )
}

function adduser() {
    // var fd = new FormData($("#upload-file-form")[0]);
    var url = getUrl();
    var storage = window.localStorage;
    var writerName = storage["adminUsername"];

    var goodsIdList=new Array();

    for(var i=1;i<=goodsLength;i++){
        var obj2 = document.getElementById("goodsIdList"+i); //定位id
        var index2 = obj2.selectedIndex; // 选中索引
        goodsIdList.push(goodsList[index2].id);
    }
    var fd = new FormData($("#upload-file-form")[0]);
    $.ajax({
        url: url + "/upload",
        type: "POST",
        data: fd,
        enctype: 'multipart/form-data',
        processData: false,
        contentType: false,
        cache: false,
        async: false,
        success: function (data) {
            var data1 = JSON.stringify({
                "id": "",
                "title": $("#title").val(),
                "writerName": writerName,
                "image": data,
                "price": $("#price").val(),
                "courses":goodsIdList
            })
            $.ajax(
                {
                    url: url + "/courseGroup/add",
                    type: "POST",
                    dataType: "json",
                    contentType: "application/json",
                    data: data1,
                    async: false,
                    success: function (data) {
                        alert("添加成功");
                        window.location.href = "combination.html";
                    },
                    error: function (xhr) {
                        //alert('动态页有问题噶！\n\n' + xhr.responseText);
                    },
                    traditional: true
                }
            )
        },
        error: function (xhr) {
            //alert(xhr.responseText);
            // Handle upload error
            // ...
        }
    });


}