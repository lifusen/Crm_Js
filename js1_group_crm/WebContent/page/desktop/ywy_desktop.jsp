<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<link href="css/jdy.css" rel="stylesheet" type="text/css" media="screen"/>
<!-- BEGIN PAGE CONTAINER-->
<div class="container-fluid p-t-20 desktopContainer">
		
	<!--开始：第1行 -->
	<div class="row-fluid">
		<!--开始：第1行第1列 -->
		<div class="span5">
			<!--开始：第1行第1列中的盒子-->
			<div class="portlet box blue">
				<div class="portlet-title">
					<div class="caption"><i class="icon-cogs"></i>今日提醒</div>
					<div class="pull-right white-label">日</div>
				</div>
				<!--开始：第1行第1列中的盒子body-->
				<div class="portlet-body" style="padding-bottom: 1px;" id="todayRemindDataArea">
					<jsp:include page="../common/loadDataProgress.jsp"/>
				</div>										
				<!--结束：第1行第1列中的盒子的body-->
			</div>
			<div class="portlet box blue" >
				<div class="portlet-title">
					<div class="caption"><i class="icon-cogs"></i>签单转换率</div>
					<div class="pull-right white-label">月</div>
				</div>
				<!--开始：第1行第2列中的盒子body-->
				<div class="portlet-body" style="padding-bottom: 0px;" id="signRankingDataArea">
					
				</div>
				<!--结束：第1行第2列中的盒子的body-->
			</div>
			<!--结束：第1行第1列中的盒子-->
		</div>
		<!--结束：第1行第1列 -->
		<!--开始：第1行第3列 -->
		<div class="span7">
			<!--开始：第1行第3列中的盒子-->
			<div class="portlet box blue tabbable">
				<div class="portlet-title">
					<div class="caption"><i class="icon-reorder"></i></div>
				</div>
				<!--开始：第1行第3列中的盒子body-->							
				<div class="portlet-body  t-g" style="min-height:435px;">
					<div class="tabbable portlet-tabs" id="ywyMeaageLoad">
						
					</div>
				</div>
				<!--结束：第1行第3列中的盒子的body-->
			</div>								
			<!-- 结束：第1行第3列中的盒子-->
		</div>							
		<!--结束：第1行第3列 -->
	</div>
	<!--结束：第1行 -->
	
</div>
<!-- END PAGE CONTAINER--> 


<script>
$(function(){
	//加载每日提醒区域
	$('#todayRemindDataArea').load('desktop/getTodayRemindData.do');
	//加载签单转化率区域
	$('#signRankingDataArea').load('desktop/getSignRankingData.do');
});

jQuery(document).ready(function() {    

	//App.init(); // 初始化页面的布局，并且加载基本组件					

	$('.easy-pie-chart div').easyPieChart({
              animate: 1000,
              size: 90,
              lineWidth: 10,
              barColor: "#019FC2"
          });
	
	$("[id^='menu-item-']").click(function(){
		$("[id^='menu-item-']").removeClass("active");
		$(this).addClass("active");
		$("[id^='menu-item-']").find("a span[class='selected']").remove();
		$("[id^='menu-item-']").find("a span[class^='arrow']").removeClass("open");
		$(this).find("a").append("<span class=\"selected\"></span>");
		$(this).find("a span[class^='arrow']").addClass("open");
	});
	
	$("#ywyMeaageLoad").load("message/getDesktopList.do");
});
</script>