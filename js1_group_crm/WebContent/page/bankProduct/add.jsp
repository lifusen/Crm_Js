<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<style type="text/css">
	.simpleEntryForm{
		width:500px;
		margin: 20px auto;
	}
	.simpleEntryForm .labelTd{
		width:100px;
		padding-right:5px;
		text-align:right;
	}
</style>
<div class="container-fluid" style="padding-top:8px;">
	<div class="row-fluid">
		<div class="span12">
			<div class="portlet box blue">
				<div class="portlet-title">
					<div class="caption"><i class="icon-cogs"></i>添加银行产品信息</div>
				</div>
				<div class="portlet-body">		
					<form id="addBankProductForm" action="bankProduct/add.do" method="post">
						<table class="formTable simpleEntryForm">
							<tr>
								<td class="labelTd"><label class="control-label">　银行产品名称<span class="required">*</span></label></td>
								<td>
									<input type="text" name="name" class="m-wrap medium" style="width:100px;">
								</td>
							</tr>
							<tr>
								<td class="labelTd"><label class="control-label">　银行产品备注&nbsp;</label></td>
								<td>
									<textarea name="remark" class="m-wrap" rows="3"></textarea>
								</td>
							</tr>
						</table>
						<div class="form-actions" style="padding:8px;text-align:center;margin-top:5px;margin-bottom:10px;" >
							<button class="btn blue" type="submit"><i class="icon-ok"></i> 保存</button>
							<a id="canceBankProductSave" class="btn" href="javascript:;"><i class="icon-remove"></i> 取消</a>
						</div>
					</form>
				</div>
			</div>
		</div>
	</div>
</div>

<script>
	$(function(){
		 $("#addBankProductForm").validate({
	        rules: {
	           name: {
			    required: true,
			    remote:'bankProduct/checkNameIsUnique.do'
			   },
			   remark: {
			    maxlength: 100
			   }
	  		},
			messages: {
				name: {
			     required: '必填',
			     remote:'该名称已经存在!'
			    },
			    remark: {
			     maxlength: '最多输入{0}个字符!'
			    }
			},submitHandler: function() {
				$.post($("#addBankProductForm").attr("action"),$("#addBankProductForm").serialize(),function(data){
					if(data){
						alert('添加成功!');
						aPost("bankProduct/getList.do");
					}else{
						alert('添加失败!');
					}
				});
			}
		 });
		$("#canceBankProductSave").click(function(){
			aPost("bankProduct/getList.do");
		});
	});
</script>