<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!-- 客户列表JS -->
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
				<li><i class="icon-home"></i> <a href="javascript:void(0);">行政部</a>
					<i class="icon-angle-right"></i></li>
				<li><a href="javascript:void(0);">无效客户</a></li>
			</ul>
		</div>
	</div>
	<div class="row-fluid">
		<div class="span12">
			<!-- BEGIN EXAMPLE TABLE PORTLET-->
			<div class="portlet box green">
				<div class="portlet-title">
					<div class="caption"><i class="icon-globe"></i>无效客户列表</div>
					<div class="tools">
						<a href="javascript:;" class="collapse"></a>
					</div>
				</div>
				<div class="portlet-body">
					<div>
						<p style="width:500px;float:left;">搜索: <input type="text" style="width:300px;" id="keyStr" value="${requestScope.keyStr }" placeholder="客户电话/姓名/等级/贷款类型" aria-controls="customerTable">
						<a class="btn green" id="searchButton" href="javascript:;" style="margin-top: -10px;padding-top:5px;padding-bottom: 5px;">
							<i class="icon-search"></i> 搜索
						</a></p>
					</div>
					<div id="tableList">
						<jsp:include page="nullity-List.jsp" />
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
<script>
	$(function(){
		$("#keyStr").focus();
		// 全文搜索
		$("#keyStr").keyup(function(event){
			 if(event.keyCode == 13){
				var val = strTrim($(this).val());
				$("#tableList").load("customer/getNullityList.do?initPage=1&pageIndex=1&keyStr="+val);
			 }
		 });
		$("#searchButton").click(function(){
			var val = strTrim($("#keyStr").val());
			$("#tableList").load("customer/getNullityList.do?initPage=1&pageIndex=1&keyStr="+val);
		});	
	});
	function strTrim(val){
		var str = val.replace(/\s+/g,"");
		return str;
	}
</script>