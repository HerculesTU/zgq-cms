function pieCharts(id,data){
	var myChart = echarts.init(document.getElementById(id));
	var option = {
	    color: ['#c52726','#eeeeee'],
	    series : [
	        {
	            type:'pie',
	            radius : [55, 63],
	            data:data,
	            label: {show:false}
	        }
	    ]
	};
	myChart.setOption(option);
}
