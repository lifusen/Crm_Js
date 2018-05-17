function CompanyDepartmentEmployeeCascade(option){
		// 1.下拉列表框元素
		this.$companySelect = option.companySelect;
		this.$departmentSelect = option.departmentSelect;
		this.$employeeSelect = option.employeeSelect;
		this.rootPath = window.rootPath;
		this.companyType = option.companyType || 1;
		this.departmentType = option.departmentType || null;
		this.companyUrl = this.rootPath + "/company/getCompanyListByType.do?type="+this.companyType;
		this.departmentUrl = this.rootPath + "/department/getDepartmentListMapByCompanyId.do";
		this.employeeUrl = this.rootPath + "/employee/getEmployeeListMapByDepartmentId.do";
		// 2.设置level,默认为3
		this.level =  option.level || 3;
		// 3.设置默认显示的label
		this.companyLabel = option.companyLabel || "所有公司";
		this.departmentLabel = option.departmentLabel || "所有部门";
		this.employeeLabel = option.employeeLabel || "所有员工";
		// 4.设置是否默认显示label
		this.initLabel = option.initLabel || true;
		// 5.当只显示部门和员工时,需传入当前的公司id,默认为1
		this.paramCompanyId = option.paramCompanyId || 1;
		// 6.当只员工时,需传入当前的部门id
		this.paramDepartmentId = option.paramDepartmentId;
		// 7.设置下拉列表框针对不同级别是否隐藏
		this.levelHidden = option.levelHidden || true;
		
		// 初始化方法
		this.init = function(){
			var _this = this;
			if(_this.initLabel){
				_this.loadDepartmentsByCompanyId(-1);		// 初始化部门select
				_this.loadEmployeesByDepartmentId(-1);		// 初始化员工select
			}
			
			if(_this.level == 3){	// 显示公司/部门/员工
				// 获取公司数据
				_this.initCompanyData();
			}else if(_this.level == 2){	//显示部门/员工
				_this.loadDepartmentsByCompanyId(_this.paramCompanyId);
				if(_this.levelHidden){
					_this.$companySelect.hide();
				}
			}else if(_this.level == 1){	//显示员工
				if(_this.levelHidden){
					_this.$companySelect.hide();
					_this.$departmentSelect.hide();
				}
				_this.loadEmployeesByDepartmentId(_this.paramDepartmentId);
			}
		},
		this.initCompanyData = function(type){
			var _this = this;
			_this.$companySelect.empty();
			$("<option value=''>"+_this.companyLabel+"</option>").appendTo(_this.$companySelect);
			$.getJSON(_this.companyUrl,{type:type},function(companys){
				for(var i = 0; i < companys.length; i++){
					var company = companys[i];
					$("<option value='"+company.id+"'>"+company.name+"</option>").appendTo(_this.$companySelect);
				}
				
				// 给公司下拉框绑定change事件
				_this.bindCompanyChangeEvent();
			});
			
		},
		this.bindCompanyChangeEvent = function(){
			var _this = this;
			_this.$companySelect.change(function(){
				var id = $(this).val();
				// 删除部门的change事件
				_this.unbindDepartmentChangeEvent();
				// 通过公司ID加载部门列表
				_this.loadDepartmentsByCompanyId(id);
			});
		},
		this.bindDepartmentChangeEvent = function(){
			var _this = this;
			_this.$departmentSelect.change(function(){
				var id = $(this).val();
				// 通过部门ID加载员工列表
				_this.loadEmployeesByDepartmentId(id);
			});
		},
		this.unbindDepartmentChangeEvent = function(){
			this.$departmentSelect.unbind("change");
		},
		this.loadDepartmentsByCompanyId = function(companyId){
			var _this = this;
			_this.$departmentSelect.empty();
			$("<option value=''>"+_this.departmentLabel+"</option>").appendTo(_this.$departmentSelect);
			// 如果没有选择对应的公司,则不需要加载部门数据
			if(!companyId || companyId < 1){
				return;
			}
			$.getJSON(_this.departmentUrl,{companyId:companyId,type:_this.departmentType},function(departments){
				for(var i = 0; i < departments.length; i++){
					var department = departments[i];
					$("<option value='"+department.id+"'>"+department.name+"</option>").appendTo(_this.$departmentSelect);
				}
				
				// 给部门绑定change事件
				_this.bindDepartmentChangeEvent();
			});
		},
		this.loadEmployeesByDepartmentId = function(departmentId){
			var _this = this;
			_this.$employeeSelect.empty();
			$("<option value=''>"+_this.employeeLabel+"</option>").appendTo(_this.$employeeSelect);
			
			// 如果没有选择对应的部门,则不需要加载员工数据
			if(!departmentId || departmentId < 1){
				return;
			}
			$.getJSON(_this.employeeUrl,{departmentId:departmentId},function(employees){
				for(var i = 0; i < employees.length; i++){
					var employee = employees[i];
					$("<option value='"+employee.id+"'>"+employee.name+"</option>").appendTo(_this.$employeeSelect);
				}
			});
		}
};