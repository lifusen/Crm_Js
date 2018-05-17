<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!-- 客户列表JS -->
<div id="validCustomerListDiv">
	<div class="row-fluid">
		<div class="span12">
			<h3 class="page-title"></h3>
			<ul class="breadcrumb">
				<li><i class="icon-home"></i> <a href="javascript:void(0);">行政部</a>
					<i class="icon-angle-right"></i></li>
				<li><a href="javascript:void(0);">面试人员列表</a></li>
			</ul>
		</div>
	</div>
	<div class="row-fluid">
		<div class="span12">
			<!-- BEGIN EXAMPLE TABLE PORTLET-->
			<div class="portlet box green">
				<div class="portlet-title">
					<div class="caption">
						<i class="icon-globe"></i>面试人员列表
					</div>
					<div class="tools">
						<a href="javascript:;" class="collapse"></a>
					</div>
				</div>
				<div class="portlet-body">
					<p style="text-align: center; margin: 0px;">
						搜索: <input type="text" id="keyStr" style="width: 300px;"
							value="${requestScope.keyStr }" placeholder="电话/姓名"
							aria-controls="customerTable"> <a class="btn green"
							id="searchButton" href="javascript:;"
							style="margin-top: -10px; padding-top: 5px; padding-bottom: 5px;">
							<i class="icon-search"></i> 搜索
						</a>
					</p>
					<div id="tableList">
						<jsp:include page="validCustomer-List.jsp" />
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
<div id="validCustomerDetail"></div>
<script>
	$(function() {
		//全文搜索
		$("#keyStr").keyup(
				function(event) {
					if (event.keyCode == 13) {
						var val = strTrim($(this).val());
						$.messager.showLoadProgress();
						$("#tableList").load(
								"customer/getValidCustomerList.do?initPage=1&pageIndex=1&keyStr="
										+ val + "&pageSize="
										+ $("#pageSize").val(), function() {
									$.messager.closeLoadProgress();
								});
					}
				});
		$("#searchButton").click(
				function() {
					$.messager.showLoadProgress();
					var val = strTrim($("#keyStr").val());
					$("#tableList")
							.load(
									"customer/getValidCustomerList.do?initPage=1&pageIndex=1&keyStr="
											+ val + "&pageSize="
											+ $("#pageSize").val(), function() {
										$.messager.closeLoadProgress();
									});
				});
		$("#keyStr").focus();
	});
	function strTrim(val) {
		var str = val.replace(/\s+/g, "");
		return str;
	}
</script>