$("#loader").hide();

var index=1;
var deletes=[];
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
    var videos=[]
    var url=getUrl();
    var i=0;
    while(i<index){
        var flag=0;
        var tem_index=0;
        while( tem_index<deletes.length){
            if(deletes[tem_index]==i){

                flag=1;
                break;
            }
            tem_index=tem_index+1;
        }

        if(flag==1){
            i=i+1;
            continue
        }


        var form2 = new FormData();
        var str_i=i.toString()
        form2.append('video',$('#'+str_i)[0].files[0]);

        $.ajax({
            url: url + "/courseVideo",
            type: "POST",
            data: form2,
            enctype: 'multipart/form-data',
            processData: false,
            contentType: false,
            cache: false,
            async: false,
            success: function (data) {

                if(data!="") {

                    videos.push(data);

                }
            },
            error: function (xhr) {
                $("#loader").hide();
            }
        });

        //添加文件

        i=i+1;

    }


    if(checkRate("price")) {
        var url = getUrl();
        var storage = window.localStorage;
        var id = storage["adminUsername"];
        var myDate = new Date();
        var date = myDate.toLocaleDateString();
        // 上传图片
        var image="";
        var el = $('#image')[0];
        var formData = new FormData();
        if (!el.files[0]) {
            return;
        }
        formData.append('image', el.files[0]);

        $("#loader").hide();
        $.ajax({
            url: url + "/courseImage",
            type: "POST",
            data: formData,
            enctype: 'multipart/form-data',
            processData: false,
            contentType: false,
            cache: false,
            async: false,
            success: function (data) {

                if(data!="") {
                    image = data;
                }

                $.ajax(
                    {
                        url: url + "/addCourse",
                        data: {
                            title: $("#title").val(),
                            detail:$("#details").val(),
                            writerName: id,
                            date: date,
                            price: parseInt($("#price").val()),
                            image:image,
                            videos:videos,
                            isTextualResearchCourse:false
                        },
                        async: false,
                        success: function (data) {
                            alert("添加成功");
                            window.location.href = "course.html";
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
            }
        });




    }
}