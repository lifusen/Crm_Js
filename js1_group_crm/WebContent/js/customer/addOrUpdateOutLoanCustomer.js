/**
 * 隐藏放款
 * 
 * @param obj
 */
function hideOutLoan(obj) {
	$('#outLoanCustomerTemplateContainer').hide();
	$("#addOutLoanCustomerBtn").attr('disabled', false).addClass('blue');
	$('#addOutLoanCustomerForm')[0].reset();
}

/**
 * 是否添加垫资信息
 */
function hasAddLoaning(obj) {
	var v = $(obj).val();
	var loandings = $(obj).parents('#outLoanCustomerContainer').find(
			'.loaningTemplate');
	var hasLoanding = loandings.length;

	if (v == '是') {
		$('#loaningTemplateContainer>.loaningTemplate').clone().insertAfter(
				$(obj).parents('table'));
	} else {
		$(loandings).remove();
	}
}

var errorTipMsg = "";

/**
 * 非空验证
 * 
 * @param obj
 *            表单
 * @param errorTipMsg
 *            累加的错误提示消息
 * @param errorMsg
 *            错误消息字符串
 */
function required(obj, errorMsg) {
	if (obj == "") {
		errorTipMsg += errorMsg + "!<br/>";
	}
}

/**
 * 验证放款客户
 */
function validateOutLoanCustomer($form) {
	// 表单验证
	// 获取到当前区域的表单对象
	var form = $form[0];
	console.log(form);

	var name = form.name.value; // 客户名称
	var loanAmount = form.loanAmount.value; // 贷款金额
	var realPrice = form.realPrice.value; // 实收费用
	var receivedDeposit = form.receivedDeposit.value; // 已收定金
	var cost = form.cost.value; // 后台成本
	var netEarnings = form.netEarnings.value; // 净业绩
	var paymentAmount = form.paymentAmount.value; // 还款金额
	var paymenttime = form.paymenttime.value; // 还款时间
	var repaymentType = form.repaymentType.value; // 还款方式

	var loanType = form['loanProducts[0].loanType'].value; // 放款类型
	var lendingInstitution = form['loanProducts[0].lendingInstitution'].value; // 放款机构
	var apr = form['loanProducts[0].apr'].value; // 月利率
	var loanYear = form['loanProducts[0].loanYear'].value; // 贷款年限

	// 验证
	var validation = new Validation();
	validation.required(name, "客户名称");
	validation.currency(loanAmount, "贷款金额", true);
	/*
	 * validation.currency(realPrice,"实收费用",true);
	 * validation.currency(receivedDeposit,"已收定金",true);
	 * validation.currency(cost,"后台成本",true);
	 * validation.currency(netEarnings,"净业绩",true);
	 */

	// 验证放款产品
	validation.required(loanType, "放款类型", true); // 放款类型
	validation.required(lendingInstitution, "放款机构", true); // 放款机构
	validation.required(apr, "月利率"); // 月利率
	validation.required(loanYear, "贷款年限", true); // 贷款年限

	$form.find('.loanProductTemplateTr').each(
			function(i, v) {
				var loanType = $(v).find('td.loanType select').val();
				var lendingInstitution = $(v).find(
						'td.lendingInstitution select').val();
				var apr = $(v).find('td.apr input').val();
				var loanYear = $(v).find('td.loanYear select').val();
				validation.required(loanType, "放款类型", true); // 放款类型
				validation.required(lendingInstitution, "放款机构", true); // 放款机构
				validation.required(apr, "月利率"); // 月利率
				validation.required(loanYear, "贷款年限", true); // 贷款年限
			});

	validation.currency(paymentAmount, "还款金额", true);
	validation.required(paymenttime, "还款时间", true);
	validation.required(repaymentType, "还款方式", true);

	// 垫资金额
	var loaningAmountInput = form['loaningAmount'];
	if (loaningAmountInput != undefined) {
		validation.currency(loaningAmountInput.value, "垫资金额");
	}

	return validation.getErrorMsg();
}

function serializeOutLoanCustomerForm($form) {
	// 序列化表单对象
	var v = $form.serialize();
	return v;
}
