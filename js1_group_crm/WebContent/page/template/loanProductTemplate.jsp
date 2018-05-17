<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!-- 贷款产品模板 start -->
<div class="loanProductTemplate" style="display: none;">
	<table>
		<tr class="loanProductTemplateTr">
			<td><label class="control-label">贷款类型</label></td>
			<td class="loanType">
				<select class="small m-wrap" style="width:126px;">
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
			<td><label class="control-label">贷款机构</label></td>
			<td class="lendingInstitution">
				<select data-placeholder=" " class="small m-wrap">
					<option></option>
					<%@ include file="../common/lendingInstitution.jsp" %>		
				</select>
			</td>
			<td><label class="control-label">银行产品</label></td>
			<td class="bankProduct">
				<input type="text" class="input"/> 
			</td>
			<td><label class="control-label">月利率</label></td>
			<td class="apr">
				<input type="text" class="input"/> % 
			</td>
			<td width="50"><label class="control-label">贷款年限</label></td>
			<td width="70" class="loanYear">
				<select class="" style="width:125px;">
					<option value=""></option>
					<c:forEach begin="1" end="30" var="i">
						<option value="${i}年">${i}年</option>
					</c:forEach>
				</select>
			</td>
			<td class="deleteLoanProductTemplate">
				<button class="btn mini yellow" type="button" style="margin-bottom: 8px;padding:2px 3px;" onclick="deleteLoanProductTemplate(this)">删除</button>
			</td>
		</tr>
	</table>
</div>
<!-- 贷款产品模板 end -->
<script type="text/javascript">
/**
 * 删除贷款产品
 */
function deleteLoanProductTemplate(obj){
	if(confirm('您确定要删除贷款产品吗?')){
		$(obj).parents('tr').remove();
	}
}
</script>