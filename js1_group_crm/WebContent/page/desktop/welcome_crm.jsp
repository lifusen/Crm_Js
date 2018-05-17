<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<link href="css/jdy.css" rel="stylesheet" type="text/css" media="screen"/>
<!-- BEGIN PAGE CONTAINER-->
<div class="container-fluid desktopContainer">
	
	<div class="alert alert-info" style="padding: 10px 70px;">
		<H2>欢迎使用蜀创CRM</H2>
		<h4>您当前登录账号为系统超级管理员</h4>
	</div>
	
	<!--开始：第2行 -->
	<div class="row-fluid">
		<div class="span12">
			<!--开始：第2行第1列中的盒子 -->
			<div class="portlet box blue tabbable">
				<div class="portlet-title">
					<div class="caption"><i class="icon-envelope"></i></div>
				</div>
				<!--开始：第2行第1列中的盒子body-->	
				<div id="desktopMessageArea">
					<jsp:include page="../common/loadDataProgress.jsp"/>
				</div>						
				<!--结束：第2行第1列中的盒子的body-->
			</div>								
			<!--结束：第2行第1列中的盒子 -->						
		</div>					
		
	</div>
	<!--结束：第2行 -->
	
	<div style="padding:20px;">&nbsp;</div>
</div>
<!-- END PAGE CONTAINER--> 

<script type="text/javascript">
	//加载消息区域
	$('#desktopMessageArea').load('message/getDesktopList.do');
	
</script>

<script>
	
</script>