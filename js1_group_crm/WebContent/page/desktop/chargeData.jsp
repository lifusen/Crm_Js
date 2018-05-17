<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<table id="sfhz-total" style="width:100%;" class="no-border-table3 t-g">
	<tr>
		<td width="15%">${typeName}</td>
		<td width="10%">房贷</td>
		<td width="15%">二抵</td>
		<td width="10%">信贷</td>
		<td width="15%">信用卡</td>
		<td width="10%">车贷</td>
		<td width="15%">企贷</td>
		<td width="10%">短借</td>
	</tr>
	<c:forEach items="${requestScope.chargeData }" var="cd" varStatus="vs">
		<tr class="${vs.count==chargeData.size()?'bg-g t-w':''}">
			<td width="15%">${cd.name }</td>
			<td width="10%">${cd.houseLoan }%</td>
			<td width="15%">${cd.twoLoan }%</td>
			<td width="10%">${cd.creditLoan }%</td>
			<td width="15%">${cd.creditCard }%</td>
			<td width="10%">${cd.carLoan }%</td>
			<td width="15%">${cd.carLoan }%</td>
			<td width="10%">${cd.carLoan }%</td>
		</tr>
	</c:forEach>
</table>
 