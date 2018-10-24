
var storage=window.localStorage;
var id=storage["thisClassification"];
var url=getUrl();
$.ajax(
    {
        url: url+"/getClassification",
        headers :{
            'Authorization': 'Bearer ' + getToken(),
            'content-type': 'application/x-www-form-urlencoded'
        },
        data: {
            userLabel:id
        },
        async:false,
        success: function (data) {

            document.getElementById("userLabel").value=data.classification.userLabel;
            if(data.classification.workClass=="capital"){
                document.getElementById("workClass").value="资金类";
            }
            else if(data.classification.workClass=="stock"){
                document.getElementById("workClass").value="股票类";
            }
            else if(data.classification.workClass=="merge"){
                document.getElementById("workClass").value="并购类";
            }
        },
        error: function (xhr) {
            alert('动态页有问题噶！\n\n' + xhr.responseText);
        },
        traditional: true,
    }
)
document.getElementById("ad").onclick=function() {
    var userLabel = $("#userLabel").val();
    var obj1= document.getElementById("workClass"); //定位id
    var index1 = obj1.selectedIndex; // 选中索引
    var workClass = obj1.options[index1].text; // 选中文本
    if(workClass=="资金类"){
        workClass="capital";
    }
    else if(workClass=="股票类"){
        workClass="stock";
    }
    else if(workClass=="并购类"){
        workClass="merge";
    }
    var url=getUrl();
    $.ajax(
        {
            url: url+"/updateClassification",
            headers :{
                'Authorization': 'Bearer ' + getToken(),
                'content-type': 'application/x-www-form-urlencoded'
            },
            data: {
                userLabel:userLabel,
                workClass:workClass
            },
            success: function (data) {
                alert("修改成功");
                window.location.href = "classification.html";
            },
            error: function (xhr) {
                alert('动态页有问题噶！\n\n' + xhr.responseText);
            },
            traditional: true,
        }
    )


}
