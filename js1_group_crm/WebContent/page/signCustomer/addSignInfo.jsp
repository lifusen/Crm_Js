<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!-- 签单客户 start-->
<div id="signCustomerContainer" style="display: ${customer.state==4||customer.state==5||customer.state==6||customer.state==7?'block':'none'};">
	<div class="portlet box blue">
		<div class="portlet-title">
			<div class="caption"><i class="icon-cogs"></i>签单客户</div>
			<div class="tools">
				<a class="collapse" href="javascript:;"></a>
			</div>
		</div>
		<div class="portlet-body">
			<div id="signCustomerArea">
			
				
			<div id="showSignCustomerSummaryBtnGroup" class="signCustomerBtnGroup">
			
			</div>
				<div id="updateSignCustomerListArea">
					<c:if test="${customer.state==4}">
						<div class="progress progress-striped active" style="padding:10px 0px;">
							<div style="width: 100%;" class="bar"></div>
						</div>
						正在加载签单信息....
					</c:if>
				</div>
						
			</div>
		</div>
	</div>
</div>
<!-- 签单客户 end-->

<!--引入签单客户模板 -->
<jsp:include page="../template/signCustomerTemplate.jsp"/>

<!--引入签单贷款产品模板 -->
<jsp:include page="../template/loanProductTemplate.jsp"/>

<!--引入上传证件模板 -->
<jsp:include page="../template/uploadCertificateModal.jsp"/>

<!--引入显示证件模板 -->
<jsp:include page="../template/showCertificateModal.jsp"/>

<!-- 添加或修改签单客户信息 -->  
<script src="${pageContext.request.contextPath}/js/customer/addOrUpdateSignCustomer.js" type="text/javascript"></script>  
 
<script type="text/javascript">
$(function(){
	
	//签单
	$('#customerSignBtn').click(function(){
		var count = $('#signCustomerArea .signCustomerTemplate.add').length;
		if(count>0){
			alert('您有一笔未完成的签单,请先保存或取消!');
			return;
		}
		
		var isShow = $('#signCustomerContainer').is(':hidden');
		if(isShow==true){
			$('#signCustomerContainer').show();
		}
		signCustomerTemplate();		//添加签单模板
		
		//获取到抵押物
		var signCollaterals = $('#signCustomerArea td.signCollaterals>input');
		console.log(signCollaterals);
		// alert(123);
		$.each(signCollaterals,function(i,v){
			$(v).attr('type','checkbox');
		});
	});
	
	
	//下拉搜索多选框
	$(".chosen").chosen({
	   allow_single_deselect: $(this).attr("data-with-diselect") === "1" ? true : false
	});
	
	
	// 获取当前客户所有签单客户的概要信息
	// 显示签单列表的按钮组
	var $showSignCustomerSummaryBtnGroup = $("#showSignCustomerSummaryBtnGroup");
	// 客户跟进区域显示的签单信息按钮组
	var $signCustomerFollowBtnGroup = $("#signCustomerFollowBtnGroup");
	
	// 当单击显示签单概要按钮时的处理方法
	function showSignCustomerSummaryBtnHandler($ele){
		$ele.addClass("blue").siblings().removeClass("blue");
		var signId = $ele.attr("data-sign-id");
		$signCustomerFollowBtnGroup.find("[data-sign-id="+signId+"]").click();
	}
	
	
	var signOfCustomerId = "${customer.id}";
	var activeSignCustomerId = "${sId}";
	$.getJSON("signCustomer/getSignCustomerSummaryInfo.do",{customerId : signOfCustomerId},function(datas){
		console.log("getSignCustomerSummaryInfo...");
		console.log(datas);
		if(!datas || datas.length == 0){
			return;
		}
		
		// 循环创建button
		for(var i = 0;i < datas.length; i++){
			var data = datas[i];
			var signId = data.id;
			var btnStr = '<button class="btn" data-sign-id="'+signId+'">'+data.contractNO+'('+data.name+') '+data.signTime+'</button>&nbsp;';
			$(btnStr).appendTo($showSignCustomerSummaryBtnGroup).data("sign-data",data);
			$(btnStr).appendTo($signCustomerFollowBtnGroup).data("sign-data",data);
		}
		
		// 签单列表显示区域按钮点击事件,当单击时,客户跟进的按钮联动
		$showSignCustomerSummaryBtnGroup.find("button").click(function(){
			showSignCustomerSummaryBtnHandler($(this));
			var signCustomerId = $(this).attr("data-sign-id");
			$('#updateSignCustomerListArea').load('signCustomer/getUpdateSignCustomerListNew.do?customerId=${customer.id}&signCustomerId='+signCustomerId+'&refererUrl=${refererUrl}');
		});
		
		
		
		// 客户跟进区域中签单按钮的单击事件
		$signCustomerFollowBtnGroup.find("button").click(function(){
			$(this).addClass("blue").siblings().removeClass("blue");
			var signData = $(this).data("sign-data");
			$("#followSignCustomerId").val(signData.id);
			$("#followSignCustomerContractNO").val(signData.contractNO);
			$("#followSignCustomerName").val(signData.name);
			// 权证信息
			$("#followSignWarrantCompanyId").val(signData.warrantCompanyId);
			$("#followSignWarrantDepartmentId").val(signData.warrantDepartmentId);
			$("#followSignWarrantEmployeeId").val(signData.warrantEmployeeId);
			
		});
		

		// 如果有指定的签单ID,则选择指定的按钮,否则,选中第一个
		var $activeBtnEle; // 选中的按钮元素
		if(activeSignCustomerId){
			$activeBtnEle = $showSignCustomerSummaryBtnGroup.find("[data-sign-id="+activeSignCustomerId+"]");
		}else{
			$activeBtnEle = $showSignCustomerSummaryBtnGroup.find("button:first");
		}
		// 激活 
		showSignCustomerSummaryBtnHandler($activeBtnEle);
		
	})
	
	
	
	//加载已签到列表
	$('#updateSignCustomerListArea').load('signCustomer/getUpdateSignCustomerList.do?customerId=${customer.id}&sId=${sId}&refererUrl=${refererUrl}&closeWindow=${closeWindow}');
});
</script>