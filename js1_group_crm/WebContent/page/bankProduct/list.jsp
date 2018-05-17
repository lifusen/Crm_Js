<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!-- BEGIN PAGE CONTAINER-->
<div class="container-fluid p-t-20" style="padding-top:8px;">
	<div class="row-fluid">
		<div class="span12">
			<div class="portlet box blue">
				<div class="portlet-title">
					<div class="caption"><i class="icon-cogs"></i>银行产品信息管理</div>
				</div>
				<div class="portlet-body">		
					<div class="clearfix">
						<div class="btn-group">
							<a class="btn green aPost" href="page/bankProduct/add.jsp"><i class="icon-plus"></i> 添加银行产品</a>
						</div>
					</div>
					<table class="table table-striped table-hover table-bordered table-condensed" id="bankProductTable">
						<thead>
							<tr>
								<th width="10%">序号</th>
								<th width="30%">银行产品名称</th>
								<th width="40%">银行产品描述</th>
								<th width="20%">操作</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach var="bankProduct" items="${requestScope.bankProducts }" varStatus="vs">
								<tr>
									<td>${vs.count }</td>
									<td>${bankProduct.name }</td>
									<td>${bankProduct.remark }</td>
									<td>
										<button type="button" class="btn blue mini" onclick="updateBankProduct(${bankProduct.id})">修改</button>
										<button type="button" class="btn yellow mini" onclick="deleteBankProduct(${bankProduct.id})">删除</button>
									</td>
								</tr>
							</c:forEach>
						</tbody>
					</table>												
				</div>										
			</div>
		</div>
	</div>
</div>
<!-- END PAGE CONTAINER--> 

<script type="text/javascript">

/**
 * 修改
 */
function updateBankProduct(id){
	aPost("bankProduct/gotoUpdate.do?id="+id);
}
/**
 * 删除
 */
function deleteBankProduct(id){
	if(confirm('您确定要删除吗?')){
		$.post("bankProduct/delete.do",{id:id},function(data){
			if(data){
				alert('删除成功!');
				aPost("bankProduct/getList.do");
			}else{
				alert('删除失败,该银行产品下有客户引用!');
			}
		});
	}
}
</script>