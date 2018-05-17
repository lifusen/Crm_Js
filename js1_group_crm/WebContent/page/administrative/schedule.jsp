<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<link href="css/jdy.css" rel="stylesheet" type="text/css" media="screen"/>
<!-- BEGIN PAGE CONTAINER-->
<div class="container-fluid p-t-20">
	<div class="row-fluid">
		<div class="span12">		
			<div class="portlet box blue calendar">
				<div class="portlet-title">
					<div class="caption"><i class="icon-reorder"></i>日程安排</div>
					<div class="action">
						<button id="btn-calendar-save" class="btn yellow mini" style="margin-left: 30px;"><i class="icon-save"></i>&nbsp;&nbsp;保存工作日程安排</button>
					</div>
				</div>

				<div class="portlet-body">
					<div id="calendar" class="has-toolbar"></div>		
					
				</div>
			</div>
		</div>
		<!--结束：第1行第1列-->		
	</div>
	<!--结束：第1行-->		
</div>
<!-- END PAGE CONTAINER--> 

<script>
	Array.prototype.remove = function(val) {
		var index = this.indexOf(val);
		if (index > -1) {
			this.splice(index, 1);
		}
	};
	
	jQuery(document).ready(function() {    
	
		
		$("[id^='menu-item-']").click(function(){
			$("[id^='menu-item-']").removeClass("active");
			$(this).addClass("active");
			$("[id^='menu-item-']").find("a span[class='selected']").remove();
			$("[id^='menu-item-']").find("a span[class^='arrow']").removeClass("open");
			$(this).find("a").append("<span class=\"selected\"></span>");
			$(this).find("a span[class^='arrow']").addClass("open");
		});		
		
		var today = new Date();			
		var workdays = [];
		var lastMonth = -1;
		
		$("#btn-calendar-save").on("click", function(){
			jdyConfirm("是否设置" + workdays + "为" + (lastMonth + 1) + "月的工作日？", function(){
			
			
			})			
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
			allDayDefault:true,
			dayRender: function(date, cell){
				var weekday = date.getDay();			
				var currMonth = $("#calendar").fullCalendar("getDate").getMonth();
				if(currMonth != lastMonth) {
					workdays = [];
					lastMonth = currMonth;
				}
				if( date.getMonth() == currMonth && weekday > 0 && weekday <6) {
					$(cell).addClass("bg-b");
					workdays.push(date.getDate());
				}					
			},
			dayClick: function(date){
				var d = date.getDate();
				if(date.getMonth() != lastMonth) return;
				$(this).toggleClass("bg-b");
				if($(this).hasClass("bg-b")) {
					workdays.push(d);
				} else {
					workdays.remove(d);
				}
				workdays.sort(function(a, b){
					return parseInt(a) - parseInt(b);
				});
			}
			
		});
		
	});
	

	
	function jdyConfirm(content, callback) {
		var html = "";
		html += "<div id=\"static\" class=\"modal hide\"  data-backdrop=\"static\" data-keyboard=\"false\">"
		html += "<div class=\"modal-body\">"
		html += "<p>" + content + "</p>"
		html += "</div>"
		html += "<div class=\"modal-footer\">"
		html += "<button type=\"button\" data-dismiss=\"modal\" class=\"btn\">取消</button>"
		html += "<button id=\"message-delete-confirm\" type=\"button\" data-dismiss=\"modal\" class=\"btn red\">确定</button>"
		html += "</div>"
		html += "</div>	"	
		$(html).modal().css("margin-top", "80px");
		$("#message-delete-confirm").on("click", callback);			
	}
</script>
