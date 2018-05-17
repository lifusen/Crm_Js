package cn.itproject.crm.bean;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;


/**
 * 放款产品
 * @author MrLiu
 *
 */
@Entity
public class OutLoanProduct implements Serializable{
	private static final long serialVersionUID = -5445557000100494361L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@Column
	private String loanType;							//贷款类型
	@Column
	private Integer lendingInstitution;					//贷款机构
	@Column
	private String bankProduct;							//银行产品
	@Column
	private String apr;									//月利率
	@Column
	private String rateType;							//利率类型 	2016.12.30 by SwordLiu 新增
	@Column
	private String loanYear;							//贷款年限
	@ManyToOne
	@JoinColumn(name="outLoanCustomerId")
	private OutLoanCustomer outLoanCustomer;			//放款客户
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getLoanType() {
		return loanType;
	}
	public void setLoanType(String loanType) {
		this.loanType = loanType;
	}
	public Integer getLendingInstitution() {
		return lendingInstitution;
	}
	public void setLendingInstitution(Integer lendingInstitution) {
		this.lendingInstitution = lendingInstitution;
	}
	public String getBankProduct() {
		return bankProduct;
	}
	public void setBankProduct(String bankProduct) {
		this.bankProduct = bankProduct;
	}
	public String getApr() {
		return apr;
	}
	public void setApr(String apr) {
		this.apr = apr;
	}
	public String getLoanYear() {
		return loanYear;
	}
	public void setLoanYear(String loanYear) {
		this.loanYear = loanYear;
	}
	public OutLoanCustomer getOutLoanCustomer() {
		return outLoanCustomer;
	}
	public void setOutLoanCustomer(OutLoanCustomer outLoanCustomer) {
		this.outLoanCustomer = outLoanCustomer;
	}
	@Override
	public String toString() {
		return "OutLoanProduct [id=" + id + ", loanType=" + loanType
				+ ", lendingInstitution=" + lendingInstitution
				+ ", bankProduct=" + bankProduct + ", apr=" + apr
				+ ", loanYear=" + loanYear + ", rateType=" + rateType + "]";
	}
	public String getRateType() {
		return rateType;
	}
	public void setRateType(String rateType) {
		this.rateType = rateType;
	}
}
