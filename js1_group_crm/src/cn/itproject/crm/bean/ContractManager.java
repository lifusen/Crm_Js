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
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class ContractManager implements Serializable {
	private static final long serialVersionUID = 3610769888013390723L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	/* ————————————————————-项目情况Start ———————————— */

	@Column
	private String itemNo; // 项目编号
	@Column
	private String itemName; // 项目名称
	@Column
	private String custAuthorizedPer; // 客户项目授权人
	@Column
	private String custContactPer; // 客户项目联系人
	@Column
	private String custqq; // 客户QQ
	@Column
	private String custEmail; // 客户邮箱
	@Column
	private String custAddress; // 客户通讯地址
	@Column
	private String contactPer; // 我方联系人
	@Column
	private String qq; // 我方联系人QQ
	@Column
	private String email; // 我方联系人邮箱
	@Column
	private Date startDate; // 起始时间
	@Column
	private Date endDate; // 结束日期
	@Column
	private String cycle; // 维护周期
	@Column
	private String contractAddr; // 合同附件地址

	/*------------------------项目环境配置---------------------*/
	@Column
	private String cpu; // CPU
	@Column
	private String memory; // 内存
	@Column
	private String hardDisk; // 硬盘
	@Column
	private String bandwidth; // 带宽
	@Column
	private String server; // 服务器系统及版本
	@Column
	private String devetools; // 开发工具
	@Column
	private String dataValue; // 数据库
	@Column
	private String codeMana; // 代码管理器
	@Column
	private String envirVarible;// 环境变量
	@Column
	private String other;// 其他

	/*------------------项目开发情况-------------------*/

	@Column
	private Date startDatePlan; // 计划开始时间
	@Column
	private Date endDatePlan; // 计划完成时间
	@Column
	private String productMana; // 产品经理
	@Column
	private String projectLeader;// 项目负责人
	@Column
	private String groupLeader; // 项目组长
	@Column
	private String groupPer; // 组员
	@Column
	private String extCooperation; // 外部合作项目内容
	@Column
	private String extTeam; // 外部个人或者团队
	@Column
	private String extEndTime; // 外部完成时间
	@Column
	private String testPer; // 测试人员
	@Column
	private String extContent; // 外援内容
	@Column
	private String extPer; // 外援人员

	/*-------------项目验收维护--------------------*/

	@Column
	private String accepData; // 验收资料
	@Column
	private String accDelivery;// 验收资料交付
	@Column
	private String sourceCode; // 源代码
	@Column
	private String souDelivery;// 源代码交付
	@Column
	private String serverProvider; // 服务器提供方
	@Column
	private String serverVendor;// 服务器供应商
	@Column
	private String serverAddr;// 服务器地址
	@Column
	private String serverAccount;// 服务器账号
	@Column
	private String serverPassword;// 服务器密码
	@Column
	private String domainName; // 上线域名
	@Column
	private String managementAddr; // 后台管理地址
	@Column
	private String managementAccount; // 后台管理账号
	@Column
	private String managementPassword; // 后台管理密码
	@Column
	private String RemServerAddr; // 远程服务器部署地址
	@Column
	private String localServerAddr;// 本地服务器部署地址
	@Column
	private String localServerBackUp; // 本地服务器备份位置
	@Column
	private String hardDiskBackUp;// 移动硬盘备份位置

	/*-----------------------合同信息------------------*/
	@Column
	private String contractNO; // 合同编号
	@Column
	private String contractPrice; // 合同总金额
	@Column
	private String photoScan; // 拍照扫描
	@Column
	private Date signDate;// 签订日期
	@Column
	private Date completDate;// 完成日期
	@Column
	private Integer price; // 支付金额
	@Column
	private String paymentTerms; // 支付条件
	@Column
	private Integer havetopay; // 已支付金额
	@Column
	private Date payDate; // 支付日期

	/*--------------项目成本列表---------------*/
	@Column
	private Date planProject; // 计划工期
	@Column
	private Integer planCost; // 计划成本
	@Column
	private String construction; // 已投入工期
	@Column
	private Integer beyond;// 超出、结余
	@Column
	private Integer wage;// 工资
	@Column
	private Integer BusinessFee; // 商务费
	@Column
	private Integer cooperationFee; // 合作包费
	@Column
	private Integer taxFee; // 税务费
	@Column
	private Integer travelFee; // 差旅费
	@Column
	private Integer entertainment; // 招待费

	/*---------------------预留字段-------------------*/
	@Column
	private String other1; // 预留字段1
	@Column
	private String other2;// 预留字段2
	@Column
	private String other3;// 预留字段3
	@Column
	private String other4;// 预留字段4
	@Column
	private String age;// 预留字段5
	@Column
	private String phone;// 预留字段6

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

	@Column
	private String addPersonName; // 录入人员名称
	@Column
	@Temporal(TemporalType.DATE)
	private Date createDate = new Date(); // 录入时间(年月日)
	@Column
	@Temporal(TemporalType.TIMESTAMP)
	private Date createTime = new Date(); // 录入时间(年月日时分秒毫秒)
	// ------------------------------------- 客户从属关系相关字段 end

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getItemNo() {
		return itemNo;
	}

	public void setItemNo(String itemNo) {
		this.itemNo = itemNo;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public String getCustAuthorizedPer() {
		return custAuthorizedPer;
	}

	public void setCustAuthorizedPer(String custAuthorizedPer) {
		this.custAuthorizedPer = custAuthorizedPer;
	}

	public String getCustContactPer() {
		return custContactPer;
	}

	public void setCustContactPer(String custContactPer) {
		this.custContactPer = custContactPer;
	}

	public String getCustqq() {
		return custqq;
	}

	public void setCustqq(String custqq) {
		this.custqq = custqq;
	}

	public String getCustEmail() {
		return custEmail;
	}

	public void setCustEmail(String custEmail) {
		this.custEmail = custEmail;
	}

	public String getCustAddress() {
		return custAddress;
	}

	public void setCustAddress(String custAddress) {
		this.custAddress = custAddress;
	}

	public String getContactPer() {
		return contactPer;
	}

	public void setContactPer(String contactPer) {
		this.contactPer = contactPer;
	}

	public String getQq() {
		return qq;
	}

	public void setQq(String qq) {
		this.qq = qq;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public String getCycle() {
		return cycle;
	}

	public void setCycle(String cycle) {
		this.cycle = cycle;
	}

	public String getContractAddr() {
		return contractAddr;
	}

	public void setContractAddr(String contractAddr) {
		this.contractAddr = contractAddr;
	}

	public String getCpu() {
		return cpu;
	}

	public void setCpu(String cpu) {
		this.cpu = cpu;
	}

	public String getMemory() {
		return memory;
	}

	public void setMemory(String memory) {
		this.memory = memory;
	}

	public String getHardDisk() {
		return hardDisk;
	}

	public void setHardDisk(String hardDisk) {
		this.hardDisk = hardDisk;
	}

	public String getBandwidth() {
		return bandwidth;
	}

	public void setBandwidth(String bandwidth) {
		this.bandwidth = bandwidth;
	}

	public String getServer() {
		return server;
	}

	public void setServer(String server) {
		this.server = server;
	}

	public String getDevetools() {
		return devetools;
	}

	public void setDevetools(String devetools) {
		this.devetools = devetools;
	}

	public String getDataValue() {
		return dataValue;
	}

	public void setDataValue(String dataValue) {
		this.dataValue = dataValue;
	}

	public String getCodeMana() {
		return codeMana;
	}

	public void setCodeMana(String codeMana) {
		this.codeMana = codeMana;
	}

	public String getEnvirVarible() {
		return envirVarible;
	}

	public void setEnvirVarible(String envirVarible) {
		this.envirVarible = envirVarible;
	}

	public String getOther() {
		return other;
	}

	public void setOther(String other) {
		this.other = other;
	}

	public Date getStartDatePlan() {
		return startDatePlan;
	}

	public void setStartDatePlan(Date startDatePlan) {
		this.startDatePlan = startDatePlan;
	}

	public Date getEndDatePlan() {
		return endDatePlan;
	}

	public void setEndDatePlan(Date endDatePlan) {
		this.endDatePlan = endDatePlan;
	}

	public String getProductMana() {
		return productMana;
	}

	public void setProductMana(String productMana) {
		this.productMana = productMana;
	}

	public String getProjectLeader() {
		return projectLeader;
	}

	public void setProjectLeader(String projectLeader) {
		this.projectLeader = projectLeader;
	}

	public String getGroupLeader() {
		return groupLeader;
	}

	public void setGroupLeader(String groupLeader) {
		this.groupLeader = groupLeader;
	}

	public String getGroupPer() {
		return groupPer;
	}

	public void setGroupPer(String groupPer) {
		this.groupPer = groupPer;
	}

	public String getExtCooperation() {
		return extCooperation;
	}

	public void setExtCooperation(String extCooperation) {
		this.extCooperation = extCooperation;
	}

	public String getExtTeam() {
		return extTeam;
	}

	public void setExtTeam(String extTeam) {
		this.extTeam = extTeam;
	}

	public String getExtEndTime() {
		return extEndTime;
	}

	public void setExtEndTime(String extEndTime) {
		this.extEndTime = extEndTime;
	}

	public String getTestPer() {
		return testPer;
	}

	public void setTestPer(String testPer) {
		this.testPer = testPer;
	}

	public String getExtContent() {
		return extContent;
	}

	public void setExtContent(String extContent) {
		this.extContent = extContent;
	}

	public String getExtPer() {
		return extPer;
	}

	public void setExtPer(String extPer) {
		this.extPer = extPer;
	}

	public String getAccepData() {
		return accepData;
	}

	public void setAccepData(String accepData) {
		this.accepData = accepData;
	}

	public String getAccDelivery() {
		return accDelivery;
	}

	public void setAccDelivery(String accDelivery) {
		this.accDelivery = accDelivery;
	}

	public String getSourceCode() {
		return sourceCode;
	}

	public void setSourceCode(String sourceCode) {
		this.sourceCode = sourceCode;
	}

	public String getSouDelivery() {
		return souDelivery;
	}

	public void setSouDelivery(String souDelivery) {
		this.souDelivery = souDelivery;
	}

	public String getServerProvider() {
		return serverProvider;
	}

	public void setServerProvider(String serverProvider) {
		this.serverProvider = serverProvider;
	}

	public String getServerVendor() {
		return serverVendor;
	}

	public void setServerVendor(String serverVendor) {
		this.serverVendor = serverVendor;
	}

	public String getServerAddr() {
		return serverAddr;
	}

	public void setServerAddr(String serverAddr) {
		this.serverAddr = serverAddr;
	}

	public String getServerAccount() {
		return serverAccount;
	}

	public void setServerAccount(String serverAccount) {
		this.serverAccount = serverAccount;
	}

	public String getServerPassword() {
		return serverPassword;
	}

	public void setServerPassword(String serverPassword) {
		this.serverPassword = serverPassword;
	}

	public String getDomainName() {
		return domainName;
	}

	public void setDomainName(String domainName) {
		this.domainName = domainName;
	}

	public String getManagementAddr() {
		return managementAddr;
	}

	public void setManagementAddr(String managementAddr) {
		this.managementAddr = managementAddr;
	}

	public String getManagementAccount() {
		return managementAccount;
	}

	public void setManagementAccount(String managementAccount) {
		this.managementAccount = managementAccount;
	}

	public String getManagementPassword() {
		return managementPassword;
	}

	public void setManagementPassword(String managementPassword) {
		this.managementPassword = managementPassword;
	}

	public String getRemServerAddr() {
		return RemServerAddr;
	}

	public void setRemServerAddr(String remServerAddr) {
		RemServerAddr = remServerAddr;
	}

	public String getLocalServerAddr() {
		return localServerAddr;
	}

	public void setLocalServerAddr(String localServerAddr) {
		this.localServerAddr = localServerAddr;
	}

	public String getLocalServerBackUp() {
		return localServerBackUp;
	}

	public void setLocalServerBackUp(String localServerBackUp) {
		this.localServerBackUp = localServerBackUp;
	}

	public String getHardDiskBackUp() {
		return hardDiskBackUp;
	}

	public void setHardDiskBackUp(String hardDiskBackUp) {
		this.hardDiskBackUp = hardDiskBackUp;
	}

	public String getContractNO() {
		return contractNO;
	}

	public void setContractNO(String contractNO) {
		this.contractNO = contractNO;
	}

	public String getContractPrice() {
		return contractPrice;
	}

	public void setContractPrice(String contractPrice) {
		this.contractPrice = contractPrice;
	}

	public String getPhotoScan() {
		return photoScan;
	}

	public void setPhotoScan(String photoScan) {
		this.photoScan = photoScan;
	}

	public Date getCompletDate() {
		return completDate;
	}

	public void setCompletDate(Date completDate) {
		this.completDate = completDate;
	}

	public String getPaymentTerms() {
		return paymentTerms;
	}

	public void setPaymentTerms(String paymentTerms) {
		this.paymentTerms = paymentTerms;
	}

	public Date getPlanProject() {
		return planProject;
	}

	public void setPlanProject(Date planProject) {
		this.planProject = planProject;
	}

	public String getConstruction() {
		return construction;
	}

	public void setConstruction(String construction) {
		this.construction = construction;
	}

	public Integer getBusinessFee() {
		return BusinessFee;
	}

	public void setBusinessFee(Integer businessFee) {
		BusinessFee = businessFee;
	}

	public String getOther1() {
		return other1;
	}

	public void setOther1(String other1) {
		this.other1 = other1;
	}

	public Integer getBeyond() {
		return beyond;
	}

	public void setBeyond(Integer beyond) {
		this.beyond = beyond;
	}

	public Integer getWage() {
		return wage;
	}

	public void setWage(Integer wage) {
		this.wage = wage;
	}

	public Integer getCooperationFee() {
		return cooperationFee;
	}

	public void setCooperationFee(Integer cooperationFee) {
		this.cooperationFee = cooperationFee;
	}

	public Integer getTaxFee() {
		return taxFee;
	}

	public void setTaxFee(Integer taxFee) {
		this.taxFee = taxFee;
	}

	public Integer getTravelFee() {
		return travelFee;
	}

	public void setTravelFee(Integer travelFee) {
		this.travelFee = travelFee;
	}

	public Integer getEntertainment() {
		return entertainment;
	}

	public void setEntertainment(Integer entertainment) {
		this.entertainment = entertainment;
	}

	public String getOther2() {
		return other2;
	}

	public void setOther2(String other2) {
		this.other2 = other2;
	}

	public String getOther3() {
		return other3;
	}

	public void setOther3(String other3) {
		this.other3 = other3;
	}

	public String getOther4() {
		return other4;
	}

	public void setOther4(String other4) {
		this.other4 = other4;
	}

	public String getAge() {
		return age;
	}

	public void setAge(String age) {
		this.age = age;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public Integer getHavetopay() {
		return havetopay;
	}

	public void setHavetopay(Integer havetopay) {
		this.havetopay = havetopay;
	}

	public Integer getPlanCost() {
		return planCost;
	}

	public void setPlanCost(Integer planCost) {
		this.planCost = planCost;
	}

	public Date getSignDate() {
		return signDate;
	}

	public void setSignDate(Date signDate) {
		this.signDate = signDate;
	}

	public Integer getPrice() {
		return price;
	}

	public void setPrice(Integer price) {
		this.price = price;
	}

	public Date getPayDate() {
		return payDate;
	}

	public void setPayDate(Date payDate) {
		this.payDate = payDate;
	}

}
