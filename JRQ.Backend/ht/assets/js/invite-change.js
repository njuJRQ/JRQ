$("#loader").hide();
var url=getUrl();
var storage = window.localStorage;
var id=storage["thisJobCart"];
$.ajax(
    {
        url: url+"/jobCard/findById",
        type:"POST",
        data: {
            id:id
        },
        async:false,
        success: function (data) {
            document.getElementById("id").innerText=data.jobCardItem.id;
            document.getElementById("expectPosition").value=data.jobCardItem.expectPosition;
            document.getElementById("expectWage").value=data.jobCardItem.expectWage;
            document.getElementById("degree").value=data.jobCardItem.degree;
            document.getElementById("introduction").value=data.jobCardItem.introduction;
            if(data.jobCardItem.isFresh){
                data.jobCardItem.isFresh="是";
            }
            else{
                data.jobCardItem.isFresh="否";
            }

            document.getElementById("isFresh").value=data.jobCardItem.isFresh;
            document.getElementById("enterprise").value=data.jobCardItem.enterprise;
            document.getElementById("advantage").value=data.jobCardItem.advantage;
            document.getElementById("city").value=data.jobCardItem.city;
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
        alert(input+"请输入数字");
        document.getElementById(input).value = "";
        return false;
    }
    return true;
}

function changeFile() {

    var url = getUrl();
    $.ajax(
        {
            url: url + "/jobCard/update",
            type: "POST",
            data: JSON.stringify({
                "id":id,
                "expectPosition":$("#expectPosition").val(),
                "expectWage":$("#expectWage").val(),
                "degree":$("#degree").val(),
                "introduction":$("#introduction").val(),
                "isFresh":$("#isFresh option:selected").val(),
                "enterprise":$("#enterprise").val(),
                "advantage":$("#advantage").val(),
                "city":$("#city").val()
            }),
            dataType:"json",
            contentType:"application/json",
            cache: false,
            async: false,
            success: function (data) {
                alert("修改成功");
                window.location.href = "invite.html";
            },
            error: function (xhr) {
                //alert('动态页有问题噶！\n\n' + xhr.responseText);
            },
            traditional: true
        }
    )



}
