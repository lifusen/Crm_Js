<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<style>
	#readTab.active{
		border-top: 3px solid green;
	}


	#notificationBtnGroup{
		margin-bottom: 10px;
	}

	#notificationBtnGroup .btn{
		padding: 7px 20px;
		width:170px;
	}

	#notificationBtnGroup .btn:hover{

	}


	.form-condition-wrapper .form-condition{

	}

	.form-condition-wrapper select.form-condition{
		width:120px;
	}


	.inbox .table th, .inbox .table td{
		/**border: 0px;**/
	}
	.inbox .table tbody tr.unread{
		font-size:13px;
		color:#FF0033;
	}
	.inbox .table tbody tr.unread:hover{
		color:red;
		cursor: pointer;
	}
</style>

<!-- BEGIN PAGE CONTENT-->
<div class="row-fluid" style="padding-top:5px;">
	<div class="span12">
		
		<!-- BEGIN PROGRESS BARS PORTLET-->
		<div class="portlet box green">
			<div class="portlet-title">
				<div class="caption"><i class="icon-cogs"></i>消息通知列表</div>
				<div class="tools">
					<a href="javascript:;" class="collapse"></a>
				</div>
			</div>
			<div class="portlet-body">


				<div class="row-fluid">
					<div class="span12">


						<div class="inbox">

							<div id="notificationBtnGroup">
								<button class="btn green"><i class="icon-envelope"></i> 所有消息</button>
								<button data-type="1" data-state="0" class="btn"><i class="icon-envelope"></i> 未读新增消息</button>
								<button data-type="2" data-state="0" class="btn"><i class="icon-envelope"></i> 未读跟进消息</button>
								<button data-type="1" data-state="1" class="btn"><i class="icon-envelope"></i> 已读新增消息</button>
								<button data-type="2" data-state="1" class="btn"><i class="icon-envelope"></i> 已读跟进消息</button>
							</div>

							<div class="form-condition-wrapper">
								<form action="#" class="form-horizontal" id="notificationSearchForm">
									<div class="control-group">
										<div class="controls">
											<input id="notificationSearchKeyword" type="text" class="span7 m-wrap" name="keyword" placeholder="请输入消息内容"/>
											<input id="notificationSearchType" type="hidden" name="type"/>
											<input id="notificationSearchState" type="hidden" name="state"/>
											<button type="submit" class="btn green"><i class="icon-search"></i> 搜索</button>
										</div>
									</div>
								</form>
							</div>

							<div id="notificationTableWrapper">
							
							</div>
							
						</div>

					</div>
				</div>




			</div>
		</div>
		<!-- END PROGRESS BARS PORTLET-->
		
	</div>
</div>


<!-- END PAGE CONTENT-->


<script>
	function loadNotificationTable(keyword,type,state){
		keyword = keyword || ""
		$.messager.showLoadProgress();
		$("#notificationTableWrapper").load("notification/getNotificationList.do",{keyword:keyword,type:type,state:state},function(){
			$.messager.closeLoadProgress();
		});
		
	}
	loadNotificationTable();
	// 表单搜索
	$("#notificationSearchForm").submit(function(e){
		e.preventDefault();
		var keyword = $("#notificationSearchKeyword").val();
		var type = $("#notificationSearchType").val();
		var state = $("#notificationSearchState").val();
		loadNotificationTable(keyword,type,state);
	});
	
	$("#notificationBtnGroup .btn").click(function(){
		var $this = $(this);
		var type = $this.attr("data-type");
		var state = $this.attr("data-state");
		//alert(type);
		$this.addClass("green");
		$this.siblings().removeClass("green");
		$("#notificationSearchType").val(type);
		$("#notificationSearchState").val(state);
		$("#notificationSearchForm").submit();
	});

</script>