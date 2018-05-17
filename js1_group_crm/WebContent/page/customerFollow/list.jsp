<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %> 
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>   
<style style="text/css">
	.remindP{
		width:200px;
		white-space:nowrap;
		overflow: hidden;
		text-overflow:ellipsis;
	}
</style>
<table class="table table-striped table-bordered table-hover table-full-width">
	<tr>
		<td style="width:30px;"><label class="control-label">序号</label></td>
		<td style="width:60px;"><label class="control-label">提醒类型</label></td>
		<td style="width:62px;"><label class="control-label">提醒日期</label></td>
		<td style="text-align:center;width:33.5%;"><label class="control-label">提醒内容</label></td>
		<td style="width:40px;"><label class="control-label">跟进人</label></td>
		<td style="text-align:center;"><label class="control-label">反馈内容</label></td>
		<td style="width:100px;"><label class="control-label">操作时间</label></td>
	</tr>
	<c:if test="${fn:length(customerFollows)>0 }">
		<c:forEach var="customerFollow" items="${customerFollows }" varStatus="vs">
			<tr>
				<td>
					${((pageIndex-1)*pageSize)+(vs.index+1) }
				</td>
				<td>
					<c:choose>
						<c:when test="${customerFollow.type==1}">电话联系</c:when>
						<c:when test="${customerFollow.type==2}">邀约上门</c:when>
						<c:when test="${customerFollow.type==3}">上门洽谈</c:when>
						<c:when test="${customerFollow.type==4}">成功签约</c:when>
						<c:when test="${customerFollow.type==5}">售后服务</c:when>
						
						<c:when test="${customerFollow.type==100}">合同签订</c:when>
						<c:when test="${customerFollow.type==101}">合同审批</c:when>
						<c:when test="${customerFollow.type==102}">抵押办理</c:when>
						<c:when test="${customerFollow.type==103}">放款</c:when>
						<c:when test="${customerFollow.type==104}">其他</c:when>
						<c:otherwise></c:otherwise>
					</c:choose>
				</td>
				<td>
					<fmt:formatDate value="${customerFollow.remindTime }" pattern="yyyy-MM-dd"/>
				</td>
				<td style="width:200px;">
					<span style="color:#0d638f">${customerFollow.remindContent }</span>
				</td>
				
				<c:choose>
				<c:when test="${customerFollow.type>=100 }"><td style="color:red;">${customerFollow.employee.name }</td></c:when>
				<c:otherwise><td>${customerFollow.employee.name }</td></c:otherwise>
				</c:choose>
				
				<td>
					<span style="color:#0d638f">${customerFollow.feedbackContent }</span>
					<%--
					<a href="javascript:;" class="popovers"
						data-trigger="hover" data-html="true"
						data-content="${customerFollow.feedbackContent }"
						data-original-title="反馈内容详细">
						<p class="remindP">
							${customerFollow.feedbackContent }
						</p>	
					</a>
					 --%>
				</td>
				<td>
					<fmt:formatDate value="${customerFollow.edtTime}" pattern="yyyy-MM-dd HH:mm"/>
				</td>
			</tr>
		</c:forEach>
	</c:if>
	
</table>
<jsp:include page="../common/paginator.jsp"/>
<script type="text/javascript">
$(function() {
	$('.popovers').popover();
});
</script>
