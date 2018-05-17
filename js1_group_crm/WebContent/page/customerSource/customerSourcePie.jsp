<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!-- BEGIN PAGE CONTAINER-->
<div class="container-fluid p-t-20" style="padding-top:8px;">
	<div class="row-fluid">
		<div class="span12">
			<div class="portlet box blue">
				<div class="portlet-title">
					<div class="caption"><i class="icon-bar-chart"></i>渠道来源数量统计</div>
				</div>
				<div class="portlet-body">		
					
					<div id="customerSourceBtnGroup">
						<button type="button" class="btn blue" data-order="cs.id">按渠道来源录入时间顺序排列</button>
						<button type="button" class="btn" data-order="cs.id desc">按渠道来源录入时间倒序排列</button>
						<button type="button" class="btn" data-order="temp.total">按渠道数量顺序排列</button>
						<button type="button" class="btn" data-order="temp.total desc">按渠道数量倒序排列</button>
					</div>
					
					<!-- 为ECharts准备一个具备大小（宽高）的Dom -->
    				<div id="customerSourcePieContainer" style="height:650px">
    					
    				</div>
					
					<div id="customerSourceDataDiv" style="width:550px;margin:0px auto;display:none;">
						
						<h2>有客户来源的总客户数:<span id="customerSourceTotalCount"></span></h2>
						
						<table class="table table-striped table-hover table-bordered">
							<thead>
								<tr>
									<th width="60px;">序号</th>
									<th width="120px;">渠道名称</th>
									<th width="100px;">渠道数量</th>
									<th width="100px;">百分比</th>
								</tr>
							</thead>
							<tbody id="customerSourceDataTbody">
								
							</tbody>
						</table>			
					</div>
															
				</div>										
			</div>
		</div>
	</div>
</div>
<!-- END PAGE CONTAINER--> 

<script type="text/javascript" src="${pageContext.request.contextPath}/js/echarts-all.js"></script>
<script type="text/javascript">
	$("#customerSourceBtnGroup .btn").click(function(){
		$(this).addClass("blue").siblings().removeClass("blue");
		var order = $(this).attr("data-order");
		loadCustomerSourcePie(order);
	});
	$("#customerSourceBtnGroup .btn:first").click();


	//基于准备好的dom，初始化echarts图表
	var myChart = echarts.init(document.getElementById('customerSourcePieContainer')); 
	
	/**
	* 显示客户来源拼图
	*/
	function showCustomerSourcePie(names,datas,total){
		var option = {
				title : {
					text: '渠道来源数量统计',
					subtext: '有客户来源的总客户数:'+total,
					x:'center'
				},
				tooltip : {
					trigger: 'item',
					formatter: "{b}:{c}({d}%)"
				},
				legend: {
					orient : 'vertical',
					x : 'left',
					data:names
				},
				toolbox: {
					show : true,
					feature : {
						dataView : {show: true, readOnly: false},
						magicType : {
							show: true, 
							type: ['pie', 'funnel'],
							option: {
								funnel: {
									x: '25%',
									width: '50%',
									funnelAlign: 'left',
									max: 1548
								}
							}
						},
						restore : {show: true},
						saveAsImage : {show: true}
					}
				},
				calculable : true,
				series : [
					{
						name:'渠道来源数量',
						type:'pie',
						radius : '50%',
						center: ['50%', '60%'],
						data:datas,
						itemStyle:{ 
				            normal:{ 
				                  label:{ 
				                    show: true, 
				                    formatter: '{b} : {c} ({d}%)' 
				                  }, 
				                  labelLine :{show:true} 
				                } 
				            } 
					}
				]
			};
								
			
			// 为echarts对象加载数据 
			myChart.setOption(option); 
	}
	
	/**
	* 显示数据表格
	*/
	function showCustomerSourceTable(datas,total){
		$("#customerSourceDataTbody").empty();
		$("#customerSourceTotalCount").text(total);
		for(var i = 0; i < datas.length; i++){
			var data = datas[i];
			var value = data.value;
			var percent = (value / total * 100).toFixed(2) + '%';
			
			var tr = '<tr><td>'+(i+1)+'</td><td>'+data.name+'</td><td>'+value+'</td><td>'+percent+'</td></tr>';
			$(tr).appendTo($("#customerSourceDataTbody"));
		}
		$("#customerSourceDataDiv").show();
	}
	
	/**
	* AJAX加载客户来源饼图数据
	*/
	function loadCustomerSourcePie(order){
		$.messager.showLoadProgress();
		$.getJSON("${pageContext.request.contextPath}/customerSource/getCustomerSourcePie.do",{order:order},function(datas){
			$.messager.closeLoadProgress();
			var sourceNames = [];
			var total = 0;
			var values = [];
			for(var i = 0; i < datas.length; i++){
				var data = datas[i];
				sourceNames.push(data.name);
				var value = parseInt(data.value);
				total += value;
				values.push(data.value);
			}
			console.log(sourceNames);
			console.log(total);
			console.log(datas);
			console.log(values);
			showCustomerSourcePie(sourceNames,datas,total);
			showCustomerSourceTable(datas,total);
			
		});
	}
	
</script>