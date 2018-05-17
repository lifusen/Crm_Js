<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<style>
	#updateConfigForm{
		padding-top:10px;
		width:500px;
		border:0px solid red;
		margin:0px auto;
	}
	#updateConfigForm table td.name{
		width:150px;
		border:0px solid red;
		text-align:right;
		padding-right:5px;
	}
	#updateConfigForm table td.value{
		line-height:35px;
	}
	#updateConfigForm table td.value input{
		
	}
	
	#updateCustomerNoOfEmployee>div{
		margin-top: 10px;margin-bottom: 10px;
		height:100px ;
	}
	#updateCustomerNoOfEmployee>div #customerNOOfEmployee{
		border-right:1px solid lightblue;
		float:left;
		width:45%;
	}
	#updateCustomerNoOfEmployee>div #customerNOOfEmployee select{
		width:30%;
	}
	#updateCustomerNoOfEmployee>div #customerNOOfEmployee>div #employeeRole{
		width:40%;
		text-align: center;
		line-height: 40px;
		float: left;
	}
	#updateCustomerNoOfEmployee>div #customerNOOfEmployee>div #currentNO{
		width:40%;
		margin-top: 3px;
		text-align: center;
		line-height: 29px;
		float: left;
	}
	#updateCustomerNoOfEmployee>div #customerNOOfEmployee #customerNOView{
		margin-top: 15px;
	}
	#updateCustomerNoOfEmployee>div #customerNOOfEmployee>div button{
		margin-top:5px;
		float: left;
	}
	#updateCustomerNoOfEmployee>div #customerNOOfEmployee input{
		width:30px;
	}
	#updateCustomerNoOfEmployee>div #roleCustomerNO{
		padding-left: 3%;
		float:left;
		width:40%;
	}
	#updateCustomerNoOfEmployee>div #roleCustomerNO #roleNO{
		margin-top: 20px;
		height: 40px;
		border: 0px solid red;
	}
	#updateCustomerNoOfEmployee>div #roleCustomerNO #roleNO label{
		margin-right: 40px;
		float: left;
	}
	#updateCustomerNoOfEmployee>div #roleCustomerNO #roleNO input{
		width: 50px;
	}
	#updateCustomerNoOfEmployee>div #roleCustomerNO #roleNO button{
		float: left;
	}
	#updateCustomerNoOfEmployee>div #roleCustomerNO select{
		width:30%;
	}
	#updateCustomerNoOfEmployee>div #clearFloat{
		clear: both;
	}
	
</style>
<!-- BEGIN PAGE CONTAINER-->
<div class="container-fluid p-t-20" style="padding-top:8px;">
	<!--开始：第1行 -->
	<div class="row-fluid">
		<!--开始：第1行第1列 -->
		<div class="span12">
			<!--开始：第1行第1列中的盒子-->
			<div class="portlet box blue">
				<div class="portlet-title">
					<div class="caption"><i class="icon-cogs"></i>参数配置管理</div>
					<div class="tools">
						<a href="javascript:;" class="collapse"></a>
					</div>
				</div>
				<!--开始：第1行第1列中的盒子body-->
				<div class="portlet-body">		
					<form id="updateConfigForm" action="config/updateConfig.do" method="post">
						<table class="formTable" style="width:100%;">
							<tr>
								<td class="configKey"><label class="control-label">公共池自动释放天数:</label></td>
								<td class="value">
									<input id="commonPoolDay" type="text" name="commonPoolDay" value="${configMap.commonPoolDay}" class="m-wrap medium" style="width:100px;"> 天
								</td>
							</tr>
						</table>
						<div style="padding:8px;text-align:center;margin-top:5px;margin-bottom:10px;" >
							<button class="btn blue" type="button" id="saveConfigbtn"><i class="icon-ok"></i> 保存</button>
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
	<!--开始：第2行 -->
	<div class="row-fluid">
		<!--开始：第2行第1列 -->
		<div class="span12">
			<!--开始：第2行第1列中的盒子-->
			<div class="portlet box blue">
				<div class="portlet-title">
					<div class="caption"><i class="icon-cogs"></i>业务员拥有客户上限管理(如业务员当前持有客户上限为0，则以角色客户上限为准 )</div>
					<div class="tools">
						<a href="javascript:;" class="collapse"></a>
					</div>
				</div>
				<!--开始：第2行第1列中的盒子body-->
				<div class="portlet-body">		
					<form id="updateCustomerNoOfEmployee" action="config/.do" method="post">
						<!-- 业务员持有客户数量上限管理 -->
						<div class="">
						<div id="customerNOOfEmployee" class="">
							<div>
								<select id="comSelect" class="span2">
									<option value="0">所有业务中心</option>
									<c:forEach items="${requestScope.companys }" var="company">
										<option value="${company.id }" 
											<c:if test="${companyId == company.id}">
												selected="selected"
											</c:if>
										>${company.name }</option>
									</c:forEach>
								</select>
								<select id="demSelect" class="span2">
									<option value="0">所有业务部</option>
									<c:forEach items="${requestScope.departments }" var="department">
										<option value="${department.id }"
											<c:if test="${dId == department.id}">
												selected="selected"
											</c:if>
										>${department.name }</option>
									</c:forEach>
								</select>
								<select class="span2" id="emplSelect">
									<option value="0">所有客户经理</option>
									<c:forEach items="${requestScope.employees }" var="empl">
										<option value="${empl.id}" 
										<c:if test="${requestScope.eId==empl.id}">selected="selected"</c:if>
										>${empl.name}</option>
									</c:forEach>
								</select>
							</div>
							<div id="customerNOView" >
								<label id="employeeRole" class="control-label"><span>中心超级管理员</span>:&nbsp;<i>9999</i>&nbsp;人</label>
								<label id="currentNO">当前客户上限：&nbsp;<input name="currentNO" type="text" value="">&nbsp;人</label>
								<button class="btn blue" type="button" id="saveCustomerNoOfEmployeeBtn"><i class="icon-ok"></i> 保存</button>
							</div>
						</div>
						<!-- 角色默认持有数量上限管理 -->
						<div id="roleCustomerNO"  class="">
							<div>
								<select class="span2" id="roleSelect">
									<option value="0">角色选择</option>
									<c:forEach items="${requestScope.roles }" var="role">
										<option value="${role.id}">${role.roleName}</option>
									</c:forEach>
								</select>
							</div>
							<div id="roleNO">
								<label class="control-label">当前角色客户上限：&nbsp;<input name="roleCustomerNO" type="text" value="">&nbsp;人</label>
								<button class="btn blue" type="button" id="saveRoleNO"><i class="icon-ok"></i> 保存</button>
							</div>
						</div>
						<div id="clearFloat"></div>
					</div>
					</form>
					
				</div>
				<!--结束：第2行第1列中的盒子的body-->
			</div>
			<!--结束：第2行第1列中的盒子-->
		</div>
		<!--结束：第2行第1列 -->					
	</div>
	<!--结束：第2行 -->
</div>
<!-- END PAGE CONTAINER--> 

<script>
	$(function(){
		
		$("#saveConfigbtn").click(function(){
			if(validateForm()){
				var $form = $("#updateConfigForm");
				var formParam = $form.serialize(); 
				console.log(formParam);
				$.ajax({
					type : "POST",
					url : "config/updateConfig.do",
					data : formParam,
					success: function(data){
				    	if(data){
							$.messager.alert("提示","保存成功!");
							window.setTimeout(function(){
								aPost("config/getConfigInfo.do");
							},2000)
						}else{
							$.messager.error("错误提示","保存失败!");
						}
				    },
				    error : function(){
				    	$.messager.error("错误提示","保存失败!");
				    }
				});
			}
			
		});
		
		
		// 表单验证
		function validateForm(){
			// 验证公共池天数
			var $commonPoolDay = $("#commonPoolDay");
			var commonPoolDay = $commonPoolDay.val();
			if(isNaN(commonPoolDay)){
				$.messager.error("验证提示","公共池天数只能输入数字类型!");
				return false;
			}
			commonPoolDay = parseInt(commonPoolDay);
			if(commonPoolDay < 10 || commonPoolDay > 80){
				$.messager.error("验证提示","公共池天数只能输入10~80之间!");
				return false;
			}
			
			return true;
		}
		
		//业务员拥有客户上限管理--start
		
		$("#comSelect").change(function(){
			var companyId = $(this).val();
			var s = $("#emplSelect");
			s.empty();
			$("<option />",{value:0,text:"所有客户经理"}).appendTo(s);
			if(companyId!=0 && typeof(companyId) != "undefined"){
				getDep(companyId,$("#demSelect"));
			}
		});
		
		$("#demSelect").change(function(){
			var val = $(this).val();
			$.post("employee/getEmployeesByDId.do",{dId:val},function(data){
				var s = $("#emplSelect");
				s.empty();
				$("<option />",{value:0,text:"所有客户经理"}).appendTo(s);
				$.each(data,function(i,item){
					$("<option />",{value:item.id,text:item.name}).appendTo(s);
				});
			});
		});
		
		$("#emplSelect").change(function(){
			var employeeId = $(this).val();
			if(employeeId>0){
				$.getJSON("employee/getCustomerNO.do",{employeeId:employeeId},function(data){
					$("#employeeRole i").text(data[0].roleCustomerNO);
					$("#employeeRole span").text(data[0].roleName);
					$("input[name='currentNO']").val(data[0].customerNO);
				})
			}
		});
		
		function getDep(companyId,departmentSelect){
			$.getJSON("department/getDepartmentListMapByCompanyId.do",
					{companyId:companyId,type:1},function(departments){
				departmentSelect.empty();
				$("<option value='0'>所有业务部</option>").appendTo(departmentSelect);
				for(var i = 0; i < departments.length; i++){
					var department = departments[i];
					if($("#depId").val()==department.id){
						$("<option selected='selected' value='"+department.id+"'>"+department.name+"</option>").appendTo(departmentSelect);
					}else{
						$("<option value='"+department.id+"'>"+department.name+"</option>").appendTo(departmentSelect);
					}
				}
			});
		}
		
		$("#saveCustomerNoOfEmployeeBtn").click(function(){
			var eId = $("#emplSelect").val();
			var currentNO = $("input[name='currentNO']").val();
			
			if(eId==0){
				$.messager.alert("提示","请选择员工");
				return;
			}
			var reg = /^\d+$/;
			if(!reg.test(currentNO)){
				$.messager.error("错误提示","请输入 大于 或 等于 0 的整数");
				return;
			}
			
			$.getJSON("employee/saveCustomerNO.do",{eId:eId,currentNO:currentNO},function(data){
				if(data){
					$.messager.alert("提示","保存成功");
				}else {
					$.messager.error("错误提示","保存失败");
				}
			})
		})
		

		//角色客户持有数管理
		$("#roleSelect").change(function(){
			var id = $(this).val();
			$.getJSON("<%=request.getContextPath()%>/role/getRoleCustomerNO.do",{id:id},function(data){
				$("#roleNO input").val(data);
			})
		})
		
		$("#saveRoleNO").click(function(){
			var customerNO = $("#roleNO input").val();
			var id = $("#roleSelect").val();
			
			if(id==0){
				$.messager.alert("提示","请选择要修改的角色");
				return;
			}
			var check = /^\d+$/;
			if(!check.test(customerNO)){
				$.messager.alert("提示","请输入 大于 或 等于 0 的整数");
				return;
			}
			
			$.getJSON("<%=request.getContextPath()%>/role/saveRoleCustomerNO.do",{customerNO:customerNO,id:id}, function(data){
				if(data){
					$.messager.alert("提示","保存成功");
				}else {
					$.messager.error("错误提示","保存失败");
				}
			})
		})
		
		//业务员拥有客户上限管理--end
		
	});
</script>