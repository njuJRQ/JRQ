//var address = "http://localhost:8080";
var address = "https://junrongcenter.com:3389";
var picUrl = "https://www.junrongcenter.com/"
function getUrl() {
    return address;
}

function getToken(){
    var storage = window.localStorage;
    var token=storage["token"];
    return token;
}
function getPicUrl(){
    return picUrl
}