<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://itproject.cn/moneyFormat" prefix="moneyFormat"%>
<table id="skhz-month" width="100%" class="no-border-table3 t-g">
	<tr>
		<td width="20%">${typeName}</td>
		<td width="20%">放款金额</td>
		<td width="20%">目标业绩</td>
		<td width="20%">净业绩</td>
		<td width="20%">完成率</td>
	</tr>
	<c:forEach items="${requestScope.moneyData }" var="moneyData" varStatus="vs">
		<tr class="${vs.count==requestScope.moneyData.size()?'bg-g t-w':''}">
			<td width="20%">${moneyData.name }</td>
			<td width="20%">${moneyFormat:moneyFormat(moneyData.loanAmountTotal)}万</td>
			<td width="20%">${moneyFormat:moneyFormat(moneyData.goal) }万</td>
			<td width="20%">${moneyFormat:moneyFormat(moneyData.netEarningsTotal) }万</td>
			<td width="20%">${moneyData.finishPercent }%</td>
		</tr>
	</c:forEach>
</table>