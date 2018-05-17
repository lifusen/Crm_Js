<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<table class="no-border-table3 t-g small-font-container" style="width:100%;">
	<tr>
		<td width="19%">${typeName}</td>
		<td width="19%">总提醒量</td>
		<td width="19%">今日提醒</td>
		<td width="15%">已完成</td>
		<td width="14%">完成率</td>
		<td width="15%">通话量</td>
	</tr>
	<c:forEach var="entry" items="${dailyBasicData}">
		<tr>
			<td>${entry.value.name}</td>
			<td>${entry.value.total}</td>
			<td>${entry.value.today}</td>
			<td>&nbsp;</td>
			<td>&nbsp;</td>
			<td>&nbsp;</td>
		</tr>
	</c:forEach>
	<tr class="bg-g t-w">
		<td>平均</td>
		<td>${mapAvg.totalAvg}</td>
		<td>${mapAvg.todayAvg}</td>
		<td>&nbsp;</td>
		<td>&nbsp;</td>
		<td>&nbsp;</td>
	</tr>
</table>