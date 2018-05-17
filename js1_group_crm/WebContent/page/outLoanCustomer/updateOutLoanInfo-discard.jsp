<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %> 
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>   
<c:set var="olc" value="${sc.outLoanCustomer}"/>
<div id="outLoanCustomerContainer" style="display:block;">
	<!-- 放款客户模板 start-->
	<div class="outLoanCustomerTemplate">
		<div class="portlet box green">
			<div class="portlet-title">
				<div class="caption"><i class="icon-cogs"></i>放款客户</div>
				<div class="tools">
					<a class="collapse" href="javascript:;"></a>
				</div>
			</div>
			<div class="portlet-body">
				<div class="outLoanCustomerArea">
					<form id="updateOutLoanCustomerForm" action="">
						<input type="hidden" name="customerId" value="${sc.customer.id }"/>
						<table class="formTable">
							<tr>
								<td rowspan="50" style="width:10px;padding:0px 7px;background: #35AA47; color: #fff;word-break:break-all;font-size:15px;">${sc.contractNO }<span class="number"></span></td>
								<td rowspan="50" style="width:10px;"></td>
								<td width="50px"><label class="control-label">客户姓名</label></td>
								<td width="150px">
									<input type="hidden" name="id" value="${olc.id }"/>
									<input type="text" name="name" class="input" value="${olc.name }"> 
								</td>
								<td><label class="control-label">权证专员</label></td>
								<td>
									<input type="text" name="warrantName"  class="input" value="${olc.warrantName }"/> 
								</td>
								<td><label class="control-label">服务评级</label></td>
								<td>
									<input type="text" class="input width120" disabled="disabled"/> 
								</td>
								<td width="50"><label class="control-label">办全委托</label></td>
								<td>
									<select name="hasFullDelegate" class="small m-wrap">
										<option value=""></option>
										<option value="是" ${olc.hasFullDelegate=='是'?'selected':''}>是</option>
										<option value="否" ${olc.hasFullDelegate=='否'?'selected':''}>否</option>
									</select>
								</td>
							</tr>
							<tr>
								<td width="50px"><label class="control-label">放款编号</label></td>
								<td width="150px">
									<input type="text" name="outLoanNum" class="input width120" value="${olc.outLoanNum }"/> 
								</td>
								<td width="50px"><label class="control-label">放款金额</label></td>
								<td width="150px">
									<input type="text" name="loanAmount" class="input width120" value="<fmt:formatNumber pattern="#.#" value="${olc.loanAmount }"/>"> 
								</td>
								<td width="50px"><label class="control-label">审过时间</label></td>
								<td width="150px">
									<input name="approveTime" type="text" class="" value="<fmt:formatDate pattern="yyyy-MM-dd" value="${olc.approveTime}"/>" readonly="readonly" style="width:110px;">
								</td>
								<td width="50px"><label class="control-label">放款时间</label></td>
								<td width="150px">
									<input name="handleTime" type="text" class="" value="<fmt:formatDate pattern="yyyy-MM-dd" value="${olc.handleTime}"/>" readonly="readonly" style="width:110px;">
								</td>
								<td><label class="control-label">办理周期</label></td>
								<td width="150px">
									<input type="text" class="input" disabled="disabled"  value="${olc.handlePeriod }"/>
								</td>
							</tr>
							<tr>
								<td><label class="control-label">放款类型</label></td>
								<td>
									<input type="hidden" name="loanProducts[0].id" value="${olc.loanProducts[0].id }"/>
									<select name="loanProducts[0].loanType" class="small m-wrap" style="width:126px;">
										<option value="" ${olc.loanProducts[0].loanType==''?'selected':''}></option>
										<option value="信贷" ${olc.loanProducts[0].loanType=='信贷'?'selected':''}>信贷</option>
										<option value="信用卡" ${olc.loanProducts[0].loanType=='信用卡'?'selected':''}>信用卡</option>
										<option value="房贷" ${olc.loanProducts[0].loanType=='房贷'?'selected':''}>房贷</option>
										<option value="二抵" ${olc.loanProducts[0].loanType=='二抵'?'selected':''}>二抵</option>
										<option value="车贷" ${olc.loanProducts[0].loanType=='车贷'?'selected':''}>车贷</option>
										<option value="短借" ${olc.loanProducts[0].loanType=='短借'?'selected':''}>短借</option>
										<option value="企贷" ${olc.loanProducts[0].loanType=='企贷'?'selected':''}>企贷</option>
									</select>
								</td>
								<td><label class="control-label">放款机构</label></td>
								<td class=" lendingInstitutionTd">
									<select name="loanProducts[0].lendingInstitution" data-placeholder=" " class="small m-wrap chosen">
										<option value=""></option>
										<optgroup label="银行">
										<c:forEach var="lendingInstitution" items="${sessionScope.lendingInstitutionMap.bank }">
											<option value="${lendingInstitution.id }"  ${lendingInstitution.id==olc.loanProducts[0].lendingInstitution?'selected':''}>${lendingInstitution.name }</option>
										</c:forEach>
										</optgroup>
										<optgroup label="小贷">
											<c:forEach var="lendingInstitution" items="${sessionScope.lendingInstitutionMap.smallLand }">
												<option value="${lendingInstitution.id }" ${lendingInstitution.id==olc.loanProducts[0].lendingInstitution?'selected':''}>${lendingInstitution.name }</option>
											</c:forEach>
										</optgroup>		
									</select>
									
									
								</td>
								<td><label class="control-label">银行产品</label></td>
								<td>
									<input type="text" name="loanProducts[0].bankProduct" class="input" value="${olc.loanProducts[0].bankProduct }"/> 
								</td>
								<td>
									<div id="showRate"><label class="control-label"></label></div>
										<div id="choiceRate" style="display:none;">
										<select id="rateSelect" name="loanProducts[0].rateType" class="small" style="width:50px;height:26px;">
											<option value="annualRate" ${(olc.loanProducts[0].rateType=='annualRate' || empty olc.loanProducts[0].rateType || olc.loanProducts[0].rateType=='')?'selected':'' }>年利率</option>
											<option value="monthlyRate" ${olc.loanProducts[0].rateType=='monthlyRate'?'selected':'' }>月利率</option>
										</select>
									</div>
								</td>
								<td>
									<input type="text" name="loanProducts[0].apr" class="input" value="${olc.loanProducts[0].apr }"/> % 
								</td>
								<td width="50"><label class="control-label">贷款期限</label></td>
								<td width="70">
									<select name="loanProducts[0].loanYear" class="" style="width:70px;">
										<option value=""></option>
										<optgroup label="月贷">
											<c:forEach begin="1" end="12" var="i">
												<c:set var="loanMonthTemp" value="${i}月"/>
												<option value="${loanYearTemp}" ${olc.loanProducts[0].loanYear==loanMonthTemp?'selected':''}>${i}月</option>
											</c:forEach>
										</optgroup>
										<optgroup label="年贷">
											<c:forEach begin="1" end="30" var="i">
												<c:set var="loanYearTemp" value="${i}年"/>
												<option value="${loanYearTemp}" ${olc.loanProducts[0].loanYear==loanYearTemp?'selected':''}>${i}年</option>
											</c:forEach>
										</optgroup>
									</select>
								</td>
								<%--
								<td style="width:50px;">
									<button type="button" class="btn blue" style="padding:2px;" onclick="addOutLoanProduct(this)">更多</button>
								</td>
								 --%>
							</tr>
							
							<%-- <c:forEach var="lp" items="${olc.loanProducts}" varStatus="vs">
								<c:if test="${vs.count>1 }">
									<tr class="loanProductTemplateTr">
										<td>
											<label class="control-label">放款类型</label>
											<input type="hidden" value="${lp.id}" class="loanProductId"/>
										</td>
										<td class="loanType">
											<select class="small m-wrap" style="width:126px;">
												<option value=""></option>
												<option value="信贷" ${lp.loanType=='信贷'?'selected':''}>信贷</option>
												<option value="信用卡" ${lp.loanType=='信用卡'?'selected':''}>信用卡</option>
												<option value="房贷" ${lp.loanType=='房贷'?'selected':''}>房贷</option>
												<option value="二抵" ${lp.loanType=='二抵'?'selected':''}>二抵</option>
												<option value="车贷" ${lp.loanType=='车贷'?'selected':''}>车贷</option>
												<option value="短借" ${lp.loanType=='短借'?'selected':''}>短借</option>
												<option value="企贷" ${lp.loanType=='企贷'?'selected':''}>企贷</option>
											</select>
										</td>
										<td><label class="control-label">放款机构</label></td>
										<td class="lendingInstitution">
											<select data-placeholder=" " class="small m-wrap">
												<option></option>
												<c:forEach var="li" items="${sessionScope.lendingInstitutionList}">
													<option value="${li.id}" ${li.id==lp.lendingInstitution?'selected':''}>${li.name }</option>
												</c:forEach>		
											</select>
										</td>
										<td><label class="control-label">银行产品</label></td>
										<td class="bankProduct">
											<input type="text" class="input" value="${lp.bankProduct }"/> 
										</td>
										<td><label class="control-label">月利率</label></td>
										<td class="apr">
											<input type="text" class="input" value="${lp.apr }"/> 
										</td>
										<td width="50"><label class="control-label">贷款年限</label></td>
										<td class="loanYear">
											<select class="small m-wrap">
												<option value=""></option>
												<c:forEach begin="1" end="30" var="i">
													<c:set var="loanYearTemp" value="${i}年"/>
													<option value="${loanYearTemp}" ${sc.loanProducts[0].loanYear==loanYearTemp?'selected':''}>${i}年</option>
												</c:forEach>
											</select>
										</td>
										<td class="deleteLoanProductTemplate">
											<button class="btn mini yellow" type="button" style="margin-bottom: 8px;padding:2px 3px;" onclick="deleteLoanProductTemplate(this)">删除</button>
										</td>
									</tr>
								</c:if>
							</c:forEach> --%>
							
							<tr class="customerOutLoanProductAreaAfter">
								<td><label class="control-label">还款金额</label></td>
								<td>
									<input type="text" name="paymentAmount" class="input" value="<fmt:formatNumber pattern="#.#" value="${olc.paymentAmount }"/>"> 
								</td>
								<td><label class="control-label">还款时间</label></td>
								<td>
									<%--
									<div class="input-append date form_datetime">
										<input name="paymenttime" data-provide="datepicker" type="text" class="" value="<fmt:formatDate pattern="yyyy-MM-dd" value="${olc.paymenttime }"/>" readonly="readonly" size="12" style="width:110px;">
									</div>
									 --%>
									<input class="paymenttime_form_datetime" name="paymenttime" size="12" style="width:110px;" type="text" value="<fmt:formatDate pattern="yyyy-MM-dd" value="${olc.paymenttime }"/>" readonly>
								</td>
								<td><label class="control-label">已收定金</label></td>
								<td>
									<input type="text" name="receivedDeposit" class="input width120" value="<fmt:formatNumber pattern="#.#" value="${olc.receivedDeposit }"/>"/> 
								</td>
								<td width="50"><label class="control-label">压证件</label></td>
								<td>
									<select name="hasCertificate" class="small m-wrap">
										<option value=""></option>
										<option value="是" ${olc.hasCertificate=='是'?'selected':''}>是</option>
										<option value="否" ${olc.hasCertificate=='否'?'selected':''}>否</option>
									</select>
								</td>
							</tr>
							<tr>
								<td><label class="control-label">评估公司</label></td>
								<td>
									<input type="text" name="assessCompany" class="input width120" value="${olc.assessCompany }"/> 
								</td>
								<td><label class="control-label">评估费用</label></td>
								<td>
									<input type="text" name="assessCost" class="input width120" value="<fmt:formatNumber pattern="#.#" value="${olc.assessCost }"/>"/> 
								</td>
								<td><label class="control-label">评估金额</label></td>
								<td>
									<input type="text" name="assessMoney" class="input width120" value="<fmt:formatNumber pattern="#.#" value="${olc.assessMoney }"/>"/> 
								</td>
								<td><label class="control-label">抵押费用</label></td>
								<td>
									<input type="text" name="pledgeCost" class="input width120" value="<fmt:formatNumber pattern="#.#" value="${olc.pledgeCost }"/>"/> 
								</td>
							</tr>
							<tr>
								<td><label class="control-label">合&nbsp;同<br/>公证费</label></td>
								<td>
									<input type="text" name="contractNotarizationCharge" class="input width120" value="<fmt:formatNumber pattern="#.#" value="${olc.contractNotarizationCharge }"/>"/> 
								</td>
								<td><label class="control-label">单&nbsp;身<br/>公证费</label></td>
								<td>
									<input type="text" name="aloneNotarizationCharge" class="input width120" value="<fmt:formatNumber pattern="#.#" value="${olc.aloneNotarizationCharge }"/>"/> 
								</td>
								<td><label class="control-label">委&nbsp;托<br/>公证费</label></td>
								<td>
									<input type="text" name="entrustNotarizationCharge" class="input width120" value="<fmt:formatNumber pattern="#.#" value="${olc.entrustNotarizationCharge }"/>"/> 
								</td>
								<td><label class="control-label">已收证件</label></td>
								<td colspan="5">
									<c:forEach var="cfvb" items="${olc.certificateViewBeans}">
										<a href="javascript:void(0);" onclick="showCertificateModal('${cfvb.name}','${cfvb.srcfileName}','${cfvb.path}')" style="padding:1px;">${cfvb.name}</a>
										<input type="hidden" name="certificates" value="${cfvb.id}-${cfvb.name}-${cfvb.srcfileName}-${cfvb.path}"/>
									</c:forEach>
								</td>
							</tr>
							<tr>
								
								<td><label class="control-label">实收费用</label></td>
								<td>
									<input type="text" name="realPrice" class="input" value="<fmt:formatNumber pattern="#.#" value="${olc.realPrice }"/>"/> 
								</td>
								<td><label class="control-label">后台成本</label></td>
								<td>
									<input type="text" name="cost" class="input" value="<fmt:formatNumber pattern="#.#" value="${olc.cost }"/>"/> 
								</td>
								<td><label class="control-label">净业绩</label></td>
								<td>
									<input type="text" name="netEarnings" class="input" value="<fmt:formatNumber pattern="#.#" value="${olc.netEarnings }"/>"/> 
								</td>
							</tr>
							<tr>
								<td><label class="control-label">是否垫资</label></td>
								<td>
									<select name="hasLoaning" class="small m-wrap" onchange="hasAddLoaning(this)">
										<option value=""></option>
										<option value="是" ${olc.hasLoaning=='是'?'selected':''}>是</option>
										<option value="否" ${olc.hasLoaning=='否'?'selected':''}>否</option>
									</select>
								</td>
								<td><label class="control-label">还款方式</label></td>
								<td>
									<select name="repaymentType" class="small m-wrap">
										<option value=""></option>
										<option value="先息后本" ${olc.repaymentType=='先息后本'?'selected':''}>先息后本</option>
										<option value="等额本息" ${olc.repaymentType=='等额本息'?'selected':''}>等额本息</option>
										<option value="等额本金" ${olc.repaymentType=='等额本金'?'selected':''}>等额本金</option>
										<option value="不规则还款" ${olc.repaymentType=='不规则还款'?'selected':''}>不规则还款</option>
										<option value="其他" ${olc.repaymentType=='其他'?'selected':''}>其他</option>
									</select>
								</td>
								<td colspan="8">
									<button type="button" class="btn blue" onclick="importCertificate(this,${sc.customer.id})"><i class="icon-plus"></i> 导入影像文件</button>
								</td>
							</tr>
							<tr>
								<td><label class="control-label">抵押物</label></td>
								<td style="width:60px;">
									<c:forEach var="cvb" items="${sc.collateralViewBeans}">
										<input type="checkbox" name="collaterals" value="${cvb}" checked="checked"/>${cvb.name}${cvb.id}
									</c:forEach>
									
									<c:forEach var="cvb" items="${noneSelectedAssets}">
										<input type="checkbox" name="collaterals" value="${cvb}"/>${cvb.name}${cvb.id}
									</c:forEach>
								</td>
								<td><label class="control-label">其他备注</label></td>
								<td colspan="9">
									<textarea name="remark" rows="2" cols="" style="width:796px;">${olc.remark }</textarea> 
								</td>
							</tr>
						</table>
						
						<c:if test="${olc.hasLoaning=='是' }">
							<div class="loaningTemplate">
								<table class="formTable">
									<tr>
										<td rowspan="2" style="width:15px;padding:5px;background: #35AA47; color: #fff;">垫资信息</td>
										<td rowspan="2" style="width:5px;"></td>
										<td width="50px"><label class="control-label">垫资金额</label></td>
										<td width="150px">
											<input type="text" class="input" name="loaningAmount" value="<fmt:formatNumber pattern="#.#" value="${olc.loaningAmount}"/>"/>
										</td>
										<td width="50px"><label class="control-label">垫资费率</label></td>
										<td width="150px">
											<input type="text" class="input" name="loaningRate" value="${olc.loaningRate}"/>
										</td>
								 		<td width="50px"><label class="control-label">垫资时间</label></td>
										<td width="150px">
											<div class="input-append date form_datetime">
												<input name="loaningDate" data-provide="datepicker" type="text" class="" readonly="readonly" size="12" style="width:110px;"  value="<fmt:formatDate pattern="yyyy-MM-dd" value="${olc.loaningDate}"/>"/>
											</div>
										</td>
										<td width="50px"><label class="control-label">垫资周期</label></td>
										<td width="150px">
											<input type="text" class="input" name="loaningCycle" value="${olc.loaningCycle}"/>
										</td>
										<td style="width:100px;"><label class="control-label" style="width:100px;">是否在我公司做单</label></td>
										<td>
											<select class="xsmall" style="width:30px;" name="loaningType">
												<option value=""></option>					
												<option value="是" ${olc.loaningType=='是'?'selected':''}>是</option>					
												<option value="否" ${olc.loaningType=='否'?'selected':''}>否</option>					
											</select>
										</td>
									</tr>
									<tr>
										<td><label class="control-label">预估风险</label></td>
										<td colspan="9">
											<input type="text" class="" style="width:1020px;" name="loaningRisk" value="${olc.loaningRisk }"/>
										</td>
									</tr>
								</table>
							</div>
						</c:if>
						
					</form>
					<div class="form-actions" style="padding:8px;text-align:center;margin-top:5px;margin-bottom:10px;" >
						<button class="btn blue" type="button" onclick="updateOutLoanCustomer(this)"><i class="icon-ok"></i> 保存</button>
						<a class="btn aPost" href="${refererUrl}"><i class="icon-remove"></i> 取消</a>
					</div>
					   	
				</div>
			</div>
		</div>
	</div>
	<!-- 放款客户模板 end-->
	
</div>

<script type="text/javascript">
/**
 * 修改放款客户
 */
function updateOutLoanCustomer(obj){
	//获取到当前区域的表单对象
	var form = $('#updateOutLoanCustomerForm');
	//验证表单
	var errorTipMsg = validateOutLoanCustomer(form);
	//判断并给出提示
	if(errorTipMsg!=''){
		$('#errorTipModalBody').html(errorTipMsg);
		$('#errorTipModal').modal('show');
		return;
	}
	//获取表单的值
	var v = serializeOutLoanCustomerForm(form);
	//添加
	$.post('outLoanCustomer/updateOutLoanCustomer.do',v,function(data){
		if(data==true){
			alert('修改放款客户成功!');
			//aPost('signCustomer/getOutLoanCustomerList.do');
		}else{
			alert('修改放款客户失败!');
		}
	});
}

//利率显示处理器
$(function(){
	
	//默认显示
	$('#showRate label').html($('#rateSelect option:selected').text());
	
	//单击利率
	$('#showRate').click(function(){
		$(this).hide();
		$('#choiceRate').show();
	});
	
	$('#rateSelect').change(function(){
		$('#choiceRate').hide();
		$('#showRate label').html($('#rateSelect option:selected').text());
		$('#showRate').show();
	});
	
})
</script>

<script type="text/javascript" src="${pageContext.request.contextPath}/js/customer/dateFormHandler.js"></script>
