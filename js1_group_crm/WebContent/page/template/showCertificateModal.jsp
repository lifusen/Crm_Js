<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!-- 显示证件图片 start -->
<div id="showCertificateModal" class="modal hide fade" tabindex="-1" role="dialog" aria-labelledby="showCertificateModalLabeal" aria-hidden="true">
	<div class="modal-header">
		<button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>
		<h3 id="showCertificateModalLabeal"></h3>
	</div>
	<div class="modal-body">
		<div>
			<img alt="" src="${pageContext.request.contextPath}/images/zip.png" style="width:128px;height:128px;">
			<h3 id="showCertificateModalFileName"></h3>
			<input id="showCertificateModalPath" type="hidden" value=""/>
		</div>
	</div>
	<div class="modal-footer">
		<a onclick="downloadCertificateImage()" class="btn green" href="javascript:void(0);"><i class="icon-download"></i>下载</a>
		<button type="button" class="btn" onclick="closeShowCertificateModal()">关闭</button>
	</div>
</div>
<script type="text/javascript">
function closeShowCertificateModal(){
	$('#showCertificateModal').modal('hide');
}
function downloadCertificateImage(){
	var path = $('#showCertificateModalPath').val();
	location.href='certificate/download.do?path='+path
}
$('#showCertificateModal').on('hide.bs.modal', function () {
	//重置
	$('#showCertificateModalLabeal').text('');			//重置标题
	$('#showCertificateModalPath').val('');				//重置图片路径
})
</script>
<!-- 显示证件图片 end -->