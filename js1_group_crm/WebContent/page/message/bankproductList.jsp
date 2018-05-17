<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en" class="no-js">

<!-- BEGIN HEAD -->
<head>
	<meta charset="utf-8" />
	<title>CRM</title>
	<meta content="width=device-width, initial-scale=1.0" name="viewport" /><meta http-equiv="X-UA-Compatible" content="IE=9" />
	<meta content="ItProject" name="author" />
	<meta http-equiv="X-UA-Compatible" content="IE=9" />

	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    

	<%@ include file="../common/basicStyle.jsp" %>
	<%@ include file="../common/basicScript.jsp" %>
</head>
<!-- END HEAD -->

<!-- BEGIN BODY -->
<body>
	<!-- BEGIN CONTAINER -->
	<div class="page-container" style="background-color: white;min-height:700px;">
		<div id="bankproducatContainer" class="container-fluid">
			
			<div class="row-fluid">
				<div class="span12">
					<h3 class="page-title"></h3>
					<ul class="breadcrumb">
						<li><i class="icon-home"></i> <a href="javascript:void(0);">银行产品列表</a></li>
					</ul>
				</div>
			</div>
		
		
			<div class="row-fluid">
				<div class="span12">
					<div class="portlet box blue">
						<div class="portlet-title">
							<div class="caption"><i class="icon-cogs"></i>银行产品列表</div>
						</div>
						<div class="portlet-body">		
							<div id="messageListArea">
								<jsp:include page="bankproductTable.jsp"/>
							</div>
						</div>										
					</div>
				</div>
			</div>
		</div>
	</div>
	<!-- END CONTAINER -->

	<%@ include file="../common/footer.jsp" %>

</html>