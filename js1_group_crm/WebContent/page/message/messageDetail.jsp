<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html lang="en" class="no-js">

<!-- BEGIN HEAD -->
<head>
	<meta charset="utf-8" />
	<title>${message.title }</title>
	<meta content="width=device-width, initial-scale=1.0" name="viewport" /><meta http-equiv="X-UA-Compatible" content="IE=9" />
	<meta content="ItProject" name="author" />
	<meta http-equiv="X-UA-Compatible" content="IE=9" />

	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    

	<%@ include file="../common/basicStyle.jsp" %>
	<%@ include file="../common/basicScript.jsp" %>
	<style type="text/css">
		/**
		#messageContentArea table{
			border:1px solid #ccc;
		}
		#messageContentArea td{
			border:1px solid #ccc;
		}
		**/
	</style>
</head>
<!-- END HEAD -->

<!-- BEGIN BODY -->
<body>
	<!-- BEGIN CONTAINER -->
	<div class="page-container" style="background-color: white;min-height:630px;padding-top:0px;font-size: 14px;font-family: 微软雅黑;">
		<div class="container-fluid">
			<h3>${typeName }</h3>
			<div class="alert alert-info">
				<h4><c:out value="${message.title }"/></h4>
				<span>发布人:</span> ${message.publisher.name}&nbsp;
				<span>时间:</span> <fmt:formatDate value="${message.pubdate}" pattern="yyyy-MM-dd HH:mm"/>
			</div>
			<div id="messageContentArea" style="padding:10px 5px; border:1px solid #D9EDF7">
				${message.content}
			</div>
		</div>
	</div>
	<!-- END CONTAINER -->

	<%@ include file="../common/footer.jsp" %>

</html>