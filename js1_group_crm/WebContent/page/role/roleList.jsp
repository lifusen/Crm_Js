<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!-- BEGIN PAGE CONTAINER-->
<div class="container-fluid p-t-20" style="padding-top:8px;">
	<!--开始：第1行 -->
	<div class="row-fluid">
		<!--开始：第1行第1列 -->
		<div class="span12">
			<!--开始：第1行第1列中的盒子-->
			<div class="portlet box blue">
				<div class="portlet-title">
					<div class="caption"><i class="icon-cogs"></i>角色管理</div>
					<div class="tools">
						<a href="javascript:;" class="collapse"></a>
					</div>
				</div>
				<!--开始：第1行第1列中的盒子body-->
				<div class="portlet-body">		
					<div class="clearfix">
						<div class="btn-group">
							<a id="addRole" class="btn green aPost" href="javascript:;"><i class="icon-plus"></i> 添加角色</a>
						</div>
					</div>
					<table class="table table-striped table-hover table-bordered">
						<thead>
							<tr>
								<th>序号</th>
								<th>名称</th>
								<th>备注</th>
								<th>操作</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${requestScope.roles }" varStatus="vst" var="role">
								<tr>
									<td>${vst.index+1 }</td>
									<td>${role.roleName }</td>
									<td>${role.remark }</td>
									<td>
										<a class="role-edit btn blue mini" id="e-${role.id }" href="javascript:;">编辑</a>
										<%-- <c:if test="${role.readOnly==1 }">
											<a class="role-delete btn yellow mini" id="${role.id }" href="javascript:;">删除</a>
										</c:if> --%>
									</td>
								</tr>
							</c:forEach>
						</tbody>
					</table>												
				</div>										
				<!--结束：第1行第1列中的盒子的body-->
			</div>
			<!--结束：第1行第1列中的盒子-->
		</div>
		<!--结束：第1行第1列 -->					
	</div>
	<!--结束：第1行 -->
</div>
<!-- END PAGE CONTAINER--> 

<script>
jQuery(document).ready(function() {
	$("#addRole").click(function(){
		aPost("role/gotoAdd.do");
	});
	
	$(".role-edit").click(function(){
		var id = $(this).attr("id");
		id = id.substring(id.lastIndexOf("-")+1);
		aPost("role/gotoUpdate.do?id="+id);
	});
	$(".role-delete").click(function(){
		if(confirm("确定删除?")){
			var id = $(this).attr("id");
			$.post("role/delete.do",{id:id},function(data){
				if(data){
					aPost("role/roleList.do");
				}else{
					alert("删除失败！");
				}
			});
		}
	});
});
</script>