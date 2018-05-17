<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!-- 客户列表JS -->
<script src="${pageContext.request.contextPath}/js/customerTable.js"></script>
<style type="text/css">
	.superSearch{
		width:100%;height:320px;
		border:1px solid #ccc;
		margin: 10px 0;
	}
</style>
<div class="container-fluid">
	<div class="row-fluid">
		<div class="span12">
			<h3 class="page-title"></h3>
			<ul class="breadcrumb">
				<li><i class="icon-home"></i> <a class="aPost"
					href="${pageContext.request.contextPath}/page/customer/list.jsp">客户管理</a>
					<i class="icon-angle-right"></i></li>
				<li><a href="#">客户列表</a></li>
			</ul>
		</div>
	</div>
	<div class="row-fluid">

		<div class="span12">
			<!-- BEGIN EXAMPLE TABLE PORTLET-->
			<div class="portlet box green">
				<div class="portlet-title">
					<div class="caption"><i class="icon-globe"></i>客户列表</div>
					<div class="tools">
						<a href="javascript:;" class="reload"></a>
					</div>
				</div>
				<div class="portlet-body">
					<div class="customerListArea">
						<p style="text-align: center;"><a class="btn green" id="superSearchButton" href="javascript:void(0);">
							<i class="icon-search"></i> 高级搜索
						</a></p>
						<!-- 高级搜索 -->
						<div id="superSearchDiv" class="superSearch hide" style="clear:left;">
							<p style="margin: 10px 0 0 10px;">
								　　　　部门：
								<a class="green btn mini all filterBtn" href="javascript:void(0);">不限</a>
								<a class="btn mini filterBtn" href="javascript:void(0);">业务一部</a>
								<a class="btn mini filterBtn" href="javascript:void(0);">业务二部</a>
								<a class="btn mini filterBtn" href="javascript:void(0);">业务三部</a>
								<a class="btn mini filterBtn" href="javascript:void(0);">业务四部</a>
								<a class="btn mini filterBtn" href="javascript:void(0);">市场部</a>
							</p>
							<p style="margin: 10px 0 0 10px;">
								　　客户经理：
								<a class="green btn mini all filterBtn" href="javascript:void(0);">不限</a>
								<a class="btn mini filterBtn" href="javascript:void(0);">张三</a>
								<a class="btn mini filterBtn" href="javascript:void(0);">李四</a>
								<a class="btn mini filterBtn" href="javascript:void(0);">王四</a>
								<a class="btn mini filterBtn" href="javascript:void(0);">张三</a>
								<a class="btn mini filterBtn" href="javascript:void(0);">李四</a>
								<a class="btn mini filterBtn" href="javascript:void(0);">王五</a>
								<a class="btn mini filterBtn" href="javascript:void(0);">赵六</a>
								<a class="btn mini filterBtn" href="javascript:void(0);">王四</a>
								<a class="btn mini filterBtn" href="javascript:void(0);">王四</a>
							</p>
							<p style="margin: 10px 0 0 10px;">
								　　贷款类型：
								<a class="green btn mini all filterBtn" href="javascript:void(0);">不限</a>
								<a class="btn mini filterBtn" href="javascript:void(0);">企贷</a>
								<a class="btn mini filterBtn" href="javascript:void(0);">2贷</a>
								<a class="btn mini filterBtn" href="javascript:void(0);">信贷</a>
								<a class="btn mini filterBtn" href="javascript:void(0);">房贷</a>
								<a class="btn mini filterBtn" href="javascript:void(0);">短借</a>
								<a class="btn mini filterBtn" href="javascript:void(0);">车贷</a>
								<a class="btn mini filterBtn" href="javascript:void(0);">信用卡</a>
							</p>
							<p style="margin: 10px 0 0 10px;">
								　　客户等级：
								<a class="green btn mini all filterBtn" href="javascript:void(0);">不限</a>
								<a class="btn mini filterBtn" href="javascript:void(0);">优级客户</a>
								<a class="btn mini filterBtn" href="javascript:void(0);">中级客户</a>
								<a class="btn mini filterBtn" href="javascript:void(0);">差级客户</a>
							</p>
							<p style="margin: 10px 0 0 10px;">
								　　客户状态：
								<a class="green btn mini all filterBtn" href="javascript:void(0);">不限</a>
								<a class="btn mini filterBtn" href="javascript:void(0);">为录资料</a>
								<a class="btn mini filterBtn" href="javascript:void(0);">正常</a>
								<a class="btn mini filterBtn" href="javascript:void(0);">自留</a>
								<a class="btn mini filterBtn" href="javascript:void(0);">签单</a>
								<a class="btn mini filterBtn" href="javascript:void(0);">全局公共池</a>
								<a class="btn mini filterBtn" href="javascript:void(0);">退电</a>
							</p>
							<p style="margin: 10px 0 0 10px;">
								来源首电类型：
								<a class="green btn mini all filterBtn" href="javascript:void(0);">不限</a>
								<a class="btn mini filterBtn" href="javascript:void(0);">直客</a>
								<a class="btn mini filterBtn" href="javascript:void(0);">名单</a>
								<a class="btn mini filterBtn" href="javascript:void(0);">客户介绍</a>
								<a class="btn mini filterBtn" href="javascript:void(0);">中介</a>
								<a class="btn mini filterBtn" href="javascript:void(0);">续贷客户</a>
								<a class="btn mini filterBtn" href="javascript:void(0);">合作客户</a>
								<a class="btn mini filterBtn" href="javascript:void(0);">有道</a>
								<a class="btn mini filterBtn" href="javascript:void(0);">电台105.6</a>
								<a class="btn mini filterBtn" href="javascript:void(0);">电台94.0</a>
								<a class="btn mini filterBtn" href="javascript:void(0);">百度</a>
								<a class="btn mini filterBtn" href="javascript:void(0);">谷歌</a>
								<a class="btn mini filterBtn" href="javascript:void(0);">搜狗</a>
								<a class="btn mini filterBtn" href="javascript:void(0);">SOSO</a>
								<a class="btn mini filterBtn" href="javascript:void(0);">电视</a>
								<a class="btn mini filterBtn" href="javascript:void(0);">网络</a>
							</p>
							<p style="margin: 10px 0 0 10px;">
								　　时间类型：
								<a class="green btn mini all filterBtn" href="javascript:void(0);">不限</a>
								<a class="btn mini filterBtn" href="javascript:void(0);">反馈时间</a>
								<a class="btn mini filterBtn" href="javascript:void(0);">录入时间</a>
								<a class="btn mini filterBtn" href="javascript:void(0);">签单时间</a>
							</p>
							<p style="margin: 10px 0 0 10px;">
								　　　　时间：
								<input data-provide="datepicker" type="text" class="span2 small" readonly="readonly" size="12">
								 至 <input data-provide="datepicker" type="text" class="span2 small" readonly="readonly" size="12">
							</p>
							<p style="padding:10px 0 0 95px;">
								<a class="btn" id="clearFilter" href="javascript:void(0);"><i class="icon-remove icon-white"></i> 清空条件</a>
							</p>
						</div>
					
						<!-- table table-striped table-bordered table-hover -->
						<table class="table table-striped table-bordered table-hover table-full-width dblclickUpdate" id="customerTable" style="background: #EEE;">
							<thead>
								<tr>
									<th style="min-width: 24px;">序号</th>
									<th style="min-width: 60px;">部门</th>
									<th style="min-width: 50px;">客户经理</th>
									<th style="min-width: 50px;">客户姓名</th>
									<th style="min-width: 50px;">电话</th>
									<th style="min-width: 160px;">资产</th>
									<th style="min-width: 50px;">贷款类型</th>
									<th style="min-width: 50px;">谈判师</th>
									<th style="min-width: 25px;">通话</th>
									<th style="min-width: 25px;">上门</th>
									<th style="min-width: 90px;">反馈时间</th>
									<th style="min-width: 50px;">资料完善</th>
									<th style="min-width: 50px;">关注等级</th>
									<th style="min-width: 50px;">签单状态</th>
									<th style="min-width: 25px;">自留</th>
									<th style="min-width: 25px;">提醒</th>
									<th style="min-width: 36px;">倒计时</th>
								</tr>
							</thead>
							<tbody>
								<tr>
									<td class="">1</td>
									<td class="">业务一部</td>
									<td class="">杨静</td>
									<td>张三</td>
									<td>158****3366</td>
									<td>
										<a href="javascript:;" class="popovers"
											data-trigger="hover" data-html="true"
											data-content="住房:高新区天华一路英俊一期雍雅河鲜旁,面积108,均价9800,精装,9成新,租金3000/月&lt;br/&gt;住房:高新区天华一路英俊一期雍雅河鲜旁,面积108,均价9800,精装,9成新,租金3000/月"
											data-original-title="资产信息"> 房屋1栋,汽车一辆 </a>
									</td>
									<td>小贷</td>
									<td>王丽丽</td>
									<td>0</td>
									<td>1</td>
									<td>2015-2-1 10:18</td>
									<td style="text-align:center;">78%</td>
									<td style="text-align:center;">A</td>
									<td>未签单</td>
									<td>是</td>
									<td>否</td>
									<td>10</td>
								</tr>
								<tr>
									<td class="">1</td>
									<td class="">业务一部</td>
									<td class="">杨静</td>
									<td>李四</td>
									<td>158****8888</td>
									<td>
										<a href="javascript:;" class="popovers"
											data-trigger="hover" data-html="true"
											data-content="住房:高新区天华一路英俊一期雍雅河鲜旁,面积108,均价9800,精装,9成新,租金3000/月&lt;br/&gt;住房:高新区天华一路英俊一期雍雅河鲜旁,面积108,均价9800,精装,9成新,租金3000/月"
											data-original-title="资产信息"> 房屋1栋,汽车一辆 </a>
									</td>
									<td>小贷</td>
									<td>王丽丽</td>
									<td>2</td>
									<td>2</td>
									<td>2015-2-5 10:18</td>
									<td style="text-align:center;">60%</td>
									<td style="text-align:center;">B</td>
									<td>未签单</td>
									<td>是</td>
									<td>否</td>
									<td>1</td>
								</tr>
								<tr>
									<td class="">1</td>
									<td class="">业务一部</td>
									<td class="">杨静</td>
									<td>王五</td>
									<td>188****3698</td>
									<td>
										<a href="javascript:;" class="popovers"
											data-trigger="hover" data-html="true"
											data-content="住房:高新区天华一路英俊一期雍雅河鲜旁,面积108,均价9800,精装,9成新,租金3000/月&lt;br/&gt;住房:高新区天华一路英俊一期雍雅河鲜旁,面积108,均价9800,精装,9成新,租金3000/月"
											data-original-title="资产信息"> 房屋1栋,汽车一辆 </a>
									</td>
									<td>小贷</td>
									<td>王丽丽</td>
									<td>3</td>
									<td>3</td>
									<td>2015-2-4 10:18</td>
									<td style="text-align:center;">60%</td>
									<td style="text-align:center;">C</td>
									<td>未签单</td>
									<td>是</td>
									<td>否</td>
									<td>5</td>
								</tr>
								<tr>
									<td class="">1</td>
									<td class="">业务一部</td>
									<td class="">杨静</td>
									<td>李晨</td>
									<td>189****4567</td>
									<td>
										<a href="javascript:;" class="popovers"
											data-trigger="hover" data-html="true"
											data-content="住房:高新区天华一路英俊一期雍雅河鲜旁,面积108,均价9800,精装,9成新,租金3000/月&lt;br/&gt;住房:高新区天华一路英俊一期雍雅河鲜旁,面积108,均价9800,精装,9成新,租金3000/月"
											data-original-title="资产信息"> 房屋1栋,汽车一辆 </a>
									</td>
									<td>小贷</td>
									<td>王丽丽</td>
									<td>0</td>
									<td>1</td>
									<td>2015-2-3 10:18</td>
									<td style="text-align:center;">70%</td>
									<td style="text-align:center;">D</td>
									<td>未签单</td>
									<td>是</td>
									<td>否</td>
									<td>6</td>
								</tr>
								<tr>
									<td class="">1</td>
									<td class="">业务一部</td>
									<td class="">杨静</td>
									<td>范冰冰</td>
									<td>135****1111</td>
									<td>
										<a href="javascript:;" class="popovers"
											data-trigger="hover" data-html="true"
											data-content="住房:高新区天华一路英俊一期雍雅河鲜旁,面积108,均价9800,精装,9成新,租金3000/月&lt;br/&gt;住房:高新区天华一路英俊一期雍雅河鲜旁,面积108,均价9800,精装,9成新,租金3000/月"
											data-original-title="资产信息"> 房屋1栋,汽车一辆 </a>
									</td>
									<td>小贷</td>
									<td>王丽丽</td>
									<td>3</td>
									<td>4</td>
									<td>2015-2-4 10:18</td>
									<td style="text-align:center;">80%</td>
									<td style="text-align:center;">A</td>
									<td>未签单</td>
									<td>是</td>
									<td>否</td>
									<td>26</td>
								</tr>
								<tr>
									<td class="">1</td>
									<td class="">业务一部</td>
									<td class="">杨静</td>
									<td>李冰冰</td>
									<td>189****2222</td>
									<td>
										<a href="javascript:;" class="popovers"
											data-trigger="hover" data-html="true"
											data-content="住房:高新区天华一路英俊一期雍雅河鲜旁,面积108,均价9800,精装,9成新,租金3000/月&lt;br/&gt;住房:高新区天华一路英俊一期雍雅河鲜旁,面积108,均价9800,精装,9成新,租金3000/月"
											data-original-title="资产信息"> 房屋1栋,汽车一辆 </a>
									</td>
									<td>小贷</td>
									<td>王丽丽</td>
									<td>5</td>
									<td>6</td>
									<td>2015-2-6 10:18</td>
									<td style="text-align:center;">88%</td>
									<td style="text-align:center;">B</td>
									<td>未签单</td>
									<td>是</td>
									<td>否</td>
									<td>19</td>
								</tr>
								<tr>
									<td class="">1</td>
									<td class="">业务一部</td>
									<td class="">杨静</td>
									<td>张三</td>
									<td>188****1010</td>
									<td>
										<a id="testaaa" href="javascript:;" class="popovers"
											data-trigger="hover" data-html="true"
											data-content="住房:高新区天华一路英俊一期雍雅河鲜旁,面积108,均价9800,精装,9成新,租金3000/月&lt;br/&gt;住房:高新区天华一路英俊一期雍雅河鲜旁,面积108,均价9800,精装,9成新,租金3000/月"
											data-original-title="资产信息"> 房屋1栋,汽车一辆 </a>
									</td>
									<td>小贷</td>
									<td>王丽丽</td>
									<td>8</td>
									<td>8</td>
									<td>2015-2-6 10:18</td>
									<td style="text-align:center;">90%</td>
									<td style="text-align:center;">C</td>
									<td>未签单</td>
									<td>是</td>
									<td>否</td>
									<td>20</td>
								</tr>
								
							</tbody>
						</table>
					</div>
				</div>

			</div>

			<!-- END EXAMPLE TABLE PORTLET-->



		</div>
	</div>
</div>

<script>
	$('#customerTable tbody tr').dblclick(function(){
		return;
		aPost("${pageContext.request.contextPath}/customer/gotoValidListUpdateUI.do?id=1");
	});

	$(function() {
		$('.popovers').popover();
		//$('#testaaa').mouseover();
	});
	
	// 启动、关闭 高级搜索
	$("#superSearchButton").click(function(){
		var searchDiv = $("#superSearchDiv");
		if($(searchDiv).hasClass("hide")){
			$("#superSearchDiv").removeClass("hide");
			$(this).html('<i class="icon-remove icon-white"></i> 关闭高级搜索');
		}else{
			$("#superSearchDiv").addClass("hide");
			$(this).html('<i class="icon-search"></i> 高级搜索');
		}
	});
	
	// 按条件搜索
	$(".filterBtn").click(function(){
		if($(this).hasClass("all")){
			$(this).parent().children().removeClass("green");
			$(this).addClass("green");
		}else{
			var children = $(this).parent().children();
			$(children[0]).removeClass("green");
			$(this).addClass("green");
		}
	});
	
	// 清空条件
	$("#clearFilter").click(function(){
		$(".filterBtn").removeClass("green");
		$(".all").addClass("green");
	});		
</script>