<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>    
<div class="modal-header">
	<button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>
	<h3 id="messageModalLabel">${typeName}</h3>
</div>
<div class="modal-body" id="messageModalBody">
	<div class="alert alert-info">
		<h5 >${message.title }</h5>
		<span>发布人:</span> ${message.publisher.name}&nbsp;
		<span>时间:</span> <fmt:formatDate value="${message.pubdate}" pattern="yyyy-MM-dd HH:mm"/>
	</div>
	<div style="padding:10px 5px; border:1px solid #D9EDF7">
		${message.content}
	</div>
</div>
<div class="modal-footer">
	<button id="closeMessageModal" class="btn" data-dismiss="modal" aria-hidden="true">关闭</button>
	<a id="messageDetailBtn" class="btn green" target="_blank" href="message/gotoDetail.do?id=${message.id}&type=${message.type}&blank=0">详细</a>
</div>
<script type="text/javascript">
	$(function(){
		$("#messageDetailBtn").click(function(){
			$("#closeMessageModal").click();
		});
		
	});
</script>