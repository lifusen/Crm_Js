<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!-- BEGIN PAGE CONTAINER-->
<div class="container-fluid p-t-20" style="padding-top:8px;">
	<div class="row-fluid">
		<div class="span12">
			<div class="portlet box blue">
				<div class="portlet-title">
					<div class="caption"><i class="icon-cogs"></i>个人信息</div>
					<div class="tools">
						<a href="javascript:;" class="collapse"></a>
					</div>
				</div>
				<div class="portlet-body">	
					<div>
						<div style="width:120px; height: 120px; margin:10px 0px 0px 10px; float: left;">
							<img class="page-lock-img" src="${sessionScope.loginUser.headImg }" alt="">	
						</div>
						<div style="width:700px; height:400px; margin-top:30px; float:left;">
							<table class="formTable" style="margin-left: 100px;">
								<tr>
									<td><label class="control-label">　　　　　姓　名:</label></td>
									<td><label class="control-label">　${sessionScope.loginUser.name }</label></td>
									<td><label class="control-label">　　　　　　　　　　登录账号:</label></td>
									<td><label class="control-label">　${sessionScope.loginUser.account }</label></td>
								</tr>
								<tr>
									<td><label class="control-label">　　　　　工　号:</label></td>
									<td><label class="control-label">　${sessionScope.loginUser.jobNumber }</label></td>
									<td><label class="control-label">　　　　　　　　　　　角　色:</label></td>
									<td><label class="control-label">　${sessionScope.loginUser.role.roleName }</label></td>
								</tr>
								<tr>
									<td><label class="control-label">　　　　　手　机:</label></td>
									<td><label class="control-label">　${sessionScope.loginUser.mobile }</label></td>
									<td><label class="control-label">　　　　　　　　　　　性　别:</label></td>
									<td><label class="control-label">　${sessionScope.loginUser.gender }</label></td>
								</tr>
								<tr>
									<td><label class="control-label">　　　　　部　门:</label></td>
									<td><label class="control-label">　${sessionScope.loginUser.department.name }</label></td>
									<td><label class="control-label">　　　　　　　　　　　座　机:</label></td>
									<td><label class="control-label">　${sessionScope.loginUser.tel }</label></td>
								</tr>
								<tr>
									<td><label class="control-label">　　　　　短　号:</label></td>
									<td><label class="control-label">　${sessionScope.loginUser.cornet }</label></td>
									<td><label class="control-label">　　　　　　　　　　　身份证:</label></td>
									<td><label class="control-label">　${sessionScope.loginUser.idCard }</label></td>
								</tr>
								<tr>
									<td><label class="control-label">　　　　　住　址:</label></td>
									<td><label class="control-label">　${sessionScope.loginUser.address }</label></td>
									<td><label class="control-label">　　　　　　　　　　入职时间:</label></td>
									<td><label class="control-label">　<fmt:formatDate value="${sessionScope.loginUser.entryDate }" pattern="yyyy-MM-dd"/></label></td>
								</tr>
								<tr>
									<td><label class="control-label">　　　　　户　籍:</label></td>
									<td><label class="control-label">　${sessionScope.loginUser.census }</label></td>
									<td><label class="control-label">　　　　　　　　　紧急联系人:</label></td>
									<td><label class="control-label">　${sessionScope.loginUser.remarkPhoneName }</label></td>
								</tr>
								<tr>
									<td><label class="control-label">　紧急联系人电话:</label></td>
									<td><label class="control-label">　${sessionScope.loginUser.remarkPhone }</label></td>
									<td><label class="control-label">　　　　　　　　　　　关　系:</label></td>
									<td><label class="control-label">　${sessionScope.loginUser.relation }</label></td>
								</tr>
								<tr>
									<td><label class="control-label">入职资料是否完善:</label></td>
									<td><label class="control-label">　${sessionScope.loginUser.entryDatum }</label></td>
									<td><label class="control-label">　　　　　　　　　　　谈判师:</label></td>
									<td><label class="control-label">　${sessionScope.loginUser.talks }</label></td>
								</tr>
								<tr>
									<td><label class="control-label">　　　　　备　注:</label></td>
									<td><label class="control-label">　${sessionScope.loginUser.remark }</label></td>
								</tr>
							</table>
						</div>
					</div>
					<div style="clear: left;"></div>
				</div>
			</div>
		</div>
	</div>
</div>
<!-- END PAGE CONTAINER-->