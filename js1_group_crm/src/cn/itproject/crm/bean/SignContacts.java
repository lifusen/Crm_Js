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
 * 签单联系人
 * @author MrLiu
 *
 */
@Entity
public class SignContacts implements Serializable{
	private static final long serialVersionUID = 2439104888724056734L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@Column
	private String name;									//客户姓名
	@Column
	private String idCardNo;								//身份证号码
	@Column
	private String remark;									//备注
	@Column
	private Integer age;									//年龄
	@ManyToOne
	@JoinColumn(name="signCustomerId")
	private SignCustomer signCustomer;
	public SignContacts() {
		super();
	}
	public SignContacts(String name, String idCardNo, String remark,
			Integer age, SignCustomer signCustomer) {
		super();
		this.name = name;
		this.idCardNo = idCardNo;
		this.remark = remark;
		this.age = age;
		this.signCustomer = signCustomer;
	}
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
	public String getIdCardNo() {
		return idCardNo;
	}
	public void setIdCardNo(String idCardNo) {
		this.idCardNo = idCardNo;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public Integer getAge() {
		return age;
	}
	public void setAge(Integer age) {
		this.age = age;
	}
	public SignCustomer getSignCustomer() {
		return signCustomer;
	}
	public void setSignCustomer(SignCustomer signCustomer) {
		this.signCustomer = signCustomer;
	}
	@Override
	public String toString() {
		return "SignContacts [id=" + id + ", name=" + name + ", idCardNo="
				+ idCardNo + ", remark=" + remark + ", age=" + age + "]";
	}
}
