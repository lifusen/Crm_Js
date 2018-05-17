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
		<th style="min-width: 50px;">类型</th>
		<th style="min-width: 50px;">退单退件时间</th>
		<th style="min-width: 50px; max-width:50px;">退单退件原因</th>
		<th style="min-width: 150px;">退单退件备注</th>
		<th style="min-width: 30px;">通话</th>
		<th style="min-width: 30px;">上门</th>
		<th style="min-width: 30px;">资料完善</th>
		<th style="min-width: 25px;">关注等级</th>
		<th style="min-width: 25px;">自留</th>
	</tr>
</thead>
<tbody>
	<c:forEach items="${requestScope.rejectCustomers }" var="obj" varStatus="vs">
		<tr cId="${obj.customerId }" sId="${obj.signCustomerId }">
			<td>${((pageIndex-1)*pageSize)+(vs.index+1) }</td>
			<td>${allCompanyNameMap[obj.companyId]}</td>
			<td>${obj.departmentName }</td>
			<td>${obj.employeeName }</td>
			<td>${obj.customerName }</td>
			<td>${phone:phone(obj.phone)}</td>
			<td>${obj.contractNo }</td>
			<td>
				${obj.lastFailureMessageType==0?'退件':'退单'}
			</td>
			<td>${obj.lastFailureMessageTime}</td>
			<td>${obj.lastFailureMessageCause}</td>
			<td>
				<a href="javascript:void(0);" title="${obj.lastFailureMessageRemark}" style="text-decoration: none;color:#000;">
					<div style="width:200px; overflow: hidden;text-overflow: ellipsis;white-space: nowrap;">
						${obj.lastFailureMessageRemark}
					</div>
				</a>
			</td>
			<td>${obj.callCount}</td>
			<td>${obj.visitCount}</td>
			<td>${obj.dataPercent}</td>
			<td>${obj.attentionLevel}</td>
			<td>${obj.visibility}</td>
			<td>${obj.countdown}</td>
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
			var failureMessage = $("#lastFailureMessageCause").val();
			
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
			
			aPost("customer/gotoValidListUpdateUI.do?id="+cId+"&sId="+sId
					+"&refererUrl="+"rejectCustomer/getRejectCustomerList.do?pageIndex=${pageIndex},keyword="
					+val+",state="+state+",dId="+dId+",eId="+
					eId+",beginDate="+beginDate+",endDate="+endDate+",failureMessage="+failureMessage+",pageSize="+$("#pageSize").val());
		});
	});
</script>