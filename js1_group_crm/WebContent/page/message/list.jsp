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
					<div class="caption"><i class="icon-cogs"></i>${typeName}列表</div>
				</div>
				<div class="portlet-body">		
					<div class="clearfix">
						<div class="btn-group">
							<a class="btn green aPost" href="message/gotoAdd.do?type=${type}"><i class="icon-plus"></i> 发布${typeName}</a>
						</div>
					</div>
					
					<div id="messageListArea">
						<jsp:include page="table.jsp"/>
					</div>
																
				</div>										
			</div>
		</div>
	</div>
</div>
<!-- END PAGE CONTAINER--> 
