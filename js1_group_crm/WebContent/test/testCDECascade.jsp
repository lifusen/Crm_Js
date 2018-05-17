<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Test</title>
<%
	String contextPath = request.getContextPath();
	String rootPath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+contextPath;
	request.setAttribute("rootPath", rootPath);
%>
<script type="text/javascript">
	window.rootPath = '${rootPath}';
</script>
<script type="text/javascript" src="${pageContext.request.contextPath}/media/js/jquery-1.10.1.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/companyDepartmentEmployeeCascade.js"></script>
</head>
<body>
	<h1>Test2</h1>
	权证中心
	<select id="companySelect">
		
	</select>
	权证部门
	<select id="departmentSelect">
		
	</select>
	权证专员
	<select id="employeeSelect">
	
	</select>
	<hr/>
	
	业务中心
	<select id="companySelect1">
		
	</select>
	业务部门
	<select id="departmentSelect1">
		
	</select>
	业务员
	<select id="employeeSelect1">
		
	</select>
<script type="text/javascript">
	var cdec = new CompanyDepartmentEmployeeCascade({
		companySelect : $("#companySelect"),
		departmentSelect : $("#departmentSelect"),
		employeeSelect : $("#employeeSelect"),
		companyLabel : "请选择权证公司",
		departmentLabel : "请选择权证部门",
		employeeLabel : "请选择权证专员",
		level : 3,
		departmentId : 1
	});
	cdec.init();
	/**
	var cdec1 = new CompanyDepartmentEmployeeCascade({
		companySelect : $("#companySelect1"),
		departmentSelect : $("#departmentSelect1"),
		employeeSelect : $("#employeeSelect1"),
		companyLabel : "请选择业务公司",
		departmentLabel : "请选择业务部门",
		employeeLabel : "请选择业务员",
		level : 3,
		departmentId : 1
	});
	cdec1.init();
	**/
	
</script>	
</body>
</html>