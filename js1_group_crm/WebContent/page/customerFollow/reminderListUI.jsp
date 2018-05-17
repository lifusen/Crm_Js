<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<style type="text/css">
	.superSearch{
		width:100%;height:320px;
		border:1px solid #ccc;
		margin: 10px 0;
	}
</style>
<div class="container-fluid">
	<div class="row-fluid">
		<div class="span12">
			<h3 class="page-title"></h3>
			<ul class="breadcrumb">
				<li><i class="icon-home"></i> <a href="javascript:void(0);">业务部</a>
					<i class="icon-angle-right"></i></li>
				<li><a href="javascript:void(0);">工作提醒</a></li>
			</ul>
		</div>
	</div>
	
	<div class="row-fluid">
		<div class="span12">
			<!-- BEGIN EXAMPLE TABLE PORTLET-->
			<div class="portlet box green">
				<div class="portlet-title">
					<div class="caption"><i class="icon-globe"></i>工作提醒</div>
					<div class="tools">
						<a href="javascript:;" class="collapse"></a>
					</div>
				</div>
				<div class="portlet-body">
					<div>
						<div class="row-fluid">
							<div class="span14" id="reminderSearchArea">
								搜索: <input type="text" style="width:200px;" id="keyStr" value="${requestScope.keyStr }" placeholder="客户电话/姓名/等级/签单贷款类型" aria-controls="customerTable">
									<select id="fllowType" class="span2" style="width:100px;">
										<option value="0">所有提醒类型</option>
										<option value="1"<c:if test="${fllowType==1 }"> selected="selected"</c:if>>电话联系</option>
										<option value="2"<c:if test="${fllowType==2 }"> selected="selected"</c:if>>邀约上门</option>
										<option value="3"<c:if test="${fllowType==3 }"> selected="selected"</c:if>>上门洽谈</option>
										<option value="4"<c:if test="${fllowType==4 }"> selected="selected"</c:if>>成功签约</option>
										<option value="5"<c:if test="${fllowType==5 }"> selected="selected"</c:if>>售后服务</option>
									</select>
								<c:if test="${requestScope.roleId==1 || requestScope.roleId==5 ||requestScope.roleId==2 || requestScope.roleId==7}">
									<select id="demSelect" class="span2" style="width:100px;">
										<option value="0">所有业务部</option>
										<c:forEach items="${requestScope.departments }" var="department">
											<option value="${department.id }"
												<c:if test="${dId == department.id}">
													selected="selected"
												</c:if>
											>${department.name }</option>
										</c:forEach>
									</select>
								</c:if>
								<c:if test="${requestScope.roleId==1 || requestScope.roleId==5 ||requestScope.roleId==2 || requestScope.roleId==7  || requestScope.roleId==3}">
									<select class="span2" id="emplSelect" style="width:110px;">
										<option value="0">所有客户经理</option>
										<c:if test="${requestScope.roleId==3}">
											<c:forEach items="${requestScope.employees }" var="employee">
												<option value="${employee.id }"
												<c:if test="${eId == employee.id}">
													selected="selected"
												</c:if>
												>${employee.name }</option>
											</c:forEach>											
										</c:if>
									</select>
								</c:if>
								提醒时间：
								<input type="text" class="span2 small datetimepickerYMD" id="beginDate" value="${requestScope.beginDate }" readonly="readonly" size="12">
								 至 <input type="text" class="span2 small datetimepickerYMD" id="endDate" value="${requestScope.endDate }" readonly="readonly" size="12">
								 
								<a class="btn green" id="searchButton" href="javascript:;" style="margin-top: -10px;padding-top:5px;padding-bottom: 5px;">
									<i class="icon-search"></i> 搜索
								</a>
							</div>
						</div>
					</div>
					<div id="tableList">
						<jsp:include page="reminderList.jsp" />
					</div>
				</div>
			</div>
		</div>
	</div>
</div>

<script>
	$(function() {

		/**
		 * 工作提醒列表时间筛选
		 */
		$("input.datetimepickerYMD").datetimepicker({ // 开始时间
			format: 'yyyy-mm-dd',
			autoclose : true,
			todayHighlight : true,
			minView : 'month',
			pickerPosition : 'bottom-left',
			todayBtn : true
				
		});
		
		
		var val = $("#demSelect").val();
		if(val!=null || typeof(val) != "undefined"){
			$.post("employee/getEmployeesByDId.do",{dId:val},function(data){
				var s = $("#emplSelect");
				s.empty();
				$("<option />",{value:0,text:"所有客户经理"}).appendTo(s);
				$.each(data,function(i,item){
					var eId = ${eId};
					if(eId==item.id){
						$("<option />",{value:item.id,text:item.name,selected:true}).appendTo(s);
					}else{
						$("<option />",{value:item.id,text:item.name}).appendTo(s);
					}
				});
			});
		}
		
		// 全文搜索
		$("#keyStr").keyup(function(event){
			 if(event.keyCode == 13){
				 var beginDate = $("#beginDate").val();
				 var endDate = $("#endDate").val();
				 if(beginDate==null || typeof(beginDate) == "undefined"){
					 beginDate="";
				 }
				 if(endDate==null || typeof(endDate) == "undefined"){
					 endDate="";
				 }
				var val = strTrim($(this).val());
				var dId = $("#demSelect").val();
				var eId = $("#emplSelect").val();
				var fllowType = $("#fllowType").val();
				
				if(dId==null || typeof(dId) == "undefined"){
					dId="";
				}
				if(eId==null || typeof(eId) == "undefined"){
					eId="";
				}
				
				$.messager.showLoadProgress();
				$("#tableList").load("customerFollow/getReminderList.do?initPage=1&pageIndex=1&keyword="
						+val+"&beginDate="+beginDate+"&endDate="+endDate+"&dId="+dId+"&eId="+eId+"&fllowType="+fllowType,function(){
			            	$.messager.closeLoadProgress();
			            });
			 }
		 });
		
		$("#searchButton").click(function(){
			var val = strTrim($("#keyStr").val());
			 var beginDate = $("#beginDate").val();
			 var endDate = $("#endDate").val();
			 if(beginDate==null || typeof(beginDate) == "undefined"){
				 beginDate="";
			 }
			 if(endDate==null || typeof(endDate) == "undefined"){
				 endDate="";
			 }
			var dId = $("#demSelect").val();
			var eId = $("#emplSelect").val();
			if(dId==null || typeof(dId) == "undefined"){
				dId="";
			}
			if(eId==null || typeof(eId) == "undefined"){
				eId="";
			}
			var fllowType = $("#fllowType").val();
			
			$.messager.showLoadProgress();
			 $("#tableList").load("customerFollow/getReminderList.do?initPage=1&pageIndex=1&keyword="
						+val+"&beginDate="+beginDate+"&endDate="+endDate+"&dId="+dId+"&eId="+eId+"&fllowType="+fllowType,function(){
			            	$.messager.closeLoadProgress();
			            });
		});
		
		
		$("#demSelect").change(function(){
			var val = $(this).val();
			$.post("employee/getEmployeesByDId.do",{dId:val},function(data){
				var s = $("#emplSelect");
				s.empty();
				$("<option />",{value:0,text:"所有客户经理"}).appendTo(s);
				$.each(data,function(i,item){
					var eId = ${eId};
					if(eId==item.id){
						$("<option />",{value:item.id,text:item.name,selected:true}).appendTo(s);
					}else{
						$("<option />",{value:item.id,text:item.name}).appendTo(s);
					}
				});
			});
		});
	});
	function strTrim(val){
		var str = val.replace(/\s+/g,"");
		return str;
	}
</script>