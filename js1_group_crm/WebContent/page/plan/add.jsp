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
					<div class="caption"><i class="icon-cogs"></i>制定${typeName}月计划</div>
				</div>
				<div class="portlet-body">	
					<form id="addPlanForm" action="plan/add.do" method="post">
						<div class="row-fluid" style="">
							<div class="span3">
							</div>
							<div class="span6" style="padding:10px;padding-left:100px;">
								<table style="width:100%;">
									<tr>
										<td width="70" style="min-width:62px;">
											<label class="control-label">目标上门</label>
										</td>
										<td>
											<input type="hidden" name="type" value="${type}"/>
											<input type="text" name="visit"/>
										</td>
									</tr>
									<tr>
										<td>
											<label class="control-label">目标签单</label>
										</td>
										<td>
											<input type="text" name="sign"/>
										</td>
									</tr>
									<tr>
										<td>
											<label class="control-label">目标业绩</label>
										</td>
										<td>
											<input type="text" name="performance"/>
										</td>
									</tr>
								</table>
							</div>
							<div class="span3">
							</div>
						</div>	
						<div class="form-actions" style="padding:8px;text-align:center;margin-top:0px;margin-bottom:10px;" >
							<button class="btn blue" type="submit"><i class="icon-ok"></i> 保存</button>
							<a id="cancePlanSave" class="btn" href="javascript:;"><i class="icon-remove"></i> 取消</a>
						</div>
					</form>
				</div>
			</div>
		</div>
	</div>
</div>

<script>
	$(function(){
		 $("#addPlanForm").validate({
	        rules: {
	           visit: {
			     required : true,
			     digits : true
			   },
	           sign: {
			     required : true,
			     digits : true
			   },
			   performance: {
			     required : true,
			     number : true
			   }
	  		},
			messages: {
			   visit: {
			     required : '必填',
			     digits : '请输入整数'
			   },
	           sign: {
			     required : '必填',
			     digits : '请输入整数'
			   },
			   performance: {
			     required : '必填',
			     number : '请输入有效的数字'
			   }
			},submitHandler: function() {
				$.post($("#addPlanForm").attr("action"),$("#addPlanForm").serialize(),function(data){
					if(data){
						//alert('添加成功!');
						aPost('desktop/getDesktop.do');
					}else{
						alert('添加失败!');
					}
				});
			}
		 });
		$("#cancePlanSave").click(function(){
			aPost('desktop/getDesktop.do');
		});
	});
</script>