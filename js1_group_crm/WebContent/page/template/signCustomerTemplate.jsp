<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<div id="signCustomerTemplateContainer" style="display: none;">
	<div class="signCustomerTemplate add" style="padding-bottom: 10px;">
		<form class="addSignCustomerForm" action="">
			<table class="formTable signCustomerTable">
				<tr>
					<td class="signCustomerTitleTd" rowspan="50"
						style="width: 10px; padding: 6px; background: #ddd; color: #fff;">&nbsp;<span
						class="number"></span></td>
					<td rowspan="50" style="width: 10px;"></td>
					<td width="50px"><label class="control-label"
						style="width: 60px;">客户姓名<span class="required">*</span></label></td>
					<td width="140px"><input type="hidden" name="customer.id"
						value="${customer.id }"> <input id="signContactsName"
						type="text" name="signContacts[0].name" class="input" /></td>
					<td width="50px"><label class="control-label"
						style="width: 60px;">身份证号<span class="required">*</span></label></td>
					<td width="140px"><input type="text"
						name="signContacts[0].idCardNo" class="input width120"
						maxlength="18" /></td>
					<td width="50px"><label class="control-label">备注</label></td>
					<td width="140px"><input type="text"
						name="signContacts[0].remark" class="input" /></td>
					<td width="50px"><label class="control-label">年龄</label></td>
					<td width="140px"><input type="text"
						name="signContacts[0].age" class="input" maxlength="2" /></td>
					<td>
						<button type="button" class="btn blue" style="padding: 2px;"
							onclick="addSignContactsTemplate(this)">更多</button>
					</td>

				</tr>
				<tr class="signContactsAreaAfter" style="display: none">
					<td><label class="control-label">客户状态</label></td>
					<td><select name="" class="small m-wrap customerStatusSelect"
						disabled="disabled">
							<option value="0" selected="selected">进行中</option>
							<option value="1">银行退单</option>
							<option value="2">权证退件</option>
					</select></td>
					<td><label class="control-label">放款情况</label></td>
					<td><input type="text" name="outLoanCondition"
						class="input width120" readonly="readonly" /></td>
					<td><label class="control-label">是否再贷</label></td>
					<td><select name="hasLoanWish" class="small m-wrap">
							<option value=""></option>
							<option value="是">是</option>
							<option value="否">否</option>
					</select></td>
					<td><label class="control-label">预期时间</label></td>
					<td>
						<%--
						<div class="input-append date form_datetime">
							<input name="planNextLoanTime" data-provide="datepicker" type="text" class="" readonly="readonly" value="" size="12" style="width:110px;">
						</div>
						 --%> <input class="planNextLoanTime_form_datetime"
						name="planNextLoanTime" size="12" style="width: 110px;"
						type="text" value="" readonly />

					</td>
				</tr>
				<tr>
					<td><label class="control-label">合同费用</label></td>
					<td><input type="text" name="costOfContract" class="input"
						maxlength="11" /></td>
					<!--  
					<td><label class="control-label" style="width: 60px;">贷款金额<span
							class="required">*</span></label></td>
					<td><input type="text" name="loanAmount" class="input"
						maxlength="11" /></td>
						-->
					<td><label class="control-label" style="width: 60px;">已收定金<span
							class="required">*</span></label></td>
					<td><input type="text" name="handsel" class="input width120"
						maxlength="11" /></td>
					<td><label class="control-label">销售员<span
							class="required">*</span></label></td>
					<td><jsp:include page="allNegotiators.jsp" /></td>
					<td><label class="control-label" style="width: 60px;">已收证件<span
							class="required">*</span></label></td>
					<td colspan="2"><input type="text" name="receivedCertificate"
						class="input" style="width: 150px;" /></td>
				</tr>
				<tr style="display: none">
					<td><label class="control-label" style="width: 60px;">贷款类型<span
							class="required">*</span></label></td>
					<td><select name="loanProducts[0].loanType"
						class="small m-wrap" style="width: 126px;">
							<option value=""></option>
							<option value="信贷">信贷</option>
							<option value="信用卡">信用卡</option>
							<option value="房贷">房贷</option>
							<option value="二抵">二抵</option>
							<option value="车贷">车贷</option>
							<option value="短借">短借</option>
							<option value="企贷">企贷</option>
					</select></td>
					<td><label class="control-label" style="width: 60px;">贷款机构<span
							class="required">*</span></label></td>
					<td class=" lendingInstitutionTd"><select
						name="loanProducts[0].lendingInstitution" data-placeholder=" "
						class="small m-wrap">
							<option></option>
							<%@ include file="../common/lendingInstitution.jsp"%>
					</select></td>
					<td><label class="control-label" style="width: 60px;">银行产品<span
							class="required">*</span></label></td>
					<td><input type="text" name="loanProducts[0].bankProduct"
						class="input" /></td>
					<td><label class="control-label">月利率<span
							class="required">*</span></label></td>
					<td><input type="text" name="loanProducts[0].apr"
						class="input" /> %</td>
					<td width="50"><label class="control-label"
						style="width: 60px;">贷款年限<span class="required">*</span></label></td>
					<td width="70"><select name="loanProducts[0].loanYear"
						class="" style="width: 125px;">
							<option value=""></option>
							<c:forEach begin="1" end="30" var="i">
								<option value="${i}年">${i}年</option>
							</c:forEach>
					</select></td>
					<%--
					<td style="width:50px;">
						<button type="button" class="btn blue" style="padding:2px;" onclick="addLoanProduct(this)">更多</button>
					</td>
					 --%>
				</tr>
				<tr class="customerLoanProductAreaAfter" style="display: none">
					<td><label class="control-label">是否垫资</label></td>
					<td><select name="hasLoaning" class="small m-wrap"
						onchange="hasAddLoaning(this)">
							<option value=""></option>
							<option value="是">是</option>
							<option value="否">否</option>
					</select></td>
					<td><label class="control-label">还款方式</label></td>
					<td><select name="repaymentType" class="small m-wrap">
							<option value=""></option>
							<option value="先息后本">先息后本</option>
							<option value="等额本息">等额本息</option>
							<option value="等额本金">等额本金</option>
							<option value="不规则还款">不规则还款</option>
							<option value="其他">其他</option>
					</select></td>
					<td colspan="7">
						<button type="button" class="btn blue"
							onclick="importCertificate(this,${customer.id})">
							<i class="icon-plus"></i> 导入影像文件
						</button>
					</td>
				</tr>

				<c:if
					test="${not empty loginUser.companyId and loginUser.companyId == 3}">
					<tr>
						<td><label class="control-label">中介名称</label></td>
						<td><input type="text" class="input" name="intermediaryName" />
						</td>
						<td><label class="control-label">中介电话</label></td>
						<td class="intermediaryPhone"><input type="text"
							class="input" name="intermediaryPhone" /></td>
						<td><label class="control-label">中介返点</label></td>
						<td class="intermediaryRebate"><input type="text"
							class="input" name="intermediaryRebate" /></td>
					</tr>
				</c:if>

				<tr>
					<!-- 
					<td><label class="control-label">抵押物</label></td>
					<td style="width: 60px;" class="signCollaterals"><c:forEach
							var="cvb" items="${customerAssetViewBeans}">
							<input type="text" name="collaterals" value="${cvb}">${cvb.name}${cvb.id}
						</c:forEach></td>
					 -->
					<td><label class="control-label">其他备注</label></td>
					<td colspan="8"><textarea name="remark" rows="2" cols=""
							style="width: 796px;"></textarea></td>
				</tr>
				<tr>
					<td><label class="control-label">合同上传</label></td>
					<td><input name="pic" id="file" type="file"
						onchange=uploadPic() /> <img id="allUrl" src="" /> <input
						type="hidden" name="situation" id="imgUrl">
				</tr>
				<!-- 权证信息 -->
				<tr class="addSignOfWarrantInfo" style="display: none">
					<td><label class="control-label">权证中心</label></td>
					<td><input type="hidden" name="isUpdateWarrant" value="true" />
						<select name="warrantCompanyId"
						class="small m-wrap warrantCompanyId">
					</select></td>
					<!-- 取消业务员分配签单到权证部门和专员权限，隐藏此部分功能 -->
					<td><label class="control-label hidden">权证部门</label></td>
					<td><select name="warrantDepartmentId"
						class="small m-wrap warrantDepartmentId hidden">
					</select></td>
					<td><label class="control-label hidden">权证专员</label></td>
					<td><select name="warrantEmployeeId"
						class="small m-wrap warrantEmployeeId hidden">
					</select></td>
					<td colspan="4"><p
							style="margin: 0px; padding: 0px; height: 42px; line-height: 33px; color: #0362fd; font-size: 14px;"></p></td>
				</tr>


			</table>
			<div class="form-actions"
				style="padding: 8px; text-align: center; margin-top: 5px; margin-bottom: 10px;">
				<button class="btn blue" type="button"
					onclick="saveSignCustomer(this)">
					<i class="icon-ok"></i> 保存
				</button>
				<button class="btn" type="button" onclick="cancelAddSign(this)">
					<i class="icon-remove"></i> 取消
				</button>
			</div>
		</form>

	</div>


	<!-- 签单客户联系人模板 start -->
	<div class="signContactsTemplate">
		<table>
			<tr class="signContactsTemplateTr">
				<td width="50px"><label class="control-label">客户姓名<input
						type="hidden" class="signContactsId" value="" /></label></td>
				<td width="140px" class="name"><input type="text" class="input">
				</td>
				<td width="50px"><label class="control-label">身份证号</label></td>
				<td width="140px" class="idCardNo"><input type="text"
					class="input width120" maxlength="18" /></td>
				<td width="50px"><label class="control-label">备注</label></td>
				<td width="140px" class="remark"><input type="text"
					class="input"></td>
				<td width="50px"><label class="control-label">年龄</label></td>
				<td width="140px" class="age"><input type="text" class="input"
					maxlength="2"></td>
				<td class="deleteSignContactsTemplate">
					<button class="btn mini yellow" type="button"
						style="margin-bottom: 8px; padding: 2px 3px;"
						onclick="deleteSignContactsTemplate(this)">删除</button>
				</td>
			</tr>
		</table>
	</div>

	<!-- 退件模板 start -->
	<div class="rejectTemplate">
		<table>
			<tr>
				<td
					style="width: 15px; padding: 4px; background: #FFB848; color: #fff;">退件</td>
				<td style="width: 5px;"></td>
				<td><label class="control-label" style="width: 55px;">退件时间</label></td>
				<td>
					<div class="input-append date form_datetime">
						<input data-provide="datepicker" type="text" class=""
							readonly="readonly" value="" size="12" style="width: 73px;"
							name="failureMessage.time">
					</div>
				</td>
				<td><label class="control-label" style="width: 55px;">退件原因</label></td>
				<td>
					<%--
					<input type="text" class="input" name="failureMessage.cause" style="width:380px;"/>
					 --%> <select name="failureMessage.cause" style="width: 140px;">
						<option value=""></option>
						<option value="不接受利息">不接受利息</option>
						<option value="客户不做了">客户不做了</option>
						<option value="无渠道/不符合条件">无渠道/不符合条件</option>
						<option value="额度达不到客户要求">额度达不到客户要求</option>
						<option value="客户不配合补资料">客户不配合补资料</option>
						<option value="客户联系不上">客户联系不上</option>
						<option value="其他">其他</option>
				</select>
				</td>
				<td><label class="control-label" style="width: 55px;">退件备注</label></td>
				<td colspan="5"><input type="hidden" name="failureMessage.type"
					value="0" /> <input type="text" class="input"
					style="width: 675px;" name="failureMessage.remark" /></td>
				<%--
				<td>
					<button class="btn mini yellow" type="button" style="margin-bottom: 8px;padding:2px 3px;" onclick="deleteRejectTemplate(this)">删除</button>
				</td>
				 --%>
			</tr>
		</table>
	</div>
	<!-- 退件模板 end -->

	<!-- 退单模板 start -->
	<div class="chargebackTemplate">
		<table>
			<tr>
				<td style="width: 15px; padding: 4px; background: red; color: #fff;">退单</td>
				<td style="width: 5px;"></td>
				<td><label class="control-label">退单时间</label></td>
				<td>
					<div class="input-append date form_datetime">
						<input data-provide="datepicker" type="text" class=""
							readonly="readonly" value="" size="12" style="width: 73px;"
							name="failureMessage.time">
					</div>
				</td>
				<td><label class="control-label">退单原因</label></td>
				<td>
					<%--
					<input type="text" class="input" name="failureMessage.cause" style="width:250px;"/>
					 --%> <select name="failureMessage.cause" style="width: 140px;">
						<option value=""></option>
						<option value="不接受利息">不接受利息</option>
						<option value="客户不做了">客户不做了</option>
						<option value="无渠道/不符合条件">无渠道/不符合条件</option>
						<option value="额度达不到客户要求">额度达不到客户要求</option>
						<option value="客户不配合补资料">客户不配合补资料</option>
						<option value="客户联系不上">客户联系不上</option>
						<option value="其他">其他</option>
				</select>
				</td>
				<td><label class="control-label">退单备注</label></td>
				<td colspan="5"><input type="hidden" name="failureMessage.type"
					value="1" /> <input type="text" class="input"
					style="width: 675px;" name="failureMessage.remark" /></td>
				<%--
				<td>
					<button class="btn mini yellow" type="button" style="margin-bottom: 8px;padding:2px 3px;" onclick="deleteChargebackTemplate(this)">删除</button>
				</td>
				 --%>
			</tr>
		</table>
	</div>
	<!-- 退单模板 end -->

	<!-- 再次更改为跟进状态 start -->
	<div class="signFollowTemplate">
		<table>
			<tr>
				<td
					style="width: 15px; padding: 4px; background: green; color: #fff;">跟进</td>
				<td style="width: 5px;"></td>
				<td><label class="control-label">备注</label></td>
				<td colspan="10"><input type="hidden"
					name="failureMessage.type" value="2" /> <input type="text"
					class="input" style="width: 900px;" name="failureMessage.remark" />
				</td>
			</tr>
		</table>
	</div>
	<!-- 再次更改为跟进状态 end -->


</div>

<%@ include file="loaningTemplate.jsp"%>

<script type="text/javascript">
/**
 * 添加签单客户模板
 */
function signCustomerTemplate(){
	var template = $('#signCustomerTemplateContainer>.signCustomerTemplate').clone().prependTo($('#signCustomerArea'));
	var select = $(template).find('td.lendingInstitutionTd>select');
	activeSelectChosenObject($(select));
	activeAddSignOfWarrantCdeCascade(template);	// 激活添加签单模板后权证信息下来列表框
}

function activeAddSignOfWarrantCdeCascade(container){
	new CompanyDepartmentEmployeeCascade({
		companySelect : container.find("select.warrantCompanyId"),
		departmentSelect : container.find("select.warrantDepartmentId"),
		employeeSelect : container.find("select.warrantEmployeeId"),
		companyType : 2,
		companyLabel : "请选择权证公司",
		departmentLabel : "请选择权证部门",
		employeeLabel : "请选择权证专员",
		level : 3
	}).init();
}

/**
 * 添加签单客户
 */
function saveSignCustomer(obj){
	//获取当前form对象
	var $form = $(obj).parents('.addSignCustomerForm');
	//验证表单
	//var errorTipMsg = validateSignCustomerForm($form);
	//判断并给出提示
	//if(errorTipMsg!=''){
	//	$('#errorTipModalBody').html(errorTipMsg);
	//	$('#errorTipModal').modal('show');
	//	return;
	//}
	//$(obj).attr('disabled',true);
	//序列化表单
	var v = serializeSignCustomerForm($form);
	
	//添加
	$.post('${pageContext.request.contextPath}/signCustomer/addSignCustomer.do',v,function(data){
		if(data==true){
			alert('添加成功!');
			//aPost('signCustomer/getSignCustomerList.do');
		}else{
			alert('添加失败!');
			$(obj).attr('disabled',false);
		}
	});
}
</script>

<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/customer/dateFormHandler.js"></script>

