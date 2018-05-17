<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://itproject.cn/phone" prefix="phone"%>
<table class="table table-striped table-bordered table-hover"id="customer-feedback-table">
	<thead>
		<tr role="row"  style="background: #EEEEEE;">
			<th width="5%">序号</th>
			<th width="10%">部门</th>
			<th width="10%">业务员</th>
			<th width="15%">姓名</th>
			<th width="15%">电话</th>
			<th width="15%">贷款金额</th>
			<th width="15%">操作</th>
		</tr>
	</thead>
	<tbody>
		<c:forEach items="${requestScope.maintains }" var="maintain" varStatus="vsta">
			<tr>
				<td class="">${((pageIndex-1)*pageSize)+(vsta.index+1) }</td>
				<td class="">${maintain.dname }</td>
				<td class="">${maintain.ename }</td>
				<td>${maintain.cname }</td>
				<td>${phone:phone(maintain.phone) }</td>
				<td>${maintain.loanAmount }</td>
				<td>
					<div class="btn-group">
						<button class="btn mini green dropdown-toggle" data-toggle="dropdown">客户对业务员评价&nbsp;&nbsp;<i class="icon-angle-down"></i></button>
						<ul class="dropdown-menu pull-right">
							<li><a class="evaluate" val="A-${maintain.sId }" href="javascript:;">★★★★★非常满意</a></li>
							<li><a class="evaluate" val="B-${maintain.sId }" href="javascript:;">★★★★比较满意</a></li>
							<li><a class="evaluate" val="C-${maintain.sId }" href="javascript:;">★★服务一般</a></li>
							<li><a class="evaluate" val="D-${maintain.sId }" href="javascript:;">★服务较差</a></li>														
						</ul>
					</div>
				</td>
			</tr>
		</c:forEach>
	</tbody>
</table>	
<jsp:include page="../common/paginator.jsp" />
<script>
	/* $(function() {
		$('#customerTable tbody tr').dblclick(function(){
			var id = $(this).attr("trId");
			aPost("customer/gotoUpdateBasicUI.do?id="+id+"&refererUrl="+"customer/getNewCustomerList.do"+"&isShowFollow=true");
		});
		$('.popovers').popover();
	}); */
	
	$(function(){
		$(".evaluate").click(function(){
			var val = $(this).attr("val");
			$.post("signCustomer/signCustomerEvaluate.do",{val:val},function(data){
				if(data){
					aPost("signCustomer/signCustomerMaintainList.do");
				}else{
					alert("评级失败！");			
				}
			});
		});
	});
</script>