var reportGlobal = {
		id:'',//问卷id
		ctx:'',//context root
		jsondata:{},//问卷数据
		cycle:'month',//环比周期
		startDate:'',
		endDate:'',
		samplefilter:{
			cycle:'month',
			period:{
				start_time:'',end_time:''
			}
		}
};
//
//POV.labelTop = {normal : {label : {show : true,position : 'center',formatter : '{b}',textStyle: {baseline : 'bottom'}},labelLine : {show : false}}};POV
//POV.labelFromatter = {normal : {label : {formatter : function (params){return 100 - params.value + '%'},textStyle: {baseline : 'top'}}}};
//POV.labelBottom = {normal : {color: '#ccc',label : {show : true,position : 'center'},labelLine : {show : false}},emphasis: {color: 'rgba(0,0,0,0)'}};

reportGlobal.initSumReport = function(){
	reportGlobal.initSumReportUI();
	var url ="";
	var postdata = {};
	url = reportGlobal.ctx+'/page/proreport/queryTemplateById.json';
	postdata = {id:reportGlobal.id};
	$.ajax({
		type : 'post',
		url : url,
		data : postdata,
		dataType : 'json',
		cache : false,
		success : function(data) {
			reportGlobal.jsondata = data.data;
			load_reportpage();
		}
	});
}

reportGlobal.initSumReportUI = function(){

	var d = new Date()
	$('input[name="end"]').datepicker({
	  format: 'yyyy/mm',  
	         autoclose: true,  
	         startView: 1,
	         endDate:d.getFullYear() + '/' + (d.getMonth()+1),
	         minViewMode: 1,  
	         forceParse: true
	}).on('changeMonth', function(e) {
	  reportGlobal.endDate = e.date;
	  reportGlobal.samplefilter.period.end_time = reportGlobal.endDate;
	  $('input[name="start"]').datepicker('setEndDate',e.date.getFullYear() + '/' + (e.date.getMonth()+1));
	  load_reportpage();
	});
	$('input[name="start"]').datepicker({
	  format: 'yyyy/mm',  
	         autoclose: true,  
	         startView: 1,
	         endDate:d.getFullYear() + '/' + (d.getMonth()+1),
	         minViewMode: 1,  
	         forceParse: true
	}).on('changeMonth', function(e) {
	  reportGlobal.startDate = e.date;
	  reportGlobal.samplefilter.period.start_time = reportGlobal.startDate; 
	    $('input[name="end"]').datepicker('setStartDate',e.date.getFullYear() + '/' + (e.date.getMonth()+1));
	    load_reportpage();
	})	
		var cdate = new Date();
		cdate.setMonth(cdate.getMonth() -5);
	    $( 'input[name="start"]' ).datepicker( "setDate", cdate.getFullYear() + '/' + (cdate.getMonth()+1));
	    $( 'input[name="end"]' ).datepicker( "setDate", d.getFullYear() + '/' + (d.getMonth()+1));
	    $('#showmenu').selectmenu({
	   	  change: function( event, ui ) {
	   		reportGlobal.samplefilter.cycle = $(this).val();
	   		load_reportpage();
	   	  }
	   });
	    $('#storemenu').selectmenu({
		   	  change: function( event, ui ) {
		   		load_storeCard();
		   	  }
		 });
	    $('#satifymenu').selectmenu({
		   	  change: function( event, ui ) {
		   		load_storeCard();
		   	  }
		   });
		reportGlobal.startDate = $( 'input[name="start"]' ).datepicker( "getDate").format('yyyy/MM/dd');
		reportGlobal.endDate = $( 'input[name="end"]' ).datepicker( "getDate").format('yyyy/MM/dd');
		reportGlobal.samplefilter.period.start_time = reportGlobal.startDate;
		reportGlobal.samplefilter.period.end_time = reportGlobal.endDate;
		reportGlobal.samplefilter.cycle = $('#showmenu').val();
		
}

function load_reportpage(){
	drawStoreCard('.storeCardChart');
	loadPage('Summary',"groupOverview");
	loadPage('SummaryNPS',"groupOverview");
	loadPage('SummaryHEALTH',"groupOverview");	
	loadPage('DetailedItem',"groupDriver");
	loadPage('Services',"groupStandard");
}

function loadPage(page,businessType) {
	var data = reportGlobal.jsondata.questions.sort(function (a, b) {return a.sortNumber - b.sortNumber;});;
	var qGroup  = reportGlobal.jsondata.questionGroup.sort(function (a, b) {return a.sortNumber - b.sortNumber;});
	var qid = '';
	var qstGrup = [];
	var groupId = "";
 	
	//qustSatisfaction,qustRepurchase,qustRecommendation,qustCustomerHealth
	if (page == 'Summary'){
		qustBusinessType = 'qustSatisfaction';
	}else	if (page == 'SummaryNPS'){
		qustBusinessType = 'qustRecommendation';
	}else	if (page == 'SummaryHEALTH'){
		qustBusinessType = 'qustCustomerHealth';
	}else	if (page == 'DetailedItem'){
		qustBusinessType = 'qustSatisfaction';
	}	if (page == 'Services'){
		qustBusinessType = 'qustSatisfaction';
	}
	for (var i = 0; i < qGroup.length; i++) {
		if(businessType == qGroup[i].businessType){
			groupId = qGroup[i].questionGroupId;			
		}
	}

	for (var i = 0; i < data.length; i++) {
		if (groupId == data[i].questionGroupId && data[i].activeFlag) {
			var qst = {};
			qst.questionGroupId = groupId;
			qst.questionId = data[i].questionId;
			qst.questionValue = data[i].questionValue;
			qst.questionName = data[i].questionName;
			qst.sortNumber = data[i].sortNumber;
			qstGrup.push(qst);
			if(qustBusinessType == data[i].businessType){
				qid = data[i].questionId;
			}
		}
	}
	// console.log(qstGrup);
	var postdata = {questionnaire_id:reportGlobal.id,question_id:qid,
			page:page,filter:JSON.stringify(reportGlobal.samplefilter),questionGroup:JSON.stringify(qstGrup),groupId:groupId};
	$.ajax({
        type: 'post',
        url: reportGlobal.ctx+'/page/reportEchart/getChartSummaryInfo.json',
        data: postdata,
        dataType: 'json',
	    cache: false,
	    success: function (data) {
	    	if("Summary"==page){
	    		drawSummaryCharts(data.data);
	    	}else if("DetailedItem"==page){
	    		drawDetailedItemCharts(data.data);
	    	}else if("Services"==page){
	    		drawServicesCharts(data.data);
	    	}else if("SummaryNPS"==page){
	    		drawSummaryNPSCharts(data.data);
	    	}else if("SummaryHEALTH"==page){
	    		drawSummaryHEALTHCharts(data.data);
	    	}			
	    },
	    error: function(XMLHttpRequest, textStatus, errorThrown) {
            console.log(XMLHttpRequest.status);
            console.log(XMLHttpRequest.readyState);
            console.log(textStatus);
        }
	});

	//eval('load_'+page+'()');
	/*
	switch(page) {

	}*/
}

function drawDetailedItemCharts(jsondata) {
	jsondata = jsondata.data;
	var tableStr ="" 
	$('.detailedItemTable tr:not(:eq(0))').remove();
	if(jsondata && jsondata.length>=1){	
		for(var i=0;i<jsondata.length;i++){
			var newRow = '<tr><td>'+jsondata[i].name+'</td><td><div style="height:10px;width:'+jsondata[i].pct*0.8+'%;background-color:#C4D99E;float:left" title="'+jsondata[i].pct+'%"></div><span class="'+(jsondata[i].trend>0?'arrow-increase':(jsondata[i].trend<0?'arrow-decrease':'equal'))+'"></span></td></tr>';
			 $(".detailedItemTable tr:last").after(newRow);
		}						
	}else{
		$(".detailedItemTable tr:last").after('<tr><td colspan=2>暂无数据</td></tr>');
	}
}

function drawServicesCharts(jsondata) {
	jsondata = jsondata.data;
	var tableStr ="" 
	$('.servicesTable tr:not(:eq(0))').remove();
	if(jsondata && jsondata.length>=1){	
		for(var i=0;i<jsondata.length;i++){
			var newRow = '<tr><td>'+jsondata[i].name+'</td><td><div style="height:10px;width:'+jsondata[i].pct*0.8+'%;background-color:#C4D99E;float:left" title="'+jsondata[i].pct+'%"></div><span class="'+(jsondata[i].trend>0?'arrow-increase':(jsondata[i].trend<0?'arrow-decrease':'equal'))+'"></span></td></tr>';
			 $(".servicesTable tr:last").after(newRow);
		}						
	}else{
		$(".servicesTable tr:last").after('<tr><td colspan=2>暂无数据</td></tr>');
	}
}

function drawSummaryCharts(jsondata) {	
	if(jsondata.option){
		$('.summarynodata').hide();
		$('.summarydata').show();
		var chart = echarts.init($('.overviewChart')[0]);
		chart.setOption((jsondata.option));	
		$('.overviewLegend').html(jsondata.chartlegend);		
	}
	if(jsondata.optionTrend){
		$('.summarynodata').hide();
		$('.summarydata').show();
		var chartTrend = echarts.init($('.overviewTrend')[0]);
		chartTrend.setOption((jsondata.optionTrend));	
	}
}

function drawSummaryNPSCharts(jsondata) {	
	if(jsondata.option){
		$('.summarynodata').hide();
		$('.summarydata').show();
		var chart = echarts.init($('.recommendChart')[0]);
		chart.setOption((jsondata.option));	
		$('.recommendLegend').html(jsondata.chartlegend);		
	}
	if(jsondata.optionTrend){
		$('.summarynodata').hide();
		$('.summarydata').show();
		var chartTrend = echarts.init($('.recommendTrend')[0]);
		chartTrend.setOption((jsondata.optionTrend));	
	}
}
function drawSummaryHEALTHCharts(jsondata) {	
	if(jsondata.option){
		$('.summarynodata').hide();
		$('.summarydata').show();
		var chart = echarts.init($('.healthChart')[0]);
		chart.setOption((jsondata.option));	
		$('.healthLegend').html(jsondata.chartlegend);		
	}
	if(jsondata.optionTrend){
		$('.summarynodata').hide();
		$('.summarydata').show();
		var chartTrend = echarts.init($('.healthTrend')[0]);
		chartTrend.setOption((jsondata.optionTrend));	
	}
}

function drawStoreCard(chart){
	var option = {
		    title : {
		        text: '',
		        subtext: ''
		    },
		    grid: {
		        left: '3%',
		        right: '7%',
		        bottom: '3%',
		        show:false,
		        containLabel: false
		    },
		    tooltip : {
		        trigger: 'axis',
		        showDelay : 0,
		        formatter : function (params) {
		            if (params.value.length > 1) {
		                return params.seriesName + ' :<br/>'
		                   + params.value[0] + ','
		                   + params.value[1] + '';
		            }
		            else {
		                return params.seriesName + ' :<br/>'
		                   + params.name + ' : '
		                   + params.value + ' ';
		            }
		        },
		        axisPointer:{
		            show: true,
		            type : 'cross',
		            lineStyle: {
		                type : 'dashed',
		                width : 1
		            }
		        }
		    },
		    xAxis : [
		        {
		            type : 'value',
		            scale:true,
		            axisLine:{
		                show:false
		            },
		           axisTick:{
		                show:false
		            },             
		            axisLabel : {
		                show:false
		            },
		            splitLine: {
		               show: false
		            }
		        }
		    ],
		    yAxis : [
		        {
		            type : 'value',
		            scale:true,
		            axisLine:{
		                show:false
		            },
		           axisTick:{
		                show:false
		            },            
		            axisLabel : {
		                show:false
		            },
		            splitLine: {
		               show: false
		            }
		        }
		    ],
		    series : [
		        {
		            name:'分店组',
		            type:'scatter',
		            data: [[-71.2, 51.6], [-77.5, 59.0], [59.5,-49.2], [57.0, 63.0], [55.8, 53.6],
		                [-40.0, 59.0], [59.1,-47.6], [-76.0, 69.8], [-46.2, 66.8], [-70.2, 75.2],
		                [-42.5, 55.2], [-40.9, 54.2], [-42.9, 62.5], [53.4,-42.0], [-70.0, 50.0],
		                [47.2,-49.8], [-78.2,-49.2], [-45.0, 73.2], [57.0,-47.8], [-77.6, 68.8],[-44.0, 65.6], [-45.3, 71.8], [33.5, -20.7], [86.5, 72.6], [87.2, 78.8],
		                [81.5, 74.8], [84.0, -26.4], [84.5, 78.4], [-45.0, 62.0], [84.0, -21.6],
		                [80.0, 76.6], [-47.8, -23.6], [32.0, 90.0], [-46.0, 74.6], [-44.0, 71.0],
		                [84.0, 79.6], [32.7, 93.8], [-41.5, 70.0], [-43.0, 72.4], [-46.0, -25.9]
		            ],
		            itemStyle :{
		            	normal:{color:'#41a8d9'}
		            },
		            markLine : {
		                data : [
		                    { xAxis: 0 },
		                    { yAxis: 0 }
		                ]
		            }
		        }
		    ]
		};
	var chartTrend = echarts.init($(chart)[0]);
	chartTrend.setOption(option);	

}

function zoomCard(handle){	
	var dialog =  $(".cardDialog").dialog({
        autoOpen: false,
        height: 500,
        width: $(handle).parent().parent().width()*2,
        modal: true,
        title:$(handle).parent().text().trim(),	        
        close: function() {
        	$( ".cardDialog" ).dialog('destroy')
        }
      });
	
	$(".cardDialog").html($(handle).parent().next().html().trim());
	dialog.dialog( "open" );
	if($(handle).parent().parent().attr('class').indexOf('storeoverview')>-1){
		$('.cardDialog .storeCardChart').height($('.cardDialog .storeCardChart').height()*1.5)
		drawStoreCard('.cardDialog .storeCardChart');
	}
    
}

function load_storemetrics() {
	$.ajax({
        type: 'post',
        url: reportGlobal.ctx+'/assets/report/testdata-sum-store.js',
        data: JSON.stringify(''),
        dataType: 'json',
	    headers: {
	        Accept: "application/json",
	        "Content-Type": "application/json"
	    },
	    processData: false,
	    cache: false,
	    success: function (data) {
	    	drawStoreMetrics(data);
	    },
	    error: function(XMLHttpRequest, textStatus, errorThrown) {
            console.log(XMLHttpRequest.status);
            console.log(XMLHttpRequest.readyState);
            console.log(textStatus);
        }
	});
}


function drawStoreMetrics(jsondata) {
	var legend = [];
	var randata = [];
	for(var i = 0; i < jsondata.data.length; i++) {
		legend.push(jsondata.data[i].name);
		randata.push([jsondata.data[i].OS,jsondata.data[i].NPS, jsondata.data[i].HI]);
	}
	
	

		// 基于准备好的dom，初始化echarts图表
		var chart = ec.init($(".p4 .chart-main")[0]);
		
		option = {
			    tooltip : {
			        trigger: 'axis',
			        showDelay : 0,
			        axisPointer:{
			            show: true,
			            type : 'cross',
			            lineStyle: {
			                type : 'dashed',
			                width : 1
			            }
			        }
			    },
			    legend: {
			        data:legend
			    },
			    toolbox: {
			        show : false,
			        feature : {
			            mark : {show: true},
			            dataZoom : {show: true},
			            dataView : {show: true, readOnly: false},
			            restore : {show: true},
			            saveAsImage : {show: true}
			        }
			    },
			    xAxis : [
			        {
			            type : 'value',
			            splitNumber: 4,
			            scale: true
			        }
			    ],
			    yAxis : [
			        {
			            type : 'value',
			            splitNumber: 4,
			            scale: true
			        }
			    ],
			    series : [
			        {
			            name:'scatter1',
			            type:'scatter',
			            symbolSize: function (value){
			                return Math.round(value[2] / 5);
			            },
			            data: randata
			        },
			        {
			            name:'scatter2',
			            type:'scatter',
			            symbolSize: function (value){
			                return Math.round(value[2] / 5);
			            },
			            data: randata
			        }
			    ]
			};		

		chart.setOption(option);
	
}


/*
函数：格式化日期
参数：formatStr-格式化字符串
d：将日显示为不带前导零的数字，如1
dd：将日显示为带前导零的数字，如01
ddd：将日显示为缩写形式，如Sun
dddd：将日显示为全名，如Sunday
M：将月份显示为不带前导零的数字，如一月显示为1
MM：将月份显示为带前导零的数字，如01
MMM：将月份显示为缩写形式，如Jan
MMMM：将月份显示为完整月份名，如January
yy：以两位数字格式显示年份
yyyy：以四位数字格式显示年份
h：使用12小时制将小时显示为不带前导零的数字，注意||的用法
hh：使用12小时制将小时显示为带前导零的数字
H：使用24小时制将小时显示为不带前导零的数字
HH：使用24小时制将小时显示为带前导零的数字
m：将分钟显示为不带前导零的数字
mm：将分钟显示为带前导零的数字
s：将秒显示为不带前导零的数字
ss：将秒显示为带前导零的数字
l：将毫秒显示为不带前导零的数字
ll：将毫秒显示为带前导零的数字
tt：显示am/pm
TT：显示AM/PM
返回：格式化后的日期
*/
Date.prototype.format = function (formatStr) {
    var date = this;
    /*
    函数：填充0字符
    参数：value-需要填充的字符串, length-总长度
    返回：填充后的字符串
    */
    var zeroize = function (value, length) {
        if (!length) {
            length = 2;
        }
        value = new String(value);
        for (var i = 0, zeros = ''; i < (length - value.length); i++) {
            zeros += '0';
        }
            return zeros + value;
    };
    return formatStr.replace(/"[^"]*"|'[^']*'|\b(?:d{1,4}|M{1,4}|yy(?:yy)?|([hHmstT])\1?|[lLZ])\b/g, function($0) {
        switch ($0) {
            case 'd': return date.getDate();
            case 'dd': return zeroize(date.getDate());
            case 'ddd': return ['Sun', 'Mon', 'Tue', 'Wed', 'Thr', 'Fri', 'Sat'][date.getDay()];
            case 'dddd': return ['Sunday', 'Monday', 'Tuesday', 'Wednesday', 'Thursday', 'Friday', 'Saturday'][date.getDay()];
            case 'M': return date.getMonth() + 1;
            case 'MM': return zeroize(date.getMonth() + 1);
            case 'MMM': return ['Jan', 'Feb', 'Mar', 'Apr', 'May', 'Jun', 'Jul', 'Aug', 'Sep', 'Oct', 'Nov', 'Dec'][date.getMonth()];
            case 'MMMM': return ['January', 'February', 'March', 'April', 'May', 'June', 'July', 'August', 'September', 'October', 'November', 'December'][date.getMonth()];
            case 'yy': return new String(date.getFullYear()).substr(2);
            case 'yyyy': return date.getFullYear();
            case 'h': return date.getHours() % 12 || 12;
            case 'hh': return zeroize(date.getHours() % 12 || 12);
            case 'H': return date.getHours();
            case 'HH': return zeroize(date.getHours());
            case 'm': return date.getMinutes();
            case 'mm': return zeroize(date.getMinutes());
            case 's': return date.getSeconds();
            case 'ss': return zeroize(date.getSeconds());
            case 'l': return date.getMilliseconds();
            case 'll': return zeroize(date.getMilliseconds());
            case 'tt': return date.getHours() < 12 ? 'am' : 'pm';
            case 'TT': return date.getHours() < 12 ? 'AM' : 'PM';
        }
    });
}