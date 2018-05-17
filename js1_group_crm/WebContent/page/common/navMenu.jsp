<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!-- BEGIN SIDEBAR -->
<div class="page-sidebar nav-collapse collapse">
	<!-- BEGIN SIDEBAR MENU -->        
	<ul class="page-sidebar-menu">
		<li>
			<!-- BEGIN SIDEBAR TOGGLER BUTTON -->
			<div class="sidebar-toggler hidden-phone"></div>
			<!-- BEGIN SIDEBAR TOGGLER BUTTON -->
		</li>
		<li id="showEmployeeHeadNavMenu" class="active">
			<ul class="sub-menu">
				<li>
					<a>
						<i class="icon-blank"></i> 
						<span class="title">
							<img src="${sessionScope.loginUser.headImg }" style="width:100px;height:100px;border:1px solid #ccc;"/>
						</span>
					</a>
				</li>
				<li style="text-align:center;">
					<a href="javascript:void(0);" style="padding-left: 0px !important;">
						<%--
						<i class="icon-blank"></i>
						 --%> 
						<span class="title">姓名:${sessionScope.loginUser.name }</span>
						<span class="selected"></span>
					</a>
				</li>
				<li >
					<a href="javascript:void(0);">
						<i class="icon-blank"></i> 
						<span class="title">职务:${sessionScope.loginUser.role.roleName }</span>
						<span class="selected"></span>
					</a>
				</li>
			</ul>
		</li>
		<li id="myDesktopNavMenu" class="start active ">
			<a class="ajaxify" href="desktop/getDesktop.do">
			<%--
			 <a href="index.jsp">
			 --%>
				<i class="icon-home"></i> 
				<span class="title">我的桌面</span>
				<span class="selected"></span>
			</a>
		</li>
		<c:forEach items="${sessionScope.menuTrees }" var="menuTree">
			 <li class="">
				<a href="javascript:;">
					<i class="${menuTree.menu.icon }"></i> 
					<span class="title">${menuTree.menu.name }</span>
					<span class="arrow "></span>
				</a>
				<ul class="sub-menu">
					<c:forEach items="${menuTree.menus }" var="menu">
						<li >
							<c:if test="${menu.openNewPage==0 }">
								<a class="ajaxify" href="${menu.url }"><i class="${menu.icon }"></i> ${menu.name }</a>
							</c:if>
							<c:if test="${menu.openNewPage==1 }">
								<a href="${menu.url }" target="_blank"><i class="${menu.icon }"></i> ${menu.name }</a>
							</c:if>
						</li>
					</c:forEach>
					
					<%--
					<li >
						<a class="ajaxify" href="page/customerSource/customerSourcePie.jsp"><i class="icon-cogs"></i> 客户来源统计</a>
					</li>
					 --%>
					
				</ul>
			</li> 
		</c:forEach>
		<!-- 2016.12.19 by SwordLiu
		<li class="">
			<a href="javascript:;">
				<i class="icon-cogs"></i> 
				<span class="title">权证部</span>
				<span class="arrow "></span>
			</a>
			<ul class="sub-menu">
				<li >
					<a class="ajaxify" href="center/orderList.do"><i class="icon-cogs"></i> 中心新订单</a>
				</li>
				<li >
					<a class="ajaxify" href="warrantdepartment/orderList.do"><i class="icon-cogs"></i> 部门新订单</a>
				</li>
				<li >
					<a class="ajaxify" href="warranter/orderList.do"><i class="icon-cogs"></i> 有效客户列表</a>
				</li>
				<li >
					<a class="ajaxify" href="warranter/getOutLoanList.do"><i class="icon-cogs"></i> 放款客户列表</a>
				</li>
				<li >
					<a class="ajaxify" href="warrant/getRejectList.do"><i class="icon-cogs"></i> 退单退件客户列表</a>
				</li>
				<li >
					<a class="ajaxify" href="warrant/getReminderList.do"><i class="icon-cogs"></i> 工作提醒</a>
				</li>
			</ul>
		</li>
		 -->
	</ul>
	<!-- END SIDEBAR MENU -->
</div>
<!-- END SIDEBAR -->
<script>
	/**
	$('#myDesktopNavMenu').click(function(){
		alert(0);
		return false;
	});
	**/
</script>