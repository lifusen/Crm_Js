<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<!-- 标题列table -->
<table class="customTable" style="width:170px;">
	<thead>
		<tr role="row" class="dateHead">
			<th style="height:73px;width:170px; border-right: 1px solid #333;">类型/日期</th>
		</tr>
	</thead>
	<tbody role="alert" aria-live="polite" aria-relevant="all">
		<c:forEach items="${requestScope.reportTitle}" var="title">
			<tr class="odd gradeX dataBody">
				<td style="border-right: 1px solid #333;"><c:if test="${loginUser.type=='SUPER_ROLE'}">${allCompanyNameMap[title.companyId]} - </c:if>${title.name }</td>
			</tr>
		</c:forEach>
			<tr class="odd gradeX dataBody">
				<td style="border-right: 1px solid #333;">合计</td>
			</tr>
	</tbody>
</table>
		
<div style="overflow: auto;">
		
<table>
  <tr id="reportTr">
    
    <c:forEach items="${requestScope.report}" var="entry" varStatus="vst">
		<td>
			<table class="customTable">
				<thead>
					<tr role="row" class="dateHead">
						<th colspan="5" style="border-right:1px solid #333;">
							${entry.key}
						</th>
					</tr>
					<tr role="row" style="background: #EEEEEE;" class="titleHead">
						<th>电话联系</th>
						<th>邀约上门</th>
						<th>上门洽谈</th>
						<th>成功签约</th>
						<th style="border-right:1px solid #333;">售后服务</th>
					</tr>
				</thead>
				<tbody role="alert" aria-live="polite" aria-relevant="all">
					<c:forEach items="${entry.value }" var="entry1" varStatus="vs">
						<c:set var="phoneSum" value="${entry1.value.phoneCount+phoneSum }" />
						<c:set var="messageSum" value="${entry1.value.messageCount+messageSum }" />
						<c:set var="visitSum" value="${entry1.value.visitCount+visitSum }" />
						<c:set var="signSum" value="${entry1.value.signCount+signSum }" />
						<c:set var="afterSaleSum" value="${entry1.value.afterSaleCount+afterSaleSum }" />
						<tr class="odd gradeX dataBody">
							<td class="tdDetail" typeVal="1-${entry1.key }" tdIndex="1-${vs.count}" dateVal="${entry.key}">${entry1.value.phoneCount }</td>
							<td class="tdDetail" typeVal="2-${entry1.key }" tdIndex="2-${vs.count}" dateVal="${entry.key}">${entry1.value.messageCount }</td>
							<td class="tdDetail" typeVal="3-${entry1.key }" tdIndex="3-${vs.count}" dateVal="${entry.key}">${entry1.value.visitCount }</td>
							<td class="tdDetail" typeVal="4-${entry1.key }" tdIndex="4-${vs.count}" dateVal="${entry.key}">${entry1.value.signCount }</td>
							<td class="tdDetail" typeVal="5-${entry1.key }" tdIndex="5-${vs.count}" dateVal="${entry.key}" style="border-right:1px solid #333;">${entry1.value.afterSaleCount }</td>
						</tr>
					</c:forEach>
					<tr class="odd gradeX dataBody">
						<td>${phoneSum}</td>
						<td>${messageSum}</td>
						<td>${visitSum}</td>
						<td>${signSum}</td>
						<td style="border-right:1px solid #333;">${afterSaleSum}</td>
					</tr>
					<c:set var="phoneSum" value="0" />
					<c:set var="messageSum" value="0" />
					<c:set var="visitSum" value="0" />
					<c:set var="signSum" value="0" />
					<c:set var="afterSaleSum" value="0" />
				</tbody>
			</table>
		</td>
	</c:forEach>
  </tr>
</table>
</div>

<!-- 引入报表客户页面 -->
<jsp:include page="reportCustomerUI.jsp"/>

<div style="clear: left;"></div>
<div>
	<div style="padding-left:10px;width:100%;padding-top:10px;padding-bottom:10px;">
		<c:forEach items="${requestScope.reportTitle}" var="title">
			<button class="btn reportGroup" type="button" val="${title.id }">${title.name }</button>
		</c:forEach>
		<button class="btn reportGroup sum" type="button">合　　计</button>
	</div>
	<div id="reportChart" style="width:100%; height:400px;"></div>
	<input type="hidden" value="${requestScope.e_or_d }" id="e_or_d"/>
</div>
<input type="hidden" id="dSize" value="${requestScope.dSize }"/>
<script>
	$(function(){
		//如果默认显示为周,则填充日期框的值
		if('${defaultShow}'=='week'){
			$("#beginDate").val('${beginDate}');
			$("#endDate").val('${endDate}');
		}
		$(".reportGroup").click(function(){
			var beginDate = $("#beginDate").val();
			var endDate = $("#endDate").val();
			var e_or_d = $("#e_or_d").val();
			var btn = $(this);
			var val = "";
			if($(btn).hasClass("sum")){	// 合计
				var groups = $(".reportGroup");
				for(var i=0;i<groups.length;i++){
					if(!$(groups[i]).hasClass("sum")){
						val = val + $(groups[i]).attr("val")+",";
					}
				}
			}else{
				val = $(this).attr("val");
			}
			var postKey = null;
			if(e_or_d=='E'){
				postKey = {eId:val,edtDateBegin:beginDate,edtDateEnd:endDate};
			}else if(e_or_d=='D'){
				postKey = {dId:val,edtDateBegin:beginDate,edtDateEnd:endDate};
			}
			$.post("report/reportDetail.do",postKey,function(data){
				$(".reportGroup").removeClass("green");
				btn.addClass("green");
				
				$('#reportChart').highcharts({
		            chart: {
		                type: 'column'
		            },
		            title: {
		                text: $(this).text()
		            },
		            subtitle: {
		                text: ''
		            }, scrollbar: {
		                enabled: true
		           },
		            xAxis: {
		                categories: data.dates
		                ,max : 6
		            },
		            yAxis: {
		                min: 0,
		                title: {
		                    text: ''
		                }
		            },
		            tooltip: {
		                headerFormat: '<span style="font-size:10px">{point.key}</span><table>',
		                pointFormat: '<tr><td style="color:{series.color};padding:0">{series.name}: </td>' +
		                    '<td style="padding:0"><b>{point.y}</b></td></tr>',
		                footerFormat: '</table>',
		                shared: true,
		                useHTML: true
		            },
		            plotOptions: {
		                column: {
		                    pointPadding: 0.2,
		                    borderWidth: 0
		                }
		            },
		            series: data.seriesInfos
			    });
			});
		});
		// 获取详情页面
		$(".tdDetail").dblclick(function(){
			//如果当前单元格为0,则不查询
			var v = $(this).text();
			if(v==0){
				$('#reportCustomerUIContainer').hide();
				return;
			}
			
			var typeVal = $(this).attr("typeVal");
			var dateVal = $(this).attr("dateVal");
			var e_or_d = $("#e_or_d").val();
			
			$.messager.showLoadProgress();
			$("#tableList").load("report/reportCustomerList.do?types="+typeVal+"&dateStr="+dateVal+"&eOrd="+e_or_d,function(){
				$.messager.closeLoadProgress();	
			});
			//window.location.hash = "#tableList"; 
			$('#reportCustomerUIContainer').show();
		});
		var group = $(".reportGroup");
		$(group[0]).click();
		
		
		
		
		
		
		
		var td = $("<td />");
		td.appendTo($("#reportTr"));
		var table = $("<table />",{'class':"customTable"});
		table.appendTo(td);
		var thead = $("<thead />");
		thead.appendTo(table);
		var tr = $("<tr />",{role:"row", 'class':"dateHead"});
		tr.appendTo(thead);
		var th = $("<th />",{colspan:"5", style:"border-right:1px solid #333;",text:'合计'});
		th.appendTo(tr);	
		var tr1 = $("<tr />",{role:"row", style:"background: #EEEEEE;", 'class':"titleHead"});
		tr1.appendTo(thead);
		$("<th />",{text:'电话联系'}).appendTo(tr1);
		$("<th />",{text:'邀约上门'}).appendTo(tr1);
		$("<th />",{text:'上门洽谈'}).appendTo(tr1);
		$("<th />",{text:'成功签约'}).appendTo(tr1);
		$("<th />",{style:"border-right:1px solid #333;",text:'售后服务'}).appendTo(tr1);
		var tbody = $("<tbody />",{'role':"alert",'aria-live':"polite",'aria-relevant':"all"});
		tbody.appendTo(table);
		// X轴合计
		var count = parseInt($("#dSize").val());
		var pSum = 0;
		var mSum = 0;
		var vSum = 0;
		var sSum = 0;
		var aSum = 0;
		for(var i=1;i<=count;i++){
			// 电话
			var tds = $("td[tdIndex='1-"+i+"']");
			var phoneCount = 0;
			var messageCount = 0;
			var visitCount = 0;
			var signCount = 0;
			var afterSaleCount = 0;
			
			for(var j=0;j<tds.length;j++){
				phoneCount = phoneCount+parseInt($(tds[j]).text());
			}
			
			tds = $("td[tdIndex='2-"+i+"']");
			for(var j=0;j<tds.length;j++){
				messageCount = messageCount+parseInt($(tds[j]).text());
			}
			
			tds = $("td[tdIndex='3-"+i+"']");
			for(var j=0;j<tds.length;j++){
				visitCount = visitCount+parseInt($(tds[j]).text());
			}
			
			tds = $("td[tdIndex='4-"+i+"']");
			for(var j=0;j<tds.length;j++){
				signCount = signCount+parseInt($(tds[j]).text());
			}
			
			tds = $("td[tdIndex='5-"+i+"']");
			for(var j=0;j<tds.length;j++){
				afterSaleCount = afterSaleCount+parseInt($(tds[j]).text());
			}
			pSum = pSum+phoneCount;
			mSum = mSum+messageCount;
			vSum = vSum+visitCount;
			sSum = sSum+signCount;
			aSum = aSum+afterSaleCount;
			var tr2 = $("<tr />",{'class':"odd gradeX dataBody"});
			$("<td />",{text:phoneCount}).appendTo(tr2);
			$("<td />",{text:messageCount}).appendTo(tr2);
			$("<td />",{text:visitCount}).appendTo(tr2);
			$("<td />",{text:signCount}).appendTo(tr2);
			$("<td />",{text:afterSaleCount}).appendTo(tr2);
			tr2.appendTo(tbody);
		}
		
		var tr3 = $("<tr />",{'class':"odd gradeX dataBody"});
		$("<td />",{text:pSum}).appendTo(tr3);
		$("<td />",{text:mSum}).appendTo(tr3);
		$("<td />",{text:vSum}).appendTo(tr3);
		$("<td />",{text:sSum}).appendTo(tr3);
		$("<td />",{text:aSum}).appendTo(tr3);
		tr3.appendTo(tbody);
	});
</script>