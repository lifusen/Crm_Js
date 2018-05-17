<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<link href="${pageContext.request.contextPath}/css/addCustomerForm.css"
	rel="stylesheet" type="text/css" />
<!-- 客户基本信息 start -->
<form id="updateCustomerBasicInfoForm"
	action="${pageContext.request.contextPath}/customer/updateCustomer.do"
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
				<table id="" class="formTable" style="border: 0px solid red;">
					<tr>
						<td><label class="control-label">联系人:</label></td>
						<td><input id="updateCustomerId" type="hidden" name="id"
							value="${customer.id }" /> <input id="customerName" type="text"
							name="name" class="small" data-required="1" style="width: 100px;"
							value="${customer.name}"></td>
						<td style="width: 30px; border: 0px solid red;"><label
							class="control-label">性别</label></td>
						<td style="width: 70px; border: 0px solid red;">
							<div class="controls" style="padding-bottom: 8px;">
								<label class="radio"><input type="radio" name="gender"
									value="男" ${customer.gender=='男'?'checked':''}
									style="margin-bottom: 5px; margin-right: 3px; margin-left: 0px; float: none;" />男
								</label> <label class="radio"><input type="radio" name="gender"
									value="女" ${customer.gender=='女'?'checked':''}
									style="margin-bottom: 5px; margin-right: 3px; margin-left: 0px; float: none;" />女
								</label>
							</div>
						</td>
						<td><label class="control-label">联系方式:<span
								class="required">*</span></label></td>
						<td><input id="customerPhone" type="text" name="phone"
							class="small" style="width: 100px;" value="${customer.phone}"
							${loginRoleId == 999?"":"readonly" } /></td>
						<td><label class="control-label">备注:</label></td>
						<td><input type="text" name="phoneRemark" class="small"
							style="width: 100px;" value="${customer.phoneRemark}">
							<button onclick="addPhoneTemplate(this)"
								class="btn blue addPhone" type="button"
								style="padding: 4px 2px;">
								<i class="icon-plus"></i> 更多
							</button></td>

						<td style="display: none"><label class="control-label">贷款类型<span
								class="required">*</span></label></td>
						<!--
						<td>
							
							如果未签单,则不能选择贷款类型
							如果贷款类型为null,则可以选择贷款类型
							
							实现:
								如果未签单并且有贷款类型,则不能选择贷款类型,只读状态
								如果已签单则可以修改
						-->
						<!--  
						<c:choose>
								<c:when
									test="${customer.signState == '未签单' && (not empty customer.loanType)}">
									<select id="loanTypeSelect" name="loanType"
										class="small m-wrap" readonly>
										<option value="${customer.loanType}">${customer.loanType}</option>
									</select>
								</c:when>
								<c:otherwise>
									<select id="loanTypeSelect" name="loanType"
										class="small m-wrap">
										<option value="" ${customer.loanType==''?'selected':''}></option>
										<option value="信贷" ${customer.loanType=='信贷'?'selected':''}>信贷</option>
										<option value="信用卡" ${customer.loanType=='信用卡'?'selected':''}>信用卡</option>
										<option value="房贷" ${customer.loanType=='房贷'?'selected':''}>房贷</option>
										<option value="二抵" ${customer.loanType=='二抵'?'selected':''}>二抵</option>
										<option value="车贷" ${customer.loanType=='车贷'?'selected':''}>车贷</option>
										<option value="短借" ${customer.loanType=='短借'?'selected':''}>短借</option>
										<option value="企贷" ${customer.loanType=='企贷'?'selected':''}>企贷</option>
									</select>
								</c:otherwise>
							</c:choose>
							

						</td>
						-->
						<td><label class="control-label">来源<span
								class="required">*</span></label></td>
						<td><c:set var="canUpdateCustomerSource"
								value="${loginRoleId == 999 or loginRoleId == 1000}"></c:set> <c:set
								var="updateCustomerSourceId"
								value="${customer.customerSource.id}"></c:set> <select
							name="customerSource.id" class="small m-wrap"
							${canUpdateCustomerSource == true ?"":"readonly" }>
								<c:choose>
									<c:when test="${canUpdateCustomerSource == true}">
										<c:if test="${fn:length(customerSources)>0 }">
											<c:forEach var="cs" items="${customerSources}">
												<option value="${cs.id}"
													${cs.id==updateCustomerSourceId?'selected':''}>${cs.sourceName}</option>
											</c:forEach>
										</c:if>
									</c:when>
									<c:otherwise>
										<option value="${updateCustomerSourceId }">${customerSourceCacheMap[updateCustomerSourceId]}</option>
									</c:otherwise>
								</c:choose>
						</select></td>
						<td><label class="control-label">年龄</label></td>
						<td><input type="text" name="age" class="small" maxlength="2"
							style="width: 40px;" value="${customer.age}" /></td>
					</tr>
					<table style="width: 30%;">
						<tr>
							<td><label class="control-label">公司名称：</label></td>
							<td><input id="lendingInstitution" type="text"
								name="customerAsset" class="small" style="width: 100px;"
								value="${customer.customerAsset }"></td>
							<td><label class="control-label">意向产品：</label></td>
							<td><input type="text" name="loanType" class="small"
								style="width: 100px;" value="${customer.loanType} "></td>
						</tr>
					</table>
				</table>
				<c:if test="${fn:length(customer.contacts)>0}">
					<c:forEach var="c" items="${customer.contacts}">
						<div class="phoneTemplateTable">
							<table>
								<tr>
									<td width="50px"><label class="control-label">联系方式</label>
										<input class="phoneId" type="hidden" value="${c.id }" /></td>
									<td class="phoneNumber"><input type="text" class="small"
										style="width: 100px;" value="${c.phone }" phoneId="${c.id }"
										${isUpdatePhone==false?'readonly':''} /></td>
									<td width="35px"><label class="control-label">备注</label></td>
									<td class="phoneRemark"><input type="text" class="small"
										style="width: 212px;" value="${c.remark }"></td>
									<%--
								<td class="deletePhoneTemplate">
									<button class="btn mini yellow" type="button" style="margin-bottom: 10px;" onclick="deletePhoneTemplate(this)"><i class="icon-remove"></i> 删除</button>
								</td>
								 --%>
								</tr>
							</table>
						</div>
					</c:forEach>
				</c:if>
				<table style="width: 100%;">
					<tr>
						<td width="50px;"><label class="control-label">备注信息</label></td>
						<td style="" colspan="14"><textarea name="otherInfo" rows=""
								style="width: 100%;">${customer.otherInfo }</textarea></td>
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
			<div id="customerAssetArea">
				<c:if test="${fn:length(customer.houses)>0 }">
					<c:forEach var="house" items="${customer.houses }">
						<div class="houseTemplateTable">
							<table class="formTable" style="width: 100%;">
								<tr>
									<th rowspan="2"
										style="width: 14px; padding: 0px 3px; background: #35AA47; color: #fff;"><span
										class="houseName">${house.type==1?'住房':house.type==2?'商铺':'写字楼'}${house.id}</span><span
										class="number"></span></th>
									<th><label class="control-label">位置(区)</label></th>
									<th><label class="control-label">位置(街道)</label></th>
									<th><label class="control-label">位置(详细)</label></th>
									<th><label class="control-label">位置(楼盘)</label></th>
									<th><label class="control-label">国土性质</label></th>
									<th><label class="control-label">建筑年代</label></th>
									<th><label class="control-label">面积</label></th>
									<th><label class="control-label">房产/平均均价</label></th>
									<th><label class="control-label">装修情况</label></th>
									<th><label class="control-label">房屋使用情况</label></th>
									<th><label class="control-label">租金/月</label></th>
									<th><input class="houseId" type="hidden"
										value="${house.id }" /> <input class="houseType"
										type="hidden" value="${house.type }" /></th>
								</tr>
								<tr>
									<td class="placeArea"><input type="text" class="input"
										value="${house.placeArea }" /></td>
									<td class="placeStreet"><input type="text" class="input"
										value="${house.placeStreet }" /></td>
									<td class="placeDetail"><input type="text" class="input"
										value="${house.placeDetail }" /></td>
									<td class="placePremise"><input type="text" class="input"
										value="${house.placePremise }" /></td>
									<td class="landNature"><input type="text" class="input"
										value="${house.landNature }" /></td>
									<td class="constructDate"><input type="text" class="input"
										value="${house.constructDate }" /></td>
									<td class="area"><input type="text" class="input "
										value="${house.area }" /></td>
									<td class="price"><input type="text" class="input "
										value="${house.price }" /></td>
									<td class="decorateCase"><input type="text" class="input"
										value="${house.decorateCase }" /></td>
									<td class="useCase"><input type="text" class="input"
										value="${house.useCase }" /></td>
									<td class="rental"><input type="text" class="input "
										value="${house.rental }" /></td>
									<%--
									<td class="deleteHouseTemplate">
										<button class="btn mini yellow" type="button" style="margin-bottom: 8px;padding:2px 3px;" onclick="deleteHouseTemplate(this)">删除</button>
									</td>
									 --%>
								</tr>
							</table>
						</div>
					</c:forEach>
				</c:if>
				<%-- 土地 --%>
				<c:if test="${fn:length(customer.lands)>0 }">
					<c:forEach var="land" items="${customer.lands }">
						<div class="landTemplateTable">
							<table class="formTable" style="width: 100%;">
								<tr>
									<th
										style="width: 14px; padding: 0px 3px; background: #35AA47; color: #fff;">土地${land.id}</th>
									<td style="width: 50px;"><label class="control-label">备注</label>
										<input class="landId" type="hidden" value="${land.id }" /></td>
									<td class="description"><input type="text" class="input"
										style="width: 1000px;" value="${land.description }"></td>
									<%--
									<td class="deleteLandTemplate">
										<button class="btn mini yellow" type="button" style="margin-bottom: 8px;padding:2px 3px;" onclick="deleteLandTemplate(this)">删除</button>
									</td>
									 --%>
								</tr>
							</table>
						</div>
					</c:forEach>
				</c:if>

				<%-- 汽车 --%>
				<c:if test="${fn:length(customer.cars)>0 }">
					<c:forEach var="car" items="${customer.cars }">
						<div class="carTemplateTable">
							<table class="formTable">
								<tr>
									<th rowspan="2"
										style="width: 14px; padding: 0px 3px; background: #35AA47; color: #fff;">汽车${car.id }</th>
									<th><label class="control-label">品牌</label></th>
									<th><label class="control-label">购入价值</label></th>
									<th><label class="control-label">购入时间</label></th>
									<th><label class="control-label">贷款情况</label></th>
									<th><label class="control-label">月供</label></th>
									<th><label class="control-label">已还款时间</label> <input
										class="carId" type="hidden" value="${car.id }" /></th>
								</tr>
								<tr>
									<td class="brand"><input type="text" class="input "
										value="${car.brand }" /></td>
									<td class="price"><input type="text" class="input "
										value="${car.price }" /></td>
									<td class="buyDate"><input type="text" class="input "
										value="${car.buyDate }" /></td>
									<td class="loanStatus"><input type="text" class="input"
										value="${car.loanStatus }" /></td>
									<td class="refundMonth"><input type="text" class="input "
										value="${car.refundMonth }" /></td>
									<td class="refundDate"><input type="text" class="input"
										value="${car.refundDate }" /></td>
									<%--
									<td class="deleteCarTemplate">
										<button class="btn mini yellow" type="button" style="margin-bottom: 8px;padding:2px 3px;" onclick="deleteCarTemplate(this)">删除</button>
									</td>
									 --%>
								</tr>
							</table>
						</div>
					</c:forEach>
				</c:if>

				<%-- 企业 --%>
				<c:if test="${fn:length(customer.enterprises)>0 }">
					<c:forEach var="enterprise" items="${customer.enterprises }">
						<div class="enterpriseTemplateTable">
							<table class="formTable" style="width: 100%;">
								<tr style="text-align: center;">
									<th rowspan="2"
										style="width: 14px; padding: 0px 3px; background: #35AA47; color: #fff;">企业${enterprise.id }</th>
									<th><label class="control-label">企业类型</label></th>
									<th><label class="control-label">年产值(万)</label></th>
									<th><label class="control-label">流水能否体现</label></th>
									<th><label class="control-label">证照情况</label></th>
									<th><label class="control-label">经营场所</label></th>
									<th><label class="control-label">企业经营范围</label></th>
									<th><label class="control-label">占股比例</label></th>
									<th><label class="control-label">章程能否体现</label></th>
									<th><label class="control-label">经营时间</label></th>
									<th><label class="control-label">承兑汇票</label></th>
									<th><label class="control-label">刷卡客户</label> <input
										class="enterpriseId" type="hidden" value="${enterprise.id }" />
									</th>
									<th></th>
								</tr>
								<tr>
									<td class="type"><select style="width: 90px;">
											<option value=""></option>
											<option value="个体" ${enterprise.type=='个体'?'selected':''}>个体</option>
											<option value="公司" ${enterprise.type=='公司'?'selected':''}>公司</option>
											<option value="其它" ${enterprise.type=='其它'?'selected':''}>其它</option>
									</select></td>
									<td class="annualValue"><select style="width: 90px;">
											<option value=""></option>
											<option value="500以下"
												${enterprise.annualValue=='500以下'?'selected':''}>500以下</option>
											<option value="500-1000"
												${enterprise.annualValue=='500-1000'?'selected':''}>500-1000</option>
											<option value="1000-2000"
												${enterprise.annualValue=='1000-2000'?'selected':''}>1000-2000</option>
											<option value="2000-5000"
												${enterprise.annualValue=='2000-5000'?'selected':''}>2000-5000</option>
											<option value="5000以上"
												${enterprise.annualValue=='5000以上'?'selected':''}>5000以上</option>
									</select></td>
									<td class="showDetail"><select style="width: 90px;">
											<option value=""></option>
											<option value="能" ${enterprise.showDetail=='能'?'selected':''}>能</option>
											<option value="否" ${enterprise.showDetail=='否'?'selected':''}>否</option>
									</select></td>
									<td class="license"><select style="width: 90px;">
											<option value=""></option>
											<option value="齐全" ${enterprise.license=='齐全'?'selected':''}>齐全</option>
											<option value="无" ${enterprise.license=='无'?'selected':''}>无</option>
									</select></td>
									<td class="operationPlace"><select style="width: 90px;">
											<option value=""></option>
											<option value="自有物业"
												${enterprise.operationPlace=='自有物业'?'selected':''}>自有物业</option>
											<option value="租赁物业"
												${enterprise.operationPlace=='租赁物业'?'selected':''}>租赁物业</option>
											<option value="无"
												${enterprise.operationPlace=='无'?'selected':''}>无</option>
									</select></td>
									<td class="operationScope"><select class="small m-wrap"
										style="width: 100px;">
											<option value=""></option>
											<option value="生产加工型"
												${enterprise.operationScope=='生产加工型'?'selected':''}>生产加工型</option>
											<option value="物流贸易型"
												${enterprise.operationScope=='物流贸易型'?'selected':''}>物流贸易型</option>
											<option value="设计研发型"
												${enterprise.operationScope=='设计研发型'?'selected':''}>设计研发型</option>
											<option value="勘探开采型"
												${enterprise.operationScope=='勘探开采型'?'selected':''}>勘探开采型</option>
											<option value="建筑开发型"
												${enterprise.operationScope=='建筑开发型'?'selected':''}>建筑开发型</option>
											<option value="金融证券型"
												${enterprise.operationScope=='金融证券型'?'selected':''}>金融证券型</option>
											<option value="科技型"
												${enterprise.operationScope=='科技型'?'selected':''}>科技型</option>
									</select></td>
									<td class="shareRatio"><select style="width: 90px;">
											<option value=""></option>
											<option value="1" ${enterprise.shareRatio=='1'?'selected':''}>10%以下</option>
											<option value="2" ${enterprise.shareRatio=='2'?'selected':''}>10%-30%</option>
											<option value="3" ${enterprise.shareRatio=='3'?'selected':''}>30%-50%</option>
											<option value="4" ${enterprise.shareRatio=='4'?'selected':''}>50%以上</option>
									</select></td>
									<td class="showStatute"><select style="width: 80px;">
											<option value=""></option>
											<option value="能"
												${enterprise.showStatute=='能'?'selected':''}>能</option>
											<option value="否"
												${enterprise.showStatute=='否'?'selected':''}>否</option>
									</select></td>
									<td class="operationTime"><select style="width: 90px;">
											<option value=""></option>
											<option value="半年以内"
												${enterprise.operationTime=='半年以内'?'selected':''}>半年以内</option>
											<option value="一年以内"
												${enterprise.operationTime=='一年以内'?'selected':''}>一年以内</option>
											<option value="两年以内"
												${enterprise.operationTime=='两年以内'?'selected':''}>两年以内</option>
											<option value="三年以内"
												${enterprise.operationTime=='三年以内'?'selected':''}>三年以内</option>
											<option value="三年以上"
												${enterprise.operationTime=='三年以上'?'selected':''}>三年以上</option>
									</select></td>
									<td class="invoice"><select style="width: 80px;">
											<option value=""></option>
											<option value="是" ${enterprise.invoice=='是'?'selected':''}>是</option>
											<option value="否" ${enterprise.invoice=='否'?'selected':''}>否</option>
									</select></td>
									<td class="cardCustomer"><select style="width: 80px;">
											<option value=""></option>
											<option value="大量"
												${enterprise.cardCustomer=='大量'?'selected':''}>大量</option>
											<option value="少量"
												${enterprise.cardCustomer=='少量'?'selected':''}>少量</option>
											<option value="无"
												${enterprise.cardCustomer=='无'?'selected':''}>无</option>
									</select></td>
									<%--
									<td class="deleteEnterpriseTemplate">
										<button class="btn mini yellow" type="button" style="margin-bottom: 8px;padding:2px 3px;" onclick="deleteEnterpriseTemplate(this)">删除</button>
									</td>
									 --%>
								</tr>
							</table>
						</div>
					</c:forEach>
				</c:if>

				<%-- 信用卡 --%>
				<c:if test="${fn:length(customer.creditCards)>0 }">
					<c:forEach var="creditCard" items="${customer.creditCards }">
						<div class="creditCardTemplateTable">
							<table class="formTable" style="width: 950px;">
								<tr style="text-align: center;">
									<th rowspan="2"
										style="width: 14px; padding: 0px 3px; background: #35AA47; color: #fff;">信用卡${creditCard.id }</th>
									<td><label class="control-label">发卡银行</label> <input
										class="creditCardId" type="hidden" value="${creditCard.id }" />
									</td>
									<td class="bank"><input type="text" class="input"
										value="${creditCard.bank }" /></td>
									<td><label class="control-label">信用额度</label></td>
									<td class="creditLimit"><input type="text" class="input "
										value="${creditCard.creditLimit }" /></td>
									<td><label class="control-label">逾期情况</label></td>
									<td class="overdueCase"><input type="text" class="input"
										value="${creditCard.overdueCase }" /></td>
									<td><label class="control-label">发卡日期</label></td>
									<td class="publishCardDate">
										<div class="input-append date form_datetime"
											data-date-format="yyyy年mm月dd日">
											<input data-provide="datepicker" type="text" class=""
												readonly="readonly" value="${creditCard.publishCardDate }"
												size="12" style="width: 100px;">
										</div>
									</td>
									<td><label class="control-label">卡号</label></td>
									<td class="cardNumber"><input type="text" class="input"
										style="width: 200px;" value="${creditCard.cardNumber }" /></td>
									<%--
									<td class="deleteCreditCardTemplate">
										<button class="btn mini yellow" type="button" style="margin-bottom: 8px;padding:2px 3px;" onclick="deleteCreditCardTemplate(this)">删除</button>
									</td>
									 --%>
								</tr>
							</table>
						</div>
					</c:forEach>
				</c:if>
			</div>
			<div style="text-align: center;">
				<a onclick="addHouseTemplate(this)" class="btn gray"
					href="javascript:;"><i class="icon-plus"></i> 添加住房</a> <a
					onclick="addStoreTemplate(this)" class="btn gray"
					href="javascript:;"><i class="icon-plus"></i> 添加商铺</a> <a
					onclick="addOfficeTemplate(this)" class="btn gray"
					href="javascript:;"><i class="icon-plus"></i> 添加写字楼</a> <a
					onclick="addLandTemplate(this)" class="btn gray"
					href="javascript:;"><i class="icon-plus"></i> 添加土地</a> <a
					onclick="addCarTemplate(this)" class="btn gray" href="javascript:;"><i
					class="icon-plus"></i> 添加汽车</a> <a
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
						<td><select id="monthIncomeSelect" name="monthIncome"
							class="small m-wrap">
								<option value=""></option>
								<option value="20人以内">20人以内</option>
								<option value="20-50人">20-50人</option>
								<option value="50人以上">50人以上</option>
								<option value="50人以上">50-100人</option>
								<option value="50人以上">100人以上</option>
						</select></td>
						<td><label class="control-label">主营业务</label></td>
						<td><input name="requiredMoney" type="text" class="small"
							style="width: 100px;" value="${customer.requiredMoney}" /></td>
						<td><label class="control-label">行业类别</label></td>
						<td><input name="repaymentLimit" type="text" class="small"
							style="width: 100px;" value="${customer.repaymentLimit}" /></td>
						<td><label class="control-label">公司地址</label></td>
						<td>
							<%--
									<div class="input-append date form_datetime"  data-date-format="yyyy年mm月dd日">
										<input name="useDate" data-provide="datepicker" type="text" class="" readonly="readonly" value="" size="12" style="width:100px;">
									</div>
									  --%> <input name="repaymentType" type="text" class="small"
							style="width: 100px;" value="${customer.repaymentType}" />
						</td>
						<td><label class="control-label">单位性质</label></td>
			
						<td><select id="enterpriseNatureSelect"
							name="enterpriseNature" class="small m-wrap">
								<option value=""></option>
								<option value="事业单位"
									${customer.enterpriseNature=='事业单位'?'selected':''}>事业单位</option>
								<option value="公务员"
									${customer.enterpriseNature=='公务员'?'selected':''}>公务员</option>
								<option value="上市公司"
									${customer.enterpriseNature=='上市公司'?'selected':''}>上市公司</option>
								<option value="普通公司"
									${customer.enterpriseNature=='普通公司'?'selected':''}>普通公司</option>
								<option value="特种行业"
									${customer.enterpriseNature=='特种行业'?'selected':''}>特种行业</option>
						</select></td>
						<td><label class="control-label">客户性格</label></td>
						<td colspan="3"><input name="customerCharacter" type="text"
							class="small" style="width: 93%;"
							value="${customer.customerCharacter}" /></td>
					</tr>
					<tr>
						<td style="display: none"><label class="control-label">需求资金</label></td>
						<td style="display: none"><input id="requiredMoneyInput"
							name="requiredMoney" type="text" class="small" maxlength="11"
							style="width: 100px;" value="${customer.requiredMoney}" /></td>

						<td style="display: none"><label class="control-label">用款时间</label></td>
						<td style="display: none">
							<%--
						<div class="input-append date form_datetime"  data-date-format="yyyy年mm月dd日">
							<input name="useDate" data-provide="datepicker" type="text" class="" readonly="readonly" value="<fmt:formatDate value="${customer.useDate}" pattern="yyyy-MM-dd"/>" size="12" style="width:100px;">
						</div>
						 --%> <input id="useDate" name="useDate" size="12"
							style="width: 100px;" type="text"
							value="<fmt:formatDate value="${customer.useDate}" pattern="yyyy-MM-dd"/>"
							readonly>
						</td>
						<td style="display: none"><label class="control-label">推荐贷款机构</label></td>
						<td style="display: none"><select
							id="lendingInstitutionSelect" name="lendingInstitution"
							class="small m-wrap">
								<option value=""
									${customer.lendingInstitution==null||customer.lendingInstitution==''?'selected':'' }></option>
								<option value="银行"
									${customer.lendingInstitution=='银行'?'selected':''}>银行</option>
								<option value="小贷"
									${customer.lendingInstitution=='小贷'?'selected':''}>小贷</option>
								<option value="私人"
									${customer.lendingInstitution=='私人'?'selected':''}>私人</option>
						</select></td>
						<td style="display: none"><label class="control-label">户籍</label></td>
						<td style="display: none"><select name="census"
							class="small m-wrap">
								<option value=""></option>
								<option value="大成都范围内"
									${customer.census=='大成都范围内'?'selected':''}>大成都范围内</option>
								<option value="川内" ${customer.census=='川内'?'selected':''}>川内</option>
								<option value="全国" ${customer.census=='全国'?'selected':''}>全国</option>
								<option value="沿海" ${customer.census=='沿海'?'selected':''}>沿海</option>
						</select></td>
						<td style="display: none"><label class="control-label">婚姻</label></td>
						<td style="display: none"><select id="marriageSelect"
							name="marriage" class="small m-wrap">
								<option value=""></option>
								<option value="已婚" ${customer.marriage=='已婚'?'selected':''}>已婚</option>
								<option value="至今未婚" ${customer.marriage=='至今未婚'?'selected':''}>至今未婚</option>
								<option value="离异未再婚"
									${customer.marriage=='离异未再婚'?'selected':''}>离异未再婚</option>
								<option value="丧偶未再婚"
									${customer.marriage=='丧偶未再婚'?'selected':''}>丧偶未再婚</option>
						</select></td>
					</tr>
					<tr style="display: none">
						<td style="display: none"><label class="control-label">债权到期时间</label></td>
						<td>
							<%--
						<div class="input-append date form_datetime">
							<input name="bondExpireDate" data-provide="datepicker" type="text" class="" readonly="readonly" value="<fmt:formatDate value="${customer.bondExpireDate}" pattern="yyyy-MM-dd"/>" size="12" style="width:100px;">
						</div>
						 --%> <input id="bondExpireDate" name="bondExpireDate" size="12"
							style="width: 100px;" type="text"
							value="${customer.bondExpireDate}" readonly>
						</td>
						<td><label class="control-label">还款方式</label></td>
						<td><select name="repaymentType" class="small m-wrap">
								<option value=""></option>
								<option value="先息后本"
									${customer.repaymentType=='先息后本'?'selected':'' }>先息后本</option>
								<option value="等额本息"
									${customer.repaymentType=='等额本息'?'selected':'' }>等额本息</option>
								<option value="等额本金"
									${customer.repaymentType=='等额本金'?'selected':'' }>等额本金</option>
								<option value="不规则还款"
									${customer.repaymentType=='不规则还款'?'selected':'' }>不规则还款</option>
								<option value="其他"
									${customer.repaymentType=='其他'?'selected':'' }>其他</option>
						</select></td>
						<td><label class="control-label">用途</label></td>
						<td colspan="3"><input name="fundUse" type="text"
							class="small" style="width: 93%;" value="${customer.fundUse}" />
						</td>
					</tr>
					<tr style="display: none">
						<td><label class="control-label">债权构造</label></td>
						<td><input type="hidden" name="amountliabilitys[0].id"
							value="${customer.amountliabilitys[0].id }" /> <select
							name="amountliabilitys[0].creditor" data-placeholder=" "
							class="small m-wrap lendingInstitutionSelect"
							style="width: 114px;">
								<option value="">&nbsp;</option>
								<option
									value="${sessionScope.lendingInstitutionMap.personal[0].id}"
									${sessionScope.lendingInstitutionMap.personal[0].id==customer.amountliabilitys[0].creditor?'selected':''}>私人</option>
								<optgroup label="银行">
									<c:forEach var="lendingInstitution"
										items="${sessionScope.lendingInstitutionMap.bank }">
										<option value="${lendingInstitution.id }"
											${lendingInstitution.id==customer.amountliabilitys[0].creditor?'selected':'' }>${lendingInstitution.name }</option>
									</c:forEach>
								</optgroup>
								<optgroup label="小贷">
									<c:forEach var="lendingInstitution"
										items="${sessionScope.lendingInstitutionMap.smallLand }">
										<option value="${lendingInstitution.id }"
											${lendingInstitution.id==customer.amountliabilitys[0].creditor?'selected':'' }>${lendingInstitution.name }</option>
									</c:forEach>
								</optgroup>
						</select></td>
						<td><label class="control-label">负债金额</label></td>
						<td><input type="text" name="amountliabilitys[0].debtMoney"
							value="<fmt:formatNumber pattern="#.#" maxFractionDigits="2" value="${customer.amountliabilitys[0].debtMoney }"/>"
							class="small " style="width: 100px;" /></td>
						<td>
							<button onclick="addAmountliabilityTemplate(this)" type="button"
								class="btn blue" style="padding: 4px 2px;">更多</button>
						</td>
						<td colspan="2"><span style="color: #555;">预估负债总金额</span> <input
							name="debtTotal" type="text" class="small " style="width: 100px;"
							value="<fmt:formatNumber pattern="#.#" maxFractionDigits="2" value="${customer.debtTotal}"/>"
							readonly="readonly" /></td>
						<td colspan="6"><span style="color: #555;">推荐人</span> <input
							name="" type="text" class="small" style="width: 100px;"
							readonly="readonly"></td>
					</tr>
					<c:if test="${fn:length(customer.amountliabilitys)>0 }">
						<c:forEach var="amountliability"
							items="${customer.amountliabilitys }" varStatus="vs">
							<c:if test="${vs.count>1 }">
								<tr class="amountliabilityTemplateTr">
									<td><label class="control-label">债权构造</label> <input
										type="hidden" class="amountliabilityId"
										value="${amountliability.id }" /></td>
									<td class="creditor"><select data-placeholder=" "
										class="small m-wrap lendingInstitutionSelect"
										style="width: 114px;">
											<option value=""></option>
											<option
												value="${sessionScope.lendingInstitutionMap.personal[0].id}"
												${sessionScope.lendingInstitutionMap.personal[0].id==amountliability.creditor?'selected':''}>私人</option>
											<optgroup label="银行">
												<c:forEach var="lendingInstitution"
													items="${sessionScope.lendingInstitutionMap.bank }">
													<option value="${lendingInstitution.id }"
														${lendingInstitution.id==amountliability.creditor?'selected':'' }>${lendingInstitution.name }</option>
												</c:forEach>
											</optgroup>
											<optgroup label="小贷">
												<c:forEach var="lendingInstitution"
													items="${sessionScope.lendingInstitutionMap.smallLand }">
													<option value="${lendingInstitution.id }"
														${lendingInstitution.id==amountliability.creditor?'selected':'' }>${lendingInstitution.name }</option>
												</c:forEach>
											</optgroup>
									</select></td>
									<td><label class="control-label">负债金额</label></td>
									<td class="debtMoney"><input type="text" class="small"
										maxlength="11" style="width: 100px;"
										value="<fmt:formatNumber pattern="#.#" maxFractionDigits="2" value="${amountliability.debtMoney }"/>">
									</td>
									<td class="deleteAmountliabilityTemplate">
										<button class="btn mini yellow" type="button"
											style="margin-bottom: 8px; padding: 2px 3px;"
											onclick="deleteAmountliabilityTemplate(this)">删除</button>
									</td>
								</tr>
							</c:if>
						</c:forEach>
					</c:if>
				</table>

				<div class="form-actions"
					style="padding: 8px; text-align: center; margin-top: 5px; margin-bottom: 10px;">
					<c:if test="${noShowSaveButton ne true}">

						<button id="saveCustomerBacicInfoBtn" class="btn blue"
							type="button">
							<i class="icon-ok"></i> 保存
						</button>

					</c:if>

					<%-- 				<a class="btn aPost" href="${refererUrl}"><i class="icon-remove"></i> 取消</a> --%>

					<c:choose>
						<c:when test="${closeWindow == true }">
							<a class="btn" href="javascript:void(0);" onclick="closeWindow()"><i
								class="icon-remove"></i> 关闭此页面</a>
						</c:when>
						<c:when test="${commonPool==true }">
							<a id="noNeedBtn" class="btn" href="javascript:void(0);"><i
								class="icon-remove"></i> 暂时不需要</a>
						</c:when>
						<c:when test="${refererUrl != null }">
							<a class="btn aPost" href="${refererUrl}"><i
								class="icon-remove"></i> 取消</a>
						</c:when>
						<c:when test="${refererUrl == null }">
							<a class="btn" href="javascript:void(0);" id="hideDetail"><i
								class="icon-remove"></i> 取消</a>
						</c:when>
					</c:choose>

					<input type="hidden" id="refererUrl" value="${refererUrl }">
					<input type="hidden" id="menuAttrId" value="${menuAttrId }">
					<!-- 判断需要显示的按钮 -->
					<c:choose>
						<c:when test="${showButton==null||showButton==''}">
							<!-- Do Nothing -->
						</c:when>
						<c:when test="${showButton=='sign'}">
							<button id="customerSignBtn" class="btn green" type="button">
								<i class="icon-ok"></i> 签单
							</button>
						</c:when>
						<c:when test="${showButton=='receive'}">
							<c:if test="${sessionScope.loginUser.role.id != 3 }">
								<button id="receiveBtn" class="btn green" type="button">
									<i class="icon-ok"></i> 领单
								</button>
							</c:if>
						</c:when>
					</c:choose>
				</div>
			</div>
		</div>
	</div>
</form>
<!-- 辅助信息 end-->

<script type="text/javascript">
	console.log("修改客户列表(updateBasicInfo.jsp)");
	console.log("currentListDomId:${currentListDomId}");

	//领单
	$(function() {
		$("#receiveBtn").click(function() {
			var cId = $("#updateCustomerId").val();
			$.post("customer/receive.do", {
				id : cId
			}, function(data) {
				if (data.flag) {
					// 				aPost("customer/getValidCustomerList.do");
					aPost('${refererUrl}');
				} else {
					alert(data.msg);
				}
			});
		});
	});

	$(function() {
		$('#noNeedBtn').click(function() {
			var id = '${updateCustomerId}';
			$.post('customer/updateLastTime.do', {
				id : id
			}, function(data) {
				aPost('${refererUrl}');
			});
		});

	});

	//修改客户
	$(function() {

		<c:if test="${managerRole==true}">
		//客户的手机号码
		$('#customerPhone').blur(function() {
			var oldPhone = '${customer.phone}';
			var v = $.trim($(this).val());
			if (oldPhone == v) {
				return true;
			}
			//console.log(v);
			var errorTipMsg = validateCustomerPhone(v);
			if (errorTipMsg != '') {
				$('#errorTipModalBody').html(errorTipMsg);
				$('#errorTipModal').modal('show');
			}
		});
		</c:if>

		//保存按钮
		$('#saveCustomerBacicInfoBtn').click(function() {//alert("update--->>>>");
			var errorTipMsg = "";
			var phoneValue = $.trim($('#customerPhone').val());
			//表单验证
			<c:if test="${managerRole==true}">
			var oldPhone = '${customer.phone}';
			if (oldPhone != phoneValue) {
				errorTipMsg += validateCustomerPhone(phoneValue);
			}
			</c:if>

			//验证客户来源
			if ($("select[name='customerSource.id']").val() == '') {
				errorTipMsg += '请选择客户来源!<br/>';
			}

			if (errorTipMsg == '') {
				//验证当前页面是否有重复号码
				errorTipMsg += validateRepeatAndContactsPhone(phoneValue);
			}
			//获取到表单对象
			var form = $('#updateCustomerBasicInfoForm');

			if (errorTipMsg == '') {
				//验证表单
				errorTipMsg += validateCustomerBasicInfo(form);
			}

			//添加客户的类型,如果是业务员添加,则验证必填项
			var updateCustomerType = "${param.updateType}";
			//alert(updateCustomerType);
			if (updateCustomerType != "1") {
				//验证必填项
				errorTipMsg += ValidateCustomer.basicSave();
			}

			//判断并给出提示
			if (errorTipMsg != '') {
				$('#errorTipModalBody').html(errorTipMsg);
				$('#errorTipModal').modal('show');
				return;
			}

			//序列化表单
			var v = serializeCustomerBasicInfoForm(form);

			//修改
			$.post(form.attr('action'), v, function(data) {
				alert(data)
				if (data == 0) {
					$('#customerPhone').attr('disabled', true);
					$('#saveCustomerBacicInfoBtn').attr('disabled', true);
					alert('修改成功!');
					//aPost($('#refererUrl').val());
				} else if (data == 1) {
					alert('修改失败!');
					//$(this).attr('disabled',false);
				} else if (data == 2) {
					alert('该客户已被删除!');
					//$(this).attr('disabled',false);
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

		$("#hideDetail").click(
				function() {
					console.log("修改客户列表取消按钮...");
					$("#validCustomerListDiv").show();
					$("#validCustomerDetail").empty();
					$('#customerDiv').load(
							$("#nowUrl").val() + "&pageIndex="
									+ $("#nowPageIndex").val() + "&pageSize="
									+ $("#nowPageSize").val()
									+ "&srarchDefault=1");
				});
	});
</script>

<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/customer/dateFormHandler.js"></script>
