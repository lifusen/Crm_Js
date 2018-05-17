<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<table class="table table-striped table-hover table-bordered" id="employee-table">
	<thead>
		<tr>
			<th>序号</th>
			<th>公司</th>
			<th>部门</th>
			<th>角色</th>
			<th>编号</th>											
			<th>账号</th>
			<th>姓名</th>
			<th>性别</th>
			<th>手机</th>
			<th>入职时间</th>
			<th>离职时间</th>
			<th>状态</th>
			<th>操作</th>
		</tr>
	</thead>
	<tbody>
		<c:forEach items="${requestScope.employees }" var="employee" varStatus="vst">
			<tr>
				<td>${((pageIndex-1)*pageSize)+(vst.index+1) }</td>
				<td>${allCompanyNameMap[employee.companyId]}</td>
				<td>${employee.department.name }</td>
				<td>${employee.role.roleName }</td>
				<td>${employee.jobNumber }</td>
				<td>${employee.account }</td>
				<td>${employee.name }</td>
				<td>${employee.gender }</td>
				<td>${employee.mobile }</td>
				<td><fmt:formatDate value="${employee.entryDate }" pattern="yyyy-MM-dd"/></td>
				<td><fmt:formatDate value="${employee.dimissionDate }" pattern="yyyy-MM-dd"/></td>
				<td>
					<c:if test="${employee.status==0 }">
						离职
					</c:if>
					<c:if test="${employee.status==1 }">
						在职
					</c:if>
					<c:if test="${employee.status==2 }">
						在职
					</c:if>
				</td>
				<td>
				<c:if test="${employee.status==1 }">
					<a class="employee-update btn green mini" id="employee-udpate-${employee.id }" href="javascript:;">编辑</a>
	 			 	<a class="btn blue mini" href="javascript:;" onclick="uploadHead(${employee.id })" >修改头像</a>
					<c:if test="${employee.id != 1}">
						<a class="employee-edit btn yellow mini"  onclick="updateEmployee(${employee.id })" href="javascript:;">离职</a>
					</c:if>
				</c:if>
				
<%--  			<a class="btn yellow mini edit employee-delete" id="employee-delete-${employee.id }" href="javascript:;">删除</a> --%>
 				<c:if test="${employee.id != 1}">
	 				<a class="btn red mini" href="javascript:;" onclick="deleteEmployyEmployee(${employee.id })">删除</a>
 				</c:if>
				</td>
			</tr>
		</c:forEach>
	</tbody>
</table>
<jsp:include page="../common/paginator.jsp" />
<script>
jQuery(document).ready(function() {
	$("#addEmployeeInfo").click(function(){
		aPost("employee/gotoAdd.do");
	});

	$(".employee-update").click(function(){
		var id = $(this).attr("id");
		id = id.substring(id.lastIndexOf("-")+1);
		aPost("employee/gotoUpdate.do?id="+id);
	});
});
function updateEmployee(id){
	if(confirm("确定设置为离职?")){
		$.post("employee/updateStatusDimission.do",{id:id},function(data){
			if(data){
				aPost("employee/getEmployees.do");
			}else{
				alert("操作失败，请核实该员工是否还有客户存在！");
			}
		});
	}
}

function deleteEmployyEmployee(id){
	if(confirm("确定删除?")){
		$.post("employee/updateStatusDelete.do",{id:id},function(data){
			if(data){
				aPost("employee/getEmployees.do");
			}else{
				alert("操作失败，请核实该员工是否还有客户存在！");
			}
		});
	}
}
function uploadHead(id){
	aPost("employee/gotoUploadHead.do?id="+id);
}
</script>
