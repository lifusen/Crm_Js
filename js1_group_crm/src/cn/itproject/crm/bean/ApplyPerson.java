package cn.itproject.crm.bean;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.hibernate.annotations.Type;

/**
 * 应聘员工
 * 
 * @author yangpeixin
 * 
 * @Date 2017年6月21日
 *
 *       version 1.0
 */
@Entity
public class ApplyPerson implements Serializable {
	private static final long serialVersionUID = 6224354187813218263L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	// ------------------------------------------ 1.基本信息 start
	// --------------------------------------------
	@Column(length = 100)
	private String name; // 名称
	@Column
	private String gender; // 性别
	@Column
	private Integer age; // 年龄
	@Column(length = 100)
	private String phone; // 电话
	@Column
	private String jobs; // 应聘岗位
	@Column
	private String year; // 工作年限
	@Column
	private String situation; // 情况说明
	@Column
	@Lob
	@Type(type = "text")
	private String otherInfo; // 其它消息,批量导入时使用
	@Column
	@Temporal(TemporalType.TIMESTAMP)
	private Date creattime; // 添加时间
	// ------------------------------------------ 基本信息 end
	// ------------------------------------- 5.客户从属关系相关字段 start
	// ------------------------------------
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "departmentId")
	private Department department; // 属于哪个部门
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "employeeId")
	private Employee employee; // 属于哪个员工
	@Column
	private Integer state = CustomerState.unassigned.ordinal(); // 客户状态:默认为刚录入
	@Column
	private Integer addPersonId; // 录入人员id
	@Column
	private String addPersonName; // 录入人员名称
	@Column
	@Temporal(TemporalType.DATE)
	private Date createDate = new Date(); // 录入时间(年月日)
	@Column
	@Temporal(TemporalType.TIMESTAMP)
	private Date createTime = new Date(); // 录入时间(年月日时分秒毫秒)
	// ------------------------------------- 客户从属关系相关字段 end
	// --------------------------------------------

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getJobs() {
		return jobs;
	}

	public void setJobs(String jobs) {
		this.jobs = jobs;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public String getSituation() {
		return situation;
	}

	public void setSituation(String situation) {
		this.situation = situation;
	}

	public String getOtherInfo() {
		return otherInfo;
	}

	public void setOtherInfo(String otherInfo) {
		this.otherInfo = otherInfo;
	}

	public Date getCreattime() {
		return creattime;
	}

	public void setCreattime(Date creattime) {
		this.creattime = creattime;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
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

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	public Integer getAddPersonId() {
		return addPersonId;
	}

	public void setAddPersonId(Integer addPersonId) {
		this.addPersonId = addPersonId;
	}

	public String getAddPersonName() {
		return addPersonName;
	}

	public void setAddPersonName(String addPersonName) {
		this.addPersonName = addPersonName;
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

}
