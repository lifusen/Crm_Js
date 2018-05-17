<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<div class="portlet-body  t-g">
<div class="tabbable portlet-tabs">
	<ul class="nav nav-tabs">
		<li  style="width:30%; text-algin: center;" ><a href="#portlet_tab3"data-toggle="tab">说辞</a></li>
		<li style="width:30%; text-algin: center;"  ><a href="#portlet_tab2" data-toggle="tab">制度</a></li>
		<li class="active" style="width:30%;  text-algin: center;" ><a href="#portlet_tab1" data-toggle="tab">公告</a></li>
	</ul>
	<div class="tab-content" id="messageTabContent">
		<div class="tab-pane active small-font-container" id="portlet_tab1" style="line-height: 130%;">
				<c:forEach var="message" items="${noticeMessages}">									
					<p class="message" data-mid="${message.id }">
						<span class="f-s fr"><fmt:formatDate value="${message.pubdate}" pattern="yyyy-MM-dd HH:mm"/></span>
						<a href="javascript:void(0);">${message.title}</a>
					</p>
				</c:forEach>		
			</div>
			<div class="tab-pane" id="portlet_tab2">
				<c:forEach var="message" items="${institutionMessages}">									
					<p class="message" data-mid="${message.id }">
						<span class="f-s fr"><fmt:formatDate value="${message.pubdate}" pattern="yyyy-MM-dd HH:mm"/></span>
						<a href="javascript:void(0);">${message.title}</a>
					</p>
				</c:forEach>		
			</div>
			<div class="tab-pane" id="portlet_tab3">
				<c:forEach var="message" items="${excuseMessages}">									
					<p class="message" data-mid="${message.id }">
						<span class="f-s fr"><fmt:formatDate value="${message.pubdate}" pattern="yyyy-MM-dd HH:mm"/></span>
						<a href="javascript:void(0);">${message.title}</a>
					</p>
				</c:forEach>													
			</div>
		</div>
	</div>
</div>

<script type="text/javascript">
	$('#messageTabContent p.message').dblclick(function(){
		var mid = $(this).attr('data-mid');
		//$('#messageModalLabel').text(mtitle);
		//$('#messageModalBody').html(mcontent);
		$('#messageModal').load('message/gotoDetail.do?id='+mid+'&type=1',function(){
			$('#messageModal').modal('show');
		});
	});
</script>

<!-- 消息窗体 start -->
<div id="messageModal" class="modal hide fade" tabindex="-1" role="dialog" aria-labelledby="messageModalLabel" aria-hidden="true"
	style=""
>
	
	
</div>   
<!-- 消息窗体 end -->   