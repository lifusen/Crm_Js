<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/media/css/DT_bootstrap.css" />
<script type="text/javascript"
	src="${pageContext.request.contextPath}/media/js/select2.min.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/media/js/jquery.dataTables.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/media/js/DT_bootstrap.js"></script>
<script
	src="${pageContext.request.contextPath}/media/js/table-managed.js"></script>
<style type="text/css">
	.customTable{
	float: left;}
	.customTable th,.customTable td{
		font-weight:normal;
		border:1px solid #ccc;
		height:30px;
		width:30px;
		min-width:30px;
		text-align: center;
	}
	.trSelected{
		background-color: #35AA47;
	}
</style>
<div class="container-fluid">
	<div class="row-fluid">
		<div class="span12">
			<h3 class="page-title"></h3>
			<ul class="breadcrumb">
				<li><i class="icon-home"></i> <a href="javascript:void(0);">客户管理</a>
					<i class="icon-bar-chart"></i></li>
				<li><a href="javascript:;">业务反馈报表</a></li>
			</ul>
		</div>
	</div>
	<div class="row-fluid">
		<div class="span12">
			<div class="portlet box green">
				<div class="portlet-title">
					<div class="caption">
						<i class="icon-user"></i>业务反馈报表
					</div>
				</div>
				<div class="portlet-body">
					<div role="grid" style="">
						<table id="" class="formTable">
							<tr>
								<c:if test="${requestScope.roleId==1 || requestScope.roleId==5 || requestScope.roleId==2 || requestScope.roleId==7 || loginUser.type=='SUPER_ROLE'}">
									<td style="width:200px;">
										<select id="demSelect" class="small m-wrap">
											<option value="0">所有部门</option>
											<c:forEach items="${requestScope.departments }" var="department">
												<option value="${department.id }"><c:if test="${loginUser.type=='SUPER_ROLE'}">${allCompanyNameMap[department.companyId]} - </c:if>${department.name }</option>
											</c:forEach>
										</select>
									</td>
								</c:if>
								<c:if test="${requestScope.roleId==1 || requestScope.roleId==5 || 
								requestScope.roleId==2 || requestScope.roleId==7  || requestScope.roleId==3 || loginUser.type=='SUPER_ROLE'}">
									<td style="width:200px;">
										<select class="small m-wrap" id="emplSelect">
											<option value="0">所有客户经理</option>
											<c:if test="${requestScope.roleId==3}">
												<c:forEach items="${requestScope.employees }" var="empl">
													<option value="${empl.id}">${empl.name}</option>
												</c:forEach>
											</c:if>
										</select>　
									</td>
								</c:if>
								<td style="width:300px;">
									<%--
									时间
									<input data-provide="datepicker" type="text" class="span5 small" readonly="readonly" id="beginDate" size="12">
									至<input data-provide="datepicker" type="text" class="span5 small" readonly="readonly" id="endDate" size="12">
									 --%>
									 
									时间
									<input type="text" class="span5 small reportDatePicker" readonly="readonly" id="beginDate" size="12">
									至 <input type="text" class="span5 small reportDatePicker" readonly="readonly" id="endDate" size="12">
									
									 
								</td>
								<td style="width:100px;">
								　　<button class="btn green" type="button" id="reportSearch">搜索</button>
								</td>
							</tr>
						</table>
						<div id="reportDIV">
							<jsp:include page="reportDetail.jsp" />
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	
</div>
<script>
	$(function(){
		$("input.reportDatePicker").datetimepicker({
			format: 'yyyy-mm-dd',
			autoclose : true,
			todayHighlight : true,
			minView : 'month',
			pickerPosition : 'bottom-left'
		});
		
		// 搜索
		$("#reportSearch").click(function(){
			var beginDate = $("#beginDate").val();
			var endDate = $("#endDate").val();
			var dId = $("#demSelect").val();
			var eId = $("#emplSelect").val();
			if(dId==null || typeof(dId) == "undefined"){
				dId="";
			}
			if(eId==null || typeof(eId) == "undefined"){
				eId="";
			}
			
			$.messager.showLoadProgress();
			$("#reportDIV").load("report/report.do?initPage=1&edtDateBegin="
					+beginDate+"&edtDateEnd="+endDate+"&dId="+dId+"&eId="+eId,function(){
				$.messager.closeLoadProgress();
			});
		});
		// 查询
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
		
	});
</script>