<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<div id="outLoanCustomerContainer" style="display:none;">
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
					<form id="addOutLoanCustomerForm" action="">
						<table class="formTable">
							<tr>
								<td rowspan="50" style="width:10px;padding:0px 7px;background: #35AA47; color: #fff;word-break:break-all;font-size:15px;">${sc.contractNO }<span class="number"></span></td>
								<td rowspan="50" style="width:10px;"></td>
								<td width="50px"><label class="control-label">客户姓名</label></td>
								<td width="150px">
									<input type="hidden" name="signCustomer.id" value="${sc.id }"/>
									<input type="text" name="name" class="input" value="${sc.signContacts[0].name }"> 
								</td>
								<td width="50px"><label class="control-label">贷款金额</label></td>
								<td width="150px">
									<input type="text" name="loanAmount" class="input width120" > 
								</td>
								<td width="50px"><label class="control-label">办理时间</label></td>
								<td width="150px">
									<input name="handleTime" type="text" class="" value="<fmt:formatDate pattern="yyyy-MM-dd" value="${sc.signTime}"/>" readonly="readonly" style="width:110px;">
								</td>
								<td><label class="control-label">办理周期</label></td>
								<td width="150px">
									<input type="text" name="handlePeriod" class="input" readonly="readonly" value="${diffDay}"/>
								</td>
							</tr>
							<tr>
								<td><label class="control-label">权证专员</label></td>
								<td>
									<input type="text" name="warrantName"  class="input"> 
								</td>
								<td><label class="control-label">服务评级</label></td>
								<td>
									<input type="text" name="serveRating" class="input width120" disabled="disabled"/> 
								</td>
								<td width="50"><label class="control-label">压证件</label></td>
								<td>
									<select name="hasCertificate" class="small m-wrap">
										<option value=""></option>
										<option value="是">是</option>
										<option value="否">否</option>
									</select>
								</td>
								<td width="50"><label class="control-label">办全委托</label></td>
								<td>
									<select name="hasFullDelegate" class="small m-wrap">
										<option value=""></option>
										<option value="是">是</option>
										<option value="否">否</option>
									</select>
								</td>
							</tr>
							<tr>
								<td><label class="control-label">实收费用</label></td>
								<td>
									<input type="text" name="realPrice" class="input"/> 
								</td>
								<td><label class="control-label">已收定金</label></td>
								<td>
									<input type="text" name="receivedDeposit" class="input width120"> 
								</td>
								<td><label class="control-label">后台成本</label></td>
								<td>
									<input type="text" name="cost" class="input"> 
								</td>
								<td><label class="control-label">净业绩</label></td>
								<td>
									<input type="text" name="netEarnings" class="input"> 
								</td>
							</tr>
							<tr>
								<td><label class="control-label">放款类型</label></td>
								<td>
									<select name="loanProducts[0].loanType" class="small m-wrap" style="width:126px;">
										<option value=""></option>
										<option value="信贷">信贷</option>
										<option value="信用卡">信用卡</option>
										<option value="房贷">房贷</option>
										<option value="二抵">二抵</option>
										<option value="车贷">车贷</option>
										<option value="短借">短借</option>
										<option value="企贷">企贷</option>
									</select>
								</td>
								<td><label class="control-label">放款机构</label></td>
								<td class=" lendingInstitutionTd">
									<select name="loanProducts[0].lendingInstitution" data-placeholder=" " class="small m-wrap chosen">
										<option></option>
										<%@ include file="../common/lendingInstitution.jsp" %>
									</select>
								</td>
								<td><label class="control-label">银行产品</label></td>
								<td>
									<input type="text" name="loanProducts[0].bankProduct" class="input"/> 
								</td>
								<td><label class="control-label">月利率</label></td>
								<td>
									<input type="text" name="loanProducts[0].apr" class="input"/> % 
								</td>
								<td width="50"><label class="control-label">贷款年限</label></td>
								<td width="70">
									<select name="loanProducts[0].loanYear" class="" style="width:70px;">
										<option value=""></option>
										<c:forEach begin="1" end="30" var="i">
											<option value="${i}年">${i}年</option>
										</c:forEach>
									</select>
								</td>
								<%--
								<td style="width:50px;">
									<button type="button" class="btn blue" style="padding:2px;" onclick="addOutLoanProduct(this)">更多</button>
								</td>
								 --%>
							</tr>
							<tr class="customerOutLoanProductAreaAfter">
								<td><label class="control-label">还款金额</label></td>
								<td>
									<input type="text" name="paymentAmount" class="input"> 
								</td>
								<td><label class="control-label">还款时间</label></td>
								<td>
									<%--
									<div class="input-append date form_datetime">
										<input name="paymenttime" data-provide="datepicker" type="text" class="" readonly="readonly" size="12" style="width:110px;">
									</div>
									 --%>
									 <input class="paymenttime_form_datetime" name="paymenttime" size="12" style="width:110px;" type="text" value="" readonly>
								</td>
								<td><label class="control-label">已收证件</label></td>
								<td colspan="5">
									<c:forEach var="cfvb" items="${sc.certificateViewBeans}">
										<a href="javascript:void(0);" onclick="showCertificateModal('${cfvb.name}','${cfvb.srcfileName}','${cfvb.path}')" style="padding:1px;">${cfvb.name}</a>
										<input type="hidden" name="certificates" value="${cfvb.id}-${cfvb.name}-${cfvb.srcfileName}-${cfvb.path}"/>
									</c:forEach>
								</td>
							</tr>
							<tr>
								<td><label class="control-label">是否垫资</label></td>
								<td>
									<select name="hasLoaning" class="small m-wrap" onchange="hasAddLoaning(this)">
										<option value=""></option>
										<option value="是">是</option>
										<option value="否">否</option>
									</select>
								</td>
								<td><label class="control-label">还款方式</label></td>
								<td>
									<select name="repaymentType" class="small m-wrap">
										<option value=""></option>
										<option value="先息后本">先息后本</option>
										<option value="等额本息">等额本息</option>
										<option value="等额本金">等额本金</option>
										<option value="不规则还款">不规则还款</option>
										<option value="其他">其他</option>
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
									<textarea name="remark" rows="2" cols="" style="width:796px;;"></textarea> 
								</td>
							</tr>
						</table>
					</form>
					<div class="form-actions" style="padding:8px;text-align:center;margin-top:5px;margin-bottom:10px;" >
						<button class="btn blue" type="button" onclick="addOutLoanCustomer(this)"><i class="icon-ok"></i> 保存</button>
						<button class="btn" type="button" onclick="hideOutLoan(this)"><i class="icon-remove"></i> 取消</button>
					</div>
					   	
				</div>
			</div>
		</div>
	</div>
	<!-- 放款客户模板 end-->
	
</div>

<script type="text/javascript">
/**
 * 添加放款客户
 */
function addOutLoanCustomer(obj){
	//获取到当前区域的表单对象
	var form = $('#addOutLoanCustomerForm');
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
	$.post('outLoanCustomer/addOutLoanCustomer.do',v,function(data){
		if(data==true){
			alert('添加放款客户成功!');
			//aPost('signCustomer/getOutLoanCustomerList.do');
		}else{
			alert('添加放款客户失败!');
		}
	});
	
	//下拉搜索多选框
	$(".chosen").chosen({
	   allow_single_deselect: $(this).attr("data-with-diselect") === "1" ? true : false
	});
}
</script>

<script type="text/javascript" src="${pageContext.request.contextPath}/js/customer/dateFormHandler.js"></script>
