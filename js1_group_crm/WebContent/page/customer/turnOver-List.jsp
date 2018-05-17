<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://itproject.cn/phone" prefix="phone"%>
<%@ taglib uri="http://itproject.cn/fllowDate" prefix="fllowDate"%>
<style type="text/css">
	.superSearch{
		width:100%;height:320px;
		border:1px solid #ccc;
		margin: 10px 0;
	}
</style>
<div>
	<p>　　搜索： <input type="text" id="keyStr" style="width:318px;" value="${requestScope.keyStr }" 
	placeholder="客户电话/姓名/等级/贷款类型" aria-controls="customerTable">
		<a class="btn green" id="searchButton" href="javascript:;" style="margin-top: -10px;padding-top:5px;padding-bottom: 5px;">
			<i class="icon-search"></i> 搜索
		</a>
	</p>
	<div class="row-fluid">
		<div class="span14">
			查询：
			<c:if test="${loginUser.type=='SUPER_ROLE'}">
				<select id="comSelect" class="span2">
					<option value="0">所有业务中心</option>
					<c:forEach items="${requestScope.companys }" var="company">
						<option value="${company.id }" 
							<c:if test="${companyId == company.id}">
								selected="selected"
							</c:if>
						>${company.name }</option>
					</c:forEach>
				</select>
			</c:if>
			<c:if test="${requestScope.roleId==1 || requestScope.roleId==5 ||requestScope.roleId==2 || requestScope.roleId==7 || loginUser.type=='SUPER_ROLE'}">
				<input type="hidden" id="eId" value="${requestScope.eId }" />
					<select id="demSelect" class="span2">
						<option value="0">所有业务部</option>
						<c:forEach items="${requestScope.departments }" var="department">
							<option value="${department.id }"
								<c:if test="${dId == department.id}">
									selected="selected"
								</c:if>
							>${department.name }</option>
						</c:forEach>
					</select>
			</c:if>
			<select class="span2" id="emplSelect">
				<option value="0">所有客户经理</option>
				<c:forEach items="${requestScope.employees }" var="empl">
					<option value="${empl.id}" 
					<c:if test="${requestScope.eId==empl.id}">selected="selected"</c:if>
					>${empl.name}</option>
				</c:forEach>
			</select>
			<select id="followLevel" class="span2">
				<option value="0">所有关注等级</option>
				<option value="A" 
					<c:if test="${fLevel == 'A'}">
						 selected="selected"
					</c:if>>A</option>
				<option value="B" 
					<c:if test="${fLevel == 'B'}">
						 selected="selected"
					</c:if>>B</option>
				<option value="C" 
					<c:if test="${fLevel == 'C'}">
						 selected="selected"
					</c:if>>C</option>
				<option value="D" 
					<c:if test="${fLevel == 'D'}">
						 selected="selected"
					</c:if>>D</option>
			</select>
			<select id="customerLevel" class="span2">
				<option value="0">所有客户等级</option>
				<option value="优"
					<c:if test="${cLevel == '优'}">
						 selected="selected"
					</c:if>
				>优</option>
				<option value="良"
					<c:if test="${cLevel == '良'}">
						 selected="selected"
					</c:if>
				>良</option>
				<option value="中"
					<c:if test="${cLevel == '中'}">
						 selected="selected"
					</c:if>
				>中</option>
				<option value="差"
					<c:if test="${cLevel == '差'}">
						 selected="selected"
					</c:if>
				>差</option>
			</select>
			<a class="btn green" id="ListSearchButton" href="javascript:void(0);" style="margin-top: -10px;"><i class="icon-search"></i> 搜索</a>
			<a class="btn green" id="turnsButton" href="javascript:void(0);" style="margin-top: -10px;"><i class="icon-ok"></i> 批量移交</a>
		</div>
	</div>
	<div class="row-fluid">
		<div class="span14">
		移交信息：
			<c:if test="${loginUser.type=='SUPER_ROLE'}">
				<select id="comSelect1" class="span2">
					<option value="0">业务中心</option>
					<c:forEach items="${requestScope.companys }" var="company">
						<option value="${company.id }">${company.name }</option>
					</c:forEach>
				</select>
			</c:if>
			<c:if test="${requestScope.roleId==1 || requestScope.roleId==5 ||requestScope.roleId==2 || requestScope.roleId==7 || loginUser.type=='SUPER_ROLE'}">
				<select id="demSelect1" class="span2">
					<option value="0">所有业务部</option>
					<c:forEach items="${requestScope.departments }" var="department">
						<option value="${department.id }">${department.name }</option>
					</c:forEach>
				</select>
			</c:if>
			<select class="span2" id="emplSelect1">
				<option value="0">所有客户经理</option>
				<c:forEach items="${requestScope.employees }" var="employee">
					<option value="${employee.id}">${employee.name}</option>
				</c:forEach>
			</select>
			移交原因：
			<select class="span2" id="cause">
				<option value="晋升">晋升</option>
				<option value="资源整合">资源整合</option>
				<option value="离职">离职</option>
				<option value="合作开单">合作开单</option>
				<option value="其他">其他</option>
			</select>
			<input type="text" placeholder="其他原因" id="otherCause" />
			<a class="btn green" id="turnButton" href="javascript:void(0);" style="margin-top: -10px;"><i class="icon-ok"></i> 移交</a>
		</div>
	</div>
</div>
<!-- table table-striped table-bordered table-hover -->
<table class="table table-striped table-bordered table-hover table-full-width dblclickUpdate" id="customerTable" style="background: #EEE;">
	<thead>
		<tr>
		<td style="width:5%"><input type="checkbox" id="selectAll" onchange="selectAll(this);" class="group-checkable" data-set="#adv-custom-table .checkboxes" /></th>
			<td style="min-width: 48px;">公司</td>
			<td style="min-width: 48px;">部门</td>
			<td style="min-width: 48px;">客户经理</td>
			<td style="min-width: 48px;">客户姓名</td>
			<td style="min-width: 70px;">电话</td>
			<td style="min-width: 50px;">资产</td>
			<td style="min-width: 48px;">贷款类型</td>
			<td style="min-width: 25px;">通话</td>
			<td style="min-width: 25px;">上门</td>
			<td style="min-width: 50px;">资料完善</td>
			<td style="min-width: 50px;">关注等级</td>
			<td style="min-width: 50px;">客户等级</td>
			<td style="min-width: 50px;">签单状态</td>
			<td style="min-width: 25px;">自留</td>
			
			<td style="min-width: 48px;">来源</td>
			<td style="min-width: 40px;">移交信息</td>
			<td style="min-width: 48px;">工资体现</td>
			<td style="min-width: 50px;">单位性质</td>
			<td style="min-width: 36px;">倒计时</td>
		</tr>
	</thead>
	<tbody>
		<c:forEach items="${requestScope.customers }" var="cust" varStatus="vsta">
			<tr cId="${cust.customerId}">
			<td><input type="checkbox" eId="${cust.employeeId}" 
			onchange="selectParent(this);" class="checkboxes customerList" value="${cust.customerId }" /></td>
<%-- 				<td>${((pageIndex-1)*pageSize)+(vsta.index+1) }</td> --%>
				<td>${allCompanyNameMap[cust.companyId]}</td>
				<td>${cust.departmentName }</td>
				<td>${cust.employeeName }</td>
				<td class="hidden-480">${cust.customerName }</td>
				<td>${phone:phone(cust.phone)}</td>
				<td>
					<a id="testaaa" href="javascript:;" class="popovers"
						data-trigger="hover" data-html="true"
						data-content="${cust.customerAsset }"
						data-original-title="资产信息"> ${cust.customerAssetTitile } </a>
				</td>
				<td class="hidden-480">${cust.loanType }</td>
				<td class="hidden-480">${cust.callCount }</td>
				<td class="hidden-480">${cust.visitCount }</td>
				<td class="hidden-480">
					<c:if test="${cust.dataPercent!=null && cust.dataPercent>0 }">
							${cust.dataPercent==0} %
					</c:if>
				</td>
				<td class="hidden-480">${cust.attentionLevel }</td>
				<td class="hidden-480">${cust.customerLevel }</td>
				<td class="hidden-480">
					<c:if test="${cust.state==4 }">
						已签单
					</c:if>
					<c:if test="${cust.state!=4 }">
						未签单
					</c:if>
				</td>
				<td class="hidden-480">
					<c:if test="${cust.visibility==0 }">
						是
					</c:if>
					<c:if test="${cust.visibility==1 }">
						否
					</c:if>
				</td>
				<td>${cust.sourceName }</td>
				<td class="hidden-480">
					<a href="javascript:void(0);" title="${cust.turnDetail }" style="text-decoration: none;color: #000000">
						<div style="width:40px; overflow: hidden;text-overflow: ellipsis;white-space: nowrap;">
							${cust.turnDetail }
						</div>
					</a>
				</td>
				<td class="hidden-480">
					${cust.embodiment }
				</td>
				<td class="hidden-480">
					${cust.enterpriseNature }
				</td>
				<td class="hidden-480">
					${fllowDate:fllowDate(cust.fllowDate)}
				</td>
			</tr>
		</c:forEach>
	</tbody>
</table>
<jsp:include page="../common/paginator.jsp" />
<script>

	$(function() {
	$('.popovers').popover();
	$('table.dblclickUpdate tbody tr').dblclick(function(){
		var cId = $(this).attr('cId');
		aPost("${pageContext.request.contextPath}/customer/gotoValidListUpdateUI.do?id="+cId+"&refererUrl=customer/turnOverList.do");
	});
	
	var val = $("#demSelect").val();
	if(val!=0 && typeof(val) != "undefined" ){
		$.post("employee/getEmployeesByDId.do",{dId:val},function(data){
			var s = $("#emplSelect");
			s.empty();
			var eId = $("#eId").val();
			$("<option />",{value:0,text:"所有客户经理"}).appendTo(s);
			$.each(data,function(i,item){
				var op = $("<option />",{value:item.id,text:item.name});
				if(eId==item.id){
					op.attr("selected",true);
				}
				op.appendTo(s);
			});
		});
	}
	// 查询
	$("#demSelect").change(function(){
		var val = $(this).val();
		$.post("employee/getEmployeesByDId.do",{dId:val},function(data){
			var s = $("#emplSelect");
			s.empty();
			$("<option />",{value:0,text:"所有客户经理"}).appendTo(s);
			$.each(data,function(i,item){
				$("<option />",{value:item.id,text:item.name}).appendTo(s);
			});
		});
	});
	// 移交信息
	$("#demSelect1").change(function(){
		var val = $(this).val();
		$.post("employee/getEmployeesByDId.do",{dId:val},function(data){
			var s = $("#emplSelect1");
			s.empty();
			$("<option />",{value:0,text:"所有客户经理"}).appendTo(s);
			$.each(data,function(i,item){
				$("<option />",{value:item.id,text:item.name}).appendTo(s);
			});
		});
	});
	
	// 客户移交
	$("#turnButton").click(function(){
		var inputs = $(".customerList");
		var ids = "";
		var ary = new Array();
		
		for(var i=0;i<inputs.length;i++){
			if($(inputs[i]).attr("checked")){
				var id = $(inputs[i]).val();
				var eId = $(inputs[i]).attr("eId");
				ids = ids + (id+"-"+eId+",");
				ary.push(parseInt(eId)); 
			}
		}
		if(ids==""){
			alert("请选择客户");
			return;
		}
		var employeeId = $("#emplSelect1").val();
		employeeId = parseInt(employeeId);
		if(employeeId<1){
			alert("请选择移交的客户经理！");
			return;
		}
		for(var i=0;i<ary.length;i++){
			if(ary[i]==employeeId){
				alert("选择的客户列表中，现在的客户经理和希望移交给的客户经理是同一个人！");
				return;
			}
		}
		var cause = $("#cause").val();
		var otherCause = $("#otherCause").val();
		if(confirm("确定移交吗?")){
			$.post("customer/doTurn.do",{ids:ids,toEmployeeId:employeeId,cause:cause,otherCause:otherCause},function(data){
				if(data == 'true'){
					alert("移交成功！");
					aPost("customer/turnOverList.do");
				}else if(data == 'false'){
					alert("移交失败！");
				}else {
					alert("移交失败：你移交的客户数量超过  [ " + $("#emplSelect1 option:selected").text()
							+ " ] 所拥有的客户数量上限：[ " + data + " ] 人！")
				}
			});
		}
	});
	// 批量移交
	$("#turnsButton").click(function(){
		var dId = $("#demSelect").val();
		var eId = $("#emplSelect").val();
		var fLevel = $("#followLevel").val();
		var cLevel = $("#customerLevel").val();
		
		
		var dText = $("#demSelect").find("option:selected").text();
		var eText = $("#emplSelect").find("option:selected").text();
		var fText = $("#followLevel").find("option:selected").text();
		var cText = $("#customerLevel").find("option:selected").text();
		
		var dText1 = $("#demSelect1").find("option:selected").text();
		var eText1 = $("#emplSelect1").find("option:selected").text();
		
		
		if(eId=="0"){
			alert("请选择客户经理");
			return;
		}
		
		// 移交参数
		var employeeId = $("#emplSelect1").val();
		if(eId==employeeId){
			alert("被移交和移交客户经理是同一人");
			return;
		}
		employeeId = parseInt(employeeId);
		if(employeeId<1){
			alert("请选择移交的客户经理！");
			return;
		}
		var cause = $("#cause").val();
		var otherCause = $("#otherCause").val();
		$.post("customer/getTurnCount.do",{dId:dId,eId:eId,fLevel:fLevel,cLevel:cLevel},function(data){
			if(data<1){
				alert("没有可移交客户");
				return;
			}
			var msg = "确定将["+dText+"] ["+eText+"] 的 "+data+" 个客户移交给["+dText1+"]["+eText1+"]吗?";
			if(confirm(msg)){
				$.post("customer/batchTurn.do",{dId:dId,eId:eId,fLevel:fLevel,cLevel:cLevel,toEmployeeId:employeeId,cause:cause,otherCause:otherCause},function(data){
					if(data=='true'){
						alert("移交成功！");
						aPost("customer/turnOverList.do");
					}else if(data=='false') {
						alert("移交失败！");
					}else {
						alert("移交失败：你移交的客户数量超过  [ " + eText1
							+ " ] 所拥有的客户数量上限：[ " + data + " ] 人！")
					}
				});
			}
		});
		
		
		if(employeeId<1){
			alert("请选择移交的客户经理！");
			return;
		}
	});
	
	$("#ListSearchButton").click(function(data){
		var dId = $("#demSelect").val();
		var eId = $("#emplSelect").val();
		var fLevel = $("#followLevel").val();
		var companyId = $("#comSelect").val();
		var cLevel = $("#customerLevel").val();
		if(typeof(dId) == "undefined"){
			dId=0;
		}
		if(typeof(eId) == "undefined"){
			eId=0;
		}
		if(typeof(companyId) == "undefined"){
			companyId=0;
		}
		aPost("customer/turnOverList.do?dId="+dId+"&eId="+eId+"&fLevel="+fLevel+"&cLevel="+cLevel+"&companyId="+companyId);
	});
	
	$("#comSelect").change(function(){
		var companyId = $(this).val();
		if(companyId!=0 && typeof(companyId) != "undefined"){
			getDep(companyId,$("#demSelect"));
		}
	});
	$("#comSelect1").change(function(){
		var companyId = $(this).val();
		if(companyId!=0 && typeof(companyId) != "undefined"){
			getDep(companyId,$("#demSelect1"));
		}
	});
	//全文搜索
	$("#keyStr").keyup(function(event){
		 if(event.keyCode == 13){
			var val = strTrim($(this).val());
			aPost("customer/turnOverList.do?keyStr="+val);
		 }
	 });
	$("#searchButton").click(function(){
		var val = strTrim($("#keyStr").val());
		aPost("customer/turnOverList.do?keyStr="+val);
	});
});
	
	// 全选
	function selectAll(cb){
		if($(cb).attr("checked")){
			$(".customerList").attr("checked",true);
			$(".customerList").parent().addClass("checked");
		}else{
			$(".customerList").attr("checked",false);
			$(".customerList").parent().removeClass("checked");
		}
	}
	
	function selectParent(checkbox){
		var menus = $(".customerList");
		var isAll = true;
		for(var i=0;i<menus.length;i++){
			if(!$(menus[i]).attr("checked")){
				isAll = false;
				break;
			}
		}
		if(!isAll){
			$("#selectAll").attr("checked",false);
			$("#selectAll").parent().removeClass("checked");
		}else{
			$("#selectAll").attr("checked",true);
			$("#selectAll").parent().addClass("checked");
		}
	}
	function strTrim(val){
		var str = val.replace(/\s+/g,"");
		return str;
	}
	
	function getDep(companyId,departmentSelect){
		$.getJSON("department/getDepartmentListMapByCompanyId.do",
				{companyId:companyId,type:1},function(departments){
			departmentSelect.empty();
			$("<option value='0'>所有业务部</option>").appendTo(departmentSelect);
			for(var i = 0; i < departments.length; i++){
				var department = departments[i];
				if($("#depId").val()==department.id){
					$("<option selected='selected' value='"+department.id+"'>"+department.name+"</option>").appendTo(departmentSelect);
				}else{
					$("<option value='"+department.id+"'>"+department.name+"</option>").appendTo(departmentSelect);
				}
			}
		});
	}
</script>