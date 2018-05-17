<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    
<style>
	#notificationWrapper{
    	/**background:rgba(0,0,0,0.5) none repeat scroll !important;**/
    	height:42px;/**42px;**/
    	z-index:99999;
    	position: fixed; left: 0; top: 0; right: 0; bottom: 0;
    	margin:0px auto;
    	padding:0px;
    	margin-right: 200px;/** 110px**/
    	margin-left: 145px;
    	border:0px solid red;
	}
	
	#notificationWrapper .content{
		margin:0px;
		padding:0px;
		border:0px solid red;
		color : #FFF100;
		font-size:17px;
		line-height:42px;
		text-align:center; 
	}
	
	#notificationWrapper .closex{
		border:0px solid red;
		width:50px;
		height:42px;
		line-height:42px;
		color : #FFF100;
		text-align:center;
		position: absolute;
		top:0px;
		right:0px;
		font-size:15px;
		display:none;
	}
	
	#notificationWrapper .closex:hover{
		color : white;
	}
	
</style>
<div id="notificationWrapper">
	<%--
	<p class="content">新增客户：蜀创1中心业务一部张三的客户李哥完成签单并移交给您! 移交时间:2016年6月5号15点10分,请及时处理!</p>
	 --%>
	<!-- 
	<audio autoplay src="http://tsn.baidu.com/text2audio?tex=%E6%96%B0%E5%A2%9E%E5%AE%A2%E6%88%B7%EF%BC%9A%E8%9C%80%E5%88%9B1%E4%B8%AD%E5%BF%83%E4%B8%9A%E5%8A%A1%E4%B8%80%E9%83%A8%E5%BC%A0%E4%B8%89%E7%9A%84%E5%AE%A2%E6%88%B7%E6%9D%8E%E5%93%A5%E5%AE%8C%E6%88%90%E7%AD%BE%E5%8D%95%E5%B9%B6%E7%A7%BB%E4%BA%A4%E7%BB%99%E6%82%A8!%20%E7%A7%BB%E4%BA%A4%E6%97%B6%E9%97%B4:2016%E5%B9%B46%E6%9C%885%E5%8F%B715%E7%82%B910%E5%88%86,%E8%AF%B7%E5%8F%8A%E6%97%B6%E5%A4%84%E7%90%86!&lan=zh&tok=24.1e4dc637b0244905cce69ed8a28bbfb2.2592000.1467696505.282335-8222816&ctp=1&cuid=fe80::250b:332c:ff3e:b1a9%12"></audio>
	 -->
	<p class="closex"><i class="icon-remove"></i>关闭</p>
</div>