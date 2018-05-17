<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://itproject.cn/phone" prefix="phone"%>
<%@ taglib uri="http://itproject.cn/fllowDate" prefix="fllowDate"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<style type="text/css">
	.superSearch{
		width:100%;height:320px;
		border:1px solid #ccc;
		margin: 10px 0;
	}
</style>
<div>
	<p>搜&nbsp;&nbsp;索： <input type="text" id="keyStr" style="width:318px;" value="${requestScope.keyStr }" 
	placeholder="客户电话/姓名/等级/贷款类型" aria-controls="customerTable">
		<a class="btn green" id="searchButton" href="javascript:;" style="margin-top: -10px;padding-top:5px;padding-bottom: 5px;">
			<i class="icon-search"></i> 搜索
		</a>
	</p>
	<div class="row-fluid">
		<div class="span14">
			查&nbsp;&nbsp;询：
			<c:if test="${loginUser.type=='SUPER_ROLE' || requestScope.roleId==2000}">
				<select id="comSelect" class="span2">
					<option value="0">所有权证中心</option>
					<c:forEach items="${requestScope.companys }" var="company">
						<option value="${company.id }" 
							<c:if test="${companyId == company.id}">
								selected="selected"
							</c:if>
						>${company.name }</option>
					</c:forEach>
				</select>
			</c:if>
			<c:if test="${requestScope.roleId==2000 || requestScope.roleId==2001 || loginUser.type=='SUPER_ROLE'}">
				<input type="hidden" id="eId" value="${requestScope.eId }" />
					<select id="demSelect" class="span2">
						<option value="0">所有权证部</option>
						<c:forEach items="${requestScope.departments }" var="department">
							<option value="${department.id }"
								<c:if test="${dId == department.id}">
									selected="selected"
								</c:if>
							>${department.name }</option>
						</c:forEach>
					</select>
			</c:if>
			<select class="span2" id="emplSelect">
				<option value="0">所有权证专员</option>
				<c:forEach items="${requestScope.employees }" var="empl">
					<option value="${empl.id}" 
					<c:if test="${requestScope.eId==empl.id}">selected="selected"</c:if>
					>${empl.name}</option>
				</c:forEach>
			</select>
			<a class="btn green" id="ListSearchButton" href="javascript:void(0);" style="margin-top: -10px;"><i class="icon-search"></i> 搜索</a>
			<!-- <a class="btn green" id="turnsButton" href="javascript:void(0);" style="margin-top: -10px;"><i class="icon-ok"></i> 批量移交</a> -->
		</div>
	</div>
	<div class="row-fluid">
		<div class="span14">
		移交信息：
			<c:if test="${loginUser.type=='SUPER_ROLE' || requestScope.roleId==2000}">
				<select id="comSelect1" class="span2">
					<option value="0">所有权证中心</option>
					<c:forEach items="${requestScope.companys }" var="company">
						<option value="${company.id }">${company.name }</option>
					</c:forEach>
				</select>
			</c:if>
			<c:if test="${requestScope.roleId==2000 || requestScope.roleId==2001 || loginUser.type=='SUPER_ROLE'}">
				<select id="demSelect1" class="span2">
					<option value="0">所有权证部</option>
					<c:forEach items="${requestScope.departments }" var="department">
						<option value="${department.id }">${department.name }</option>
					</c:forEach>
				</select>
			</c:if>
			<select class="span2" id="emplSelect1">
				<option value="0">所有权证专员</option>
				<c:forEach items="${requestScope.employees }" var="employee">
					<option value="${employee.id}">${employee.name}</option>
				</c:forEach>
			</select>
			移交原因：
			<select class="span2" id="cause">
				<option value="晋升">晋升</option>
				<option value="资源整合">资源整合</option>
				<option value="离职">离职</option>
				<option value="合作开单">合作开单</option>
				<option value="其他">其他</option>
			</select>
			<input type="text" placeholder="其他原因" id="otherCause" />
			<a class="btn green" id="turnButton" href="javascript:void(0);" style="margin-top: -10px;"><i class="icon-ok"></i> 移交</a>
		</div>
	</div>
</div>
<!-- table table-striped table-bordered table-hover -->
<table class="table table-striped table-bordered table-hover table-full-width dblclickUpdate" id="customerTable" style="background: #EEE;">
	<thead>
		<tr>
		<th style="width:5%"><input type="checkbox" id="selectAll" onchange="selectAll(this);" class="group-checkable" data-set="#adv-custom-table .checkboxes" /></th>
			<th style="width:4%">编号</th>
			<th width="8%">权证中心</th>
			<th width="6%">权证部门</th>
			<th width="6%">权证专员</th>
			<th width="6%">业务中心</th>
			<th width="6%">业务部门</th>
			<th width="6%">业务员</th>
			<th width="6%">客户姓名</th>
			<th width="9%">客户电话</th>
			<th width="6%">贷款类型</th>
			<th width="5%">来源</th>
			<th width="9%">合同号</th>
			<th width="5%">资产</th>
			<th width="15%">分配时间</th>
		</tr>
	</thead>
	<tbody>
		<c:forEach items="${requestScope.customers }" var="cust" varStatus="vsta">
			<tr cId="${cust.cId}">
			<td><input type="checkbox" eId="${cust.warrantEmployeeId}" 
			onchange="selectParent(this);" class="checkboxes customerList" value="${cust.sId }" /></td>
<%-- 				<td>${((pageIndex-1)*pageSize)+(vsta.index+1) }</td> --%>
				<td>${((pageIndex-1)*pageSize)+(vsta.index+1) }</td>
				<td>${allCompanyNameMap[cust.warrantCompanyId]}</td>
				<td>${departmentCacheMap[cust.warrantDepartmentId]}</td>
				<td>${employeeCacheMap[cust.warrantEmployeeId]}</td>
				<td>${companyCacheMap[cust.companyId]}</td>
				<td>${departmentCacheMap[cust.departmentId]}</td>
				<td>${cust.employeeName }</td>
				<td>${cust.customerName }</td>
				<td>${phone:phone(cust.phone)}</td>
				<td>${cust.loanType }</td>
				<td>${cust.sourceName }</td>
				<td>${cust.contractNO }</td>
				<td>
					<a id="testaaa" href="javascript:;" class="popovers"
						data-trigger="hover" data-html="true"
						data-content="${cust.customerAsset }"
						data-original-title="资产信息"> ${cust.customerAssetTitile } </a>
				</td>
				<td><fmt:formatDate value="${cust.createTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
			</tr>
		</c:forEach>
	</tbody>
</table>
<jsp:include page="../common/paginator.jsp" />
<script>

	$(function() {
	$('.popovers').popover();
	$('table.dblclickUpdate tbody tr').dblclick(function(){
		var cId = $(this).attr('cId');
		aPost("${pageContext.request.contextPath}/customer/gotoValidListUpdateUI.do?id="+cId+"&refererUrl=customer/turnOverList.do");
	});
	
	var val = $("#demSelect").val();
	if(val!=0 && typeof(val) != "undefined" ){
		$.post("employee/getEmployeesByDId.do",{dId:val,cId:2},function(data){
			var s = $("#emplSelect");
			s.empty();
			var eId = $("#eId").val();
			$("<option />",{value:0,text:"所有权证专员"}).appendTo(s);
			$.each(data,function(i,item){
				var op = $("<option />",{value:item.id,text:item.name});
				if(eId==item.id){
					op.attr("selected",true);
				}
				op.appendTo(s);
			});
		});
	}
	// 查询
	$("#demSelect").change(function(){
		var val = $(this).val();
		$.post("employee/getEmployeesByDId.do",{dId:val,cId:2},function(data){
			var s = $("#emplSelect");
			s.empty();
			$("<option />",{value:0,text:"所有权证专员"}).appendTo(s);
			$.each(data,function(i,item){
				$("<option />",{value:item.id,text:item.name}).appendTo(s);
			});
		});
	});
	// 移交信息
	$("#demSelect1").change(function(){
		var val = $(this).val();
		$.post("employee/getEmployeesByDId.do",{dId:val,cId:2},function(data){
			var s = $("#emplSelect1");
			s.empty();
			$("<option />",{value:0,text:"所有权证专员"}).appendTo(s);
			$.each(data,function(i,item){
				$("<option />",{value:item.id,text:item.name}).appendTo(s);
			});
		});
	});
	
	// 客户移交
	$("#turnButton").click(function(){
		var inputs = $(".customerList");
		var ids = "";
		var ary = new Array();
		
		for(var i=0;i<inputs.length;i++){
			if($(inputs[i]).attr("checked")){
				var id = $(inputs[i]).val();
				var eId = $(inputs[i]).attr("eId");
				ids = ids + (id+"-"+eId+",");
				ary.push(parseInt(eId)); 
			}
		}
		if(ids==""){
			alert("请选择客户");
			return;
		}
		var employeeId = $("#emplSelect1").val();
		employeeId = parseInt(employeeId);
		if(employeeId<1){
			alert("请选择移交的客户经理！");
			return;
		}
		for(var i=0;i<ary.length;i++){
			if(ary[i]==employeeId){
				alert("选择的客户列表中，现在的客户经理和希望移交给的客户经理是同一个人！");
				return;
			}
		}
		var cause = $("#cause").val();
		var otherCause = $("#otherCause").val();
		if(confirm("确定移交吗?")){
			$.post("warrant/doTurn.do",{ids:ids,toEmployeeId:employeeId,cause:cause,otherCause:otherCause},function(data){
				if(data){
					alert("移交成功！");
					aPost("warrant/warranterTurnOverList.do");
				}
			});
		}
	});
	// 批量移交
	$("#turnsButton").click(function(){
		var dId = $("#demSelect").val();
		var eId = $("#emplSelect").val();
		
		
		var dText = $("#demSelect").find("option:selected").text();
		var eText = $("#emplSelect").find("option:selected").text();
		
		var dText1 = $("#demSelect1").find("option:selected").text();
		var eText1 = $("#emplSelect1").find("option:selected").text();
		
		if(eId=="0"){
			alert("请选择客户经理");
			return;
		}
		
		// 移交参数
		var employeeId = $("#emplSelect1").val();
		if(eId==employeeId){
			alert("被移交和移交客户经理是同一人");
			return;
		}
		employeeId = parseInt(employeeId);
		if(employeeId<1){
			alert("请选择移交的客户经理！");
			return;
		}
		var cause = $("#cause").val();
		var otherCause = $("#otherCause").val();
		$.post("warrant/getTurnCount.do",{dId:dId,eId:eId},function(data){
			if(data<1){
				alert("没有可移交客户");
				return;
			}
			var msg = "确定将["+dText+"] ["+eText+"] 的 "+data+" 个客户移交给["+dText1+"]["+eText1+"]吗?";
			if(confirm(msg)){
				$.post("warrant/batchTurn.do",{dId:dId,eId:eId,toEmployeeId:employeeId,cause:cause,otherCause:otherCause},function(data){
					if(data){
						alert("移交成功！");
						aPost("warrant/warranterTurnOverList.do");
					}
				});
			}
		});
		
		
		if(employeeId<1){
			alert("请选择移交的客户经理！");
			return;
		}
	});
	
	$("#ListSearchButton").click(function(data){
		var dId = $("#demSelect").val();
		var eId = $("#emplSelect").val();
		var companyId = $("#comSelect").val();
		if(typeof(dId) == "undefined"){
			dId=0;
		}
		if(typeof(eId) == "undefined"){
			eId=0;
		}
		if(typeof(companyId) == "undefined"){
			companyId=0;
		}
		aPost("warrant/warranterTurnOverList.do?departmentId="+dId+"&employeeId="+eId+"&companyId="+companyId);
	});
	
	$("#comSelect").change(function(){
		var companyId = $(this).val();
		if(companyId!=0 && typeof(companyId) != "undefined"){
			getDep(companyId,$("#demSelect"));
		}
	});
	$("#comSelect1").change(function(){
		var companyId = $(this).val();
		if(companyId!=0 && typeof(companyId) != "undefined"){
			getDep(companyId,$("#demSelect1"));
		}
	});
	//全文搜索
	$("#keyStr").keyup(function(event){
		 if(event.keyCode == 13){
			var val = strTrim($(this).val());
			aPost("warrant/warranterTurnOverList.do?keyStr="+val);
		 }
	 });
	$("#searchButton").click(function(){
		var val = strTrim($("#keyStr").val());
		aPost("warrant/warranterTurnOverList.do?keyStr="+val);
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
	function strTrim(val){
		var str = val.replace(/\s+/g,"");
		return str;
	}
	
	function getDep(companyId,departmentSelect){
		$.getJSON("department/getDepartmentListMapByCompanyId.do",
				{companyId:companyId,type:2},function(departments){
			departmentSelect.empty();
			$("<option value='0'>所有权证部</option>").appendTo(departmentSelect);
			for(var i = 0; i < departments.length; i++){
				var department = departments[i];
				if($("#depId").val()==department.id){
					$("<option selected='selected' value='"+department.id+"'>"+department.name+"</option>").appendTo(departmentSelect);
				}else{
					$("<option value='"+department.id+"'>"+department.name+"</option>").appendTo(departmentSelect);
				}
			}
		});
	}
</script>