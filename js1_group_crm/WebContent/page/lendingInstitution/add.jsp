<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<div class="container-fluid" style="padding-top:8px;">
	<div class="row-fluid">
		<div class="span12">
			<div class="portlet box blue">
				<div class="portlet-title">
					<div class="caption"><i class="icon-cogs"></i>添加贷款机构信息</div>
				</div>
				<div class="portlet-body">		
					<form id="addLendingInstitutionForm" action="lendingInstitution/add.do" method="post">
						<table class="formTable" style="width:360px;margin: 20px auto;">
							<tr>
								<td><label class="control-label">　贷款机构名称<span class="required">*</span></label></td>
								<td>
									<input type="text" name="name" class="m-wrap medium" style="width:100px;">
								</td>
							</tr>
							<tr>
								<td><label class="control-label">　贷款机构类型</label></td>
								<td>
									<select name="type" style="font-size:14px;line-height:20px;">
										<option value="0">银行</option>
										<option value="1">小贷公司</option>
									</select>
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
		 $("#addLendingInstitutionForm").validate({
	        rules: {
	           name: {
			    required: true
			   }
	  		},
			messages: {
				name: {
			     required: '必填'
			    }
			},submitHandler: function() {
				$.post($("#addLendingInstitutionForm").attr("action"),$("#addLendingInstitutionForm").serialize(),function(data){
					if(data){
						alert('添加成功!');
						aPost("lendingInstitution/getList.do");
					}else{
						alert('添加失败!');
					}
				});
			}
		 });
		$("#cancelSave").click(function(){
			aPost("lendingInstitution/getList.do");
		});
	});
</script>