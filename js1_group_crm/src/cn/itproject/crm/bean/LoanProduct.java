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
 * 签单贷款产品
 * @author MrLiu
 *
 */
@Entity
public class LoanProduct implements Serializable{
	private static final long serialVersionUID = 5374651013374152857L;
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
	private String loanYear;							//贷款年限
	@ManyToOne
	@JoinColumn(name="signCustomerId")
	private SignCustomer signCustomer;					//签单客户
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
	public SignCustomer getSignCustomer() {
		return signCustomer;
	}
	public void setSignCustomer(SignCustomer signCustomer) {
		this.signCustomer = signCustomer;
	}
	@Override
	public String toString() {
		return "LoanProduct [id=" + id + ", loanType=" + loanType
				+ ", lendingInstitution=" + lendingInstitution
				+ ", bankProduct=" + bankProduct + ", apr=" + apr
				+ ", loanYear=" + loanYear + "]";
	}
}
