<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!-- BEGIN PAGE CONTAINER-->
<div class="container-fluid p-t-20" style="padding-top:8px;">
	<div class="row-fluid">
		<div class="span12">
			<div class="portlet box blue">
				<div class="portlet-title">
					<div class="caption"><i class="icon-cogs"></i>渠道信息管理</div>
				</div>
				<div class="portlet-body">		
					<div class="clearfix">
						<div class="btn-group">
							<a class="btn green aPost" href="page/customerSource/addCustomerSource.jsp"><i class="icon-plus"></i> 添加渠道</a>
						</div>
					</div>
					<table class="table table-striped table-hover table-bordered table-condensed" id="customerSourceTable">
						<thead>
							<tr>
								<th width="10%">序号</th>
								<th width="30%">渠道名称</th>
								<th width="40%">渠道描述</th>
								<th width="20%">操作</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach var="customerSource" items="${requestScope.customerSources }" varStatus="vs">
								<tr>
									<td>${vs.count }</td>
									<td>${customerSource.sourceName }</td>
									<td>${customerSource.remark }</td>
									<td>
										<button type="button" class="btn blue mini" onclick="updateCustomerSource(${customerSource.id})">修改</button>
										<button type="button" class="btn yellow mini" onclick="deleteCustomerSource(${customerSource.id})">删除</button>
									</td>
								</tr>
							</c:forEach>
						</tbody>
					</table>												
				</div>										
			</div>
		</div>
	</div>
</div>
<!-- END PAGE CONTAINER--> 

<script type="text/javascript">

/**
 * 修改
 */
function updateCustomerSource(id){
	aPost("customerSource/gotoUpdate.do?id="+id);
}
/**
 * 删除
 */
function deleteCustomerSource(id){
	if(confirm('您确定要删除吗?')){
		$.post("customerSource/delete.do",{id:id},function(data){
			if(data){
				alert('删除成功!');
				aPost("customerSource/getCustomerSources.do");
			}else{
				alert('删除失败,该渠道下有客户存在!');
			}
		});
	}
}

$(function() {
	$('.aPost').click(function(){
		aPost($(this).attr('href'));
		return false;
	});
	
	
	$("#addEmployeeInfo").click(function(){
		aPost("employee/gotoAdd.do");
	});
	
	$(".employee-edit").click(function(){
		if(confirm("确定设置为离职?")){
			var id = $(this).attr("id");
			id = id.substring(id.lastIndexOf("-")+1);
			$.post("employee/updateStatusDimission.do",{id:id},function(data){
				aPost("employee/getEmployees.do?pageIndex=1");
			});
		}
	});
	$(".employee-delete").click(function(){
		if(confirm("确定删除?")){
			var id = $(this).attr("id");
			id = id.substring(id.lastIndexOf("-")+1);
			$.post("employee/updateStatusDelete.do",{id:id},function(data){
				aPost("employee/getEmployees.do?pageIndex=1");
			});
		}
	});
});
</script>