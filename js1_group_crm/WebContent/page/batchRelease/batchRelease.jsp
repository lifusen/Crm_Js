<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!-- 客户列表JS -->
<style type="text/css">
	.superSearch{
		width:100%;height:320px;
		border:1px solid #ccc;
		margin: 10px 0;
	}
</style>
<div class="container-fluid">
	<div class="row-fluid">
		<div class="span12">
			<h3 class="page-title"></h3>
			<ul class="breadcrumb">
				<li><i class="icon-home"></i> <a href="javascript:void(0);">业务部</a>
					<i class="icon-angle-right"></i></li>
				<li><a href="javascript:void(0);">批量释放客户到公共池</a></li>
			</ul>
		</div>
	</div>
	<div class="row-fluid">
		<div class="span12">
			<!-- BEGIN EXAMPLE TABLE PORTLET-->
			<div class="portlet box green">
				<div class="portlet-title">
					<div class="caption"><i class="icon-globe"></i>批量释放客户到公共池</div>
					<div class="tools">
						<a href="javascript:;" class="collapse"></a>
					</div>
				</div>
				<div class="portlet-body" id="tableList">
					
					<div class="row-fluid">
						<div class="span14">
						　业务部门：
						<select id="demSelect" class="span2">
							<option value="0">所有业务部</option>
							<c:forEach items="${requestScope.departments }" var="department">
								<option value="${department.id }"
									<c:if test="${dId == department.id}">
										selected="selected"
									</c:if>
								>${department.name }</option>
							</c:forEach>
						</select>
						<select class="span2" id="emplSelect">
							<option value="0">所有客户经理</option>
						</select>
						<select id="followLevel" class="span2">
							<option value="0">所有关注等级</option>
							<option value="A">A</option>
							<option value="B">B</option>
							<option value="C">C</option>
							<option value="D">D</option>
						</select>
							<select id="customerLevel" class="span2">
								<option value="0">所有客户等级</option>
								<option value="优">优</option>
								<option value="良">良</option>
								<option value="中">中</option>
								<option value="差">差</option>
							</select>
							<a class="btn green" id="releasesButton" href="javascript:void(0);" style="margin-top: -10px;"><i class="icon-ok"></i> 批量释放</a>
						</div>
					</div>
					
					
					
					
				</div>
			</div>
		</div>
	</div>
</div>
<script>
/**
$.messager.alert("标题","消息");
$.messager.confirm("标题","您确认要删除吗?",function(result){
	alert(result);
});
**/
//批量释放
$("#releasesButton").click(function(){
	var dId = $("#demSelect").val();
	var eId = $("#emplSelect").val();
	var fLevel = $("#followLevel").val();
	var cLevel = $("#customerLevel").val();
	
	if(fLevel!="C" && fLevel!="D" 
			&& cLevel!="中" && cLevel!="差"){
		alert("请选择关注等级 C/D,或者客户等级 中/差");
		return;
	}
	$.post("releaseCommonPool/getbatchReleaseCount.do",{
		departmentId:dId,employeeId:eId,customerLevel:cLevel,attentionLevel:fLevel},function(data){
			if(data>0){
				//共搜索到["+data+"]条匹配的数据，请审核所选择的释放信息，确定无误后释放！释放后不能恢复，请谨慎操作！！！
				if(confirm("共搜索到["+data+"]条匹配的数据，请审核所选择的释放信息，确定无误后释放！释放后不能恢复，请谨慎操作！！！")){
					$.post("releaseCommonPool/batchRelease.do",
							{departmentId:dId,employeeId:eId,customerLevel:cLevel,attentionLevel:fLevel},function(data){
						if(data){
							alert("释放成功！");
						}
					});
				}
			}else{
				alert("没有可释放的客户信息");
			}
	});
});

//查询
$("#demSelect").change(function(){
	var val = $(this).val();
	$.post("employee/getEmployeesByDId.do",{dId:val},function(data){
		var s = $("#emplSelect");
		s.empty();
		$("<option />",{value:0,text:"所有客户经理"}).appendTo(s);
		$.each(data,function(i,item){
			$("<option />",{value:item.id,text:item.name}).appendTo(s);
		});
	});
});
</script>