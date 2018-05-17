<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://itproject.cn/phone" prefix="phone"%>
<div class="customerListArea ">
<table class="table table-striped table-bordered table-hover table-full-width" id="customerTable" style="background: #EEE;">
<thead>
	<tr>
		<th style="width:3%">编号</th>
		<th width="7%">权证中心</th>
		<th width="6%">权证部门</th>
		<th width="6%">权证专员</th>
		<th width="6%">业务中心</th>
		<th width="6%">业务部门</th>
		<th width="6%">业务员</th>
		<th width="5%">客户姓名</th>
		<th width="9%">客户电话</th>
		<th width="8%">放款类型</th>
		<th width="5%">来源</th>
		<th width="9%">合同号</th>
		<th width="5%">资产</th>
		<th width="10%">放款时间</th>
	</tr>
</thead>
<tbody>
	<c:forEach items="${requestScope.outLoanCustomers }" var="obj" varStatus="vs">
		<tr cId="${obj.customerId }" sId="${obj.signCustomerId }">
			<td>${((pageIndex-1)*pageSize)+(vs.index+1) }</td>
			<td>${companyCacheMap[obj.warrantCompanyId]}</td>
			<td>${departmentCacheMap[obj.warrantDepartmentId]}</td>
			<td>${employeeCacheMap[obj.warrantEmployeeId]}</td>
			<td>${allCompanyNameMap[obj.companyId]}</td>
			<td>${obj.departmentName }</td>
			<td>${obj.employeeName }</td>
			<td>${obj.customerName }</td>
			<td>${phone:phone(obj.phone)}</td>
			<td>${obj.loanType}</td>
			<td>${obj.sourceName }</td>
			<td>${obj.contractNo }</td>
			<td>
				<a id="testaaa" href="javascript:;" class="popovers"
						data-trigger="hover" data-html="true"
						data-content="${obj.customerAsset }"
						data-original-title="资产信息"> ${obj.customerAssetTitile } </a>
			</td>
			<td><fmt:formatDate value="${obj.createTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
		</tr>
	</c:forEach>
	</tbody>
</table>
</div>
<jsp:include page="../common/paginator.jsp" />
<script>
	$(function() {
		$('.popovers').popover();
		$('#customerTable tbody tr').dblclick(function(){
			var cId = $(this).attr("cId");
			var sId = $(this).attr("sId");
			
			var val = strTrim($("#keyStr").val());
			var state = $("#signState").val();
			var dId = $("#demSelect").val();
			var eId = $("#emplSelect").val();
			var beginDate = $("#beginDate").val();
			var endDate = $("#endDate").val();
			
			if(dId==null || typeof(dId) == "undefined"){
				dId="";
			}
			if(eId==null || typeof(eId) == "undefined"){
				eId="";
			}
			if(beginDate==null || typeof(beginDate) == "undefined"){
				 beginDate="";
			}
			if(endDate==null || typeof(endDate) == "undefined"){
				 endDate="";
			}
			// TODO
			aPost("customer/gotoValidListUpdateUI.do?id="+cId+"&sId="+sId
					+"&refererUrl="+"outLoanCustomer/getOutLoanCustomerList.do?pageIndex=${pageIndex},keyword="
					+val+",dId="+dId+",eId="+
					eId+",beginDate="+beginDate+",endDate="+endDate+",pageSize="+$("#pageSize").val());
		});
	});
</script>