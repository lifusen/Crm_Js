<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<style>
	#loadProgressDiv{
		position:absolute;
		top:45%;
		left:45%;
		border:1px solid #ccc;
		padding:12px 50px 12px 35px;
		display:none;
		background-color:#fff;
		opacity:0.8;
		z-index:99999;
	}
	#loadProgressDiv p{
		position:relative;
		padding:0px;
		margin:0px;
		font-size:13px;
	}
	#loadProgressDiv p img{
		margin-right:5px;
	}
</style>
<div id="loadProgressDiv">
	<p>
		<img src='${pageContext.request.contextPath}/images/loading.gif'/> 
		正在加载数据,请稍等...
	</p>
</div>