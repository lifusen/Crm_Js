<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!-- 导入影像文件 start -->
<div id="uploadCertificateModal" class="modal hide fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel1" aria-hidden="true">
	<div class="modal-header">
		<button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>
		<h3 id="myModalLabel1">导入影像文件</h3>
	</div>
	<div class="modal-body">
		
		<form id="uploadCertificateForm" action="certificate/upload.do" enctype="multipart/form-data" method="post">
			<p>
				证件名称:<input type="text" name="certificateName"/>
			</p>
			<p>
				<input type="file" name="file" style="border:0px;line-height: 0px;"/>
			</p>
			<p>
				<button id="uploadCertificateBtn" type="submit" class="btn blue" style="width:300px;">上传</button>
			</p>
		</form>
		
	</div>
	<div class="modal-footer">
		<button type="button" class="btn" onclick="closeUploadFileModal()">关闭</button>
	</div>
</div>
<script type="text/javascript">
var srcButton;
var customerId;
function importCertificate(obj,cId){
	srcButton=obj;
	customerId = cId;
	$('#uploadCertificateModal').modal('show');
}

$("#uploadCertificateForm").validate({
    rules: {
		certificateName: {
	    	required: true
	    },
		file: {
	    	required: true,
	    	zipType: true
	    }
	},
	messages: {
		certificateName: {
	     	required: '必填'
	    },
		file: {
	    	required: '请选择文件!'
	    }
	},submitHandler: function(form) {
		//导入影像文件按钮所在的td
		var td = $(srcButton).parents('td');
		
		$(form).ajaxSubmit({  
		    dataType: 'json',  
		    data:{customerId:customerId},
		    success: function(data){
		    	if(data.flag==false){
		    		alert(data.msg);
		    	}else{
		    		alert('导入成功!');
		    		var c = data.msg;
		    		console.log(c);
		    		$('<a/>',{
		    			href : 'javascript:void(0);',
		    			text : c.name,
		    			style : 'padding:5px;',
		    			click : function(){
		    				showCertificateModal(c.name,c.srcfileName,c.path);
		    			}
		    		}).appendTo(td);
		    		$('<input/>',{
		    			type : 'hidden',
		    			name : 'certificates',
		    			value : c.id+'-'+c.name+'-'+c.srcfileName+'-'+c.path
		    		}).appendTo(td);
		    		
		    		closeUploadFileModal();		//关闭
		    	}
		    }  
		});
	}
 });

/**
 * 显示证件的窗体
 */
function showCertificateModal(name,srcfileName,path){
	$('#showCertificateModalLabeal').text("证件名称:"+name);
	$('#showCertificateModalFileName').text(srcfileName);
	$('#showCertificateModalPath').val(path);
	$('#showCertificateModal').modal('show');
}


/**
 * 关闭上传证件的Modal并重置表单
 */
function closeUploadFileModal(){
	//管理Modal
	$('#uploadCertificateModal').modal('hide');
}

$('#uploadCertificateModal').on('hide.bs.modal', function () {
	//重置表单
	$('#uploadCertificateForm')[0].reset();
})


</script>
<!-- 导入影像文件 end -->