$(function(){
    $(".ghgk-left ul li").click(function(){
        $(".ghgk-left ul li").removeClass("select");
        $(this).addClass("select");
        var index = $(this).index();
        $(".ghgk-con").removeClass("active");
        $($(".ghgk-con")[index]).addClass("active");
    });
});