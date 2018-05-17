<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<link href="css/jdy.css" rel="stylesheet" type="text/css" media="screen"/>
<!-- BEGIN PAGE CONTAINER-->
<div class="container-fluid p-t-20 desktopContainer">
		
	<!--开始：第1行 -->
	<div class="row-fluid">
		<!--开始：第1行第1列 -->
		<div class="span4">
			<!--开始：第1行第1列中的盒子-->
			<div class="portlet box blue">
				<div class="portlet-title">
					<div class="caption"><i class="icon-cogs"></i>每日基础工作数据汇总</div>
				</div>
				<!--开始：第1行第1列中的盒子body-->
				<div class="portlet-body">		
					<table width="100%" class="no-border-table3 t-g small-font-container">
						<tr><td width="14%">部门</td><td width="15%">总提醒量</td><td width="15%">今日提醒</td><td width="14%">已完成</td><td width="14%">完成率</td><td width="14%">通话量</td><td width="14%">完成率</td></tr>
						<tr><td>1</td><td>1035</td><td>1035</td><td>1035</td><td>92.5%</td><td>1035</td><td>92.5%</td></tr>
						<tr><td>2</td><td>1035</td><td>1035</td><td>1035</td><td>92.5%</td><td>1035</td><td>92.5%</td></tr>
						<tr><td>3</td><td>1035</td><td>1035</td><td>1035</td><td>92.5%</td><td>1035</td><td>92.5%</td></tr>
						<tr><td>4</td><td>1035</td><td>1035</td><td>1035</td><td>92.5%</td><td>1035</td><td>92.5%</td></tr>
						<tr><td>5</td><td>1035</td><td>1035</td><td>1035</td><td>92.5%</td><td>1035</td><td>92.5%</td></tr>
						<tr><td>6</td><td>1035</td><td>1035</td><td>1035</td><td>92.5%</td><td>1035</td><td>92.5%</td></tr>
						<tr><td>7</td><td>1035</td><td>1035</td><td>1035</td><td>92.5%</td><td>1035</td><td>92.5%</td></tr>
						<tr><td>8</td><td>1035</td><td>1035</td><td>1035</td><td>92.5%</td><td>1035</td><td>92.5%</td></tr>
						<tr class="bg-g t-w"><td>平均</td><td>1035</td><td>1035</td><td>1035</td><td>92.5%</td><td>1035</td><td>92.5%</td></tr>
					</table>
				</div>										
				<!--结束：第1行第1列中的盒子的body-->
			</div>
			<!--结束：第1行第1列中的盒子-->
		</div>
		<!--结束：第1行第1列 -->
		
		<!--开始：第1行第2列 -->
		<div class="span8">
			<!--开始：第1行第2列中的盒子-->
			<div class="portlet box blue" >
				<div class="portlet-title">
					<div class="caption"><i class="icon-cogs"></i>上门/签单进度汇总</div>
					<div class="pull-right"><span class="white-label  c-p" id="lblMonth">月</span><span id="lblDay" class="blue-label c-p">日</span></div>
				</div>
				<!--开始：第1行第2列中的盒子body-->
				<div class="portlet-body t-g">	
					<table id="smqdhz-month" width="100%" class="no-border-table3 t-g">
						<tr><td width="12%">部门</td><td width="16%">目标上门</td><td width="12%">已上门</td><td width="12%">完成率</td><td width="12%">目标签单</td><td width="12%">已签单</td><td width="12%">完成率</td><td width="12%">转换率</td></tr>
						<tr><td>1</td><td>40</td><td>37</td><td>92.5%</td><td>40</td><td>37</td><td>92.5%</td><td>90%</td></tr>
						<tr><td>1</td><td>40</td><td>37</td><td>92.5%</td><td>40</td><td>37</td><td>92.5%</td><td>90%</td></tr>
						<tr><td>1</td><td>40</td><td>37</td><td>92.5%</td><td>40</td><td>37</td><td>92.5%</td><td>90%</td></tr>
						<tr><td>1</td><td>40</td><td>37</td><td>92.5%</td><td>40</td><td>37</td><td>92.5%</td><td>90%</td></tr>
						<tr><td>1</td><td>40</td><td>37</td><td>92.5%</td><td>40</td><td>37</td><td>92.5%</td><td>90%</td></tr>
						<tr><td>1</td><td>40</td><td>37</td><td>92.5%</td><td>40</td><td>37</td><td>92.5%</td><td>90%</td></tr>
						<tr><td>1</td><td>40</td><td>37</td><td>92.5%</td><td>40</td><td>37</td><td>92.5%</td><td>90%</td></tr>
						<tr><td>1</td><td>40</td><td>37</td><td>92.5%</td><td>40</td><td>37</td><td>92.5%</td><td>90%</td></tr>
						<tr class="bg-g t-w"><td>平均</td><td>40</td><td>37</td><td>92.5%</td><td>40</td><td>37</td><td>92.5%</td><td>90%</td></tr>
					</table>
					<table id="smqdhz-day" width="100%" class="no-border-table3 t-g">
						<tr><td width="12%">人员</td><td width="16%">目标上门</td><td width="12%">已上门</td><td width="12%">完成率</td><td width="12%">目标签单</td><td width="12%">已签单</td><td width="12%">完成率</td><td width="12%">转换率</td></tr>
						<tr><td>1</td><td>2</td><td>1</td><td>92.5%</td><td>40</td><td>37</td><td>92.5%</td><td>90%</td></tr>
						<tr><td>2</td><td>2</td><td>0</td><td>92.5%</td><td>40</td><td>37</td><td>92.5%</td><td>90%</td></tr>
						<tr><td>3</td><td>2</td><td>0</td><td>92.5%</td><td>40</td><td>37</td><td>92.5%</td><td>90%</td></tr>
						<tr><td>4</td><td>2</td><td>3</td><td>150.0%</td><td>40</td><td>37</td><td>92.5%</td><td>90%</td></tr>
						<tr><td>5</td><td>2</td><td>2</td><td>92.5%</td><td>40</td><td>37</td><td>92.5%</td><td>90%</td></tr>
						<tr><td>6</td><td>2</td><td>1</td><td>92.5%</td><td>40</td><td>37</td><td>92.5%</td><td>90%</td></tr>
						<tr><td>7</td><td>2</td><td>1</td><td>50.0%</td><td>40</td><td>37</td><td>92.5%</td><td>90%</td></tr>
						<tr><td>8</td><td>2</td><td>0</td><td>0.0%</td><td>40</td><td>37</td><td>92.5%</td><td>90%</td></tr>
						<tr class="bg-g t-w"><td>平均</td><td>40</td><td>37</td><td>92.5%</td><td>40</td><td>37</td><td>92.5%</td><td>90%</td></tr>
					</table>
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
		<div class="span4">
			<!--开始：第2行第1列中的盒子 -->
			<div class="portlet box blue tabbable">
				<div class="portlet-title">
					<div class="caption"><i class="icon-reorder"></i></div>
				</div>
				<!--开始：第2行第1列中的盒子body-->							
				<div class="portlet-body  t-g">
					<div class="tabbable portlet-tabs">
						<ul class="nav nav-tabs">
							<li  style="width:30%; text-algin: center;" ><a href="#portlet_tab3"data-toggle="tab">政策</a></li>
							<li style="width:30%; text-algin: center;"  ><a href="#portlet_tab2" data-toggle="tab">公告</a></li>
							<li class="active" style="width:30%;  text-algin: center;" ><a href="#portlet_tab1" data-toggle="tab">事件<span class="tip">5</span></a></li>
						</ul>
						<div class="tab-content">
							<div class="tab-pane active small-font-container" id="portlet_tab1" style="line-height: 130%;">									
								<p><span class="f-s fr">2015-1-27</span>周一业务员全体早迎接</p>		
								<p class="t-r"><span class="f-s fr">2015-1-27</span>周一业务员全体早迎接</p>		
								<p><span class="f-s fr">2015-1-27</span>周一业务员全体早迎接</p>		
								<p><span class="f-s fr">2015-1-27</span>周一业务员全体早迎接</p>		
								<p><span class="f-s fr">2015-1-27</span>周一业务员全体早迎接</p>		
								<p><span class="f-s fr">2015-1-27</span>周一业务员全体早迎接</p>		
								<p><span class="f-s fr">2015-1-27</span>周一业务员全体早迎接</p>		
								<p><span class="f-s fr">2015-1-27</span>周一业务员全体早迎接</p>		
								<p><span class="f-s fr">2015-1-27</span>周一业务员全体早迎接</p>			
								<p><span class="f-s fr">2015-1-27</span>周一业务员全体早迎接</p>		
								<p><span class="f-s fr">2015-1-27</span>周一业务员全体早迎接</p>														
							</div>
							<div class="tab-pane" id="portlet_tab2">
								<p>
									Lorem ipsum dolor sit amet, consetetur sadipsci
								</p>
							</div>
							<div class="tab-pane" id="portlet_tab3">
								<p>
									Ut wisi enim ad minim veni
								</p>												
							</div>
						</div>
					</div>
				</div>
				<!--结束：第2行第1列中的盒子的body-->
			</div>								
			<!--结束：第2行第1列中的盒子 -->						
		</div>					
		<!--开始：第2行第2列 -->
		<div class="span4">
			<!--开始：第2行第2列中的盒子-->
			<div class="portlet box blue">
				<div class="portlet-title">
					<div class="caption"><i class="icon-cogs"></i>收费数据汇总</div>
					<div class="pull-right"><span class="white-label  c-p" id="lblTotal">总</span><span id="lblMonth2" class="blue-label c-p">月</span></div>
				</div>
				<!--开始：第2行第2列中的盒子body-->
				<div class="portlet-body">		
					<table id="sfhz-total" width="100%" class="no-border-table3 t-g">
						<tr><td width="16%">部门</td><td width="16%">房贷</td><td width="16%">二贷</td><td width="16%">信抵</td><td width="20%">信用卡</td><td width="16%">车贷</td></tr>
						<tr><td>1</td><td>4.23%</td><td>4.23%</td><td>4.23%</td><td>4.23%</td><td>4.23%</td></tr>
						<tr><td>1</td><td>4.23%</td><td>4.23%</td><td>4.23%</td><td>4.23%</td><td>4.23%</td></tr>
						<tr><td>1</td><td>4.23%</td><td>4.23%</td><td>4.23%</td><td>4.23%</td><td>4.23%</td></tr>
						<tr><td>1</td><td>4.23%</td><td>4.23%</td><td>4.23%</td><td>4.23%</td><td>4.23%</td></tr>
						<tr><td>1</td><td>4.23%</td><td>4.23%</td><td>4.23%</td><td>4.23%</td><td>4.23%</td></tr>
						<tr><td>1</td><td>4.23%</td><td>4.23%</td><td>4.23%</td><td>4.23%</td><td>4.23%</td></tr>
						<tr><td>1</td><td>4.23%</td><td>4.23%</td><td>4.23%</td><td>4.23%</td><td>4.23%</td></tr>
						<tr><td>1</td><td>4.23%</td><td>4.23%</td><td>4.23%</td><td>4.23%</td><td>4.23%</td></tr>
						<tr class="bg-g t-w"><td>平均</td><td>4.23</td><td>4.23</td><td>4.23</td><td>4.23</td><td>4.23</td></tr>
					</table>
					<table id="sfhz-month" width="100%" class="no-border-table3 t-g">
						<tr><td width="16%">部门</td><td width="16%">房贷</td><td width="16%">二贷</td><td width="16%">信抵</td><td width="20%">信用卡</td><td width="16%">车贷</td></tr>
						<tr><td>1</td><td>4.24%</td><td>4.23%</td><td>4.23%</td><td>4.23%</td><td>4.23%</td></tr>
						<tr><td>2</td><td>4.25%</td><td>4.23%</td><td>4.23%</td><td>4.23%</td><td>4.23%</td></tr>
						<tr><td>3</td><td>4.21%</td><td>4.23%</td><td>4.23%</td><td>4.23%</td><td>4.23%</td></tr>
						<tr><td>4</td><td>4.23%</td><td>4.23%</td><td>4.23%</td><td>4.23%</td><td>4.23%</td></tr>
						<tr><td>5</td><td>4.23%</td><td>4.23%</td><td>4.23%</td><td>4.23%</td><td>4.23%</td></tr>
						<tr><td>6</td><td>4.23%</td><td>4.23%</td><td>4.23%</td><td>4.23%</td><td>4.23%</td></tr>
						<tr><td>7</td><td>4.23%</td><td>4.23%</td><td>4.23%</td><td>4.23%</td><td>4.23%</td></tr>
						<tr><td>8</td><td>4.23%</td><td>4.23%</td><td>4.23%</td><td>4.23%</td><td>4.23%</td></tr>
						<tr class="bg-g t-w"><td>平均</td><td>4.23</td><td>4.23</td><td>4.23</td><td>4.23</td><td>4.23</td></tr>
					</table>
				</div>										
				<!--结束：第2行第2列中的盒子的body-->
			</div>
			<!--结束：第2行第2列中的盒子-->
		</div>
		<!--结束：第2行第2列 -->
		
		<!--开始：第2行第3列 -->
		<div class="span4">
			<!--开始：第2行第3列中的盒子-->
			<div class="portlet box blue">
				<div class="portlet-title">
					<div class="caption"><i class="icon-cogs"></i>收款数据汇总</div>
					<div class="pull-right"><span class="white-label  c-p" id="lblMonth3">月</span><span id="lblDay3" class="blue-label c-p">日</span></div>
				</div>
				<!--开始：第2行第3列中的盒子body-->
				<div class="portlet-body">		
					<table id="skhz-month" width="100%" class="no-border-table3 t-g">
						<tr><td width="20%">部门</td><td width="20%">放款金额</td><td width="20%">目标业绩</td><td width="20%">净业绩</td><td width="20%">完成率</td></tr>
						<tr><td>1</td><td>40</td><td>20</td><td>20</td><td>90%</td></tr>
						<tr><td>1</td><td>40</td><td>20</td><td>20</td><td>90%</td></tr>
						<tr><td>1</td><td>40</td><td>20</td><td>20</td><td>90%</td></tr>
						<tr><td>1</td><td>40</td><td>20</td><td>20</td><td>90%</td></tr>
						<tr><td>1</td><td>40</td><td>20</td><td>20</td><td>90%</td></tr>
						<tr><td>1</td><td>40</td><td>20</td><td>20</td><td>90%</td></tr>
						<tr><td>1</td><td>40</td><td>20</td><td>20</td><td>90%</td></tr>
						<tr><td>1</td><td>40</td><td>20</td><td>20</td><td>90%</td></tr>
						<tr class="bg-g t-w"><td>平均</td><td>40</td><td>20</td><td>20</td><td>90%</td></tr>
					</table>
					<table id="skhz-day" width="100%" class="no-border-table3 t-g">
						<tr><td width="20%">人员</td><td width="20%">放款金额</td><td width="20%">目标业绩</td><td width="20%">净业绩</td><td width="20%">完成率</td></tr>
						<tr><td>1</td><td>10</td><td>20</td><td>20</td><td>90%</td></tr>
						<tr><td>2</td><td>10</td><td>20</td><td>20</td><td>90%</td></tr>
						<tr><td>3</td><td>10</td><td>20</td><td>20</td><td>90%</td></tr>
						<tr><td>4</td><td>40</td><td>20</td><td>20</td><td>90%</td></tr>
						<tr><td>5</td><td>40</td><td>20</td><td>20</td><td>90%</td></tr>
						<tr><td>6</td><td>40</td><td>20</td><td>20</td><td>90%</td></tr>
						<tr><td>7</td><td>40</td><td>20</td><td>20</td><td>90%</td></tr>
						<tr><td>8</td><td>40</td><td>20</td><td>20</td><td>90%</td></tr>
						<tr class="bg-g t-w"><td>平均</td><td>40</td><td>20</td><td>20</td><td>90%</td></tr>
					</table>
				</div>										
				<!--结束：第2行第3列中的盒子的body-->
			</div>
			<!--结束：第2行第3列中的盒子-->
		</div>
		<!--结束：第2行第3列 -->
	</div>
	<!--结束：第2行 -->
	
	<!--开始：第3行 -->
	<div class="row-fluid portlet blue">
		<div class="span2">
			<div class="t-w text-center t-20" style="line-height: 60px;">
				部门经理桌面
			</div>
		</div>
		<div class="span8">
			<table width="100%" class="t-w text-center" style="line-height: 200%;">
				<tr><td width="12.5%" class="b-lb-w">业务一部</td><td width="12.5%" class="b-lb-w">业务二部</td><td width="12.5%" class="b-lb-w">业务三部</td><td width="12.5%" class="b-lb-w">业务四部</td><td width="12.5%" class="b-lb-w"></td><td width="12.5%" class="b-lb-w"></td><td width="12.5%" class="b-lb-w"></td><td width="12.5%" class="b-lb-w b-r-w"></td></tr>
				<tr><td class="b-l-w">李开拓</td><td class="b-l-w">郭红军</td><td class="b-l-w">李豪</td><td class="b-l-w">王瑶</td><td class="b-l-w"></td><td class="b-l-w"></td><td class="b-l-w"></td><td class="b-l-w  b-r-w"></td></tr>
			</table>
		</div>
		<div class="span2 text-center t-w" style="line-height: 30px;">本月资源合计60个<br/>成本9,000元</div>
	</div>
	<!--结束：第3行 -->
	
	<!--开始：第4行 -->
	<div class="row-fluid bg-lb" style="height:106px;">
		<div class="span1">
			<div class="t-w text-center t-20 p-t-10 p-l-20" style="line-height: 140%;">
				月<br/>数<br/>据
			</div>
		</div>
		<div class="span1" >	
			<div class="easy-pie-chart bg-w" style="margin: 1px 0px; width: 90px; padding: 7px 10px;">
					<div data-percent="75" class="t-lb"><span class="t-36" style="font-weight: bold;" >75</span><span style="position:relative; font-size: 14px;  top:-20px;">%</span></div>
			</div>
		</div>
		<div class="span4">
			<table class="no-border-table1 t-w">
				<tr>
					<td>&nbsp;</td>
					<td>放款净业绩</td>
					<td>签单合计</td>
					<td>上门合计</td>
				</tr>							
				<tr>
					<td>计划</td>
					<td>19</td>
					<td>35</td>
					<td>90</td>
				</tr>							
				<tr>
					<td>实际</td>
					<td>15</td>
					<td>30</td>
					<td>80</td>
				</tr>							
				<tr>
					<td>完成</td>
					<td>78%</td>
					<td>85%</td>
					<td>90%</td>
				</tr>
			</table>
		</div>
		<div class="span6 bg-o text-center t-w">
			<div class="span2">
				<div class="t-w text-center t-20 p-t-10 p-l-20" style="line-height: 140%;">
					年<br/>数<br/>据
				</div>
			</div>
			<div class="span2" >	
				<div class="easy-pie-chart bg-w" style="margin: 1px 0px; width: 90px; padding: 7px 10px;">
						<div data-percent="10" class="t-o"><span class="t-36" style="font-weight: bold;" >10</span><span style="position:relative; font-size: 14px;  top:-20px;">%</span></div>
				</div>
			</div>
			<div class="span4 text-center" style="line-height: 106px;">收款完成比</div>
			<div class="span4" style="line-height: 80px;"><span class="t-80" style="position: relative; top: 15px;">15</span><span class="t-36" style="position: relative; top: 15px;">%</span></div>
		</div>
	</div>
	<!--结束：第4行 -->
	<div>&nbsp;</div>
</div>
<!-- END PAGE CONTAINER--> 


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
	jdyTab("lblMonth3", "lblDay3", "skhz-month", "skhz-day");
	
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