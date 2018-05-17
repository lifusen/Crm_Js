<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://itproject.cn/phone" prefix="phone"%>
<%@ taglib uri="http://itproject.cn/fllowDate" prefix="fllowDate"%>
<table class="table table-striped table-bordered table-hover table-full-width" id="customerTable" style="background: #EEE;">
<thead>
	<tr>
		<th style="min-width: 24px;">序号</th>
		<th style="min-width: 60px;">公司</th>
		<th style="min-width: 60px;">部门</th>
		<th style="min-width: 50px;">客户经理</th>
		<th style="min-width: 50px;">客户姓名</th>
		<th style="min-width: 50px;">电话</th>
		<th style="min-width: 80px;">资产</th>
		<th style="min-width: 50px;">贷款类型</th>
		<th style="min-width: 50px;">客户来源</th>
		<th style="min-width: 50px;">分配时间</th>
		<th style="min-width: 80px;">倒计时</th>
	</tr>
</thead>
<tbody>
	<c:forEach items="${requestScope.customers }" var="newCustomer" varStatus="vsta">
		<tr trId="${newCustomer.id }">
			<td class="">${((pageIndex-1)*pageSize)+(vsta.index+1) }</td>
			<td>${allCompanyNameMap[newCustomer.companyId]}</td>
			<td class="">${newCustomer.dName }</td>
			<td class="">${newCustomer.eName }</td>
			<td>${newCustomer.cName }</td>
			<td>${phone:phone(newCustomer.phone)}</td>
			<td>
				<a href="javascript:;" class="popovers"
					data-trigger="hover" data-html="true"
					data-content="${newCustomer.customerAsset }"
					data-original-title="资产信息">${newCustomer.customerAssetTitile } </a>
			</td>
			<td>${newCustomer.loanType }</td>
			<td>${newCustomer.sourceName }</td>
			<td><fmt:formatDate value="${newCustomer.updateTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
			<td>${fllowDate:fllowDate(newCustomer.updateTime)}</td>
		</tr>
	</c:forEach>
	</tbody>
</table>
<jsp:include page="../common/paginator.jsp" />
<script>
	$(function() {
		$('#customerTable tbody tr').dblclick(function(){
			var id = $(this).attr("trId");
			var val = strTrim($("#keyStr").val());
			aPost("customer/gotoUpdateBasicUI.do?id="+id+"&refererUrl="+"customer/getNewCustomerList.do?keyStr="+val+
					",pageIndex=${pageIndex},pageSize=${pageSize}"+"&isShowFollow=true");
		});
		$('.popovers').popover();
	});
</script>