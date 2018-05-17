//----------------------------- 验证客户的联系方式 start ----------------------------- 
/**
 * 验证手机号码和座机号码
 * 
 * @param phone
 *            手机或者座机号码
 * @returns 错误消息
 */
function validatePhone(phone) {
	phone = $.trim(phone);
	var errorTipMsg = '';
	var phoneErrorMsg = '您输入的电话号码“' + phone
			+ '”有误,请输入有效的手机号码或者座机号码！手机号码为11位有效的数字,座机号码格式为:区号-号码<br/>';
	if (phone.indexOf('-') == -1) {// 手机
		// 验证长度
		if (phone.length != 11) {
			errorTipMsg += phoneErrorMsg;
		} else {
			// 验证内容
			var phoneReg = /^1[34578]\d{9}$/;
			if (!phoneReg.test(phone)) {
				errorTipMsg += phoneErrorMsg;
			}
		}
	} else {// 座机
		var telReg = /^(\d{3,4})-[1-9]\d{5,7}$/;
		if (!telReg.test(phone)) {
			errorTipMsg += phoneErrorMsg;
		}
	}
	return errorTipMsg;
}

/**
 * 检查客户的电话号码是否存在
 * 
 * @param phone
 *            手机号码
 * @returns {String} 错误消息
 */
function checkCustomerPhoneIsExists(obj) {
	var errorTipMsg = '';
	$.ajax({
		url : 'contacts/getInfoByPhone.do',
		async : false,
		data : {
			phone : obj.phone, // 电话号码
		},
		success : function(data) {
			// console.log(data);
			// console.log(data=='');
			if (data != '') { // 有重复电话号码存在
				errorTipMsg += data + '<br/>'; // 错误消息
			}
		}
	});
	return errorTipMsg;
}

/**
 * 检查客户的手机号码
 */
function checkCustomerPhone(obj) {
	var errorTipMsg = '';
	// 非空验证
	if (obj.phone == '') {
		errorTipMsg += '请输入客户联系方式!<br/>';
	} else {
		// 验证手机号码格式
		errorTipMsg += validatePhone(obj.phone);
	}

	if (errorTipMsg == "") {// 验证格式成功
		// 检查唯一性
		errorTipMsg += checkCustomerPhoneIsExists(obj);
	}
	return errorTipMsg;
}

/**
 * 验证添加或修改客户的电话号码
 */
function validateCustomerPhone(phone) {
	var obj = {
		phone : phone, // 电话号码
	}
	return checkCustomerPhone(obj);
}
/**
 * 验证添加修改客户的通讯录号码
 */
function validateContactsPhone(phone) {
	var obj = {
		phone : phone, // 电话号码
	}
	var errorTipMsg = '';
	// 非空验证
	if (obj.phone == '') {
		errorTipMsg += '请输入客户联系方式!<br/>';
	} else {
		// 验证手机号码格式
		errorTipMsg += validatePhone(obj.phone);
	}

	return errorTipMsg;
}

/**
 * 验证当前页面中的电话号码是否重复和验证通讯录
 */
function validateRepeatAndContactsPhone(customerPhone, customerId) {
	var errorTipMsg = '';
	var phones = [];
	// 获取到客户通讯录中的所有电话号码
	$('#customerBasicInfoArea td.phoneNumber>input').each(function(i, v) {
		var id = $(v).attr('phoneId');
		var phone = $(v).val();
		phones.push({
			id : id,
			phone : phone
		});
	});

	// 判断当前页面中的电话号码是否重复
	if (phones.length > 0) {
		var tempPhones = [ customerPhone ];
		for (var i = 0; i < phones.length; i++) {
			var po = phones[i].phone;
			if (po != '') {
				if (tempPhones.indexOf(po) != -1) {
					errorTipMsg += '不能输入相同的电话号码!';
					break;
				}
			}
			tempPhones.push(po); // 不重复,则添加到临时数组中
		}
	}
	// 判断是否存在,如果有一个存在,则跳出
	if (errorTipMsg == '' && phones.length > 0) {
		// 循环验证,只要发现一个错误,则跳出
		for (var i = 0; i < phones.length; i++) {
			var id = phones[i].id;
			var p = phones[i].phone;
			errorTipMsg += validateContactsPhone(p);
			if (errorTipMsg != '') {
				break;
			}
		}
	}
	return errorTipMsg;
}

// ------------------------------- 验证客户的联系方式 end -------------------------------

/**
 * 验证客户基本信息表单
 * 
 * @param obj
 */
function validateCustomerBasicInfo(obj) {
	var form = obj[0];
	// 创建验证器对象
	var validation = new Validation();

	// 验证年龄:数字类型
	var age = form.age.value;
	validation.integer(age, '年龄');
	// 需求资金:货币类型
	//var requiredMoney = form.requiredMoney.value;
	//validation.currency(requiredMoney, '需求资金');
	// 负债金额:货币类型
	var debtMoney = form['amountliabilitys[0].debtMoney'].value;
	validation.currency(debtMoney, '负债金额');

	$(obj).find('.amountliabilityTemplateTr').each(function(i, v) {
		var debtMoney = $(v).find('td.debtMoney input').val();
		validation.currency(debtMoney, '负债金额');
	});

	// 预估负债总金额:货币类型
	var debtTotal = form.debtTotal.value;
	validation.currency(debtTotal, '预估负债总金额');

	return validation.getErrorMsg();
}
/**
 * 序列化客户基本信息的表单
 * 
 * @param obj
 */
function serializeCustomerBasicInfoForm(form) {
	// 获取手机号码并拼接成字符串
	var phoneStr = '';
	$.each($('#customerBasicInfoArea .phoneTemplateTable'), function(i, v) {
		// 获取到手机ID
		var pId = $.trim($(v).find('td input.phoneId').val());
		// console.log(pId);
		// 手机号码
		var p = $.trim($(v).find('td.phoneNumber input').val());
		// console.log(p);
		// 手机备注
		var r = $.trim($(v).find('td.phoneRemark input').val());
		// console.log(r);

		phoneStr += '&contacts[' + i + '].id=' + pId;
		phoneStr += '&contacts[' + i + '].phone=' + p;
		phoneStr += '&contacts[' + i + '].remark=' + r;

	});
	console.log(phoneStr);

	// 获取房产信息并拼接成字符串
	// var houseStr = '';
	// $.each($('#customerAssetArea .houseTemplateTable'),function(i,v){
	// //房产ID
	// houseStr+='&houses['+i+'].id='+$.trim($(v).find('th
	// input.houseId').val());
	// //住房位置(区) $.trim();
	// houseStr+='&houses['+i+'].placeArea='+$.trim($(v).find('td.placeArea
	// input').val());
	// //住房位置(街道)
	// houseStr+='&houses['+i+'].placeStreet='+$.trim($(v).find('td.placeStreet
	// input').val());
	// //住房位置(详情)
	// houseStr+='&houses['+i+'].placeDetail='+$.trim($(v).find('td.placeDetail
	// input').val());
	// //住房位置(楼盘)
	// houseStr+='&houses['+i+'].placePremise='+$.trim($(v).find('td.placePremise
	// input').val());
	// //国土性质
	// houseStr+='&houses['+i+'].landNature='+$.trim($(v).find('td.landNature
	// input').val());
	// //建造年代
	// houseStr+='&houses['+i+'].constructDate='+$.trim($(v).find('td.constructDate
	// input').val());
	// //面积
	// houseStr+='&houses['+i+'].area='+$.trim($(v).find('td.area
	// input').val());
	// //价格
	// houseStr+='&houses['+i+'].price='+$.trim($(v).find('td.price
	// input').val());
	// //装修情况
	// houseStr+='&houses['+i+'].decorateCase='+$.trim($(v).find('td.decorateCase
	// input').val());
	// //房屋使用情况
	// houseStr+='&houses['+i+'].useCase='+$.trim($(v).find('td.useCase
	// input').val());
	// //租金/月
	// houseStr+='&houses['+i+'].rental='+$.trim($(v).find('td.rental
	// input').val());
	// //房产类型,住房:1,商铺:2,写字楼:3
	// houseStr+='&houses['+i+'].type='+$(v).find('th input.houseType').val();
	// });
	// //console.log(houseStr);
	// //console.log(houseStr.length);
	// if(houseStr.length==249){
	// houseStr = '';
	// }
	//	
	// //获取汽车信息并拼接成字符串
	// var carStr = '';
	// $.each($('#customerAssetArea .carTemplateTable'),function(i,v){
	// //汽车ID
	// carStr+='&cars['+i+'].id='+$.trim($(v).find('th input.carId').val());
	// //购入品牌
	// carStr+='&cars['+i+'].brand='+$.trim($(v).find('td.brand input').val());
	// //购入价值
	// carStr+='&cars['+i+'].price='+$.trim($(v).find('td.price input').val());
	// //购入时间
	// carStr+='&cars['+i+'].buyDate='+$.trim($(v).find('td.buyDate
	// input').val());
	// //贷款情况
	// carStr+='&cars['+i+'].loanStatus='+$.trim($(v).find('td.loanStatus
	// input').val());
	// //月供
	// carStr+='&cars['+i+'].refundMonth='+$.trim($(v).find('td.refundMonth
	// input').val());
	// //还款时间
	// carStr+='&cars['+i+'].refundDate='+$.trim($(v).find('td.refundDate
	// input').val());
	// });
	// //console.log(carStr);
	//	
	// //获取土地信息并拼接成字符串
	// var landStr = '';
	// $.each($('#customerAssetArea .landTemplateTable'),function(i,v){
	// //土地ID
	// landStr+='&lands['+i+'].id='+$.trim($(v).find('td input.landId').val());
	// //土地描述
	// landStr+='&lands['+i+'].description='+$.trim($(v).find('td.description
	// input').val());
	// });
	// //console.log(landStr);
	//	
	// //获取企业信息并拼接成字符串
	// var enterpriseStr = '';
	// $.each($('#customerAssetArea .enterpriseTemplateTable'),function(i,v){
	// //企业ID
	// enterpriseStr+='&enterprises['+i+'].id='+$.trim($(v).find('th
	// input.enterpriseId').val());
	// //企业类型
	// enterpriseStr+='&enterprises['+i+'].type='+$.trim($(v).find('td.type
	// select').val());
	// //年产值
	// enterpriseStr+='&enterprises['+i+'].annualValue='+$.trim($(v).find('td.annualValue
	// select').val());
	// //流水能否体现
	// enterpriseStr+='&enterprises['+i+'].showDetail='+$.trim($(v).find('td.showDetail
	// select').val());
	// //证照情况
	// enterpriseStr+='&enterprises['+i+'].license='+$.trim($(v).find('td.license
	// select').val());
	// //经营场所
	// enterpriseStr+='&enterprises['+i+'].operationPlace='+$.trim($(v).find('td.operationPlace
	// select').val());
	// //经营范围
	// enterpriseStr+='&enterprises['+i+'].operationScope='+$.trim($(v).find('td.operationScope
	// select').val());
	// //股份比例
	// enterpriseStr+='&enterprises['+i+'].shareRatio='+$.trim($(v).find('td.shareRatio
	// select').val());
	// //章程体现
	// enterpriseStr+='&enterprises['+i+'].showStatute='+$.trim($(v).find('td.showStatute
	// select').val());
	// //经营时间
	// enterpriseStr+='&enterprises['+i+'].operationTime='+$.trim($(v).find('td.operationTime
	// select').val());
	// //承兑汇票
	// enterpriseStr+='&enterprises['+i+'].invoice='+$.trim($(v).find('td.invoice
	// select').val());
	// //刷卡客户
	// enterpriseStr+='&enterprises['+i+'].cardCustomer='+$.trim($(v).find('td.cardCustomer
	// select').val());
	// });
	// //console.log(enterpriseStr);
	//	
	// //获取信用卡信息并拼接成字符串
	// var creditCardStr = '';
	// $.each($('#customerAssetArea .creditCardTemplateTable'),function(i,v){
	// //信用卡ID
	// creditCardStr+='&creditCards['+i+'].id='+$.trim($(v).find('td
	// input.creditCardId').val());
	// //发卡银行
	// creditCardStr+='&creditCards['+i+'].bank='+$.trim($(v).find('td.bank
	// input').val());
	// //信用额度
	// creditCardStr+='&creditCards['+i+'].creditLimit='+$.trim($(v).find('td.creditLimit
	// input').val());
	// //逾期情况
	// creditCardStr+='&creditCards['+i+'].overdueCase='+$.trim($(v).find('td.overdueCase
	// input').val());
	// //发卡日期
	// creditCardStr+='&creditCards['+i+'].publishCardDate='+$.trim($(v).find('td.publishCardDate
	// input').val());
	// //卡号
	// creditCardStr+='&creditCards['+i+'].cardNumber='+$.trim($(v).find('td.cardNumber
	// input').val());
	// });
	// //console.log(creditCardStr);
	//	
	// //获取债权构造并拼接成字符串
	// var amountliabilityStr = '';
	// $.each($('#customerAuxiliaryInfoArea
	// .amountliabilityTemplateTr'),function(i,v){
	// //债权构造ID
	// amountliabilityStr+='&amountliabilitys['+(i+1)+'].id='+$.trim($(v).find('td
	// input.amountliabilityId').val());
	// //债权构造
	// amountliabilityStr+='&amountliabilitys['+(i+1)+'].creditor='+$.trim($(v).find('td.creditor
	// select').val());
	// //负债金额
	// amountliabilityStr+='&amountliabilitys['+(i+1)+'].debtMoney='+$.trim($(v).find('td.debtMoney
	// input').val());
	// });
	// //console.log(amountliabilityStr);
	//	
	// //$('#updateCustomerBasicInfoForm').submit();
	// 表单的值
	var v = $(form).serialize();
	// 拼接联系方式
	v += phoneStr;
	// //拼接房产
	// v+=houseStr;
	// //拼接汽车
	// v+=carStr;
	// //拼接土地
	// v+=landStr;
	// //拼接企业
	// v+=enterpriseStr;
	// //拼接信用卡
	// v+=creditCardStr;
	// //拼接债权构造
	// v+=amountliabilityStr;
	// //console.log(v);
	return v;
}
