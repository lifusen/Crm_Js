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
					<div class="caption"><i class="icon-cogs"></i>修改密码</div>
					<div class="tools">
						<a href="javascript:;" class="collapse"></a>
					</div>
				</div>
				<div class="portlet-body">		
					<div id="showAddError" class="alert alert-error hide">
						<button data-dismiss="alert" class="close"></button>
						<strong>　　修改失败，</strong>旧密码不正确！
					</div>
					<form id="updatePasswordForm" action="employee/updatePassword.do" method="post">
						<table class="formTable" style="margin-left: 400px;">
							<tr>
								<td><label class="control-label">　旧密码<span class="required">*</span></label></td>
								<td>
									<input type="password" name="oldPassword" class="m-wrap medium" style="width:100px;">
								</td>
							</tr>
							<tr>
								<td><label class="control-label">　新密码<span class="required">*</span></label></td>
								<td>
									<input type="password" name="newPassword" id="newPassword" class="m-wrap medium" style="width:100px;">
								</td>
							</tr>
							<tr>
								<td><label class="control-label">　确认密码<span class="required">*</span></label></td>
								<td>
									<input type="password" name="confirmNewPassword" class="m-wrap medium" style="width:100px;">
								</td>
							</tr>
						</table>
						<div class="form-actions" style="padding:8px;text-align:center;margin-top:5px;margin-bottom:10px;" >
							<button class="btn blue" type="submit" id="saveSubmit"><i class="icon-ok"></i> 保存</button>
						</div>
					</form>
				</div>
			</div>
		</div>
	</div>
</div>
<!-- END PAGE CONTAINER--> 

<script>
	$(function(){
		 $("#updatePasswordForm").validate({
	        rules: {
	        	oldPassword: {
			    required: true
			   },
			   newPassword: {
			    required: true
			   },
			   confirmNewPassword: {
				   equalTo:"#newPassword"
			   }
	  		},
			messages: {
				oldPassword: {
				 required: "必填"
			   },
			   newPassword: {
			    required: "必填"
			   },
			   confirmNewPassword: {
				   equalTo:"两次输入不一致"
			   }
			},submitHandler: function() {
				$.post($("#updatePasswordForm").attr("action"),$("#updatePasswordForm").serialize(),function(data){
					if(data){
						window.location = "index.jsp";
					}else{
						$("#showAddError").removeClass("hide");
					}
				});
			}
		 });
	});
</script>