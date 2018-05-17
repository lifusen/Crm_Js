package cn.itproject.crm.bean;

import java.io.Serializable;
import java.text.SimpleDateFormat;
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
 * 信用卡
 * @author MrLiu
 *
 */
@Entity
public class CreditCard implements Serializable{
	private static final long serialVersionUID = -1420703638629807747L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@Column
	private String bank;					// 发卡银行
	@Column
	private String creditLimit;				// 信用额度
	@Column
	private String overdueCase;				// 逾期情况
	@Column
	@Temporal(TemporalType.DATE)
	private Date publishCardDate;			// 发卡日期
	@Column
	private String cardNumber;				// 卡号
	@ManyToOne
	@JoinColumn(name = "customerId")
	private Customer customer;
	public CreditCard() {
		super();
	}
	public CreditCard(String bank, String creditLimit, String overdueCase,
			Date publishCardDate, String cardNumber) {
		super();
		this.bank = bank;
		this.creditLimit = creditLimit;
		this.overdueCase = overdueCase;
		this.publishCardDate = publishCardDate;
		this.cardNumber = cardNumber;
	}
	public CreditCard(Integer id, String bank, String creditLimit,
			String overdueCase, Date publishCardDate, String cardNumber) {
		super();
		this.id = id;
		this.bank = bank;
		this.creditLimit = creditLimit;
		this.overdueCase = overdueCase;
		this.publishCardDate = publishCardDate;
		this.cardNumber = cardNumber;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getBank() {
		return bank;
	}
	public void setBank(String bank) {
		this.bank = bank;
	}
	public String getCreditLimit() {
		return creditLimit;
	}
	public void setCreditLimit(String creditLimit) {
		this.creditLimit = creditLimit;
	}
	public String getOverdueCase() {
		return overdueCase;
	}
	public void setOverdueCase(String overdueCase) {
		this.overdueCase = overdueCase;
	}
	public Date getPublishCardDate() {
		return publishCardDate;
	}
	public void setPublishCardDate(Date publishCardDate) {
		this.publishCardDate = publishCardDate;
	}
	public String getCardNumber() {
		return cardNumber;
	}
	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}
	public Customer getCustomer() {
		return customer;
	}
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((bank == null) ? 0 : bank.hashCode());
		result = prime * result
				+ ((cardNumber == null) ? 0 : cardNumber.hashCode());
		result = prime * result
				+ ((creditLimit == null) ? 0 : creditLimit.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result
				+ ((overdueCase == null) ? 0 : overdueCase.hashCode());
		result = prime * result
				+ ((publishCardDate == null) ? 0 : publishCardDate.hashCode());
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
		CreditCard other = (CreditCard) obj;
		if (bank == null) {
			if (other.bank != null)
				return false;
		} else if (!bank.equals(other.bank))
			return false;
		if (cardNumber == null) {
			if (other.cardNumber != null)
				return false;
		} else if (!cardNumber.equals(other.cardNumber))
			return false;
		if (creditLimit == null) {
			if (other.creditLimit != null)
				return false;
		} else if (!creditLimit.equals(other.creditLimit))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (overdueCase == null) {
			if (other.overdueCase != null)
				return false;
		} else if (!overdueCase.equals(other.overdueCase))
			return false;
		if (publishCardDate == null) {
			if (other.publishCardDate != null)
				return false;
		} else if (!publishCardDate.equals(other.publishCardDate))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "CreditCard [id=" + id + ", bank=" + bank + ", creditLimit="
				+ creditLimit + ", overdueCase=" + overdueCase
				+ ", publishCardDate=" + publishCardDate + ", cardNumber="
				+ cardNumber + "]";
	}

	private String dateFormat(Date date){
		if (date==null) {
			return "";
		}
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		return format.format(date);
	}
	
	public String toTagString() {
		return  formatNull("发卡银行", bank)+
				formatNull("信用额度", creditLimit)+
				formatNull("逾期请款", overdueCase)+
				formatNull("发卡日期", dateFormat(publishCardDate))+
				formatNull("卡号", cardNumber);
	}
	private String formatNull(String name,String value){
		if (value==null||value.trim().equals("")) {
			return "";
		}
		return name+":"+value+" ";
	}
}
