<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<table class="table table-striped table-hover table-bordered">
	<thead>
		<tr>
			<th style="width: 50px;">序号</th>
			<th style="min-width: 500px;text-align:center;">${typeName}标题</th>
			<th style="width: 60px;">发布人</th>
			<th style="width: 110px;">发布时间</th>
			<th style="min-width: 100px;">操作</th>
		</tr>
	</thead>
	<tbody>
	<c:forEach var="message" items="${requestScope.messages }" varStatus="vs">
		<tr>
		<td>${vs.count }</td>
		<td>${message.title }</td>
		<td>${message.publisher.name }</td>
		<td><fmt:formatDate value="${message.pubdate }" pattern="yyyy-MM-dd HH:mm"/></td>
		<td>
			<button type="button" class="btn blue mini" onclick="updateBankProduct(${message.id})">修改</button>
			<button type="button" class="btn yellow mini" onclick="deleteBankProduct(${message.id})">删除</button>
			</td>
		</tr>
	</c:forEach>
	</tbody>
</table>	

<jsp:include page="../../page/common/paginator.jsp"/>

<script type="text/javascript">

/**
 * 修改
 */
function updateBankProduct(id){
	aPost("message/gotoUpdate.do?id="+id+"&type=${type}");
}
/**
 * 删除
 */
function deleteBankProduct(id){
	if(confirm('您确定要删除吗?')){
		$.post("message/delete.do",{id:id},function(data){
			if(data){
				alert('删除成功!');
				aPost("message/getList.do");
			}else{
				alert('删除失败!');
			}
		});
	}
}
</script>