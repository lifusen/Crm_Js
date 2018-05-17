$(function(){
	//设置jQuery AJAX全局配置:不缓存
	$.ajaxSetup({ cache: false });
	//
})
//--------------------------- jquery validate plug addMethod start ---------------------------
$.validator.addMethod("excelType",function(value){
	//console.log(value)
	var lastIndex = value.lastIndexOf('.');
	var suffix = value.substring(lastIndex+1);
	//console.log(suffix);
	value = value.toLowerCase();
	if("xls"==suffix||"xlsx"==suffix){
		return true;
	}
	return false;
},"请选择xls或xlsx格式的Excel文件!");


$.validator.addMethod("imgType",function(value){
	//console.log(value)
	var lastIndex = value.lastIndexOf('.');
	var suffix = value.substring(lastIndex+1);
	//console.log(suffix);
	if(value==null || value==""){
		return false;
	}
	value = value.toLowerCase();
	if("jpg"==suffix||"png"==suffix){
		return true;
	}
	return false;
},"请选择jpg或者png格式的图片");

/**
 * 压缩包类型
 */
$.validator.addMethod("zipType",function(value){
	//console.log(value)
	var lastIndex = value.lastIndexOf('.');
	var suffix = value.substring(lastIndex+1);
	//console.log(suffix);
	if(value==null || value==""){
		return false;
	}
	value = value.toLowerCase();
	if("zip"==suffix||"rar"==suffix||"7z"==suffix){
		return true;
	}
	return false;
},"请选择zip/rar/7z格式的压缩文件!");
//--------------------------- jquery validate plug addMethod end ---------------------------

/**
 * 激活文本框控件的输入格式
 
function activeInputmask(){
	//十位
	$('.inputmask-integer-2').inputmask({ "mask": "9", "repeat": 2, "greedy": false });
	//千万
	$('.inputmask-integer-8').inputmask({ "mask": "9", "repeat": 8, "greedy": false });
	//小数
	$('.inputmask-decimal').inputmask("decimal",{digits: 2, "greedy": false,rightAlignNumerics: false});
}
*/

/**
 * 激活下拉搜索多选框
 */
function activeSelectChosen(){
	 $(".lendingInstitutionSelect").chosen({
         allow_single_deselect: $(this).attr("data-with-diselect") === "1" ? true : false
     });
}
/**
 * 激活下拉搜索多选框
 */
function activeSelectChosenObject(obj){
	$(obj).chosen({
		allow_single_deselect: $(this).attr("data-with-diselect") === "1" ? true : false
	});
}

/**
 * 关闭当前窗口
 */
var  closeWindow = function(){
    window.open('', '_self', '');
    window.close();
}