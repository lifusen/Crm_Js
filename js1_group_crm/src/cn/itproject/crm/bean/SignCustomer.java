package cn.itproject.crm.bean;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.hibernate.annotations.Type;

import cn.itproject.crm.controller.viewbean.CertificateViewBean;
import cn.itproject.crm.controller.viewbean.CollateralViewBean;
/**
 * 
 * @author yangpeixin
 * 
 * @Date 2017年7月3日
 *
 * version 1.0
 */
@Entity
public class SignCustomer implements Serializable{
	private static final long serialVersionUID = -5887929641571959788L;
	//客户状态
	public enum SignCustomerStatus{
		underway,			//进行中
		bankChargeback,		//银行退单
		warrantReject,		//权证退件
		outLoan				//客户放款
//		Loaning,			//公司垫资
	}
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@Column
	private String contractNO;						//合同编号
	
	@OneToMany(cascade=CascadeType.ALL)
	@JoinColumn(name="signCustomerId")
	private List<SignContacts> signContacts;		//签单联系人
	
	@Column
	private Integer customerStatus=0;				//客户状态,默认跟进中
	@Column
	private String outLoanCondition;				//放款情况
	@Column
	private String hasLoanWish;						//是否有再贷意愿
	@Column
	@Temporal(TemporalType.DATE)
	private Date planNextLoanTime;					//预计下次贷款时间
	@Column
	private Double costOfContract;					//合同费用
	@Column
	private Double loanAmount;						//贷款金额
	@Column
	private Double handsel;							//已收定金
	@Column
	private Integer negotiatorId;					//谈判师(员工)
	@Column
	private String receivedCertificate;				//已收客户证件
	
	@OneToMany(cascade=CascadeType.ALL)
	@JoinColumn(name="signCustomerId")
	private List<LoanProduct> loanProducts;			//多个贷款产品
	
	@Column
	private String loanType;						//记录第一个贷款产品的贷款类型
	@Column
	@Lob
	@Type(type="text")
	private String remark;							//其它备注

	//------------------------------------- 客户证件 start -------------------------------------
	@Column(length=5000)
	private String certificateString;							//证件JSON字符串
	@Transient
	private List<String> certificates;							//多个客户证件
	@Transient
	private List<CertificateViewBean> certificateViewBeans;		//客户证件的ViewBean集合
	//------------------------------------- 客户证件 end ---------------------------------------

	//------------------------------------- 抵押物 start ---------------------------------------
	@Column(length=5000)
	private String collateralString;							//抵押物JSON字符串
	@Transient
	private List<String> collaterals;							//多个抵押物字符串
	@Transient
	private List<CollateralViewBean> collateralViewBeans;		//多个抵押物ViewBean集合
	//------------------------------------- 抵押物 end -----------------------------------------
	
	//------------------------------------- 垫资 start -----------------------------------------
	@Column
	private String hasLoaning;					//是否垫资
	@Column
	private String loaningAmount;				//垫资金额
	@Column
	private String loaningRate;					//垫资费率
	@Column
	private Date loaningDate;					//垫资时间
	@Column
	private String loaningCycle;				//周期
	@Column
	private String loaningType;					//是否在我公司做单
	@Column
	private String loaningRisk;					//预估风险
	//------------------------------------- 垫资 end -------------------------------------------
	
	//------------------------------------- 退单退件消息 start -------------------------------------------
	@Transient
	private FailureMessage failureMessage; 				//退单退件消息
	@Transient
	private List<FailureMessage> failureMessages;		//退单退件消息
		//------------------------记录最后一次退单退件的信息 start ------------------------
	@Column
	@Temporal(TemporalType.DATE)
	private Date lastFailureMessageTime;			//最后一次退单退件的时间
	@Column
	private String lastFailureMessageCause;			//最后一次退单退件的原因			
	@Column
	private Integer lastFailureMessageType;			//最后一次退单退件的类型
	@Column
	private String lastFailureMessageRemark;		//最后一次退单退件的 备注
		//------------------------记录最后一次退单退件的信息 end ------------------------
	//------------------------------------- 退单退件消息 end -------------------------------------------
	
	@ManyToOne
	@JoinColumn(name = "customerId")
	private Customer customer;						//对应的客户
	
	@Column
	@Temporal(TemporalType.DATE)
	private Date signDate = new Date();				//签单日期

	@Column
	@Temporal(TemporalType.TIMESTAMP)
	private Date signTime = new Date();				//签单时间
	
	@Column
	private Date updateTime = new Date();			//修改时间
	
	@Column
	private String repaymentType;					//还款方式
	
	//@Transient
	//private OutLoanCustomer outLoanCustomer;		//显示放款客户
	
	@Column
	private String serveRating;						//服务评级
	
	@Column
	private Integer companyId;				//公司ID
	@Column
	private Integer warrantCompanyId;		//权证中心ID
	@Column
	private Integer warrantDepartmentId;	//权证部门ID
	@Column
	private Integer warrantEmployeeId;		//权证专员ID
	@Column
	@Temporal(TemporalType.TIMESTAMP)
	private Date turnOverWarrantTime;		//移交给权证的时间
	@Column
	private Integer isNewOrder;				//是否是新订单(针对权证专员),非空,0:否/1:是
	@Column
	private Integer orderType;				//签单类型(0新增,1退单退件,2返单,3放款)
	@Column
	private Integer listType;				// 1中心列表、2部门列表、3专员列表
	
	//------------------------------------- 蜀创车贷中心字段 start -------------------------------------------
	@Column
	private String intermediaryName;		//中介名称
	@Column
	private String intermediaryPhone;		//中介电话
	@Column
	private String intermediaryRebate;		//中介返点
	//------------------------------------- 蜀创车贷中心字段 end -------------------------------------------
	
	
	public SignCustomer(Integer id) {
		this.id = id;
	}

	public SignCustomer() {
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getContractNO() {
		return contractNO;
	}

	public void setContractNO(String contractNO) {
		this.contractNO = contractNO;
	}

	public Integer getCustomerStatus() {
		return customerStatus;
	}

	public void setCustomerStatus(Integer customerStatus) {
		this.customerStatus = customerStatus;
	}

	public String getOutLoanCondition() {
		return outLoanCondition;
	}

	public void setOutLoanCondition(String outLoanCondition) {
		this.outLoanCondition = outLoanCondition;
	}

	public String getHasLoanWish() {
		return hasLoanWish;
	}

	public void setHasLoanWish(String hasLoanWish) {
		this.hasLoanWish = hasLoanWish;
	}

	public Date getPlanNextLoanTime() {
		return planNextLoanTime;
	}

	public void setPlanNextLoanTime(Date planNextLoanTime) {
		this.planNextLoanTime = planNextLoanTime;
	}

	public Double getCostOfContract() {
		return costOfContract;
	}

	public void setCostOfContract(Double costOfContract) {
		this.costOfContract = costOfContract;
	}

	public Double getHandsel() {
		return handsel;
	}

	public void setHandsel(Double handsel) {
		this.handsel = handsel;
	}

	public Integer getNegotiatorId() {
		return negotiatorId;
	}

	public void setNegotiatorId(Integer negotiatorId) {
		this.negotiatorId = negotiatorId;
	}

	public String getReceivedCertificate() {
		return receivedCertificate;
	}

	public void setReceivedCertificate(String receivedCertificate) {
		this.receivedCertificate = receivedCertificate;
	}

	public String getHasLoaning() {
		return hasLoaning;
	}

	public void setHasLoaning(String hasLoaning) {
		this.hasLoaning = hasLoaning;
	}
	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public List<String> getCollaterals() {
		return collaterals;
	}

	public void setCollaterals(List<String> collaterals) {
		this.collaterals = collaterals;
	}

	
	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public List<SignContacts> getSignContacts() {
		return signContacts;
	}

	public void setSignContacts(List<SignContacts> signContacts) {
		this.signContacts = signContacts;
	}

	public List<LoanProduct> getLoanProducts() {
		return loanProducts;
	}

	public void setLoanProducts(List<LoanProduct> loanProducts) {
		this.loanProducts = loanProducts;
	}

	public List<String> getCertificates() {
		return certificates;
	}

	public void setCertificates(List<String> certificates) {
		this.certificates = certificates;
	}

	public List<CertificateViewBean> getCertificateViewBeans() {
		return certificateViewBeans;
	}

	public void setCertificateViewBeans(
			List<CertificateViewBean> certificateViewBeans) {
		this.certificateViewBeans = certificateViewBeans;
	}
	
	public FailureMessage getFailureMessage() {
		return failureMessage;
	}

	public void setFailureMessage(FailureMessage failureMessage) {
		this.failureMessage = failureMessage;
	}

	public Date getSignTime() {
		return signTime;
	}

	public void setSignTime(Date signTime) {
		this.signTime = signTime;
	}

	public List<CollateralViewBean> getCollateralViewBeans() {
		return collateralViewBeans;
	}

	public void setCollateralViewBeans(List<CollateralViewBean> collateralViewBeans) {
		this.collateralViewBeans = collateralViewBeans;
	}

	public String getCollateralString() {
		return collateralString;
	}

	public void setCollateralString(String collateralString) {
		this.collateralString = collateralString;
	}

	public String getCertificateString() {
		return certificateString;
	}

	public void setCertificateString(String certificateString) {
		this.certificateString = certificateString;
	}

	public String getRepaymentType() {
		return repaymentType;
	}

	public void setRepaymentType(String repaymentType) {
		this.repaymentType = repaymentType;
	}

	public List<FailureMessage> getFailureMessages() {
		return failureMessages;
	}

	public void setFailureMessages(List<FailureMessage> failureMessages) {
		this.failureMessages = failureMessages;
	}

	public Date getLastFailureMessageTime() {
		return lastFailureMessageTime;
	}

	public void setLastFailureMessageTime(Date lastFailureMessageTime) {
		this.lastFailureMessageTime = lastFailureMessageTime;
	}

	public String getLastFailureMessageCause() {
		return lastFailureMessageCause;
	}

	public void setLastFailureMessageCause(String lastFailureMessageCause) {
		this.lastFailureMessageCause = lastFailureMessageCause;
	}

	public Integer getLastFailureMessageType() {
		return lastFailureMessageType;
	}

	public void setLastFailureMessageType(Integer lastFailureMessageType) {
		this.lastFailureMessageType = lastFailureMessageType;
	}

	/*public OutLoanCustomer getOutLoanCustomer() {
		return outLoanCustomer;
	}

	public void setOutLoanCustomer(OutLoanCustomer outLoanCustomer) {
		this.outLoanCustomer = outLoanCustomer;
	}*/

	public String getLoaningAmount() {
		return loaningAmount;
	}

	public void setLoaningAmount(String loaningAmount) {
		this.loaningAmount = loaningAmount;
	}

	public String getLoaningRate() {
		return loaningRate;
	}

	public void setLoaningRate(String loaningRate) {
		this.loaningRate = loaningRate;
	}

	public Date getLoaningDate() {
		return loaningDate;
	}

	public void setLoaningDate(Date loaningDate) {
		this.loaningDate = loaningDate;
	}

	public String getLoaningCycle() {
		return loaningCycle;
	}

	public void setLoaningCycle(String loaningCycle) {
		this.loaningCycle = loaningCycle;
	}

	public String getLoaningType() {
		return loaningType;
	}

	public void setLoaningType(String loaningType) {
		this.loaningType = loaningType;
	}

	public String getLoaningRisk() {
		return loaningRisk;
	}

	public void setLoaningRisk(String loaningRisk) {
		this.loaningRisk = loaningRisk;
	}
	public String getLoanType() {
		return loanType;
	}

	public void setLoanType(String loanType) {
		this.loanType = loanType;
	}

	public Double getLoanAmount() {
		return loanAmount;
	}

	public void setLoanAmount(Double loanAmount) {
		this.loanAmount = loanAmount;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public String getServeRating() {
		return serveRating;
	}

	public void setServeRating(String serveRating) {
		this.serveRating = serveRating;
	}

	public String getLastFailureMessageRemark() {
		return lastFailureMessageRemark;
	}

	public void setLastFailureMessageRemark(String lastFailureMessageRemark) {
		this.lastFailureMessageRemark = lastFailureMessageRemark;
	}

	public Date getSignDate() {
		return signDate;
	}

	public void setSignDate(Date signDate) {
		this.signDate = signDate;
	}

	public Integer getCompanyId() {
		return companyId;
	}

	public void setCompanyId(Integer companyId) {
		this.companyId = companyId;
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

	public Date getTurnOverWarrantTime() {
		return turnOverWarrantTime;
	}

	public void setTurnOverWarrantTime(Date turnOverWarrantTime) {
		this.turnOverWarrantTime = turnOverWarrantTime;
	}

	public Integer getIsNewOrder() {
		return isNewOrder;
	}

	public void setIsNewOrder(Integer isNewOrder) {
		this.isNewOrder = isNewOrder;
	}

	public Integer getOrderType() {
		return orderType;
	}

	public void setOrderType(Integer orderType) {
		this.orderType = orderType;
	}

	public Integer getListType() {
		return listType;
	}

	public void setListType(Integer listType) {
		this.listType = listType;
	}

	public String getIntermediaryName() {
		return intermediaryName;
	}

	public void setIntermediaryName(String intermediaryName) {
		this.intermediaryName = intermediaryName;
	}

	public String getIntermediaryPhone() {
		return intermediaryPhone;
	}

	public void setIntermediaryPhone(String intermediaryPhone) {
		this.intermediaryPhone = intermediaryPhone;
	}

	public String getIntermediaryRebate() {
		return intermediaryRebate;
	}

	public void setIntermediaryRebate(String intermediaryRebate) {
		this.intermediaryRebate = intermediaryRebate;
	}

	@Override
	public String toString() {
		return "SignCustomer [id=" + id + ", contractNO=" + contractNO
				+ ", signContacts=" + signContacts + ", customerStatus="
				+ customerStatus + ", outLoanCondition=" + outLoanCondition
				+ ", hasLoanWish=" + hasLoanWish + ", planNextLoanTime="
				+ planNextLoanTime + ", costOfContract=" + costOfContract
				+ ", handsel=" + handsel + ", negotiatorId=" + negotiatorId
				+ ", receivedCertificate=" + receivedCertificate
				+ ", hasLoaning=" + hasLoaning
				+ ", remark=" + remark 
				+ ", \ncollaterals=" + collaterals 
				+ ", \ncollateralString=" + collateralString 
				+ ", \ncollateralViewBeans=" + collateralViewBeans 
				+ ", \nsignTime=" + signTime
				+ ", warrantCompanyId=" +warrantCompanyId
				+ ", warrantDepartmentId=" +warrantDepartmentId
				+ ", warrantEmployeeId=" +warrantEmployeeId+"]";
	}
}
