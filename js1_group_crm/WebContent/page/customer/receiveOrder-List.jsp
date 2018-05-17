<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://itproject.cn/phone" prefix="phone"%>
<!-- BEGIN PAGE CONTAINER-->
<table class="table table-striped table-bordered table-hover" id="adv-custom-table">
	<thead>
		<tr>
			<th style="width:5%">编号</th>
			<th class="hidden-480">公司</th>
			<th class="hidden-480">客户资产</th>
			<th class="hidden-480">部门</th>
			<th class="hidden-480">客户经理</th>
			<th class="hidden-480">客户电话</th>
			<th class="hidden-480">客户姓名</th>
			<th class="hidden-480">客户来源</th>
			<th class="hidden-480">客户等级</th>
			<th class="hidden-480">贷款类型</th>
			<th style="width:25%">移交信息</th>
			<th class="hidden-480">状态</th>
		</tr>
	</thead>
	<tbody>
	<c:forEach items="${requestScope.customers }" var="cust" varStatus="vsta">
		<tr id="adv-custom-row-1" class="odd gradeX">
			<td>${((pageIndex-1)*pageSize)+(vsta.index+1) }</td>
			<td>${allCompanyNameMap[cust.companyId]}</td>
			<td>
				<a id="testaaa" href="javascript:;" class="popovers"
					data-trigger="hover" data-html="true"
					data-content="${cust.customerAsset }"
					data-original-title="资产信息"> ${cust.customerAssetTitile } </a>
			</td>
			<td>${cust.dname }</td>
			<td>${cust.ename }</td>
			<td>${phone:phone(cust.phone)}</td>
			<td class="hidden-480">${cust.cname }</td>
			<td class="hidden-480">${cust.sourceName }</td>
			<td class="hidden-480">${cust.customerLevel }</td>
			<td class="hidden-480">${cust.loanType }</td>
			<td class="hidden-480">${cust.turnDetail }</td>
			<td class="hidden-480">
				<c:if test="${cust.state==1 }">
					分配到部门
				</c:if>
				<c:if test="${cust.state==2 }">
					分配到员工
				</c:if>
			</td>
		</tr>
	</c:forEach>
	</tbody>
</table>	
<jsp:include page="../common/paginator.jsp" />
<script>
	$(function() {
		$('.popovers').popover();
	});
</script>