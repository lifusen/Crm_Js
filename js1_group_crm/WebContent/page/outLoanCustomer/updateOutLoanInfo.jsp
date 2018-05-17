<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %> 
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>   

<div id="outLoanCustomerContainer" style="display:${sc.outLoanCondition=='已放款'?'block':'none'};">
	<!-- 放款客户 start-->
	<div class="outLoanCustomerTemplate">
		<div class="portlet box green">
			<div class="portlet-title">
				<div class="caption"><i class="icon-cogs"></i>放款客户</div>
				<div class="tools">
					<a class="collapse" href="javascript:;"></a>
				</div>
			</div>
			<div class="portlet-body">
				<div id="outLoanCustomerArea">
					<%@include file="../outLoanCustomer/addOutLoanInfo.jsp" %>
					<div id="showOutLoanCustomerSummaryBtnGroup" class="signCustomerBtnGroup">
				
					</div>
					<div id="updateOutLoanCustomerArea" class="outLoanCustomerArea" style="display:${sc.outLoanCondition=='已放款'?'block':'none'};">
						放款信息加载中 ... ...
						<%-- <%@include file="/page/outLoanCustomer/outLoanInfoForm.jsp" %> --%>
					</div>
				</div>
			</div>
		</div>
	</div>
	<!-- 放款客户 end-->
	
	<!-- 引入放款客户模板 -->
	
	
	
</div>

<script type="text/javascript">

$(function(){
	
	
	
	// 获取当前签单所有放款的概要信息
	// 显示放款列表的按钮组
	var $showOutLoanCustomerSummaryBtnGroup = $('#showOutLoanCustomerSummaryBtnGroup');
	
	// 当单击显示放款概要按钮时的处理方法
	function showOutLoanCustomerSummaryBtnHandler($ele){
		var oId = $ele.attr("data-loan-id");
		//加载按钮对应的已放款信息
		var refererUrl = "${refererUrl}";
		$('#updateOutLoanCustomerArea').load('outLoanCustomer/getUpdateOutLoanCustomerList.do?sId=${sc.id}&oId='+oId+'&refererUrl='+refererUrl);
		
		//激活相对应的按钮
		$ele.addClass("blue").siblings().removeClass("blue");
	}
	
	//获取放款概要信息列表
	var loanOfSignCustomerId = "${sc.id}";
	var activeOutLoanCustomerId = "${oId}";//预留参数，当点击相应的放款之后，记录放款ID，直接显示该放款
	$.getJSON("outLoanCustomer/getOutLoanCustomerSummaryInfo.do", {signCustomerId : loanOfSignCustomerId}, function(datas){
		console.log("getOutLoanCustomerSummaryInfo...");
		console.log(datas);
		if(!datas || datas.length == 0){ //如果为空或者长度为0，则返回
			return;
		}
		
		// 循环创建button
		for(var i = 0;i < datas.length; i++){
			var data = datas[i];
			var loanId = data.id;
			var btnStr = '<button class="btn" data-loan-id="'+loanId+'">'+(data.outLoanNum==null||data.outLoanNum==""?"0000":data.outLoanNum)+' ['+data.warrantName+'] '+data.createTime+'</button>&nbsp;';
			$(btnStr).appendTo($showOutLoanCustomerSummaryBtnGroup).data("loan-data",data);
		}
		
		// 放款列表显示区域按钮点击事件
		$showOutLoanCustomerSummaryBtnGroup.find("button").click(function(){
			showOutLoanCustomerSummaryBtnHandler($(this));
			var outLoanCustomerId = $(this).attr("data-loan-id");
		});
		
		// 如果有指定的签单ID,则选择指定的按钮,否则,选中第一个
		var $activeBtnEle; // 选中的按钮元素
		if(activeOutLoanCustomerId){
			$activeBtnEle = $showOutLoanCustomerSummaryBtnGroup.find("[data-loan-id="+activeOutLoanCustomerId+"]");
		}else{
			$activeBtnEle = $showOutLoanCustomerSummaryBtnGroup.find("button:first");
		}
		// 激活选中的按钮
		showOutLoanCustomerSummaryBtnHandler($activeBtnEle);
		
	});
	
})

</script>
