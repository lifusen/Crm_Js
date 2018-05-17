<%@page import="java.util.Date"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://itproject.cn/phone" prefix="phone"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!-- BEGIN PAGE CONTAINER-->
<table class="table table-striped table-bordered table-hover" id="adv-custom-table">
	<thead>
		<tr>
			<th style="width:40px;">编号</th>
			<th style="width:50px;">部门</th>
			<th style="width:70px;">客户经理</th>
			<th style="width:70px;">客户姓名</th>
			<th style="width:70px;">客户电话</th>
			<th style="width:70px;">提醒类型</th>
			<th style="width:70px;">提醒时间</th>
			<th style="min-width:70px;">提醒内容</th>
			<th style="width:100px;">反馈时间</th>
			<th style="min-width:70px;">反馈内容</th>
		</tr>
	</thead>
	<tbody id="reportCustomerListTbody">
	<c:set var="nowDate">
		<fmt:formatDate value="<%=new Date() %>" pattern="yyyy-MM-dd" type="date"/>
	</c:set>
	<c:forEach items="${requestScope.customers }" var="cust" varStatus="vsta">
		<tr data-customer-id="${cust.customerId }" class="odd gradeX" style="${nowDate gt cust.remindTime?'color:red;':''}">
			<td>${((pageIndex-1)*pageSize)+(vsta.index+1) }</td>
			<td>${cust.dname }</td>
			<td>${cust.ename }</td>
			<td>${cust.cname }</td>
			<td>${phone:phone(cust.phone)}</td>
			<td>
				<c:choose>
					<c:when test=""></c:when>
					<c:when test="${cust.type==1}">电话联系</c:when>
				</c:choose>
				<c:choose>
					<c:when test="${cust.type==2}">邀约上门</c:when>
				</c:choose>
				<c:choose>
					<c:when test="${cust.type==3}">上门洽谈</c:when>
				</c:choose>
				<c:choose>
					<c:when test="${cust.type==4}">成功签约</c:when>
				</c:choose>
				<c:choose>
					<c:when test="${cust.type==5}">售后服务</c:when>
				</c:choose>
			</td>
			<td><fmt:formatDate value="${cust.remindTime }" pattern="yyyy-MM-dd"/></td>
			<td>${cust.remindContent }</td>
			<td><fmt:formatDate value="${cust.edtTime}" pattern="yyyy-MM-dd HH:mm"/></td>
			<td>${cust.feedbackContent }</td>
		</tr>
	</c:forEach>
	</tbody>
</table>	
<input type="hidden" id="eOrd" value="${requestScope.eOrd }" />
<input type="hidden" id="dateStr" value="${requestScope.dateStr}" />
<input type="hidden" id="types" value="${requestScope.types}" />
<jsp:include page="../common/paginator.jsp" />
<script>
	$(function() {
		$('.popovers').popover();
		
		$("#reportCustomerListTbody tr").dblclick(function(){
			var customerId = $(this).attr("data-customer-id");
			var customerDetailToken = '${customerDetailToken}';
			window.open("${pageContext.request.contextPath}/getCustomerDetailView.do?customerId="+customerId+"&customerDetailToken="+customerDetailToken);
		});
	});
</script>