$(function(){
	$("#agreeSqs").click(function(){
		if($(this).is(":checked")){
			$("#tijiao").removeAttr("disabled");
		}else{
			$("#tijiao").attr("disabled",true);
		}
	});
	$("#agree").click(function(){
		layer.open({
			type: 1,
			anim: 2,
			area: ['500px', '300px'],
			title:'中华全国总工会入会申请书',
			shadeClose: true, //开启遮罩关闭
			content: '<div style="text-indent: 2em;font-size: 16px;line-height: 30px;padding: 20px;">我自愿加入中华全国总工会，遵守工会章程，执行工会决议，积极参加工会活动，为全面建设小康社会、把我国建设成为富强民主和谐的社会主义现代化国家、实现中华民族伟大复兴的中国梦而奋斗。</div>'
		});
	});
	$("#uploadImg").change(function(){
		var windowURL = window.URL || window.webkitURL;
		var dataURL = windowURL.createObjectURL($(this)[0].files[0]);
		$("#imgs")[0].src=dataURL;
		$(".uploads").hide();
		$(".img-show").show();
		$("#uploadImg-error").hide();
		doupload('uploadtest');
	});
	$("#delImg").click(function(){
		$(".uploads").show();
		$(".img-show").hide();
		$("#imgs")[0].src="";
		$("#uploadImg").val('');
	});
});