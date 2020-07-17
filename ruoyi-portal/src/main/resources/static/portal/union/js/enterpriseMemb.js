$(function(){
	jQuery.validator.addMethod("isIdCardNo", function (value, element) {
		return this.optional(element) || isIdCardNo(value,element.name);
	}, "请正确输入身份证号码");
	jQuery.validator.addMethod("isRightOrNot", function (value, element) {
		return this.optional(element) || isRightOrNot(value);
	}, "请正确输入验证码");
	jQuery.validator.addMethod("passWord", function (value, element) {
		return this.optional(element) || /(?=.*\d)(?=.*[a-zA-Z])(?=.*[^a-zA-Z0-9]).{6,20}/.test(value);
	}, "请输入6-20位密码（包含大小写字母、符号和数字）");
	jQuery.validator.addMethod("isPhone", function(value, element) {
		return this.optional(element)
			|| /^1[34589]\d{1}\d{4}\d{4}( x\d{1,6})?$/.test(value);
	}, "手机号码格式不正确");
	$("#up1").uploadPreview({ Img: "img1", Width: 173, Height: 88,id:'tradingCertificate_uploadtest3' });
	$("#up2").uploadPreview({ Img: "img2", Width: 173, Height: 88,id:'frCertificate_uploadtest4' });
	$("#up3").uploadPreview({ Img: "img3", Width: 173, Height: 88,id:'ghzxsfz_uploadtest12' });
	$("#up4").uploadPreview({ Img: "img4", Width: 173, Height: 88,id:'sfrsfz_uploadtest13' });
	$("#up5").uploadPreview({ Img: "img5", Width: 173, Height: 88,id:'jbrsfz_uploadtest14' });
	$("#up6").uploadPreview({ Img: "img6", Width: 173, Height: 88,id:'yyzzdzb_uploadtest15' });
	imgUpload({
		inputId: 'file', //input框id
		imgBox: 'imgBox', //图片容器id
		buttonId: 'btn', //提交按钮id
		upUrl: '', //提交地址
		data: 'file1', //参数名
		num: "5" //上传个数
	});
	//qh();
    $(".infoenter.user_form .filename").each(function(){
        if($(this).find("span").html()!=""){
            $(this).find("span").html($(this).find("span").html().split("/")[$(this).find("span").html().split("/").length-1])
            $(this).show();
        }
    });
    $(".infoenter.user_form .a_yyzz").each(function(){
        if($(this).parents("li").find("input[type='hidden']").val()){
            $(this).find(".a_yyzzFade").hide();
        }
    });
    if($("#photoMeeting_upBox").val()!=""){
        var cc=$("#photoMeeting_upBox").val();
        var imgList=[];
        if(cc.indexOf(",")>0){
             imgList = cc.split(",");
		}else{
             imgList.push(cc);
		}

        var str = '';
        for(var i=0;i<imgList.length;i++){
            str += '<div class="imgContainer">';
            str += '<img title="" alt="" src="'+imgList[i]+'" onclick="imgDisplay(this)">';
            str += '<p onclick="removeImg(this,0)" class="imgDelete">删除</p>';
            str += '</div>';
        }
        $("#imgBox").html(str);
    }
    if($("#videoMeeting_uploadtest5").val()!=""){
    	var cc=$("#videoMeeting_uploadtest5").val();
        var videoList =[];
        if(cc.indexOf(",")>0){
            videoList=cc.split(",");
		}else{
            videoList.push(cc);
		}

        var str = '';
        for(var i=0;i<videoList.length;i++){
            str += '<div class="item">';
            str += '<video class="video" src="'+videoList[i]+'"></video>';
            str += '<p>'+videoList[i]+'</p>';
            str += '<img onclick="removesp(this)" src="/portal/union/images/delicon.png" class="spdelete">';
            str += '</div>';
        }
        $("#video_content").html(str);
    }
    if($("#sex").val()!=""){
        $("#adminSex").val($("#sex").val());
    }
    if($("#nssd").val()!=""){
		$("#enterprisePayTaxesDistrict").val($("#nssd").val());
	}
	if($("#ssyq").val()!=""){
		$("#enterprisePark").val($("#ssyq").val());
	}
	$('.form-item .form-control').each(function(){
		$(this).select2({
			placeholder: "请选择",
			allowClear: true
		});
	});
});
function qh(){
	$(".part_ul li.selects").click(function(){
		var index = $(this).index();
		$(".formInfo").removeClass("select");
		$(".formInfo").eq(index).addClass("select");
	});
}
function uploadbg(obj){
	$(obj).next(".fileinput").click();
}
function change(obj,id){
	/*var files = $("#upword").val();
	var fil = files.substring(files.indexOf("fakepath\\")+9,files.length);
	$(".filetext").val(fil);*/
	$(obj).next(".filename").show();
	var files = $(obj)[0].files[0];
	$(obj).next(".filename").find("span").html(files.name);
	$(obj).hide();
	$(obj).prev(".input_upload").hide();
	if(id.indexOf("_")>0){
		var ids=id.split("_");
        var dd=doupload(ids[1]);
        if(dd.code==1){
			layer.msg(dd.msg);
		}else{
            $("#"+id).val(JSON.stringify(dd));
		}
	}
}

function nextstep(num){
    var info=checkInterResponseBody();
    var nodeKeys=info.nodeKeys;
    var jbpmExeId=info.jbpmExeId;
    var bs=info.bs;
    var bss=parseInt(bs);

/*    var nn = num+1;
    $(".part_ul li").eq(num).addClass("step"+nn+"s");
    $(".part_ul li").eq(num).addClass("selects");
    $(".formInfo").removeClass("select");
    $(".formInfo").eq(num).addClass("select");
    qh();*/
    if(num<=bss){
        var nn = num+1;
        $(".part_ul li").eq(num).addClass("step"+nn+"s");
        $(".part_ul li").eq(num).addClass("selects");
        $(".formInfo").removeClass("select");
        $(".formInfo").eq(num).addClass("select");
        qh();
	}else{
		switch (bss){
			case 0:
				submitHandler();
				break;
			case 1:
                submitHandler2(nodeKeys,jbpmExeId);
				break;
			case 2:
                submitHandler3(nodeKeys,jbpmExeId);
				break;
			case 3:
                submitHandler4(nodeKeys,jbpmExeId);
				break;
			case 4:
                submitHandler5(nodeKeys,jbpmExeId);
				break;
		}
	}
}
function prestep(num){
	var index = num-1;
	var nn = num+1;
	$(".part_ul li").eq(num).removeClass("step"+nn+"s");
	$(".formInfo").removeClass("select");
	$(".formInfo").eq(index).addClass("select");
}
function delfile(obj){
	$(obj).prev("span").html("");
	$(obj).parent().hide();
	$(obj).parent().prev(".fileinput").css("display","inline-block");
	$(obj).parent().prev(".fileinput").val("");
	$(obj).parent().prev(".fileinput").prev(".input_upload").show();
}
//视频上传
function Upload(obj,ids){
	var filextension = obj.value.substring(obj.value.lastIndexOf("."),obj.value.length);
	filextension = filextension.toLowerCase();
	if((filextension!='.mp4')&&(filextension!='.avi')&&(filextension!='.rmvb')&&(filextension!='.rm')&&(filextension!='.3gp')&&(filextension!='.mkv')){	
		alert('请添加正确的视频格式')
			obj.focus();
	}else{
			var path;
			var html = "";
		var newUpload = "";
		//IE 
		if(document.all){
			obj.select();
			var path = document.selection.createRange().text;
			var filextensions = obj.value.substring(obj.value.lastIndexOf("."),12);
	//                  document.getElementById("video_content").innerHTML="";
	//                  document.getElementsByClassName("video").style.filter = "progid:DXImageTransform.Microsoft.AlphaImageLoader(enabled='true',sizingMethod='scale',src=\"" + path + "\")";//使用滤镜效果
			html += "<div class='item'>";
			html +='<div class="imgTop">';
			html += "<img class='video'  src='/portal/union/images/person_img.jpg'/>";
			html += '</div>';
			html += '<video src= "'+path+'"></video>';
			html += "<p>"+filextensions+"</p>";
			html += "</div>";	
			/* newUpload += '<div class="fl-upload fl ContinueAdd" id="fl-upload ">';
			newUpload += '<img src="assets/images/addimg.png" class="imgs" id="ImgPr"/>';
			newUpload += '<p>继续添加视频</p>';
			newUpload += '<input type="file" multiple="multiple" onchange="Upload(this)"/>';
			newUpload += '</div>';	 */						  
			$(".fl-upload").hide();
			$(".newUpload").hide();
			$("#video_content").append(html);/*+newUpload*/
		}else{
			//获取文件的长度
			var ImgLenght = obj.files.length;
			var html = "";
			var newUpload = "";
			for(var i=0; i<ImgLenght; i++){
				//解析每个文件的路径
				var URls = window.URL.createObjectURL(obj.files[i]);
				var name = obj.files[i].name.split(".");
				html += "<div class='item'>";
				html += "<video class='video' src="+URls+" ></video>";
				html += "<p>"+sub(name[0],9)+"</p>";
				html += "<img onclick='removesp(this)' src='/portal/union/images/delicon.png' class='spdelete'></img>";
				html += "</div>";
			}
				/* newUpload += '<div class="fl-upload fl ContinueAdd" id="fl-upload ">';
				newUpload += '<img src="assets/images/addimg.png" class="imgs" id="ImgPr"/>';
				newUpload += '<p>继续添加视频</p>';
				newUpload += '<input type="file" multiple="multiple" onchange="Upload(this)"/>';
				newUpload += '</div>';		 */					  
				$(".fl-upload").hide();
				$(".newUpload").hide();
				$(".video_content").append(html);/*+newUpload*/
			}
			//调用上传文件函数
			if(ids.indexOf("_")>0){
				var idss=ids.split("_");
				var dd=doupload(idss[1]);
				if(dd.code==1){
					layer.msg(dd.msg);
				}else{
					$("#"+ids).val(JSON.stringify(dd));
				}
			}
			//doupload(ids);
		}
}
//截取中英文字符串
function sub(str,n){ 
  var r=/[^\x00-\xff]/g; 
	if(str.replace(r,"mm").length<=n){return str;} 
	    var m=Math.floor(n/2); 
	for(var i=m;i<str.length;i++){ 
	   if(str.substr(0,i).replace(r,"mm").length>=n){ 
	    return str.substr(0,i)+"..."; 
		} 
	} 
	return str;  
}
function removesp(obj) {
	$(obj).parent().remove();
}
