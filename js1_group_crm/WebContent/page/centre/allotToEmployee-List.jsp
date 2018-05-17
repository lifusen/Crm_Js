<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://itproject.cn/phone" prefix="phone"%>
<div class="row-fluid span10">
	<div class="span17">
		搜索: <input type="text" style="width:200px;" id="keyStr" value="${requestScope.keyStr }" placeholder="客户电话/姓名" aria-controls="customerTable">
		<a class="btn green" id="searchButton" href="javascript:;" style="margin-top: -10px;padding-top:5px;padding-bottom: 5px;">
			<i class="icon-search"></i> 搜索
		</a>　
		<c:if test="${loginUser.type == 'SUPER_ROLE' || loginUser.role.id==2000}">
			<select id="companySelect" name="companyId" class="span2">
				<option value="0">请选择权证中心</option>
				<c:forEach items="${requestScope.companys }" var="company">
					<option 
						<c:if test="${companyId==company.id }"> selected='selected'</c:if>
					value="${company.id }">${company.name }</option>
				</c:forEach>
			</select>
		</c:if>
		<c:if test="${loginUser.type != 'SUPER_ROLE' && loginUser.role.id != 2000}">
			<input id="companySelect" class="hidden" style="width:0px;" type="text" value="${loginUser.companyId }"/>
		</c:if>
		<select id="demSelect" class="span2">
			<option value="0">请选择权证部</option>
			<c:forEach items="${requestScope.departmentList }" var="department">
				<option value="${department.id }">${department.name }</option>
			</c:forEach>
		</select>
		<select class="span2" id="emplSelect">
			<option value="0">请选择权证专员</option>
		</select>
		<a class="btn green" id="batchAllot" href="javascript:void(0);" style="margin-top: -10px;"><i class="icon-ok"></i> 分配</a>
	</div>
</div>
<table class="table table-striped table-bordered table-hover" id="adv-custom-table">
	<thead>
		<tr>
			<th style="width:5%"><input type="checkbox" onchange="selectAll(this);" id="selectAll" class="group-checkable" data-set="#adv-custom-table .checkboxes" /></th>
			<th style="width:5%">编号</th>
			<th width="8%" class="hidden-480">权证中心</th>
			<th width="8%" class="hidden-480">业务中心</th>
			<th width="8%">业务部门</th>
			<th width="8%"class="hidden-480">业务员</th>
			<th width="10%">客户姓名</th>
			<th width="10%">客户电话</th>
			<th width="15%" class="hidden-480">客户资产</th>
			<th style="min-width: 50px;">贷款类型</th>
			<th width="15%" class="hidden-480">分配时间</th>
		</tr>
	</thead>
	<tbody>
		<c:forEach items="${requestScope.customers }" var="cust" varStatus="vsta">
			<tr id="adv-custom-row-1" class="odd gradeX"  ondblclick="dblclickRowUpdate(${cust.sId})" customerId="${cust.sId }-${cust.cId}">
				<td><input type="checkbox" onchange="selectParent(this);" class="checkboxes customerList" value="${cust.sId }" /></td>
				<td>${((pageIndex-1)*pageSize)+(vsta.index+1) }</td>
				<td class="hidden-480">${companyCacheMap[cust.warrantCompanyId]}</td>
				<td class="hidden-480">${companyCacheMap[cust.companyId]}</td>
				<td>${departmentCacheMap[cust.departmentId]}</td>
				<td>${cust.employeeName }</td>
				<td class="hidden-480">${cust.customerName }</td>
				<td>${phone:phone(cust.phone)}</td>
				<td>
					<a id="testaaa" href="javascript:;" class="popovers"
						data-trigger="hover" data-html="true"
						data-content="${cust.customerAsset }"
						data-original-title="资产信息"> ${cust.customerAssetTitile } </a>
				</td>
				<td>${cust.loanType }</td>
				<td><fmt:formatDate value="${cust.updateTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
			</tr>
		</c:forEach>
	</tbody>
</table>
<jsp:include page="../common/paginator.jsp" />								
<script>

$(function(){
	$('#adv-custom-table tbody tr').dblclick(function(){
		var val = $(this).attr("customerId");
		var valarray=val.split("-"); 
		aPost("customer/gotoValidListUpdateUI.do?id="+valarray[1]+"&sId="+valarray[0]
				+"&refererUrl="+"center/orderList.do?pageSize="+$("#pageSize").val());
	});
});


	jQuery(document).ready(function() {
		$('.popovers').popover();
		
		var val = $("#companySelect").val();
		if(val!='0'){
			getDep(val);
		}
		
		
		// 批量分配
		$("#batchAllot").click(function(){
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
			
			var comVal = $("#companySelect").val();
			var demVal = $("#demSelect").val();
			var empVal = $("#emplSelect").val();
			
			if(comVal=="0" && demVal=="0" && empVal=="0"){
				alert("请选择中心、部门或者权证专员");
				return;
			}
			
			if(confirm("确定分配?")){
				$.post("warrant/allot.do",{companyId:comVal,dId:demVal,eId:empVal,ids:ids},function(data){
					if(data){
						alert("分配成功！");
						aPost("center/orderList.do");
					}
				});
			}
		});
		
		
		$("#companySelect").change(function(){
			var val = $(this).val();
			getDep(val);
			$("#emplSelect").empty();
			$("<option />",{value:0,text:"请选择权证专员"}).appendTo($("#emplSelect"));
			//为权证部门添加中心超管分配功能，取消按中心匹配相应客户功能
			//$("#tableList").load("center/orderList.do?pageIndex=1&companyId="+val+"&pageSize="+$("#pageSize").val());
		});
		$("#demSelect").change(function(){
			var val = $(this).val();
			$.post("employee/getEmployeesByDId.do",{dId:val},function(data){
				var s = $("#emplSelect");
				s.empty();
				$("<option />",{value:0,text:"请选择权证专员"}).appendTo(s);
				$.each(data,function(i,item){
					$("<option />",{value:item.id,text:item.name}).appendTo(s);
				});
			});
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
				$("#tableList").load("center/orderList.do?pageIndex=1&queryWord="+val+"&pageSize="+$("#pageSize").val());
			 }
		 });
		
		$("#searchButton").click(function(){
			var val = strTrim($("#keyStr").val());
			$("#tableList").load("center/orderList.do?pageIndex=1&queryWord="+val+"&pageSize="+$("#pageSize").val());
		});
	});
	function strTrim(val){
		var str = val.replace(/\s+/g,"");
		return str;
	}
	
	function getDep(companyId){
		$.post("department/getDepartmentListMapByCompanyId.do",
				{companyId:companyId},function(departments){
			$("#demSelect").empty();
			$("<option value='0'>请选择权证部</option>").appendTo($("#demSelect"));
			for(var i = 0; i < departments.length; i++){
				var department = departments[i];
				$("<option value='"+department.id+"'>"+department.name+"</option>").appendTo($("#demSelect"));
			}
		});
	}
</script>
