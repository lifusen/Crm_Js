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
 * 放款客户信息
 * @author MrLiu
 *
 */
@Entity
public class OutLoanCustomer implements Serializable{
	private static final long serialVersionUID = -1007558394889137084L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@Column
	private String name;							//客户姓名
	@Column
	private Double loanAmount;						//贷款金额  --放款金额
	@Column
	@Temporal(value = TemporalType.DATE)
	private Date handleTime;						//办理时间 --放款时间
	@Column
	private String handlePeriod;					//办理周期
	@Column
	private String hasCertificate;					//是否压证件
	@Column
	private String hasFullDelegate;					//是否办全委托
	@Column
	private Integer warrantId;						//权证人员ID
	@Column
	private String warrantName;						//权证专员名称
	@Column
	private String serveRating;						//服务评级
	@Column
	private Double realPrice ;						//实收费用
	@Column
	private Double receivedDeposit ;				//已收定金
	@Column
	private Double cost;							//后台成本
	@Column
	private Double netEarnings;						//净业绩
	@OneToMany(cascade=CascadeType.ALL)
	@JoinColumn(name="outLoanCustomerId")
	private List<OutLoanProduct> loanProducts;		//多个贷款产品
	@Column
	private Double paymentAmount;					//还款金额
	@Temporal(TemporalType.DATE)
	private Date paymenttime;						//还款时间
	@Column
	private String repaymentType;					//还款方式
	
	@Column
	private String loanType;						//放款类型
	
	@Column
	@Lob
	@Type(type="text")
	private String remark;							//其它备注
	
	/*---------------------------------权证部门测试新增项目start--2016.12.30 by SwordLiu----*/
	@Column
	private String outLoanNum;						//放款编号
	@Column
	private Date approveTime;						//审过时间
	@Column
	private String purpose;							//用途
	@Column
	private Double visitCharge;						//上门费
	@Column
	private Double otherCost;						//其他费
	@Column
	private Double rebate;							//返点
	@Column
	private Double channelCost;						//渠道成本
	@Column
	private Double creditCost;						//征信费
	@Column
	private Double electricCost;					//电票
	@Column
	private Double workProve;						//工作证明
	@Column
	private Double postage;							//快递费
	@Column
	private Double checkCost;						//面签费
	@Column
	private Double telephoneCost;					//座机费
	@Column
	private Double phoneCard;						//电话卡
	@Column
	private Double onlyHandle;						//纯处理
	@Column
	private Double houseAge;						//房龄
	@Column
	private String assessCompany;					//评估公司
	@Column
	private Double assessCost;						//评估费用
	@Column
	private Double assessMoney;						//评估金额
	@Column
	private Double pledgeCost;						//抵押费用
	@Column
	private Double contractNotarizationCharge;		//合同公证费
	@Column
	private Double aloneNotarizationCharge;			//单身公证费
	@Column
	private Double entrustNotarizationCharge;		//委托公证费
	@Column
	private Double insuranceCost;					//保险费
	
	/*----------------------------------权证部门测试新增项目end-------------------------------*/
	
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
	
	@ManyToOne
	//@JoinColumn(name="signCustomerId",unique=true)
	@JoinColumn(name="signCustomerId")
	private SignCustomer signCustomer;			//签单客户
	
	@Column
	@Temporal(TemporalType.DATE)
	private Date createDate = new Date();		//添加日期(年月日)
	
	@Column
	@Temporal(TemporalType.TIMESTAMP)
	private Date createTime = new Date();		//添加时间(年月日时分秒)
	
	@Column
	private Integer companyId;				//公司ID
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Double getLoanAmount() {
		return loanAmount;
	}
	public void setLoanAmount(Double loanAmount) {
		this.loanAmount = loanAmount;
	}
	public Date getHandleTime() {
		return handleTime;
	}
	public void setHandleTime(Date handleTime) {
		this.handleTime = handleTime;
	}
	public String getHandlePeriod() {
		return handlePeriod;
	}
	public void setHandlePeriod(String handlePeriod) {
		this.handlePeriod = handlePeriod;
	}
	public String getServeRating() {
		return serveRating;
	}
	public void setServeRating(String serveRating) {
		this.serveRating = serveRating;
	}
	public Double getRealPrice() {
		return realPrice;
	}
	public void setRealPrice(Double realPrice) {
		this.realPrice = realPrice;
	}
	public Double getCost() {
		return cost;
	}
	public void setCost(Double cost) {
		this.cost = cost;
	}
	public Double getNetEarnings() {
		return netEarnings;
	}
	public void setNetEarnings(Double netEarnings) {
		this.netEarnings = netEarnings;
	}
	public List<OutLoanProduct> getLoanProducts() {
		return loanProducts;
	}
	public void setLoanProducts(List<OutLoanProduct> loanProducts) {
		this.loanProducts = loanProducts;
	}
	public Double getPaymentAmount() {
		return paymentAmount;
	}
	public void setPaymentAmount(Double paymentAmount) {
		this.paymentAmount = paymentAmount;
	}
	public Date getPaymenttime() {
		return paymenttime;
	}
	public void setPaymenttime(Date paymenttime) {
		this.paymenttime = paymenttime;
	}
	public Double getReceivedDeposit() {
		return receivedDeposit;
	}
	public void setReceivedDeposit(Double receivedDeposit) {
		this.receivedDeposit = receivedDeposit;
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
	public SignCustomer getSignCustomer() {
		return signCustomer;
	}
	public void setSignCustomer(SignCustomer signCustomer) {
		this.signCustomer = signCustomer;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getHasCertificate() {
		return hasCertificate;
	}
	public void setHasCertificate(String hasCertificate) {
		this.hasCertificate = hasCertificate;
	}
	public String getHasFullDelegate() {
		return hasFullDelegate;
	}
	public void setHasFullDelegate(String hasFullDelegate) {
		this.hasFullDelegate = hasFullDelegate;
	}
	public Integer getWarrantId() {
		return warrantId;
	}
	public void setWarrantId(Integer warrantId) {
		this.warrantId = warrantId;
	}
	public String getWarrantName() {
		return warrantName;
	}
	public void setWarrantName(String warrantName) {
		this.warrantName = warrantName;
	}
	public String getRepaymentType() {
		return repaymentType;
	}
	public void setRepaymentType(String repaymentType) {
		this.repaymentType = repaymentType;
	}
	public String getCertificateString() {
		return certificateString;
	}
	public void setCertificateString(String certificateString) {
		this.certificateString = certificateString;
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
	public String getCollateralString() {
		return collateralString;
	}
	public void setCollateralString(String collateralString) {
		this.collateralString = collateralString;
	}
	public List<String> getCollaterals() {
		return collaterals;
	}
	public void setCollaterals(List<String> collaterals) {
		this.collaterals = collaterals;
	}
	public List<CollateralViewBean> getCollateralViewBeans() {
		return collateralViewBeans;
	}
	public void setCollateralViewBeans(List<CollateralViewBean> collateralViewBeans) {
		this.collateralViewBeans = collateralViewBeans;
	}
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
	public Integer getCompanyId() {
		return companyId;
	}
	public void setCompanyId(Integer companyId) {
		this.companyId = companyId;
	}
	public String getOutLoanNum() {
		return outLoanNum;
	}
	public void setOutLoanNum(String outLoanNum) {
		this.outLoanNum = outLoanNum;
	}
	public Date getApproveTime() {
		return approveTime;
	}
	public void setApproveTime(Date approveTime) {
		this.approveTime = approveTime;
	}
	public String getAssessCompany() {
		return assessCompany;
	}
	public void setAssessCompany(String assessCompany) {
		this.assessCompany = assessCompany;
	}
	public Double getAssessCost() {
		return assessCost;
	}
	public void setAssessCost(Double assessCost) {
		this.assessCost = assessCost;
	}
	public Double getAssessMoney() {
		return assessMoney;
	}
	public void setAssessMoney(Double assessMoney) {
		this.assessMoney = assessMoney;
	}
	public Double getPledgeCost() {
		return pledgeCost;
	}
	public void setPledgeCost(Double pledgeCost) {
		this.pledgeCost = pledgeCost;
	}
	public Double getContractNotarizationCharge() {
		return contractNotarizationCharge;
	}
	public void setContractNotarizationCharge(Double contractNotarizationCharge) {
		this.contractNotarizationCharge = contractNotarizationCharge;
	}
	public Double getAloneNotarizationCharge() {
		return aloneNotarizationCharge;
	}
	public void setAloneNotarizationCharge(Double aloneNotarizationCharge) {
		this.aloneNotarizationCharge = aloneNotarizationCharge;
	}
	public Double getEntrustNotarizationCharge() {
		return entrustNotarizationCharge;
	}
	public void setEntrustNotarizationCharge(Double entrustNotarizationCharge) {
		this.entrustNotarizationCharge = entrustNotarizationCharge;
	}
	
	public String getPurpose() {
		return purpose;
	}
	public void setPurpose(String purpose) {
		this.purpose = purpose;
	}
	public Double getVisitCharge() {
		return visitCharge;
	}
	public void setVisitCharge(Double visitCharge) {
		this.visitCharge = visitCharge;
	}
	public Double getPostage() {
		return postage;
	}
	public void setPostage(Double postage) {
		this.postage = postage;
	}
	public Double getOtherCost() {
		return otherCost;
	}
	public void setOtherCost(Double otherCost) {
		this.otherCost = otherCost;
	}
	public Double getRebate() {
		return rebate;
	}
	public void setRebate(Double rebate) {
		this.rebate = rebate;
	}
	public Double getChannelCost() {
		return channelCost;
	}
	public void setChannelCost(Double channelCost) {
		this.channelCost = channelCost;
	}
	public Double getCreditCost() {
		return creditCost;
	}
	public void setCreditCost(Double creditCost) {
		this.creditCost = creditCost;
	}
	public Double getElectricCost() {
		return electricCost;
	}
	public void setElectricCost(Double electricCost) {
		this.electricCost = electricCost;
	}
	public Double getWorkProve() {
		return workProve;
	}
	public void setWorkProve(Double workProve) {
		this.workProve = workProve;
	}
	public Double getCheckCost() {
		return checkCost;
	}
	public void setCheckCost(Double checkCost) {
		this.checkCost = checkCost;
	}
	public Double getTelephoneCost() {
		return telephoneCost;
	}
	public void setTelephoneCost(Double telephoneCost) {
		this.telephoneCost = telephoneCost;
	}
	public Double getPhoneCard() {
		return phoneCard;
	}
	public void setPhoneCard(Double phoneCard) {
		this.phoneCard = phoneCard;
	}
	public Double getOnlyHandle() {
		return onlyHandle;
	}
	public void setOnlyHandle(Double onlyHandle) {
		this.onlyHandle = onlyHandle;
	}
	public Double getHouseAge() {
		return houseAge;
	}
	public void setHouseAge(Double houseAge) {
		this.houseAge = houseAge;
	}
	public Double getInsuranceCost() {
		return insuranceCost;
	}
	public void setInsuranceCost(Double insuranceCost) {
		this.insuranceCost = insuranceCost;
	}
	@Override
	public String toString() {
		return "OutLoanCustomer [id=" + id + ", name=" + name + ", loanAmount="
				+ loanAmount + ", handleTime=" + handleTime + ", handlePeriod="
				+ handlePeriod + ", hasCertificate=" + hasCertificate
				+ ", hasFullDelegate=" + hasFullDelegate + ", warrantId="
				+ warrantId + ", warrantName=" + warrantName + ", serveRating="
				+ serveRating + ", realPrice=" + realPrice
				+ ", receivedDeposit=" + receivedDeposit + ", cost=" + cost
				+ ", netEarnings=" + netEarnings + ", loanProducts="
				+ loanProducts + ", paymentAmount=" + paymentAmount
				+ ", paymenttime=" + paymenttime + ", repaymentType="
				+ repaymentType + ", remark=" + remark + ", certificateString="
				+ certificateString + ", certificates=" + certificates
				+ ", certificateViewBeans=" + certificateViewBeans
				+ ", collateralString=" + collateralString + ", collaterals="
				+ collaterals + ", collateralViewBeans=" + collateralViewBeans
				+ ", hasLoaning=" + hasLoaning + ", loaningAmount="
				+ loaningAmount + ", loaningRate=" + loaningRate
				+ ", loaningDate=" + loaningDate + ", loaningCycle="
				+ loaningCycle + ", loaningType=" + loaningType
				+ ", loaningRisk=" + loaningRisk 
				+ ", outLoanNum=" + outLoanNum + ", approveTime=" + approveTime
				+ ", assessCompany=" + assessCompany + ", assessCost=" + assessCost 
				+ ", assessMoney=" + assessMoney + ", pledgeCost=" + pledgeCost
				+ ", contractNotarizationCharge=" + contractNotarizationCharge 
				+ ", aloneNotarizationCharge=" + aloneNotarizationCharge
				+ ", entrustNotarizationCharge=" + entrustNotarizationCharge + "]";
	}
}
