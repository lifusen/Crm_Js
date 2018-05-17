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
					<div class="caption"><i class="icon-cogs"></i>修改部门</div>
					<div class="tools">
						<a href="javascript:;" class="collapse"></a>
					</div>
				</div>
				<!--开始：第1行第1列中的盒子body-->
				<div class="portlet-body">
					<div id="showUpdateError" class="alert alert-error hide">
						<button data-dismiss="alert" class="close"></button>
						<strong>　　修改失败!</strong>　　请稍后重试...
					</div>
					<form id="updateDepartmentForm" action="department/updateDepartment.do" method="post">
					<input type="hidden" value="${requestScope.department.id }" id="deId" name="id">
						<table class="formTable" style="margin-left: 300px;">
							<tr>
								<td><label class="control-label">　名　称<span class="required">*</span></label></td>
								<td>
									<input type="text" id="name" name="name" value="${requestScope.department.name}" class="m-wrap medium" style="width:100px;">
								</td>
							</tr>
							<c:if test="${loginUser.type == 'SUPER_ROLE'}">
								<tr>
									<td><label class="control-label">　中　心<span class="required">*</span></label></td>
									<td>
										<select id="companyId" name="companyId" class="medium m-wrap">
											<option value="-1">请选择中心</option>
											<c:forEach items="${companys }" var="company">
												<option 
												<c:if test="${requestScope.department.companyId==company.id}"> selected='selected'</c:if>
												 value="${company.id }">${company.name }</option>
											</c:forEach>
										</select>
									</td>
								</tr>
							</c:if>
							<tr>
								<td><label class="control-label">　类　型<span class="required">*</span></label></td>
								<td>
									<select id="type" name="type" class="medium m-wrap">
										<option value="-1">是否是业务部</option>
										<option value="业务" <c:if test="${requestScope.department.type == '业务'}"> selected="selected"</c:if> >是</option>
										<option value="其他" <c:if test="${requestScope.department.type == '其他'}"> selected="selected"</c:if>>否</option>
									</select>
								</td>
							</tr>
							<tr>
								<td><label class="control-label">　备　注</label></td>
								<td>
									<textarea name="remark" id="remark" rows="2" class="large m-wrap">${requestScope.department.remark }</textarea>
								</td>
							</tr>
						</table>
						<div class="form-actions" style="padding:8px;text-align:center;margin-top:5px;margin-bottom:10px;" >
							<button class="btn blue" type="button" id="saveSubmit"><i class="icon-ok"></i> 保存</button>
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
<script src="${pageContext.request.contextPath}/media/js/jquery.validate.min.js" type="text/javascript"></script>
<script>
	$(function(){
		 $("#saveSubmit").click(function(){
		    var id = $("#deId").val();
		    var name = $("#name").val();
			var remark = $("#remark").val();
			var type = $("#type").val();
			var companyId = $("#companyId").val();
			if(companyId=="-1"){
				alert("请选择中心");
				return;
			}
			if(type=="-1"){
				alert("请选择部门类型");
				return;
			}
			$.post($("#updateDepartmentForm").attr("action"),{id:id,name:name,remark:remark,
				type:type,companyId:companyId},function(data){
				if(data){
					aPost("department/departmentList.do");
				}else{
					$("#showUpdateError").removeClass("hide");
				}
			});
		 });
		$("#cancelSave").click(function(){
			aPost("department/departmentList.do");
		});
	});
</script>