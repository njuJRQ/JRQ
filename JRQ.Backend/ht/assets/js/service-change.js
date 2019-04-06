var url=getUrl();
var storage = window.localStorage;
var id=storage["thisService"];
$.ajax(
    {
        url: url+"/business/findImageById",
        data: {
            id:id
        },
        async:false,
        success: function (data) {
            document.getElementById("id").innerText=id;

            if (data.businessImageItem.marketType == 'GOLD_MARKET') {
                data.businessImageItem.marketType = '地金市场'
            } else if (data.businessImageItem.marketType == 'PRIMARY_MARKET') {
                data.businessImageItem.marketType = '一级市场'
            } else if (data.businessImageItem.marketType == 'SECONDARY_MARKET') {
                data.businessImageItem.marketType = '二级市场'
            } else if (data.businessImageItem.marketType == 'PAPER_MARKET') {
                data.businessImageItem.marketType = '票据市场'
            } else if (data.businessImageItemList[i].marketType == 'BAD_ASSETS') {
                data.businessImageItem.marketType = '不良资产'
            } else if (data.businessImageItem.marketType == 'LARGE_SHORT_BREAK') {
                data.businessImageItem.marketType = '大额短拆'
            } else if (data.businessImageItem.marketType == 'ASSET_SECURITIZATION') {
                data.businessImageItem.marketType = '资产证券化'
            } else if (data.businessImageItem.marketType == 'ISSUANCE_BY_GOVERNMENT') {
                data.businessImageItem.marketType = '政府平台发债'
            } else if (data.businessImageItem.marketType == 'FINANCIAL_LICENSE') {
                data.businessImageItem.marketType = '金融牌照'
            } else if (data.businessImageItem.marketType == 'FUND_SERVICE') {
                data.businessImageItem.marketType = '基金服务'
            } else if (data.businessImageItem.marketType == 'OTHERS') {
                data.businessImageItem.marketType = '其他'
            }
            document.getElementById("marketType").value=data.businessImageItem.marketType;
            document.getElementById("position").value=data.businessImageItem.position;


        },
        error: function (xhr) {
            alert('动态页有问题噶！\n\n' + xhr.responseText);
        },
        traditional: true,
    }
)

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