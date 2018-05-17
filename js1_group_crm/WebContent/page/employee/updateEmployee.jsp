<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!-- BEGIN PAGE CONTAINER-->
<div class="container-fluid p-t-20" style="padding-top:8px;">
	<!--开始：第1行 -->
	<div class="row-fluid">
		<!--开始：第1行第1列 -->
		<div class="span12">
			<!--开始：第1行第1列中的盒子-->
			<div class="portlet box blue">
				<div class="portlet-title">
					<div class="caption"><i class="icon-cogs"></i>添加员工信息</div>
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
					<form id="updateEmployeeForm" action="employee/update.do" method="post">
						<input type="hidden" name="status" value="${requestScope.employee.status }" class="m-wrap medium">
						<input type="hidden" name="id" value="${requestScope.employee.id }" class="m-wrap medium">
						<table class="formTable" style="margin-left: 200px;">
							<tr>
								<td><label class="control-label">　姓　名<span class="required">*</span></label></td>
								<td>
									<input type="text" name="name" value="${requestScope.employee.name }" class="m-wrap medium" style="width:100px;">
								</td>
								<td><label class="control-label">登录账号<span class="required">*</span></label></td>
								<td>
									<input type="text" name="account" value="${requestScope.employee.account }" class="m-wrap medium" readonly="readonly" style="width:100px;">
								</td>
							</tr>
							<tr>
								<td><label class="control-label">　工　号<span class="required">*</span></label></td>
								<td>
									<input type="text" name="jobNumber" value="${requestScope.employee.jobNumber }" class="m-wrap medium" style="width:100px;">
								</td>
								<td><label class="control-label">　角　色<span class="required">*</span></label></td>
								<td>
									<select name="role.id" class="medium m-wrap">
										<c:forEach items="${requestScope.roles }" var="role">
											<option value="${role.id }"
											<c:if test="${requestScope.employee.role.id==role.id }"> selected="selected"</c:if>
											>${role.roleName }</option>
										</c:forEach>
									</select>
								</td>
							</tr>
							<tr>
								<td><label class="control-label">　手　机<span class="required">*</span></label></td>
								<td>
									<input type="text" name="mobile" value="${requestScope.employee.mobile }" class="m-wrap medium" style="width:100px;">
								</td>
								<td><label class="control-label">　性　别</label></td>
								<td>
									<div class="controls" style="margin-left: 30px;">
										<label class="radio">
											<span class="checked"><input type="radio" value="男" name="gender"
												<c:if test="${requestScope.employee.gender=='男'}">
											 		checked="checked"
												</c:if>
											 ></span>
											男
										</label>　　
										<label class="radio">
											<span class=""><input type="radio"
												<c:if test="${requestScope.employee.gender=='女'}">
											 		checked="checked"
												</c:if>
											 value="女" name="gender"></span>
											女
										</label>
									</div>
								</td>
							</tr>
							<tr>
								<td><label class="control-label">　部　门</label></td>
								<td>
									<select name="department.id" class="medium m-wrap">
										<c:forEach items="${requestScope.departments }" var="department">
											<option 
											<c:if test="${requestScope.employee.department.id==department.id }"> selected="selected"</c:if>
											value="${department.id }">${department.name }</option>
										</c:forEach>
									</select>
								</td>
								<td><label class="control-label">　座　机</label></td>
								<td>
									<input type="text" name="tel" value="${requestScope.employee.tel }" class="m-wrap medium" style="width:100px;">
								</td>
							</tr>
							<tr>
								<td><label class="control-label">　短　号</label></td>
								<td>
									<input type="text" name="cornet" value="${requestScope.employee.cornet }" class="m-wrap medium" style="width:100px;">
								</td>
								<td><label class="control-label">　身份证</label></td>
								<td>
									<input type="text" name="idCard" value="${requestScope.employee.idCard }" class="m-wrap medium" style="width:100px;">
								</td>
							</tr>
							<tr>
								<td><label class="control-label">　住　址</label></td>
								<td>
									<input type="text" name="address" value="${requestScope.employee.address }" class="m-wrap medium" style="width:100px;">
								</td>
								<td><label class="control-label">入职时间</label></td>
								<td>
								
									<input data-provide="datepicker" value="<fmt:formatDate value="${requestScope.employee.entryDate }" pattern="yyyy-MM-dd"/>" 
									name="entryDate" type="text" class="span12" style="height:30px;" readonly="readonly" size="14">
								</td>
							</tr>
							
							<tr>
								<td><label class="control-label">　户　籍</label></td>
								<td>
									<select name="census" class="medium m-wrap">
										<option value="是" <c:if test="${requestScope.employee.census=='是' }"> selected="selected"</c:if> >是</option>
										<option value="否" <c:if test="${requestScope.employee.census=='否' }"> selected="selected"</c:if> >否</option>
									</select>
								</td>
								<td><label class="control-label">紧急联系人</label></td>
								<td>
									<input type="text"  value="${requestScope.employee.remarkPhoneName }" name="remarkPhoneName" class="m-wrap medium" style="width:100px;">
								</td>
							</tr>
							<tr>
								<td><label class="control-label">紧急联系人电话</label></td>
								<td>
									<input type="text" value="${requestScope.employee.remarkPhone }" name="remarkPhone" class="m-wrap medium" style="width:100px;">
								</td>
								<td><label class="control-label">关　　系</label></td>
								<td>
									<input type="text" name="relation" class="m-wrap medium" value="${requestScope.employee.relation }" style="width:100px;">
								</td>
							</tr>
							<tr>
								<td><label class="control-label">入职资料是否完善</label></td>
								<td>
									<select name="entryDatum" class="medium m-wrap">
										<option value="是" <c:if test="${requestScope.employee.census=='是' }"> selected="selected"</c:if> >是</option>
										<option value="否" <c:if test="${requestScope.employee.census=='否' }"> selected="selected"</c:if> >否</option>
									</select>
								</td>
								<td><label class="control-label">谈判师</label></td>
								<td>
									<select name="talks" class="medium m-wrap">
										<option value="否" <c:if test="${requestScope.employee.talks=='否' }"> selected="selected"</c:if>>否</option>
										<option value="是" <c:if test="${requestScope.employee.talks=='是' }"> selected="selected"</c:if>>是</option>
									</select>
								</td>
							</tr>
							<tr>
								<td><label class="control-label">　备　注</label></td>
								<td>
									<textarea name="remark" rows="2" class="large m-wrap">${requestScope.employee.remark }</textarea>
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

<script src="${pageContext.request.contextPath}/media/js/jquery.validate.min.js" type="text/javascript"></script>
<script>
	$(function(){
		 $("#updateEmployeeForm").validate({
	        rules: {
	           name: {
			    required: true
			   },
			   account: {
			    required: true
			   },
			   jobNumber: {
			    required: true
			   },
			   position: {
			    required: true
			   },
			   mobile: {
			    required: true
			   }
	  		},
			messages: {
			   name: {
			    required: "必填"
			   },
			   account: {
			    required: "必填"
			   },
			   jobNumber: {
			    required: "必填"
			   },
			   position: {
			    required: "必填"
			   },
			   mobile: {
			    required: "必填"
			   } 
			},submitHandler: function() {
				$.post($("#updateEmployeeForm").attr("action"),$("#updateEmployeeForm").serialize(),function(data){
					if(data){
						aPost("employee/getEmployees.do");
					}else{
						$("#showUpdateError").removeClass("hide");
					}
				});
			}
		 });
		$("#cancelSave").click(function(){
			aPost("employee/getEmployees.do?pageIndex=1");
		});
	});
</script>