<%@ page language="java" import="java.util.*,java.net.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<!--[if IE 8]> <html lang="en" class="ie8"> <![endif]-->
<!--[if IE 9]> <html lang="en" class="ie9"> <![endif]-->
<!--[if !IE]><!--> <html lang="en"> <!--<![endif]-->
<!-- BEGIN HEAD -->
<head>
	<meta charset="utf-8" />
	<title>CRM-忘记密码</title>
	<meta content="width=device-width, initial-scale=1.0" name="viewport" />
	<meta content="" name="description" />
	<meta content="" name="author" />
	<!-- BEGIN GLOBAL MANDATORY STYLES -->
	<link href="media/css/bootstrap.min.css" rel="stylesheet" type="text/css"/>
	<link href="media/css/bootstrap-responsive.min.css" rel="stylesheet" type="text/css"/>
	<link href="media/css/font-awesome.min.css" rel="stylesheet" type="text/css"/>
	<link href="media/css/style-metro.css" rel="stylesheet" type="text/css"/>
	<link href="media/css/style.css" rel="stylesheet" type="text/css"/>
	<link href="media/css/style-responsive.css" rel="stylesheet" type="text/css"/>
	<link href="media/css/default.css" rel="stylesheet" type="text/css" id="style_color"/>
	<link href="media/css/uniform.default.css" rel="stylesheet" type="text/css"/>
	<!-- END GLOBAL MANDATORY STYLES -->
	<link href="${pageContext.request.contextPath}/upload/favicon.ico" rel="shortcut icon" />

	<style>
		span.error{
			color : red;
		}
		span.success{
			color : green;
			font-size:15px;
			font-weight: bold;
		}
		#sendEmailCodeBtn{
			width:109px;
		}
		#saveSecurityEmailBtn{
			width:335px;
		}

		#resetPasswordBtn{
			width:335px;
		}
		#setSecurityEmailContainer{
			    width: 720px;
				border: 0px solid red;
				margin: 0px auto;
		}
	</style>

</head>
<!-- END HEAD -->

<!-- BEGIN BODY -->
<body class="page-header-fixed">
	<!-- BEGIN HEADER -->
<div class="header navbar navbar-inverse navbar-fixed-top">
	<!-- BEGIN TOP NAVIGATION BAR -->
	<div class="navbar-inner">
		<div class="container-fluid">
			<!-- BEGIN LOGO -->
			<a class="brand" href="index.jsp">
				<img src="upload/sc_logo.png" alt="蜀创担保" style="margin-top:-2px;"/>
			</a>
			<!-- END LOGO -->
			<!-- BEGIN RESPONSIVE MENU TOGGLER -->
			<a href="javascript:;" class="btn-navbar collapsed" data-toggle="collapse" data-target=".nav-collapse">
			<img src="media/image/menu-toggler.png" alt="" />
			</a>          
			<!-- END RESPONSIVE MENU TOGGLER -->    
			
		</div>
	</div>
	<!-- END TOP NAVIGATION BAR -->
	

	
</div>
<!-- END HEADER -->

	<!-- BEGIN CONTAINER -->   
	<div class="page-container row-fluid">
		
		<!-- BEGIN PAGE -->
		<div class="page-content" style="margin-left:0px;min-height:590px;">
			<!-- BEGIN PAGE CONTAINER-->
			<div class="container-fluid" style="height:auto;padding-top:10px;">
				

				<!-- BEGIN PAGE CONTENT-->


				<div class="row-fluid">
					<div class="span12">
						<!-- BEGIN PROGRESS BARS PORTLET-->
						<div class="portlet box green">
							<div class="portlet-title">
								<div class="caption"><i class="icon-cogs"></i>忘记密码</div>
								<div class="tools">
									<a href="javascript:;" class="collapse"></a>
								</div>
							</div>
							<div class="portlet-body">



								<div id="forgetPassworTip" class="alert alert-info" style="text-align:center;">
									<button class="close" data-dismiss="alert"></button>
									<strong>提示!</strong>请输入您设置的安全邮箱地址,进行密码重置!
								</div>



								<div id="setSecurityEmailContainer">

									<!-- BEGIN FORM-->
									<form id="securityEmailForm" action="#" class="form-horizontal">

										<div class="control-group">
											<label class="control-label">邮箱</label>
											<div class="controls">
												<input id="securityEmailInput" type="text" placeholder="请输入安全邮箱" class="m-wrap large" />
												<span id="securityEmailInputTip" class="help-inline error"></span>
											</div>
										</div>

										<div class="control-group">
											<label class="control-label">邮箱验证码</label>
											<div class="controls">
												<input id="securityEmailCodeInput" type="text" placeholder="请输入邮箱中收到的验证码" class="m-wrap" />
												<button id="sendEmailCodeBtn" type="button" class="btn btn-min blue">
													发送验证码
												</button>
												<span id="securityEmailCodeInputTip" class="help-inline error"></span>
											</div>
										</div>

										<div class="control-group">
											<label class="control-label"></label>
											<div class="controls">
												<button id="saveSecurityEmailBtn" type="submit" class="btn blue">下一步</button>
											</div>
										</div>

									</form>
									<!-- END FORM-->


									<!-- 重置密码 -->
									<!-- BEGIN FORM-->
									<form id="resetPasswordForm" action="#" class="form-horizontal" style="display:none;">

										<div class="control-group" style="margin-bottom: 10px;">
											<label class="control-label">账号</label>
											<div class="controls">
												<p id="currentSecurityEmail" style="padding: 0px;margin: 0px;line-height: 35px;font-size: 16px;"></p>
												<select id="emailAccountListSelect" class="m-wrap large">
												</select>
											</div>
										</div>

										<div class="control-group">
											<label class="control-label">新密码</label>
											<div class="controls">
												<input id="resetPasswordInput" type="password" placeholder="请输入新密码" class="m-wrap large" />
												<span id="resetPasswordInputTip" class="help-inline error"></span>
											</div>
										</div>

										<div class="control-group">
											<label class="control-label">确认新密码</label>
											<div class="controls">
												<input id="resetRePasswordInput" type="password" placeholder="请输入确认新密码" class="m-wrap large" />
												<span id="resetRePasswordInputTip" class="help-inline error"></span>
											</div>
										</div>



										<div class="control-group">
											<label class="control-label"></label>
											<div class="controls">
												<button id="resetPasswordBtn" type="submit" class="btn blue">重置密码</button>
											</div>
										</div>

									</form>
									<!-- END FORM-->


									<h2 id="showSecurityEmailWrapper" style="padding-left:150px;display:none;">账号<span id="showSecurityEmail"></span>密码重置成功!
										<br/>
										<a href="login.jsp" class="btn btn-link blue" style="width:335px;">立即登录</a>
									</h2>


								</div>




							</div>
						</div>
						<!-- END PROGRESS BARS PORTLET-->
					</div>
				</div>


				<!-- END PAGE CONTENT-->

			</div>
			<!-- END PAGE CONTAINER-->
		</div>
		<!-- END PAGE -->
	</div>
	<!-- END CONTAINER -->

	<!-- BEGIN FOOTER -->
	<div class="footer" style="text-align:center;">
		<div class="footer-inner" style="float:none;">
			2015 © <a style="color: #999;" href="http://www.mrliu.org.cn" target="_blank">ItProject</a> All Rights Reserved
		</div>
		<div class="footer-tools">
			<span class="go-top">
			<i class="icon-angle-up"></i>
			</span>
		</div>
	</div>
	<!-- END FOOTER -->

	<!-- BEGIN JAVASCRIPTS(Load javascripts at bottom, this will reduce page load time) -->
	<!-- BEGIN CORE PLUGINS -->
	<script src="media/js/jquery-1.10.1.min.js" type="text/javascript"></script>
	<script src="media/js/jquery-migrate-1.2.1.min.js" type="text/javascript"></script>
	<!-- IMPORTANT! Load jquery-ui-1.10.1.custom.min.js before bootstrap.min.js to fix bootstrap tooltip conflict with jquery ui tooltip -->
	<script src="media/js/jquery-ui-1.10.1.custom.min.js" type="text/javascript"></script>
	<script src="media/js/bootstrap.min.js" type="text/javascript"></script>
	<!--[if lt IE 9]>
	<script src="media/js/excanvas.min.js"></script>
	<script src="media/js/respond.min.js"></script>
	<![endif]-->
	<script src="media/js/jquery.slimscroll.min.js" type="text/javascript"></script>
	<script src="media/js/jquery.blockui.min.js" type="text/javascript"></script>
	<script src="media/js/jquery.cookie.min.js" type="text/javascript"></script>
	<script src="media/js/jquery.uniform.min.js" type="text/javascript" ></script>
	<!-- END CORE PLUGINS -->
	<script src="media/js/app.js"></script>
	<script>
		jQuery(document).ready(function() {
		   App.init();
		});
	</script>
	<!-- END JAVASCRIPTS -->
	
	<script src="js/forgetPassword.js"></script>
	
</body>
<!-- END BODY -->
</html>