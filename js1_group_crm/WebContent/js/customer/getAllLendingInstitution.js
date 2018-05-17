$(function(){
	$.post('lendingInstitution/getAllLendingInstitution.do',function(data){
		//console.log(data);
		//贷款机构下拉列表对象
		var lendingInstitutionSelect = $('#lendingInstitutionSelect');
		//银行组
		var bankOptgroup = $('<optgroup/>',{label:'银行'}).appendTo(lendingInstitutionSelect);
		//小贷组
		var smallLandOptgroup = $('<optgroup/>',{label:'小贷'}).appendTo(lendingInstitutionSelect);
		$.each(data,function(i,v){
			/**
			 * <optgroup label="银行">
					<option value="zgyh">中国银行</option>
					<option value="nyyh">农业银行</option>
					<option>工商银行</option>
					<option>建设银行</option>
				</optgroup>
				<optgroup label="小贷">
					<option>Minnesota Vikings</option>
				</optgroup>
			 */
			//选项
			var option = $('<option/>',{value : v.id,text : v.name});
			//如果是银行,则添加到银行组
			if(v.type==0){
				option.appendTo(bankOptgroup);
			}else{//否则,添加到小贷组
				option.appendTo(smallLandOptgroup);
			}
			
		});
		//console.log(lendingInstitutionSelect.html());
	});
	
	$("#lendingInstitutionSelect").chosen();
//	$("#lendingInstitutionSelect").trigger("chosen:updated");
	$("#lendingInstitutionSelect").trigger("liszt:updated");
	 //下拉搜索多选框
	 //$("#lendingInstitutionSelect").chosen({
      //  allow_single_deselect: $(this).attr("data-with-diselect") === "1" ? true : false
    //});//.trigger("chosen:updated")
	
});