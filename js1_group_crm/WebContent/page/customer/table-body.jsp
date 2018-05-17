<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://itproject.cn/phone" prefix="phone"%>
<%@ taglib uri="http://itproject.cn/fllowDate" prefix="fllowDate"%>

<style>
.sorting_asc {
	background: url("../image/sort_asc.png") no-repeat scroll right center
		rgba(0, 0, 0, 0);
}

.sorting_asc_disabled {
	background: url("../image/sort_asc_disabled.png") no-repeat scroll right
		center rgba(0, 0, 0, 0);
}

.sorting_desc {
	background: url("../image/sort_desc.png") no-repeat scroll right center
		rgba(0, 0, 0, 0);
}

.sorting_desc_disabled {
	background: url("../image/sort_desc_disabled.png") no-repeat scroll
		right center rgba(0, 0, 0, 0);
}
</style>
<table
	class="table table-striped table-bordered table-hover table-full-width dblclickUpdate"
	id="customerTable" style="background: #EEE;">
	<thead>
		<tr>
			<td style="min-width: 25px;">序号</td>
			<td style="min-width: 48px;">公司</td>
			<td style="min-width: 48px;">部门</td>
			<td style="min-width: 48px;">客户经理</td>
			<td style="min-width: 48px;">联系人</td>
			<td style="min-width: 70px;">电话</td>
			<c:if
				test='${requestScope.orderKey!="callCount_asc" and requestScope.orderKey!="callCount_desc"}'>
				<td style="min-width: 25px;" class="sorting_asc_disabled sortTh"
					val="callCount_asc">通话</td>
			</c:if>
			<c:if test="${requestScope.orderKey=='callCount_asc'}">
				<td style="min-width: 25px;" class="sorting_asc sortTh"
					val="callCount_asc">通话</td>
			</c:if>
			<c:if test="${requestScope.orderKey=='callCount_desc'}">
				<td style="min-width: 25px;" class="sorting_desc sortTh"
					val="callCount_desc">通话</td>
			</c:if>

			<c:if
				test="${requestScope.orderKey!='visitCount_asc' and requestScope.orderKey!='visitCount_desc'}">
				<td style="min-width: 25px;" class="sorting_asc_disabled sortTh"
					val="visitCount_asc">上门</td>
			</c:if>
			<c:if test="${requestScope.orderKey=='visitCount_desc'}">
				<td style="min-width: 25px;" class="sorting_desc sortTh"
					val="visitCount_asc">上门</td>
			</c:if>
			<c:if test="${requestScope.orderKey=='visitCount_asc'}">
				<td style="min-width: 25px;" class="sorting_asc sortTh"
					val="visitCount_asc">上门</td>
			</c:if>
			<!-- 			<td style="min-width: 90px;">反馈时间</td> -->
			<!-- 
			<td style="min-width: 50px;">资料<br />完善</td>
			 -->
			<td style="min-width: 48px;">关注等级</td>
			<td style="min-width: 48px;">来源</td>
			<td style="min-width: 48px;">签单状态</td>
			<td style="min-width: 40px;">移交信息</td>
			<td style="min-width: 50px;">公司名称</td>
			<td style="min-width: 26px;">意向产品</td>
			<c:if
				test="${requestScope.orderKey!='fllowDate_asc' and requestScope.orderKey!='fllowDate_desc'}">
				<td style="min-width: 36px;" class="sorting_asc_disabled sortTh"
					val="fllowDate_asc">倒计时</td>
			</c:if>
			<c:if test="${requestScope.orderKey=='fllowDate_desc'}">
				<td style="min-width: 36px;" class="sorting_desc sortTh"
					val="fllowDate_asc">倒计时</td>
			</c:if>
			<c:if test="${requestScope.orderKey=='fllowDate_asc'}">
				<td style="min-width: 36px;" class="sorting_asc sortTh"
					val="fllowDate_asc">倒计时</td>
			</c:if>
		</tr>
	</thead>
	<tbody>
		<c:forEach items="${requestScope.customers }" var="cust"
			varStatus="vsta">
			<tr cId="${cust.customerId}" hId="${cust.hid}">
				<td>${((pageIndex-1)*pageSize)+(vsta.index+1) }</td>
				<td class="hidden-480">${allCompanyNameMap[cust.companyId]}</td>
				<td>${cust.departmentName }</td>
				<td>${cust.employeeName }</td>
				<td><a href="javascript:void(0);" title="${cust.customerName }"
					style="text-decoration: none; color: #000000">
						<div
							style="width: 48px; overflow: hidden; text-overflow: ellipsis; white-space: nowrap;">
							${cust.customerName }</div>
				</a></td>
				<td><a href="javascript:void(0);"
					title="${phone:phone(cust.phone)}"
					style="text-decoration: none; color: #000000">
						<div
							style="width: 70px; overflow: hidden; text-overflow: ellipsis; white-space: nowrap;">
							${phone:phone(cust.phone)}</div>
				</a></td>

				<td class="hidden-480">${cust.callCount }</td>
				<td class="hidden-480">${cust.visitCount }</td>
				<!-- 
				<td class="hidden-480">
					<c:if test="${cust.dataPercent!=null && cust.dataPercent>0 }">
							${cust.dataPercent==0} %
					</c:if>
				</td>
				 -->
				<td class="hidden-480">${cust.attentionLevel }</td>
				<td>${cust.sourceName }</td>
				<td class="hidden-480"><c:if
						test="${cust.state==4 or cust.state==5 or cust.state==6 or cust.state==7}">
						已签单
					</c:if> <c:if
						test="${cust.state!=4 and cust.state!=5 and cust.state!=6 and cust.state!=7 }">
						未签单
					</c:if></td>
				<td class="hidden-480"><a href="javascript:void(0);"
					title="${cust.turnDetail }"
					style="text-decoration: none; color: #000000">
						<div
							style="width: 40px; overflow: hidden; text-overflow: ellipsis; white-space: nowrap;">
							${cust.turnDetail }</div>
				</a></td>
				<td class="hidden-480">${cust.customerAsset}</td>
				<td class="hidden-480">${cust.loanType }</td>
				<td class="hidden-480">
					<!-- 2016.12.19 by SwordLiu --> <c:if test="${cust.state==3}">
						${fllowDate:fllowDate(cust.fllowDate)}
					</c:if>
				</td>
			</tr>
		</c:forEach>
	</tbody>
</table>
<jsp:include page="../common/paginator.jsp" />
<input value="${url }" type="hidden" id="nowUrl" />
<input value="${pageIndex }" type="hidden" id="nowPageIndex" />
<input value="${pageSize }" type="hidden" id="nowPageSize" />
<script>
	//当前列表的容器的DomId
	var validCustomerListDomId = "validCustomerListDiv";
	//获取当前列表的Dom容器
	var validCustomerListDiv = $("#" + validCustomerListDomId);
	$('table.dblclickUpdate tbody tr').dblclick(
			function() {
				//console.log("有效客户列表双击tr");
				var cId = $(this).attr('cId');
				var hId = $(this).attr('hId');
				$.messager.showLoadProgress();
				$("#validCustomerDetail").load(
						"${pageContext.request.contextPath}/customer/gotoValidListUpdateUI.do?id="
								+ cId + "&hId=" + hId + "&currentListDomId="
								+ validCustomerListDomId, function() {
							$.messager.closeLoadProgress();
							//console.log("有效客户列表加载数据成功")
							validCustomerListDiv.hide(); //隐藏有效客户列表区域
						});
			});

	$(function() {
		$('.popovers').popover();
		$(".sortTh").click(function() {
			var val = "";
			if ($(this).hasClass("sorting_asc_disabled")) {
				$(this).removeClass("sorting_asc_disabled");
				$(this).addClass("sorting_asc");
				val = $(this).attr("val");
			}

			if ($(this).hasClass("sorting_asc")) {
				$(this).removeClass("sorting_asc");
				$(this).addClass("sorting_desc");
				val = $(this).attr("val");
				val = val.substring(0, val.lastIndexOf("_")) + "_desc";
			} else {
				$(this).removeClass("sorting_desc");
				$(this).addClass("sorting_asc");
				val = $(this).attr("val");
				val = val.substring(0, val.lastIndexOf("_")) + "_asc";
			}
			customerSearchList(val);
		});
	});

	function strTrim(val) {
		var str = val.replace(/\s+/g, "");
		return str;
	}

	function customerSearchList(sort) {
		// 获取值发送请求
		var comanyIds = $(".companyVal");
		var depas = $(".departVal");
		var empls = $(".employeeVal");
		var loans = $(".loantypeVal");
		var cls = $(".customerL");
		var cstas = $(".customerSta");
		var csources = $(".customerSource");
		var follow = $(".followL");
		var wage = $(".wage");
		var unit = $(".unit");
		var visibility = $(".visibility");

		// 公司
		var cIds = "";
		if (comanyIds != null && comanyIds.length > 0) {
			for (var i = 0; i < comanyIds.length; i++) {
				if ($(comanyIds[i]).hasClass("green")) {
					var id = $(comanyIds[i]).attr("val");
					if (id != "") {
						cIds = cIds + id + ",";
					}
				}
			}
		}

		// 部门
		var depaIds = "";
		if (depas != null && depas.length > 0) {
			for (var i = 0; i < depas.length; i++) {
				if ($(depas[i]).hasClass("green")) {
					var id = $(depas[i]).attr("val");
					if (id != "") {
						depaIds = depaIds + id + ",";
					}
				}
			}
		}
		// 客户经理
		var emplIds = "";
		if (empls != null && empls.length > 0) {
			for (var i = 0; i < empls.length; i++) {
				if ($(empls[i]).hasClass("green")) {
					var id = $(empls[i]).attr("val");
					if (id != "") {
						emplIds = emplIds + id + ",";
					}
				}
			}
		}
		/*
		// 贷款类型
		var loanIds = "";
		if(loans!=null && loans.length>0){
			for(var i=0;i<loans.length;i++){
				if($(loans[i]).hasClass("green")){
					var id = $(loans[i]).attr("val");
					if(id!=""){
						loanIds = loanIds + id+",";
					}
				}
			}
		}
		 */
		// 客户等级
		var clsIds = "";
		if (cls != null && cls.length > 0) {
			for (var i = 0; i < cls.length; i++) {
				if ($(cls[i]).hasClass("green")) {
					var id = $(cls[i]).attr("val");
					if (id != "") {
						clsIds = clsIds + id + ",";
					}
				}
			}
		}
		// 客户状态
		var cstasIds = "";
		if (cstas != null && cstas.length > 0) {
			for (var i = 0; i < cstas.length; i++) {
				if ($(cstas[i]).hasClass("green")) {
					var id = $(cstas[i]).attr("val");
					if (id != "") {
						cstasIds = cstasIds + id + ",";
					}
				}
			}
		}
		// 来源类型
		var csourcesIds = "";
		if (csources != null && csources.length > 0) {
			for (var i = 0; i < csources.length; i++) {
				if ($(csources[i]).hasClass("green")) {
					var id = $(csources[i]).attr("val");
					if (id != "") {
						csourcesIds = csourcesIds + id + ",";
					}
				}
			}
		}

		// 关注等级
		var follows = "";
		if (follow != null && follow.length > 0) {
			for (var i = 0; i < follow.length; i++) {
				if ($(follow[i]).hasClass("green")) {
					var id = $(follow[i]).attr("val");
					if (id != "") {
						follows = follows + id + ",";
					}
				}
			}
		}
		/*
		// 工资体现
		var wages = "";
		if(wage!=null && wage.length>0){
			for(var i=0;i<wage.length;i++){
				if($(wage[i]).hasClass("green")){
					var id = $(wage[i]).attr("val");
					if(id!=""){
						wages = wages + id+",";
					}
				}
			}
		}
		 */

		// 单位性质
		var units = "";
		if (unit != null && unit.length > 0) {
			for (var i = 0; i < unit.length; i++) {
				if ($(unit[i]).hasClass("green")) {
					var id = $(unit[i]).attr("val");
					if (id != "") {
						units = units + id + ",";
					}
				}
			}
		}
		// 是否自留
		var visibilitys = "";
		if (visibility != null && visibility.length > 0) {
			for (var i = 0; i < unit.length; i++) {
				if ($(visibility[i]).hasClass("green")) {
					var id = $(visibility[i]).attr("val");
					if (id != "") {
						visibilitys = visibilitys + id + ",";
					}
				}
			}
		}

		// 录入时间
		var beginDate = $("#beginDate").val();
		var endDate = $("#endDate").val();

		$.messager.showLoadProgress();

		$("#customerDiv").load(
				"customer/searchValidCustomer.do?companyIds=" + cIds
						+ "&depaIds=" + depaIds + "&emplIds=" + emplIds
						+ "&loanIds=" + loanIds + "&clsIds=" + clsIds
						+ "&cstasIds=" + cstasIds + "&csourcesIds="
						+ csourcesIds + "&follows=" + follows + "&wages="
						+ wages + "&units=" + units + "&visibilitys="
						+ visibilitys + "&beginDate=" + beginDate + "&endDate="
						+ endDate + "&orderKey=" + sort + "&pageSize="
						+ '${pageSize}', function() {
					$.messager.closeLoadProgress();
				});
	}
</script>