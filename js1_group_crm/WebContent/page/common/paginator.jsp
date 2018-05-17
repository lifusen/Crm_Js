<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<style>
	.active a:hover{
		cursor:pointer;
	}
</style>
<script type="text/javascript">
  		$(function(){
	        var options = {
	            currentPage: '${pageIndex}',
	            totalPages: '${totalPage}',
	            numberOfPages: 10,
	            itemTexts: function (type, page, current) { //显示文本
	                switch (type) {
		                case "first":
		                    return "首页";
		                case "prev":
		                    return "上一页";
		                case "next":
		                    return "下一页";
		                case "last":
		                    return "尾页";
		                case "page":
		                    return page;
					}
		        },
		        tooltipTitles: function(type, page, current){	//提示
		        	return '';
		        },
	            onPageClicked: function(event, originalEvent, type,page){
	                var currentTarget = $(event.currentTarget);
	                var pages = currentTarget.bootstrapPaginator("getPages");
	                if(page==pages.current){
						return ;
	                }
	                var loadId = '${loadId}';
	                if(loadId==null || loadId==""){
	                	loadId = "content";
	                }
	                $.messager.showLoadProgress();
		            $('#'+loadId).load('${url }'+"&pageIndex="+page+"&pageSize=${pageSize}",function(){
		            	$.messager.closeLoadProgress();
		            });
	            }
	        };
	        //获取分页区域
	        var paginationArea = $('#paginationArea').attr('id','paginationArea'+new Date().getTime());
	        //获取分页栏
	        var pagination = $(paginationArea).find("div.pagination");
	        //设置分页选项
	        pagination.bootstrapPaginator(options);
	        //获取页码输入框
	        var gotoTagPage = $(paginationArea).find("input.gotoTagPage");
	        //获取pageSize下拉列表框
	        var choicePageSizeSelect = $(paginationArea).find("select.choicePageSizeSelect");
	        console.log(choicePageSizeSelect.size());
	        //如果有下拉列表框
	        if(choicePageSizeSelect.length>0){
	        	//设置pageSize
		        var pageSize = '${pageSize}';
		        if(pageSize==null || typeof(pageSize) == "undefined" || pageSize==""){
		        	pageSize = 10;
				}
		        choicePageSizeSelect.val(pageSize);
		        
				//下拉列表框选择事件	        
		        choicePageSizeSelect.change(function(){
		        	var v = $(this).val();
		        	var loadId = '${loadId}';
	                if(loadId==null || loadId==""){
	               		loadId = "content";
	                }
	                $.messager.showLoadProgress();
		        	 $('#'+loadId).load('${url }'+"&pageIndex=1&pageSize="+v,function(){
			            	$.messager.closeLoadProgress();
			            });
		        });	
	        }
	        
	        
	        //跳转到指定的页
	        gotoTagPage.keyup(function(event){
				 if(event.keyCode == 13){
					 var bool= /^[0-9]*$/;
					 var pageIndex = $(this).val();
					 if(pageIndex==null || typeof(pageIndex) == "undefined" || pageIndex==""){
						 pageIndex=1;
					 }
					 if(!bool.test(pageIndex)){
						 alert("请输入大于0的整数");
						 return ;
					 }
					 pageIndex = parseInt(pageIndex);
					 var totalPage = '${totalPage}';
					 if(pageIndex > totalPage){
						 pageIndex = totalPage;
					 }
					 var loadId = '${loadId}';
	                 if(loadId==null || loadId==""){
	                	loadId = "content";
	                 }
	                 $.messager.showLoadProgress();
					 $('#'+loadId).load('${url }'+"&pageIndex="+pageIndex+"&pageSize=${pageSize}",function(){
			            	$.messager.closeLoadProgress();
			            });
				 }
			});
  		});
 </script>
 
<div id="paginationArea">
	<div style="width:226px;float: left;">
	<c:if test="${param.isShowPageSizeSelect!='false'}">
		<select style="width:50px;height:23px;margin-bottom:6px;padding:0px;" class="choicePageSizeSelect">
	 		<option value="5">5</option>
	 		<option value="10">10</option>
	 		<option value="20">20</option>
	 		<option value="30">30</option>
	 		<option value="50">50</option>
	 		<option value="100">100</option>
	 		<option value="200">200</option>
	 		<option value="300">300</option>
	 		<option value="500">500</option>
	 	</select> 
	 </c:if>
	 <input style="width:30px;height: 13px;margin-top: 5px;" type="text" value="${pageIndex }" class="gotoTagPage"/>/${totalPage }页,共${count }条数据</div>
	 <div class="pagination" style="text-align: right; margin-top: 0px;float:right;"></div>
</div>
 <div style="clear: both;"></div>
<input id="pageSize" type="hidden" value="${pageSize }"/>