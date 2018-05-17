package cn.itproject.crm.bean;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 * 垫资信息
 * @author MrLiu
 *
 */
@Deprecated
//@Entity
public class Loaning implements Serializable{
	private static final long serialVersionUID = -5605264655055882044L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@Column
	private String amount;					//垫资金额
	@Column
	private String rate;					//垫资费率
	@Column
	private Date date;						//垫资时间
	@Column
	private String cycle;					//周期
	@Column
	private String type;					//是否在我公司做单
	@Column
	private String risk;					//预估风险
	@ManyToOne
	@JoinColumn(name="signCustomerId")
	private SignCustomer signCustomer;		//签单客户
	public Loaning() {
		super();
	}
	public Loaning(Integer id, String amount, String rate, Date date,
			String cycle, String type, String risk) {
		super();
		this.id = id;
		this.amount = amount;
		this.rate = rate;
		this.date = date;
		this.cycle = cycle;
		this.type = type;
		this.risk = risk;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getAmount() {
		return amount;
	}
	public void setAmount(String amount) {
		this.amount = amount;
	}
	public String getRate() {
		return rate;
	}
	public void setRate(String rate) {
		this.rate = rate;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public String getCycle() {
		return cycle;
	}
	public void setCycle(String cycle) {
		this.cycle = cycle;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getRisk() {
		return risk;
	}
	public void setRisk(String risk) {
		this.risk = risk;
	}
	public SignCustomer getSignCustomer() {
		return signCustomer;
	}
	public void setSignCustomer(SignCustomer signCustomer) {
		this.signCustomer = signCustomer;
	}
	@Override
	public String toString() {
		return "Loaning [id=" + id + ", amount=" + amount + ", rate=" + rate
				+ ", date=" + date + ", cycle=" + cycle + ", type=" + type
				+ ", risk=" + risk + "]";
	}
}
