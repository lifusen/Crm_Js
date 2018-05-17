<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://itproject.cn/phone" prefix="phone"%>
<div class="row-fluid span10">
	<div class="span16" style="text-align: center;">
		搜索: <input type="text" style="width:300px;" id="keyStr" value="${requestScope.keyStr }" placeholder="客户电话/姓名" aria-controls="customerTable">
		<a class="btn green" id="searchButton" href="javascript:;" style="margin-top: -10px;padding-top:5px;padding-bottom: 5px;">
			<i class="icon-search"></i> 搜索
		</a>　　　　
	</div>
<div>
	<p style="text-align: center;"><a class="btn green" id="superSearchButton" href="javascript:void(0);">
		<i class="icon-search"></i> 高级搜索
	</a></p>
	<!-- 高级搜索 -->
	<div id="superSearchDiv" class="superSearch hide" style="clear:left;">
		<c:if test="${roleId==1000 || roleId==999 }">
			<p style="margin: 10px 0 0 10px;">权证中心：
				<a class="green btn mini all filterBtn companyVal hideDepart" val="" href="javascript:;">不限</a>
				<c:forEach items="${queryCompanys }" var="company">
					<a class="btn mini filterBtn companyVal showDepart" val="${company.id }" href="javascript:;">${company.name }</a>
				</c:forEach>
			</p>
		</c:if>
		<c:if test="${roleId==1000 || roleId==1001  || roleId==999}">
			<p style="margin: 10px 0 0 10px; display:block;" id="departManage">权证部门：
				<a class="green btn mini all filterBtn depVal hideEmp" val="" href="javascript:;">不限</a>
				<c:forEach items="${queryDepartments }" var="department">	
					<a class="btn mini filterBtn depVal showEmp" val="${department.id }" href="javascript:;">${department.name }</a>
				</c:forEach>
			</p>
		</c:if>
		<p style="clear:left;"></p>
		<c:if test="${roleId==1000 || roleId==1001 || roleId==1002 || roleId==999}">
			<p style="margin: 10px 0 0 10px;display:block;" id="empManage">权证专员：
			<a class="green btn mini all filterBtn empVal" val="" href="javascript:;">不限</a>
			<c:forEach items="${queryEmployees }" var="employee">
				<a class="btn mini filterBtn empVal" val="${employee.id }" href="javascript:;">${employee.name }</a>
			</c:forEach>
			</p>
		</c:if>
		<p style="margin: 10px 0 0 10px;">　　类型：
			<a class="green btn mini all filterBtn typeVal" val="-1" href="javascript:;">不限</a>
			<a class="btn mini filterBtn typeVal" val="0" href="javascript:;">新增</a>
			<a class="btn mini filterBtn typeVal" val="1" href="javascript:;">退单退件</a>
			<a class="btn mini filterBtn typeVal" val="2" href="javascript:;">返单</a>
			<a class="btn mini filterBtn typeVal" val="3" href="javascript:;">放款</a>
		</p>
		<p style="margin: 10px 0 0 10px;">贷款类型：
			<a class="green btn mini all filterBtn followL" val="" href="javascript:;">不限</a>
			<a class="btn mini filterBtn loantypeVal" val="信贷" href="javascript:;">信贷</a>
			<a class="btn mini filterBtn loantypeVal" val="信用卡" href="javascript:;">信用卡</a>
			<a class="btn mini filterBtn loantypeVal" val="房贷" href="javascript:;">房贷</a>
			<a class="btn mini filterBtn loantypeVal" val="二抵" href="javascript:;">二抵</a>
			<a class="btn mini filterBtn loantypeVal" val="车贷" href="javascript:;">车贷</a>
			<a class="btn mini filterBtn loantypeVal" val="短借" href="javascript:;">短借</a>
			<a class="btn mini filterBtn loantypeVal" val="企贷" href="javascript:;">企贷</a>
		</p>
		<p style="margin: 10px 0 0 10px;">签单时间：
			<input data-provide="datepicker" type="text" onchange="search()" class="span2 small" id="beginDate" readonly="readonly" size="12">
			 至 <input data-provide="datepicker" type="text" onchange="search()" class="span2 small" id="endDate" readonly="readonly" size="12">
		</p>
		<p style="padding:0px 0 0 100px;">
			<a class="btn" id="clearFilter" href="javascript:void(0);"><i class="icon-remove icon-white"></i>清空条件</a>
		</p>
	</div>
</div>
</div>
<div id="tableList1">
	<jsp:include page="table-list.jsp" />								
</div>
<script>
	jQuery(document).ready(function() {
		$('.popovers').popover();
	});
	$(function(){
		$('#adv-custom-table tbody tr').dblclick(function(){
			var val = $(this).attr("customerId");
			var valarray=val.split("-"); 
			aPost("customer/gotoValidListUpdateUI.do?id="+valarray[1]+"&sId="+valarray[0]
					+"&refererUrl="+"warranter/orderList.do?pageSize="+$("#pageSize").val());
		});
	});
	$(function(){
		$("#keyStr").focus();
		// 全文搜索
		$("#keyStr").keyup(function(event){
			 if(event.keyCode == 13){
				var val = strTrim($(this).val());
				$("#tableList").load("warranter/orderList.do?pageIndex=1&queryWord="+val+"&pageSize="+$("#pageSize").val());
			 }
		 });
		
		$("#searchButton").click(function(){
			var val = strTrim($("#keyStr").val());
			$("#tableList").load("warranter/orderList.do?pageIndex=1&queryWord="+val+"&pageSize="+$("#pageSize").val());
		});
	//启动、关闭 高级搜索
	$("#superSearchButton").click(function(){
		var searchDiv = $("#superSearchDiv");
		if($(searchDiv).hasClass("hide")){
			$("#superSearchDiv").removeClass("hide");
			$(this).html('<i class="icon-remove icon-white"></i> 关闭高级搜索');
		}else{
			$("#superSearchDiv").addClass("hide");
			$(this).html('<i class="icon-search"></i> 高级搜索');
		}
	});
	
	$(".filterBtn").bind("click",function(){
		if($(this).hasClass("all")){
			$(this).parent().children().removeClass("green");
			$(this).addClass("green");
		}else{
			var children = $(this).parent().children();
			$(children[0]).removeClass("green");
			if($(this).hasClass("green")){
				$(this).removeClass("green");
				var flag = false;
				for(var i=0;i<children.length;i++){
					if($(children[i]).hasClass('green')){
						flag=true;
						break;
					}
				}
				if(!flag){
					$(children[0]).addClass("green");
				}
			}else{
				$(this).addClass("green");
			}
		}
		
		if($(this).hasClass("hideDepart")){
		}else if($(this).hasClass('showDepart')){
			var em = $(".showDepart");
			var dIds="";
			for(var i=0;i<em.length;i++){
				if($(em[i]).hasClass("green")){
					var id = $(em[i]).attr("val");
					if(id!=""){
						dIds = dIds + id+",";
					}
				}
			}
			$.post("department/getByCompanyJsonIds.do",{companyIds:dIds},function(data){
				var s = $("#departManage");s.empty();
				s.text("权证部门：");
				$("<a class='green btn mini all filterBtn companyVal hideEmp' style='margin-left:5px;' val='' href='javascript:;'>不限</a>").appendTo(s);
				$.each(data,function(i,item){
					$("<a class='btn mini filterBtn empVal showEmp' style='margin-left:5px;' val="+item.id+" href='javascript:;'>"+item.name+"</a>").appendTo(s);
				});
			});
		}
		
		if($(this).hasClass("hideEmp")){
		}else if($(this).hasClass('showEmp')){
			var em = $(".showEmp");
			var dIds="";
			for(var i=0;i<em.length;i++){
				if($(em[i]).hasClass("green")){
					var id = $(em[i]).attr("val");
					if(id!=""){
						dIds = dIds + id+",";
					}
				}
			}
			$.post("employee/getEmployeeJsonByDId.do",{dIds:dIds},function(data){
				var s = $("#empManage");s.empty();
				s.text("权证专员：");
				$("<a class='green btn mini all filterBtn companyVal' style='margin-left:5px;' val='' href='javascript:;'>不限</a>").appendTo(s);
				$.each(data,function(i,item){
					$("<a class='btn mini filterBtn empVal' style='margin-left:5px;' val="+item.id+" href='javascript:;'>"+item.name+"</a>").appendTo(s);
				});
			});
		}
		search();
	});
	
	
	$('table.dblclickUpdate tbody tr').dblclick(function(){
		//console.log("有效客户列表双击tr");
		var cId = $(this).attr('cId');
		var hId = $(this).attr('hId');
		$.messager.showLoadProgress();
		$("#validCustomerDetail").load("${pageContext.request.contextPath}/customer/gotoValidListUpdateUI.do?id="
				+cId+"&hId="+hId+"&currentListDomId="+validCustomerListDomId,
			function(){
			$.messager.closeLoadProgress();
			validCustomerListDiv.hide(); //隐藏有效客户列表区域
		});
	});
	
	
	// 清空条件
	$("#clearFilter").click(function(){
		$(".filterBtn").removeClass("green");
		$(".filterBtn").removeClass("green");
		$(".all").addClass("green");
		$("#beginDate").val("");
		$("#endDate").val("");
		search();
	});
	});
	function strTrim(val){
		var str = val.replace(/\s+/g,"");
		return str;
	}
	
	
	// 高级查询
	function search(){
		// 获取值发送请求
		var comanyIds = $(".companyVal");	// 中心
		var depas = $(".depVal");			// 部门
		var empls = $(".empVal");			// 权证专员
		var types = $(".typeVal");			// 类型
		var loantypes = $(".loantypeVal");	// 贷款类型
		
		// 公司
		var cIds = "";
		if(comanyIds!=null && comanyIds.length>0){
			for(var i=0;i<comanyIds.length;i++){
				if($(comanyIds[i]).hasClass("green")){
					var id = $(comanyIds[i]).attr("val");
					if(id!=""){
						cIds = cIds + id+",";
					}
				}
			}
		}
		
		// 部门
		var depaIds = "";
		if(depas!=null && depas.length>0){
			for(var i=0;i<depas.length;i++){
				if($(depas[i]).hasClass("green")){
					var id = $(depas[i]).attr("val");
					if(id!=""){
						depaIds = depaIds + id+",";
					}
				}
			}
		}
		// 权证专员
		var emplIds = "";
		if(empls!=null && empls.length>0){
			for(var i=0;i<empls.length;i++){
				if($(empls[i]).hasClass("green")){
					var id = $(empls[i]).attr("val");
					if(id!=""){
						emplIds = emplIds + id+",";
					}
				}
			}
		}
		
		// 贷款类型
		var loanIds = "";
		if(loantypes!=null && loantypes.length>0){
			for(var i=0;i<loantypes.length;i++){
				if($(loantypes[i]).hasClass("green")){
					var id = $(loantypes[i]).attr("val");
					if(id!=""){
						loanIds = loanIds + id+",";
					}
				}
			}
		}
		// 类型
		var typeIds = "";
		if(types!=null && types.length>0){
			for(var i=0;i<types.length;i++){
				if($(types[i]).hasClass("green")){
					var id = $(types[i]).attr("val");
					if(id!="" && id!="-1"){
						typeIds = typeIds + id+",";
					}
				}
			}
		}
		
		// 签单时间
		var beginDate = $("#beginDate").val();
		var endDate = $("#endDate").val();
		$.messager.showLoadProgress();
		$("#tableList1").load("warranter/searchList.do?companyIds="+cIds+"&depIds="+depaIds+"&empIds="+emplIds+
				"&types="+typeIds+"&loanTypes="+loanIds+"&signDateStart="+beginDate+"&signDateEnd="+endDate+"&pageSize=10",function(){
        $.messager.closeLoadProgress();
        });
	}
</script>
