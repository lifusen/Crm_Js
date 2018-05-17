<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<link href="${pageContext.request.contextPath}/css/addCustomerForm.css"
	rel="stylesheet" type="text/css" />
<div class="container-fluid">
	<!-- BEGIN PAGE HEADER-->
	<div class="row-fluid">
		<div class="span12">
			<!-- BEGIN STYLE CUSTOMIZER -->
			<!-- END BEGIN STYLE CUSTOMIZER -->
			<!-- BEGIN PAGE TITLE & BREADCRUMB-->
			<h3 class="page-title"></h3>
			<ul class="breadcrumb">
				<li><i class="icon-home"></i> <a href="javascript:void(0);">${param.addCustomerType=='salesman'?'业务部':'市场部'}</a>
					<i class="icon-angle-right"></i></li>
				<li><a href="javascript:void(0);">录入客户信息</a></li>
				<li class="pull-right no-text-shadow">
					<div id="dashboard-report-range"
						class="dashboard-date-range tooltips no-tooltip-on-touch-device responsive"
						data-tablet="" data-desktop="tooltips" data-placement="top"
						data-original-title="Change dashboard date range">
						<i class="icon-calendar"></i> <span></span> <i
							class="icon-angle-down"></i>
					</div>
				</li>
			</ul>
			<!-- END PAGE TITLE & BREADCRUMB-->
		</div>
	</div>
	<!-- END PAGE HEADER-->

	<div class="customerFormContainer">
		<!-- 客户基本信息 start -->
		<form id="addCustomerBasicInfoForm"
			action="${pageContext.request.contextPath}/customer/${param.addCustomerType=='salesman'?'addCustomerOfSalesman':'addCustomer'}.do"
			method="post">
			<div class="portlet box blue">
				<div class="portlet-title">
					<div class="caption">
						<i class="icon-cogs"></i>基础信息
					</div>
					<div class="tools">
						<a class="collapse" href="javascript:;"></a>
					</div>
				</div>
				<div class="portlet-body">
					<div id="customerBasicInfoArea">
						<table id="" class="formTable">
							<tr>
								<td><label class="control-label">联系人：</label></td>
								<td><input id="customerName" type="text" name="name"
									class="small" data-required="1" style="width: 100px;">
								</td>
								<td><label class="control-label">性别：</label></td>
								<td
									style="padding-top: 0px; padding-bottom: 0px; line-height: 0px;">
									<div class="controls" style="padding-bottom: 8px;">
										<label class="radio"><input type="radio" name="gender"
											value="男" checked />男 </label> <label class="radio"><input
											type="radio" name="gender" value="女" />女 </label>
									</div>
								</td>
								<td><label class="control-label">联系方式：<span
										class="required">*</span></label></td>
								<td><input id="customerPhone" type="text" name="phone"
									class="small" style="width: 100px;"> <a
									id="customerPhoneErrorTip" href="javascript:;" class="popovers"
									data-content="15828636400是杨静的客户电话!"
									data-original-title="该号码已经存在!"> </a></td>
								<td><label class="control-label">备注：</label></td>
								<td><input type="text" name="phoneRemark" class="small"
									style="width: 100px;">
									<button onclick="addPhoneTemplate(this)"
										class="btn blue addPhone" type="button"
										style="padding: 4px 2px;">
										<i class="icon-plus"></i> 更多
									</button></td>
								<td><label class="control-label">来源：<span
										class="required">*</span></label></td>
								<td><select name="customerSource.id" class="small m-wrap">
										<option value=""></option>
										<c:if test="${fn:length(customerSources)>0 }">
											<c:forEach var="cs" items="${customerSources}">
												<option value="${cs.id}">${cs.sourceName}</option>
											</c:forEach>
										</c:if>
								</select></td>
								<td><label class="control-label">年龄：</label></td>
								<td><input type="text" name="age" class="small"
									maxlength="2" style="width: 40px;" /></td>
							</tr>
						</table>
						<table style="width: 30%;">
							<tr>
								<td><label class="control-label">公司名称：</label></td>
								<td><input id="lendingInstitution" type="text"
									name="customerAsset" class="small" data-required="1"
									style="width: 100px;"></td>
								<td><label class="control-label">意向产品：</label></td>
								<td><input id="loanType" type="text" name="loanType"
									class="small" data-required="1" style="width: 100px;">
								</td>
							</tr>
						</table>

						<table style="width: 100%;">
							<tr>
								<td width="50px;"><label class="control-label">备注信息</label></td>
								<td style="" colspan="14"><textarea name="otherInfo"
										rows="" style="width: 100%;">${customer.otherInfo}</textarea>
								</td>
								<td>&nbsp;</td>
							</tr>
						</table>

					</div>
				</div>
			</div>
			<!-- 客户基本信息 end -->


			<!-- 客户资产 start-->
			<div class="portlet box green" style="display: none">
				<div class="portlet-title">
					<div class="caption">
						<i class="icon-cogs"></i>客户资产
					</div>
					<div class="tools">
						<a class="collapse" href="javascript:;"></a>
					</div>
				</div>
				<div class="portlet-body">
					<div id="customerAssetArea"></div>
					<div style="text-align: center;">
						<a onclick="addHouseTemplate(this)" class="btn gray"
							href="javascript:;"><i class="icon-plus"></i> 添加住房</a> <a
							onclick="addStoreTemplate(this)" class="btn gray"
							href="javascript:;"><i class="icon-plus"></i> 添加商铺</a> <a
							onclick="addOfficeTemplate(this)" class="btn gray"
							href="javascript:;"><i class="icon-plus"></i> 添加写字楼</a> <a
							onclick="addLandTemplate(this)" class="btn gray"
							href="javascript:;"><i class="icon-plus"></i> 添加土地</a> <a
							onclick="addCarTemplate(this)" class="btn gray"
							href="javascript:;"><i class="icon-plus"></i> 添加汽车</a> <a
							onclick="addEnterpriseTemplate(this)" class="btn gray"
							href="javascript:;"><i class="icon-plus"></i> 添加企业</a> <a
							onclick="addCreditCardTemplate(this)" class="btn gray"
							href="javascript:;"><i class="icon-plus"></i> 添加信用卡</a>
					</div>
				</div>
			</div>
			<!-- 客户资产 end-->


			<!-- 辅助信息 start -->
			<div class="portlet box blue">
				<div class="portlet-title">
					<div class="caption">
						<i class="icon-cogs"></i>辅助信息
					</div>
					<div class="tools">
						<a class="collapse" href="javascript:;"></a>
					</div>
				</div>
				<div class="portlet-body">
					<div id="customerAuxiliaryInfoArea">
						<table class="formTable" style="width: 100%;">

							<tr>
								<td><label class="control-label">公司规模</label></td>
								<td><select id="workYearSelect" name="workYear"
									class="small m-wrap">
										<option value=""></option>
										<option value="20人以内">20人以内</option>
										<option value="20-50人">20-50人</option>
										<option value="50人以上">50人以上</option>
										<option value="50人以上">50-100人</option>
										<option value="50人以上">100人以上</option>
								</select></td>
								<td><label class="control-label">主营业务</label></td>
								<td><input id="requiredMoneyInput" name="requiredMoney"
									type="text" class="small" maxlength="11" style="width: 100px;" />
								</td>
								<td><label class="control-label">行业类别</label></td>
								<td><input name="repaymentLimit" type="text" class="small"
									style="width: 100px;" /></td>
								<td><label class="control-label">公司地址</label></td>
								<td>
									<%--
									<div class="input-append date form_datetime"  data-date-format="yyyy年mm月dd日">
										<input name="useDate" data-provide="datepicker" type="text" class="" readonly="readonly" value="" size="12" style="width:100px;">
									</div>
									  --%> <input name="repaymentType" type="text" class="small"
									style="width: 100px;" />
								</td>
								<td><label class="control-label">客户性格</label></td>
								<td colspan="3"><input name="customerCharacter" type="text"
									class="small" style="width: 93%;" /></td>

							</tr>
							<tr style="display: none">
								<td><label class="control-label">债权到期时间</label></td>
								<td>
									<%--
									<div class="input-append date form_datetime">
										<input name="bondExpireDate" data-provide="datepicker" type="text" class="" readonly="readonly" value="" size="12" style="width:100px;">
									</div>
									 --%> <input id="bondExpireDate" name="bondExpireDate"
									size="12" style="width: 100px;" type="text" value="" readonly>
								</td>
								<td><label class="control-label">还款方式</label></td>
								<td><select name="repaymentType" class="small m-wrap">
										<option value=""></option>
										<option value="先息后本">先息后本</option>
										<option value="等额本息">等额本息</option>
										<option value="等额本金">等额本金</option>
										<option value="不规则还款">不规则还款</option>
										<option value="其他">其他</option>
								</select></td>
								<td><label class="control-label">用途</label></td>
								<td colspan="3"><input name="fundUse" type="text"
									class="small" style="width: 93%;" /></td>

							</tr>
							<tr style="display: none">
								<td><label class="control-label">债权构造</label></td>
								<td><select name="amountliabilitys[0].creditor"
									data-placeholder=" "
									class="small m-wrap lendingInstitutionSelect"
									style="width: 114px;">
										<option value="">&nbsp;</option>
										<option value="">&nbsp;</option>
										<option value="2">私人</option>
										<%@ include file="../common/lendingInstitution.jsp"%>
								</select></td>
								<td><label class="control-label">负债金额</label></td>
								<td><input type="text" name="amountliabilitys[0].debtMoney"
									class="small" maxlength="11" style="width: 100px;" /></td>
								<td>
									<button onclick="addAmountliabilityTemplate(this)"
										type="button" class="btn blue" style="padding: 4px 2px;">更多</button>
								</td>
								<td colspan="2"><span style="color: #555;">预估负债总金额</span> <input
									id="debtTotal" name="debtTotal" type="text" class="small"
									style="width: 100px;" value="" readonly="readonly"></td>
								<td colspan="6"><span style="color: #555;">推荐人</span> <input
									name="" type="text" class="small" style="width: 100px;"
									readonly="readonly" /></td>

							</tr>
						</table>

						<div class="form-actions"
							style="padding: 8px; text-align: center; margin-top: 5px; margin-bottom: 10px;">
							<button id="saveCustomerBacicInfoBtn" class="btn blue"
								type="button">
								<i class="icon-ok"></i> 保存
							</button>
							<input type="hidden" id="addCustomerBasicInfoSuccessUrl"
								value="${param.addCustomerType=='salesman'?'customer/getValidCustomerList.do':'customer/getAllotCustomerList.do'}" />
							<%--
							<a id="startAllotBtn" class="btn purple aPost" href="javascript:;"><i class="icon-ok"></i> 开始分配</a>
							 --%>
						</div>
					</div>
				</div>
			</div>
		</form>
		<!-- 辅助信息 end-->
	</div>
</div>

<script type="text/javascript">
	$(function() {

		//客户的手机号码
		$('#customerPhone').blur(function() {
			var v = $.trim($(this).val());
			//console.log(v);
			var errorTipMsg = validateCustomerPhone(v);
			if (errorTipMsg != '') {
				$('#errorTipModalBody').html(errorTipMsg);
				$('#errorTipModal').modal('show');
			}
		});
		console.log('---------------->phone contacts.............')

		//添加住房
		$('#addHouseBtn').click();

		//保存按钮
		$('#saveCustomerBacicInfoBtn').click(function() {
			var errorTipMsg = "";
			var phoneValue = $.trim($('#customerPhone').val());
			//表单验证
			//验证手机号码
			errorTipMsg += validateCustomerPhone(phoneValue);

			//意向产品、公司名称非空 
			if ($("#lendingInstitution").val() == '') {
				errorTipMsg += '请输入公司名称<br/>';
			}
			if ($("#loanType").val() == '') {
				errorTipMsg += '请输入意向产品<br/>';
			}
			//验证客户来源
			if ($("select[name='customerSource.id']").val() == '') {
				errorTipMsg += '请选择客户来源!<br/>';
			}

			if (errorTipMsg == '') {
				//验证当前页面是否有重复号码
				errorTipMsg += validateRepeatAndContactsPhone(phoneValue);
			}

			//添加客户的类型,如果是业务员添加,则验证必填项
			var addCustomerType = "${param.addCustomerType}";
			//alert(addCustomerType);
			if (addCustomerType == "salesman") {
				//保存客户时的基本验证
				errorTipMsg += ValidateCustomer.basicSave();
			}

			//获取到表单对象
			var form = $('#addCustomerBasicInfoForm');

			if (errorTipMsg == '') {
				//验证表单
				errorTipMsg += validateCustomerBasicInfo(form);
			}

			//判断并给出提示
			if (errorTipMsg != '') {
				$('#errorTipModalBody').html(errorTipMsg);
				$('#errorTipModal').modal('show');
				return;
			}

			//禁用保存按钮
			$(this).attr('disable', true);
			//序列化表单
			var v = serializeCustomerBasicInfoForm(form);

			$.post(form.attr('action'), v, function(data) {
				if (data == "true") {
					alert('添加成功!');
					aPost($('#addCustomerBasicInfoSuccessUrl').val());
				} else if (data == "false") {
					alert('添加失败!');
					$(this).attr('disable', false);
				} else {
					alert("添加失败：你的客户数量已达到上限！不能继续添加。")
				}
			});
		});

		$('select.lendingInstitutionSelect')
				.chosen(
						{
							allow_single_deselect : $(this).attr(
									"data-with-diselect") === "1" ? true
									: false
						});
	});
</script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/customer/dateFormHandler.js"></script>
