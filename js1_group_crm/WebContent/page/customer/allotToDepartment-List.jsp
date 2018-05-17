<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://itproject.cn/phone" prefix="phone"%>
<style>
	table.allotTable td{
		padding:9px;
	}
</style>
<!-- BEGIN PAGE CONTAINER-->
<div class="row-fluid" style="width:600px;float: left;">
	<div class="span14">
		搜索: <input type="text" style="width:300px;" id="keyStr" value="${requestScope.keyStr }" placeholder="客户电话/姓名/等级/贷款类型" aria-controls="customerTable">
		<a class="btn green" id="searchButton" href="javascript:;" style="margin-top: -10px;padding-top:5px;padding-bottom: 5px;">
			<i class="icon-search"></i> 搜索
		</a>
	</div>
</div>
<div class="btn-group pull-right" style="float: left;">
	<c:choose>
		<c:when test="${loginUser.type == 'SUPER_ROLE'}">
			<button class="btn green" onclick="showChoiceCompanyDepartmentModal(1,this)">批量分配&nbsp;&nbsp;</button>
		</c:when>
		<c:otherwise>
			<button id="btn-adv-custom-page-share" class="btn green dropdown-toggle" data-toggle="dropdown">批量分配&nbsp;&nbsp;<i class="icon-angle-down"></i></button>
		</c:otherwise>
	</c:choose>
	<ul class="dropdown-menu pull-right">
		<c:forEach items="${requestScope.departments }" var="department">
			<li><a id="depar-${department.id }" href="javascript:;" class="batchAllot">${department.name }</a></li>
		</c:forEach>
	</ul>
</div>		
<table class="table table-striped table-bordered table-hover allotTable" id="adv-custom-table">
	<thead>
		<tr>
			<th style="width:5%"><input type="checkbox" id="selectAll" onchange="selectAll(this);" class="group-checkable" data-set="#adv-custom-table .checkboxes" /></th>
			<th style="width:5%">编号</th>
			<th width="10%"class="hidden-480">公司</th>
			<th width="10%"class="hidden-480">客户姓名</th>
			<th width="10%">客户电话</th>
			<th width="20%" class="hidden-480">客户资产</th>
			<th width="10%" class="hidden-480">录入人</th>
			<th style="min-width: 50px;">贷款类型</th>
			<th style="min-width: 50px;">客户来源</th>
			<th width="10%" class="hidden-480">操作</th>
		</tr>
	</thead>
	<tbody>
	<c:forEach items="${requestScope.customers }" var="cust" varStatus="vsta">
		<tr id="adv-custom-row-1" class="odd gradeX" ondblclick="dblclickRowUpdate(${cust.id })">
			<td><input type="checkbox" onchange="selectParent(this);" class="checkboxes customerList" value="${cust.id }" /></td>
			<td>${((pageIndex-1)*pageSize)+(vsta.index+1) }</td>
			<td class="hidden-480">${allCompanyNameMap[cust.companyId]}</td>
			<td class="hidden-480">${cust.cName }</td>
			<td>${phone:phone(cust.phone)}</td>
			<td>
				<a id="testaaa" href="javascript:;" class="popovers"
					data-trigger="hover" data-html="true"
					data-content="${cust.customerAsset }"
					data-original-title="资产信息"> ${cust.customerAssetTitile} </a>
			</td>
			<td class="hidden-480">${cust.addPersonName }</td>
			<td>${cust.loanType }</td>
			<td>${cust.sourceName }</td>
			<td >
			
				<c:choose>
					<c:when test="${loginUser.type == 'SUPER_ROLE'}">
						<button class="btn green mini" onclick="showChoiceCompanyDepartmentModal(2,this)">分配&nbsp;&nbsp;</button>
					</c:when>
					<c:otherwise>
						<div class="btn-group" style="margin-bottom:0px !important;">
							<button class="btn mini green dropdown-toggle" data-toggle="dropdown">分配&nbsp;&nbsp;<i class="icon-angle-down"></i></button>
							<ul class="dropdown-menu pull-right">
								<c:forEach items="${requestScope.departments }" var="department">
									<li><a id="depars-${department.id }" class="allotToDepartment" href="javascript:;">${department.name }</a></li>
								</c:forEach>
							</ul>
						</div>
					</c:otherwise>
				</c:choose>
			
				
			</td>
		</tr>
	</c:forEach>
	</tbody>
</table>	
<jsp:include page="../common/paginator.jsp" />
<jsp:include page="../common/fullScreenProgressBarModal.jsp"/>
<jsp:include page="allotToDepartment-modal.jsp"/>
<script>
	
	$(function(){
		$("#keyStr").focus();
		// 全文搜索
		$("#keyStr").keyup(function(event){
			 if(event.keyCode == 13){
				var val = strTrim($(this).val());
				$("#tableList").load("customer/getAllotCustomerList.do?initPage=1&pageIndex=1&keyStr="+val+"&pageSize="+$("#pageSize").val());
			 }
		 });
		
		$("#searchButton").click(function(){
			var val = strTrim($("#keyStr").val());
			$("#tableList").load("customer/getAllotCustomerList.do?initPage=1&pageIndex=1&keyStr="+val+"&pageSize="+$("#pageSize").val());
		});
	});

	function dblclickRowUpdate(customerId){
		aPost("customer/gotoUpdateBasicUI.do?id="+customerId+"&refererUrl="+"customer/getAllotCustomerList.do&updateType=1");
	}
	jQuery(document).ready(function() {
		$('.popovers').popover();
		$(".allotToDepartment").click(function(){
			if(confirm("确定分配?")){
				var dId = $(this).attr("id");
				dId = dId.substring(dId.lastIndexOf("-")+1);
				var tds = $(this).parent().parent().parent()
									.parent().parent().children();
				var cId = $(tds[0]).find("input").val();
				$.post("customer/allotToDepartment.do",{dId:dId,cId:cId},function(data){
					if(data){
						alert("分配成功，请分配客户到业务员..");
						aPost("customer/getAllotCustomerList.do");
					}
				});
			}
		});
		
		
		// 批量分配
		$(".batchAllot").click(function(){
			var inputs = $(".customerList");
			var ids = "";
			for(var i=0;i<inputs.length;i++){
				if($(inputs[i]).attr("checked")){
					var id = $(inputs[i]).val();
					ids = ids + (id+",");
				}
			}
			if(ids==""){
				alert("请选择客户");
				return;
			}
			
			if(confirm("确定分配?")){
				var dId = $(this).attr("id");
				dId = dId.substring(dId.lastIndexOf("-")+1);
				$.post("customer/batchAllotToDepartment.do",{dId:dId,ids:ids},function(data){
					if(data){
						alert("分配成功，请分配客户到业务员..");
						aPost("customer/getAllotCustomerList.do");
					}
				});
			}
		});
		// 导入数据全单分配
		$(".importBatchAllotAll").click(function(){
			if(confirm("确定分配?")){
				var dId = $(this).attr("id");
				dId = dId.substring(dId.lastIndexOf("-")+1);
				var ulId = $(this).parent().parent().attr("id");
				ulId = ulId.substring(ulId.lastIndexOf("-")+1);
				$("#btn-allot-all-"+ulId).addClass("disabled");
				//全屏进度条
				$('#fullScreenProgressBarModal').modal('show');
				$.post("customer/importBatchAllotAll.do",{dId:dId,rId:ulId},function(data){
					if(data){
						$("#btn-allot-all-"+ulId).removeClass("disabled");
						$('#fullScreenProgressBarModal').modal('hide');
						alert("分配成功，请分配客户到业务员..");
						aPost("customer/getAllotCustomerList.do");
					}
				});
			}
		});
		// 导入分单分配
		$(".importBatchAllot").click(function(){
			var val = $(this).parent().parent().parent().
							parent().children("input").val();
			var reg = new RegExp("^[0-9]*$");
			if(!reg.test(val) || val==""){
		        alert("请输入分配数量");
		        return ;
		    }
			var dId = $(this).attr("id");
			dId = dId.substring(dId.lastIndexOf("-")+1);
			var ulId = $(this).parent().parent().attr("id");
			ulId = ulId.substring(ulId.lastIndexOf("-")+1);
			var text = $("#batch-custom-count-"+ulId).text();
			if(parseInt(text)<parseInt(val)){
				alert("最多只能分配"+text+"个客户");
				return ;
			}
			if(confirm("确定分配?")){
				$("#btn-allot-"+ulId).addClass("disabled");
				//全屏进度条
				$('#fullScreenProgressBarModal').modal('show');
					$.post("customer/importBatchAllot.do",{dId:dId,rId:ulId,count:val},function(data){
					if(data){
						$("#btn-allot-"+ulId).removeClass("disabled");
						$('#fullScreenProgressBarModal').modal('hide');
						alert("分配成功，请分配客户到业务员..");
						aPost("customer/getAllotCustomerList.do");
					}
				});
			}
		});
	});
	
	// 全选
	function selectAll(cb){
		if($(cb).attr("checked")){
			$(".customerList").attr("checked",true);
			$(".customerList").parent().addClass("checked");
		}else{
			$(".customerList").attr("checked",false);
			$(".customerList").parent().removeClass("checked");
		}
	}
	
	function selectParent(checkbox){
		var menus = $(".customerList");
		var isAll = true;
		for(var i=0;i<menus.length;i++){
			if(!$(menus[i]).attr("checked")){
				isAll = false;
				break;
			}
		}
		if(!isAll){
			$("#selectAll").attr("checked",false);
			$("#selectAll").parent().removeClass("checked");
		}else{
			$("#selectAll").attr("checked",true);
			$("#selectAll").parent().addClass("checked");
		}
	}
	
	function strTrim(val){
		var str = val.replace(/\s+/g,"");
		return str;
	}
</script>
