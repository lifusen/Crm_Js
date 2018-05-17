<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<link href="css/jdy.css" rel="stylesheet" type="text/css" media="screen"/>
<!-- BEGIN PAGE CONTAINER-->
<div class="container-fluid" style="padding:10px;">
	<div class="row-fluid">
		<div class="span12">		
			<div class="portlet box blue calendar">
				<div class="portlet-title">
					<div class="caption"><i class="icon-reorder"></i>日程安排</div>
					<div class="action" style="font-size:15px;width:150px;margin:0px auto;">
						当月工作日为22天
					</div>
				</div>

				<div class="portlet-body" style="padding:10px;">
					<div id="calendar" class="has-toolbar" style="width:620px;margin:0px auto;"></div>	
						
					<div class="form-actions" style="padding:8px;text-align:center;margin-top:5px;margin-bottom:10px;" >
						<button id="btn-calendar-save" class="btn blue" type="submit"><i class="icon-ok"></i> 保存</button>
						<a id="canceMessageSave" class="btn" href="javascript:;"><i class="icon-remove"></i> 取消</a>
					</div>
				</div>
			</div>
		</div>
		<!--结束：第1行第1列-->		
	</div>
	<!--结束：第1行-->		
</div>
<!-- END PAGE CONTAINER--> 

<script>
	$(function() {    
		var workdays = [];
		var lastMonth = -1;
		var choiceDate;
		
		$("#btn-calendar-save").on("click", function(){
			alert($("#calendar").fullCalendar("getDate"));
			//声明日期字符串
			var dateString = "";
			
			//获取到所有选中的日期
			var tds = $('td.bg-b');
			tds.each(function(i,v){
				workdays.push($(v).attr('data-date'));
			});
			if(workdays.length==0){
				alert('请选择工作日!');
				return;
			}
			
			console.log(workdays);
			dateString=workdays.join(',');
			console.log(dateString);
			
			$.post('workday/add.do',{day:workdays.length,dateString:dateString},function(data){
				if(!data){
					alert('保存失败!');
				}
			});
			
		})
		
		$("#calendar").fullCalendar({
			defaultView: "month",				
			monthNames: ["一月", "二月", "三月", "四月", "五月", "六月", "七月", "八月", "九月", "十月", "十一月", "十二月"],  
			monthNamesShort: ["一月", "二月", "三月", "四月", "五月", "六月", "七月", "八月", "九月", "十月", "十一月", "十二月"],  
			dayNames: ["周日", "周一", "周二", "周三", "周四", "周五", "周六"],  
			dayNamesShort: ["周日", "周一", "周二", "周三", "周四", "周五", "周六"],  
			today: ["今天"],  			
			buttonText: {  
				  today: '本月',  
				  month: '月',  
				  week: '周',  
				  day: '日',  
				  prev: '上一月',  
				  next: '下一月'  
			},		
			header:false,	//不显示头部
			allDayDefault:true,
			dayRender: function(date, cell){
				choiceDate = date;
				var weekday = date.getDay();	
				var currMonth = $("#calendar").fullCalendar("getDate").getMonth();
				if(currMonth != lastMonth) {
					//workdays = [];
					lastMonth = currMonth;
				}
				if( date.getMonth() == currMonth && weekday > 0 && weekday <6) {
					$(cell).addClass("bg-b");
				}					
			},
			dayClick: function(date){
				if(date.getMonth() != lastMonth) return;
				$(this).toggleClass("bg-b");
			}
		});
		
	});
</script>
