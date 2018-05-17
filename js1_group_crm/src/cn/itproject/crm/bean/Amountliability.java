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
 * 
 * @author yangpeixin
 * 
 * @Date 2017年7月4日
 *
 * version 1.0
 */
@Entity
public class Amountliability implements Serializable{
	private static final long serialVersionUID = 3610769888013390712L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@Column
	private Integer creditor;				// 债权构造
	@Column
	private Double debtMoney;				// 负债金额
	@ManyToOne
	@JoinColumn(name = "customerId")
	private Customer customer;

	public Amountliability() {
	}
	public Amountliability(Integer creditor, Double debtMoney) {
		this.creditor = creditor;
		this.debtMoney = debtMoney;
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
	public Double getDebtMoney() {
		return debtMoney;
	}
	public void setDebtMoney(Double debtMoney) {
		this.debtMoney = debtMoney;
	}
	public Customer getCustomer() {
		return customer;
	}
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	public Integer getCreditor() {
		return creditor;
	}
	public void setCreditor(Integer creditor) {
		this.creditor = creditor;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((creditor == null) ? 0 : creditor.hashCode());
		result = prime * result
				+ ((debtMoney == null) ? 0 : debtMoney.hashCode());
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
		Amountliability other = (Amountliability) obj;
		if (creditor == null) {
			if (other.creditor != null)
				return false;
		} else if (!creditor.equals(other.creditor))
			return false;
		if (debtMoney == null) {
			if (other.debtMoney != null)
				return false;
		} else if (!debtMoney.equals(other.debtMoney))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "Amountliability [id=" + id + ", creditor=" + creditor
				+ ", debtMoney=" + debtMoney + "]";
	}
	
}
