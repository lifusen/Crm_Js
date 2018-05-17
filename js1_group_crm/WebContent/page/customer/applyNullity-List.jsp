<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://itproject.cn/phone" prefix="phone"%>
<style type="text/css">
	.superSearch{
		width:100%;height:320px;
		border:1px solid #ccc;
		margin: 10px 0;
	}
</style>

<!-- table table-striped table-bordered table-hover -->
<table class="table table-striped table-bordered table-hover table-full-width dblclickUpdate" id="customerTable" style="background: #EEE;">
	<thead>
		<tr>
			<th style="width:2%"><input type="checkbox" onchange="selectAll(this);" id="selectAll" class="group-checkable" data-set="#adv-custom-table .checkboxes" /></th>
			<th style="width:5%">序号</th>
			<th style="width:8%">公司</th>
			<th style="width:8%">部门</th>
			<th style="width:8%">客户经理</th>
			<th style="width:10%">客户姓名</th>
			<th style="width:7%">电话</th>
			<th style="width:5%">资产</th>
			<th style="width:10%">贷款类型</th>
			<th style="width:5%">通话</th>
			<th style="width:5%">上门</th>
			<th style="width:10%">资料完善</th>
			<th style="width:10%">关注等级</th>
			<th style="width:10%">签单状态</th>
			<th style="width:5%">自留</th>
		</tr>
	</thead>
	<tbody>
		<c:forEach items="${requestScope.customers }" var="cust" varStatus="vsta">
			<tr cId="${cust.customerId}">
				<td><input type="checkbox" onchange="selectParent(this);" class="checkboxes customerList" value="${cust.customerId }" /></td>
				<td>${((pageIndex-1)*pageSize)+(vsta.index+1) }</td>
				<td>${allCompanyNameMap[cust.companyId]}</td>
				<td>${cust.departmentName }</td>
				<td>${cust.employeeName }</td>
				<td class="hidden-480">${cust.customerName }</td>
				<td>${phone:phone(cust.phone )}</td>
				<td>
					<a id="testaaa" href="javascript:;" class="popovers"
						data-trigger="hover" data-html="true"
						data-content="${cust.customerAsset }"
						data-original-title="资产信息"> ${cust.customerAssetTitile } </a>
				</td>
				<td class="hidden-480">${cust.loanType }</td>
				<td class="hidden-480">${cust.callCount }</td>
				<td class="hidden-480">${cust.visitCount }</td>
				<td class="hidden-480">
					<c:if test="${cust.dataPercent!=null && cust.dataPercent>0 }">
							${cust.dataPercent==0} %
					</c:if>
				</td>
				<td class="hidden-480">${cust.attentionLevel }</td>
				<td class="hidden-480">
					<c:if test="${cust.state==4 }">
						已签单
					</c:if>
					<c:if test="${cust.state!=4 }">
						未签单
					</c:if>
				</td>
				<td class="hidden-480">
					<c:if test="${cust.visibility==0 }">
						是
					</c:if>
					<c:if test="${cust.visibility==1 }">
						否
					</c:if>
				</td>
			</tr>
		</c:forEach>
	</tbody>
</table>
<jsp:include page="../common/paginator.jsp" />
<script>
	$('table.dblclickUpdate tbody tr').dblclick(function(){
		var cId = $(this).attr('cId');
		var val = strTrim($("#keyStr").val());
		aPost("${pageContext.request.contextPath}/customer/gotoValidListUpdateUI.do?id="
				+cId+"&refererUrl=customer/getApplyNullityList.do?keyStr="+val+",pageIndex=${pageIndex},pageSize=${pageSize}");
	});

	$(function() {
		$('.popovers').popover();
		
		$(".applyBtn").click(function(){
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
			var id = $(this).attr("id");
			var state;
			if(id=="no"){
				state=1;
			}else{
				state=0;
			}
			if(confirm("确定提交请求吗?")){
				$.post("customer/verify.do",{ids:ids,state:state},function(data){
					if(data){
						alert("审核成功！");
						aPost("customer/getApplyNullityList.do");
					}
				});
			}
		});
	});
	
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
</script>