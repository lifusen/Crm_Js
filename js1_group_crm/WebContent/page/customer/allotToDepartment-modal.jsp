<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<style>
	#choiceDepartmentSelect{
		width:138px;
	}
	#allotCompanyDepartmentBtn{
		vertical-align: top;
		width: 100px;
	}
</style>
<!-- 导入影像文件 start -->
<div id="choiceCompanyDepartmentModal" class="modal hide fade" tabindex="-1" role="dialog" aria-hidden="true">
	<div class="modal-header">
		<button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>
		<h3 id="choiceCompanyDepartmentModalTitle">分配客户</h3>
	</div>
	<div class="modal-body">
		
		<form id="choiceCompanyDepartmentForm" action="#" method="post">
			<select id="choiceCompanySelect" name="companyId" class="small m-wrap"></select>
			<select id="choiceDepartmentSelect" name="departmentId" class="m-wrap"></select>
			<select id="choiceEmployeeSelect" style="display:none;"></select>
			<input id="allotCompanyDepartmentBtn" type="submit" class="btn green" value="保存"/>
		</form>
		
	</div>
	<div class="modal-footer">
		<button type="button" class="btn" onclick="closeChoiceCompanyDepartmentModal()">关闭</button>
	</div>
</div>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/allotToDepartment-modal.js"></script>
<!-- 导入影像文件 end -->