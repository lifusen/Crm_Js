package cn.itproject.crm.controller.viewbean;

import java.io.Serializable;

/**
 * 公司部门员工信息
 * @author MrLiu
 *
 */
public class CDEVo implements Serializable{
	private static final long serialVersionUID = 4205738500544549738L;
	private Integer companyId;			// 公司ID
	private String companyName;			// 公司名称
	private Integer departmentId;		// 部门ID
	private String departmentName;		// 部门名称
	private Integer employeeId;			// 员工ID
	private String employeeName;		// 员工名称
	public Integer getCompanyId() {
		return companyId;
	}
	public void setCompanyId(Integer companyId) {
		this.companyId = companyId;
	}
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	public Integer getDepartmentId() {
		return departmentId;
	}
	public void setDepartmentId(Integer departmentId) {
		this.departmentId = departmentId;
	}
	public String getDepartmentName() {
		return departmentName;
	}
	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}
	public Integer getEmployeeId() {
		return employeeId;
	}
	public void setEmployeeId(Integer employeeId) {
		this.employeeId = employeeId;
	}
	public String getEmployeeName() {
		return employeeName;
	}
	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}
	@Override
	public String toString() {
		return "CDEVo [companyId=" + companyId + ", companyName=" + companyName + ", departmentId=" + departmentId
				+ ", departmentName=" + departmentName + ", employeeId=" + employeeId + ", employeeName=" + employeeName
				+ "]";
	}
}
