package cn.itproject.crm.controller.viewbean;

import java.io.Serializable;

public class ChargeData implements Serializable{
	private static final long serialVersionUID = 8065102449139238765L;
	private Integer id;
	private String name;
	private Double houseLoan=0.0;	// 房贷
	private Double twoLoan=0.0;		// 二抵
	private Double creditLoan=0.0;	// 信贷
	private Double creditCard=0.0;	// 信用卡
	private Double carLoan=0.0;		// 车贷
	private Double corpLoan=0.0;	// 企贷
	private Double briefLoan=0.0;	// 短借
	
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
	public Double getHouseLoan() {
		return houseLoan;
	}
	public void setHouseLoan(Double houseLoan) {
		this.houseLoan = houseLoan;
	}
	public Double getTwoLoan() {
		return twoLoan;
	}
	public void setTwoLoan(Double twoLoan) {
		this.twoLoan = twoLoan;
	}
	public Double getCreditLoan() {
		return creditLoan;
	}
	public void setCreditLoan(Double creditLoan) {
		this.creditLoan = creditLoan;
	}
	public Double getCreditCard() {
		return creditCard;
	}
	public void setCreditCard(Double creditCard) {
		this.creditCard = creditCard;
	}
	public Double getCarLoan() {
		return carLoan;
	}
	public void setCarLoan(Double carLoan) {
		this.carLoan = carLoan;
	}
	public Double getCorpLoan() {
		return corpLoan;
	}
	public void setCorpLoan(Double corpLoan) {
		this.corpLoan = corpLoan;
	}
	public Double getBriefLoan() {
		return briefLoan;
	}
	public void setBriefLoan(Double briefLoan) {
		this.briefLoan = briefLoan;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ChargeData other = (ChargeData) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	public ChargeData(){}
	public ChargeData(Integer id) {
		this.id = id;
	}
	
}