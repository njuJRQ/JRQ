
var storage=window.localStorage;
var username=storage["adminUsername"];
var id;
var date;
var url=getUrl();
var limits="";
$.ajax(
    {
        url: url+"/getAdminByUsername",
        headers :{
            'Authorization': 'Bearer ' + getToken(),
            'content-type': 'application/x-www-form-urlencoded'
        },
        data: {
            username:username
        },
        async:false,
        success: function (data) {
            document.getElementById("number").innerText=data.admin.id;
            id=data.admin.id;
            document.getElementById("name").value=data.admin.username;
            document.getElementById("password").value=data.admin.password;
            date=data.admin.date;
            limits=data.admin.limits;

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
                window.location.href = "index.html";
            },
            error: function (xhr) {
                alert('动态页有问题噶！\n\n' + xhr.responseText);
            },
            traditional: true,
        }
    )


}