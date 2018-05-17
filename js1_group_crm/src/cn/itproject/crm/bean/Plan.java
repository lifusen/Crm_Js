package cn.itproject.crm.bean;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * 计划
 * @author MrLiu
 *
 */
@Entity 
public class Plan implements Serializable{ 
	private static final long serialVersionUID = 8423659017892968273L;
	/**部门*/
	public static final Integer DEPT = 0;			//部门
	/**员工*/
	public static final Integer EMP  = 1;			//员工
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@Column
	private Integer type;					//类型:部门/员工
	@Column
	private Integer visit;					//目标上门
	@Column
	private Integer sign;					//目标签单
	@Column
	private Double performance;				//目标业绩
	@ManyToOne
	@JoinColumn(name="departmentId")
	private Department department;			//部门
	@ManyToOne
	@JoinColumn(name="employeeId")
	private Employee employee;				//制定人
	@Temporal(TemporalType.DATE)
	@Column
	private Date createDate = new Date();	//创建日期
	@Temporal(TemporalType.TIMESTAMP)
	@Column
	private Date createTime = new Date();	//创建时间
	@Temporal(TemporalType.TIMESTAMP)
	@Column
	private Date updateTime = new Date();	//修改时间
	@Column
	private Integer companyId;				//公司ID
	public Plan() {
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public Integer getVisit() {
		return visit;
	}
	public void setVisit(Integer visit) {
		this.visit = visit;
	}
	public Integer getSign() {
		return sign;
	}
	public void setSign(Integer sign) {
		this.sign = sign;
	}
	public Double getPerformance() {
		return performance;
	}
	public void setPerformance(Double performance) {
		this.performance = performance;
	}
	public Employee getEmployee() {
		return employee;
	}
	public void setEmployee(Employee employee) {
		this.employee = employee;
	}
	public Department getDepartment() {
		return department;
	}
	public void setDepartment(Department department) {
		this.department = department;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public Date getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	public Integer getCompanyId() {
		return companyId;
	}
	public void setCompanyId(Integer companyId) {
		this.companyId = companyId;
	}
}
