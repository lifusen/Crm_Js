var ValidateCustomer = (function(){
	var validateLoadType = function(type){
		var errorTipMsg = "";
		//2016-3-1 start
		//贷款类型必填
		//验证贷款类型对应的必填项
		var loanTypeSelect = $("#loanTypeSelect").val();
		if(loanTypeSelect==''){
			errorTipMsg += '请选择贷款类型!<br/>'; 
		}else if(loanTypeSelect=='房贷'){
			var len = $('#customerAssetArea .houseTemplateTable').length;
			if(len==0){
				errorTipMsg += ("您当前选择的贷款类型为房贷,请添加客户资产中的住房/商铺/写字楼中的任意一个房产,然后填写位置、面积、房产/平均均价!");
				/**
				bootbox.alert(
				    "请添加客户资产中的住房/商铺/写字楼中的任意一个房产,然后填写位置、面积、价值!", 
				    function(){}
				);
				**/
			}else{
				$.each($('#customerAssetArea .houseTemplateTable'),function(i,v){
					//房产类型,住房:1,商铺:2,写字楼:3
					var houseType = $(v).find('th input.houseType').val();
					var houseTypeName = "住房";
					if(houseType==2){
						houseTypeName = "商铺";
					}else if(houseType==3){
						houseTypeName = "写字楼";
					}
					var hourseError = "";
					//保存基本信息
					if(type == 0){
						//住房位置(区) $.trim();
						var placeArea = $.trim($(v).find('td.placeArea input').val());
						//面积
						var area = $.trim($(v).find('td.area input').val());
						//价格
						var price = $.trim($(v).find('td.price input').val());
						//如果住房位置为空
						if(placeArea==""){
							hourseError += "住房位置(区)、";
						}
						if(area==""){
							hourseError += "面积、";
						}
						if(price==""){
							hourseError += "房产/平均均价 ";
						}else{
							//当前房产验证成功
						}
					}else if(type == 1){ //保存客户跟进记录
						//根据贷款类型判断必填项
						//房贷:位置(街道)   房产/平均均价
						//信贷:社保    单位性质  推荐贷款机构      婚姻     征信
						//住房位置(街道) $.trim();
						var placeStreet = $.trim($(v).find('td.placeStreet input').val());
						//价格
						var price = $.trim($(v).find('td.price input').val());
						//如果住房街道为空
						if(placeStreet==""){
							hourseError += "住房位置(街道)、";
						}
						if(price==""){
							hourseError += "房产/平均均价 ";
						}else{
							//当前房产验证成功
						}
					}
					
					if(hourseError!=""){
						hourseError = hourseError.substring(0,hourseError.length-1);
						errorTipMsg += "第"+(i+1)+"个"+houseTypeName+"资产的 "+hourseError+" 未填写!<br/>";
					}
					
				});
				
			}
			
		}else if(loanTypeSelect=='信贷'){
			var creditError = "";
			//月收入  体现   需求资金
			if(type==0){//基本保存
				//月收入
				var monthIncome = $("#monthIncomeSelect").val();
				//体现
				var embodiment = $("#embodimentSelect").val();
				//需求资金
				var requiredMoney = $("#requiredMoneyInput").val();
				if(monthIncome==""){
					creditError += "月收入、";
				}
				if(embodiment==""){
					creditError += "体现、";
				}
				if(requiredMoney==""){
					creditError += "需求资金、";
				}
				
				if(creditError!=""){
					creditError = creditError.substring(0,creditError.length-1);
					errorTipMsg += "您当前选择的是信贷,您还有 "+creditError+" 未填写!<br/>";
				}
			}else if(type==1){//社保    单位性质  推荐贷款机构      婚姻     征信  保存客户跟进记录
				//社保
				var socialInsurance = $("#socialInsuranceSelect").val();
				//单位性质
				var enterpriseNature = $("#enterpriseNatureSelect").val();
				//推荐贷款机构
				var lendingInstitution = $("#lendingInstitutionSelect").val();
				//婚姻
				var marriage = $("#marriageSelect").val();
				//征信
				var credit = $("#creditSelect").val();
				
				if(socialInsurance==""){
					creditError += "社保 ";
				}
				if(enterpriseNature==""){
					creditError += "单位性质 ";
				}
				if(lendingInstitution==""){
					creditError += "推荐贷款机构 ";
				}
				if(marriage==""){
					creditError += "婚姻 ";
				}
				if(credit==""){
					creditError += "征信 ";
				}
				
				if(creditError!=""){
					errorTipMsg += "您当前选择的是信贷,您还有 "+creditError+"未填写!<br/>";
				}
				
			}
		}
		
		//2016-3-1 end
		return errorTipMsg;
	}
	
	
	
	
	return {
		basicSave : function(){
			return validateLoadType(0);
		},
		saveCustomerFollow : function(){
			return validateLoadType(1);
		}
	}
})();