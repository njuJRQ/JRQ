$("#your-alert-1").hide();
$("#your-alert-2").hide();
$("#your-alert-3").hide();
$("#your-alert-4").hide();
$("#your-alert-5").hide();
$("#page1").hide();
$("#page2").hide();
$("#page3").hide();
$("#page4").hide();
$("#page5").hide();
var list=new Array();
var firstID=0;
var currentpage=1;
var theGroup=0;
$.get("assets/php/getadmin.php" ,async =false,function (data){
    var temp=data.split("\n");
    var temp1=temp[temp.length-1];
    var questionlist=temp1.split(",");
    for(var i=0;i<questionlist.length-1;i++){
        var question=questionlist[i].split(" ");
        list.push(question);
    }
    changepage(1);
    document.getElementById("jilu").innerText="共"+(list.length)+"条记录";
})
function setthisquestion(n){
    var q=list[firstID+n];
    var thisquestion=q[0]+" "+q[1]+" "+q[2]+" "+q[3]+" "+q[4];
    $.post("assets/php/setthisquestion.php" ,{thisquestion:thisquestion},function (data){

    })
}
function deletequestion(n){
    var q=list[firstID+n];
    $.post("assets/php/deleteadmin.php", {number:q[0]} ,function (data){
        window.location.href="admin-list.html";
    })
}



function changegroup(to){
    $("#page1").show();
    $("#page2").show();
    $("#page3").show();
    $("#page4").show();
    $("#page5").show();
    if(to==1){
        if(theGroup!=0){
            theGroup=theGroup-1;
        }
    }
    else{
        theGroup=theGroup+1;

    }
    document.getElementById("page1").innerText=theGroup*5+1;
    document.getElementById("page2").innerText=theGroup*5+2;
    document.getElementById("page3").innerText=theGroup*5+3;
    document.getElementById("page4").innerText=theGroup*5+4;
    document.getElementById("page5").innerText=theGroup*5+5;


    changepage(1);
}
function changepage(page){
    $("#page1").show();
    $("#page2").show();
    $("#page3").show();
    $("#page4").show();
    $("#page5").show();
    firstID=(theGroup*5+page-1)*5;
    if(list.length<(theGroup*25+21)){
        $("#page5").hide();
        if(list.length<(theGroup*25+16)){
            $("#page4").hide();
            if(list.length<(theGroup*25+11)){
                $("#page3").hide();
                if(list.length<(theGroup*25+6)){
                    $("#page2").hide();
                    if(list.length<(theGroup*25+1)){
                        $("#page1").hide();
                    }
                }
            }
        }

    }
    if(list.length<(firstID+1)){
        $("#your-alert-1").hide();
        $("#your-alert-2").hide();
        $("#your-alert-3").hide();
        $("#your-alert-4").hide();
        $("#your-alert-5").hide();
    }
    else if(list.length<(firstID+2)){
        $("#your-alert-1").show();
        document.getElementById("number"+(firstID%5+1)).innerText=list[firstID][0];
        document.getElementById("name"+(firstID%5+1)).innerText=list[firstID][1];
        document.getElementById("date"+(firstID%5+1)).innerText=list[firstID][4];
        $("#your-alert-2").hide();
        $("#your-alert-3").hide();
        $("#your-alert-4").hide();
        $("#your-alert-5").hide();
    }
    else if(list.length<(firstID+3)){
        $("#your-alert-1").show();
        $("#your-alert-2").show();
        document.getElementById("number"+(firstID%5+1)).innerText=list[firstID][0];
        document.getElementById("name"+(firstID%5+1)).innerText=list[firstID][1];
        document.getElementById("date"+(firstID%5+1)).innerText=list[firstID][4];
        document.getElementById("number"+(firstID%5+2)).innerText=list[firstID+1][0];
        document.getElementById("name"+(firstID%5+2)).innerText=list[firstID+1][1];
        document.getElementById("date"+(firstID%5+2)).innerText=list[firstID+1][4];
        $("#your-alert-3").hide();
        $("#your-alert-4").hide();
        $("#your-alert-5").hide();
    }
    else if(list.length<(firstID+4)){
        $("#your-alert-1").show();
        $("#your-alert-2").show();
        $("#your-alert-3").show();
        document.getElementById("number"+(firstID%5+1)).innerText=list[firstID][0];
        document.getElementById("name"+(firstID%5+1)).innerText=list[firstID][1];
        document.getElementById("date"+(firstID%5+1)).innerText=list[firstID][4];
        document.getElementById("number"+(firstID%5+2)).innerText=list[firstID+1][0];
        document.getElementById("name"+(firstID%5+2)).innerText=list[firstID+1][1];
        document.getElementById("date"+(firstID%5+2)).innerText=list[firstID+1][4];
        document.getElementById("number"+(firstID%5+3)).innerText=list[firstID+2][0];
        document.getElementById("name"+(firstID%5+3)).innerText=list[firstID+2][1];
        document.getElementById("date"+(firstID%5+3)).innerText=list[firstID+2][4];
        $("#your-alert-4").hide();
        $("#your-alert-5").hide();
    }
    else if(list.length<(firstID+5)){
        $("#your-alert-1").show();
        $("#your-alert-2").show();
        $("#your-alert-3").show();
        $("#your-alert-4").show();
        document.getElementById("number"+(firstID%5+1)).innerText=list[firstID][0];
        document.getElementById("name"+(firstID%5+1)).innerText=list[firstID][1];
        document.getElementById("date"+(firstID%5+1)).innerText=list[firstID][4];
        document.getElementById("number"+(firstID%5+2)).innerText=list[firstID+1][0];
        document.getElementById("name"+(firstID%5+2)).innerText=list[firstID+1][1];
        document.getElementById("date"+(firstID%5+2)).innerText=list[firstID+1][4];
        document.getElementById("number"+(firstID%5+3)).innerText=list[firstID+2][0];
        document.getElementById("name"+(firstID%5+3)).innerText=list[firstID+2][1];
        document.getElementById("date"+(firstID%5+3)).innerText=list[firstID+2][4];
        document.getElementById("number"+(firstID%5+4)).innerText=list[firstID+3][0];
        document.getElementById("name"+(firstID%5+4)).innerText=list[firstID+3][1];
        document.getElementById("date"+(firstID%5+4)).innerText=list[firstID+3][4];
        $("#your-alert-5").hide();
    }
    else{
        $("#your-alert-1").show();
        $("#your-alert-2").show();
        $("#your-alert-3").show();
        $("#your-alert-4").show();
        $("#your-alert-5").show();
        document.getElementById("number"+(firstID%5+1)).innerText=list[firstID][0];
        document.getElementById("name"+(firstID%5+1)).innerText=list[firstID][1];
        document.getElementById("date"+(firstID%5+1)).innerText=list[firstID][4];
        document.getElementById("number"+(firstID%5+2)).innerText=list[firstID+1][0];
        document.getElementById("name"+(firstID%5+2)).innerText=list[firstID+1][1];
        document.getElementById("date"+(firstID%5+2)).innerText=list[firstID+1][4];
        document.getElementById("number"+(firstID%5+3)).innerText=list[firstID+2][0];
        document.getElementById("name"+(firstID%5+3)).innerText=list[firstID+2][1];
        document.getElementById("date"+(firstID%5+3)).innerText=list[firstID+2][4];
        document.getElementById("number"+(firstID%5+4)).innerText=list[firstID+3][0];
        document.getElementById("name"+(firstID%5+4)).innerText=list[firstID+3][1];
        document.getElementById("date"+(firstID%5+4)).innerText=list[firstID+3][4];
        document.getElementById("number"+(firstID%5+5)).innerText=list[firstID+4][0];
        document.getElementById("name"+(firstID%5+5)).innerText=list[firstID+4][1];
        document.getElementById("date"+(firstID%5+5)).innerText=list[firstID+4][4];
    }


}

function deletesingle(n){
    var q=list[firstID+n];
    $.post("assets/php/deleteadmin.php", {number:q[0]} ,function (data){
    })
}
function delAll(){
    if(document.getElementById("c1").checked){
        deletesingle(0);
    }
    if(document.getElementById("c2").checked){
        deletesingle(1);
    }
    if(document.getElementById("c3").checked){
        deletesingle(2);
    }
    if(document.getElementById("c4").checked){
        deletesingle(3);
    }
    if(document.getElementById("c5").checked){
        deletesingle(4);
    }
    alert("批量删除成功");
    window.location.href="admin-list.html";

}

function search(){
    var text=$("#con").val();
    for(var i=0;i<list.length;i++){
        if(list[i][1]==text){
            $("#your-alert-1").show();
            document.getElementById("number"+(firstID%5+1)).innerText=list[i][0];
            document.getElementById("name"+(firstID%5+1)).innerText=list[i][1];
            document.getElementById("date"+(firstID%5+1)).innerText=list[i][4];
            $("#your-alert-2").hide();
            $("#your-alert-3").hide();
            $("#your-alert-4").hide();
            $("#your-alert-5").hide();
            firstID=i-1;
        }
    }

}


