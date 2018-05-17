<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!-- BEGIN PAGE CONTAINER-->
<div class="container-fluid p-t-20" style="padding-top:8px;">
	<div class="row-fluid">
		<div class="span12">
			<div class="portlet box blue">
				<div class="portlet-title">
					<div class="caption"><i class="icon-cogs"></i>贷款机构管理</div>
				</div>
				<div class="portlet-body">		
					<div class="clearfix">
						<div class="btn-group">
							<button id="addLendingInstitutionBtn" class="btn green" type="button"><i class="icon-plus"></i> 添加贷款机构</button>
						</div>
					</div>
					
					
					<!--BEGIN TABS-->
					<div class="tabbable tabbable-custom tabs-left">
						<ul class="nav nav-tabs tabs-left">
							<li class="active"><a href="#tab_3_1" data-toggle="tab">银行</a></li>
							<li ><a href="#tab_3_2" data-toggle="tab">小贷公司</a></li>
						</ul>

						<div class="tab-content">
							<div class="tab-pane active" id="tab_3_1">
								<table class="table table-striped table-hover table-bordered table-condensed" id="yh-table">
									<thead>
										<tr>
											<th width="10%">序号</th>
											<th width="60%">银行名称</th>
											<th width="30%">操作</th>
										</tr>
									</thead>
									<tbody>
										<%int i = 0; %>
										<c:forEach var="lendingInstitution" items="${requestScope.lendingInstitutions }">
											<c:if test="${lendingInstitution.type==0 }">
												<tr>
													<td><%=i+=1 %></td>
													<td>${lendingInstitution.name }</td>
													<td>
														<button type="button" class="btn blue mini" onclick="updateLendingInstitution(${lendingInstitution.id})">修改</button>
														<button type="button" class="btn yellow mini" onclick="deleteLendingInstitution(${lendingInstitution.id})">删除</button>
													</td>
												</tr>
											</c:if>
										</c:forEach>
									</tbody>
								</table>
							</div>
							
							<div class="tab-pane " id="tab_3_2">
								<table class="table table-striped table-hover table-bordered table-condensed" id="yh-table">
									<thead>
										<tr>
											<th width="10%">序号${vs.count }</th>
											<th width="60%">小贷公司名称</th>
											<th width="30%">操作</th>
										</tr>
									</thead>
									<tbody>
										<%int j=0; %>
										<c:forEach var="lendingInstitution" items="${requestScope.lendingInstitutions }">
											<c:if test="${lendingInstitution.type==1 }">
												<tr>
													<td><%=j+=1 %></td>
													<td>${lendingInstitution.name }</td>
													<td>
														<button type="button" class="btn blue mini" onclick="updateLendingInstitution(${lendingInstitution.id})">修改</button>
														<button type="button" class="btn yellow mini" onclick="deleteLendingInstitution(${lendingInstitution.id})">删除</button>
													</td>
												</tr>
											</c:if>
										</c:forEach>
									</tbody>
								</table>
							</div>									
						</div>
						<!--结束：Tab Contents-->		
					</div>
					<!--END TABS-->
					
					
												
				</div>										
			</div>
		</div>
	</div>
</div>
<!-- END PAGE CONTAINER--> 

<script type="text/javascript">

/**
 * 修改
 */
function updateLendingInstitution(id){
	aPost("lendingInstitution/gotoUpdate.do?id="+id);
}
/**
 * 删除
 */
function deleteLendingInstitution(id){
	if(confirm('您确定要删除吗?')){
		$.post("lendingInstitution/delete.do",{id:id},function(data){
			if(data){
				alert('删除成功!');
				aPost("lendingInstitution/getList.do");
			}else{
				alert('删除失败,该渠道下有客户存在!');
			}
		});
	}
}

$(function() {
	//添加页面
	$('#addLendingInstitutionBtn').click(function(){
		aPost('page/lendingInstitution/add.jsp');
	});
});
</script>