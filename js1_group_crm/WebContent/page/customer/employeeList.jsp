<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<div style="clear: left;">
	<p style="float:left;">　　 客户经理：</p>
	<p style="max-width:1000px;float: left;">
		<a class="green btn mini all filterBtn1 employeeVal" val="" href="javascript:;">不限</a>
		<c:forEach items="${requestScope.employees }" var="em">
			<a class="btn mini filterBtn1 employeeVal" val="${em.id }" href="javascript:;">${em.name }</a>
		</c:forEach>
	</p>
</div>


<script type="text/javascript">
$(".filterBtn1").bind("click",function(){
	if($(this).hasClass("all")){
		$(this).parent().children().removeClass("green");
		$(this).addClass("green");
	}else{
		var children = $(this).parent().children();
		$(children[0]).removeClass("green");
		if($(this).hasClass("green")){
			$(this).removeClass("green");
			var flag = false;
			for(var i=0;i<children.length;i++){
				if($(children[i]).hasClass('green')){
					flag=true;
					break;
				}
			}
			if(!flag){
				$(children[0]).addClass("green");
			}
		}else{
			$(this).addClass("green");
		}
	}
	customerSearchList("");
});
</script>