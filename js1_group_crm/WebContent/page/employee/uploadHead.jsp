<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!-- BEGIN PAGE CONTAINER-->
<div class="container-fluid p-t-20" style="padding-top: 8px;">
	<!--开始：第1行 -->
	<div class="row-fluid">
		<!--开始：第1行第1列 -->
		<div class="span12">
			<!--开始：第1行第1列中的盒子-->
			<div class="portlet box blue">
				<div class="portlet-title">
					<div class="caption">
						<i class="icon-cogs"></i>上传头像
					</div>
					<div class="tools">
						<a href="javascript:;" class="collapse"></a>
					</div>
				</div>
				<!--开始：第1行第1列中的盒子body-->
				<div class="portlet-body">
					<div id="showUpdateError" class="alert alert-error hide">
						<button data-dismiss="alert" class="close"></button>
						<strong>　　上传失败!</strong>　　请稍后重试...
					</div>
					<form id="addEmployeeForm" action="employee/uploadHead.do"
						enctype="multipart/form-data" method="post">
						<input type="hidden" name="id" value="${id}" class="m-wrap medium">
						<table class="formTable" style="margin-left: 200px;">
							<tr>
								<td>请选择jpg或者png格式的图片</td>
							</tr>
							<tr>
								<td><input type="file" name="employeeHeadImg"
									style="border: 0px; line-height: 0px;" /></td>
							</tr>
							<tr>
							</tr>
							<tr>
								<td><button class="btn blue" type="submit" id="saveSubmit">
								<i class="icon-ok"></i> 上传
							</button></td>
							</tr>
						</table>
					</form>
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
	$(function() {
		$("#addEmployeeForm").validate({
			rules : {
				employeeHeadImg : {
					required : true,
					imgType:true
				}
			},
			messages : {
				employeeHeadImg : {
					required : "必填"
				}
			},
			submitHandler : function(form) {
				$(form).ajaxSubmit({
					dataType : 'json',
					success : function(data) {
						if (data) {
							aPost("employee/getEmployees.do?pageIndex=1");
						} else {
							$("#showAddError").removeClass("hide");
						}
					}
				});
			}
		});
	});
</script>