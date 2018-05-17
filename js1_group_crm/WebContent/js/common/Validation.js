/**
 * 自定义验证器类
 * required:必填
 * currency:货币
 * integer:整数
 * idCard:身份证
 */
var Validation = function(){
	this.error = "",
	this.currencyRegExp = /^\d+(\.\d{1,2})?$/,	
	this.integerRegExp = /^\d+$/,
	this.idCardRegExp = /^\d{6}(18|19|20)?\d{2}(0[1-9]|1[012])(0[1-9]|[12]\d|3[01])\d{3}(\d|X)$/i;
	/**
	 * 必填验证器
	 * @param v 值
	 * @param name 名称(如姓名)
	 * @param isSelect true表示是下拉列表框
	 * @returns true:验证成功/false:验证失败
	 */
	this.required = function(v,name,isSelect){
		var v = $.trim(v);
		var prefix = "请输入";
		if(isSelect==true){
			prefix = "请选择";
		}
		if(v==""){
			this.error+=(prefix+name+'!<br/>');
			return false;
		}
		return true;
	},
	/**
	 * 整数验证器
	 * @param v 值
	 * @param name 名称(如年龄)
	 * @param isRequired true:必填
	 */
	this.integer = function(v,name,isRequired){
		var v = $.trim(v);
		var errorMsg = "您输入的"+name+"“"+v+"”有误,请输入整数!<br/>";
		//必填,且是整数类型
		if(isRequired==true){
			if(this.required(v,name)){
				if(!this.integerRegExp.test(v)){
					this.error+=(errorMsg);
				}
			}
		}else{//不是必须的,且是数字类型
			if(v!=''){
				//验证
				if(!this.integerRegExp.test(v)){
					this.error+=(errorMsg);
				}
			}
		}
	},
	/**
	 * 货币验证器
	 * @param v 值
	 * @param name 名称(如贷款金额)
	 * @param isRequired true:必填
	 */
	this.currency = function(v,name,isRequired){
		var v = $.trim(v);
		var errorMsg = "您输入的"+name+"“"+v+"”有误,请输入有效的金额,且金额只保留2位小数,单位默认为元!<br/>";
		if(isRequired==true){//必填,且是货币类型
			if(this.required(v,name)){
				if(!this.currencyRegExp.test(v)){
					this.error+=errorMsg
				}
			}
		}else{//不是必须的,且是数字类型
			if(v!=''){
				//验证
				if(!this.currencyRegExp.test(v)){
					this.error+=errorMsg
				}
			}
		}
	},
	/**
	 * 货币验证器
	 * @param v 值
	 * @param name 名称(如贷款金额)
	 * @param isRequired true:必填
	 */
	this.decimal = function(v){
		var v = $.trim(v);
		if(this.currencyRegExp.test(v)){
			return true;
		}
		return false;
	},
	/**
	 * 身份证验证器
	 * @param v 值
	 * @param name 名称(如身份证)
	 * @param isRequired true:必填
	 */
	this.idCard = function(v,name,isRequired){
		var v = $.trim(v);
		var suffix = "您输入的身份证“"+v+"”有误,请输入15/18合法的身份证号码!<br/>";
		if(isRequired==true){//必填,且是合法的身份证
			if(this.required(v,name)){
				if(!this.idCardRegExp.test(v)){
					this.error+=(suffix);
				}
			}
		}else{//不是必须的,且是合法的身份证
			if(v!=''){
				//验证
				if(!this.idCardRegExp.test(v)){
					this.error+=(suffix);
				}
			}
		}
	},
	
	
	/**
	 * 判断是否通过验证
	 * @returns true:通过验证/false:未通过验证
	 */
	this.isValidate = function(){
		if(this.error==''){
			return true;
		}
		return false;
	},
	/**
	 * 获取错误消息
	 * @returns 错误消息
	 */
	this.getErrorMsg = function(){
		return this.error;
	}
}