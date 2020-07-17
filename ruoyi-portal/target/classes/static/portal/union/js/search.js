function quickSearch(obj) {
    var searchInput = $(obj).prev("input").val();
    var searchVal = $.trim(searchInput);
    if (searchVal == '') {
        layer.msg("哎呀，你好像忘记输入搜索内容了！");
        return false;
    }
    if (searchVal.length < 2) {
        layer.msg("搜索关键字至少需要2个字哟！");
        return false;
    } else {
        window.open(ctx + "portal/search?content=" + searchVal);
    }
}