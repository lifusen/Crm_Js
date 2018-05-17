<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<table class="table table-striped table-hover table-bordered" id="bankproductTable">
	<thead>
		<tr>
			<th style="width:50px;">序号</th>
			<th style="min-width: 500px;text-align:center;">${typeName}标题</th>
			<th style="width: 60px;">发布人</th>
			<th style="width: 110px;">发布时间</th>
		</tr>
	</thead>
	<tbody>
	<c:forEach var="message" items="${requestScope.messages }" varStatus="vs">
		<tr data-mid="${message.id }">
		<td>${((pageIndex-1)*pageSize)+(vs.index+1) }</td>
		<td>${message.title }</td>
		<td>${message.publisher.name }</td>
		<td><fmt:formatDate value="${message.pubdate }" pattern="yyyy-MM-dd HH:mm"/></td>
	</c:forEach>
	</tbody>
</table>	

<jsp:include page="../../page/common/paginator.jsp"/>

<script type="text/javascript">
	$(function(){
		$('#bankproductTable tr').dblclick(function(){
			var id = $(this).attr('data-mid');
			$('#bankproducatContainer').load('/crm/message/gotoDetail.do?id='+id);
		});
	})
</script>
