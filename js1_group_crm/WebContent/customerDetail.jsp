<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en" class="no-js">

<!-- BEGIN HEAD -->
<head>
	<meta charset="utf-8" />
	<title>CRM</title>
	<meta content="width=device-width, initial-scale=1.0" name="viewport" />
	<meta content="ItProject" name="author" />
	<meta name="renderer" content="webkit" />
	<meta http-equiv="X-UA-Compatible" content="IE=Edge,chrome=1"/>

	<%@ include file="page/common/style.jsp" %>
	
</head>
<!-- END HEAD -->

<!-- BEGIN BODY -->
<body class="page-header-fixed">
	
	<!-- BEGIN HEADER -->
	<div class="header navbar navbar-inverse navbar-fixed-top">
		<div class="navbar-inner">
			<div class="container-fluid">
				<a class="brand" href="index.jsp">
					<img src="${pageContext.request.contextPath}/${systemHeaderLogo}" alt="蜀创集团" style="margin-top:-2px;"/>
				</a>
				
				<ul class="nav pull-right">
					<li class="dropdown user">
						<a href="#" class="dropdown-toggle" data-toggle="dropdown">
							<img alt="" style="width:24px;height:24px;" src="${sessionScope.loginUser.headImg }" />
							<span class="username">${sessionScope.loginUser.name }</span>
							<i class="icon-angle-down"></i>
						</a>
						
						<ul class="dropdown-menu">
							<li><a href="logout.do"><i class="icon-signout"></i> 退出</a></li>
						</ul>
						
					</li>
				</ul>
				
				
			</div>
		</div>
		
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

	
	<!-- BEGIN CONTAINER -->
	<div class="page-container">
		
		<!-- BEGIN PAGE -->
		<div class="page-content" style="margin-left: 0px;">
			<div class="page-content-body" id="mainContentContainer">
				
				

			</div>
		</div>
		<!-- END PAGE -->
	</div>
	<!-- END CONTAINER -->

	<%@ include file="page/common/footer.jsp" %>
	
	<%@ include file="page/common/script.jsp" %>
	
	<!-- 静态包含客户模板 -->
	<%@ include file="page/template/customerTemplate.jsp" %>

	<!-- 引入错误提示的窗体 -->
   	<%@include file="page/common/errorTipModal.jsp"%>

	<!-- 浏览器版本过低提示 -->
   	<%@include file="page/common/browserVersionLowAlert.jsp"%>

	<!-- 加载数据的进度DIV -->
   	<%@include file="page/common/loading.jsp"%>
	
	
	
	<script type="text/javascript">
		$.messager.showLoadProgress();
		var url = "${pageContext.request.contextPath}/customer/gotoValidListUpdateUI.do?id=${customerId}&closeWindow=${closeWindow}";	
		$("#mainContentContainer").load(url,function(){
			$.messager.closeLoadProgress();
		})
	</script>	
	
</html>