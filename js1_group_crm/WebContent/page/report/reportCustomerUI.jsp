<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<div class="row-fluid" id="reportCustomerUIContainer" style="display:none;padding-top:5px;">
	<div class="span12">
		<div class="portlet box green" style="margin-bottom:5px;">
			<div class="portlet-title">
				<div class="caption">
					<i class="icon-user"></i>客户列表
				</div>
				<div class="tools">
					<a class="collapse" href="javascript:;"></a>
				</div>
			</div>
			<div class="portlet-body">
				<p style="text-align: center;margin:0px;">搜索: <input type="text" id="keyStr" style="width:300px;" value="${requestScope.keyStr }" 
				placeholder="客户电话/姓名/等级/贷款类型" aria-controls="customerTable">
					<a class="btn green" id="searchButton" href="javascript:;" style="margin-top: -10px;padding-top:5px;padding-bottom: 5px;">
						<i class="icon-search"></i> 搜索
					</a>
				</p>
				<div id="tableList">
					
					<div class="progress progress-striped active" style="padding:10px 0px;">
						<div style="width: 100%;" class="bar"></div>
					</div>
					正在加载客户信息....
					
				</div>
			</div>
		</div>
	</div>
</div>
<script>
	$(function(){
		$("#keyStr").keyup(function(event){
			 if(event.keyCode == 13){
				var types = $("#types").val();
				var eOrd = $("#eOrd").val();
				var dateStr = $("#dateStr").val();
				if(types==null || typeof(types) == "undefined"){
					return ;
				}
				if(eOrd==null || typeof(eOrd) == "undefined"){
					return ;
				}
				if(dateStr==null || typeof(dateStr) == "undefined"){
					return ;
				}
				var val = strTrim($(this).val());
				$("#tableList").load("report/reportCustomerList.do?types="+types+"&dateStr="+dateStr+"&eOrd="+eOrd+"&whereKey="+val);
			 }
		 });
		$("#searchButton").click(function(){
			var types = $("#types").val();
			var eOrd = $("#eOrd").val();
			var dateStr = $("#dateStr").val();
			if(types==null || typeof(types) == "undefined"){
				return ;
			}
			if(eOrd==null || typeof(eOrd) == "undefined"){
				return ;
			}
			if(dateStr==null || typeof(dateStr) == "undefined"){
				return ;
			}
			var val = strTrim($("#keyStr").val());
			$("#tableList").load("report/reportCustomerList.do?types="+types+"&dateStr="+dateStr+"&eOrd="+eOrd+"&whereKey="+val);
		});
		$("#keyStr").focus();
	});
	function strTrim(val){
		var str = val.replace(/\s+/g,"");
		return str;
	}
</script>