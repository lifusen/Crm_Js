<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en" class="no-js">

<!-- BEGIN HEAD -->
<head>
	<meta charset="utf-8" />
	<title>佳胜科技CRM</title>
	<meta content="width=device-width, initial-scale=1.0" name="viewport" />
	<meta content="ItProject" name="author" />
	<meta name="renderer" content="webkit" />
	<meta http-equiv="X-UA-Compatible" content="IE=Edge,chrome=1"/>
	
	<%@ include file="page/common/style.jsp" %>
	
</head>
<!-- END HEAD -->

<!-- BEGIN BODY -->
<body class="page-header-fixed">
	
	<%@ include file="page/common/header.jsp" %>
	<%@ include file="page/common/notification.jsp" %>
	
	<!-- BEGIN CONTAINER -->
	<div class="page-container">
		
		<%@ include file="page/common/navMenu.jsp" %>
		
		<!-- BEGIN PAGE -->
		<div class="page-content">
			<div class="page-content-body">
				
				<div id="dashboard">
				
				</div>

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
		$('#dashboard').load('desktop/getDesktop.do');
	</script>	
	
</html>