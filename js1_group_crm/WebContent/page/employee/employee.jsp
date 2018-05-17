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
					<div class="caption"><i class="icon-cogs"></i>员工信息管理</div>
					<div class="tools">
						<a href="javascript:;" class="collapse"></a>
					</div>
				</div>
				<!--开始：第1行第1列中的盒子body-->
				<div class="portlet-body">
					
					
					<div class="clearfix">
						<!-- 添加员工按钮 -->
						<div class="btn-group">
							<a id="addEmployeeInfo" class="btn green aPost" href="javascript:;"><i class="icon-plus"></i> 添加员工信息</a>
						</div>
						
						<%--
						<!-- 搜索区域start -->
						<div class="row-fluid">
							<div class="span12">
								<input type="text" style="width:240px;" id="keyStr" value="${requestScope.keyStr }" placeholder="编号/账号/姓名/手机">
								<select id="companySearchSelect" style="width:110px;">
									<option value="-1">所有公司</option>
									<c:forEach var="company" items="${applicationScope.allCompanyList}">
							 			<option value="${company.id}">${company.name}</option>
									</c:forEach>
								</select>
								<select id="departmentSearchSelect" style="width:100px;">
						 			<option value="-1">所有部门</option>
								</select>
								<select id="signState" style="width:100px;">
						 			<option value="1">在职</option>
						 			<option value="0">离职</option>
						 			<option value="2">删除</option>
						 			<option value="-1">所有状态</option>
								</select>
									<label for="entryDate" style="display: inline-block;">入职时间：</label>
					<input id="entryDate" class="formDatepicker" name="remindTime" size="12" style="width:120px;" type="text" value="${requestScope.beginDate }" readonly>
										 至 
					<input class="formDatepicker" name="remindTime" size="12" style="width:120px;" type="text" value="${requestScope.endDate }" readonly>
									<a class="btn green" id="searchButton" href="javascript:;" style="margin-top: -10px;padding-top:5px;padding-bottom: 5px;">
										<i class="icon-search"></i> 搜索
									</a>
							</div>
						</div>
						<!-- 搜索区域end -->
						 --%>
						
					</div>
					
					<div id="tableList">
						<jsp:include page="employee-List.jsp" />												
					</div>
						
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
<script type="text/javascript" src="${pageContext.request.contextPath}/js/customer/dateFormHandler.js"></script>
<script type="text/javascript">
	$("#companySearchSelect").change(function(){
		var id = $(this).val();
		getDepartmentsByCompanyId(id);
	});
	
	function getDepartmentsByCompanyId(id){
		var $departmentSearchSelect = $("#departmentSearchSelect");
		$departmentSearchSelect.empty();
		$("<option value='-1'>所有部门</option>").appendTo($departmentSearchSelect);
		if(id==-1){
			return;
		}
		$.getJSON("department/getByCompanyId.do",{companyId:id},function(departments){
			for(var i = 0; i < departments.length; i++){
				var department = departments[i];
				$("<option value='"+department.id+"'>"+department.name+"</option>").appendTo($departmentSearchSelect);
			}
		});
	}
</script>
