<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	
	
	<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!-- BEGIN PAGE CONTAINER-->
<div class="container-fluid p-t-20" style="padding-top:8px;">
	<!--开始：第1行 -->
	<div class="row-fluid">
		<!--开始：第1行第1列 -->
		<div class="span12">
			<!--开始：第1行第1列中的盒子-->
			<div class="portlet box blue">
				<div class="portlet-title">
					<div class="caption"><i class="icon-cogs"></i>分配客户到部门</div>
					<div class="tools">
						<a href="javascript:;" class="collapse"></a>
					</div>
				</div>
				<!--开始：第1行第1列中的盒子body-->
				<div class="portlet-body" id="tableList">		
					<jsp:include page="allotToDepartment-List.jsp" />
				</div>										
				<!--结束：第1行第1列中的盒子的body-->
			</div>
			<!--结束：第1行第1列中的盒子-->
		</div>
		<!--结束：第1行第1列 -->					
	</div>
	<!--结束：第1行 -->
	<div class="row-fluid">
		<div class="span12">
			<!--开始：第2行第1列中的盒子 -->
			<div class="portlet box blue">
				<div class="portlet-title">
					<div class="caption"><i class="icon-cogs"></i>批量客户信息名单分配【分配给业务部】</div>
					<div class="tools"><a href="javascript:;" class="collapse"></a></div>
				</div>
				<!--开始：第2行第1列中的盒子body-->							
				<div class="portlet-body">
					<table class="table table-striped table-bordered table-hover" id="batch-custom-table">
						<thead>
							<tr>											
								<th width="50%">名单</th>
								<th width="10%">可分配数量</th>
								<th class="30%">整单分配</th>
								<th width="10%">分单分配</th>
							</tr>
						</thead>
						<tbody>
						<c:forEach items="${requestScope.rosters }" var="roster">
							<tr id="batch-custom-row-1" class="odd gradeX">
								<td><a href="javascript:;">${roster.roster.name }</a><span class="t-12">&nbsp;&nbsp;[名单总数：${roster.roster.successCount }]</span></td>
								<td id="batch-custom-count-${roster.roster.id }" class="hidden-480">${roster.count}</td>
								<td class="center">
									<c:choose>
										<c:when test="${loginUser.type == 'SUPER_ROLE'}">
											<button class="btn green dropdown-toggle" id="btn-allot-all-${roster.roster.id}" onclick="showChoiceCompanyDepartmentModal(3,this)">整单分配&nbsp;&nbsp;</button>
										</c:when>
										<c:otherwise>
											<div class="btn-group">
												<button class="btn green dropdown-toggle" id="btn-allot-all-${roster.roster.id}" data-toggle="dropdown">整单分配&nbsp;&nbsp;<i class="icon-angle-down"></i></button>
												<ul class="dropdown-menu pull-right" id="all-depar-ul-${roster.roster.id}">
													<c:forEach items="${requestScope.departments }" var="department">
														<li><a id="all-depar-${department.id }" href="javascript:;" class="importBatchAllotAll">${department.name }</a></li>
													</c:forEach>
												</ul>
											</div>
										</c:otherwise>
									</c:choose>
								</td>
								<td >			
									
									<c:choose>
										<c:when test="${loginUser.type == 'SUPER_ROLE'}">
											<div class="input-append">													
												<input class="m-wrap medium" type="text" />
												<div class="btn-group">
													<button class="btn green" id="btn-allot-${roster.roster.id}" onclick="showChoiceCompanyDepartmentModal(4,this)">分单分配&nbsp;&nbsp;</button>
												</div>
											</div>	
										</c:when>
										<c:otherwise>
											<div class="input-append">													
												<input class="m-wrap medium" type="text" />
												<div class="btn-group">
													<button class="btn green dropdown-toggle"  id="btn-allot-${roster.roster.id}" data-toggle="dropdown">分单分配<i class="icon-angle-down"></i></button>
													<ul class="dropdown-menu" id="all1-depar-ul-${roster.roster.id}">
														<c:forEach items="${requestScope.departments }" var="department">
															<li><a id="all1-depar-${department.id }" href="javascript:;" class="importBatchAllot">${department.name }</a></li>
														</c:forEach>
													</ul>
												</div>
											</div>	
										</c:otherwise>
									</c:choose>
																		
																			
								</td>
							</tr>
						</c:forEach>
						</tbody>
					</table>
				</div>
				<!--结束：第2行第1列中的盒子的body-->
			</div>
			<!--结束：第2行第1列中的盒子 -->						
		</div>
	</div>
</div>
<!-- END PAGE CONTAINER--> 
