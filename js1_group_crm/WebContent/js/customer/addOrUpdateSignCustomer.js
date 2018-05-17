/**
 * 添加签单联系人
 */
function addSignContactsTemplate(obj){
	var afterTr = $(obj).parents('tr').siblings('tr.signContactsAreaAfter');
	$('#signCustomerTemplateContainer>.signContactsTemplate table tr').clone().insertBefore(afterTr);
	
}

/**
 * 添加贷款产品
 */
function addLoanProduct(obj){
	var afterTr = $(obj).parents('tr').siblings('tr.customerLoanProductAreaAfter');
	var template = $('.loanProductTemplate table tr').clone().insertBefore(afterTr);
	var select = $(template).find('td.lendingInstitution>select');
	activeSelectChosenObject($(select));
}

/**
 * 客户状态改变
 */
function customerStatusChange(obj){
	var v = $(obj).val();
	var text = "";
	if(v==0){	//客户跟进
		//alert('客户跟进中...')
		$('#signCustomerTemplateContainer .signFollowTemplate').clone().insertBefore($(obj).parents('table').siblings('div.updateButtonArea'));
		text="跟进中";
	}else if(v==1){//退单
		$('#signCustomerTemplateContainer .chargebackTemplate').clone().insertBefore($(obj).parents('table').siblings('div.updateButtonArea'));
		text="退单";
	}else if(v==2){//退件
		$('#signCustomerTemplateContainer .rejectTemplate').clone().insertBefore($(obj).parents('table').siblings('div.updateButtonArea'));
		text="退件";
	}
	var input = "<input type='text' value="+text+" class='input' readonly='readonly'/>"+
				"<input type='hidden' name='customerStatus' value='"+v+"'/>"	;
	$(obj).replaceWith(input);
	
}

/**
 * 删除签单联系人
 */
function deleteSignContactsTemplate(obj){
	if(confirm('您确定要删除该联系人吗?')){
		$(obj).parents('tr').remove();
	}
}

/**
 * 删除权证退件消息
 */
function deleteRejectTemplate(obj){
	$(obj).parents('.rejectTemplate').remove();
}

/**
 * 删除权证退件消息
 */
function deleteChargebackTemplate(obj){
	$(obj).parents('.chargebackTemplate').remove();
}

/**
 * 是否添加垫资信息
 */
function hasAddLoaning(obj){
	var v = $(obj).val();
	var loandings = $(obj).parents('.signCustomerTemplate').find('.loaningTemplate');
	var hasLoanding = loandings.length;
	
	if(v=='是'){
		$('#loaningTemplateContainer>.loaningTemplate').clone().insertAfter($(obj).parents('table'));
	}else{
		$(loandings).remove();
	}
}


/**
 * 取消添加签单按钮单击事件
 */
function cancelAddSign(obj){
	//$('#signCustomerArea').empty();
	$(obj).parents('.signCustomerTemplate').remove();
}

/**
 * 验证签单客户表单
 */
function validateSignCustomerForm($form){
	//获取到当前区域的表单对象
	var form = $form[0];
	
	var idCardNo = form['signContacts[0].idCardNo'].value;			//身份证号码
	var age = form['signContacts[0].age'].value;					//年龄
	
	var costOfContract = form.costOfContract.value;					//合同费用
	var loanAmount = form.loanAmount.value;							//贷款金额
	var handsel = form.handsel.value;								//已收定金
	var negotiatorId = form['negotiatorId'].value;					//谈判师
	var receivedCertificate = form.receivedCertificate.value;		//已收证件
	
	var loanType = form['loanProducts[0].loanType'].value;			//贷款类型
	var lendingInstitution = form['loanProducts[0].lendingInstitution'].value;	//贷款机构
	var apr = form['loanProducts[0].apr'].value;					//月利率
	var loanYear = form['loanProducts[0].loanYear'].value;			//贷款年限
	
	
	//2016-3-4
	var bankProduct = form['loanProducts[0].bankProduct'].value;		//银行产品
	
	
	
	
	
	//创建验证对象
	var validation = new Validation();
	
	//验证签单联系人中的身份证号年龄
	validation.idCard(idCardNo,"身份证");							//验证身份证
	validation.integer(age,"年龄");								//验证年龄
	$form.find('.signContactsTemplateTr').each(function(i,v){
		var idCard = $(v).find('td.idCardNo input').val();
		var age = $(v).find('td.age input').val();
		validation.idCard(idCard,"身份证");						//验证身份证
		validation.integer(age,"年龄");							//验证年龄
	});
	
	//validation.currency(costOfContract,"合同费用",true);			//合同费用
	validation.currency(loanAmount,"贷款金额",true);				//贷款金额
	validation.currency(handsel,"已收定金",true);					//已收定金
	validation.required(negotiatorId,"谈判师",true);				//谈判师
	validation.required(receivedCertificate,"已收证件");			//合同费用
	
	//验证贷款产品
	validation.required(loanType,"贷款类型",true);				//贷款类型
	validation.required(lendingInstitution,"贷款机构",true);		//贷款机构
	validation.required(apr,"月利率");							//月利率
	validation.required(loanYear,"贷款年限",true);				//贷款年限

	
	validation.required(bankProduct,"银行产品");					//银行产品
	
	
	
	$form.find('.loanProductTemplateTr').each(function(i,v){
		var loanType = $(v).find('td.loanType select').val();
		var lendingInstitution = $(v).find('td.lendingInstitution select').val();
		var apr = $(v).find('td.apr input').val();
		var loanYear = $(v).find('td.loanYear select').val();
		validation.required(loanType,"贷款类型",true);				//贷款类型
		validation.required(lendingInstitution,"贷款机构",true);		//贷款机构
		validation.required(apr,"月利率");							//月利率
		validation.required(loanYear,"贷款年限",true);				//贷款年限
	});
	
	//垫资金额
	var loaningAmountInput = form['loaningAmount'];					
	if(loaningAmountInput!=undefined){
		validation.currency(loaningAmountInput.value,"垫资金额");
	}
	
	//验证退单退件消息
	var failureMessageTimeInput = form['failureMessage.time'];
	if(failureMessageTimeInput!=undefined){
		validation.required(failureMessageTimeInput.value,"签单失败时间");
	}
	
	var failureMessageCauseInput = form['failureMessage.cause'];
	if(failureMessageCauseInput!=undefined){
		validation.required(failureMessageCauseInput.value,"签单失败原因",true);
	}
	
	
	var errorTipMsg = validation.getErrorMsg();
	

	/**
	//如果是房贷,则验证是否有抵押物
	if(loanType == "房贷"){
		//获取抵押物
		//signCollaterals
		console.log("抵押物....");
		console.log(form['collaterals']);
		var collateralsInput = form['collaterals'];
		var flag = false; //当有抵押物时,验证是否选择
		var hasCollaterals = true;
		if(collateralsInput==null||collateralsInput.length==0){
			hasCollaterals = false;
			errorTipMsg += "没有抵押物,请先填写客户资产中的房产信息!<br/>";
		}else if($(collateralsInput).length==1){
			if(collateralsInput.checked){
				flag = true;
			}
		}else{
			//如果有一个选择,则表示验证通过
			for(var i = 0;i < collateralsInput.length;i++){
				var c = collateralsInput[i].checked;
				if(c){
					flag = true; //验证通过
					break;
				}
			}
		}
		if(hasCollaterals && !flag){
			errorTipMsg += "请选择抵押物!<br/>";
		}
		
	}
	**/
	
	
	return errorTipMsg; //返回错误信息
}

function serializeSignCustomerForm($form){
	//序列化表单对象
	var v = $form.serialize();
	
	//获取签单客户联系信息并拼接成字符串
	var signContactsStr = '';
	$.each($form.find('tr.signContactsTemplateTr'),function(i,v){
		//客户ID
		signContactsStr+='&signContacts['+(i+1)+'].id='+$.trim($(v).find('td input.signContactsId').val());
		//客户姓名
		signContactsStr+='&signContacts['+(i+1)+'].name='+$.trim($(v).find('td.name input').val());
		//身份证号码
		signContactsStr+='&signContacts['+(i+1)+'].idCardNo='+$.trim($(v).find('td.idCardNo input').val());
		//备注
		signContactsStr+='&signContacts['+(i+1)+'].remark='+$.trim($(v).find('td.remark input').val());
		//年龄
		signContactsStr+='&signContacts['+(i+1)+'].age='+$.trim($(v).find('td.age input').val());
	});
	console.log(signContactsStr);
	v+=signContactsStr;
	console.log(v);
	
	//获取签单客户联系信息并拼接成字符串
	var loanProductStr = '';
	$.each($form.find('tr.loanProductTemplateTr'),function(i,v){
		//贷款产品ID
		loanProductStr+='&loanProducts['+(i+1)+'].id='+$.trim($(v).find('td input.loanProductId').val());
		//贷款类型
		loanProductStr+='&loanProducts['+(i+1)+'].loanType='+$.trim($(v).find('td.loanType select').val());
		//贷款机构
		loanProductStr+='&loanProducts['+(i+1)+'].lendingInstitution='+$.trim($(v).find('td.lendingInstitution select').val());
		//银行产品
		loanProductStr+='&loanProducts['+(i+1)+'].bankProduct='+$.trim($(v).find('td.bankProduct input').val());
		//年利率
		loanProductStr+='&loanProducts['+(i+1)+'].apr='+$.trim($(v).find('td.apr input').val());
		//贷款年限
		loanProductStr+='&loanProducts['+(i+1)+'].loanYear='+$.trim($(v).find('td.loanYear input').val());
	});
	console.log(loanProductStr);
	v+=loanProductStr;
	console.log(v);
	return v;
}
