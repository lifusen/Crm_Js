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
	padding: 7px 40px;
}

#notificationBtnGroup .btn:hover{

}


.form-condition-wrapper .form-condition{

}

.form-condition-wrapper select.form-condition{
	width:120px;
}


.inbox .table th, .inbox .table td{
	border: 0px;
}
.inbox .table tbody tr{
	font-size:13px;
	color:red;
	color: #d84a38;
	color:#FF0033;
}
.inbox .table tbody tr:hover{
	color:black;
	cursor: pointer;
}
</style>

<!-- BEGIN PAGE CONTENT-->
<div class="row-fluid" style="padding-top:2px;">
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
						<!--BEGIN TABS-->
						<div class="tabbable tabbable-custom">
							<ul id="notificationTabWrapper" class="nav nav-tabs">
								<li id="unreadTab" class="active"><a href="#notificationTab" data-toggle="tab">未读消息</a></li>
								<li id="readTab" class=""><a href="#notificationTab" data-toggle="tab">已读消息</a></li>
							</ul>
							<div class="tab-content">
								<div class="tab-pane active" id="notificationTab">



									<div class="inbox">

										<div id="notificationBtnGroup">
											<button data-type="all" class="btn green"><i class="icon-envelope"></i> 所有(16)</button>
											<button data-type="1" class="btn"><i class="icon-envelope"></i> 分配(10)</button>
											<button data-type="2" class="btn"><i class="icon-envelope"></i> 跟进(6)</button>
										</div>



										<div class="form-condition-wrapper">
											<select class="form-condition">
												<option>发送人公司</option>
												<option>CRM一中心</option>
												<option>CRM二中心</option>
												<option>CRM三中心</option>
											</select>
											<select class="form-condition">
												<option>发送人部门</option>
												<option>CRM一部门</option>
												<option>CRM二部门</option>
												<option>CRM三部门</option>
											</select>
											<select class="form-condition">
												<option>发送人</option>
												<option>张三</option>
												<option>李四</option>
												<option>王五</option>
											</select>
											<select class="form-condition">
												<option>接收人公司</option>
												<option>CRM一中心</option>
												<option>CRM二中心</option>
												<option>CRM三中心</option>
											</select>
											<select class="form-condition">
												<option>接收人部门</option>
												<option>CRM一部门</option>
												<option>CRM二部门</option>
												<option>CRM三部门</option>
											</select>
											<select class="form-condition">
												<option>接收人</option>
												<option>张三</option>
												<option>李四</option>
												<option>王五</option>
											</select>
											<select class="form-condition">
												<option>开始时间</option>
											</select>
											<select class="form-condition">
												<option>结束时间</option>
											</select>
										</div>

										<div class="form-condition-wrapper">
											<form action="#" class="form-horizontal" id="submit_form">
												<div class="control-group">
													<div class="controls">
														<input id="keyword" type="text" class="span7 m-wrap" name="keyword" placeholder="请输入消息内容"/>
														<button type="button" class="btn green"><i class="icon-search"></i> 搜索</button>
													</div>
												</div>
											</form>
										</div>

										<table id="notificationTable" class="table table-hover table-striped">
											<thead>
											<tr>
												<th style="width:30px;">序号</th>
												<th style="width:60px;">消息类型</th>
												<th>消息内容</th>
												<th style="width:115px;">发送时间</th>
												<th style="width:50px;">发送人</th>
												<th style="width:50px;">接收人</th>
												<th style="width:60px;">操作</th>
											</tr>
											</thead>
											<tbody>
											<tr data-id="1">
												<td>1</td>
												<td>新增</td>
												<td>CRM一中心业务一部张三的签单客户李四分配给你,请及时处理!</td>
												<td>2016-6-6 14:06:36</td>
												<td>张三</td>
												<td>孙悟空</td>
												<td><button type="button" class="btn green mini"><i class="icon-search"></i> 查看</button></td>
											</tr>
											<tr data-id="2">
												<td>2</td>
												<td>跟进</td>
												<td>CRM一中心业务一部客户经理张三给签单客户李四添加了客户跟进,跟进类型为:预约上门,请查看!</td>
												<td>2016-6-6 14:06:36</td>
												<td>张三</td>
												<td>孙悟空</td>
												<td><button type="button" class="btn green mini"><i class="icon-search"></i> 查看</button></td>
											</tr>
											<tr data-id="3">
												<td>3</td>
												<td>跟进</td>
												<td>CRM一中心业务一部客户经理张三给签单客户李四添加了客户跟进,跟进类型为:预约上门,请查看!</td>
												<td>2016-6-6 14:06:36</td>
												<td>张三</td>
												<td>孙悟空</td>
												<td><button type="button" class="btn green mini"><i class="icon-search"></i> 查看</button></td>
											</tr>
											<tr data-id="4">
												<td>4</td>
												<td>跟进</td>
												<td>CRM一中心业务一部客户经理张三给签单客户李四添加了客户跟进,跟进类型为:预约上门,请查看!</td>
												<td>2016-6-6 14:06:36</td>
												<td>张三</td>
												<td>孙悟空</td>
												<td><button type="button" class="btn green mini"><i class="icon-search"></i> 查看</button></td>
											</tr>
											<tr data-id="5">
												<td>5</td>
												<td>跟进</td>
												<td>CRM一中心业务一部客户经理张三给签单客户李四添加了客户跟进,跟进类型为:预约上门,请查看!</td>
												<td>2016-6-6 14:06:36</td>
												<td>张三</td>
												<td>孙悟空</td>
												<td><button type="button" class="btn green mini"><i class="icon-search"></i> 查看</button></td>
											</tr>
											</tbody>

										</table>
									</div>






								</div>
							</div>
						</div>
						<!--END TABS-->
					</div>
				</div>









































			</div>
		</div>
		<!-- END PROGRESS BARS PORTLET-->
	</div>
</div>


<!-- END PAGE CONTENT-->


<script>
	$("#notificationTable tbody tr").dblclick(function(){
		var id = $(this).attr("data-id");
		alert(id);
	});
	$("#notificationBtnGroup .btn").click(function(){
		var $this = $(this);
		var type = $this.attr("data-type");
		//alert(type);
		$this.addClass("green");
		$this.siblings().removeClass("green");
		getNotificationList(type);
	});

	function getNotificationList(type){
		alert("正在加载数据列表:"+type);
	}


	$("#notificationTabWrapper>li").click(function(){

		alert($(this).text());
		$("#notificationTab").html("<h1>正在加载数据...</h1>")
		
	});

</script>