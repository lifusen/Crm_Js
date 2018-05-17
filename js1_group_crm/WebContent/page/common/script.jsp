<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
	String contextPath = request.getContextPath();
	String rootPath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+contextPath;
	request.setAttribute("contextPath", contextPath);
	request.setAttribute("rootPath", rootPath);
%>
<script type="text/javascript">
	window.contextPath = '${contextPath}';
	window.rootPath = '${rootPath}';
	window.loginUserId = '${loginUser.id}';
</script>

<!-- BEGIN JAVASCRIPTS(Load javascripts at bottom, this will reduce page load time) -->
<!-- BEGIN CORE PLUGINS -->
<script src="${pageContext.request.contextPath}/media/js/metronic.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/media/js/jquery-1.10.1.min.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/media/js/jquery-migrate-1.2.1.min.js" type="text/javascript"></script>
<!-- IMPORTANT! Load jquery-ui-1.10.1.custom.min.js before bootstrap.min.js to fix bootstrap tooltip conflict with jquery ui tooltip -->
<script src="${pageContext.request.contextPath}/media/js/jquery-ui-1.10.1.custom.min.js" type="text/javascript"></script>      
<script src="${pageContext.request.contextPath}/media/js/bootstrap.min.js" type="text/javascript"></script>
<!--[if lt IE 9]>
<script src="${pageContext.request.contextPath}/media/js/excanvas.min.js"></script>
<script src="${pageContext.request.contextPath}/media/js/respond.min.js"></script>  
<![endif]-->   
<script src="${pageContext.request.contextPath}/media/js/jquery.slimscroll.min.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/media/js/jquery.blockui.min.js" type="text/javascript"></script>  
<script src="${pageContext.request.contextPath}/media/js/jquery.cookie.min.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/media/js/jquery.uniform.min.js" type="text/javascript" ></script>
<!-- END CORE PLUGINS -->
<!-- BEGIN PAGE LEVEL PLUGINS -->
<script src="${pageContext.request.contextPath}/media/js/jquery.flot.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/media/js/jquery.flot.resize.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/media/js/jquery.pulsate.min.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/media/js/jquery.gritter.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/media/js/jquery.easy-pie-chart.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/media/js/jquery.sparkline.min.js" type="text/javascript"></script>  
<script src="${pageContext.request.contextPath}/media/js/table-ajax.js" type="text/javascript"></script>  
<script src="${pageContext.request.contextPath}/js/plugs/bootstrap-paginator.min.js" type="text/javascript"></script>  
<!-- END PAGE LEVEL PLUGINS -->
<!-- BEGIN PAGE LEVEL SCRIPTS -->
    

<!-- 表单 -->
<script src="${pageContext.request.contextPath}/media/js/chosen.jquery.min.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/media/js/jquery.validate.min.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/js/plugs/jquery.form.js"></script>

<!-- 日期相关插件的JS -->
<script src="${pageContext.request.contextPath}/media/js/bootstrap-datepicker.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/media/js/bootstrap-datetimepicker.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/media/js/bootstrap-datetimepicker.zh-CN.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/media/js/date.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/media/js/daterangepicker.js" type="text/javascript"></script> 
<script src="${pageContext.request.contextPath}/media/js/bootstrap-timepicker.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/media/js/fullcalendar.min.js" type="text/javascript"></script>


<!-- 图表 -->
<script src="${pageContext.request.contextPath}/js/plugs/highstock.js" type="text/javascript"></script>

<!-- 数据表格 -->
<script src="${pageContext.request.contextPath}/media/js/select2.min.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/media/js/jquery.dataTables.min.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/media/js/DT_bootstrap.js" type="text/javascript"></script>

<!-- 公共插件 -->

<!-- 公共JS -->
<script  src="${pageContext.request.contextPath}/js/common/constant.js" type="text/javascript"></script>
<script  src="${pageContext.request.contextPath}/js/common/common.js" type="text/javascript"></script>
<script  src="${pageContext.request.contextPath}/js/common/DateUtil.js" type="text/javascript"></script>

<!-- 应用程序 -->
<script src="${pageContext.request.contextPath}/media/js/app.js" type="text/javascript"></script>

<!-- 自定义JS -->
<script src="${pageContext.request.contextPath}/media/js/index.js" type="text/javascript"></script>        
<script src="${pageContext.request.contextPath}/js/common/aPost.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/js/common/preventBackspace.js" type="text/javascript"></script>

<!-- 验证器 -->
<script src="${pageContext.request.contextPath}/js/common/Validation.js" type="text/javascript"></script>
<!-- 添加或修改客户基本信息 -->
<script src="${pageContext.request.contextPath}/js/customer/addOrUpdateCustomerBasicInfo.js" type="text/javascript"></script>
<!-- 添加客户的各种验证 -->  
<script src="${pageContext.request.contextPath}/js/common/validateCustomer.js" type="text/javascript"></script>  
 
<!-- 消息框插件 -->
<script src="${pageContext.request.contextPath}/js/plugs/bootbox.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/js/plugs/jquery.messager.js" type="text/javascript"></script>

<!-- 路径工具 -->
<script src="${pageContext.request.contextPath}/js/locationUtil.js" type="text/javascript"></script>

<!-- 消息通知DOM操作工具类 -->
<script src="${pageContext.request.contextPath}/js/notificationUtil.js"></script>
 
<!-- 消息通知 -->
<script src="${pageContext.request.contextPath}/js/notify/notify.js"></script>
<script src="${pageContext.request.contextPath}/js/notify/audio.js"></script>
<script src="${pageContext.request.contextPath}/js/websocket/websocketHandler.js"></script>
<script src="${pageContext.request.contextPath}/js/websocket/websocketClient.js"></script> 

<!-- 公司部门员工级联 -->
<script src="${pageContext.request.contextPath}/js/companyDepartmentEmployeeCascade.js"></script> 
 
<!-- END PAGE LEVEL SCRIPTS -->  
<script>
	jQuery(document).ready(function() {
		   App.init(); // initlayout and core plugins
	});
	
	// 初始化消息通知头部列表数据
	NotificationUtil.init();
</script>
<!-- END JAVASCRIPTS -->