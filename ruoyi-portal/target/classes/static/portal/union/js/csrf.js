var getCookie = function (name) {
    var arr, reg = new RegExp("(^| )" + name + "=([^;]*)(;|$)");
    if (arr = document.cookie.match(reg)) {
        return arr[2]
    } else {
        return null;
    }
};
$(document).ajaxSend(function(e, xhr, options) {
    xhr.setRequestHeader('X-XSRF-TOKEN', getCookie('X-XSRF-TOKEN'));
});