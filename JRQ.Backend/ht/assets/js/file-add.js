$("#loader").hide();
var index=1;
var deletes=[];
function addFile(){
    var fragment=document.createDocumentFragment();
    var divNode=document.getElementById("container");
    var newDiv=document.createElement("div");
    newDiv.setAttribute("id",index.toString()+"delfile");
    fragment.appendChild(newDiv);
    var newInput=document.createElement("input");
    newInput.setAttribute("type","file");
    newInput.setAttribute("id",index.toString());
    newInput.setAttribute("name","选择文件");
    newDiv.appendChild(newInput);

    var newInput=document.createElement("input");
    newInput.setAttribute("type","button");
    newInput.setAttribute("value","删除");

    newInput.setAttribute("id",index.toString()+"del");
    newInput.setAttribute("onclick","delFile()");
    newDiv.appendChild(newInput);
    index=index+1
    divNode.appendChild(fragment);
}
function delFile(){
    var divNode=document.getElementById("container");
    var str_tem=event.target.id.substring(0,event.target.id.length-3);
    //alert(str_tem)
    var child=document.getElementById(event.target.id+"file");
    deletes.push(str_tem)
    divNode.removeChild(child);
}
function adduser() {





    $("#loader").show();

    var fd1 = new FormData($("#upload-file-form1")[0]);

    var storage = window.localStorage;
    var id = storage["adminUsername"];
    var myDate = new Date();
    var date = myDate.toLocaleDateString();
    var attachment="";
    var image="";

    var files=[]
    var url=getUrl();
    var i=0;

    while(i<index) {
        var flag = 0;
        var tem_index = 0;
        while (tem_index < deletes.length) {
            if (deletes[tem_index] == i) {
                alert(i)
                flag = 1;
                break;
            }
            tem_index = tem_index + 1;
        }

        if (flag == 1) {
            i = i + 1;
            continue
        }
        var str_i=i.toString()
        var fd = new FormData();

        fd.append('file',$('#'+str_i)[0].files[0]);

        $.ajax({
            url: url + "/upload",
            type: "POST",
            data: fd,
            enctype: 'multipart/form-data',
            processData: false,
            contentType: false,
            cache: false,
            async: false,
            success: function (data) {
                if (data != "") {
                    files.push(data);
                }

            },
            error: function (xhr) {

                $("#loader").hide();
                //alert(xhr.responseText);
                // Handle upload error
                // ...
            }
        });
        i=i+1;
    }

    $.ajax({
        url: url + "/documentImage",
        type: "POST",
        data: fd1,
        enctype: 'multipart/form-data',
        processData: false,
        contentType: false,
        cache: false,
        async: false,
        success: function (d) {
            if(d!="") {
                image = d;
            }
            $("#loader").hide();
            $.ajax(
                {
                    url: url + "/addDocument",
                    data: {
                        title: $("#title").val(),
                        content: $("#content").val(),
                        detail:$("#detail").val(),
                        attachments:files,
                        image:image,
                        writerName: id,
                        price: $("#price").val()
                    },
                    async: false,
                    success: function (data) {

                        alert("添加成功");
                        window.location.href = "file.html";
                    },
                    error: function (xhr) {
                        //alert('动态页有问题噶！\n\n' + xhr.responseText);
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