document.getElementById("ad").onclick=function() {
    if($("#password").val()!=$("#passwordagain").val()){
        alert("请保证密码一致！")
    }
    else {
        var name = $("#name").val();
        var pass = $("#password").val();
        var myDate = new Date();
        var date = myDate.toLocaleDateString();
        var limits = "";
        if (document.getElementById("c1").checked) {
            limits = limits + "1" + "...";
        }
        if (document.getElementById("c2").checked) {
            limits = limits + "2" + "...";
        }
        if (document.getElementById("c3").checked) {
            limits = limits + "3" + "...";
        }
        if (document.getElementById("c4").checked) {
            limits = limits + "4" + "...";
        }
        if (document.getElementById("c5").checked) {
            limits = limits + "5" + "...";
        }
        if (document.getElementById("c6").checked) {
            limits = limits + "6" + "...";
        }
        var url=getUrl();
        $.ajax(
            {
                url: url+"/addadmin",
                data: {
                    username:name,
                    password:pass,
                    limits:limits,
                    date:date
                },
                success: function (data) {
                    alert("添加成功");
                    window.location.href = "admin-list.html";
                },
                error: function (xhr) {
                    alert('动态页有问题噶！\n\n' + xhr.responseText);
                },
                traditional: true,
            }
        )

    }
}