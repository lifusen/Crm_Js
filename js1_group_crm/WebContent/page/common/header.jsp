<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    
<!-- BEGIN HEADER -->
<div class="header navbar navbar-inverse navbar-fixed-top">
	<!-- BEGIN TOP NAVIGATION BAR -->
	<div class="navbar-inner">
		<div class="container-fluid">
			<!-- BEGIN LOGO -->
			<a class="brand" href="index.jsp">
				<img src="" alt="佳胜科技" style="margin-top:-2px;"/>
			</a>
			<!-- END LOGO -->
			<!-- BEGIN RESPONSIVE MENU TOGGLER -->
			<a href="javascript:;" class="btn-navbar collapsed" data-toggle="collapse" data-target=".nav-collapse">
			<img src="media/image/menu-toggler.png" alt="" />
			</a>          
			<!-- END RESPONSIVE MENU TOGGLER -->    
			
			
			
			<!-- BEGIN TOP NAVIGATION MENU -->              

				<ul class="nav pull-right">
					<!-- BEGIN NOTIFICATION DROPDOWN -->   
					<li class="dropdown" id="headerNotificationWrapper">
						<a href="#" class="dropdown-toggle" data-toggle="dropdown">
							<i class="icon-envelope"></i>
							<span id="headerNotificationCount" class="badge"></span>
						</a>
						<ul id="headerNotificationUl" class="dropdown-menu extended notification">
							<li>
								<p id="headerNotificationTotalTip" class="totalTip">您当前没有新消息!</p>
							</li>
							
							<li class="message">
								<%--
								<a href="#">
									<!-- icon-bolt -->
									<span class="label label-success" style="padding-right:8px;"><i class="icon-plus"></i></span>
									蜀创1中心业务一部张三的客户李哥完成签单并移交给您!
									<span class="time">2016-6-5 15:10</span>
								</a>
								 --%>
							</li>
							
							<li id="headerNotificationLastLi" class="external">
								<a id="showNotificationListViewBtn" href="javascript:void(0);">查看所有消息 <i class="m-icon-swapright"></i></a>
							</li>
						</ul>
					</li>
				<!-- END NOTIFICATION DROPDOWN -->
				
				
				
				
				
			
			
			        
			<!-- BEGIN TOP NAVIGATION MENU -->              
				<!-- BEGIN USER LOGIN DROPDOWN -->
				<li class="dropdown user">
					<a href="#" class="dropdown-toggle" data-toggle="dropdown">
						<img alt="" style="width:24px;height:24px;" src="${sessionScope.loginUser.headImg }" />
						<span class="username">${sessionScope.loginUser.name }</span>
						<i class="icon-angle-down"></i>
					</a>
					<ul class="dropdown-menu">
						<li>
							<a href="javascript:;" onclick="myInfo()"><i class="icon-user"></i> 我的资料</a>
						</li>
						<li><a href="javascript:;" onclick="gotoUpdatePssword()"><i class="icon-key"></i> 修改密码</a></li>
<%-- 						<li><a href="javascript:;" onclick="uploadHead(${sessionScope.loginUser.id })"><i class="icon-lock"></i> 修改头像</a></li> --%>
						<c:if test="${administration ne true }">
						<li>
							<a href="javascript:;" onclick="planning()"><i class="icon-tasks"></i> 制定月计划</a>
						</li>	
						</c:if>
						<li class="divider"></li>
						<li><a href="lock.do"><i class="icon-lock"></i> 锁屏</a></li>
						<li><a href="javascript:void(0);" onclick="showSetEmailView()"><i class="icon-lock"></i> 安全设置</a></li>
						<li><a href="logout.do"><i class="icon-signout"></i> 退出</a></li>
					</ul>
				</li>
				<!-- END USER LOGIN DROPDOWN -->
			</ul>
			<!-- END TOP NAVIGATION MENU --> 
		</div>
	</div>
	<!-- END TOP NAVIGATION BAR -->
	
	<div style="
	    position:fixed;
	    color: red;
	    font-size: 22px;
	    top: 10px;
		width:600px;
		left:20%;
		right:20%;
		border:0px solid red;
		display:none;
		margin:0px auto;">
		<p style="text-align:center;">CRM测试系统,不会影响正式数据,请放心测试</p>
	</div>
	
</div>
<!-- END HEADER -->

<script>
	function gotoUpdatePssword(){
		aPost("page/employee/updatePassword.jsp");
	}
	function myInfo(){
		aPost("employee/myInfo.do");
	}
	function planning(){
		aPost("plan/gotoPlanUI.do");
	}
	/* function uploadHead(id){
		aPost("employee/gotoUploadHead.do?id="+id);
	} */
	function showSetEmailView(){
		aPost("securitySettings/setEmailView.do");
	}
</script>