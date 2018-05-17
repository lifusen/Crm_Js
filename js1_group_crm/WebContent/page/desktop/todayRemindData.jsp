<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<table class="table table-bordered t-g b-n" width="100%">
	<tr>
		<td style="border-left: none; text-align: center">提醒人数 <div class="t-36" style="line-height: 42px;">${todayRemindData.remind }</div></td>
		<td style="text-align: center">已跟进<div class="t-36" style="line-height: 42px;">&nbsp;</div></td>
		<td style="text-align: center">提醒跟进完成率<div class="t-36" style="line-height: 42px;">&nbsp;</div></td>
	</tr>
	<tr>
		<td style="border-left: none; text-align: center">还款提醒 <div class="t-36" style="line-height: 42px;">${todayRemindData.repayment }</div></td>
		<td style="text-align: center">已完成<div class="t-36" style="line-height: 42px;">&nbsp;</div></td>
		<td style="text-align: center">工作完成率<div class="t-36" style="line-height: 42px;">&nbsp;</div></td>
	</tr>
	<tr>
		<td style="border-left: none; text-align: center">基础工作 <div class="t-36" style="line-height: 42px;">${todayRemindData.basic }</div></td>
		<td style="text-align: center">已完成<div class="t-36" style="line-height: 42px;">&nbsp;</div></td>
		<td style="text-align: center">基础工作完成率<div class="t-36" style="line-height: 42px;">&nbsp;</div></td>
	</tr>
</table>