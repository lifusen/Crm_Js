<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!-- 垫资 start-->
<div id="loaningTemplateContainer" style="display:none;">
	<div class="loaningTemplate">
		<table class="formTable">
			<tr>
				<td rowspan="2" style="width:15px;padding:4px;background: #ddd; color: #fff;">垫资信息</td>
				<td rowspan="2" style="width:5px;"></td>
				<td width="50px"><label class="control-label">垫资金额</label></td>
				<td width="150px">
					<input type="text" class="input" name="loaningAmount" maxlength="11"/>
				</td>
				<td width="50px"><label class="control-label">垫资费率</label></td>
				<td width="150px">
					<input type="text" class="input" name="loaningRate"/>
				</td>
				<td width="50px"><label class="control-label">垫资时间</label></td>
				<td width="150px">
					<div class="input-append date form_datetime">
						<input name="loaningDate" data-provide="datepicker" type="text" class="" readonly="readonly" value="" size="12" style="width:110px;">
					</div>
				</td>
				<td width="50px"><label class="control-label">垫资周期</label></td>
				<td width="150px">
					<input type="text" class="input" name="loaningCycle"/>
				</td>
				<td style="width:100px;"><label class="control-label" style="width:100px;">是否在我公司做单</label></td>
				<td>
					<select class="xsmall" style="width:30px;" name="loaningType">
						<option value=""></option>					
						<option value="是">是</option>					
						<option value="否">否</option>					
					</select>
				</td>
			</tr>
			<tr>
				<td><label class="control-label">预估风险</label></td>
				<td colspan="9">
					<input type="text" class="" style="width:1020px;" name="loaningRisk"/>
				</td>
			</tr>
		</table>
		
	</div>
</div>
<!-- 垫资 end-->