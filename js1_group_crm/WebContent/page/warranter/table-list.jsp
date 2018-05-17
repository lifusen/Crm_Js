<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://itproject.cn/phone" prefix="phone"%>
<table class="table dblclickUpdate table-striped table-bordered table-hover" id="adv-custom-table">
	<thead>
		<tr>
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
			<th width="5%">类型</th>
			<th width="5%">资产</th>
			<th width="15%">分配时间</th>
		</tr>
	</thead>
	<tbody>
		<c:forEach items="${requestScope.customers }" var="cust" varStatus="vsta">
			<tr id="adv-custom-row-1" class="odd gradeX" customerId="${cust.sId }-${cust.cId}"  ondblclick="dblclickRowUpdate(${cust.sId})">
				<td>${((pageIndex-1)*pageSize)+(vsta.index+1) }</td>
				<td>${companyCacheMap[cust.warrantCompanyId]}</td>
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
					<c:if test="${cust.orderType==0 }">新增</c:if>
					<c:if test="${cust.orderType==1 }">退单退件</c:if>
					<c:if test="${cust.orderType==2 }">返单</c:if>
					<c:if test="${cust.outLoanState==1 && cust.orderType }">放款</c:if>
					<c:if test="${cust.orderType==100 }">合同签订</c:if>
					<c:if test="${cust.orderType==101 }">合同审批</c:if>
					<c:if test="${cust.orderType==102 }">抵押办理</c:if>
				</td>
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