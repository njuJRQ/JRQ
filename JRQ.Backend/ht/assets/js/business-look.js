var url=getUrl();
var storage = window.localStorage;
var id=storage["thisDocument"];
var path;
$.ajax(
    {
        url: url+"/business/findById",
        data: {
            id:id
        },
        async:false,
        success: function (data) {
            document.getElementById("id").innerText=data.businessItem.id;
            document.getElementById("title").innerText=data.businessItem.agencyName;
            document.getElementById("writerName").innerText=data.businessItem.phone;
            document.getElementById("identity").innerText=data.businessItem.projectInfo;
            document.getElementById("phone").innerText=data.businessItem.linkMan;
            if(data.businessItem.marketType =='GOLD_MARKET'){
                data.businessItem.marketType ='地金市场'
            }else if(data.businessItem.marketType == 'PRIMARY_MARKET'){
                data.businessItem.marketType ='一级市场'
            }else if(data.businessItem.marketType == 'SECONDARY_MARKET'){
                data.businessItem.marketType ='二级市场'
            }else if(data.businessItem.marketType == 'PAPER_MARKET'){
                data.businessItem.marketType ='票据市场'
            }else if(data.businessItem.marketType == 'BAD_ASSETS'){
                data.businessItem.marketType ='不良资产'
            }else if(data.businessItem.marketType == 'LARGE_SHORT_BREAK'){
                data.businessItem.marketType ='大额短拆'
            }else if(data.businessItem.marketType == 'ASSET_SECURITIZATION'){
                data.businessItem.marketType ='资产证券化'
            }else if(data.businessItem.marketType == 'ISSUANCE_BY_GOVERNMENT'){
                data.businessItem.marketType ='政府平台发债'
            }else if(data.businessItem.marketType == 'FINANCIAL_LICENSE'){
                data.businessItem.marketType ='金融牌照'
            }else if(data.businessItem.marketType == 'FUND_SERVICE'){
                data.businessItem.marketType ='基金服务'
            }else if(data.businessItem.marketType == 'OTHERS'){
                data.businessItem.marketType ='其他'
            }
            document.getElementById("content").innerText=data.businessItem.marketType;
            console.log(data.businessItem.marketType)

            // path="../"+data.project.attachment;
        },
        error: function (xhr) {
            alert('动态页有问题噶！\n\n' + xhr.responseText);
        },
        traditional: true,
    }
)

function attatchment(){
    var $eleForm = $("<form method='get'></form>");

    $eleForm.attr("target",'');
    $eleForm.attr("action",path);
    $(document.body).append($eleForm);
    $eleForm.submit();   //提交表单，实现下载
}