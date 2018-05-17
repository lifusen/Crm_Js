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
			<th style="width:5%">序号</th>
			<th style="width:5%">公司</th>
			<th style="width:8%">部门</th>
			<th style="width:6%">客户经理</th>
			<th style="width:7%">客户姓名</th>
			<th style="width:7%">电话</th>
			<th style="width:7%">客户等级</th>
			<th style="width:7%">贷款类型</th>
			<th style="width:5%">通话</th>
			<th style="width:5%">上门</th>
			<th style="width:7%">签单状态</th>
			<th style="width:10%">更新时间</th>
		</tr>
	</thead>
	<tbody>
		<c:forEach items="${requestScope.customers }" var="cust" varStatus="vsta">
			<tr cId="${cust.customerId}">
				<td>${((pageIndex-1)*pageSize)+(vsta.index+1) }</td>
				<td>${allCompanyNameMap[cust.companyId]}</td>
				<td>${cust.departmentName }</td>
				<td>${cust.employeeName }</td>
				<td class="hidden-480">${cust.customerName}</td>
				<td>${phone:phone(cust.phone )}</td>
				<td>${cust.customerLevel }
					<%-- <a id="testaaa" href="javascript:;" class="popovers"
						data-trigger="hover" data-html="true"
						data-content="${cust.customerAsset }"
						data-original-title="资产信息"> ${cust.customerAssetTitile } </a> --%>
				</td>
				<td class="hidden-480">${cust.loanType }</td>
				<td class="hidden-480">${cust.callCount }</td>
				<td class="hidden-480">${cust.visitCount }</td>
				<td class="hidden-480">
					<c:if test="${cust.state==4 }">
						已签单
					</c:if>
					<c:if test="${cust.state!=4 }">
						未签单
					</c:if>
				</td>
				<td class="hidden-480">${cust.releaseDate }</td>
			</tr>
		</c:forEach>
	</tbody>
</table>
<jsp:include page="../common/paginator.jsp" />
<script>
	$('table.dblclickUpdate tbody tr').dblclick(function(){
		var cId = $(this).attr('cId');
		var val = strTrim($("#keyStr").val());
		aPost("${pageContext.request.contextPath}/customer/gotoUpdateBasicUI.do"
				+"?id="+cId
				+"&refererUrl=customer/getCommonPoolList.do?keyStr="+val+",pageIndex=${pageIndex},pageSize=${pageSize}"
				+"&showButton=receive"
				+"&isShowFollow=true"
				+"&onlyShowFollowList=true"
				+"&noShowSaveButton=true"
				+"&commonPool=true"
		);
	});

	$(function() {
		$('.popovers').popover();
	});
	
	// 删除
	function deleteCustomer(id,hId){
		if(confirm("确定删除吗?")){
			$.post("customer/delete.do",{id:id,hId:hId},function(data){
				if(data){
					alert("删除成功！");
					aPost("customer/getNullityList.do");
				}else{
					alert("删除失败！");
				}
			});
		}
	}
</script>