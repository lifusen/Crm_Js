<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<div id="browserVersionLowAlert" class="alert" style="position:absolute;top:49px;left: 400px;display: none;z-index: 99999">
	<button class="close" data-dismiss="alert"></button>
	<strong>提示!</strong> 您使用的IE浏览器版本过低,建议使用
	<a href="http://dlsw.baidu.com/sw-search-sp/soft/9d/14744/ChromeStandaloneSetup41.0.2272.89.1426235198.exe">Google浏览器</a>
	<a href="http://dlsw.baidu.com/sw-search-sp/soft/51/11843/Firefox_V37.0.1.5570_setup.1428373949.exe">FireFox浏览器</a>
</div>
	
<script type="text/javascript">
	$(function(){
		if ($.browser.msie){
			 var v = $.browser.version;
			 v = parseInt(v);
			 if(v<9){
				 $('#browserVersionLowAlert').show();
				 setTimeout(function(){
					 $('#browserVersionLowAlert').slideUp(200);
				 },10000);
			 }
		}
	});
</script>