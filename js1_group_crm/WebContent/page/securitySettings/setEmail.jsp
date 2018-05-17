<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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
</style>
<!-- BEGIN PAGE CONTENT-->
<div class="row-fluid">
	<div class="span12" style="padding:8px;">
		<!-- BEGIN SAMPLE FORM PORTLET-->   
		<div class="portlet box blue tabbable">
			<div class="portlet-title">
				<div class="caption">
					<i class="icon-reorder"></i>
					<span class="hidden-480">设置安全邮箱</span>
				</div>
			</div>

			<div class="portlet-body form" style="padding:20px;">
				<div class="alert alert-info">
					<button class="close" data-dismiss="alert"></button>
					<strong>提示!</strong> 设置了安全邮箱之后,忘记密码时可通过该邮箱重置密码!
				</div>
				
				<c:if test="${not empty securityEmail}">
					<div id="showCurrentEmailContainer">
						<h3 style="padding-left:100px;"></h3>
						<h2 style="padding-left:110px;">
							安全邮箱账号:
							<span>${securityEmail}</span>
						</h2>
						<button id="updateSecurityEmailBtn" type="button" class="btn btn-min btn-block blue" style="width:335px;margin-left:180px;">
							 <i class="icon-edit"></i> 修改安全邮箱
						</button>
					</div>
				</c:if>
				
				<div id="setSecurityEmailContainer"
					<c:if test="${not empty securityEmail}"> style="display:none;"</c:if>
				>
					<h2 id="showSecurityEmailWrapper" style="padding-left:150px;;display:none;">安全邮箱设置成功:<span id="showSecurityEmail"></span> 请牢记!</h2>
				
					<!-- BEGIN FORM-->
					<form id="securityEmailForm" action="#" class="form-horizontal">
					
						<c:if test="${not empty securityEmail}">
							<div class="control-group" style="margin-bottom: 10px;">
								<label class="control-label">当前的安全邮箱</label>
								<div class="controls">
									<p id="currentSecurityEmail" style="padding: 0px;margin: 0px;line-height: 35px;font-size: 16px;">
										${securityEmail }
									</p>
								</div>
							</div>
						</c:if>
					
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
				</div>
				
			</div>
		</div>
		<!-- END SAMPLE FORM PORTLET-->
	</div>
</div>
<!-- END PAGE CONTENT-->















    
<script>
	var updateSecurityEmailBtn = $("#updateSecurityEmailBtn");					// 修改安全邮箱按钮
	var showCurrentEmailContainer = $("#showCurrentEmailContainer");			// 显示已存在的安全邮箱容器
	var setSecurityEmailContainer = $("#setSecurityEmailContainer");			// 设置安全邮箱的容器
	var currentSecurityEmail = $("#currentSecurityEmail");						// 当前的安全邮箱

	var securityEmailForm = $("#securityEmailForm");
	var securityEmailInput = $("#securityEmailInput");
	var sendEmailCodeBtn = $("#sendEmailCodeBtn");
	var securityEmailInputTip = $("#securityEmailInputTip");
	var saveSecurityEmailBtn = $("#saveSecurityEmailBtn");
	var showSecurityEmailWrapper = $("#showSecurityEmailWrapper");
	var showSecurityEmail = $("#showSecurityEmail");
	var securityEmailCodeInputTip = $("#securityEmailCodeInputTip");
	
	var emailReg = /^[a-z\d]+(\.[a-z\d]+)*@([\da-z](-[\da-z])?)+(\.{1,2}[a-z]+)+$/;
	var codeReg = /^\d{6}$/;
	
	// 修改安全邮箱按钮点击事件
	updateSecurityEmailBtn.click(function(){
		setSecurityEmailContainer.show();
		showCurrentEmailContainer.hide();
	});
	
	// 验证邮箱
	function validateSecurityEmail(email){
		// 判断是否正确
		if(!emailReg.test(email)){
			securityEmailInputTip.text("请输入正确的邮箱!");
			return false;
		}
		
		var currentSecurityEmailValue = $.trim(currentSecurityEmail.text());
		if(currentSecurityEmailValue && currentSecurityEmailValue == email){
			securityEmailInputTip.text("您设置的新邮箱与当前邮箱一致,请重新输入!");
			return false;
		}
		securityEmailInputTip.text("");
		return true;
	}
	
	// 邮箱输入框键盘输入事件
	securityEmailInput.keyup(function(){
		var v = $(this).val();
		validateSecurityEmail(v);
	});
	
	// 邮箱输入框失去焦点时,触发键盘事件的函数
	securityEmailInput.blur(function(){
		var v = $(this).val();
		validateSecurityEmail(v);
	});
	
	// 发送邮箱验证码
	sendEmailCodeBtn.click(function(){
		// 获取到输入的邮箱
		var email = securityEmailInput.val();
		if(!validateSecurityEmail(email)){
			return;
		}
		
		// 设置邮箱输入框为只读状态
		securityEmailInput.attr("readonly",true);
		// 发送按钮禁用
		$(this).attr("disabled",true).removeClass("blue");
		securityEmailCodeInputTip.text("正在发送邮箱验证码...").removeClass("error");
		
		$.getJSON("securitySettings/sendEmailCode.do",{email:email,type:1},function(result){
			if(result.success){
				securityEmailCodeInputTip.text(result.message).removeClass("error").addClass("success");
			}
		})
	});
	
	// 保存
	securityEmailForm.submit(function(){
		var email = securityEmailInput.val();
		var code = $("#securityEmailCodeInput").val();
		
		// 验证邮箱
		if(!validateSecurityEmail(email)){
			return false;
		}
		
		// 验证
		if(!code || !codeReg.test(code)){
			securityEmailCodeInputTip.text("请输入6位数的验证码!").removeClass("success").addClass("error");
			return false;
		}
		// 禁用保存按钮
		saveSecurityEmailBtn.attr("disabled",true).removeClass("blue");
		// 发送请求
		$.getJSON("securitySettings/saveSecurityEmail.do",{email:email,code:code},function(result){
			if(result.success){
				securityEmailForm.hide();				// 隐藏表单区域
				showSecurityEmail.text(email);			// 设置显示的Email
				showSecurityEmailWrapper.show();		// 显示Email区域
			}else{
				saveSecurityEmailBtn.attr("disabled",false).addClass("blue");
				securityEmailCodeInputTip.text(result.message);
			}
		})
		return false;
	});
	
</script>