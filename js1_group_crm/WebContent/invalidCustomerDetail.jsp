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
					<img src="${pageContext.request.contextPath}/upload/sc_logo.png" alt="蜀创担保" style="margin-top:-2px;"/>
				</a>
			</div>
		</div>
		
	</div>
	<!-- END HEADER -->

	
	<!-- BEGIN CONTAINER -->
	<div class="page-container">
		
		<!-- BEGIN PAGE -->
		<div class="page-content" style="margin-left: 0px;min-height: 590px !important;">
			<div class="page-content-body" style="padding:100px 50px;">
				
				<div class="" style="text-align:center;color:red;">
					<h2>链接已失效</h2>
					只有当前登录用户才能查看自己的客户!
				</div>


			</div>
		</div>
		<!-- END PAGE -->
	</div>
	<!-- END CONTAINER -->

	<%@ include file="page/common/footer.jsp" %>
	
	<%@ include file="page/common/script.jsp" %>
	
	
	<script type="text/javascript">
		
	</script>	
	
</html>