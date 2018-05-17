<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<link href="css/jdy.css" rel="stylesheet" type="text/css" media="screen"/>
<!-- BEGIN PAGE CONTAINER-->
<div class="container-fluid p-t-20 desktopContainer">
		
	<!--开始：第1行 -->
	<div class="row-fluid">
		<!--开始：第1行第3列 -->
		<div class="span12">
			<!--开始：第1行第3列中的盒子-->
			<div class="portlet box blue tabbable">
				<div class="portlet-title">
					<div class="caption"><i class="icon-reorder"></i></div>
				</div>
				<!--开始：第1行第3列中的盒子body-->							
				<div class="portlet-body  t-g" style="min-height:435px;">
					<div class="tabbable portlet-tabs" id="qzMeaageLoad">
						
					</div>
				</div>
				<!--结束：第1行第3列中的盒子的body-->
			</div>								
			<!-- 结束：第1行第3列中的盒子-->
		</div>							
		<!--结束：第1行第3列 -->
	</div>
	<!--结束：第1行 -->
	
</div>
<!-- END PAGE CONTAINER--> 


<script>
$(function(){
	$("#qzMeaageLoad").load("message/getDesktopList.do");
});
</script>