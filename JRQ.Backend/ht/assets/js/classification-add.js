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
                url: url+"/addClassification",
                data: {
                    userLabel:userLabel,
                    workClass:workClass
                },
                success: function (data) {
                    alert("添加成功");
                    window.location.href = "classification.html";
                },
                error: function (xhr) {
                    alert('动态页有问题噶！\n\n' + xhr.responseText);
                },
                traditional: true,
            }
        )


}