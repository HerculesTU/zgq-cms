/*
*名称:图片上传本地预览插件 v1.1
*作者:周祥
*时间:2013年11月26日
*介绍:基于JQUERY扩展,图片上传预览插件 目前兼容浏览器(IE 谷歌 火狐) 不支持safari
*插件网站:http://keleyi.com/keleyi/phtml/image/16.htm
*参数说明: Img:图片ID;Width:预览宽度;Height:预览高度;ImgType:支持文件类型;Callback:选择文件显示图片后回调方法;
*使用方法: 
<div>
<img id="ImgPr" width="120" height="120" /></div>
<input type="file" id="up" />
把需要进行预览的IMG标签外 套一个DIV 然后给上传控件ID给予uploadPreview事件
$("#up").uploadPreview({ Img: "ImgPr", Width: 120, Height: 120, ImgType: ["gif", "jpeg", "jpg", "bmp", "png"], Callback: function () { }});
*/
jQuery.fn.extend({
    uploadPreview: function (opts) {
        var _self = this,
            _this = $(this);
        opts = jQuery.extend({
            Img: "ImgPr",
            Width: 100,
            Height: 100,
            ImgType: ["gif", "jpeg", "jpg", "bmp", "png"],
            Callback: function () {}
        }, opts || {});
        _self.getObjectURL = function (file) {
            var url = null;
            if (window.createObjectURL != undefined) {
                url = window.createObjectURL(file)
            } else if (window.URL != undefined) {
                url = window.URL.createObjectURL(file)
            } else if (window.webkitURL != undefined) {
                url = window.webkitURL.createObjectURL(file)
            }
            return url
        };
        _this.change(function () {
            if (this.value) {
                if (!RegExp("\.(" + opts.ImgType.join("|") + ")$", "i").test(this.value.toLowerCase())) {
                    alert("选择文件错误,图片类型必须是" + opts.ImgType.join("，") + "中的一种");
                    this.value = "";
                    return false
                }
                if ($.browser.msie) {
                    try {
                        $("#" + opts.Img).attr('src', _self.getObjectURL(this.files[0]))
                    } catch (e) {
                        var src = "";
                        var obj = $("#" + opts.Img);
                        var div = obj.parent("div")[0];
                        _self.select();
                        if (top != self) {
                            window.parent.document.body.focus()
                        } else {
                            _self.blur()
                        }
                        src = document.selection.createRange().text;
                        document.selection.empty();
                        obj.hide();
                        obj.parent("div").css({
                            'filter': 'progid:DXImageTransform.Microsoft.AlphaImageLoader(sizingMethod=scale)',
                            'width': opts.Width + 'px',
                            'height': opts.Height + 'px'
                        });
                        div.filters.item("DXImageTransform.Microsoft.AlphaImageLoader").src = src;
                        $(div).parents(".a_yyzz").find(".a_yyzzFade").hide();//遮罩层
                        $(div).parents(".a_form").find(".a_yl").show();//预览按钮
                    }
                } else {
                    $("#" + opts.Img).attr('src', _self.getObjectURL(this.files[0]));
                    $("#" + opts.Img).parents(".a_yyzz").find(".a_yyzzFade").hide();//遮罩层
                    $("#" + opts.Img).parents(".a_form").find(".a_yl").show();//预览按钮
                }
                //doupload(opts.id);
                if(opts.id.indexOf("_")>0){
                    var ids=opts.id.split("_");
                    var dd=doupload(ids[1]);
                    if(dd.code==1){
                        layer.msg(dd.msg);
                    }else{
                        $("#"+opts.id).val(JSON.stringify(dd));
                    }
                }
                opts.Callback()
            }
        })
    }
});
//图片预览
function imgPreview(obj){
	var str = "";
	str += '<div class="fade"></div>';
	if($.browser.msie){
		var src = $(obj).parent().find(".a_yyzzs")[0].filters.item("DXImageTransform.Microsoft.AlphaImageLoader").src;
		str += '<div class="modal"><div class="modalClose" onclick="closeModal()"></div></div>';
		$(document.body).append(str);
		$(".modal").css("filter","progid:DXImageTransform.Microsoft.AlphaImageLoader(sizingMethod=scale)");
		$(".modal")[0].filters.item("DXImageTransform.Microsoft.AlphaImageLoader").src = src;
	}else{
		var src = $(obj).parent().find("img")[0].src;
		str += '<div class="modal"><img src='+src+' class="modals"/><div class="modalClose" onclick="closeModal()"></div></div>';
		$(document.body).append(str);
	}
	$(document.body).css("overflow","hidden");
}
//图片预览关闭
function closeModal(){
	$(".fade").remove();
	$(".modal").remove();
	$(document.body).css("overflow","auto");
}