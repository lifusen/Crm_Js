<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<div class="container-fluid" style="padding-top:8px;">
	<div class="row-fluid">
		<div class="span12">
			<div class="portlet box blue">
				<div class="portlet-title">
					<div class="caption"><i class="icon-cogs"></i>添加渠道信息</div>
				</div>
				<div class="portlet-body">		
					<form id="addCustomerSourceForm" action="customerSource/add.do" method="post">
						<table class="formTable" style="width:360px;margin: 20px auto;">
							<tr>
								<td><label class="control-label">　渠道名称<span class="required">*</span></label></td>
								<td>
									<input type="text" name="sourceName" class="m-wrap medium" style="width:100px;">
								</td>
							</tr>
							<tr>
								<td><label class="control-label">　渠道备注</label></td>
								<td>
									<textarea name="remark" class="m-wrap" rows="3"></textarea>
								</td>
							</tr>
						</table>
						<div class="form-actions" style="padding:8px;text-align:center;margin-top:5px;margin-bottom:10px;" >
							<button class="btn blue" type="submit"><i class="icon-ok"></i> 保存</button>
							<a id="cancelSave" class="btn aPost" href="javascript:;"><i class="icon-remove"></i> 取消</a>
						</div>
					</form>
				</div>
			</div>
		</div>
	</div>
</div>

<script>
	$(function(){
		 $("#addCustomerSourceForm").validate({
	        rules: {
	           sourceName: {
			    required: true
			   },
			   remark: {
			    maxlength: 100
			   }
	  		},
			messages: {
				sourceName: {
			     required: '必填'
			    },
			    remark: {
			     maxlength: '最多输入{0}个字符!'
			    }
			},submitHandler: function() {
				$.post($("#addCustomerSourceForm").attr("action"),$("#addCustomerSourceForm").serialize(),function(data){
					if(data){
						alert('添加成功!');
						aPost("customerSource/getCustomerSources.do");
					}else{
						alert('添加失败!');
					}
				});
			}
		 });
		$("#cancelSave").click(function(){
			aPost("customerSource/getCustomerSources.do");
		});
	});
</script>