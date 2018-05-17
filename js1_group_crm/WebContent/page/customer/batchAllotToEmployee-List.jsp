<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://itproject.cn/phone" prefix="phone"%>
<div class="span12">
	<!--开始：第2行第1列中的盒子 -->
	<div class="portlet box blue">
		<div class="portlet-title">
			<div class="caption"><i class="icon-cogs"></i>批量客户信息名单分配【分配给业务员】</div>
			<div class="tools"><a href="javascript:;" class="collapse"></a></div>
		</div>
		<!--开始：第2行第1列中的盒子body-->							
		<div class="portlet-body">
			<table class="table table-striped table-bordered table-hover" id="batch-custom-table">
				<thead>
					<tr>											
						<th width="15%">可分配数量</th>
						<th class="15%">整单分配</th>
						<th width="10%">分单分配</th>
					</tr>
				</thead>
				<tbody>
					<c:if test="${count>0 }">
						<tr id="batch-custom-row-1" class="odd gradeX">
							<td id="batch-custom-count" class="hidden-480">${count}</td>
							<td class="center">
								<div class="btn-group">
									<button class="btn green dropdown-toggle" id="btn-allot-all" data-toggle="dropdown">整单分配&nbsp;&nbsp;<i class="icon-angle-down"></i></button>
									<ul class="dropdown-menu pull-right" id="employee-ul-all" style="max-height:300px; overflow:auto;">
										<c:forEach items="${requestScope.employees }" var="employee">
											<li><a id="batch-employee-all-${employee.id }" class="batchAllotEmployeeAll" href="javascript:;">${employee.name }</a></li>
										</c:forEach>
									</ul>
								</div>
							</td>
							<td>
								<div class="input-append">													
									<input class="m-wrap medium" type="text" />
									<div class="btn-group">
										<button class="btn green dropdown-toggle"  id="btn-allot" data-toggle="dropdown">分单分配<i class="icon-angle-down"></i></button>
										<ul class="dropdown-menu pull-right" id="employee-ul" style="max-height:300px; overflow:auto;">
											<c:forEach items="${requestScope.employees }" var="employee">
												<li><a id="batch-employee-${employee.id }" class="batchAllotEmployee" href="javascript:;">${employee.name }</a></li>
											</c:forEach>
										</ul>
									</div>
								</div>											
							</td>
						</tr>
					</c:if>
				</tbody>
			</table>
		</div>
		<!--结束：第2行第1列中的盒子的body-->
	</div>
	<!--结束：第2行第1列中的盒子 -->						
	</div>
	
	<script>
	
	// 数据全单分配
	$(".batchAllotEmployeeAll").click(function(){
		if(confirm("确定分配?")){
			var eId = $(this).attr("id");
			var eName = $(this).text();
			eId = eId.substring(eId.lastIndexOf("-")+1);
			$("#btn-allot-all").addClass("disabled");
			var companyId = $("#comSelect").val();
			var departmentId = $("#demSelect").val();
			if (typeof(companyId) == "undefined"){ 
				companyId=0;
			}
			if (typeof(departmentId) == "undefined"){ 
				departmentId=0;
			}
			//全屏进度条
			var count = ${count};
			$.post("customer/batchAllotToEmplloy.do",{eId:eId,count:count,companyId:companyId,departmentId:departmentId},function(data){
				if(data=="true"){
					$("#btn-allot-all").removeClass("disabled");
					alert("分配给业务员成功！");
					var companyId = $("#comSelect").val();
					var departmentId = $("#demSelect").val();
					if (typeof(companyId) == "undefined"){ 
						companyId=0;
					}
					if (typeof(departmentId) == "undefined"){ 
						departmentId=0;
					}
					aPost("customer/getAllotToEmployeeList.do?companyId="+companyId+"&departmentId="+departmentId);
				}else if(data=="false") {
					alert("分配给业务员失败！");
				}else {
					alert("分配失败：你分配的客户数量超过  [ " + eName
							+ " ] 所拥有的客户数量上限：[ " + data + " ] 人！")
				}
			});
		}
	});
	
	$(".batchAllotEmployee").click(function(){
		var val = $(this).parent().parent().parent().
						parent().children("input").val();
		var reg = new RegExp("^[0-9]*$");
		if(!reg.test(val) || val==""){
	        alert("请输入分配数量");
	        return ;
	    }
		var text = ${count};
		if(parseInt(text)<parseInt(val)){
			alert("最多只能分配"+text+"个客户");
			return ;
		}
		if(confirm("确定分配?")){
			var eId = $(this).attr("id");
			var eName = $(this).text();
			eId = eId.substring(eId.lastIndexOf("-")+1);
			$("#btn-allot").addClass("disabled");
			var companyId = $("#comSelect").val();
			var departmentId = $("#demSelect").val();
			if (typeof(companyId) == "undefined"){ 
				companyId=0;
			}
			if (typeof(departmentId) == "undefined"){ 
				departmentId=0;
			}
			$.post("customer/batchAllotToEmplloy.do",{eId:eId,count:val,companyId:companyId,departmentId:departmentId},function(data){
				if(data=="true"){
					$("#btn-allot").addClass("disabled");
					alert("分配给业务员成功！");
					var companyId = $("#comSelect").val();
					var departmentId = $("#demSelect").val();
					if (typeof(companyId) == "undefined"){ 
						companyId=0;
					}
					if (typeof(departmentId) == "undefined"){ 
						departmentId=0;
					}
					aPost("customer/getAllotToEmployeeList.do?companyId="+companyId+"&departmentId="+departmentId);
				}else if(data=="false") {
					alert("分配给业务员失败！");
				}else {
					alert("分配失败：你分配的客户数量超过  [ " + eName
							+ " ] 所拥有的客户数量上限：[ " + data + " ] 人！")
				}
			});
		}
	});
	</script>
