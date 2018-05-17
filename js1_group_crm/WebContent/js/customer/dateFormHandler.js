$(function(){
	
	/**
	 * 日期控件
	 */
	$("input.formDatepicker").datetimepicker({
		format: 'yyyy-mm-dd',
		autoclose : true,
		todayHighlight : true,
		minView : 'month',
		pickerPosition : 'bottom-left'
	});
	
	/**
	 * 客户辅助信息的用款时间
	 */
	$("#useDate").datetimepicker({
		format: 'yyyy-mm-dd',
		startDate : new Date(),
		autoclose : true,
		todayHighlight : true,
		minView : 'month',
		pickerPosition : 'bottom-left'
	});
	
	/**
	 * 客户辅助信息的债权到期时间
	 */
	$("#bondExpireDate").datetimepicker({
		format: 'yyyy-mm-dd',
		autoclose : true,
		todayHighlight : true,
		minView : 'month',
		pickerPosition : 'bottom-left'
	});

	/**
	 * 签单客户的预期时间
	 */
	$("input.planNextLoanTime_form_datetime").datetimepicker({
		format: 'yyyy-mm-dd',
		startDate : new Date(),
		autoclose : true,
		todayHighlight : true,
		minView : 'month',
		pickerPosition : 'bottom-left'
	});
	
	/**
	 * 放款客户的还款时间
	 */
	$("input.paymenttime_form_datetime").datetimepicker({
		format: 'yyyy-mm-dd',
		startDate : new Date(),
		autoclose : true,
		todayHighlight : true,
		minView : 'month',
		pickerPosition : 'bottom-left'
	});
	
	/**
	 * 客户跟进的提醒日期
	 */
	$("#customerFollowRemindTime").datetimepicker({
    	format: 'yyyy-mm-dd',
    	startDate : new Date(),
    	endDate : DateUtil.getEndDate(30),
    	autoclose : true,
    	todayHighlight : true,
    	minView : 'month',
		pickerPosition : 'bottom-left'
    });
	
	/**
	 * 签单客户列表的签单时间筛选
	 */
	$("input.signBeginDate").datetimepicker({
		format: 'yyyy-mm-dd',
		autoclose : true,
		todayHighlight : true,
		minView : 'month',
		pickerPosition : 'bottom-left'
	});
	
});