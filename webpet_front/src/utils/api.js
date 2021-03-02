export function getTime(){
    var date = new Date();
    var seperator1 = "/";
    var seperator2 = ":";
    //以下代码依次是获取当前时间的年月日时分秒
    var year = date.getFullYear();
    var month = date.getMonth() + 1;
    var strDate = date.getDate();
    var minute = date.getMinutes();
    var hour = date.getHours();
    var second = date.getSeconds();
    //固定时间格式
    if (month >= 1 && month <= 9) {
        month = "0" + month;
    }
    if (strDate >= 0 && strDate <= 9) {
        strDate = "0" + strDate;
    }
    if (hour >= 0 && hour <= 9) {
        hour = "0" + hour;
    }
    if (minute >= 0 && minute <= 9) {
        minute = "0" + minute;
    }
    if (second >= 0 && second <= 9) {
        second = "0" + second;
    }
    var currentdate =  year + seperator1 + month + seperator1 + strDate
        + " " + hour + seperator2 + minute + seperator2 + second;
    return currentdate;
}
export default {
    getTime
}