//实例化编辑器
//建议使用工厂方法getEditor创建和引用编辑器实例，如果在某个闭包下引用该编辑器，直接调用UE.getEditor('editor')就能拿到相关的实例
var ue = UE.getEditor('editor',{
	initialFrameWidth : 'auto',		//初始化宽度
	elementPathEnabled : false,		//禁用元素路径
	initialFrameHeight : 230,		//初始化高度
	maximumWords : 20000,			//允许的最大字符数量		
	toolbars: [[
	            'fullscreen', 
	            'fontfamily', 'fontsize', '|',
	            'bold', 'italic', 'underline', 'fontborder','|',
	            'forecolor', 'backcolor','|',
	            'strikethrough', 'superscript', 'subscript', 'removeformat', 'formatmatch', 'autotypeset', 'blockquote', 'pasteplain', '|', 
	            , 'insertorderedlist', 'insertunorderedlist', 'selectall', 'cleardoc', '|',
	            'rowspacingtop', 'rowspacingbottom', 'lineheight', '|',
	            'customstyle', 'paragraph', '|',
	            'indent', '|',
	            'justifyleft', 'justifycenter', 'justifyright', 'justifyjustify', '|', 'touppercase', 'tolowercase', '|',
	            'link', 'unlink', 'anchor', '|', 'imagenone', 'imageleft', 'imageright', 'imagecenter', '|',
	            'simpleupload', 'insertimage', 'emotion', 'scrawl', 'insertvideo', 'music', 'map', 'insertframe', 'pagebreak', 'template', 'background', '|',
	            'horizontal', 'date', 'time', 'spechars', 'wordimage', '|',
	            'inserttable', 'deletetable', 'insertparagraphbeforetable', 'insertrow', 'deleterow', 'insertcol', 'deletecol', 'mergecells', 'mergeright', 'mergedown', 'splittocells', 'splittorows', 'splittocols', '|',
	            'print', 'preview', 'searchreplace', 'help', 'drafts'
	        ]]
});
