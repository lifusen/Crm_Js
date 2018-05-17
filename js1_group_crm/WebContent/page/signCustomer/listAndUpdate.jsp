<%@page import="cn.itproject.crm.controller.viewbean.CollateralViewBean"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<c:set var="sc" value="${requestScope.signCustomer}" />
<c:if test="${sc!=null}">
	<script type="text/javascript"
		src="${pageContext.request.contextPath }/js/customer/addOrUpdateOutLoanCustomer.js"></script>
	<c:set var="signCustomerTitleBgColor"
		value="${sc.customerStatus==0?'#4B8DF8':sc.customerStatus==1?'red':'#FFB848' }" />
	<c:if test="${not empty sc.warrantCompanyId}">
		<c:set var="signCustomerTitleBgColor" value="#35aa47" />
	</c:if>
	<div class="signCustomerTemplate" style="padding-bottom: 10px;">
		<form class="updateSignCustomerForm" action="">
			<table class="formTable signCustomerTable">
				<tr>
					<td class="signCustomerTitleTd" rowspan="50"
						style="width:9px;padding:0px 7px;background: ${signCustomerTitleBgColor}; color: #fff;word-break:break-all;font-size:15px;">${sc.contractNO }</td>
					<td rowspan="50" style="width: 10px;"></td>
					<td width="50px"><label class="control-label">客户名称</label></td>
					<td width="140px"><input type="hidden"
						name="signContacts[0].id" value="${sc.signContacts[0].id}" /> <input
						type="hidden" name="id" value="${sc.id}" /> <input type="hidden"
						name="customer.id" value="${sc.customer.id }"> <input
						id="signContactsName" type="text" name="signContacts[0].name"
						class="input" value="${sc.signContacts[0].name }" /> <input
						type="hidden" name="contractNO" value="${sc.contractNO }" /></td>
					<td width="50px"><label class="control-label">项目编号</label></td>
					<td width="140px"><input type="text"
						name="signContacts[0].idCardNo" class="input width120"
						value="${sc.signContacts[0].idCardNo }" maxlength="18" /></td>
					<td width="50px"><label class="control-label">备注</label></td>
					<td width="140px"><input type="text"
						name="signContacts[0].remark" class="input"
						value="${sc.signContacts[0].remark }" /></td>
					<td width="50px"><label class="control-label">年龄</label></td>
					<td width="140px"><input type="text"
						name="signContacts[0].age" class="input"
						value="${sc.signContacts[0].age }" maxlength="2" /></td>
					<td>
						<button type="button" class="btn blue" style="padding: 2px;"
							onclick="addSignContactsTemplate(this)">更多</button>
					</td>
				</tr>

				<!-- 循环签单联系人 -->
				<c:forEach var="signContact" items="${sc.signContacts}"
					varStatus="vs">
					<c:if test="${vs.count>1 }">
						<tr class="signContactsTemplateTr">
							<td width="50px"><label class="control-label">客户姓名<input
									type="hidden" class="signContactsId" value="${signContact.id}" /></label></td>
							<td width="140px" class="name"><input type="text"
								class="input" value="${signContact.name}" /></td>
							<td width="50px"><label class="control-label">身份证号</label></td>
							<td width="140px" class="idCardNo"><input type="text"
								class="input width120" value="${signContact.idCardNo}"
								maxlength="18" /></td>
							<td width="50px"><label class="control-label">备注</label></td>
							<td width="140px" class="remark"><input type="text"
								class="input" value="${signContact.remark}" /></td>
							<td width="50px"><label class="control-label">年龄</label></td>
							<td width="140px" class="age"><input type="text"
								class="input" value="${signContact.age}" maxlength="2" /></td>
							<%--
							<td class="deleteSignContactsTemplate">
								<button class="btn mini yellow" type="button" style="margin-bottom: 8px;padding:2px 3px;" onclick="deleteSignContactsTemplate(this)">删除</button>
							</td>
							 --%>
						</tr>
					</c:if>
				</c:forEach>


				<tr class="signContactsAreaAfter">
					<td><label class="control-label">客户状态</label></td>
					<td><c:if test="${sc.outLoanCondition=='已放款'}">
							<input type="text" class="input width120" readonly="readonly"
								value="已放款" />
						</c:if> <c:if test="${sc.outLoanCondition!='已放款'}">
							<select id="signCustomerStatusSelect" name="customerStatus"
								class="small m-wrap" onchange="customerStatusChange(this)">
								<option value="0" ${sc.customerStatus==0?'selected':''}>进行中</option>
								<option value="1" ${sc.customerStatus==1?'selected':''}>退单</option>
								<option value="2" ${sc.customerStatus==2?'selected':''}>此单不能受理</option>
							</select>
						</c:if></td>
					<!-- 
					<td><label class="control-label">放款情况</label></td>
					<td>
						<input type="text" name="outLoanCondition" class="input width120" readonly="readonly" value="${sc.outLoanCondition }"/> 
					</td>
					<td><label class="control-label">是否再贷</label></td>
					<td>
						<select name="hasLoanWish" class="small m-wrap">
							<option value="" ${sc.hasLoanWish==''?'selected':'' }></option>
							<option value="是" ${sc.hasLoanWish=='是'?'selected':'' }>是</option>
							<option value="否" ${sc.hasLoanWish=='否'?'selected':'' }>否</option>
						</select>
					</td>
					<td><label class="control-label">预期时间</label></td>
					<td>
					
						<%--
						<div class="input-append date form_datetime">
							<input name="planNextLoanTime" data-provide="datepicker" type="text" class="" readonly="readonly" value="<fmt:formatDate value="${sc.planNextLoanTime }" pattern="yyyy-MM-dd"/>" size="12" style="width:110px;">
						</div>
						 --%>
						 
						 <input class="planNextLoanTime_form_datetime" name="planNextLoanTime" size="12" style="width:110px;" type="text" value="<fmt:formatDate value="${sc.planNextLoanTime }" pattern="yyyy-MM-dd"/>" readonly/>
						 
					</td>
					 -->
					<td width="50px"><label class="control-label">签单时间</label></td>
					<td width="140px"><input type="text" class="input"
						readonly="readonly"
						value="<fmt:formatDate pattern="yyyy-MM-dd HH:mm" value="${sc.signTime}"/>" />
					</td>
				</tr>
				<tr>
					<td><label class="control-label">合同费用</label></td>
					<td><input type="text" name="costOfContract" class="input"
						value="<fmt:formatNumber pattern="#.#" maxFractionDigits="2" value="${sc.costOfContract}"/>"
						maxlength="11" /></td>
					<!-- 
					<td><label class="control-label">贷款金额</label></td>
					<td>
						<input type="text" name="loanAmount" class="input" value="<fmt:formatNumber pattern="#.#" maxFractionDigits="2" value="${sc.loanAmount}"/>" maxlength="11"/> 
					</td>
					 -->
					<td><label class="control-label">已收定金</label></td>
					<td><input type="text" name="handsel" class="input width120"
						value="<fmt:formatNumber pattern="#.#" maxFractionDigits="2" value="${sc.handsel }"/>"
						maxlength="11" /></td>
					<td><label class="control-label">销售人员</label></td>
					<td><select name="negotiatorId" class="small m-wrap">
							<option></option>
							<c:forEach var="negotiator"
								items="${sessionScope.allNegotiators}">
								<option value="${negotiator.id }"
									${negotiator.id==sc.negotiatorId?'selected':''}>${negotiator.name }</option>
							</c:forEach>
					</select></td>
					<!-- 
					<td><label class="control-label">已收证件</label></td>
					<td colspan="2">
						<input type="text" name="receivedCertificate" class="input" style="width:200px;" value="${sc.receivedCertificate }"/> 
					</td>
					 -->
				</tr>



				<!--  
				<tr>
					<td><label class="control-label">贷款类型</label></td>
					<td>
						<input type="hidden" name="loanProducts[0].id" value="${sc.loanProducts[0].id}"/>
						<select name="loanProducts[0].loanType" class="small m-wrap" style="width:126px;">
							<option value=""></option>
							<option value="" ${sc.loanProducts[0].loanType==''?'selected':''}></option>
							<option value="信贷" ${sc.loanProducts[0].loanType=='信贷'?'selected':''}>信贷</option>
							<option value="信用卡" ${sc.loanProducts[0].loanType=='信用卡'?'selected':''}>信用卡</option>
							<option value="房贷" ${sc.loanProducts[0].loanType=='房贷'?'selected':''}>房贷</option>
							<option value="二抵" ${sc.loanProducts[0].loanType=='二抵'?'selected':''}>二抵</option>
							<option value="车贷" ${sc.loanProducts[0].loanType=='车贷'?'selected':''}>车贷</option>
							<option value="短借" ${sc.loanProducts[0].loanType=='短借'?'selected':''}>短借</option>
							<option value="企贷" ${sc.loanProducts[0].loanType=='企贷'?'selected':''}>企贷</option>
						</select>
					</td>
					<td><label class="control-label">贷款机构</label></td>
					<td class=" lendingInstitutionTd">
						<select name="loanProducts[0].lendingInstitution" data-placeholder=" " class="small m-wrap chosen">
							<option value=""></option>
							<optgroup label="银行">
							<c:forEach var="lendingInstitution" items="${sessionScope.lendingInstitutionMap.bank }">
								<option value="${lendingInstitution.id }"  ${lendingInstitution.id==sc.loanProducts[0].lendingInstitution?'selected':''}>${lendingInstitution.name }</option>
							</c:forEach>
							</optgroup>
							<optgroup label="小贷">
								<c:forEach var="lendingInstitution" items="${sessionScope.lendingInstitutionMap.smallLand }">
									<option value="${lendingInstitution.id }" ${lendingInstitution.id==sc.loanProducts[0].lendingInstitution?'selected':''}>${lendingInstitution.name }</option>
								</c:forEach>
							</optgroup>		
						</select>
					</td>
					<td><label class="control-label">银行产品</label></td>
					<td>
						<input type="text" name="loanProducts[0].bankProduct" class="input" value="${sc.loanProducts[0].bankProduct}"/> 
					</td>
					<td><label class="control-label">年利率</label></td>
					<td>
						<input type="text" name="loanProducts[0].apr" class="input" value="${sc.loanProducts[0].apr }"/> 
					</td>
					<td width="50"><label class="control-label">贷款年限</label></td>
					<td width="70">
						<select name="loanProducts[0].loanYear" class="" style="width:125px;">
							<c:forEach begin="1" end="30" var="i">
								<c:set var="loanYearTemp" value="${i}年"/>
								<option value="${loanYearTemp}" ${sc.loanProducts[0].loanYear==loanYearTemp?'selected':''}>${i}年</option>
							</c:forEach>
						</select>
					</td>
					<%--
					<td style="width:60px;">
						<button type="button" class="btn blue" style="padding:2px;" onclick="addLoanProduct(this)">更多</button>
					</td>
					 --%>
				</tr>
				-->
				<c:forEach var="lp" items="${sc.loanProducts}" varStatus="vs">
					<c:if test="${vs.count>1 }">
						<tr class="loanProductTemplateTr">
							<td><label class="control-label">贷款类型</label> <input
								type="hidden" value="${lp.id}" class="loanProductId" /></td>
							<td class="loanType"><select class="small m-wrap"
								style="width: 126px;">
									<option value=""></option>
									<option value="信贷" ${lp.loanType=='信贷'?'selected':''}>信贷</option>
									<option value="信用卡" ${lp.loanType=='信用卡'?'selected':''}>信用卡</option>
									<option value="房贷" ${lp.loanType=='房贷'?'selected':''}>房贷</option>
									<option value="二抵" ${lp.loanType=='二抵'?'selected':''}>二抵</option>
									<option value="车贷" ${lp.loanType=='车贷'?'selected':''}>车贷</option>
									<option value="短借" ${lp.loanType=='短借'?'selected':''}>短借</option>
									<option value="企贷" ${lp.loanType=='企贷'?'selected':''}>企贷</option>
							</select></td>
							<td><label class="control-label">贷款机构</label></td>
							<td class="lendingInstitution"><select data-placeholder=" "
								class="small m-wrap chosen">
									<option value=""></option>
									<optgroup label="银行">
										<c:forEach var="lendingInstitution"
											items="${sessionScope.lendingInstitutionMap.bank }">
											<option value="${lendingInstitution.id }"
												${lendingInstitution.id==lp.lendingInstitution?'selected':''}>${lendingInstitution.name }</option>
										</c:forEach>
									</optgroup>
									<optgroup label="小贷">
										<c:forEach var="lendingInstitution"
											items="${sessionScope.lendingInstitutionMap.smallLand }">
											<option value="${lendingInstitution.id }"
												${lendingInstitution.id==lp.lendingInstitution?'selected':''}>${lendingInstitution.name }</option>
										</c:forEach>
									</optgroup>
							</select></td>
							<td><label class="control-label">银行产品</label></td>
							<td class="bankProduct"><input type="text" class="input"
								value="${lp.bankProduct }" /></td>
							<td><label class="control-label">月利率</label></td>
							<td class="apr"><input type="text" class="input"
								value="${lp.apr }" /></td>
							<td width="50"><label class="control-label">贷款年限</label></td>
							<td width="70" class="loanYear"><select class=""
								style="width: 125px;">
									<option value=""></option>
									<c:forEach begin="1" end="30" var="i">
										<c:set var="loanYearTemp" value="${i}年" />
										<option value="${loanYearTemp}"
											${sc.loanProducts[0].loanYear==loanYearTemp?'selected':''}>${i}年</option>
									</c:forEach>
							</select></td>
							<td class="deleteLoanProductTemplate">
								<button class="btn mini yellow" type="button"
									style="margin-bottom: 8px; padding: 2px 3px;"
									onclick="deleteLoanProductTemplate(this)">删除</button>
							</td>
						</tr>
					</c:if>
				</c:forEach>
				<!-- 
				<tr class="customerLoanProductAreaAfter">
					<td><label class="control-label">是否垫资</label></td>
					<td>
						<select name="hasLoaning" class="small m-wrap" onchange="hasAddLoaning(this)">
							<option value=""></option>
							<option value="是" ${sc.hasLoaning=='是'?'selected':'' }>是</option>
							<option value="否" ${sc.hasLoaning=='否'?'selected':'' }>否</option>
						</select>
					</td>
					<td><label class="control-label">还款方式</label></td>
					<td>
						<select name="repaymentType" class="small m-wrap">
							<option value=""></option>
							<option value="先息后本" ${sc.repaymentType=='先息后本'?'selected':''}>先息后本</option>
							<option value="等额本息" ${sc.repaymentType=='等额本息'?'selected':''}>等额本息</option>
							<option value="等额本金" ${sc.repaymentType=='等额本金'?'selected':''}>等额本金</option>
							<option value="不规则还款" ${sc.repaymentType=='不规则还款'?'selected':''}>不规则还款</option>
							<option value="其他" ${sc.repaymentType=='其他'?'selected':''}>其他</option>
						</select>
					</td>	 
					<td colspan="7">
						<button type="button" class="btn blue" onclick="importCertificate(this,${sc.customer.id})"><i class="icon-plus"></i> 导入影像文件</button>
						<c:forEach var="cfvb" items="${sc.certificateViewBeans}">
							<a href="javascript:void(0);" onclick="showCertificateModal('${cfvb.name}','${cfvb.srcfileName}','${cfvb.path}')" style="padding:1px;">${cfvb.name}</a>
							<input type="hidden" name="certificates" value="${cfvb.id}-${cfvb.name}-${cfvb.srcfileName}-${cfvb.path}"/>
						</c:forEach>
					</td>
				</tr>
 -->
				<c:if
					test="${not empty loginUser.companyId and loginUser.companyId == 3}">
					<tr>
						<td><label class="control-label">中介名称</label></td>
						<td><input type="text" class="input" name="intermediaryName"
							value="${sc.intermediaryName }"
							${not empty sc.intermediaryName ? 'readonly' : ''} /></td>
						<td><label class="control-label">中介电话</label></td>
						<td class="intermediaryPhone"><input type="text"
							class="input" name="intermediaryPhone"
							value="${sc.intermediaryPhone }"
							${not empty sc.intermediaryPhone ? 'readonly' : ''} /></td>
						<td><label class="control-label">中介返点</label></td>
						<td class="intermediaryRebate"><input type="text"
							class="input" name="intermediaryRebate"
							value="${sc.intermediaryRebate }"
							${not empty sc.intermediaryRebate ? 'readonly' : ''} /></td>
					</tr>
				</c:if>
				<tr>
					<!-- 
					<td><label class="control-label">抵押物</label></td>
					<td style="width: 60px;" class="signCollaterals">
						<%--
							客户资产:${sessionScope.customerAssetViewBeans}
							<hr/>
							未选择的资产:${requestScope.noneSelectedAssets}
							<hr/>
							已选择的资产:${sc.collateralViewBeans}
							<hr/>
						 --%> <c:forEach var="cvb" items="${sc.collateralViewBeans}">
							<input type="checkbox" name="collaterals" value="${cvb}"
								checked="checked" />${cvb.name}${cvb.id}
						</c:forEach> <c:forEach var="cvb" items="${noneSelectedAssets}">
							<input type="checkbox" name="collaterals" value="${cvb}" />${cvb.name}${cvb.id}
						</c:forEach>

					</td>
					 -->
					<td><label class="control-label">其他备注</label></td>
					<td colspan="8"><textarea name="remark" rows="2" cols=""
							style="width: 796px;">${sc.remark}</textarea></td>
				</tr>

				<!-- 选择权证相关信息 -->
				<%-- 当权证公司ID不为null,则说明移交移交给了权证,则不能修改,为只读状态  --%>
				<c:choose>
					<c:when
						test="${loginRoleId== 2000 || loginRoleId==2001 || loginRoleId==2002 || loginRoleId==2003 || loginUser.type == 'SUPER_ROLE'}">
						<%-- <tr class="outLoanBelong">
							<td><label class="control-label">权证中心</label></td>
							<td>
								<input type="hidden" name="warrantUpdatable" value="false"/>
								<p cid="${sc.warrantCompanyId }" class="staticText outLoanBelongCompanyId">${companyCacheMap[sc.warrantCompanyId]}</p>
							</td>
							<td><label class="control-label">权证部门</label></td>
							<td>
								<p did="${sc.warrantDepartmentId }" class="staticText outLoanBelongDepartmentId">${departmentCacheMap[sc.warrantDepartmentId] }</p>
							</td>
							<td><label class="control-label">权证专员</label></td>
							<td>
								<p eid="${sc.warrantEmployeeId }" class="staticText outLoanBelongWarranterId">${employeeCacheMap[sc.warrantEmployeeId] }</p>
							</td>
							<!-- 添加辅贷人员按钮，主贷type=0、辅贷type=1，只有主贷人员或上级领导才能添加辅贷 -->
							<c:if test="${outLoanBelongType==0 || loginRoleId==2000 || loginRoleId==2001 || loginRoleId==2002 || loginRoleId==999 }">
								<td colspan="2"><a id="addLoaner" class="btn blue mini"><i class="icon-plus"></i> 添加辅贷</a>
									<a id="removeOutLoaner" class="btn red mini">删除</a></td>
							</c:if>
							<td colspan="2"><p style="margin:0px;padding:0px;height:42px;line-height:33px;color: #0362fd;font-size: 14px;"></p></td>
						 </tr> --%>

						<!-- 添加贷款人员 -->
						<c:if
							test="${outLoanBelongType==0 || loginRoleId==2000 || loginRoleId==2001 || loginRoleId==2002 || loginRoleId==999 }">
							<tr id="addAssistWarranter">
								<td><label class="control-label">权证中心</label></td>
								<td><select id="addLoanerWarrantCompanyId"
									class="small m-wrap warrantCompanyId">
								</select></td>
								<td><label class="control-label">权证部门</label></td>
								<td><select id="addLoanerWarrantDepartmentId"
									class="small m-wrap warrantDepartmentId">
								</select></td>
								<td><label class="control-label">权证专员</label></td>
								<td><select id="addLoanerWarrantEmployeeId"
									class="small m-wrap warrantEmployeeId">
								</select></td>
								<td colspan="2"><a id="commitAddLoaner"
									class="btn blue mini"><i class="icon-plus"></i> 添加分享</a> <a
									id="removeOutLoaner" class="btn red mini">删除</a></td>

								<td colspan="3"><p
										style="margin: 0px; padding: 0px; height: 42px; line-height: 33px; color: #0362fd; font-size: 14px;"></p></td>
							</tr>
						</c:if>

						<!-- 显示贷款人员 -->
						<c:forEach items="${outLoanBelongs}" var="ob">
							<tr class="outLoanBelong">
								<td><label class="control-label">权证中心</label></td>
								<td>
									<p cid="${ob.companyId }"
										class="staticText outLoanBelongCompanyId">${companyCacheMap[ob.companyId]}</p>
								</td>
								<td><label class="control-label">权证部门</label></td>
								<td>
									<p did="${ob.departmentId }"
										class="staticText outLoanBelongDepartmentId">${departmentCacheMap[ob.departmentId] }</p>
								</td>
								<td><label class="control-label">权证专员</label></td>
								<td>
									<p eid="${ob.warranterId }"
										class="staticText outLoanBelongWarranterId">${employeeCacheMap[ob.warranterId] }</p>
								</td>

								<td colspan="2"><p
										style="margin: 0px; padding: 0px; height: 42px; line-height: 33px; color: #0362fd; font-size: 14px;"></p></td>
							</tr>

						</c:forEach>
					</c:when>
					<c:otherwise>
						<!--  
						<tr>
							<td><label class="control-label">权证中心</label></td>
							<td><input type="hidden" name="isUpdateWarrant" value="true" />
								<select name="warrantCompanyId"
								class="small m-wrap warrantCompanyId">
							</select></td>
							-->
						<!-- <td><label class="control-label hidden">权证部门</label></td>
							<td>
								<select name="warrantDepartmentId" class="small m-wrap warrantDepartmentId hidden">
								</select>
							</td>
							<td><label class="control-label hidden">权证专员</label></td>
							<td>
								<select name="warrantEmployeeId" class="small m-wrap warrantEmployeeId hidden">
								</select>
							</td> -->
						<!-- 
							<td colspan="4"><p
									style="margin: 0px; padding: 0px; height: 42px; line-height: 33px; color: #0362fd; font-size: 14px;"></p></td>
						</tr>
						 -->
					</c:otherwise>
				</c:choose>
			</table>

			<c:if test="${sc.hasLoaning=='是' }">
				<div class="loaningTemplate">
					<table class="formTable">
						<tr>
							<td rowspan="2"
								style="width: 15px; padding: 4px; background: #35AA47; color: #fff;">垫资信息</td>
							<td rowspan="2" style="width: 5px;"></td>
							<td width="50px"><label class="control-label">垫资金额</label></td>
							<td width="150px"><input type="text" class="input"
								name="loaningAmount"
								value="<fmt:formatNumber pattern="#.#" maxFractionDigits="2" value="${sc.loaningAmount}"/>"
								maxlength="11" /></td>
							<td width="50px"><label class="control-label">垫资费率</label></td>
							<td width="150px"><input type="text" class="input"
								name="loaningRate" value="${sc.loaningRate}" /></td>
							<td width="50px"><label class="control-label">垫资时间</label></td>
							<td width="150px">
								<div class="input-append date form_datetime">
									<input name="loaningDate" data-provide="datepicker" type="text"
										class="" readonly="readonly" size="12" style="width: 110px;"
										value="<fmt:formatDate value="${sc.loaningDate}" pattern="yyyy-MM-dd"/>" />
								</div>
							</td>
							<td width="50px"><label class="control-label">垫资周期</label></td>
							<td width="150px"><input type="text" class="input"
								name="loaningCycle" value="${sc.loaningCycle}" /></td>
							<td style="width: 100px;"><label class="control-label"
								style="width: 100px;">是否在我公司做单</label></td>
							<td><select class="xsmall" style="width: 30px;"
								name="loaningType">
									<option value=""></option>
									<option value="是" ${sc.loaningType=='是'?'selected':''}>是</option>
									<option value="否" ${sc.loaningType=='否'?'selected':''}>否</option>
							</select></td>
						</tr>
						<tr>
							<td><label class="control-label">预估风险</label></td>
							<td colspan="9"><input type="text" class=""
								style="width: 1020px;" name="loaningRisk"
								value="${sc.loaningRisk }" /></td>
						</tr>
					</table>
				</div>
			</c:if>

			<c:forEach var="failureMessage" items="${sc.failureMessages}">
				<c:choose>
					<c:when test="${failureMessage.type==0}">
						<c:set var="failureMessageTitleBgColor" value="#FFB848" />
						<c:set var="failureMessageName" value="退件" />
					</c:when>
					<c:when test="${failureMessage.type==1}">
						<c:set var="failureMessageTitleBgColor" value="red" />
						<c:set var="failureMessageName" value="退单" />
					</c:when>
					<c:when test="${failureMessage.type==2}">
						<c:set var="failureMessageTitleBgColor" value="green" />
						<c:set var="failureMessageName" value="跟进" />
						<c:set var="showCause" value="false" />
					</c:when>
				</c:choose>

				<!-- 退单退件信息 -->
				<div class="chargebackAndRejectArea" style="padding: 1px;">
					<table>
						<tr>
							<td
								style="width:15px;padding:4px;background: ${failureMessageTitleBgColor}; color: #fff;">${failureMessageName }</td>
							<td style="width: 5px;"></td>
							<td><label class="control-label" style="width: 55px;">${failureMessageName}时间</label></td>
							<td>
								<p style="width: 80px; padding-left: 5px;">
									<fmt:formatDate value="${failureMessage.time}"
										pattern="yyyy-MM-dd" />
								</p>
							</td>
							<c:if test="${showCause!=false}">
								<td><label class="control-label" style="width: 55px;">${failureMessageName}原因</label></td>
								<td>
									<p style="width: 140px; padding-left: 5px;">${failureMessage.cause}</p>
								</td>
							</c:if>
							<td><label class="control-label" style="width: 55px;">${failureMessageName}备注</label></td>
							<td>
								<p style="padding-left: 5px;">${failureMessage.remark}</p>
							</td>
						</tr>
					</table>
				</div>
			</c:forEach>

			<div class="form-actions updateButtonArea"
				style="padding: 8px; text-align: center; margin-top: 5px; margin-bottom: 10px;">

				<button class="btn blue" type="button"
					onclick="updateSignCustomer(this)">
					<i class="icon-ok"></i> 保存
				</button>


				<c:choose>
					<c:when test="${closeWindow == true }">
						<a class="btn" href="javascript:void(0);" onclick="closeWindow()"><i
							class="icon-remove"></i> 关闭此页面</a>
					</c:when>
					<c:when test="${refererUrl != null && not empty refererUrl }">
						<a class="btn aPost" href="${refererUrl}"><i
							class="icon-remove"></i> 取消</a>
					</c:when>
					<c:when test="${refererUrl == null || empty refererUrl}">
						<a class="btn" href="javascript:void(0);" id="hideDetailInSign"><i
							class="icon-remove"></i> 取消</a>
					</c:when>
				</c:choose>


				<!-- 跟进客户sc.customerStatus, 且当前角色拥有放款权限canLoanPermission，则显示放款按钮 2017.1.3 by SwordLiu -->
				<c:if test="${sc.customerStatus==0 && canLoanPermission == true}">
					<button class="btn green" type="button"
						onclick="showOutLoanArea(this)">
						<i class="icon-plus"></i> 放款
					</button>
				</c:if>
			</div>
		</form>

		<%--
		<!-- 分页 -->
		<jsp:include page="../common/paginator.jsp">
			<jsp:param value="false" name="isShowPageSizeSelect"/>
		</jsp:include>
		 --%>

		<%-- <c:if test="${sc.outLoanCondition=='已放款' }">
			<!-- 修改放款客户信息 -->
			 <%@include file="../outLoanCustomer/updateOutLoanInfo.jsp" %>
		</c:if> --%>
		<%-- 2017.1.7 by SwordLiu 取消放款模板加载限制 <c:if test="${sc.outLoanCondition!='已放款' }"> --%>
		<!-- 添加放款客户信息 -->
		<%@include file="../outLoanCustomer/updateOutLoanInfo.jsp"%>
		<%-- <jsp:include page="../outLoanCustomer/updateOutLoanInfo.jsp"></jsp:include> --%>

		<%-- <%@include file="../outLoanCustomer/addOutLoanInfo.jsp" %> --%>
		<%-- </c:if> --%>
	</div>

	<!--引入签单贷款产品模板 -->
	<jsp:include page="../template/outLoanProductTemplate.jsp" />

	<style type="text/css">
#pagination {
	margin: 0px;
}
</style>

	<script type="text/javascript">
		$('select.chosen')
				.chosen(
						{
							allow_single_deselect : $(this).attr(
									"data-with-diselect") === "1" ? true
									: false
						});

		//放款按钮点击事件---start------------
		function showOutLoanArea(obj) {

			//判断客户的状态,如果是跟进中,则显示放款信息,否则,提示,不能放款
			var v = $('#signCustomerStatusSelect').val();

			if (v > 0) {
				alert('只有客户状态为跟进中,才能填写放款信息!');
			} else {
				/* var count = $('#outLoanCustomerArea .outLoanCustomerTemplate').length;
				if(count>0){
					alert('您有一笔未完成的放款,请先保存或取消!');	
					return;
				} */

				var isShow = $('#outLoanCustomerContainer').is(':hidden');
				if (isShow == true) {
					$('#outLoanCustomerContainer').show();
				}
				outLoanCustomerTemplate(); //添加放款模板

			}
		}//放款按钮点击事件---end------------

		/**
		 * 修改签单客户
		 */
		function updateSignCustomer(obj) {

			//获取当前form对象
			var $form = $(obj).parents('.updateSignCustomerForm');
			//验证表单
			//var errorTipMsg = validateSignCustomerForm($form);
			//判断并给出提示
			//if (errorTipMsg != '') {
			//$('#errorTipModalBody').html(errorTipMsg);
			//$('#errorTipModal').modal('show');
			//return;
			//}
			//$(obj).attr('disabled',true);
			//序列化表单
			var v = serializeSignCustomerForm($form);

			//添加
			$
					.post(
							'${pageContext.request.contextPath}/signCustomer/updateSignCustomer.do',
							v, function(data) {
								if (data == true) {
									alert('修改成功!');
									$(obj).attr("disabled", true).removeClass(
											"blue");
									//aPost('signCustomer/getSignCustomerList.do');
								} else {
									alert('修改失败!');
									//$(obj).attr('disabled',false);
								}
							});
		}

		$(function() {
			//添加辅贷按钮点击事件
			/* $("#addLoaner").click(function(){
				$("#addAssistWarranter").show();
			}) */

			//确认添加按钮点击事件
			$("#commitAddLoaner")
					.click(
							function() {
								var cId = $("#addLoanerWarrantCompanyId").val();
								var dId = $("#addLoanerWarrantDepartmentId")
										.val();
								var eId = $("#addLoanerWarrantEmployeeId")
										.val();
								var sId = "${sc.id}";

								if (cId == "" || dId == "" || eId == "") {
									alert("请选择权证专员");
									return;
								}

								var cName = $(
										"#addLoanerWarrantCompanyId option:selected")
										.text();
								var dName = $(
										"#addLoanerWarrantDepartmentId option:selected")
										.text();
								var eName = $(
										"#addLoanerWarrantEmployeeId option:selected")
										.text();

								$
										.post(
												"${pageContext.request.contextPath}/warrant/addAssistLoaner.do",
												{
													companyId : cId,
													departmentId : dId,
													warranterId : eId,
													signCustomerId : sId
												},
												function(data) {
													if (data) {
														var dom = '<tr class="outLoanBelong">'
														dom += '<td><label class="control-label">权证中心</label></td>'
														dom += '<td><p cid="'+cId+'" class="staticText outLoanBelongCompanyId">'
																+ cName
																+ '</p></td>'
														dom += '<td><label class="control-label">权证部门</label></td>'
														dom += '<td><p did="'+dId+'" class="staticText outLoanBelongDepartmentId">'
																+ dName
																+ '</p></td>'
														dom += '<td><label class="control-label">权证专员</label></td>'
														dom += '<td><p eid="'+eId+'" class="staticText outLoanBelongWarranterId">'
																+ eName
																+ '</p></td>'
														dom += '<td colspan="4"><p style="margin:0px;padding:0px;height:42px;line-height:33px;color: #0362fd;font-size: 14px;"></p></td>'
														dom += '</tr>'
														$(dom)
																.insertAfter(
																		$(
																				"#commitAddLoaner")
																				.parent()
																				.parent());
														//$("#addAssistWarranter").hide();
													} else {
														alert("添加失败，你添加的权证专员重复！")
													}
												});
							});

			//删除辅助贷款人员按钮点击事件
			$("#removeOutLoaner")
					.click(
							function() {
								var $this = $(this);
								if ($this.text() == "删除") {
									var td = '<a class="isRemoveOutLoaner red btn mini"><i class="icon-remove"></i></a>';
									$(".outLoanBelong td:first-child").prepend(
											td);
									$this.text("取消");

									//移除权证专员
									$(".isRemoveOutLoaner")
											.click(
													function() {
														var tr = $(this)
																.parent()
																.parent(
																		".outLoanBelong");
														if (confirm("确定移除？")) {
															var cid = tr
																	.find(
																			".outLoanBelongCompanyId")
																	.attr("cid");
															var did = tr
																	.find(
																			".outLoanBelongDepartmentId")
																	.attr("did");
															var eid = tr
																	.find(
																			".outLoanBelongWarranterId")
																	.attr("eid");
															var sid = "${sc.id}";
															$
																	.post(
																			"${pageContext.request.contextPath}/warrant/removeAssistLoaner.do",
																			{
																				companyId : cid,
																				departmentId : did,
																				warranterId : eid,
																				signCustomerId : sid
																			},
																			function(
																					data) {
																				if (data) {
																					alert("删除成功！")
																					tr
																							.remove();
																				} else {
																					alert("删除失败！")
																				}
																			});
														}
													});

								} else {
									$(".isRemoveOutLoaner").remove();
									$this.text("删除");
								}

							});

			$("#hideDetailInSign").click(function() {
				$("#hideDetail").click();
			});
		})
	</script>
	<script type="text/javascript"
		src="${pageContext.request.contextPath}/js/customer/dateFormHandler.js"></script>
	<script type="text/javascript"
		src="${pageContext.request.contextPath}/js/warrantCdeCascade.js"></script>
</c:if>