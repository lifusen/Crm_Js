<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<div class="clearfix">
	<div class="btn-group">
		<a id="addCompany" class="btn green aPost" href="javascript:;"><i class="icon-plus"></i> 添加公司</a>
	</div>
</div>
<table class="table table-striped table-hover table-bordered" id="employee-table">
	<thead>
		<tr>
			<th style="width: 5%;">序号</th>
			<th style="width: 10%;">名字</th>
			<th style="width: 10%;">类型</th>
			<th style="width: 15%;">备注</th>
			<th style="width: 5%;">创建人</th>											
			<th style="width: 13%;">创建时间</th>
			<th style="width: 7%;">修改人</th>
			<th style="width: 13%;">修改时间</th>
			<th style="width: 10%;">操作</th>
		</tr>
	</thead>
	<tbody>
		<c:forEach items="${requestScope.companys }" var="company" varStatus="vst">
			<tr>
				<td>${((pageIndex-1)*pageSize)+(vst.index+1) }</td>
				<td>${company.name }</td>
				<td>
					<c:if test="${company.type==1}">
						业务中心
					</c:if>
					<c:if test="${company.type==2}">
						权证中心
					</c:if>
				</td>
				<td>${company.remark}</td>
				<td>${company.createrName}</td>
				<td><fmt:formatDate value="${company.createTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
				<td>${company.editorName}</td>
				<td><fmt:formatDate value="${company.updateTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
				<td>
					　　<a class="company-update btn green mini" id="company-udpate-${company.id }" href="javascript:;">编辑</a>
 				</td>
			</tr>
		</c:forEach>
	</tbody>
</table>
<jsp:include page="../common/paginator.jsp" />
<script>
jQuery(document).ready(function() {
	$("#addCompany").click(function(){
		aPost("company/gotoAdd.do");
	});

	$(".company-update").click(function(){
		var id = $(this).attr("id");
		id = id.substring(id.lastIndexOf("-")+1);
		aPost("company/gotoUpdate.do?id="+id);
	});
});

</script>