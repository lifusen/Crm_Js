<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://itproject.cn/phone" prefix="phone"%>
<style type="text/css">
.superSearch {
	width: 100%;
	border: 1px solid #ccc;
	margin: 10px 0;
}

.showEmp {
	display: none;
}
</style>
<div>
	<!--  
	<p style="text-align: center;">
		<a class="btn green" id="superSearchButton" href="javascript:void(0);">
			<i class="icon-search"></i> 高级搜索
		</a>
	</p>
	-->
	<!-- 高级搜索 -->
	<div id="superSearchDiv" class="superSearch hide" style="clear: left;">
		<c:if test="${loginUser.type=='SUPER_ROLE'}">
			<p style="margin: 10px 0 0 10px;">
				公司： <a class="green btn mini all filterBtn companyVal hideDepart"
					val="" href="javascript:;">不限</a>
				<c:forEach items="${requestScope.companys }" var="company">
					<a class="btn mini filterBtn companyVal showDepart"
						val="${company.id }" href="javascript:;">${company.name}</a>
				</c:forEach>
			</p>
		</c:if>
		<c:if
			test="${requestScope.roleId==1 || requestScope.roleId==5 ||requestScope.roleId==2 || requestScope.roleId==7 || loginUser.type=='SUPER_ROLE'}">
			<p style="margin: 10px 0 0 10px;" id="departManage"></p>
		</c:if>
		<c:if
			test="${requestScope.roleId==1 || requestScope.roleId==5 ||requestScope.roleId==2 || requestScope.roleId==7  || requestScope.roleId==3 || loginUser.type=='SUPER_ROLE'}">
			<p style="margin: 10px 0 0 10px;" id="customerManageP"></p>
			<p style="clear: left;"></p>
		</c:if>

		<p style="margin: 10px 0 0 10px;">
			客户等级： <a class="green btn mini all filterBtn customerL" val=""
				href="javascript:;">不限</a> <a class="btn mini filterBtn customerL"
				val="优" href="javascript:;">优</a> <a
				class="btn mini filterBtn customerL" val="良" href="javascript:;">良</a>
			<a class="btn mini filterBtn customerL" val="中" href="javascript:;">中</a>
			<a class="btn mini filterBtn customerL" val="差" href="javascript:;">差</a>
		</p>
		<p style="margin: 10px 0 0 10px;">
			关注等级： <a class="green btn mini all filterBtn followL" val=""
				href="javascript:;">不限</a> <a class="btn mini filterBtn followL"
				val="A" href="javascript:;">A</a> <a
				class="btn mini filterBtn followL" val="B" href="javascript:;">B</a>
			<a class="btn mini filterBtn followL" val="C" href="javascript:;">C</a>
			<a class="btn mini filterBtn followL" val="D" href="javascript:;">D</a>
		</p>
		<p style="margin: 10px 0 0 10px;">
			单位性质： <a class="green btn mini all filterBtn unit" val=""
				href="javascript:;">不限</a> <a class="btn mini filterBtn unit"
				val="事业单位" href="javascript:;">事业单位</a> <a
				class="btn mini filterBtn unit" val="公务员" href="javascript:;">公务员</a>
			<a class="btn mini filterBtn unit" val="上市公司" href="javascript:;">上市公司</a>
			<a class="btn mini filterBtn unit" val="普通公司" href="javascript:;">普通公司</a>
			<a class="btn mini filterBtn unit" val="特种行业" href="javascript:;">特种行业</a>
		</p>
		<p style="margin: 10px 0 0 10px;">
			是否自留： <a class="green btn mini all filterBtn visibility" val=""
				href="javascript:;">不限</a> <a class="btn mini filterBtn visibility"
				val="0" href="javascript:;">是</a> <a
				class="btn mini filterBtn visibility" val="1" href="javascript:;">否</a>
		</p>
		<p style="margin: 10px 0 0 10px;">
			客户状态： <a class="green btn mini all filterBtn customerSta" val=""
				href="javascript:;">不限</a>
			<!-- 		<a class="btn mini filterBtn customerSta" val="2,8" href="javascript:;">新领订单</a> -->
			<a class="btn mini filterBtn customerSta" val="3" href="javascript:;">未签单</a>
			<a class="btn mini filterBtn customerSta" val="4" href="javascript:;">已签单</a>
		</p>
		<p style="margin: 10px 0 0 10px;">
			来源类型： <a class="green btn mini all filterBtn customerSource" val=""
				href="javascript:;">不限</a>
			<c:forEach items="${requestScope.sources }" var="source">
				<a class="btn mini filterBtn customerSource" val="${source.id }"
					href="javascript:;">${source.sourceName }</a>
			</c:forEach>
		</p>
		<p style="margin: 10px 0 0 10px;">
			<%--
		　　录入时间：
		<input data-provide="datepicker" type="text" onchange="customerSearchList('');" class="span2 small" id="beginDate" readonly="readonly" size="12">
		 至 <input data-provide="datepicker" type="text" onchange="customerSearchList('');" class="span2 small" id="endDate" readonly="readonly" size="12">
		 --%>
			录入时间： <input type="text" onchange="customerSearchList('');"
				class="span2 small enteringDatepicker" id="beginDate"
				readonly="readonly" size="12"> 至 <input type="text"
				onchange="customerSearchList('');"
				class="span2 small enteringDatepicker" id="endDate"
				readonly="readonly" size="12">

		</p>
		<p style="padding: 0px 0 0 100px;">
			<a class="btn" id="clearFilter" href="javascript:void(0);"><i
				class="icon-remove icon-white"></i> 清空条件</a>
		</p>
	</div>
</div>
<div id="customerDiv">
	<jsp:include page="table-body.jsp" />
</div>
<input type="hidden" id="roleId" value="${requestScope.roleId }" />
<input type="hidden" id="departmentId"
	value="${sessionScope.loginUser.department.id }" />
<input type="hidden" id="companyId"
	value="${sessionScope.loginUser.companyId }" />

<script type="text/javascript">
	$(function() {
		$("input.enteringDatepicker").datetimepicker({
			format : 'yyyy-mm-dd',
			autoclose : true,
			todayHighlight : true,
			minView : 'month',
			pickerPosition : 'bottom-left'
		});

		var roleId = $("#roleId").val();
		if (parseInt(roleId) == 3) {
			$("#customerManageP").load(
					"employee/getEmployeeByDId.do?dIds="
							+ $("#departmentId").val());
		}
		if (parseInt(roleId) == 1 || parseInt(roleId) == 2
				|| parseInt(roleId) == 5 || parseInt(roleId) == 7) {
			$("#departManage").load(
					"department/getByCompanyIds.do?companyIds="
							+ $("#companyId").val());
		}
	});
	//启动、关闭 高级搜索
	$("#superSearchButton").click(function() {
		var searchDiv = $("#superSearchDiv");
		if ($(searchDiv).hasClass("hide")) {
			$("#superSearchDiv").removeClass("hide");
			$(this).html('<i class="icon-remove icon-white"></i> 关闭高级搜索');
		} else {
			$("#superSearchDiv").addClass("hide");
			$(this).html('<i class="icon-search"></i> 高级搜索');
		}
	});

	// 按条件搜索
	$("#superSearchDiv .filterBtn")
			.bind(
					"click",
					function() {
						if ($(this).hasClass("all")) {
							$(this).parent().children().removeClass("green");
							$(this).addClass("green");
						} else {
							var children = $(this).parent().children();
							$(children[0]).removeClass("green");
							if ($(this).hasClass("green")) {
								$(this).removeClass("green");
								var flag = false;
								for (var i = 0; i < children.length; i++) {
									if ($(children[i]).hasClass('green')) {
										flag = true;
										break;
									}
								}
								if (!flag) {
									$(children[0]).addClass("green");
								}
							} else {
								$(this).addClass("green");
							}
						}

						if ($(this).hasClass("hideDepart")) {
							// 获取部门
							$("#departManage").load(
									"department/getByCompanyIds.do?companyIds="
											+ '');
						} else if ($(this).hasClass('showDepart')) {
							var em = $(".showDepart");
							var dIds = "";
							for (var i = 0; i < em.length; i++) {
								if ($(em[i]).hasClass("green")) {
									var id = $(em[i]).attr("val");
									if (id != "") {
										dIds = dIds + id + ",";
									}
								}
							}
							// 获取部门
							$("#departManage").load(
									"department/getByCompanyIds.do?companyIds="
											+ dIds);
						}
						customerSearchList("");
					});

	// 清空条件
	$("#clearFilter").click(function() {
		$(".filterBtn").removeClass("green");
		$(".filterBtn1").removeClass("green");
		$(".all").addClass("green");
		$("#beginDate").val("");
		$("#endDate").val("");
		customerSearchList("");
	});
</script>
