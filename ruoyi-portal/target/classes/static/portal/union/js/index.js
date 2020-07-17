function tabs(obj,id){
	$(".xw-con-tab span").removeClass("select");
	$(".xw-con .new-list").removeClass("active");
	$("#newList"+id).addClass("active");
	$(obj).addClass("select");
	if(id==1){
		$("#zgh").show();
		$("#jcgh").hide();
	}else{
		$("#zgh").hide();
		$("#jcgh").show();
	}
}
$(function(){
	var data1 = [{value:100, name:'总人数'},{value:35, name:'综改区工会人数'}];
	pieCharts("pie1",data1);
	pieCharts("pie2",data1);
	pieCharts("pie3",data1);
	pieCharts("pie4",data1);
});

