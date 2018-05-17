<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!-- 贷款机构窗体 start -->
<div id="lendingInstitutionModal" class="modal hide fade" tabindex="-1" role="dialog" aria-labelledby="lendingInstitutionModalLabel" aria-hidden="true" style="width:400px;">
	<div class="modal-header">
		<button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>
		<h3 id="lendingInstitutionModalLabel">选择贷款机构:</h3>
	</div>
	<div class="modal-body">
		<div id="lendingInstitutionModalBody">
			<div style="border:1px solid #ccc;width:150px;float:left;margin-right:5px;">
				<h3>银行</h3>
				<c:forEach var="lendingInstitution" items="${requestScope.lendingInstitutions }" varStatus="vs">
					<table>
					<c:if test="${lendingInstitution.type==0}">
						<tr>
							<td>
								<label class="radio">
									<input type="radio" name="lendingInstitution" value="${lendingInstitution.name }"/>
									${lendingInstitution.name }
								</label>
							</td>
						
						</tr>
					</c:if>
					</table>
				</c:forEach>
			</div>
			<div style="border:1px solid #ccc;width:150px;float:left;">
				<h3>小贷公司</h3>
				<c:forEach var="lendingInstitution" items="${requestScope.lendingInstitutions }">
					<table>
					<c:if test="${lendingInstitution.type==1}">
						<tr>
							<td>
								<label class="radio">
									<input type="radio" name="lendingInstitution" value="${lendingInstitution.name }"/>
									${lendingInstitution.name }
								</label>
							</td>
						</tr>
					</c:if>
					</table>
				</c:forEach>
			</div>
		</div>
	</div>
	<div class="modal-footer">
		<button class="btn" data-dismiss="modal" aria-hidden="true">关闭</button>
	</div>
</div>   
<!-- 贷款机构窗体 end -->

<script type="text/javascript">
	$('#lendingInstitutionModal').modal('show');
</script>
