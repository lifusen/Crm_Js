<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://itproject.cn/phone" prefix="phone"%>
<link href="${pageContext.request.contextPath}/css/addCustomerForm.css" rel="stylesheet" type="text/css" />
<!-- 客户基本信息 start -->
<form id="updateCustomerBasicInfoForm" action="${pageContext.request.contextPath}/customer/updateCustomer.do" method="post">
<div class="portlet box blue">
	<div class="portlet-title">
		<div class="caption"><i class="icon-cogs"></i>基础信息</div>
		<div class="tools">
			<a class="collapse" href="javascript:;"></a>
		</div>
	</div>
	<div class="portlet-body">
		<div id="customerBasicInfoArea">
			<table id="" class="formTable">
				<tr>
					<td><label class="control-label">客户姓名<span class="required">*</span></label></td>
					<td>
						<input id="updateCustomerId" type="hidden" name="id" value="${customer.id }"/>
						<input id="customerName" type="text" name="name" class="small" data-required="1" style="width:100px;" value="${customer.name}">
					</td>
					<td><label class="control-label">性别<span class="required">*</span></label></td>
					<td style="padding-top:0px;padding-bottom:0px;line-height:0px;">
						<div class="controls">
							<label class="radio"><input type="radio" name="gender" value="男" ${customer.gender=='男'?'checked':''}/>男 </label>
							<label class="radio"><input type="radio" name="gender" value="女" ${customer.gender=='女'?'checked':''}/>女 </label>  
						</div>
					</td>
					<td><label class="control-label">联系方式<span class="required">*</span></label></td>
					<td>
						<input id="customerPhone" type="text" name="phone" class="small" style="width:100px;" value="${customer.phone}">
					</td>
					<td><label class="control-label">备注</label></td>
					<td>
						<input type="text" name="phoneRemark" class="small" style="width:100px;" value="${customer.phoneRemark}">
						<button id="addPhoneBtn" class="btn blue addPhone" type="button" style="padding:4px 2px;"><i class="icon-plus"></i> 更多</button>
					</td>
					<td><label class="control-label">贷款类型<span class="required">*</span></label></td>
					<td>
					
						<c:choose>
							<c:when test="${customer.signState == '未签单' && (not empty customer.loanType)}">
								<select id="loanTypeSelect" name="loanType" class="small m-wrap" readonly>
									<option value="${customer.loanType}" >${customer.loanType}</option>
								</select>
							</c:when>
							<c:otherwise>
								<select id="loanTypeSelect" name="loanType" class="small m-wrap" >
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
					<td><label class="control-label">来源</label></td>
					<td>
						<select name="customerSource.id" class="small m-wrap">
							<option ${customer.customerSource.id==1?'selected':''}></option>
							<option value="2" ${customer.customerSource.id==2?'selected':''}>中介</option>
							<option value="3" ${customer.customerSource.id==3?'selected':''}>续贷客户</option>
							<option value="4" ${customer.customerSource.id==4?'selected':''}>口碑客户</option>
							<option value="5" ${customer.customerSource.id==5?'selected':''}>权证客户</option>
							<option value="6" ${customer.customerSource.id==6?'selected':''}>名单</option>
							<option value="7" ${customer.customerSource.id==7?'selected':''}>网络1</option>
							<option value="8" ${customer.customerSource.id==8?'selected':''}>网络2</option>
							<option value="9" ${customer.customerSource.id==9?'selected':''}>网络3</option>
							<option value="10" ${customer.customerSource.id==10?'selected':''}>网络4</option>
							<option value="11" ${customer.customerSource.id==11?'selected':''}>网络5</option>
						</select>
					</td>
					<td><label class="control-label">年龄</label></td>
					<td>
						<input type="text" name="age" class="small inputmask-integer-2" style="width:40px;" value="${customer.age}"/>
					</td>
				</tr>
			</table>
			<c:if test="${fn:length(customer.contacts)>0}">
				<c:forEach var="c" items="${customer.contacts}">
					<div class="phoneTemplateTable">
						<table>
							<tr>
								<td width="60px"><label class="control-label">联系方式</label>
									<input class="phoneId" type="hidden" value="${c.id }"/>
								</td>
								<td class="phoneNumber">
									<input type="text" class="small" style="width:100px;" value="${phone:phone(c.phone)}"/>
								</td>
								<td width="35px"><label class="control-label">备注</label></td>
								<td class="phoneRemark">
									<input type="text" class="small" style="width:212px;" value="${c.remark }">
								</td>
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
			
		</div>
	</div>
</div>
<!-- 客户基本信息 end -->


<!-- 客户资产 start-->
<div class="portlet box green">
	<div class="portlet-title">
		<div class="caption"><i class="icon-cogs"></i>客户资产</div>
		<div class="tools">
			<a class="collapse" href="javascript:;"></a>
		</div>
	</div>
	<div class="portlet-body">
			<div id="customerAssetArea">
				<c:if test="${fn:length(customer.houses)>0 }">
					<c:forEach var="house" items="${customer.houses }">
						<div class="houseTemplateTable">
							<table class="formTable" style="width:100%;">
								<tr>
									<th rowspan="2" style="width:20px;background: #35AA47; color: #fff;"><span class="houseName">${house.type==1?'住房':house.type==2?'商铺':'写字楼'}</span><span class="number"></span></th>
									<th><label class="control-label">住房位置(区)</label></th>
									<th><label class="control-label">住房位置(街道)</label></th>
									<th><label class="control-label">住房位置(详细)</label></th>
									<th><label class="control-label">住房位置(楼盘)</label></th>
									<th><label class="control-label">国土性质</label></th>
									<th><label class="control-label">建筑年代</label></th>
									<th><label class="control-label">面积</label></th>
									<th><label class="control-label">房产/平均均价</label></th>
									<th><label class="control-label">装修情况</label></th>
									<th><label class="control-label">房屋使用情况</label></th>
									<th><label class="control-label">租金/月</label></th>
									<th>
										<input class="houseId" type="hidden" value="${house.id }"/>
										<input class="houseType" type="hidden" value="${house.type }"/>
									</th>
								</tr>
								<tr>
									<td class="placeArea">
										<input type="text" class="input" value="${house.placeArea }"/> 
									</td>
									<td class="placeStreet">
										<input type="text" class="input" value="${house.placeStreet }"/>
									</td>
									<td class="placeDetail">
										<input type="text" class="input" value="${house.placeDetail }"/>
									</td>
									<td class="placePremise">
										<input type="text" class="input" value="${house.placePremise }"/>
									</td>
									<td class="landNature">
										<input type="text" class="input" value="${house.landNature }"/>
									</td>
									<td class="constructDate">
										<input type="text"  class="input" value="${house.constructDate }"/>
									</td>
									<td class="area">
										<input type="text"  class="input inputmask-decimal" value="${house.area }"/>
									</td>
									<td class="price">
										<input type="text"  class="input inputmask-decimal" value="${house.price }"/>
									</td>
									<td class="decorateCase">
										<input type="text"  class="input" value="${house.decorateCase }"/>
									</td>
									<td class="useCase">
										<input type="text"  class="input" value="${house.useCase }"/>
									</td>
									<td class="rental">
										<input type="text"  class="input inputmask-decimal" value="${house.rental }"/>
									</td>
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
							<table class="formTable" style="width:100%;">
								<tr>
									<th style="width:20px;background: #35AA47; color: #fff;">土地<span class="number"></span></th>
									<td style="width:50px;"><label class="control-label">备注</label>
										<input class="landId" type="hidden" value="${land.id }"/>
									</td>
									<td class="description">
										<input type="text"  class="input" style="width:1000px;" value="${land.description }"> 
									</td>
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
									<th rowspan="2" style="width:20px;background: #35AA47; color: #fff;">汽车<span class="number"></span></th>
									<th><label class="control-label">购入价值</label></th>
									<th><label class="control-label">购入时间</label></th>
									<th><label class="control-label">贷款情况</label></th>
									<th><label class="control-label">月供</label></th>
									<th><label class="control-label">已还款时间</label>
										<input class="carId" type="hidden" value="${car.id }"/>
									</th>
								</tr>
								<tr>
									<td class="price">
										<input type="text"  class="input inputmask-decimal" value="${car.price }"/> 
									</td>
									<td class="buyDate">
										<div class="input-append date form_datetime">
											<input data-provide="datepicker" type="text" class="" readonly="readonly" value="${car.buyDate }" size="12" style="width:100px;">
										</div> 
									</td>
									<td class="loanStatus">
										<input type="text"  class="input" value="${car.loanStatus }"/> 
									</td>
									<td class="refundMonth">
										<input type="text"  class="input inputmask-decimal" value="${car.refundMonth }"/>
									</td>
									<td class="refundDate">
										<div class="input-append date form_datetime"  data-date-format="yyyy年mm月dd日">
											<input data-provide="datepicker" type="text" class="" readonly="readonly" value="${car.refundDate }" size="12" style="width:100px;">
										</div>  
									</td>
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
							<table class="formTable" style="width:100%;">
								<tr style="text-align:center;">
									<th rowspan="2" style="width:20px;background: #35AA47; color: #fff;">企业<span class="number"></span></th>
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
									<th><label class="control-label">刷卡客户</label>
										<input class="enterpriseId" type="hidden" value="${enterprise.id }"/>
									</th>
									<th></th>
								</tr>
								<tr>
									<td class="type">
										<input type="text"  class="input" value="${enterprise.type }"/> 
									</td>
									<td class="annualValue">
										<input type="text"  class="input inputmask-decimal" value="${enterprise.annualValue }"/> 
									</td>
									<td class="showDetail">
										<input type="text"  class="input" value="${enterprise.showDetail }"/>
									</td>
									<td class="license">
										<input type="text"  class="input" value="${enterprise.license }"/> 
									</td>
									<td class="operationPlace">
										<input type="text"  class="input" value="${enterprise.operationPlace }"/>
									</td>
									<td class="operationScope">
										<select class="small m-wrap" style="width:100px;">
											<option></option>
											<option value="生产加工型" ${enterprise.operationScope=='生产加工型'?'selected':'' }>生产加工型</option>
											<option value="物流贸易型" ${enterprise.operationScope=='物流贸易型'?'selected':'' }>物流贸易型</option>
											<option value="设计研发型" ${enterprise.operationScope=='设计研发型'?'selected':'' }>设计研发型</option>
											<option value="勘探开采型" ${enterprise.operationScope=='勘探开采型'?'selected':'' }>勘探开采型</option>
											<option value="建筑开发型" ${enterprise.operationScope=='建筑开发型'?'selected':'' }>建筑开发型</option>
											<option value="金融证券型" ${enterprise.operationScope=='金融证券型'?'selected':'' }>金融证券型</option>
											<option value="科技型" ${enterprise.operationScope=='科技型'?'selected':'' }>科技型</option>
										</select>
									</td>
									<td class="shareRatio">
										<input type="text"  class="input" value="${enterprise.shareRatio }"/>
									</td>
									<td class="showStatute">
										<input type="text"  class="input" value="${enterprise.showStatute }"/>
									</td>
									<td class="operationTime">
										<input type="text"  class="input" value="${enterprise.operationTime }"/>
									</td>
									<td class="invoice">
										<input type="text"  class="input" value="${enterprise.invoice }"/>
									</td>
									<td class="cardCustomer">
										<input type="text"  class="input" value="${enterprise.cardCustomer }"/>
									</td>
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
							<table class="formTable" style="width:950px;">
								<tr style="text-align:center;">
									<th rowspan="2" style="width:20px;background: #35AA47; color: #fff;">信用卡<span class="number"></span></th>
									<td><label class="control-label">发卡银行</label>
										<input class="creditCardId" type="hidden" value="${creditCard.id }"/>
									</td>
									<td class="bank">
										<input type="text"  class="input" value="${creditCard.bank }"/> 
									</td>
									<td><label class="control-label">信用额度</label></td>
									<td class="creditLimit">
										<input type="text"  class="input inputmask-decimal" value="${creditCard.creditLimit }"/>
									</td>
									<td><label class="control-label">逾期情况</label></td>
									<td class="overdueCase">
										<input type="text"  class="input" value="${creditCard.overdueCase }"/>
									</td>
									<td><label class="control-label">发卡日期</label></td>
									<td class="publishCardDate">
										<div class="input-append date form_datetime"  data-date-format="yyyy年mm月dd日">
											<input data-provide="datepicker" type="text" class="" readonly="readonly" value="${creditCard.publishCardDate }" size="12" style="width:100px;">
										</div> 
									</td>
									<td><label class="control-label">卡号</label></td>
									<td class="cardNumber">
										<input type="text"  class="input" style="width:200px;" value="${creditCard.cardNumber }"/>
									</td>
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
			<div style="text-align:center;">
				<a id="addHouseBtn" class="btn gray" href="javascript:;"><i class="icon-plus"></i> 添加住房</a>
				<a id="addStoreBtn" class="btn gray" href="javascript:;"><i class="icon-plus"></i> 添加商铺</a>
				<a id="addOfficeBtn" class="btn gray" href="javascript:;"><i class="icon-plus"></i> 添加写字楼</a>
				<a id="addLandBtn" class="btn gray" href="javascript:;"><i class="icon-plus"></i> 添加土地</a>
				<a id="addCarBtn" class="btn gray" href="javascript:;"><i class="icon-plus"></i> 添加汽车</a>
				<a id="addEnterpriseBtn" class="btn gray" href="javascript:;"><i class="icon-plus"></i> 添加企业</a>
				<a id="addCreditCardBtn" class="btn gray" href="javascript:;"><i class="icon-plus"></i> 添加信用卡</a>
			</div>
	</div>
</div>
<!-- 客户资产 end-->


<!-- 辅助信息 start -->
<div class="portlet box blue">
	<div class="portlet-title">
		<div class="caption"><i class="icon-cogs"></i>辅助信息</div>
		<div class="tools">
			<a class="collapse" href="javascript:;"></a>
		</div>
	</div>
	<div class="portlet-body">
		<div id="customerAuxiliaryInfoArea">
			<table class="formTable" style="width:100%;">
				<tr>
					<td><label class="control-label">月收入</label></td>
					<td>
						<select id="monthIncomeSelect" name="monthIncome" class="small m-wrap">
							<option value=""></option>
							<option value="3000以下" ${customer.monthIncome=='3000以下'?'selected':''}>3000以下</option>
							<option value="3000-5000" ${customer.monthIncome=='3000-5000'?'selected':''}>3000-5000</option>
							<option value="5000-8000" ${customer.monthIncome=='5000-8000'?'selected':''}>5000-8000</option>
							<option value="8000-15000" ${customer.monthIncome=='8000-15000'?'selected':''}>8000-15000</option>
							<option value="15000-20000" ${customer.monthIncome=='15000-20000'?'selected':''}>15000-20000</option>
							<option value="20000以上" ${customer.monthIncome=='20000以上'?'selected':''}>20000以上</option>
						</select>
					</td>
					<td><label class="control-label">体现</label></td>
					<td>
						<select id="embodimentSelect" name="embodiment" class="small m-wrap">
							<option value=""></option>
							<option value="现金" ${customer.embodiment=='现金'?'selected':''}>现金</option>
							<option value="打卡" ${customer.embodiment=='打卡'?'selected':''}>打卡</option>
							<option value="自由收入" ${customer.embodiment=='自由收入'?'selected':''}>自由收入</option>
						</select>
					</td>
					<td><label class="control-label">社保</label></td>
					<td>
						<select id="socialInsuranceSelect" name="socialInsurance" class="small m-wrap">
							<option value=""></option>
							<option value="无" ${customer.socialInsurance=='无'?'selected':''}>无</option>
							<option value="公司购买一年内" ${customer.socialInsurance=='公司购买一年内'?'selected':''}>公司购买一年内</option>
							<option value="公司购买一年以上" ${customer.socialInsurance=='公司购买一年以上'?'selected':''}>公司购买一年以上</option>
							<option value="自己购买" ${customer.socialInsurance=='自己购买'?'selected':''}>自己购买</option>
						</select>
					</td>
					<td><label class="control-label">征信</label></td>
					<td>
						<select id="creditSelect" name="credit" class="small m-wrap">
							<option value=""></option>
							<option value="无记录" ${customer.credit=='无记录'?'selected':''}>无记录</option>
							<option value="信用良好" ${customer.credit=='信用良好'?'selected':''}>信用良好</option>
							<option value="两年内少数逾期" ${customer.credit=='两年内少数逾期'?'selected':''}>两年内少数逾期</option>
							<option value="五年内少数逾期" ${customer.credit=='五年内少数逾期'?'selected':''}>五年内少数逾期</option>
							<option value="两年内逾期严重" ${customer.credit=='两年内逾期严重'?'selected':''}>两年内逾期严重</option>
							<option value="五年内逾期严重" ${customer.credit=='五年内逾期严重'?'selected':''}>五年内逾期严重</option>
						</select>
					</td>
					<td><label class="control-label">单位性质</label></td>
					<td>
						<select id="enterpriseNatureSelect" name="enterpriseNature" class="small m-wrap">
							<option value=""></option>
							<option value="事业单位" ${customer.enterpriseNature=='事业单位'?'selected':''}>事业单位</option>
							<option value="公务员" ${customer.enterpriseNature=='公务员'?'selected':''}>公务员</option>
							<option value="上市公司" ${customer.enterpriseNature=='上市公司'?'selected':''}>上市公司</option>
							<option value="普通公司" ${customer.enterpriseNature=='普通公司'?'selected':''}>普通公司</option>
							<option value="特种行业" ${customer.enterpriseNature=='特种行业'?'selected':''}>特种行业</option>
						</select>
					</td>
					<td><label class="control-label">工作时间</label></td>
					<td>
						<select id="workYearSelect" name="workYear" class="small m-wrap">
							<option value=""></option>
							<option value="半年以内" ${customer.workYear=='半年以内'?'selected':''}>半年以内</option>
							<option value="一年以内" ${customer.workYear=='一年以内'?'selected':''}>一年以内</option>
							<option value="一年以上" ${customer.workYear=='一年以上'?'selected':''}>一年以上</option>
						</select>
					</td>
				</tr>
				<tr>
					<td><label class="control-label">需求资金</label></td>
					<td>
						<input id="requiredMoneyInput" name="requiredMoney" type="text" class="small inputmask-decimal" style="width:100px;" value="${customer.requiredMoney}"/>
					</td>
					<td><label class="control-label">还款年限</label></td>
					<td>
						<select name="repaymentLimit" class="small m-wrap">
							<option value=""></option>
							<option value="1年" ${customer.repaymentLimit=='1年'?'selected':''}>1年</option>
							<option value="2-4年" ${customer.repaymentLimit=='2-4年'?'selected':''}>2-4年</option>
							<option value="4-8年" ${customer.repaymentLimit=='4-8年'?'selected':''}>4-8年</option>
						</select>
					</td>
					<td><label class="control-label">用款时间</label></td>
					<td>
						<%--
						<div class="input-append date form_datetime"  data-date-format="yyyy年mm月dd日">
							<input name="useDate" data-provide="datepicker" type="text" class="" readonly="readonly" value="${customer.useDate}" size="12" style="width:100px;">
						</div>
						 --%>
						 <input id="useDate" name="useDate" size="12" style="width:100px;" type="text" value="${customer.useDate}" readonly>
					</td>
					<td><label class="control-label">推荐贷款机构</label></td>
					<td>
						<select id="lendingInstitutionSelect" name="lendingInstitution.id" data-placeholder=" " class="chosen small m-wrap lendingInstitutionSelect">
							<option value=""></option>
							<optgroup label="银行">
							<c:forEach var="lendingInstitution" items="${sessionScope.lendingInstitutionMap.bank }">
								<option value="${lendingInstitution.id }" ${lendingInstitution.id==customer.lendingInstitution.id?'selected':'' }>${lendingInstitution.name }</option>
							</c:forEach>
							</optgroup>
							<optgroup label="小贷">
								<c:forEach var="lendingInstitution" items="${sessionScope.lendingInstitutionMap.smallLand }">
									<option value="${lendingInstitution.id }" ${lendingInstitution.id==customer.lendingInstitution.id?'selected':'' }>${lendingInstitution.name }</option>
								</c:forEach>
							</optgroup>
						</select>
					</td>
					<td><label class="control-label">户籍</label></td>
					<td>
						<select name="census" class="small m-wrap">
							<option value=""></option>
							<option value="大成都范围内" ${customer.census=='大成都范围内'?'selected':''}>大成都范围内</option>
							<option value="川内" ${customer.census=='川内'?'selected':''}>川内</option>
							<option value="全国" ${customer.census=='全国'?'selected':''}>全国</option>
							<option value="沿海" ${customer.census=='沿海'?'selected':''}>沿海</option>
						</select>
					</td>
					<td><label class="control-label">婚姻</label></td>
					<td>
						<select id="marriageSelect" name="marriage" class="small m-wrap">
							<option value=""></option>
							<option value="已婚" ${customer.marriage=='已婚'?'selected':''}>已婚</option>
							<option value="至今未婚" ${customer.marriage=='至今未婚'?'selected':''}>至今未婚</option>
							<option value="离异未再婚" ${customer.marriage=='离异未再婚'?'selected':''}>离异未再婚</option>
							<option value="丧偶未再婚" ${customer.marriage=='丧偶未再婚'?'selected':''}>丧偶未再婚</option>
						</select>
					</td>
				</tr>
				<tr>
					<td><label class="control-label">债权到期时间</label></td>
					<td>
						<%--
						<div class="input-append date form_datetime">
							<input name="bondExpireDate" data-provide="datepicker" type="text" class="" readonly="readonly" value="${customer.bondExpireDate}" size="12" style="width:100px;">
						</div>
						 --%>
						 
						<input id="bondExpireDate" name="bondExpireDate" size="12" style="width:100px;" type="text" value="${customer.bondExpireDate}" readonly>
						
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
					<td><label class="control-label">用途</label></td>
					<td colspan="3">
						<input name="fundUse" type="text" class="small" style="width:93%;" value="${customer.fundUse}"/>
					</td>
					<td><label class="control-label">客户性格</label></td>
					<td colspan="3">
						<input name="customerCharacter" type="text" class="small" style="width:93%;" value="${customer.customerCharacter}"/>
					</td>
				</tr>
				<tr>
					<td><label class="control-label">债权构造</label></td>
					<td>
						<input type="hidden" name="amountliabilitys[0].id" value="${customer.amountliabilitys[0].id }"/>
						<select name="amountliabilitys[0].creditor.id" class="small m-wrap lendingInstitutionSelect">
							<option value="1"></option>
							<option value="2">私人</option>
							<optgroup label="银行">
							<c:forEach var="lendingInstitution" items="${sessionScope.lendingInstitutionMap.bank }">
								<option value="${lendingInstitution.id }" ${lendingInstitution.id==customer.amountliabilitys[0].creditor.id?'selected':'' }>${lendingInstitution.name }</option>
							</c:forEach>
							</optgroup>
							<optgroup label="小贷">
								<c:forEach var="lendingInstitution" items="${sessionScope.lendingInstitutionMap.smallLand }">
									<option value="${lendingInstitution.id }" ${lendingInstitution.id==customer.amountliabilitys[0].creditor.id?'selected':'' }>${lendingInstitution.name }</option>
								</c:forEach>
							</optgroup>
						</select>
					</td>
					<td><label class="control-label">负债金额</label></td>
					<td>
						<input type="text" name="amountliabilitys[0].debtMoney" value="${customer.amountliabilitys[0].debtMoney }" class="small inputmask-decimal" style="width:100px;"/>
					</td>
					<td>
						<button id="addAmountliabilityBtn" type="button" class="btn blue" style="padding:4px 2px;">更多</button>
					</td>
					<td colspan="2">
						<span style="color:#555;">预估负债总金额</span>
						<input name="debtTotal" type="text" class="small inputmask-decimal" style="width:100px;" value="${customer.debtTotal}"/>
					</td>
					<td colspan="6">
						<span style="color:#555;">推荐人</span>
						<input name="" type="text" class="small" style="width:100px;">
					</td>
				</tr>
				<c:if test="${fn:length(customer.amountliabilitys)>0 }">
					<c:forEach var="amountliability" items="${customer.amountliabilitys }" varStatus="vs">
						<c:if test="${vs.count>1 }">
							<tr class="amountliabilityTemplateTr">
								<td><label class="control-label">债权构造</label>
									<input type="hidden" class="amountliabilityId" value="${amountliability.id }"/>
								</td>
								<td class="creditor">
									<select class="small m-wrap lendingInstitutionSelect">
										<option value="1"></option>
										<option value="2">私人</option>
										<optgroup label="银行">
										<c:forEach var="lendingInstitution" items="${sessionScope.lendingInstitutionMap.bank }">
											<option value="${lendingInstitution.id }" ${lendingInstitution.id==amountliability.creditor.id?'selected':'' }>${lendingInstitution.name }</option>
										</c:forEach>
										</optgroup>
										<optgroup label="小贷">
											<c:forEach var="lendingInstitution" items="${sessionScope.lendingInstitutionMap.smallLand }">
												<option value="${lendingInstitution.id }" ${lendingInstitution.id==amountliability.creditor.id?'selected':'' }>${lendingInstitution.name }</option>
											</c:forEach>
										</optgroup>
									</select>
								</td>
								<td><label class="control-label">负债金额</label></td>
								<td class="debtMoney">
									<input type="text" class="small inputmask-decimal" data-required="1" style="width:100px;" value="${amountliability.debtMoney }">
								</td>
							</tr>
						</c:if>
					</c:forEach>
				</c:if>
			</table>
			<div class="form-actions" style="padding:8px;text-align:center;margin-top:5px;margin-bottom:10px;" >
				<button id="saveCustomerBacicInfoBtn" class="btn blue" type="button"><i class="icon-ok"></i> 保存</button>
			</div>
		</div>
	</div>
</div>
</form>
<!-- 辅助信息 end-->
<!-- 引入错误提示的窗体 -->
<%@include file="../common/errorTipModal.jsp"%>

<!-- 引入贷款机构窗体 -->
<%--
<%@include file="lendingInstitutionModal.jsp"%>
 --%>
<script type="text/javascript" src="js/customer/updateNonAllotCustomerBasicInfo.js"></script>

<script type="text/javascript" src="${pageContext.request.contextPath}/js/customer/dateFormHandler.js"></script>
