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
					<div class="caption"><i class="icon-cogs"></i>添加员工信息</div>
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
					<form id="addEmployeeForm" action="employee/addEmployee.do"  enctype="multipart/form-data" method="post">
						<table class="formTable" style="margin-left: 200px;">
							<tr>
								<td><label class="control-label">　姓　名<span class="required">*</span></label></td>
								<td>
									<input type="text" name="name" class="m-wrap medium" style="width:100px;">
								</td>
								<td><label class="control-label">登录账号<span class="required">*</span></label></td>
								<td>
									<input type="text" name="account" class="m-wrap medium" style="width:100px;">
								</td>
							</tr>
							<tr>
								<td><label class="control-label">　工　号<span class="required">*</span></label></td>
								<td>
									<input type="text" name="jobNumber" class="m-wrap medium" style="width:100px;">
								</td>
								<td><label class="control-label">　角　色<span class="required">*</span></label></td>
								<td>
									<select id="roleSelect" name="role.id" class="medium m-wrap">
										<c:forEach items="${requestScope.roles }" var="role">
											<c:if test="${role.type!=1 }">
												<option value="${role.id }">${role.roleName }</option>
											</c:if>
										</c:forEach>
									</select>
								</td>
							</tr>
							<tr>
								<td><label class="control-label">　手　机<span class="required">*</span></label></td>
								<td>
									<input type="text" name="mobile" class="m-wrap medium" style="width:100px;">
								</td>
								<td><label class="control-label">　性　别</label></td>
								<td>
									<div class="controls" style="margin-left: 30px;">
										<label class="radio">
											<span class="checked"><input type="radio" value="男" name="gender" checked="checked"></span>
											男
										</label>　　
										<label class="radio">
											<span class=""><input type="radio" value="女" name="gender"></span>
											女
										</label>
									</div>
								</td>
							</tr>
							<tr>
								<c:if test="${requestScope.companys!=null }">
									<td><label class="control-label">　公　司</label></td>
								<td>
									<select name="companyId" class="medium m-wrap" id="companyId">
										<c:forEach items="${requestScope.companys }" var="company">
											<option value="${company.id }">${company.name }</option>
										</c:forEach>
									</select>
								</td>
								</c:if>
								<c:if test="${loginUser.companyId!=null }">
									<td><label class="control-label">　公　司</label></td>
									<td>
										<select name="companyId" class="medium m-wrap" id="companyId">
											<option value="${loginUser.companyId }">${allCompanyNameMap[loginUser.companyId]}</option>
										</select>
									</td>
								</c:if>
								<td><label class="control-label">　部　门</label></td>
								<td>
									<select id="departmentSelect" name="department.id" class="medium m-wrap">
										<%-- <c:forEach items="${requestScope.departments }" var="department">
											<option value="${department.id }">${department.name }</option>
										</c:forEach> --%>
									</select>
								</td>
							</tr>
							<tr>
								<td><label class="control-label">　座　机</label></td>
								<td>
									<input type="text" name="tel" class="m-wrap medium" style="width:100px;">
								</td>
								<td><label class="control-label">　短　号</label></td>
								<td>
									<input type="text" name="cornet" class="m-wrap medium" style="width:100px;">
								</td>
							</tr>
							<tr>
								<td><label class="control-label">　身份证</label></td>
								<td>
									<input type="text" name="idCard" class="m-wrap medium" style="width:100px;">
								</td>
								<td><label class="control-label">　住　址</label></td>
								<td>
									<input type="text" name="address" class="m-wrap medium" style="width:100px;">
								</td>
							</tr>
							<tr>
								<td><label class="control-label">入职时间</label></td>
								<td>
									<input data-provide="datepicker" name="entryDate" type="text" class="span12" style="height:30px;" readonly="readonly" size="14">
								</td>
								<td><label class="control-label">　户　籍</label></td>
								<td>
									<select name="census" class="medium m-wrap">
										<option value="是">是</option>
										<option value="否">否</option>
									</select>
								</td>
							</tr>
							<tr>
								<td><label class="control-label">紧急联系人</label></td>
								<td>
									<input type="text" name="remarkPhoneName" class="m-wrap medium" style="width:100px;">
								</td>
								<td><label class="control-label">紧急联系人电话</label></td>
								<td>
									<input type="text" name="remarkPhone" class="m-wrap medium" style="width:100px;">
								</td>
							</tr>
							<tr>
								<td><label class="control-label">关　　系</label></td>
								<td>
									<input type="text" name="relation" class="m-wrap medium" style="width:100px;">
								</td>
								<td><label class="control-label">入职资料是否完善</label></td>
								<td>
									<select name="entryDatum" class="medium m-wrap">
										<option value="是">是</option>
										<option value="否">否</option>
									</select>
								</td>
							</tr>
							<tr>
								<td><label class="control-label">谈判师</label></td>
								<td>
									<select name="talks" class="medium m-wrap">
										<option value="否">否</option>
										<option value="是">是</option>
									</select>
								</td>
								<td><label class="control-label">　备　注</label></td>
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
		 $("#addEmployeeForm").validate({
	        rules: {
	           name: {
			    required: true
			   },
			   account: {
			    required: true,
			    remote:"employee/checkAccountUnique.do?_="+new Date()
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
			    required: "必填",
			    remote:"该账号已经存在"
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
			},submitHandler: function(form) {
				var departmentId = $("#departmentSelect").val();
				var roleId = $("#roleSelect").val();
				if(roleId==3){
					$.post("employee/checkRole.do",{roleId:roleId,departmentId:departmentId},function(data){
						if(!data){
							$.messager.alert("错误","一个部门只能有一个部门经理");
						}else{
							$(form).ajaxSubmit({  
							    dataType: 'json',  
							    success: function(data){
							    	if(data){
										aPost("employee/getEmployees.do?pageIndex=1");
									}else{
										$("#showAddError").removeClass("hide");
									}
							    }  
							});
						}
					});
				}else{
					$(form).ajaxSubmit({  
					    dataType: 'json',  
					    success: function(data){
					    	if(data){
								aPost("employee/getEmployees.do?pageIndex=1");
							}else{
								$("#showAddError").removeClass("hide");
							}
					    }  
					});
				}
				
			}
		 });
		 
		 getDemp();
		 $("#companyId").change(function(){
			 getDemp();
		 });
		$("#cancelSave").click(function(){
			aPost("employee/getEmployees.do?pageIndex=1");
		});
	});
	
	function getDemp(){
		var id = $("#companyId").val();
		$.post("department/getByCompanyId.do",{companyId:id},function(data){
			var s = $("#departmentSelect");
			s.empty();
			$.each(data,function(i,item){
				var op = $("<option />",{value:item.id,text:item.name});
				op.appendTo(s);
			});
		});
	}
</script>