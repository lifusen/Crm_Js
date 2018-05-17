/**
 * Created by MrLiu on 2016/3/4.
 */
//设置按钮的默认样式
bootbox.setBtnClasses({
	CONFIRM: 'blue',
	CANCEL: '',
	OK: 'green'
});
//设置按钮的默认图标
bootbox.setIcons({
	CANCEL: "icon-remove",
	CONFIRM: "icon-ok",
	OK: "icon-ok"
});

(function($){

    var messager = {
        alert : function(title,content,backdrop,callback){
        	var _backdrop = backdrop == undefined ? true : backdrop;
        	var _content = "<h5>"+content+"</h5>";
            bootbox.dialog(_content, [{
                "label": "确定",
                "class": "blue",
                "icon": "icon-ok",
                "callback": function () {
                    if(callback&& typeof(callback)=='function'){
                        callback();
                    }
                }
            }], {header: title,backdrop:_backdrop});
        },
        error : function(title,content,backdrop,callback){
        	var _backdrop = backdrop == undefined ? true : backdrop;
        	var _content = "<h5 style='color:red;'>"+content+"</h5>";
        	bootbox.dialog(_content, [{
        		"label": "确定",
        		"class": "blue",
        		"icon": "icon-ok",
        		"callback": function () {
        			if(callback&& typeof(callback)=='function'){
        				callback();
        			}
        		}
        	}], {header: title,backdrop:_backdrop});
        },
        confirm : function(title,content,callback,backdrop){
        	var _content = "<h4>"+content+"</h4>";
            bootbox.dialog(_content, [{
                "label": "取消",
                "class":" ",
                "icon": "icon-remove",
                "callback": function() {
                    if(callback&& typeof(callback)=='function'){
                        callback(false);
                    }
                }
            }, {
                "label": "确定",
                "class": "green",
                "icon": "icon-ok",
                "callback": function() {
                    if(callback&& typeof(callback)=='function'){
                        callback(true);
                    }
                }
            }], {header: title,backdrop:backdrop});
        },
        showLoadProgress : function(content){
        	$("#loadProgressDiv").show();
        },
        closeLoadProgress : function(){
        	$("#loadProgressDiv").hide();
        }
        
        
    }


    $.extend({
        messager : messager
    });
})(jQuery);