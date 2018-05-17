// 初始化公司部门级联
new CompanyDepartmentEmployeeCascade({
	companySelect : $("#choiceCompanySelect"),
	departmentSelect : $("#choiceDepartmentSelect"),
	employeeSelect : $("#choiceEmployeeSelect"),
	departmentType : 1,
	companyType : 1,
	companyLabel : "请选择中心",
	departmentLabel : "请选择业务部门",
	level : 3
}).init();

// 显示modal
function showChoiceCompanyDepartmentModal(type,element){
	var typeName = "";
	if(type == 1){
		typeName = "批量分配";
	}else if(type == 2){
		typeName = "分配";
	}else if(type == 3){
		typeName = "整单分配";
	}else if(type == 4){
		typeName = "分单分配";
	}
	$('#choiceCompanyDepartmentModal').data("type",type);
	$('#choiceCompanyDepartmentModal').data("element",element);
	
	$("#choiceCompanyDepartmentModalTitle").text(typeName);
	$("#allotCompanyDepartmentBtn").val(typeName);
	$('#choiceCompanyDepartmentModal').modal('show');
}
// 点击按钮提交表单事件处理
$("#choiceCompanyDepartmentForm").submit(function(e){
	e.preventDefault();
	var companyId = $("#choiceCompanySelect").val();
	var departmentId = $("#choiceDepartmentSelect").val();
	var type = $('#choiceCompanyDepartmentModal').data("type");
	var element = $('#choiceCompanyDepartmentModal').data("element");
	
	if(!companyId || companyId < 1){
		alert("请选择中心");
		return false;
	}
	// ajax提交
	if(type == 1){
		batchAllot(companyId,departmentId);
	}else if(type == 2){
		allotToDepartment(companyId,departmentId,element);
	}else if(type == 3){
		importBatchAllotAll(companyId,departmentId,element);
	}else if(type == 4){
		importBatchAllot(companyId,departmentId,element);
	}
	
	// 关闭modal
	closeChoiceCompanyDepartmentModal();
	return false;
});

function closeChoiceCompanyDepartmentModal(){
	$('#choiceCompanyDepartmentModal').modal('hide');
}

//单个分配
function allotToDepartment(companyId,departmentId,element){
	if(confirm("确定分配?")){
		var tds = $(element).parent().parent().children();
		var cId = $(tds[0]).find("input").val();
		$.post("customer/allotToDepartment.do",{companyId:companyId,dId:departmentId,cId:cId},function(data){
			if(data){
				alert("分配成功，请分配客户到业务员..");
				aPost("customer/getAllotCustomerList.do");
			}
		});
	}
}
// 批量分配
function batchAllot(companyId,departmentId){
	var inputs = $(".customerList");
	var ids = "";
	for(var i=0;i<inputs.length;i++){
		if($(inputs[i]).attr("checked")){
			var id = $(inputs[i]).val();
			ids = ids + (id+",");
		}
	}
	if(ids==""){
		alert("请选择客户");
		return;
	}
	if(confirm("确定分配?")){
		$.post("customer/batchAllotToDepartment.do",{companyId:companyId,dId:departmentId,ids:ids},function(data){
			if(data){
				alert("分配成功，请分配客户到业务员..");
				aPost("customer/getAllotCustomerList.do");
			}
		});
	}
}
//导入数据全单分配
function importBatchAllotAll(companyId,departmentId,element){
	if(confirm("确定分配?")){
		var ulId = $(element).attr("id");
		ulId = ulId.substring(ulId.lastIndexOf("-")+1);
		$("#btn-allot-all-"+ulId).addClass("disabled");
		//全屏进度条
		$('#fullScreenProgressBarModal').modal('show');
		$.post("customer/importBatchAllotAll.do",{companyId:companyId,dId:departmentId,rId:ulId},function(data){
			if(data){
				$("#btn-allot-all-"+ulId).removeClass("disabled");
				$('#fullScreenProgressBarModal').modal('hide');
				alert("分配成功，请分配客户到业务员..");
				aPost("customer/getAllotCustomerList.do");
			}
		});
	}
}
//导入分单分配
function importBatchAllot(companyId,departmentId,element){
	var val = $(element).parent().parent().children("input").val();
	var reg = new RegExp("^[0-9]*$");
	if(!reg.test(val) || val==""){
        alert("请输入分配数量");
        return ;
    }
	var ulId = $(element).attr("id");
	ulId = ulId.substring(ulId.lastIndexOf("-")+1);
	var text = $("#batch-custom-count-"+ulId).text();
	if(parseInt(text)<parseInt(val)){
		alert("最多只能分配"+text+"个客户");
		return ;
	}
	if(confirm("确定分配?")){
		$("#btn-allot-"+ulId).addClass("disabled");
		//全屏进度条
		$('#fullScreenProgressBarModal').modal('show');
			$.post("customer/importBatchAllot.do",{companyId:companyId,dId:departmentId,rId:ulId,count:val},function(data){
			if(data){
				$("#btn-allot-"+ulId).removeClass("disabled");
				$('#fullScreenProgressBarModal').modal('hide');
				alert("分配成功，请分配客户到业务员..");
				aPost("customer/getAllotCustomerList.do");
			}
		});
	}
}