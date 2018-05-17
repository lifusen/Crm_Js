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
						 <a class="aPost" href="${pageContext.request.contextPath}/page/customer/list.jsp">客户管理</a>
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
		 	<!-- 引入客户贷款办理流程情况图 -->
		 	<c:if test="${customer.state==0||customer.state==1||customer.state==2||customer.state==3||customer.state==4||customer.state==5||customer.state==6||customer.state==7 }">
		 		<jsp:include page="listCourse.jsp"/>
		 	</c:if>
			<!-- 引入修改基本信息模块页面 -->
			<jsp:include page="../customer/updateBasicInfo.jsp">
				<jsp:param value="${updateType}" name="updateType"/>
			</jsp:include>
			
			<%-- --%>
			<c:if test="${isShowFollow==true}">
				<!-- 引入客户跟进模块页面 -->
				<jsp:include page="../customerFollow/listUI.jsp"/>
			</c:if>
			
		</div>
   </div>
