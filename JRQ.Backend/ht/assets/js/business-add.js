$("#loader").hide();

function adduser() {
    $("#loader").show();
    // var fd = new FormData($("#upload-file-form")[0]);
    var url = getUrl();
    var storage = window.localStorage;
    var id = storage["adminUsername"];
    var myDate = new Date();
    var date = myDate.toLocaleDateString();
    $.ajax(
        {
            url: url + "/business/add",
            data: {
                linkMan: $("#linkMan").val(),
                phone: $("#phone").val(),
                agencyName: $("#agencyName").val(),
                projectRef:$("#projectRef option:selected").val(),
                projectInfo:$("#projectInfo").val(),
                writerOpenid: id,
                // id:id,
                marketType: $("#marketType option:selected").val(),
                date: date
            },
            
            async: false,
            success: function (data) {

                alert("添加成功");
                window.location.href = "business.html";
            },
            error: function (xhr) {
                //alert('动态页有问题噶！\n\n' + xhr.responseText);
            },
            traditional: true,
        }
    );
    // error: function (xhr) {
    //     $("#loader").hide();
    //     alert(xhr.responseText);
    //     Handle upload error
    //     ...
    // }
}