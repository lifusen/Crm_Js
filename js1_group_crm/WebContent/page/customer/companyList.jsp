<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<p style="float:left;">　　 　　部门：</p>
<p id="companyChoiceArea" style="max-width:1000px;float: left;">
	<a class="green btn mini all choiceBtn departVal hideEmployee" val="" href="javascript:;">不限</a>
	<c:forEach items="${requestScope.departments }" var="department">
		<a class="btn mini choiceBtn departVal showEmployee" val="${department.id }" href="javascript:;">${department.name}</a>
	</c:forEach>
</p>
<script type="text/javascript">
$("#companyChoiceArea .choiceBtn").bind("click",function(){
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
	
	
	if($(this).hasClass("hideEmployee")){
		// 获取客户经理
		$("#customerManageP").load("employee/getEmployeeByDId.do?dIds="+'');
	}else if($(this).hasClass('showEmployee')){
		var em = $(".showEmployee");
		var dIds="";
		for(var i=0;i<em.length;i++){
			if($(em[i]).hasClass("green")){
				var id = $(em[i]).attr("val");
				if(id!=""){
					dIds = dIds + id+",";
				}
			}
		}
		// 获取客户经理
		$("#customerManageP").load("employee/getEmployeeByDId.do?dIds="+dIds);
	}
	customerSearchList("");
});
</script>