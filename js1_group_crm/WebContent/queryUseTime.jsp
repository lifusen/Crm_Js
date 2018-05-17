<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="cn.itproject.crm.controller.filter.QueryUseTimeStore" %>
<%@ page import="cn.itproject.crm.controller.filter.QueryUseTime" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>查询时间统计</title>
<style type="text/css">
	#container{
		width:1100px;
		margin:20px auto;
	}
	#container h1{
		text-align:center;
	}
	#container table{
		width:100%;
		border-collapse: collapse;
	}
	#container table tr{
		height:30px;
	}
	
	#container p{
		text-align: center;	
	}
	
	#container p a{
		padding:5px;
		text-decoration: none;	
	}
	
	#container p a:hover{
		color : red;	
		text-decoration: underline;	
	}
	
</style>
</head>
<body>

	<%
		String type = request.getParameter("type");
		String order = request.getParameter("order");
		QueryUseTime[] list = new QueryUseTime[0];
		if("diffTime".equals(type)){
			if("asc".equals(order)){
				list = QueryUseTimeStore.getListWithDiffTimeASC();
			}else{
				list = QueryUseTimeStore.getListWithDiffTimeDESC();
			}
		}else if("startDate".equals(type)){
			if("asc".equals(order)){
				list = QueryUseTimeStore.getListWithStartDateASC();
			}else{
				list = QueryUseTimeStore.getListWithStartDateDESC();
			}
		}else{
			list = QueryUseTimeStore.getListWithDiffTimeDESC();
		}
		request.setAttribute("list", list);
	%>
	<div id="container">
		<h1>查询时间统计</h1>
		<p>
			<a href="${pageContext.request.contextPath}/queryUseTime.jsp?type=diffTime&order=asc">
				按时间顺序排列
			</a>
			<a href="${pageContext.request.contextPath}/queryUseTime.jsp?type=diffTime&order=desc">
				按时间倒序排列
			</a>
			<a href="${pageContext.request.contextPath}/queryUseTime.jsp?type=startDate&order=asc">
				按开始时间顺序排列
			</a>
			<a href="${pageContext.request.contextPath}/queryUseTime.jsp?type=startDate&order=desc">
				按开始时间倒序排列
			</a>
		</p>
		<table border="1" cellpadding="0" cellspacing="0">
			<tr>
				<th>序号</th>
				<th>耗时(毫秒)</th>
				<th>耗时(分/秒)</th>
				<th>请求路径</th>
				<th>开始时间</th>
				<th>结束时间</th>
			</tr>
			<c:if test="${not empty list}">
				<c:forEach var="obj" items="${list}" varStatus="vs">
					<tr>
						<td>${vs.count}</td>
						<td align="center">${obj.diffTime }</td>
						<td align="center">${obj.diffTimeString }</td>
						<td>${obj.url }</td>
						<td><fmt:formatDate value="${obj.startDate }" pattern="yyyy-MM-dd HH:mm:ss"/></td>
						<td><fmt:formatDate value="${obj.endDate }" pattern="yyyy-MM-dd HH:mm:ss"/></td>
					</tr>			
				</c:forEach>
				</table>
			</c:if>
	</div>
</body>
</html>