<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://itproject.cn/phone" prefix="phone"%>
<%@ taglib uri="http://itproject.cn/fllowDate" prefix="fllowDate"%>
<style>
	table.allotTable td{
		padding:9px;
	}
</style>
<div class="row-fluid span10">
	<div class="span8">
		搜索: <input type="text" style="width:150px;" id="keyStr" value="${requestScope.keyStr }" placeholder="客户电话/姓名/贷款类型" aria-controls="customerTable">
		<a class="btn green" id="searchButton" href="javascript:;" style="margin-top: -10px;padding-top:5px;padding-bottom: 5px;">
			<i class="icon-search"></i> 搜索
		</a>　　
		<c:if test="${loginUser.type == 'SUPER_ROLE'}">
			<select id="comSelect" class="span3">
				<option value="0">所有中心</option>
				<c:forEach items="${requestScope.companys }" var="company">
					<option value="${company.id }"
						<c:if test="${companyId == company.id}">
							selected="selected"
						</c:if>
					>${company.name }</option>
				</c:forEach>
			</select>　
			<select id="demSelect" class="span3">
				<option value="0">所有部门</option>
			</select>
		</c:if>
	</div>
</div>
<div class="btn-group pull-right">
	<button id="btn-adv-custom-page-share" class="btn green dropdown-toggle" data-toggle="dropdown">批量分配&nbsp;&nbsp;<i class="icon-angle-down"></i></button>
	<ul class="dropdown-menu pull-right" style="max-height:300px; overflow:auto;">
		<c:forEach items="${requestScope.employees }" var="employee">
			<li><a id="employee-${employee.id }" class="batchAllot" href="javascript:;">${employee.name }</a></li>
		</c:forEach>
	</ul>
</div>						
<table class="table table-striped table-bordered table-hover allotTable" id="adv-custom-table">
	<thead>
		<tr>
			<th style="width:5%"><input type="checkbox" onchange="selectAll(this);" id="selectAll" class="group-checkable" data-set="#adv-custom-table .checkboxes" /></th>
			<th style="width:5%">编号</th>
			<th width="10%" class="hidden-480">公司</th>
			<th width="10%">业务部门</th>
			<th width="8%"class="hidden-480">客户姓名</th>
			<th width="10%">客户电话</th>
			<th width="15%" class="hidden-480">客户资产</th>
			<th width="8%" class="hidden-480">录入人</th>
			<th style="min-width: 60px;">贷款类型</th>
			<th style="min-width: 60px;">客户来源</th>
			<th width="14%">分配时间</th>
			<th width="14%">倒计时</th>
			<th width="10%">操作</th>
		</tr>
	</thead>
	<tbody>
		<c:forEach items="${requestScope.customers }" var="cust" varStatus="vsta">
			<tr id="adv-custom-row-1" class="odd gradeX"  ondblclick="dblclickRowUpdate(${cust.id})">
				<td><input type="checkbox" onchange="selectParent(this);" class="checkboxes customerList" value="${cust.id }" /></td>
				<td>${((pageIndex-1)*pageSize)+(vsta.index+1) }</td>
				<td class="hidden-480">${allCompanyNameMap[cust.companyId]}</td>
				<td>${cust.dName }</td>
				<td class="hidden-480">${cust.cName }</td>
				<td>${phone:phone(cust.phone)}</td>
				<td>
					<a id="testaaa" href="javascript:;" class="popovers"
						data-trigger="hover" data-html="true"
						data-content="${cust.customerAsset }"
						data-original-title="资产信息"> ${cust.customerAssetTitile } </a>
				</td>
				<td class="hidden-480">${cust.addPersonName}</td>
				<td>${cust.loanType }</td>
				<td>${cust.sourceName }</td>
				<td><fmt:formatDate value="${cust.updateTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
				<td>${fllowDate:fllowDate(cust.updateTime)}</td>
				<td >
					<div class="btn-group" style="margin-bottom:0px !important;">
						<button class="btn mini green dropdown-toggle" data-toggle="dropdown">分配&nbsp;&nbsp;<i class="icon-angle-down"></i></button>
						<ul class="dropdown-menu pull-right">
							<c:forEach items="${requestScope.employees }" var="employee">
								<li><a id="employee-${employee.id }" class="allotToEmployee" href="javascript:;">${employee.name }</a></li>
							</c:forEach>
						</ul>
					</div>
				</td>
			</tr>
		</c:forEach>
	</tbody>
</table>
<jsp:include page="../common/paginator.jsp" />										
<input id="depId" value="${departmentId }" type="hidden"/>
<script>
	function dblclickRowUpdate(customerId){
		aPost("customer/gotoUpdateBasicUI.do?id="+customerId+"&refererUrl="+"customer/getAllotToEmployeeList.do&updateType=1");
	}
	jQuery(document).ready(function() {
		
		var companyId = $("#comSelect").val();
		if(companyId!="0" && typeof(companyId) != "undefined"){
			getDep(companyId);
		}
		
		$('.popovers').popover();
		$(".allotToEmployee").click(function(){
			if(confirm("确定分配?")){
				var eId = $(this).attr("id");
				var eName = $(this).text();
				eId = eId.substring(eId.lastIndexOf("-")+1);
				var tds = $(this).parent().parent().parent()
									.parent().parent().children();
				var cId = $(tds[0]).find("input").val();
				$.post("customer/allotToEmployee.do",{eId:eId,cId:cId},function(data){
					if(data=="true"){
						alert("分配给业务员成功！");
						var companyId = $("#comSelect").val();
						var departmentId = $("#demSelect").val();
						if (typeof(companyId) == "undefined"){ 
							companyId=0;
						}
						if (typeof(departmentId) == "undefined"){ 
							departmentId=0;
						}
						aPost("customer/getAllotToEmployeeList.do?companyId="+companyId+"&departmentId="+departmentId);
					}else if(data=="false"){
						alert("分配给业务员失败！");
					}else {
						alert("分配失败：你分配的客户数量超过  [ " + eName
								+ " ] 所拥有的客户数量上限：[ " + data + " ] 人！")
					}
				});
			}
		});
		
		// 批量分配
		$(".batchAllot").click(function(){
			var inputs = $(".customerList");
			var ids = "";
			for(var i=0;i<inputs.length;i++){
				if($(inputs[i]).attr("checked")){
					var id = $(inputs[i]).val();
					ids = ids + (id+",");
				}
			}
			if(ids==""){
				alert("请选择客户");
				return;
			}
			
			if(confirm("确定分配?")){
				var eId = $(this).attr("id");
				var eName = $(this).text();
				eId = eId.substring(eId.lastIndexOf("-")+1);
				$.post("customer/batchAllotToEmployee.do",{eId:eId,ids:ids},function(data){
					if(data=="true"){
						alert("分配给业务员成功！");
						var companyId = $("#comSelect").val();
						var departmentId = $("#demSelect").val();
						if (typeof(companyId) == "undefined"){ 
							companyId=0;
						}
						if (typeof(departmentId) == "undefined"){ 
							departmentId=0;
						}
						aPost("customer/getAllotToEmployeeList.do?companyId="+companyId+"&departmentId="+departmentId);
					}else if(data=="false") {
						alert("分配给业务员失败！");
					}else {
						alert("分配失败：你分配的客户数量超过  [ " + eName
								+ " ] 所拥有的客户数量上限：[ " + data + " ] 人！")
					}
				});
			}
		});
	});
	
	// 全选
	function selectAll(cb){
		if($(cb).attr("checked")){
			$(".customerList").attr("checked",true);
			$(".customerList").parent().addClass("checked");
		}else{
			$(".customerList").attr("checked",false);
			$(".customerList").parent().removeClass("checked");
		}
	}
	
	function selectParent(checkbox){
		var menus = $(".customerList");
		var isAll = true;
		for(var i=0;i<menus.length;i++){
			if(!$(menus[i]).attr("checked")){
				isAll = false;
				break;
			}
		}
		if(!isAll){
			$("#selectAll").attr("checked",false);
			$("#selectAll").parent().removeClass("checked");
		}else{
			$("#selectAll").attr("checked",true);
			$("#selectAll").parent().addClass("checked");
		}
	}
	
	$(function(){
		$("#keyStr").focus();
		// 全文搜索
		$("#keyStr").keyup(function(event){
			 if(event.keyCode == 13){
				var val = strTrim($(this).val());
				$("#tableList").load("customer/getAllotToEmployeeList.do?initPage=1&pageIndex=1&keyStr="+val+"&pageSize="+$("#pageSize").val());
			 }
		 });
		
		$("#searchButton").click(function(){
			var val = strTrim($("#keyStr").val());
			$("#tableList").load("customer/getAllotToEmployeeList.do?initPage=1&pageIndex=1&keyStr="+val+"&pageSize="+$("#pageSize").val());
		});
	});
	function strTrim(val){
		var str = val.replace(/\s+/g,"");
		return str;
	}
	
	
	$("#comSelect").change(function(){
		var companyId = $(this).val();
		$("#tableList").load("customer/getAllotToEmployeeList.do?initPage=1&pageIndex=1&companyId="+companyId+"&pageSize="+$("#pageSize").val());
		$("#tableList1").load("customer/getAllotToEmployeeList.do?initPage=1&pageIndex=1&companyId="+companyId+"&pageSize="+$("#pageSize").val()+"&table=1");
		getDep(companyId);
	});
	
	$("#demSelect").change(function(){
		var companyId = $("#comSelect").val();
		var departmentId = $(this).val();
		$("#tableList").load("customer/getAllotToEmployeeList.do?initPage=1&pageIndex=1&companyId="+companyId+"&departmentId="+departmentId+"&pageSize="+$("#pageSize").val());
		$("#tableList1").load("customer/getAllotToEmployeeList.do?initPage=1&pageIndex=1&companyId="+companyId+"&departmentId="+departmentId+"&pageSize="+$("#pageSize").val()+"&table=1");
	});
	
	
	function getDep(companyId){
		$.getJSON("department/getDepartmentListMapByCompanyId.do",
				{companyId:companyId,type:1},function(departments){
			$("#demSelect").empty();
			$("<option value='0'>所有部门</option>").appendTo($("#demSelect"));
			for(var i = 0; i < departments.length; i++){
				var department = departments[i];
				if($("#depId").val()==department.id){
					$("<option selected='selected' value='"+department.id+"'>"+department.name+"</option>").appendTo($("#demSelect"));
				}else{
					$("<option value='"+department.id+"'>"+department.name+"</option>").appendTo($("#demSelect"));
				}
			}
		});
	}
</script>
