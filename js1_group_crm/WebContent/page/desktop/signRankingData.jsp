<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<table class="t-g" width="100%" cellpadding="12" >
	<tr>
		<td style="text-align: center" colspan="3">
			<table class="table" width="100%"  style="margin-bottom: 0;" >
				<tr>
					<td style="text-align: center;border: 0px;" width="50%">签单转换率<div style="line-height: 40px;" class="m-t-10 t-60">${currentEmployeeCro }<span>%</span></div></td>
					<td style="text-align: center;border: 0px;" width="50%">公司排名
					<div id="signRankingDiv" style="line-height: 40px;" class="m-t-10 t-60">
						${currentRanking }
					<%--
					<a href="javascript:void(0);" class="popovers" style="font-size:12px;line-height:15px;"
						data-trigger="hover" data-html="true"
						data-content="${rankingString}"
						data-original-title="签单转化率排名"><span style="font-size:60px;">${currentRanking }</span></a>
						 --%>
					</div></td>
				</tr>						
			</table>
		</td>										
	</tr>
</table>
<!-- 签单转化率排名窗体 start -->
<div id="signRankingModal" class="modal hide fade" tabindex="-1" role="dialog" aria-labelledby="signRankingModalLabel" aria-hidden="true"
	 style=""
>
	<div class="modal-header">
		<button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>
		<h3 id="signRankingModalLabel">签单转化率排名</h3>
	</div>
	<div class="modal-body">
		<div id="signRankingModalBody" style="">
			${rankingString}
		</div>
	</div>
	<div class="modal-footer">
		<button id="closeErrorTipModal" class="btn" data-dismiss="modal" aria-hidden="true">关闭</button>
	</div>
</div>   
<!-- 签单转化率排名窗体 end -->   
<script>
$(function(){
	//$('.popovers').popover();
	$('#signRankingDiv').dblclick(function(){
		$('#signRankingModal').modal('show');
	});
});
</script>