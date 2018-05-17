<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<div class="row-fluid">
	<div class="span12">
		<h3 class="page-title"></h3>
		<ul class="breadcrumb">
			<li><i class="icon-undo"></i> <a id="backBankproducatList" href="javascript:void(0);">返回银行产品列表</a></li>
		</ul>
	</div>
</div>
<div class="row-fluid">
	<div class="span12">
		<div class="portlet box green">
			<div class="portlet-title">
				<div class="caption"><i class="icon-cogs"></i>${typeName}</div>
			</div>
			<div class="portlet-body">		
				<div class="alert alert-info">
					<h5 >${message.title }</h5>
					<span>发布人:</span> ${message.publisher.name}&nbsp;
					<span>时间:</span> <fmt:formatDate value="${message.pubdate}" pattern="yyyy-MM-dd HH:mm"/>
				</div>
			
				<div style="padding:10px 5px; border:1px solid #D9EDF7">
					${message.content}
				</div>
			</div>
		</div>
	</div>
</div>
<script type="text/javascript">
	$(function(){
		$('#backBankproducatList').click(function(){
			$('#bankproducatContainer').load('/crm/message/geBankProductList.do');
		});
	})
</script>
