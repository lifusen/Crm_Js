<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<link href="css/jdy.css" rel="stylesheet" type="text/css" media="screen"/>
<!-- BEGIN PAGE CONTAINER-->
<div class="container-fluid desktopContainer">
		
	<!--开始：第1行 -->
	<div class="row-fluid">
		<!--开始：第1行第1列 -->
		<div class="span6">
			<!--开始：第1行第1列中的盒子-->
			<div class="portlet box blue">
				<div class="portlet-title">
					<div class="caption"><i class="icon-bar-chart"></i>每日基础工作数据汇总</div>
				</div>
				<!--开始：第1行第1列中的盒子body-->
				<div class="portlet-body">		
					<div id="dailyBasicDataArea">
						<jsp:include page="../common/loadDataProgress.jsp"/>
					</div>
					
				</div>										
				<!--结束：第1行第1列中的盒子的body-->
			</div>
			<!--结束：第1行第1列中的盒子-->
		</div>
		<!--结束：第1行第1列 -->
		
		<!--开始：第1行第2列 -->
		<div class="span6">
			<!--开始：第1行第2列中的盒子-->
			<div class="portlet box blue" >
				<div class="portlet-title">
					<div class="caption"><i class="icon-bar-chart"></i>上门/签单进度汇总</div>
					<div class="pull-right"><span id="lblMonth" class="white-label  c-p">月</span><span id="lblDay" class="blue-label c-p">日</span></div>
				</div>
				<!--开始：第1行第2列中的盒子body-->
				<div class="portlet-body t-g">	
					
					<div id="visitOrSignDataArea">
						<jsp:include page="../common/loadDataProgress.jsp"/>
					</div>
					
				</div>
				<!--结束：第1行第2列中的盒子的body-->
			</div>
			<!-- 结束：第1行第2列中的盒子-->
		</div>
		<!--结束：第1行第2列 -->			
	</div>
	<!--结束：第1行 -->

	<!--开始：第2行 -->
	<div class="row-fluid">
		<div class="span3">
			<!--开始：第2行第1列中的盒子 -->
			<div class="portlet box blue tabbable">
				<div class="portlet-title">
					<div class="caption"><i class="icon-envelope"></i></div>
				</div>
				<!--开始：第2行第1列中的盒子body-->	
				<div id="desktopMessageArea">
					<jsp:include page="../common/loadDataProgress.jsp"/>
				</div>						
				<!--结束：第2行第1列中的盒子的body-->
			</div>								
			<!--结束：第2行第1列中的盒子 -->						
		</div>					
		<!--开始：第2行第2列 -->
		<div class="span5" style="">
			<!--开始：第2行第2列中的盒子-->
			<div class="portlet box blue">
				<div class="portlet-title">
					<div class="caption"><i class="icon-bar-chart"></i>收费数据汇总</div>
					<div class="pull-right"><span class="white-label  c-p" id="lblTotal">总</span><span id="lblMonth2" class="blue-label c-p">月</span></div>
				</div>
				<!--开始：第2行第2列中的盒子body-->
				<div class="portlet-body" id="chargeDataArea">
					<jsp:include page="../common/loadDataProgress.jsp"/>
				</div>										
				<!--结束：第2行第2列中的盒子的body-->
			</div>
			<!--结束：第2行第2列中的盒子-->
		</div>
		<!--结束：第2行第2列 -->
		
		<!--开始：第2行第3列 -->
		<div class="span4" style="">
			<!--开始：第2行第3列中的盒子-->
			<div class="portlet box blue">
				<div class="portlet-title">
					<div class="caption"><i class="icon-bar-chart"></i>收款数据汇总</div>
					<div class="pull-right"><span class="white-label  c-p" id="lblTotal3">总</span><span id="lblMonth3" class="blue-label c-p">月</span></div>
				</div>
				<!--开始：第2行第3列中的盒子body-->
				<div class="portlet-body" id="moneyDataArea">		
					<jsp:include page="../common/loadDataProgress.jsp"/>
				</div>										
				<!--结束：第2行第3列中的盒子的body-->
			</div>
			<!--结束：第2行第3列中的盒子-->
		</div>
		<!--结束：第2行第3列 -->
	</div>
	<!--结束：第2行 -->
	
	<div style="padding:20px;">&nbsp;</div>
</div>
<!-- END PAGE CONTAINER--> 

<script type="text/javascript">
	//加载消息区域
	$('#desktopMessageArea').load('message/getDesktopList.do');
	//加载每日基础数据汇总
	$('#dailyBasicDataArea').load('desktop/getDailyBasicData.do');
	//加载上门签单进度汇总
	$('#visitOrSignDataArea').load('desktop/getVisitOrSignData.do?type=0');
	// 加载收费数据
	$('#chargeDataArea').load('desktop/getChargeData.do');
	// 加载收款数据
	$('#moneyDataArea').load('desktop/getMoneyData.do');
	
</script>

<script>
	jQuery(document).ready(function() {    
	
		//App.init(); // 初始化页面的布局，并且加载基本组件					
		
		$("[id^='menu-item-']").click(function(){
			$("[id^='menu-item-']").removeClass("active");
			$(this).addClass("active");
			$("[id^='menu-item-']").find("a span[class='selected']").remove();
			$("[id^='menu-item-']").find("a span[class^='arrow']").removeClass("open");
			$(this).find("a").append("<span class=\"selected\"></span>");
			$(this).find("a span[class^='arrow']").addClass("open");
		});
		
		$(".easy-pie-chart div[class='t-lb']").easyPieChart({
               animate: 1000,
               size: 90,
               lineWidth: 10,
               barColor: "#019FC2"
           });
		
		$(".easy-pie-chart div[class='t-o']").easyPieChart({
               animate: 1000,
               size: 90,
               lineWidth: 10,
               barColor: "#DA521E"
           });

		jdyTab("lblMonth", "lblDay", "smqdhz-month", "smqdhz-day");
		jdyTab("lblTotal", "lblMonth2", "sfhz-total", "sfhz-month");
		jdyTab("lblTotal3", "lblMonth3", "skhz-month", "skhz-day");
		
		
		
		$("#lblMonth").click(function(){
			$('#visitOrSignDataArea').load('desktop/getVisitOrSignData.do?type=0');
		});
		$("#lblDay").click(function(){
			$('#visitOrSignDataArea').load('desktop/getVisitOrSignData.do?type=1');
		});
		
		
		$("#lblMonth2").click(function(){
			$('#chargeDataArea').load('desktop/getChargeData.do?totalOrMonth=month');
		});
		$("#lblTotal").click(function(){
			$('#chargeDataArea').load('desktop/getChargeData.do?totalOrMonth=total');
		});
		$("#lblTotal3").click(function(){
			$('#moneyDataArea').load('desktop/getMoneyData.do?totalOrMonth=total');
		});
		$("#lblMonth3").click(function(){
			$('#moneyDataArea').load('desktop/getMoneyData.do?totalOrMonth=month');
		});
	});
	
	function jdyTab(tab1, tab2, tabContent1, tabContent2) {
		$("#" + tab1).on("click", function(){
			$("#" + tabContent2).hide();
			$("#" + tabContent1).show();
			$(this).removeClass("blue-label").addClass("white-label");
			$("#" + tab2).removeClass("white-label").addClass("blue-label");
		});
		
		$("#" + tab2).on("click", function(){
			$("#" + tabContent1).hide();
			$("#" + tabContent2).show();
			$(this).removeClass("blue-label").addClass("white-label");
			$("#" + tab1).removeClass("white-label").addClass("blue-label");
		});
	}
</script>