<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
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
					<div class="caption"><i class="icon-cogs"></i>修改${typeName}月计划</div>
				</div>
				<div class="portlet-body">		
					<form id="updatePlanForm" action="plan/update.do" method="post">
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
											<input type="hidden" name="id" value="${plan.id }"/>
											<input type="hidden" name="type" value="${plan.type}"/>
											<input type="text" name="visit" value="${plan.visit }"/>
										</td>
									</tr>
									<tr>
										<td>
											<label class="control-label">目标签单</label>
										</td>
										<td>
											<input type="text" name="sign" value="${plan.sign }"/>
										</td>
									</tr>
									<tr>
										<td>
											<label class="control-label">目标业绩</label>
										</td>
										<td>
											<input type="text" name="performance" value="<fmt:formatNumber pattern="#.#" maxFractionDigits="2" value="${plan.performance }"/>"/>
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
		 $("#updatePlanForm").validate({
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
				$.post($("#updatePlanForm").attr("action"),$("#updatePlanForm").serialize(),function(data){
					if(data){
						//alert('修改成功!');
						aPost('desktop/getDesktop.do');
					}else{
						alert('修改失败!');
					}
				});
			}
		 });
		$("#cancePlanSave").click(function(){
			aPost('desktop/getDesktop.do');
		});
	});
</script>