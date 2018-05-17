<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<div class="container-fluid" style="padding-top:8px;">
	<div class="row-fluid">
		<div class="span12">
			<div class="portlet box blue">
				<div class="portlet-title">
					<div class="caption"><i class="icon-cogs"></i>修改银行产品信息</div>
				</div>
				<div class="portlet-body">		
					<div id="showUpdateError" class="alert alert-error hide">
						<button data-dismiss="alert" class="close"></button>
						<strong>　　修改失败!</strong>　　请稍后重试...
					</div>
					<form id="updateBankProductForm" action="bankProduct/update.do" method="post">
						<table class="formTable" style="width:360px;margin: 20px auto;">
							<tr>
								<td><label class="control-label">　银行产品名称<span class="required">*</span></label></td>
								<td>
									<input type="hidden" name="id" value="${bankProduct.id }"/>
									<input type="text" name="name" value="${bankProduct.name}" class="m-wrap medium" style="width:100px;">
								</td>
							</tr>
							<tr>
								<td><label class="control-label">　银行产品备注</label></td>
								<td>
									<textarea name="remark" class="m-wrap" rows="3">${bankProduct.remark}</textarea>
								</td>
							</tr>
						</table>
						<div class="form-actions" style="padding:8px;text-align:center;margin-top:5px;margin-bottom:10px;" >
							<button class="btn blue" type="submit"><i class="icon-ok"></i> 保存</button>
							<a id="cancelBankProductSave" class="btn aPost" href="javascript:;"><i class="icon-remove"></i> 取消</a>
						</div>
					</form>
				</div>
			</div>
		</div>
	</div>
</div>

<script>
	$(function(){
		 $("#updateBankProductForm").validate({
	        rules: {
	           name: {
			    required: true
			   },
			   remark: {
			    maxlength: 100
			   }
	  		},
			messages: {
				name: {
			     required: '必填'
			    },
			    remark: {
			     maxlength: '最多输入{0}个字符!'
			    }
			},submitHandler: function() {
				$.post($("#updateBankProductForm").attr("action"),$("#updateBankProductForm").serialize(),function(data){
					if(data){
						alert('修改成功!');
						aPost("bankProduct/getList.do");
					}else{
						$("#showUpdateError").removeClass("hide");
					}
				});
			}
		 });
		$("#cancelBankProductSave").click(function(){
			aPost("bankProduct/getList.do");
		});
	});
</script>