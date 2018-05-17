var updateSecurityEmailBtn = $("#updateSecurityEmailBtn");					// 修改安全邮箱按钮
var showCurrentEmailContainer = $("#showCurrentEmailContainer");			// 显示已存在的安全邮箱容器
var setSecurityEmailContainer = $("#setSecurityEmailContainer");			// 设置安全邮箱的容器
var currentSecurityEmail = $("#currentSecurityEmail");						// 当前的安全邮箱
var resetPasswordForm = $("#resetPasswordForm");							// 重置密码的表单
var forgetPassworTip = $("#forgetPassworTip");								// 重置密码的标题提示
var emailAccountListSelect = $("#emailAccountListSelect");					// 邮箱对应的账号列表

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

	// 验证邮箱地址是否正确
	$.getJSON("securitySettings/validateEmail.do",{email:email},function(result){
		if(result.success){
			sendSecurityEmailCode(email);
		}else{
			securityEmailInputTip.text(result.message);
			return;
		}
	});



});

// 发送邮箱验证码
function sendSecurityEmailCode(email){
	securityEmailCodeInputTip.text("正在发送邮箱验证码...").removeClass("error");
	$.getJSON("securitySettings/sendEmailCode.do",{email:email,type:2},function(result){
		if(result.success){
			securityEmailCodeInputTip.text(result.message).removeClass("error").addClass("success");
		}
	})
}

// 判断邮箱验证码是否正确
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
	$.getJSON("securitySettings/validateEmailCode.do",{email:email,code:code},function(result){
		if(result.success){
			emailCodeValidateSuccess(email);
		}else{
			saveSecurityEmailBtn.attr("disabled",false).addClass("blue");
			securityEmailCodeInputTip.text(result.message);
		}
	})
	return false;
});

/**
 * 邮箱验证成功,获取账号
 * @param email
 */
function emailCodeValidateSuccess(email){
	$.getJSON("securitySettings/getAccountListByEmail.do",{email:email},function(accounts){
		emailAccountListSelect.empty();
		for(var i = 0;i < accounts.length;i++){
			var account = accounts[i];
			$("<option value='"+account.id+"'>"+account.account+"</option/>").appendTo(emailAccountListSelect);
		}
		securityEmailForm.hide();				// 隐藏邮箱验证码表单区域
		forgetPassworTip.hide();
		resetPasswordForm.show();				// 显示重置密码区域
	})
}

resetPasswordForm.submit(function(e){
	e.preventDefault();
	var resetPassword = $("#resetPasswordInput").val();
	var resetRePassword = $("#resetRePasswordInput").val();
	if(!resetPassword){
		alert("请输入新密码");
		return false;
	}
	if(resetPassword.length < 6){
		alert("请输入6位及6位以上的新密码!");
		return false;
	}
	if(resetPassword != resetRePassword){
		alert("两次输入的密码不一致!");
		return false;
	}
	
	var employeeId = emailAccountListSelect.val();
	var employeeAccount = emailAccountListSelect.find("option:selected").text();
	// 发送请求
	$.getJSON("securitySettings/updatePasswordByEmployeeId.do",{employeeId:employeeId,password:resetPassword},function(result){
		if(result.success){
			resetPasswordForm.hide();
			showSecurityEmail.text(employeeAccount);
			showSecurityEmailWrapper.show();
		}else{
			alert(result.message);
		}
	})

	return false;
});