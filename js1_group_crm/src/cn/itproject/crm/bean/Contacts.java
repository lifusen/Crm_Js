package cn.itproject.crm.bean;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * 联系方式
 * @author ham
 *
 */
@Entity
@Table(name="Contacts")
public class Contacts implements Serializable{
	private static final long serialVersionUID = 1034996499703225543L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column
	private String phone;	// 电话号码
		
	@Column
	private String remark;	// 备注
	
	@ManyToOne
	@JoinColumn(name="customerId")
	private Customer customer;

	public Contacts() {
		super();
	}

	public Contacts(String phone, String remark) {
	super();
	this.phone = phone;
	this.remark = remark;
}

	public Contacts(Integer id, String phone, String remark, Customer customer) {
		super();
		this.id = id;
		this.phone = phone;
		this.remark = remark;
		this.customer = customer;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public Contacts(Integer id, String phone, String remark) {
		super();
		this.id = id;
		this.phone = phone;
		this.remark = remark;
	}

	@Override
	public String toString() {
		return "Contacts [id=" + id + ", phone=" + phone + ", remark=" + remark
				+ "]";
	}
	
}
