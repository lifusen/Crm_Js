<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!-- 客户跟进 start -->
<div class="portlet box blue">
	<div class="portlet-title">
		<div class="caption">
			<i class="icon-cogs"></i>客户跟进
		</div>
		<div class="tools">
			<a class="collapse" href="javascript:;"></a>
		</div>
	</div>
	<div class="portlet-body" style="padding-bottom: 10px;">
		<div
			style="border: 1px solid #ccc; padding: 5px; margin-bottom: 10px;">
			<div id="signCustomerFollowBtnGroup" class="signCustomerBtnGroup">
			</div>
			<p></p>

			<table style="border: 0px solid #ccc; width: 100%;">
				<tr>
					<c:if test="${onlyShowFollowList ne true}">
						<td valign="top" style="width: 100px; border: 0px solid blue;">
							<table
								style="border: 1px solid #999; width: 90px; height: 235px; text-align: center;">
								<tr>
									<td><label class="control-label">客户等级</label></td>
								</tr>
								<tr>
									<%--
									<td style="background:#ccc; font-size:40px;height:45px; font-weight: bold;color:#0362FD;">
									 --%>
									<td style="height: 45px;">
										<div id="showCustomerLevel"
											style="height: 45px; line-height: 45px; font-size: 40px; font-weight: bold; color: #0362FD;">${customer.customerLevel==null?"无":customer.customerLevel }</div>
										<div id="choiceCustomerLevel" style="display: none;">
											<select id="customerLevelSelect" class="small"
												style="font-size: 20px; width: 50px; height: 30px; margin-bottom: 5px;">
												<option value="优">优</option>
												<option value="良">良</option>
												<option value="中">中</option>
												<option value="差">差</option>
											</select>
											<%--
											<button id="saveCustomerLevelBtn" class="btn blue mini" type="button">确定</button>
											 --%>
										</div>
									</td>
								</tr>
								<tr>
									<td><label class="control-label">关注等级</label></td>
								</tr>
								<tr>
									<td style="height: 45px;">
										<div id="showAttentionLevel"
											style="height: 45px; line-height: 45px; font-size: 40px; font-weight: bold; color: #0362FD;">${customer.attentionLevel==null?"无":customer.attentionLevel }</div>
										<div id="choiceAttentionLevel" style="display: none;">
											<select id="attentionLevelSelect" class="small"
												style="font-size: 20px; width: 50px; height: 30px; margin-bottom: 5px;">
												<option value="A">A</option>
												<option value="B">B</option>
												<option value="C">C</option>
												<option value="D">D</option>
											</select>
											<%--
											<button id="saveAttentionLevelBtn" class="btn blue mini" type="button">确定</button>
											 --%>
										</div>
									</td>
								</tr>
								<tr>
									<td><label class="control-label">资料完成比</label></td>
								</tr>
								<tr>
									<td>&nbsp;</td>
								</tr>
								<%--
								<tr>
									<td style="font-size:40px; height:45px; font-weight: bold;color:#0362FD;">90%</td>
								</tr>
								 --%>
							</table>
						</td>

						<td valign="top" style="border: 0px solid red;">
							<form id="addCustomerFollowForm"
								action="${pageContext.request.contextPath}/customerFollow/addCustomerFollow.do"
								method="post">

								<!-- 签单客户信息 -->
								<input id="followSignCustomerId" type="hidden"
									name="followSignCustomerId" /> <input
									id="followSignCustomerContractNO" type="hidden"
									name="followSignCustomerContractNO" /> <input
									id="followSignCustomerName" type="hidden"
									name="followSignCustomerName" />
								<!-- 权证信息 -->
								<input id="followSignWarrantCompanyId" type="hidden"
									name="followSignWarrantCompanyId" /> <input
									id="followSignWarrantDepartmentId" type="hidden"
									name="followSignWarrantDepartmentId" /> <input
									id="followSignWarrantEmployeeId" type="hidden"
									name="followSignWarrantEmployeeId" /> <input
									id="followCustomerId" type="hidden" name="followCustomerId"
									value="${customer.id }" />

								<!-- 当前客户的业务公司信息 -->
								<input id="followBizCompanyId" type="hidden"
									name="followBizCompanyId" value="${customer.companyId }" /> <input
									id="followBizDepartmentId" type="hidden"
									name="followBizDepartmentId" value="${customer.department.id }" />
								<input id="followBizEmployeeId" type="hidden"
									name="followBizEmployeeId" value="${customer.employee.id }" />
								<table
									style="border: 0px solid red; width: 100%; height: 215px;">
									<tr>
										<td width="55px"><label class="control-label"
											style="min-width: 55px;">提醒时间</label></td>
										<td width="150px"><input type="hidden" name="customer.id"
											value="${customer.id }" /> <%-- 
											<div class="input-append date form_datetime" data-date-format="yyyy-mm-dd HH:ii:ss">
												<input id="customerFollowRemindTime" name="remindTime" data-provide="datepicker" type="text" class="" readonly="readonly" value="" size="12" style="width:120px;">
											</div>
											 --%> <input id="customerFollowRemindTime" name="remindTime"
											size="12" style="width: 120px;" type="text" value="" readonly>

										</td>
										<td width="55px"><label class="control-label">提醒类型</label></td>
										<td width="150px"><select name="type" class="small"
											style="width: 120px;">
												<c:choose>
													<c:when
														test="${loginRoleId == 2000 or loginRoleId == 2001 or loginRoleId == 2002 or loginRoleId == 2003}">
														<option value="100">合同签订</option>
														<option value="101">合同审批</option>
														<option value="102">抵押办理</option>
														<option value="103">放款</option>
														<option value="104">其他</option>
													</c:when>
													<c:otherwise>
														<option value="1">电话联系</option>
														<option value="2">邀约上门</option>
														<option value="3">上门洽谈</option>
														<option value="4">成功签约</option>
														<option value="5">售后服务</option>
													</c:otherwise>
												</c:choose>
										</select></td>

										<td width="55px"><label class="control-label ishidden">是否自留</label></td>
										<td><select class="ishidden" name="visibility"
											style="width: 100px;">
												<option value="1" ${customer.visibility==1?'selected':''}>否</option>
												<option value="0" ${customer.visibility==0?'selected':''}>是</option>
										</select></td>
										<td style="width: 60px; padding-left: 10px;"></td>
									</tr>
									<tr>
										<td><label class="control-label">提醒内容</label></td>
										<td colspan="6" style="border: 0px solid red;"><textarea
												id="customerFollowRemindContent" name="remindContent"
												rows="3" cols="120" style="width: 98%"></textarea></td>
									</tr>
									<tr>
										<td><label class="control-label">反馈内容</label></td>
										<td colspan="6" style="border: 0px solid red;"><textarea
												id="customerFollowFeedbackContent" name="feedbackContent"
												rows="3" cols="120" style="width: 98%"></textarea></td>
									</tr>
									<tr>
										<td>&nbsp;</td>
										<td colspan="4" style="border: 0px solid red;">
											<button id="saveCustomerFollowBtn" class="btn blue"
												type="button" style="width: 120px;">
												<i class="icon-ok"></i> 保存
											</button>
										</td>

										<td colspan="2" style="border: 0px solid red;"><c:if
												test="${sessionScope.loginUser.role.id !=3 }">
												<!--  
												<a id="applyInvalidBtn" class="ishidden"
													href="javascript:void(0);" style="padding-right: 8px;">申请无效</a>
													-->
												<a id="releasedPublicPoolBtn" class="ishidden"
													href="javascript:void(0);">释放到公共池</a>
											</c:if></td>
									</tr>
								</table>
							</form>
						</td>

					</c:if>
				</tr>
			</table>
		</div>

		<div style="border: 0px solid blue;" id="customerFollowListArea">

		</div>

	</div>
</div>
<!-- 客户跟进 end -->
<input type="hidden" value="${requestScope.refererUrl}" id="refererUrl" />
<script type="text/javascript">
	$(function() {

		//alert('${customer.customerLevel}');
		//alert('${customer.attentionLevel}');
		$('#customerLevelSelect').val('${customer.customerLevel}');
		$('#attentionLevelSelect').val('${customer.attentionLevel}');

		$('#customerFollowListArea').load(
				'customerFollow/getList.do?customerId=${customer.id}');

		//申请无效
		$('#applyInvalidBtn').click(function() {
			if (confirm('您确定要申请无效吗?')) {
				var cId = ${customer.id};
				$.post("customer/applyNullity.do", {
					cId : cId
				}, function(data) {
					if (data.flag) {
						alert("申请无效成功！");
						var url = '${refererUrl}';
						if (url) {
							aPost(url);
						} else {
							$("#hideDetail").click();
						}
					} else {
						alert(data.msg);
					}
				});
			}
		});
		//是否到公共池
		$('#releasedPublicPoolBtn').click(function() {
			if (confirm('您确定要释放到公共池吗?')) {
				var cId = ${customer.id};
				//var hId = ${hId};
				$.post("customer/releasePublic.do", {
					cId : cId
				}, function(data) {
					if (data.flag) {
						alert("释放成功！");
						var url = '${refererUrl}';
						if (url) {
							aPost(url);
						} else {
							$("#hideDetail").click();
						}
					} else {
						alert(data.msg);
					}
				});
			}
		});

		//双击客户等级
		$('#showCustomerLevel').dblclick(function() {
			$(this).hide();
			$('#choiceCustomerLevel').show();
		});

		//已过时
		//保存客户等级
		/**
		$('#saveCustomerLevelBtn').click(function(){
			var v = $('#customerLevelSelect').val();
			$.post('customer/updateCustomerLevel.do',{id:'${customer.id}',customerLevel:v},function(result){
				if(result==true){
					alert('保存成功!');
					$('#choiceCustomerLevel').hide();
					$('#showCustomerLevel').text(v).show();
				}	
			});
		});
		 **/

		$('#customerLevelSelect').change(function() {
			var v = $(this).val();
			$.post('customer/updateCustomerLevel.do', {
				id : '${customer.id}',
				customerLevel : v
			}, function(result) {
				if (result == true) {
					$('#choiceCustomerLevel').hide();
					$('#showCustomerLevel').text(v).show();
				} else {
					alert('保存失败!');
				}
			});
		});

		//双击关注等级
		$('#showAttentionLevel').dblclick(function() {
			$(this).hide();
			$('#choiceAttentionLevel').show();
		});

		//保存关注等级
		/**
		$('#saveAttentionLevelBtn').click(function(){
			var v = $('#attentionLevelSelect').val();
			$.post('customer/updateAttentionLevel.do',{id:'${customer.id}',attentionLevel:v},function(result){
				if(result==true){
					alert('保存成功!');
					$('#choiceAttentionLevel').hide();
					$('#showAttentionLevel').text(v).show();
				}	
			});
		});
		 **/

		$('#attentionLevelSelect').change(function() {
			var v = $(this).val();
			$.post('customer/updateAttentionLevel.do', {
				id : '${customer.id}',
				attentionLevel : v
			}, function(result) {
				if (result == true) {
					//alert('保存成功!');
					$('#choiceAttentionLevel').hide();
					$('#showAttentionLevel').text(v).show();
				} else {
					alert("保存失败!");
				}
			});
		});

		//保存提醒反馈消息
		$("#saveCustomerFollowBtn").click(
				function() {
					//错误提示消息
					var errorTipMsg = "";
					//提醒时间
					var remindTime = $.trim($('#customerFollowRemindTime')
							.val());
					//提醒内容
					var remindContent = $
							.trim($('#customerFollowRemindContent').val());
					//反馈内容
					var feedbackContent = $.trim($(
							'#customerFollowFeedbackContent').val());

					/**
					if(remindTime==''){
						errorTipMsg+='请选择提醒时间!<br/>';
					}
					 **/

					//表单验证
					if (remindTime != '') {
						//获取选择的时间与当前时间的间隔天数
						var diffDay = DateUtil.getDiffDay(remindTime);
						//提醒时间
						if (diffDay > 30) {
							errorTipMsg += "提醒时间不能大于当前30天!<br/>";
						} else if (diffDay < -1) {
							errorTipMsg += "提醒时间不能小于当前时间!<br/>";
						}
					}

					//内容
					if (remindContent == '' && feedbackContent == '') {
						errorTipMsg += '提醒内容和反馈内容必须填写一项!<br/>';
					}

					//验证
					errorTipMsg += ValidateCustomer.saveCustomerFollow();

					if (errorTipMsg != '') {
						$('#errorTipModalBody').html(errorTipMsg);
						$('#errorTipModal').modal('show');
						return;
					}

					$.post($('#addCustomerFollowForm').attr('action'), $(
							'#addCustomerFollowForm').serialize(), function(
							data) {
						if (data.flag == true) {
							//alert('保存成功!');
							$('#addCustomerFollowForm')[0].reset();
							//$('#customerFollowListArea').load('customerFollow/getList.do?customerId=${customer.id}');
							var url = '${refererUrl}';
							//alert(url);
							if (url) {
								aPost(url);
							} else {
								$("#hideDetail").click();
							}
						} else {
							alert(data.msg);
						}
					});
				});

		//隐藏部分禁止权证操作的功能
		var loginRoleId = "${loginRoleId}";
		if (loginRoleId == 2000 || loginRoleId == 2001 || loginRoleId == 2002
				|| loginRoleId == 2003) {
			$("td .ishidden").addClass("hidden");
		}
	})
</script>

<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/customer/dateFormHandler.js"></script>
