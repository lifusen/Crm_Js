<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<link href="${pageContext.request.contextPath}/css/addCustomerForm.css"
	rel="stylesheet" type="text/css" />

<script type="text/javascript">
	//上传图片
	function uploadPic() {
		var options = {
			url : "	upload/uploadPic.do",
			dataType : "json",
			type : "post",
			success : function(data) {
				var url = data.situation;
				$("#allUrl").attr("src", url);
				$("#imgUrl").val(url);
			}
		}

		$("#addCustomerBasicInfoForm").ajaxSubmit(options);
	}
</script>
<div class="container-fluid">
	<!-- BEGIN PAGE HEADER-->
	<div class="row-fluid">
		<div class="span12">
			<!-- BEGIN STYLE CUSTOMIZER -->
			<!-- END BEGIN STYLE CUSTOMIZER -->
			<!-- BEGIN PAGE TITLE & BREADCRUMB-->
			<h3 class="page-title"></h3>
			<ul class="breadcrumb">
				<li><i class="icon-home"></i> <a href="javascript:void(0);">${param.addCustomerType=='salesman'?'行政部':'总经办'}</a>
					<i class="icon-angle-right"></i></li>
				<li><a href="javascript:void(0);">录入合同信息</a></li>
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
			action="${pageContext.request.contextPath}/contractManager/addContractManager.do"
			method="post">
			<div class="portlet box blue">
				<div class="portlet-title">
					<div class="caption">
						<i class="icon-cogs"></i>合同信息
					</div>
					<div class="tools">
						<a class="collapse" href="javascript:;"></a>
					</div>
				</div>
				<div class="portlet-body">
					<div id="customerBasicInfoArea">
						<table id="" class="formTable">
							<tr>

								<td width="50px"><label class="control-label">联系人</label></td>
								<td width="120px"><input id="custAuthorizedPer" type="text"
									name="custAuthorizedPer" class="small" style="width: 100px;"></td>
								<td><label class="control-label">联系方式<span
										class="required">*</span></label></td>
								<td><input id="customerPhone" type="text" name="phone"
									class="small" style="width: 100px;"> <a
									id="customerPhoneErrorTip" href="javascript:;" class="popovers"
									data-content="15828636400是杨静的客户电话!"
									data-original-title="该号码已经存在!"> </a></td>
								<td width="50px"><label class="control-label">项目名称</label></td>
								<td width="140px"><input id="itemName" type="text"
									name="itemName" class="small" style="width: 100px;"></td>
								<td width="50px" style="padding-left: 33px"><label
									class="control-label">项目编号</label></td>
								<td width="140px"><input id="itemNo" type="text"
									name="itemNo" class="small" style="width: 100px;"></td>
								<td style="display: none"><label class="control-label"
									style="display: none">年龄</label></td>
								<td style="display: none"><input type="text" name="age"
									class="small" maxlength="2" style="width: 40px;" /></td>

							</tr>
							<tr>
								<td style="width: 90px"><span
									style="background-color: #2894FF; font-size: 15px; color: white">签单客户</span></td>
							</tr>
							<tr>
							<tr>
								<td><label class="control-label">起始日期</label></td>
								<td>
									<div class="input-append date form_datetime">
										<input data-provide="datepicker" type="text" class=""
											readonly="readonly" value="" size="12" style="width: 100px;"
											name="startDate">
									</div>
								</td>
								<td><label class="control-label">结束日期</label></td>
								<td>
									<div class="input-append date form_datetime">
										<input data-provide="datepicker" type="text" class=""
											readonly="readonly" value="" size="12" style="width: 100px;"
											name="endDate">
									</div>
								</td>
								<td><label class="control-label">维护周期</label></td>
								<td><input id="cycle" type="text" name="cycle"
									class="small" style="width: 100px;"></td>
							</tr>
							<tr>
								<td><span
									style="background-color: #2894FF; font-size: 15px; color: white">项目环境配置</span></td>
							</tr>

							<tr>
								<td><label class="control-label">CPU</label></td>
								<td><input id="CPU" type="text" name="cpu" class="small"
									style="width: 100px;"></td>
								<td><label class="control-label">内存</label></td>
								<td><input id="memory" type="text" name="memory"
									class="small" style="width: 100px;"></td>
								<td><label class="control-label">硬盘</label></td>
								<td><input id="hardDisk" type="text" name="hardDisk"
									class="small" style="width: 100px;"></td>
								<td><label class="control-label">宽带</label></td>
								<td><input id="bandwidth" type="text" name="bandwidth"
									class="small" style="width: 100px;"></td>
							</tr>
							<tr>
								<td><label class="control-label">服务器系统及版本</label></td>
								<td><input id="server" type="text" name="server"
									class="small" style="width: 100px;"></td>
								<td><label class="control-label">开发工具</label></td>
								<td><input id="devetools" type="text" name="devetools"
									class="small" style="width: 100px;"></td>
								<td><label class="control-label">数据库</label></td>
								<td><input id="dataValue" type="text" name="database"
									class="small" style="width: 100px;"></td>
								<td><label class="control-label">代码管理器</label></td>
								<td><input id="codeMana" type="text" name="codeMana"
									class="small" style="width: 100px;"></td>
							</tr>
							<tr>
								<td><label class="control-label">环境变量</label></td>
								<td><input id="envirVarible" type="text"
									name="envirVarible" class="small" data-required="1"
									style="width: 100px;"></td>
								<td><label class="control-label">其他</label></td>
								<td><input id="other" type="text" name="other"
									class="small" style="width: 100px;"></td>
							</tr>
							<tr>
								<td width="60px"><span
									style="background-color: #2894FF; font-size: 15px; color: white">项目开发情况</span></td>
							</tr>


							<tr>
								<td><label class="control-label">计划开始时间</label></td>
								<td>
									<div class="input-append date form_datetime">
										<input data-provide="datepicker" type="text" class=""
											readonly="readonly" value="" size="12" style="width: 100px;"
											name="startDatePlan">
									</div>
								</td>
								<td><label class="control-label">计划完成时间</label></td>
								<td>
									<div class="input-append date form_datetime">
										<input data-provide="datepicker" type="text" class=""
											readonly="readonly" value="" size="12" style="width: 100px;"
											name="endDatePlan">
									</div>
								</td>
								<td><label class="control-label">产品经理</label></td>
								<td><input id="productMana" type="text" name="productMana"
									class="small" style="width: 100px;"></td>
								<td><label class="control-label">项目负责人</label></td>
								<td><input id="projectLeader" type="text"
									name="projectLeader" class="small" data-required="1"
									style="width: 100px;"></td>
							</tr>

							<tr>
								<td><label class="control-label">组长</label></td>
								<td><input id="groupLeader" type="text" name="groupLeader"
									class="small" style="width: 100px;"></td>
								<td><label class="control-label">组员</label></td>
								<td style="" colspan="14"><textarea name="groupPer" rows=""
										style="width: 70%;"></textarea></td>
							</tr>
							<tr>
								<td width="50px;"><label class="control-label">外部合作情况</label></td>
								<td style="" colspan="14"><textarea name="extCooperation"
										rows="" style="width: 70%;"></textarea></td>

							</tr>
							<tr>
								<td><label class="control-label">外部人员或团队</label></td>
								<td style="" colspan="14"><textarea name="extTeam" rows=""
										style="width: 70%;"></textarea></td>
							</tr>
							<tr>
								<td><label class="control-label">测试人员</label></td>
								<td style="" colspan="14"><textarea name="testPer" rows=""
										style="width: 70%;"></textarea></td>
							</tr>

							<tr>
								<td><label class="control-label">外援内容</label></td>
								<td style="" colspan="14"><textarea name="extContent"
										rows="" style="width: 70%;"></textarea></td>
							</tr>
							<tr>
								<td><label class="control-label">外援人员</label></td>
								<td style="" colspan="14"><textarea name="extPer" rows=""
										style="width: 70%;"></textarea></td>

							</tr>
							<tr>
								<td width="60px"><span
									style="background-color: #2894FF; font-size: 15px; color: white">项目验收维护</span></td>
							</tr>
							<tr>
								<td><label class="control-label">验收资料</label></td>
								<td><input id="accepData" type="text" name="accepData"
									class="small" style="width: 100px;"></td>
								<td><label class="control-label">是否交付</label></td>
								<td><select id="workYearSelect" name="accDelivery"
									class="small m-wrap">
										<option value=""></option>
										<option value="已交付">已交付</option>
										<option value="未交付">未交付</option>
								</select></td>
								<td><label class="control-label">源代码</label></td>
								<td><input id="sourceCode" type="text" name="sourceCode"
									class="small" style="width: 100px;"></td>
								<td><label class="control-label">是否交付</label></td>
								<td><select id="workYearSelect" name="souDelivery"
									class="small m-wrap">
										<option value=""></option>
										<option value="已交付">已交付</option>
										<option value="未交付">未交付</option>
								</select></td>
							</tr>
							<tr>
								<td><label class="control-label">服务器提供方</label></td>
								<td><input id="serverProvider" type="text"
									name="serverProvider" class="small" style="width: 100px;"></td>
								<td><label class="control-label">服务器供应商</label></td>
								<td><input id="serverVendor" type="text"
									name="serverVendor" class="small" style="width: 100px;"></td>
							</tr>
							<tr>
								<td><label class="control-label">服务器地址</label></td>
								<td style="" colspan="14"><textarea name="serverAddr"
										rows="" style="width: 70%;"></textarea></td>
							</tr>
							<tr>
								<td><label class="control-label">服务器账号</label></td>
								<td><input id="serverAccount" type="text"
									name="serverAccount" class="small" style="width: 100px;"></td>
								<td><label class="control-label">服务器密码</label></td>
								<td><input id="serverPassword" type="text"
									name="serverPassword" class="small" style="width: 100px;"></td>
								<td><label class="control-label">上线域名</label></td>
								<td><input id="domainName" type="text" name="domainName"
									class="small" style="width: 100px;"></td>
							</tr>
							<tr>
								<td><label class="control-label">后台管理地址</label></td>
								<td><input id="managementAddr" type="text"
									name="managementAddr" class="small" style="width: 100px;"></td>
								<td><label class="control-label">后台管理账号</label></td>
								<td><input id="managementAccount" type="text"
									name="managementAccount" class="small" style="width: 100px;"></td>
								<td><label class="control-label">后台管理密码</label></td>
								<td><input id="managementPassword" type="text"
									name="managementPassword" class="small" style="width: 100px;"></td>
							</tr>
							<tr>
								<td width="60px"><span
									style="background-color: #2894FF; font-size: 15px; color: white">项目备份情况</span></td>
							</tr>

							<tr>
								<td><label class="control-label">远程服务器地址</label></td>
								<td style="" colspan="14"><textarea name="remServerAddr"
										rows="" style="width: 70%;"></textarea></td>
							</tr>
							<tr>
								<td><label class="control-label">本地服务器部署地址</label></td>
								<td style="" colspan="14"><textarea name="localServerAddr"
										rows="" style="width: 70%;"></textarea></td>
							</tr>
							<tr>
								<td><label class="control-label">本地服务器备份位置</label></td>
								<td style="" colspan="14"><textarea
										name="localServerBackUp" rows="" style="width: 70%;"></textarea></td>
							</tr>
							<tr>
								<td><label class="control-label">移动硬盘备份位置</label></td>
								<td style="" colspan="14"><textarea name="hardDiskBackUp"
										rows="" style="width: 70%;"></textarea></td>

							</tr>
							<tr>
								<td width="60px"><span
									style="background-color: #2894FF; font-size: 15px; color: white">合同信息</span></td>
							</tr>

							<tr>
								<td><label class="control-label">合同编号</label></td>
								<td><input id="contractNO" type="text" name="contractNO"
									class="small" style="width: 100px;"></td>
								<td><label class="control-label">合同总金额</label></td>
								<td><input id="contractPrice" type="text"
									name="contractPrice" class="small" style="width: 100px;"></td>
								<td><label class="control-label">是否扫描</label></td>
								<td><select id="workYearSelect" name="photoScan"
									class="small m-wrap">
										<option value=""></option>
										<option value="已交付">已扫描</option>
										<option value="未交付">未扫描</option>
								</select></td>

							</tr>
							<tr>
								<td><label class="control-label">签订日期</label></td>
								<td>
									<div class="input-append date form_datetime">
										<input data-provide="datepicker" type="text" class=""
											readonly="readonly" value="" size="12" style="width: 100px;"
											name="signDate">
									</div>
								</td>

								<td><label class="control-label">完成日期</label></td>
								<td>
									<div class="input-append date form_datetime">
										<input data-provide="datepicker" type="text" class=""
											readonly="readonly" value="" size="12" style="width: 100px;"
											name="completDate">
									</div>
								</td>
								<td><label class="control-label">支付方式</label></td>
								<td><input id="paymentTerms" type="text"
									name="paymentTerms" class="small" style="width: 100px;"></td>
							</tr>
							<tr>
								<td><label class="control-label">支付金额</label></td>
								<td><input id="price" type="text" name="price"
									class="small" style="width: 100px;"></td>
								<td><label class="control-label">支付条件</label></td>
								<td><input id="paymentTerms" type="text"
									name="paymentTerms" class="small" style="width: 100px;"></td>
								<td><label class="control-label">已支付</label></td>
								<td><input id="havetopay" type="text" name="havetopay"
									class="small" style="width: 100px;"></td>
								<td><label class="control-label">支付日期</label></td>
								<td>
									<div class="input-append date form_datetime">
										<input data-provide="datepicker" type="text" class=""
											readonly="readonly" value="" size="12" style="width: 100px;"
											name="payDate">
									</div>
								</td>
							</tr>
							<tr>
								<td><label class="control-label">计划工期</label></td>
								<td><input id="planProject" type="text" name="planProject"
									class="small" style="width: 100px;"></td>
								<td><label class="control-label">计划成本</label></td>
								<td><input id="planCost" type="text" name="planCost"
									class="small" style="width: 100px;"></td>
								<td><label class="control-label">已投入工期</label></td>
								<td><input id="construction" type="text"
									name="construction" class="small" style="width: 100px;"></td>
								<td><label class="control-label">超出/结余</label></td>
								<td><input id="beyond" type="text" name="beyond"
									class="small" style="width: 100px;"></td>
							</tr>
							<tr>
								<td><label class="control-label">工资</label></td>
								<td><input id="wage" type="text" name="wage" class="small"
									style="width: 100px;"></td>
								<td><label class="control-label">商务费用</label></td>
								<td><input id="businessFee" type="text" name="businessFee"
									class="small" style="width: 100px;"></td>
								<td><label class="control-label">合作包费</label></td>
								<td><input id="cooperationFee" type="text"
									name="cooperationFee" class="small" style="width: 100px;"></td>
								<td><label class="control-label">税务费</label></td>
								<td><input id="taxFee" type="text" name="taxFee"
									class="small" style="width: 100px;"></td>
							</tr>
							<tr>
								<td><label class="control-label">差旅费</label></td>
								<td><input id="travelFee" type="text" name="travelFee"
									class="small" style="width: 100px;"></td>
								<td><label class="control-label">招待费</label></td>
								<td><input id="entertainment" type="text"
									name="entertainment" class="small" style="width: 100px;"></td>
							</tr>
						</table>
						<table style="width: 100%;">
							<tr>
								<td><input name="pic" id="file" type="file"
									onchange=uploadPic() /> <img id="allUrl" src="" /> <input
									type="hidden" name="other4" id="imgUrl">
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
						<i class="icon-cogs"></i>
					</div>
					<div class="tools">
						<a class="collapse" href="javascript:;"></a>
					</div>
				</div>
				<div class="portlet-body">
					<div id="customerAuxiliaryInfoArea">
						<!--  -->
						<table class="formTable" style="width: 100%;"
							style="display: none">

							<tr style="display: none">
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
							<input type="reset" class="reset" value="重置" /> <input
								type="hidden" id="addCustomerBasicInfoSuccessUrl" />
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