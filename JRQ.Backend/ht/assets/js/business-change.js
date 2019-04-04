$("#loader").hide();
var url = getUrl();
var storage = window.localStorage;
var id = storage["thisDocument"];
var path;
var attachment = "";
$.ajax(
    {
        url: url + "/business/findById",
        data: {
            id: id
        },
        async: false,
        success: function (data) {
            document.getElementById("id").innerText = data.businessItem.id;
            document.getElementById("writerOpenid").innerText=data.businessItem.writerOpenid;
            document.getElementById("agencyName").innerText = data.businessItem.agencyName;
            document.getElementById("phone").innerText = data.businessItem.phone;
            document.getElementById("projectInfo").innerText = data.businessItem.projectInfo;
            document.getElementById("linkMan").innerText = data.businessItem.linkMan;
            if (data.businessItem.projectRef == "ACTUAL_CONTROLLER") {
                data.businessItem.projectRef = '实控人'
            } else if (data.businessItem.projectRef == "CORE_OF_SHAREHOLDERS") {
                data.businessItem.projectRef = '核心股东'
            } else if (data.businessItem.projectRef == "EMPLOYEE") {
                data.businessItem.projectRef = '雇员'
            } else if (data.businessItem.projectRef == "THIRD_PARTY") {
                data.businessItem.projectRef = '一手第三方'
            } else {
                data.businessItem.projectRef = ''
            }
            document.getElementById("projectRef  option:selected").innerText = data.businessItem.projectRef;

            if (data.businessItem.marketType == 'GOLD_MARKET') {
                data.businessItem.marketType = '地金市场'
            } else if (data.businessItem.marketType == 'PRIMARY_MARKET') {
                data.businessItem.marketType = '一级市场'
            } else if (data.businessItem.marketType == 'SECONDARY_MARKET') {
                data.businessItem.marketType = '二级市场'
            } else if (data.businessItem.marketType == 'PAPER_MARKET') {
                data.businessItem.marketType = '票据市场'
            } else if (data.businessItem.marketType == 'BAD_ASSETS') {
                data.businessItem.marketType = '不良资产'
            } else if (data.businessItem.marketType == 'LARGE_SHORT_BREAK') {
                data.businessItem.marketType = '大额短拆'
            } else if (data.businessItem.marketType == 'ASSET_SECURITIZATION') {
                data.businessItem.marketType = '资产证券化'
            } else if (data.businessItem.marketType == 'ISSUANCE_BY_GOVERNMENT') {
                data.businessItem.marketType = '政府平台发债'
            } else if (data.businessItem.marketType == 'FINANCIAL_LICENSE') {
                data.businessItem.marketType = '金融牌照'
            } else if (data.businessItem.marketType == 'FUND_SERVICE') {
                data.businessItem.marketType = '基金服务'
            } else if (data.businessItem.marketType == 'OTHERS') {
                data.businessItem.marketType = '其他'
            }
            document.getElementById("marketType  option:selected").innerText = data.businessItem.marketType;
            console.log(data.businessItem.marketType)
        },
        error: function (xhr) {
            alert('动态页有问题噶！\n\n' + xhr.responseText);
        },
        traditional: true,
    }
)

function checkRate(input) {
    var re = /^[0-9]+.?[0-9]*$/; //判断字符串是否为数字 //判断正整数 /^[1-9]+[0-9]*]*$/
    var nubmer = document.getElementById(input).value;

    if (!re.test(nubmer)) {
        alert(input + "请输入数字");
        document.getElementById(input).value = "";
        return false;
    }
    return true;
}
function changeFile() {
        $("#loader").show();
        $.ajax(
            {
                url: url + "/business/update",
                data: {
                    linkMan: $("#linkMan").val(),
                    phone: $("#phone").val(),
                    agencyName: $("#agencyName").val(),
                    projectRef:$("#projectRef option:selected").val(),
                    projectInfo:$("#projectInfo").val(),
                    writerOpenid: writerOpenid,
                    id:id,
                    marketType: $("#marketType option:selected").val(),
                },
                async: false,
                success: function (data) {
                    alert("修改成功");
                    window.location.href = "business.html";
                },
                error: function (xhr) {
                    alert('动态页有问题噶！\n\n' + xhr.responseText);
                },
                traditional: true,
            }
        )
}
