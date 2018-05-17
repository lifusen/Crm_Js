<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.Date"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://itproject.cn/phone" prefix="phone"%>
<%@ taglib uri="http://itproject.cn/fllowDate" prefix="fllowDate"%>
<div class="customerListArea ">
<table class="table table-striped table-bordered table-hover table-full-width" id="customerTable" style="background: #EEE;">
<thead>
	<tr>
		<th style="min-width: 24px;">序号</th>
		<th style="min-width: 50px;">权证中心</th>
		<th style="min-width: 60px;">权证部门</th>
		<th style="min-width: 50px;">权证专员</th>
		<th style="min-width: 50px;">业务中心</th>
		<th style="min-width: 60px;">业务部</th>
		<th style="min-width: 50px;">客户经理</th>
		<th style="min-width: 50px;">客户姓名</th>
		<th style="min-width: 50px;">客户电话</th>
		<th style="min-width: 300px;">提醒内容</th>
		<th style="min-width: 40px;">贷款类型</th>
		<th style="min-width: 40px;">提醒类型</th>
		<th style="min-width: 50px;">提醒时间</th>
		<th style="min-width: 35px;">签单状态</th>
	</tr>
</thead>
<tbody>
	<c:set var="nowDate">
		<fmt:formatDate value="<%=new Date() %>" pattern="yyyy-MM-dd" type="date"/>
	</c:set>
	<c:forEach items="${requestScope.customerReminders }" var="cr" varStatus="vs">
		
		<tr trId="${cr.customerId }" style="${nowDate gt cr.remindTime?'color:red;':''}">
			<td class="clickLink">
				${((pageIndex-1)*pageSize)+(vs.index+1) }
			</td>
			<td>${companyCacheMap[cr.warrantCompanyId]}</td>
			<td>${departmentCacheMap[cr.warrantDepartmentId]}</td>
			<td>${employeeCacheMap[cr.warrantEmployeeId]}</td>
			<td>${allCompanyNameMap[cr.companyId]}</td>
			<td>${departmentCacheMap[cr.departmentId] }</td>
			<td>${employeeCacheMap[cr.employeeId]}</td>
			<td>
			<a href="javascript:void(0);" title="${cr.customerName }" style="text-decoration: none;color: ${nowDate gt cr.remindTime?'red;':'#000000'};">
				<div style="width:50px; overflow: hidden;text-overflow: ellipsis;white-space: nowrap;">
					${cr.customerName}
				</div>
			</a>
			</td>
			<td>${phone:phone(cr.phone)}</td>
			<td>
				<a href="javascript:void(0);" title="${cr.remindContent }" style="text-decoration: none;color: ${nowDate gt cr.remindTime?'red;':'#000000'};">
					<div style="width:300px; overflow: hidden;text-overflow: ellipsis;white-space: nowrap;">
						${cr.remindContent }
					</div>
				</a>
			</td>
			<td style="text-align:center;">${cr.loanType}</td>
			<td style="text-align:center;">
				<c:choose>
					<c:when test="${cr.customerFollowType==1}">电话联系</c:when>
					<c:when test="${cr.customerFollowType==2}">邀约上门</c:when>
					<c:when test="${cr.customerFollowType==3}">上门洽谈</c:when>
					<c:when test="${cr.customerFollowType==4}">成功签约</c:when>
					<c:when test="${cr.customerFollowType==5}">售后服务</c:when>
					<c:when test="${cr.customerFollowType==100}">合同签订</c:when>
					<c:when test="${cr.customerFollowType==101}">合同审批</c:when>
					<c:when test="${cr.customerFollowType==102}">抵押办理</c:when>
					<c:when test="${cr.customerFollowType==103}">放款</c:when>
					<c:otherwise></c:otherwise>
				</c:choose>
			</td>
			<td style="min-width:60px;"><fmt:formatDate value="${cr.remindTime}" pattern="yyyy-MM-dd"/></td>
			<td style="text-align:center;">${cr.signState}</td>
			<%-- <td style="text-align:center;">
				<c:if test="${cr.visibility==0 }">
					是
				</c:if>
				<c:if test="${cr.visibility==1 }">
					否
				</c:if>
			</td> --%>
		</tr>
	</c:forEach>
	</tbody>
</table>
</div>
<jsp:include page="../common/paginator.jsp" />
<script>
	$(function() {
		$('#customerTable tbody tr').dblclick(function(){
			var id = $(this).attr("trId");
			
			var val = strTrim($("#keyStr").val());
			 var beginDate = $("#beginDate").val();
			 var endDate = $("#endDate").val();
			 if(beginDate==null || typeof(beginDate) == "undefined"){
				 beginDate="";
			 }
			 if(endDate==null || typeof(endDate) == "undefined"){
				 endDate="";
			 }
			var dId = $("#demSelect").val();
			var eId = $("#emplSelect").val();
			if(dId==null || typeof(dId) == "undefined"){
				dId="";
			}
			if(eId==null || typeof(eId) == "undefined"){
				eId="";
			}
			var fllowType = $("#fllowType").val();
			aPost("customer/gotoValidListUpdateUI.do?id="+id+"&refererUrl="+
					"warrant/getReminderList.do?pageIndex=${pageIndex},keyword="
					+val+",dId="+dId+",eId="+
					eId+",beginDate="+beginDate+",endDate="+endDate+",pageSize="+$("#pageSize").val()+",fllowType="+fllowType);
		});
		
		/**
		 * 单击td触发tr的双击事件
		 */
		$("table tbody tr td.clickLink").click(function(){
			$(this).closest("tr").dblclick();
		});
	});
</script>