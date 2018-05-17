<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<table id="notificationTable" class="table table-hover table-striped table-bordered">
	<thead>
		<tr>
			<th style="width:30px;">序号</th>
			<th style="width:60px;">消息类型</th>
			<th style="width:50px;">发送人</th>
			<th style="width:50px;">接收人</th>
			<th>消息内容</th>
			<th style="width:140px;">发送时间</th>
		</tr>
	</thead>
	<tbody>
		<c:forEach var="notification" items="${notifications}" varStatus="vs">
			<tr data-notificationId="${notification.id}" 
				data-receiverId="${notification.receiverId}"
				data-state="${notification.state}"
				data-customer-id="${notification.customerId}"
				data-sign-customer-id="${notification.signCustomerId}"
				class="${notification.state != 1 ? 'unread' : '' }"
			>
				<td>${((pageIndex-1)*pageSize)+(vs.index+1) }</td>
				<td>${notification.typeName }</td>
				<td>${employeeCacheMap[notification.senderId] }</td>
				<td>${employeeCacheMap[notification.receiverId] }</td>
				<td>${notification.content }</td>
				<td><fmt:formatDate value="${notification.sendTime }" pattern="yyyy-MM-dd HH:mm:ss"/></td>
			</tr>
		</c:forEach>
	</tbody>
</table>

<jsp:include page="../../page/common/paginator.jsp"/>
<script>
	$("#notificationTable tbody tr").dblclick(function(){
		var notificationId = $(this).attr("data-notificationId");
		var receiverId = $(this).attr("data-receiverId");
		var state = $(this).attr("data-state");
		if(state != "1"){
			// 设置tr为已读
			$(this).removeClass("unread");
			// 修改状态为已读
			NotificationUtil.updateNotificationState(receiverId,notificationId);
			
		}
		
		var customerId = $(this).attr("data-customer-id");
		var signCustomerId = $(this).attr("data-sign-customer-id");
		var keyword = "";
		// 显示客户详情
		var refererUrl =  "notification/getNotificationListView.do";
			refererUrl += "?pageIndex=${pageIndex}";
			refererUrl += "&pageSize="+$("#pageSize").val();
			refererUrl += "&keyword=" + keyword;
		console.log(refererUrl);
		// 路径编码
		var refererUrl = encodeURIComponent(refererUrl); 	
		console.log(refererUrl);
		var requestUrl = "customer/gotoValidListUpdateUI.do?id="+customerId+"&sId="+signCustomerId
				+"&refererUrl="+refererUrl;
		// 发送请求
		aPost(requestUrl);
		
	});
</script>