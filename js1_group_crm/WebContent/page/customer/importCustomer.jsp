<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<div class="container-fluid">
	<!-- BEGIN PAGE HEADER-->
	<div class="row-fluid">
		<div class="span12">
			<h3 class="page-title"></h3>
			<ul class="breadcrumb">
				<li>
					<i class="icon-home"></i>
					 <a href="javascript:void(0);">市场部</a>
					<i class="icon-angle-right"></i>
				</li>
				<li><a href="javascript:void(0);">${operationTypeName }</a></li>
				<li class="pull-right no-text-shadow">
					<div id="dashboard-report-range" class="dashboard-date-range tooltips no-tooltip-on-touch-device responsive" data-tablet="" data-desktop="tooltips" data-placement="top" data-original-title="Change dashboard date range">
						<i class="icon-calendar"></i>
						<span></span>
						<i class="icon-angle-down"></i>
					</div>
				</li>
			</ul>
		</div>
	</div>
	<!-- END PAGE HEADER-->
	<div>
		<div class="portlet box ${themeColor}">
				<div class="portlet-title">
					<div class="caption"><i class="icon-cogs"></i>${operationTypeName }</div>
				</div>
				<div class="portlet-body">
					<div class="alert alert-info">
						<strong>提示:</strong>
						请选择Excel文件导入,格式为:xls,xlsx
						<a href="download/蜀创批量导入客户模板.xls" style="padding-left:20px;">下载导入模板</a>
					</div>
					<form id="importCustomerForm" action="customer/importCustomer.do" enctype="multipart/form-data" method="post" style="padding-top:10px;">
						<%-- 操作类型 --%>
						<input id="operationTypeInput" type="hidden" name="operationType" value="${operationType}"/>
						<p>
							<label>
							名单:<input type="text" name="customerRosterName" placeholder="请输入名单名称" style="width:280px;"/>
							</label>
						</p>
						<p style="padding-left:30px;padding-bottom:10px;"><input type="file" name="file" style="border:0px;line-height: 0px;"/></p>
						<p style="padding-left:30px;"><button id="importCustomerBtn" type="submit" class="btn ${themeColor}" style="width:295px;"><i class="icon-upload-alt"></i> ${submitButtonName}</button></p>
						<div id="uploadAfterTip" class="alert alert-success" style="display:none;">
							正在解析中,请稍候...
						</div>
					</form>
				</div>
		</div>
	</div>
</div>

<jsp:include page="../common/fullScreenProgressBarModal.jsp"/>

<script type="text/javascript">
	$("#importCustomerForm").validate({
	    rules: {
	    	customerRosterName:{
	    		required: true,
	    		maxlength:100
	    	},
			file: {
		    	required: true,
				excelType:true
		    }
		},
		messages: {
			customerRosterName:{
	    		required: '必填',
	    		maxlength: '最多输入100个字符!'
	    	},
			file: {
		    	required: '请选择文件!'
		    }
		},submitHandler: function(form) {
			$('#importCustomerBtn').attr('disabled',true);
			
			//全屏进度条
			$('#fullScreenProgressBarModal').modal('show');
			
			//显示提示消息
			$('#uploadAfterTip').show();
			$(form).ajaxSubmit({  
			    dataType: 'json',  
			    success: function(data){
			    	$('#fullScreenProgressBarModal').modal('hide');
			    	if(data.flag==true){
				    	alert(data.msg);
				    	var repeatPhones = data.repeatPhones;
				    	console.log(repeatPhones);
				    	var operationType = $("#operationTypeInput").val();
				    	//alert(operationType);
				    	if(operationType==1){//跳转到分配列表
				    		aPost('customer/getAllotCustomerList.do');
				    	}else if(operationType==2){//公共池
				    		aPost('customer/getCommonPoolList.do');
				    	}
			    	}else{
			    		alert(data.msg)
			    		$('#importCustomerBtn').attr('disabled',false);
			    		$('#uploadAfterTip').hide();
			    	}
			    }  
			});
		}
	 });
</script>
    