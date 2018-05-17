new CompanyDepartmentEmployeeCascade({
	companySelect : $("select.warrantCompanyId"),
	departmentSelect : $("select.warrantDepartmentId"),
	employeeSelect : $("select.warrantEmployeeId"),
	companyType : 2,
	companyLabel : "请选择权证公司",
	departmentLabel : "请选择权证部门",
	employeeLabel : "请选择权证专员",
	level : 3
}).init();