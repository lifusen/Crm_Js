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
 * 将客户分配给其他员工的实体类
 * 
 * @author yangpeixin
 * 
 * @Date 2017年6月14日
 *
 *       version 1.0
 */
@Entity
public class AllotCustomer implements Serializable {

	private static final long serialVersionUID = 4213977587379781181L;

	/**
	 * 分配类型
	 * 
	 * @author ham
	 *
	 */
	public static enum AllotType {
		/**
		 * 分配到部门
		 */
		TO_DEPARTMENT,
		/**
		 * 分配到员工
		 */
		TO_EMPLOYEE
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@ManyToOne
	@JoinColumn(name = "customerId")
	private Customer customer;

	@ManyToOne
	@JoinColumn(name = "operationId")
	private Employee operation; // 操作人

	@Column(name = "createDate")
	@Temporal(TemporalType.TIMESTAMP)
	private Date createDate = new Date();
	@ManyToOne
	@JoinColumn(name = "departmentId")
	private Department department; // 分配给哪个部门

	@ManyToOne
	@JoinColumn(name = "employeeId")
	private Employee employee; // 分配给哪个员工

	@Column(name = "allotType")
	private Integer allotType;

	@Column
	private Integer companyId; // 公司ID

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public Employee getOperation() {
		return operation;
	}

	public void setOperation(Employee operation) {
		this.operation = operation;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public Integer getAllotType() {
		return allotType;
	}

	public void setAllotType(Integer allotType) {
		this.allotType = allotType;
	}

	public Integer getCompanyId() {
		return companyId;
	}

	public void setCompanyId(Integer companyId) {
		this.companyId = companyId;
	}

}
