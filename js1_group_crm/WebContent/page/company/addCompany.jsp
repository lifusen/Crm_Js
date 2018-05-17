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
					<div class="caption"><i class="icon-cogs"></i>添加公司</div>
					<div class="tools">
						<a href="javascript:;" class="collapse"></a>
					</div>
				</div>
				<!--开始：第1行第1列中的盒子body-->
				<div class="portlet-body">		
					<div id="showAddError" class="alert alert-error hide">
						<button data-dismiss="alert" class="close"></button>
						<strong>　　添加失败!</strong>　　请稍后重试...
					</div>
					<form id="addCompanyForm" action="company/addCompany.do"  enctype="multipart/form-data" method="post">
						<table class="formTable" style="margin-left: 400px;">
							<tr>
								<td><label class="control-label">　名　称<span class="required">*</span></label></td>
								<td>
									<input type="text" name="name" class="m-wrap medium" style="width:100px;">
								</td>
							</tr>
							<tr>
								<td><label class="control-label">　类　型</label></td>
								<td>
									<select name="type" class="medium m-wrap">
										<option value="1">业务中心</option>
										<option value="2">权证中心</option>
									</select>
								</td>
							</tr>
							<tr>
								<td><label class="control-label">　描　述</label></td>
								<td>
									<textarea name="remark" rows="2" class="large m-wrap"></textarea>
								</td>
							</tr>
						</table>
						<div class="form-actions" style="padding:8px;text-align:center;margin-top:5px;margin-bottom:10px;" >
							<button class="btn blue" type="submit" id="saveSubmit"><i class="icon-ok"></i> 保存</button>
							<a id="cancelSave" class="btn aPost" href="javascript:;"><i class="icon-remove"></i> 取消</a>
						</div>
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
	$(function(){
		 $("#addCompanyForm").validate({
	        rules: {
	           name: {
			    required: true
			   }
	  		},
			messages: {
			   name: {
			    required: "必填"
			   }
			},submitHandler: function(form) {
				$(form).ajaxSubmit({  
				    dataType: 'json',  
				    success: function(data){
				    	if(data){
							aPost("company/getCompanys.do?pageIndex=1");
						}else{
							$("#showAddError").removeClass("hide");
						}
				    }  
				});
			}
		 });
		$("#cancelSave").click(function(){
			aPost("company/getCompanys.do?pageIndex=1");
		});
	});
</script>