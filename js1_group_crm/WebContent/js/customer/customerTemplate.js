//添加住房
function addHouseTemplate(obj){
	$('#customerTemplate>.houseTemplateTable').clone().appendTo($('#customerAssetArea'));
	activeInputmask();
}

//添加商铺
function addStoreTemplate(obj){
	var div = $('#customerTemplate>.houseTemplateTable').clone().appendTo($('#customerAssetArea'));
	div.find('span.houseName').html('商铺');		
	div.find('input.houseType').val(2);
	activeInputmask();
}


//添加写字楼
function addOfficeTemplate(obj){
	var div = $('#customerTemplate>.houseTemplateTable').clone().appendTo($('#customerAssetArea'));
	div.find('span.houseName').html('写字楼');		
	div.find('input.houseType').val(3);
	activeInputmask();
}

//添加土地
function addLandTemplate(obj){
	$('#customerTemplate>.landTemplateTable').clone().appendTo($('#customerAssetArea'));
}

//添加汽车
function addCarTemplate(obj){
	$('#customerTemplate>.carTemplateTable').clone().appendTo($('#customerAssetArea'));
	activeInputmask();
}

//添加企业
function addEnterpriseTemplate(obj){
	$('#customerTemplate>.enterpriseTemplateTable').clone().appendTo($('#customerAssetArea'));
	activeInputmask();
}

//添加信用卡
function addCreditCardTemplate(obj){
	$('#customerTemplate>.creditCardTemplateTable').clone().appendTo($('#customerAssetArea'));
	activeInputmask();
}

//添加联系人
function addPhoneTemplate(obj){
	$('#customerTemplate>.phoneTemplateTable').clone().appendTo($('#customerBasicInfoArea'));
}

//添加负债金额
function addAmountliabilityTemplate(obj){
	var tr = $('#customerTemplate>.amountliabilityTemplateTable>table tr').clone().appendTo($('#customerAuxiliaryInfoArea>table'));
	var select = $(tr).find('td.creditor>select');
	 console.log("selcet");
	 console.log(select);
	 
	 activeSelectChosenObject($(select));
	
	 activeInputmask();
}

//----------------------------删除模板----------------------------
//删除房产
function deleteHouseTemplate(obj){
	if(confirm('您确认要删除房产吗?')){
		$(obj).parents('.houseTemplateTable').remove();
	}
}

//删除土地
function deleteLandTemplate(obj){
	if(confirm('您确认要删除土地吗?')){
		$(obj).parents('.landTemplateTable').remove();
	}
}

//删除汽车
function deleteCarTemplate(obj){
	if(confirm('您确认要删除汽车吗?')){
		$(obj).parents('.carTemplateTable').remove();
	}
}

//删除企业
function deleteEnterpriseTemplate(obj){
	if(confirm('您确认要删除企业吗?')){
		$(obj).parents('.enterpriseTemplateTable').remove();
	}
}

//删除信用卡
function deleteCreditCardTemplate(obj){
	if(confirm('您确认要删除信用卡吗?')){
		$(obj).parents('.creditCardTemplateTable').remove();
	}
}

//删除联系人
function deletePhoneTemplate(obj){
	if(confirm('您确认要删除联系人吗?')){
		$(obj).parents('.phoneTemplateTable').remove();
	}
}

//删除负债金额
function deleteAmountliabilityTemplate(obj){
	if(confirm('您确认要删负债金额吗?')){
		$(obj).parents('.amountliabilityTemplateTr').remove();
	}
}
