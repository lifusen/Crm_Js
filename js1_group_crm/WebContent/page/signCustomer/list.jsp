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
		<th style="min-width: 24px;">序号</th>
		<th style="min-width: 50px;">公司</th>
		<th style="min-width: 60px;">部门</th>
		<th style="min-width: 50px;">客户经理</th>
		<th style="min-width: 50px;">客户姓名</th>
		<th style="min-width: 50px;">客户电话</th>
		<th style="min-width: 50px;">合同编号</th>
		<th style="min-width: 50px;">签单客户状态</th>
		<th style="min-width: 50px;">来源</th>	<!-- 2016.12.9 14:55新增  by:SwordLiu-->
		<th style="min-width: 50px;">通话</th>
		<th style="min-width: 50px;">上门</th>
		<th style="min-width: 50px;">资料完善</th>
		<th style="min-width: 50px;">关注等级</th>
		<th style="min-width: 50px;">自留</th>
	</tr>
</thead>
<tbody>
	<c:forEach items="${requestScope.signCustomers }" var="obj" varStatus="vs">
		<tr cId="${obj.customerId }" sId="${obj.signCustomerId }">
			<td>${((pageIndex-1)*pageSize)+(vs.index+1) }</td>
			<td>${allCompanyNameMap[obj.companyId]}</td>
			<td>${obj.departmentName }</td>
			<td>${obj.employeeName }</td>
			<td>
			<a href="javascript:void(0);" title="${obj.customerName }" style="text-decoration: none;color: #000;">
				<div style="width:50px; overflow: hidden;text-overflow: ellipsis;white-space: nowrap;">
					${obj.customerName }
				</div>
			</a>
			</td>
			<td>${phone:phone(obj.phone)}</td>
			<td>${obj.contractNo }</td>
			<td>${obj.signCustomerStatus==0?'已签单':obj.signCustomerStatus==1?'退单':'退件' }</td>
			<td>${obj.sourceName}</td>	<!-- 2016.12.9 14:55新增  by:SwordLiu-->
			<td>${obj.callCount}</td>
			<td>${obj.visitCount}</td>
			<td>${obj.dataPercent}</td>
			<td>${obj.attentionLevel}</td>
			<td>${obj.visibility}</td>
		</tr>
	</c:forEach>
	</tbody>
</table>
</div>
<jsp:include page="../common/paginator.jsp" />
<script>
	$(function() {
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
			if(state==null || typeof(state) == "undefined"){
				state="";
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
			aPost("customer/gotoValidListUpdateUI.do?id="+cId+"&sId="+sId
					+"&refererUrl="+"signCustomer/getSignCustomerList.do?pageIndex=${pageIndex},keyword="
						+val+",state="+state+",dId="+dId+",eId="+
						eId+",beginDate="+beginDate+",endDate="+endDate+",pageSize="+$("#pageSize").val());
		});
	});
</script>