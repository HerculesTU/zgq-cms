(function($){
    $.fn.tabs = function(){
        $(this).each(function(index,el){
            var that = $(el);
            $(el).find(".tab a").eq(0).addClass('active');
            var target = $(el).find(".tab").eq(0).attr('data-target');
            $(target).siblings().hide();
            $(el).on('mouseover mouseout','.tab',function(){
                $(el).find('.tab a').removeClass('active');
                $(this).find("a").addClass('active');
                var target = $(this).attr('data-target');
                $(target).siblings().hide().end().show();
            });
        });
    }
})(jQuery);

(function($){
    $.fn.tabs2 = function(){
        $(this).each(function(index,el){
            var that = $(el);
            $(el).find(".tab2 a").eq(0).addClass('active');
            var target = $(el).find(".tab2").eq(0).attr('data-target');
            $(target).siblings().hide();
            $(el).on('mouseover mouseout','.tab2',function(){
                $(el).find('.tab2 a').removeClass('active');
                $(this).find("a").addClass('active');
                var target = $(this).attr('data-target');
                $(target).siblings().hide().end().show();
            });
        });
    }
})(jQuery);

$(function(){
    $(".nav_tem").hover(function(){
        $(this).find(".nav_sublist").addClass("active")
    },function(){
        $(this).find(".nav_sublist").removeClass("active")
    });

    //$('#news_tab1').tabs();
    $('#ghyw_tab').tabs();
    /*$('#news_sty2_tab1').tabs();
    $('#news_sty2_tab2').tabs();
    $('#book_tab1').tabs2();*/

    // var ad_slider1 = $('#ad_slider1').bxSlider({
    //     auto: true,
    //     controls: false,
    //     onSliderLoad:function(){
    //         $(".ad_slider1_wrap .bx-controls-direction a").click(function(){
    //                 ad_slider1.goToNextSlide();
    //                 ad_slider1.startAuto();
    //         })
    //     }
    // });



    getDate("today");

    if($("#mar1").length>0){
        $("#mar1").marquee({
            direction:"left",
            speed:30
        });
    };
    // $('#news2_tab').tabs();
    // $('#news3_tab').tabs();


    // $(".zxft_block2:nth-child(3n)").css({
    //     "margin-right": 0
    // });

    // $(".img_list1 li").each(function(){
    //     var img = $(this).find("img");
    //     if(img.length==0){
    //         $(this).find(".img_list_wz").css({
    //             "float": "none",
    //             "width": "auto"
    //         })
    //     };
    //     if(img.attr("src")==0){
    //         img.remove();
    //         $(this).find(".img_list_wz").css({
    //             "float": "none",
    //             "width": "auto"
    //         })
    //     }
    // });

    // var localurl = "http://ggzy.gd.gov.cn/";
    // var test = "http://search.gd.gov.cn/search/local_msg/148";
    // $("a").on("click",function(event){
    //     var that = $(this);
    //     var href = $(this).attr("href");
    //     if(href!=""){
    //         if(href!="#" && href.indexOf(test)<0){
    //             event.preventDefault();
    //             var io = href.indexOf(localurl);
    //             if(io<0){
    //                 var msg = "您已选择离开广东省公共资源交易中心，页面跳转中，请您稍后......";
    //                 if(confirm(msg) == false){
    //                     return false
    //                 }else{
    //                     window.open(href);
    //                 }
    //             }else{
    //                 var par_name = that.parent().attr("class");
    //                 if(par_name=="xxgk_wrap"){
    //                     window.open(href,"_self");
    //                 }else{
    //                     window.open(href,"_blank");
    //                 }
    //             }
    //         };

    //         if(href.indexOf(test)>0){
    //             event.preventDefault();
    //             var io = href.indexOf(localurl);
    //             if(io<0){
    //                 var msg = "您已选择离开广东省公共资源交易中心，页面跳转中，请您稍后......";
    //                 if(confirm(msg) == false){
    //                     return false
    //                 }else{
    //                     window.open(href);
    //                 }
    //             }else{
    //                 var par_name = that.parent().attr("class");
    //                 if(par_name=="xxgk_wrap"){
    //                     window.open(href,"_self");
    //                 }else{
    //                     window.open(href,"_blank");
    //                 }
    //             }
    //         }
    //     }
    // });

    // $(".frd_list").change(function(event){
    //     var href = this.value;
    //     if(href!=""){
    //         if(href!="#" && href.indexOf(test)<0){
    //             event.preventDefault();
    //             var io = href.indexOf(localurl);
    //             if(io<0){
    //                 var msg = "您已选择离开广东省公共资源交易中心，页面跳转中，请您稍后......";
    //                 if(confirm(msg) == false){
    //                     return false
    //                 }else{
    //                     window.open(href);
    //                 }
    //             }else{
    //                 window.open(href);
    //             }
    //         };

    //         if(href.indexOf(test)>0){
    //             event.preventDefault();
    //             var io = href.indexOf(localurl);
    //             if(io<0){
    //                 var msg = "您已选择离开广东省公共资源交易中心，页面跳转中，请您稍后......";
    //                 if(confirm(msg) == false){
    //                     return false
    //                 }else{
    //                     window.open(href);
    //                 }
    //             }else{
    //                 window.open(href);
    //             }
    //         }
    //     }
    // });

})

function getDate(id){
    var date = new Date(),
        year = date.getFullYear(),
        month = date.getMonth() + 1,
        day = date.getDate(),
        week = date.getDay();

    switch(week){
        case 0:
            week = "天";
            break;
        case 1:
            week = "一";
            break;
        case 2:
            week = "二";
            break;
        case 3:
            week = "三";
            break;
        case 4:
            week = "四";
            break;
        case 5:
            week = "五";
            break;
        case 6:
            week = "六";
            break;
    }
    var todayHtml = year + "年" + month + "月" + day + "日 星期" + week;
    $("#"+id).html(todayHtml);

}