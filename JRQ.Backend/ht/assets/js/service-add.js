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


document.getElementById("ad").onclick=function() {
    var fd = new FormData($("#upload-file-form")[0]);
    var url=getUrl();
    var image;

    $.ajax({
        url: url + "/upload",
        type: "POST",
        data: fd,
        enctype: 'multipart/form-data',
        processData: false,
        contentType: false,
        cache: false,
        success: function (data) {
            if (data != "") {
                image = data;
            }

            var obj1 = document.getElementById("position"); //定位id
            var index1 = obj1.selectedIndex; // 选中索引
            var position = obj1.options[index1].text; // 选中文本

            var obj2 = document.getElementById("marketType"); //定位id
            var index2 = obj2.selectedIndex; // 选中索引
            var marketType = obj2.options[index2].text; // 选中文本
            if(marketType=="地金市场"){
                marketType="GOLD_MARKET";
            }
            else if(marketType=="一级市场"){
                marketType="PRIMARY_MARKET";
            }
            else if(marketType=="二级市场"){
                marketType="SECONDARY_MARKET";
            }
            else if(marketType=="票据市场"){
                marketType="PAPER_MARKET";
            }
            else if(marketType=="不良资产"){
                marketType="BAD_ASSETS";
            }
            else if(marketType=="大额短拆"){
                marketType="LARGE_SHORT_BREAK";
            }
            else if(marketType=="资产证券化"){
                marketType="ASSET_SECURITIZATION";
            }
            else if(marketType=="政府平台发债"){
                marketType="ISSUANCE_BY_GOVERNMENT";
            }
            else if(marketType=="金融牌照"){
                marketType="FINANCIAL_LICENSE";
            }
            else if(marketType=="基金服务"){
                marketType="FUND_SERVICE";
            }
            else if(marketType=="其他"){
                marketType="OTHERS";
            }
            $.ajax(
                {
                    url: url + "/business/uploadImage",
                    data: {
                        marketType:marketType,
                        position:position,
                        image:image
                    },
                    success: function (data) {
                        alert("添加成功");
                        window.location.href = "service.html";
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