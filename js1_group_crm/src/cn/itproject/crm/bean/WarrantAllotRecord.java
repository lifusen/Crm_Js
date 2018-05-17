package cn.itproject.crm.bean;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
/**
 * 权证分配表
 * @author jianghan
 *
 */
@Entity
public class WarrantAllotRecord implements Serializable{
	private static final long serialVersionUID = -8157688363809038826L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;						// 主键
	@Column
	private Integer companyId; 				// 分配者公司ID
	@Column
	private Integer departmentId;			// 分配者部门ID
	@Column
	private Integer employeeId;				// 分配者ID,非空
	@Column
	@Temporal(TemporalType.TIMESTAMP)
	private Date allotTime;					// 分配时间,非空
	@Column
	private Integer customerId;				// 客户ID
	@Column
	private Integer signCustomerId;			// 签单客户ID
	@Column
	private Integer allotType;				// 分配类型,非空,1:分配给权证中心/2:分配给权证部门/3.分配给权证专员
	@Column
	private Integer operateType;			// 操作类型,非空,区分是业务员分配的还是权证分配的,1:业务员/2:权证人员
	@Column
	private Integer warrantCompanyId; 		// 接收客户的权证公司ID,非空
	@Column
	private Integer warrantDepartmentId;	// 接收客户的部门ID
	@Column
	private Integer warrantEmployeeId;		// 接收客户的ID
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getCompanyId() {
		return companyId;
	}
	public void setCompanyId(Integer companyId) {
		this.companyId = companyId;
	}
	public Integer getDepartmentId() {
		return departmentId;
	}
	public void setDepartmentId(Integer departmentId) {
		this.departmentId = departmentId;
	}
	public Integer getEmployeeId() {
		return employeeId;
	}
	public void setEmployeeId(Integer employeeId) {
		this.employeeId = employeeId;
	}
	public Date getAllotTime() {
		return allotTime;
	}
	public void setAllotTime(Date allotTime) {
		this.allotTime = allotTime;
	}
	public Integer getCustomerId() {
		return customerId;
	}
	public void setCustomerId(Integer customerId) {
		this.customerId = customerId;
	}
	public Integer getSignCustomerId() {
		return signCustomerId;
	}
	public void setSignCustomerId(Integer signCustomerId) {
		this.signCustomerId = signCustomerId;
	}
	public Integer getAllotType() {
		return allotType;
	}
	public void setAllotType(Integer allotType) {
		this.allotType = allotType;
	}
	public Integer getOperateType() {
		return operateType;
	}
	public void setOperateType(Integer operateType) {
		this.operateType = operateType;
	}
	public Integer getWarrantCompanyId() {
		return warrantCompanyId;
	}
	public void setWarrantCompanyId(Integer warrantCompanyId) {
		this.warrantCompanyId = warrantCompanyId;
	}
	public Integer getWarrantDepartmentId() {
		return warrantDepartmentId;
	}
	public void setWarrantDepartmentId(Integer warrantDepartmentId) {
		this.warrantDepartmentId = warrantDepartmentId;
	}
	public Integer getWarrantEmployeeId() {
		return warrantEmployeeId;
	}
	public void setWarrantEmployeeId(Integer warrantEmployeeId) {
		this.warrantEmployeeId = warrantEmployeeId;
	}
}
