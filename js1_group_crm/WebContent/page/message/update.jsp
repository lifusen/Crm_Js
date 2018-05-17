<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<!-- 引入富文本编辑器 -->
<script type="text/javascript" charset="utf-8" src="/ueditor/ueditor.config.js"></script>
<script type="text/javascript" charset="utf-8" src="/ueditor/ueditor.all.min.js"> </script>
<script type="text/javascript" charset="utf-8" src="/ueditor/lang/zh-cn/zh-cn.js"></script>

<style type="text/css">
	 
</style>
<div class="container-fluid" style="padding-top:8px;padding-bottom:200px;position: relative;z-index: 1000;">
	<div class="row-fluid">
		<div class="span12">
			<div class="portlet box green">
				<div class="portlet-title">
					<div class="caption"><i class="icon-cogs"></i>修改${typeName}</div>
				</div>
				<div class="portlet-body">		
					<form id="updateMessageForm" action="message/update.do" method="post">
						<div style="padding:10px 2px;">
						<table style="width:100%; max-width: 1290px;">
							<tr>
								<td width="35">
									<label class="control-label">标题</label>
								</td>
								<td>
									<input type="hidden" id="messageType" value="${type}">
									<input id="messageTitle" type="text" name="title" style="width:98.6%;" value="${message.title}"/>
								</td>
							</tr>
							<tr>
								<td>
									<label class="control-label">内容</label>
								</td>
								<td>
    								<script id="editor" type="text/plain" style="height:500px;">${message.content}</script>
								</td>
							</tr>
						</table>
						</div>
						
						<div class="form-actions" style="padding:8px;text-align:center;margin-top:5px;margin-bottom:10px;" >
							<button class="btn blue" type="submit"><i class="icon-ok"></i> 保存</button>
							<a id="canceMessageSave" class="btn" href="javascript:;"><i class="icon-remove"></i> 取消</a>
						</div>
					</form>
				</div>
			</div>
		</div>
	</div>
</div>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/common/crmUeditor.js"></script>
<script type="text/javascript">
$(function(){
	 $("#updateMessageForm").validate({
		 rules: {
           title: {
		    required: true,
		    maxlength:100
		   }
  		},
		messages: {
			title: {
		     	required: '必填',
		     	maxlength:'标题最多输入100个字符'
	    	}
		},submitHandler: function() {
			var messageType = $('#messageType').val();
			var messageTitle = $('#messageTitle').val();
			var messageContent = ue.getContent();
			messageContent = $.trim(messageContent);
			if(messageContent==''){
				$('#errorTipModalBody').html("请输入消息内容!");
				$('#errorTipModal').modal('show');
				return;
			}
			
			$.post($("#updateMessageForm").attr("action"),{
				id:'${message.id}',
				type:messageType,
				title:messageTitle,
				content:messageContent
			},function(data){
				if(data){
					//alert('修改成功!');
					aPost("message/getList.do?type="+messageType);
				}else{
					alert('修改失败!');
				}
			});
		}
	 });
	
	
	$("#canceMessageSave").click(function(){
		aPost("message/getList.do?type=${type}");
	});
});
</script>