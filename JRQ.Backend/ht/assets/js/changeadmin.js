
var storage=window.localStorage;
var id=storage["updateadmin"];
var date;
var url=getUrl();
$.ajax(
    {
        url: url+"/getAdmin",
        headers :{
            'Authorization': 'Bearer ' + getToken(),
            'content-type': 'application/x-www-form-urlencoded'
        },
        data: {
            id:id
        },
        async:false,
        success: function (data) {

            document.getElementById("name").value=data.admin.username;
            document.getElementById("password").value=data.admin.password;
            date=data.admin.date;
            var limits=data.admin.limits;
            for(var i=1;i<=14;i++){
                for(var j=0;j<limits.length;j++){
                    if(parseInt(limits[j])==i){
                        document.getElementById("c"+i).checked=true;
                    }
                }
            }
        },
        error: function (xhr) {
            alert('动态页有问题噶！\n\n' + xhr.responseText);
        },
        traditional: true,
    }
)
document.getElementById("ad").onclick=function() {
    var name = $("#name").val();
    var pass = $("#password").val();
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
    if (document.getElementById("c7").checked) {
        limits = limits + "7" + "...";
    }

    $.ajax(
        {
            url: url+"/updateAdmin",
            headers :{
                'Authorization': 'Bearer ' + getToken(),
                'content-type': 'application/x-www-form-urlencoded'
            },
            data: {
                id:id,
                username:name,
                password:pass,
                limits:limits,
                date:date
            },
            success: function (data) {
                alert("修改成功");
                window.location.href = "admin-list.html";
            },
            error: function (xhr) {
                alert('动态页有问题噶！\n\n' + xhr.responseText);
            },
            traditional: true,
        }
    )


}