<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<table id="smqdhz-month" class="no-border-table3 t-g" style="width:100%;">
	<tr>
		<td width="12%">${typeName }</td>
		<td width="16%">目标上门</td>
		<td width="12%">已上门</td>
		<td width="12%">完成率</td>
		<td width="12%">目标签单</td>
		<td width="12%">已签单</td>
		<td width="12%">完成率</td>
		<td width="12%">转换率</td>
	</tr>
	<c:forEach var="entry" items="${visitOrSignData}">
		<tr>
			<td>${entry.value.name}</td>
			<td><fmt:formatNumber pattern="#.#" maxFractionDigits="2" value="${entry.value.planVisit }"/></td>
			<td>${entry.value.visit}</td>
			<td>${entry.value.visitPercent }%</td>
			<td><fmt:formatNumber pattern="#.#" maxFractionDigits="2" value="${entry.value.planSign }"/></td>
			<td>${entry.value.sign}</td>
			<td>${entry.value.signPercent }%</td>
			<td>${entry.value.cro }%</td>
		</tr>
	</c:forEach>
	<tr class="bg-g t-w">
		<td>平均</td>
		<td>${mapAvg.planVisitAvg}</td>
		<td>${mapAvg.visitAvg}</td>
		<td>${mapAvg.visitPercentAvg }%</td>
		<td>${mapAvg.planSignAvg}</td>
		<td>${mapAvg.signAvg}</td>
		<td>${mapAvg.signPercentAvg }%</td>
		<td>${mapAvg.croAvg }%</td>
	</tr>
</table>
<table id="smqdhz-day" class="no-border-table3 t-g" style="width:100%;">
	
</table>