<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<div id="customerTemplate" style="display: none;">
    <!-- 住房、商铺、写字楼模板 start-->
	<div class="houseTemplateTable">
		<table class="formTable" style="width:100%;">
			<tr>
				<th rowspan="2" style="width:20px;background: #35AA47; color: #fff;"><span class="houseName">住房</span><span class="number"></span></th>
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
				<th><input class="houseType" type="hidden" value="1"/></th>
			</tr>
			<tr>
				<td class="placeArea">
					<input type="text" class="input"> 
				</td>
				<td class="placeStreet">
					<input type="text" class="input"> 
				</td>
				<td class="placeDetail">
					<input type="text" class="input"> 
				</td>
				<td class="placePremise">
					<input type="text" class="input"> 
				</td>
				<td class="landNature">
					<input type="text" class="input"> 
				</td>
				<td class="constructDate">
					<input type="text"  class="input"> 
				</td>
				<td class="area">
					<input type="text"  class="input "/> 
				</td>
				<td class="price">
					<input type="text"  class="input "/> 
				</td>
				<td class="decorateCase">
					<input type="text"  class="input"> 
				</td>
				<td class="useCase">
					<input type="text"  class="input"> 
				</td>
				<td class="rental">
					<input type="text"  class="input "/> 
				</td>
				<td class="deleteHouseTemplate">
					<button class="btn mini yellow" type="button" style="margin-bottom: 8px;padding:2px 3px;" onclick="deleteHouseTemplate(this)">删除</button>
				</td>
			</tr>
		</table>   	
	</div>
	<!-- 住房、商铺、写字楼模板 end-->
	
    <!-- 汽车模板 start-->
	<div class="carTemplateTable">
		<table class="formTable">
			<tr>
				<th rowspan="2" style="width:20px;background: #35AA47; color: #fff;">汽车<span class="number"></span></th>
				<th><label class="control-label">品牌</label></th>
				<th><label class="control-label">购入价值</label></th>
				<th><label class="control-label">购入时间</label></th>
				<th><label class="control-label">贷款情况</label></th>
				<th><label class="control-label">月供</label></th>
				<th><label class="control-label">已还款时间</label></th>
			</tr>
			<tr>
				<td class="brand">
					<input type="text"  class="input"/> 
				</td>
				<td class="price">
					<input type="text"  class="input"/> 
				</td>
				<td class="buyDate">
					<input type="text" class="input"/>
				</td>
				<td class="loanStatus">
					<input type="text"  class="input"/> 
				</td>
				<td class="refundMonth">
					<input type="text"  class="input"/> 
				</td>
				<td class="refundDate">
					<input type="text"  class="input"/> 
				</td>
				<td class="deleteCarTemplate">
					<button class="btn mini yellow" type="button" style="margin-bottom: 8px;padding:2px 3px;" onclick="deleteCarTemplate(this)">删除</button>
				</td>
			</tr>
		</table>   	
	</div>
	<!-- 汽车模板 end-->
	
	<!-- 土地模板 -->
	<div class="landTemplateTable">
		<table class="formTable" style="width:100%;">
			<tr>
				<th style="width:20px;background: #35AA47; color: #fff;">土地<span class="number"></span></th>
				<td style="width:50px;"><label class="control-label">备注</label></td>
				<td class="description">
					<input type="text"  class="input" style="width:1000px;"> 
				</td>
				<td class="deleteLandTemplate">
					<button class="btn mini yellow" type="button" style="margin-bottom: 8px;padding:2px 3px;" onclick="deleteLandTemplate(this)">删除</button>
				</td>
			</tr>
		</table>   	
	</div>
	<!-- 土地模板 end-->
	
	<!-- 企业模板 start-->
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
				<th><label class="control-label">刷卡客户</label></th>
				<th></th>
			</tr>
			<tr>
				<td class="type">
					<select style="width:90px;">
						<option value=""></option>
						<option value="个体">个体</option>
						<option value="公司">公司</option>
						<option value="其它">其它</option>
					</select> 
				</td>
				<td class="annualValue">
					<select style="width:90px;">
						<option value=""></option>
						<option value="500以下">500以下</option>
						<option value="500-1000">500-1000</option>
						<option value="1000-2000">1000-2000</option>
						<option value="2000-5000">2000-5000</option>
						<option value="5000以上">5000以上</option>
					</select> 
				</td>
				<td class="showDetail">
					<select style="width:90px;">
						<option value=""></option>
						<option value="能">能</option>
						<option value="否">否</option>
					</select> 
				</td>
				<td class="license">
					<select style="width:90px;">
						<option value=""></option>
						<option value="齐全">齐全</option>
						<option value="无">无</option>
					</select> 
				</td>
				<td class="operationPlace">
					<select style="width:90px;">
						<option value=""></option>
						<option value="自有物业">自有物业</option>
						<option value="租赁物业">租赁物业</option>
						<option value="无">无</option>
					</select> 
				</td>
				<td class="operationScope">
					<select class="small m-wrap" style="width:100px;">
						<option value=""></option>
						<option value="生产加工型">生产加工型</option>
						<option value="物流贸易型">物流贸易型</option>
						<option value="设计研发型">设计研发型</option>
						<option value="勘探开采型">勘探开采型</option>
						<option value="建筑开发型">建筑开发型</option>
						<option value="金融证券型">金融证券型</option>
						<option value="科技型">科技型</option>
					</select>
				</td>
				<td class="shareRatio">
					<select style="width:90px;">
						<option value=""></option>
						<option value="1">10%以下</option>
						<option value="2">10%-30%</option>
						<option value="3">30%-50%</option>
						<option value="4">50%以上</option>
					</select>					
				</td>
				<td class="showStatute">
					<select style="width:80px;">
						<option value=""></option>
						<option value="能">能</option>
						<option value="否">否</option>
					</select>	
				</td>
				<td class="operationTime">
					<select style="width:90px;">
						<option value=""></option>
						<option value="半年以内">半年以内</option>
						<option value="一年以内">一年以内</option>
						<option value="两年以内">两年以内</option>
						<option value="三年以内">三年以内</option>
						<option value="三年以上">三年以上</option>
					</select>	
				</td>
				<td class="invoice">
					<select style="width:80px;">
						<option value=""></option>
						<option value="是">是</option>
						<option value="否">否</option>
					</select>	
				</td>
				<td class="cardCustomer">
					<select style="width:80px;">
						<option value=""></option>
						<option value="大量">大量</option>
						<option value="少量">少量</option>
						<option value="无">无</option>
					</select>
				</td>
				<td class="deleteEnterpriseTemplate">
					<button class="btn mini yellow" type="button" style="margin-bottom: 8px;padding:2px 3px;" onclick="deleteEnterpriseTemplate(this)">删除</button>
				</td>
			</tr>
		</table>   	
	</div>
	<!-- 企业模板 end-->
	
	
	<!-- 信用卡模板 start-->
	<div class="creditCardTemplateTable">
		<table class="formTable" style="width:950px;">
			<tr style="text-align:center;">
				<th rowspan="2" style="width:20px;background: #35AA47; color: #fff;">信用卡<span class="number"></span></th>
				<td><label class="control-label">发卡银行</label></td>
				<td class="bank">
					<input type="text"  class="input"> 
				</td>
				<td><label class="control-label">信用额度</label></td>
				<td class="creditLimit">
					<input type="text"  class="input "/> 
				</td>
				<td><label class="control-label">逾期情况</label></td>
				<td class="overdueCase">
					<input type="text"  class="input"> 
				</td>
				<td><label class="control-label">发卡日期</label></td>
				<td class="publishCardDate">
					<div class="input-append date form_datetime"  data-date-format="yyyy年mm月dd日">
						<input data-provide="datepicker" type="text" class="" readonly="readonly" value="" size="12" style="width:100px;">
					</div> 
				</td>
				<td><label class="control-label">卡号</label></td>
				<td class="cardNumber">
					<input type="text"  class="input" style="width:200px;"> 
				</td>
				<td class="deleteCreditCardTemplate">
					<button class="btn mini yellow" type="button" style="margin-bottom: 8px;padding:2px 3px;" onclick="deleteCreditCardTemplate(this)">删除</button>
				</td>
			</tr>
		</table>   	
	</div>
	<!-- 信用卡模板 end-->
	
	
	<!-- 手机模板 start -->
	<div class="phoneTemplateTable">
		<table>
			<tr>
				<td width="50px"><label class="control-label">联系方式</label></td>
				<td class="phoneNumber">
					<input type="text" class="small" style="width:100px;" phoneId="">
				</td>
				<td width="35px"><label class="control-label">备注</label></td>
				<td class="phoneRemark">
					<input type="text" class="small" style="width:212px;">
				</td>
				<td class="deletePhoneTemplate">
					<button class="btn mini yellow" type="button" style="margin-bottom: 10px;" onclick="deletePhoneTemplate(this)"><i class="icon-remove"></i> 删除</button>
				</td>
			</tr>
		</table>
	</div>
	<!-- 手机模板 end -->
	
	<!-- 负债金额模板 start -->
	<div class="amountliabilityTemplateTable">
		<table>
			<tr class="amountliabilityTemplateTr">
				<td><label class="control-label">债权构造</label></td>
				<td class="creditor">
					<select data-placeholder=" " class="small m-wrap">
						<option value=""></option>
						<option value=""></option>
						<option value="2">私人</option>
						<%@ include file="../common/lendingInstitution.jsp" %>
					</select>
				</td>
				<td><label class="control-label">负债金额</label></td>
				<td class="debtMoney">
					<input type="text" class="small" data-required="1" style="width:100px;">
				</td>
				<td class="deleteAmountliabilityTemplate">
					<button class="btn mini yellow" type="button" style="margin-bottom: 8px;padding:2px 3px;" onclick="deleteAmountliabilityTemplate(this)">删除</button>
				</td>
			</tr>
		</table>
	</div>
	<!-- 负债金额模板 end -->
	
</div>														
<script type="text/javascript" src="${pageContext.request.contextPath}/js/customer/customerTemplate.js"></script>
