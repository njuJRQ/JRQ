var url=getUrl();
var storage = window.localStorage;
var id=storage["thisJobCart"];
var path;
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
            document.getElementById("expectPosition").innerText=data.jobCardItem.expectPosition;
            document.getElementById("expectWage").innerText=data.jobCardItem.expectWage;
            document.getElementById("degree").innerText=data.jobCardItem.degree;
            document.getElementById("introduction").innerText=data.jobCardItem.introduction;
            if(data.jobCardItem.isFresh){
                data.jobCardItem.isFresh="是";
            }
            else{
                data.jobCardItem.isFresh="否";
            }

            document.getElementById("isFresh").innerText=data.jobCardItem.isFresh;
            document.getElementById("enterprise").innerText=data.jobCardItem.enterprise;
            document.getElementById("advantage").innerText=data.jobCardItem.advantage;
            document.getElementById("city").innerText=data.jobCardItem.city;



        },
        error: function (xhr) {
            alert('动态页有问题噶！\n\n' + xhr.responseText);
        },
        traditional: true,
    }
)

