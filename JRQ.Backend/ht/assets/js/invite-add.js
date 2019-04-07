

function adduser() {

    var url = getUrl();
    $.ajax(
        {
            url: url + "/jobCard/add",
            type: "POST",
            data: JSON.stringify({
                "id":"",
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
                alert("添加成功");
                window.location.href = "invite.html";
            },
            error: function (xhr) {
                //alert('动态页有问题噶！\n\n' + xhr.responseText);
            },
            traditional: true
        }
    )



}