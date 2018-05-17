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
import javax.persistence.Transient;

/**
 * 员工信息
 * 
 * @author yangpeixin
 * 
 * @Date 2017年6月14日
 *
 *       version 1.0
 */
@Entity
public class Employee implements Serializable {
	private static final long serialVersionUID = 1947517281836232954L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id; // id

	@Column(name = "name")
	private String name; // 姓名

	@Column(name = "jobNumber")
	private String jobNumber; // 工号

	@Column(name = "account")
	private String account; // 账号

	@Column(name = "password")
	private String password; // 密码

	@Column(name = "gender")
	private String gender; // 性别

	@Column(name = "status")
	private Integer status; // 状态 （0离职、1在职、2删除）

	@Column(name = "mobile")
	private String mobile; // 手机

	@Column(name = "tel")
	private String tel; // 座机

	@Column(name = "cornet")
	private String cornet; // 短号

	@Column(name = "idCard")
	private String idCard; // 身份证

	@Column(name = "entryDate")
	@Temporal(TemporalType.TIMESTAMP)
	private Date entryDate; // 入职时间

	@Column(name = "dimissionDate")
	@Temporal(TemporalType.TIMESTAMP)
	private Date dimissionDate; // 离职

	@Column(name = "address")
	private String address; // 住址

	@ManyToOne
	@JoinColumn(name = "roleId")
	private Role role; // 角色

	@Column(name = "remark")
	private String remark; // 备注

	@ManyToOne
	@JoinColumn(name = "departmentId")
	private Department department; // 部门

	@Column(name = "headImg")
	private String headImg; // 头像

	@Column(name = "census")
	private String census; // 户籍
	@Column(name = "remarkPhoneName")
	private String remarkPhoneName; // 备注联系人
	@Column(name = "remarkPhone")
	private String remarkPhone; // 备注电话
	@Column(name = "relation")
	private String relation; // 关系
	@Column(name = "entryDatum")
	private String entryDatum; // 入职资料
	@Column(name = "talks")
	private String talks; // 谈判师
	@Transient
	private Boolean loginLock; // 是否登录锁定
	@Column
	private Integer companyId; // 公司ID
	@Column
	private String type = "NORMAL"; // 类型(SUPER_ROLE表示系统顶级角色，可以看到整个系统的所有数据)
	@Column
	private String email; // 邮箱,用于找回密码
	@Column
	private Integer customerNO; // 员工持有客户数量上限

	public Employee(String name) {
		this.name = name;
	}

	public Employee(String name, String departmentName) {
		this.name = name;
		Department department = new Department();
		department.setName(departmentName);
		this.department = department;
	}

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

	public String getJobNumber() {
		return jobNumber;
	}

	public void setJobNumber(String jobNumber) {
		this.jobNumber = jobNumber;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getCornet() {
		return cornet;
	}

	public void setCornet(String cornet) {
		this.cornet = cornet;
	}

	public String getIdCard() {
		return idCard;
	}

	public void setIdCard(String idCard) {
		this.idCard = idCard;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public String getHeadImg() {
		return headImg;
	}

	public void setHeadImg(String headImg) {
		this.headImg = headImg;
	}

	public Date getEntryDate() {
		return entryDate;
	}

	public void setEntryDate(Date entryDate) {
		this.entryDate = entryDate;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Employee() {
	}

	public Employee(Integer id, String name, String jobNumber, String account, String password, String gender,
			Integer status, String mobile, String tel, String cornet, String idCard, Role role, String remark,
			Department department, String headImg, String address, Date entryDate) {
		super();
		this.id = id;
		this.name = name;
		this.jobNumber = jobNumber;
		this.account = account;
		this.password = password;
		this.gender = gender;
		this.status = status;
		this.mobile = mobile;
		this.tel = tel;
		this.cornet = cornet;
		this.idCard = idCard;
		this.role = role;
		this.remark = remark;
		this.department = department;
		this.headImg = headImg;
		this.address = address;
		this.entryDate = entryDate;
	}

	public Employee(Integer id, String name, String jobNumber, String account, String password, String gender,
			Integer status, String mobile, String tel, String cornet, String idCard, Date entryDate, Date dimissionDate,
			String address, Role role, String remark, Department department, String headImg, String census,
			String remarkPhoneName, String remarkPhone, String relation, String entryDatum) {
		this.id = id;
		this.name = name;
		this.jobNumber = jobNumber;
		this.account = account;
		this.password = password;
		this.gender = gender;
		this.status = status;
		this.mobile = mobile;
		this.tel = tel;
		this.cornet = cornet;
		this.idCard = idCard;
		this.entryDate = entryDate;
		this.dimissionDate = dimissionDate;
		this.address = address;
		this.role = role;
		this.remark = remark;
		this.department = department;
		this.headImg = headImg;
		this.census = census;
		this.remarkPhoneName = remarkPhoneName;
		this.remarkPhone = remarkPhone;
		this.relation = relation;
		this.entryDatum = entryDatum;
	}

	public Date getDimissionDate() {
		return dimissionDate;
	}

	public void setDimissionDate(Date dimissionDate) {
		this.dimissionDate = dimissionDate;
	}

	public String getCensus() {
		return census;
	}

	public void setCensus(String census) {
		this.census = census;
	}

	public String getRemarkPhoneName() {
		return remarkPhoneName;
	}

	public void setRemarkPhoneName(String remarkPhoneName) {
		this.remarkPhoneName = remarkPhoneName;
	}

	public String getRemarkPhone() {
		return remarkPhone;
	}

	public void setRemarkPhone(String remarkPhone) {
		this.remarkPhone = remarkPhone;
	}

	public String getRelation() {
		return relation;
	}

	public void setRelation(String relation) {
		this.relation = relation;
	}

	public String getEntryDatum() {
		return entryDatum;
	}

	public void setEntryDatum(String entryDatum) {
		this.entryDatum = entryDatum;
	}

	public Employee(Integer id) {
		super();
		this.id = id;
	}

	public String getTalks() {
		return talks;
	}

	public void setTalks(String talks) {
		this.talks = talks;
	}

	public Boolean getLoginLock() {
		return loginLock;
	}

	public void setLoginLock(Boolean loginLock) {
		this.loginLock = loginLock;
	}

	public Integer getCompanyId() {
		return companyId;
	}

	public void setCompanyId(Integer companyId) {
		this.companyId = companyId;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Integer getCustomerNO() {
		return customerNO;
	}

	public void setCustomerNO(Integer customerNO) {
		this.customerNO = customerNO;
	}

}
