$.get("assets/php/getthisvip.php" ,async =false,function (data){
    var temp=data.split("\n");
    var temp1=temp[temp.length-1];
    var question=temp1.split(" ");
    document.getElementById("number").innerText=question[0];
    document.getElementById("username").innerText=question[1];
    document.getElementById("xueli").innerText=question[2];
    document.getElementById("name").innerText=question[3];
    document.getElementById("minzu").innerText=question[4];
    document.getElementById("sex").innerText=question[5];
    document.getElementById("date").innerText=question[6];
})