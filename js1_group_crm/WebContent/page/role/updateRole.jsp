<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!-- BEGIN PAGE CONTAINER-->
<div class="container-fluid p-t-20" style="padding-top:8px;">
	<!--开始：第1行 -->
	<div class="row-fluid">
		<!--开始：第1行第1列 -->
		<div class="span12">
			<!--开始：第1行第1列中的盒子-->
			<div class="portlet box blue">
				<div class="portlet-title">
					<div class="caption"><i class="icon-cogs"></i>修改角色</div>
					<div class="tools">
						<a href="javascript:;" class="collapse"></a>
					</div>
				</div>
				<!--开始：第1行第1列中的盒子body-->
				<div class="portlet-body">
					<div id="showUpdateError" class="alert alert-error hide">
						<button data-dismiss="alert" class="close"></button>
						<strong>　　修改失败!</strong>　　请稍后重试...
					</div>
					<form id="updateRoleForm" action="role/updateRole.do" method="post">
					<input type="hidden" value="${requestScope.role.id }" id="roleId" name="id">
					<input type="hidden" value="${requestScope.role.id }" id="readOny" name="readony">
						<table class="formTable" style="margin-left: 300px;">
							<tr>
								<td><label class="control-label">　名　称<span class="required">*</span></label></td>
								<td>
									<input type="text" readonly="readonly" id="roleName" name="roleName" value="${requestScope.role.roleName }" class="m-wrap medium" style="width:100px;">
								</td>
							</tr>
							<tr>
								<td><label class="control-label">　备　注</label></td>
								<td>
									<textarea name="remark" readonly="readonly" id="remark" rows="2" class="large m-wrap">${requestScope.role.remark }</textarea>
								</td>
							</tr>
						</table>
						<div>
						<label class="control-label">选择操作权限</label>
						<c:forEach items="${requestScope.menuTrees }" var="menuTree">
							<div style="width:250px;padding-left: 30px;float: left;">
								<label class="control-label" style="font-weight: bold;"><input type="checkbox" name="menus"
										<c:forEach items="${requestScope.checkedMenus }" var="mId">
											<c:if test="${mId == menuTree.menu.id}">
												 checked="checked"
											</c:if>
										</c:forEach>
									 class="menuParent-${menuTree.menu.id }" onchange="allChecked(this);" value="${menuTree.menu.id }">${menuTree.menu.name }</label>
<%-- 								<div style="display: none;"><input type="checkbox" name="menus" checked="checked" value="${menuTree.menu.id }"></div> --%>
								<div class="controls">
									<div class="row-fluid">
										<div class="span3">
											<c:forEach items="${menuTree.menus }" var="menu">
												<label class="checkbox line" style="width:170px;">
													<span><input type="checkbox"  class="menus-${menuTree.menu.id }" onchange="checkedParent(this);" 
														<c:forEach items="${requestScope.checkedMenus }" var="mId">
															<c:if test="${mId == menu.id}">
																checked="checked"
															</c:if>
														</c:forEach>
													 name="menus" value="${menu.id }"></span> ${menu.name }
												</label>
											</c:forEach>
										</div>
									</div>
								</div>
							</div>
						</c:forEach>
						</div>
						<div style="clear: left;"></div>
						<div class="form-actions" style="padding:8px;text-align:center;margin-top:5px;margin-bottom:10px;" >
							<button class="btn blue" type="button" id="saveSubmit"><i class="icon-ok"></i> 保存</button>
							<a id="cancelSave" class="btn aPost" href="javascript:;"><i class="icon-remove"></i> 取消</a>
						</div>
					</form>
				</div>
				<!--结束：第1行第1列中的盒子的body-->
			</div>
			<!--结束：第1行第1列中的盒子-->
		</div>
		<!--结束：第1行第1列 -->					
	</div>
	<!--结束：第1行 -->
</div>
<!-- END PAGE CONTAINER--> 

<script src="${pageContext.request.contextPath}/media/js/jquery.validate.min.js" type="text/javascript"></script>
<script>
	$(function(){
		 $("#saveSubmit").click(function(){
		    var id = $("#roleId").val();
		    var roleName = $("#roleName").val();
		    var readOnly = $("#readOnly").val();
			var remark = $("#remark").val();
			var menus =  $("input[name='menus']");
			var menusVal = "";
			for(var i=0;i<menus.length;i++){
				if($(menus[i]).attr("checked")){
					menusVal = menusVal+$(menus[i]).val()+"-";
				}
			}
			$.post($("#updateRoleForm").attr("action"),{id:id,readOnly:readOnly,roleName:roleName,remark:remark,menus:menusVal},function(data){
				if(data){
					aPost("role/roleList.do");
				}else{
					$("#showUpdateError").removeClass("hide");
				}
			});
		 });
		$("#cancelSave").click(function(){
			aPost("role/roleList.do");
		});
	});
	
	// 全选/反选
	function allChecked(checkbox){
		var id = $(checkbox).attr("value");
		var menus = $(".menus-"+id);
		if($(checkbox).attr("checked")){
			$(menus).parent().addClass("checked");
			$(menus).attr("checked", true);
		}else{
			$(menus).parent().removeClass("checked");
			$(menus).attr("checked", false);
		}
	}
	
	function checkedParent(checkbox){
		var cla = $(checkbox).attr("class");
		var menus = $("."+cla);
		var isAll = false;
		for(var i=0;i<menus.length;i++){
			if($(menus[i]).attr("checked")){
				isAll = true;
				break;
			}
		}
		var pId = cla.substring(cla.lastIndexOf("-")+1);
		if(!isAll){
			$(".menuParent-"+pId).attr("checked",false);
			$(".menuParent-"+pId).parent().removeClass("checked");
		}else{
			$(".menuParent-"+pId).attr("checked",true);
			$(".menuParent-"+pId).parent().addClass("checked");
		}
	}
</script>