<%@ page language="java" import="java.util.*,java.net.*" pageEncoding="UTF-8"%><%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %><!DOCTYPE html><!--[if IE 8]> <html lang="en" class="ie8"> <![endif]--><!--[if IE 9]> <html lang="en" class="ie9"> <![endif]--><!--[if !IE]><!--> <html lang="en"> <!--<![endif]--><!-- BEGIN HEAD -->	<% 		try{			String name = "";		    Cookie[] cookies=request.getCookies();		    if(cookies!=null){			    for(int i=0;i<cookies.length;i++){			        if(cookies[i].getName().equals("account")){				        String value =  cookies[i].getValue();				        if(value!=null&&!"".equals(value)){				            name=cookies[i].getValue();				          	name = URLDecoder.decode(name,"UTF-8");				        }			        }			        request.setAttribute("loginAccount",name);			    }		    }		}catch(Exception e){		    e.printStackTrace();		}	%><head>
	<meta charset="utf-8" />
	<title>佳胜科技CRM-登录</title>
	<meta content="width=device-width, initial-scale=1.0" name="viewport" /><meta http-equiv="X-UA-Compatible" content="IE=9" />
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
	<!-- BEGIN PAGE LEVEL STYLES -->
	<link href="media/css/login.css" rel="stylesheet" type="text/css"/>
	<!-- END PAGE LEVEL STYLES -->
	<link href="${pageContext.request.contextPath}/upload/favicon.ico" rel="shortcut icon" />	<style type="text/css">		body{			position: relative;		}		.login .content{			position: absolute;			top:220px;			left:37.2%;		}				.login .copyright{			position: fixed;			bottom: 10px;			left: 43%;		}		#forgetPasswordLink{			cursor: pointer;		}		#sysLogoDiv{			position: absolute;			top:80px;			left:35.8%;			z-index:9999;			width:350px;			margin-top:20px;			height:90px;			background-image: url('upload/sc_group_logo1.png');			border: 0px solid red;		}				#sysLogoDiv img {			border: 0px solid blue;		}	</style>
</head>
<!-- END HEAD -->
<!-- BEGIN BODY -->
<body class="login" style="background-image: url('upload/system_bg.png');">
	<!-- BEGIN LOGO -->
	<div id="sysLogoDiv" class="logo">		 <img alt="" src="upload/JS_LOGO.png"/>
	</div>	<!-- END LOGO -->	<!-- BEGIN LOGIN -->	<div class="content">		<!-- BEGIN LOGIN FORM -->		<form class="form-vertical login-form" action="login.do" method="post">			<h3 class="form-title">登录</h3>			<c:if test="${requestScope.login_error==1 }">				<div class="alert alert-error">					<button class="close" data-dismiss="alert"></button>					<span>用户名或密码错误！</span>				</div>			</c:if>			<c:if test="${requestScope.login_isNull==1 }">				<div class="alert alert-error">					<button class="close" data-dismiss="alert"></button>					<span>请输入用户名和密码！</span>				</div>			</c:if>			<c:if test="${sessionScope.singleLogin == 1 }">				<div class="alert alert-error">					<button class="close" data-dismiss="alert"></button>					<span>你的帐号在其他地方登录，若非本人请修改密码！</span>				</div>			</c:if>			<div class="control-group">				<label class="control-label visible-ie8 visible-ie9">用户名</label>				<div class="controls">					<div class="input-icon left">						<i class="icon-user"></i>						<input id="account" class="m-wrap placeholder-no-fix" value="${requestScope.loginAccount }" type="text" placeholder="用户名" name="account"/>					</div>				</div>			</div>			<div class="control-group">				<label class="control-label visible-ie8 visible-ie9">密码</label>				<div class="controls">					<div class="input-icon left">						<i class="icon-lock"></i>						<input id="password" class="m-wrap placeholder-no-fix" type="password" placeholder="密码" name="password"/>					</div>				</div>			</div>			<div class="form-actions">				<label class="checkbox">					<input type="checkbox" checked="checked" name="flag" id="cacheAccount" value="1"/> 记住我				</label>				<button type="submit" class="btn green pull-right">				登录 <i class="m-icon-swapright m-icon-white"></i>				</button>            				<a id="forgetPasswordLink" href="forgetPassword.jsp">忘记密码?</a>			</div>		</form>	</div>
	<!-- END LOGIN -->
	<!-- BEGIN COPYRIGHT -->
	<div class="copyright">
		2017 &copy; <a style="color: #999;" href="http://www.mrliu.org.cn" target="_blank">佳胜</a> All Rights Reserved
	</div>
	<!-- END COPYRIGHT -->
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
	<!-- BEGIN PAGE LEVEL PLUGINS -->
	<script src="media/js/jquery.validate.min.js" type="text/javascript"></script>
	<!-- END PAGE LEVEL PLUGINS -->
	<!-- BEGIN PAGE LEVEL SCRIPTS -->
	<script src="media/js/app.js" type="text/javascript"></script>
<!-- 	<script src="media/js/login.js" type="text/javascript"></script>       -->
	<!-- END PAGE LEVEL SCRIPTS --> 
<script>	jQuery(document).ready(function() {     		App.init();		$("#cacheAccount").change(function(){			var checked = $(this).attr("checked");			if(checked){				$(this).val(1);			}else{				$(this).val(0);			}		});	});</script></html>