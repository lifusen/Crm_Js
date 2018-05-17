<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>    
<!-- 签单客户 start-->
<div id="signCustomerContainer" style="">
	<div class="portlet box blue">
		<div class="portlet-title">
			<div class="caption"><i class="icon-cogs"></i>签单客户</div>
			<div class="tools">
				<a class="collapse" href="javascript:;"></a>
			</div>
		</div>
		<div class="portlet-body">
			<div id="signCustomerArea">
				<table class="formTable">
					<tr>
						<td id="signCustomerTitleTd" rowspan="50" style="width:10px;padding:7px;background: #4B8DF8; color: #fff;">房贷<span class="number"></span></td>
						<td rowspan="50" style="width:10px;"></td>
						<td width="50px"><label class="control-label">客户姓名</label></td>
						<td width="160px">
							<input type="text"  class="input"> 
						</td>
						<td width="50px"><label class="control-label">身份证号</label></td>
						<td width="160px">
							<input type="text"  class="input width120" > 
						</td>
						<td width="86px"><label class="control-label">备注</label></td>
						<td width="160px">
							<input type="text"  class="input"> 
						</td>
						<td width="50px"><label class="control-label">年龄</label></td>
						<td colspan="3">
							<input type="text"  class="input"> 
						</td>
						<td>
							<button id="addLoanProductBtn" class="btn blue" style="padding:2px;">更多</button>
						</td>
					</tr>
					<tr>
						<td><label class="control-label">客户状态</label></td>
						<td>
							<input type="text"  class="input"> 
						</td>
						<td><label class="control-label">放款情况</label></td>
						<td>
							<input type="text"  class="input width120"> 
						</td>
						<td><label class="control-label">是否有再贷意愿</label></td>
						<td>
							<input type="text"  class="input"> 
						</td>
						<td><label class="control-label">预期下次贷款时间</label></td>
						<td colspan="3">
							<input type="text"  class="input"> 
						</td>
					</tr>
					<tr>
						<td><label class="control-label">合同费用</label></td>
						<td>
							<input type="text"  class="input"> 
						</td>
						<td><label class="control-label">已收定金</label></td>
						<td>
							<input type="text"  class="input width120"> 
						</td>
						<td><label class="control-label">已收客户证件</label></td>
						<td colspan="4">
							<input type="text"  class="input"> 
						</td>
					</tr>
					<tr>
						<td><label class="control-label">贷款类型</label></td>
						<td>
							<input type="text"  class="input"> 
						</td>
						<td><label class="control-label">贷款机构</label></td>
						<td>
							<input type="text"  class="input width120"> 
						</td>
						<td><label class="control-label">银行产品</label></td>
						<td>
							<input type="text"  class="input"> 
						</td>
						<td><label class="control-label">年利率</label></td>
						<td>
							<input type="text"  class="input"> 
						</td>
						<td width="50"><label class="control-label">贷款年限</label></td>
						<td width="">
							<input type="text"  class="input"> 
						</td>
						<td>
							<button id="addLoanProductBtn" class="btn blue" style="padding:2px;">更多</button>
						</td>
					</tr>
					<tr id="customerLoanProductAreaAfter">
						<td><label class="control-label">是否垫资</label></td>
						<td>
							<input type="text"  class="input"> 
						</td>
						<td><label class="control-label">垫子费率</label></td>
						<td>
							<input type="text"  class="input width120"> 
						</td>
						<td colspan="6">
							<button class="btn blue"><i class="icon-plus"></i> 导入影像文件 </button>
						</td>
					</tr>
					
					<tr>
						<td><label class="control-label">抵押物</label></td>
						<td style="width:60px;">
							<input type="checkbox" checked="checked"/>住房1
							<input type="checkbox" checked="checked"/>住房2<br/>
							<input type="checkbox" checked="checked"/>商铺1
							<input type="checkbox" checked="checked"/>企业1 
						</td>
						<td><label class="control-label">其他备注</label></td>
						<td colspan="7">
							<textarea rows="2" cols="" style="width:796px;;"></textarea> 
						</td>
					</tr>
					<tr>
						<td><label class="control-label">退件时间</label></td>
						<td>
							<input type="text"  class="input"> 
						</td>
						<td><label class="control-label">退件原因</label></td>
						<td>
							<input type="text"  class="input width120"> 
						</td>
						<td><label class="control-label">退件备注</label></td>
						<td colspan="5">
							<input type="text"  class="input" style="width:550px;">
						</td>
					</tr>
					<tr>
						<td><label class="control-label">退单时间</label></td>
						<td>
							<input type="text"  class="input"> 
						</td>
						<td><label class="control-label">退单原因</label></td>
						<td>
							<input type="text"  class="input width120"> 
						</td>
						<td><label class="control-label">退单备注</label></td>
						<td colspan="5">
							<input type="text"  class="input" style="width:550px;">
						</td>
					</tr>
				</table>
				
				<div class="form-actions" style="padding:8px;text-align:center;margin-top:5px;margin-bottom:10px;" >
					<button class="btn blue" type="button"><i class="icon-ok"></i> 保存</button>
					<button id="customerLoanBtn" class="btn purple" type="button"><i class="icon-ok"></i> 放款</button>
				</div>
				   	
			</div>
		</div>
	</div>
</div>
<!-- 签单客户 end-->