<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<link href="${pageContext.request.contextPath}/css/addCustomerForm.css" rel="stylesheet" type="text/css" />
    <div class="container-fluid">
		<!-- BEGIN PAGE HEADER-->
		<div class="row-fluid">
			<div class="span12">
				<h3 class="page-title"></h3>
				<ul class="breadcrumb">
					<li>
						<i class="icon-home"></i>
						 <a href="javascript:void(0);">客户管理</a>
						<i class="icon-angle-right"></i>
					</li>
					<li><a href="#">客户信息</a></li>
					<li class="pull-right no-text-shadow">
						<div id="dashboard-report-range" class="dashboard-date-range tooltips no-tooltip-on-touch-device responsive" data-tablet="" data-desktop="tooltips" data-placement="top" data-original-title="Change dashboard date range">
							<i class="icon-calendar"></i>
							<span></span>
							<i class="icon-angle-down"></i>
						</div>
					</li>
				</ul>
			</div>
		</div>
		<!-- END PAGE HEADER-->
		<div class="customerFormContainer">
		<%--
		${customer}
		 --%>
		 	<!-- 引入客户流程情况图 -->
		 	<c:if test="${customer.state==0||customer.state==1||customer.state==2||customer.state==3||customer.state==4||customer.state==5||customer.state==6 }">
		 		<jsp:include page="listCourse.jsp"/>
		 	</c:if>
			<!-- 引入修改基本信息模块页面 -->
			<jsp:include page="../customer/updateBasicInfo.jsp"/>
			<!-- 引入添加签单信息模块页面 -->
			<jsp:include page="../signCustomer/addSignInfo.jsp"/>
			<!-- 引入客户跟进模块页面 -->	
			<jsp:include page="../customerFollow/listUI.jsp"/>
			
			<c:if test="${closeWindow == true }">
				<div style="text-align:center;padding:12px;">
					<a class="btn" href="javascript:void(0);" onclick="closeWindow()"><i class="icon-remove"></i> 关闭此页面</a>
				</div>
			</c:if>
			
		</div>
   </div>
