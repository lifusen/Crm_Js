/*
 * 图片处理
 * 需要Layer、jQuery、jquery.form.js支持
 * 
 * 调用示例
 */
/*上传图片*/

//<span class="btn btn-primary btn-file  btn-sm" type="button">
//<i class="glyphicon glyphicon-plus"></i>
//上传图片<input type="file" name="file" onChange="operations.uploadImage(this)"/>

//	uploadImage:function(obj){
//		imageHandle.upload(obj,$("#ctx").val());
//		if(imageHandle.result != null && imageHandle.result.state == "success"){
//			$("#img-icon").attr("src",$("#staticUrl").val()+imageHandle.result.object);
//			$("#icon").val(imageHandle.result.object);
//		}
//	},


var imageHandle = {
	/*obj file元素,id 自定义id,basePath 基础路径*/
	upload:function(obj,basePath){
		imageHandle.basePath = basePath;
		imageHandle.uploadForm(obj);
		imageHandle.uploadImage(obj);
		imageHandle.uploadUnForm(obj);
	},
	/*图片格式正则*/
	reg:/.jpg$|.JPG$|.GIF$|.gif$|.png$|.PNG$|.bmp$|.BMP$/,
	/*上传地址*/
	uploadUrl:function(){
		return imageHandle.basePath+"/file/image/upload"
	},
	/*basePath*/
	basePath:'',
	/*给上传按钮包围FORM元素*/
	uploadForm:function(obj){
		$(obj).wrap("<form id='ajaxupload' target='hidden_iframe' action='' method='post' enctype='multipart/form-data' ></form>");
	},
	/*ggid*/
	guid:function(){
		return 'xxxxxxxx-xxxx-4xxx-yxxx-xxxxxxxxxxxx'.replace(/[xy]/g, function(c) {
	        var r = Math.random()*16|0, v = c == 'x' ? r : (r&0x3|0x8);
	        return v.toString(16);
	    });
	},
	/*给上传按钮去除包围FORM元素*/
	uploadUnForm:function(obj){
		$(obj).unwrap();
  		obj.outerHTML = obj.outerHTML;
	},
	/*上传图片*/
	uploadImage:function(obj){
		if (!imageHandle.reg.test($(obj).val())) {
	        layer.alert("请上传jpg|gif|png|bmp 格式的图片文件！", {
	            icon: 2,
	            closeBtn: 0, // 显示关闭按钮
	            title: '错误提示',
	            btn: ['确定'],
	            yes: function (index) {
	                layer.close(index);
	            }
	        }, function () {
	        });
	        return;
	    }
		/*static*/
    	var fileName=$(obj).val();
    	var arr = fileName.split('.');
    	//拼装图片uuid名称
		var fileName=imageHandle.guid()+"."+arr[arr.length - 1];
	    $("#ajaxupload").ajaxSubmit({    
	        url:imageHandle.uploadUrl(),    
	        type:"post",    
	        data:{fileName:fileName}, 
	        async: false,
	        dataType:"json",
	        success:function(result){    
	        	imageHandle.result = result;
	        }    
	    }); 
	},
	result:null
};
//----------------------------------图片处理  end ----------------------------------