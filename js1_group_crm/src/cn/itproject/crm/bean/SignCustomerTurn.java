package cn.itproject.crm.bean;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name="signcustomerturn")
public class SignCustomerTurn implements Serializable{
	private static final long serialVersionUID = 8699830857919057278L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern="yyyy-mm-dd")
	private Date createDate;
	@Column
	private String cause;
	@Column
	private String otherCause;
	@ManyToOne
	@JoinColumn(name="oldEmpId")
	private Employee oldEmp;
	@ManyToOne
	@JoinColumn(name="nowEmpId")
	private Employee nowEmp;
	@ManyToOne
	@JoinColumn(name="signCustomerId")
	private SignCustomer signCustomer;
	@Column
	private Integer companyId;				//公司ID
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public String getCause() {
		return cause;
	}
	public void setCause(String cause) {
		this.cause = cause;
	}
	public String getOtherCause() {
		return otherCause;
	}
	public void setOtherCause(String otherCause) {
		this.otherCause = otherCause;
	}
	public Employee getOldEmp() {
		return oldEmp;
	}
	public void setOldEmp(Employee oldEmp) {
		this.oldEmp = oldEmp;
	}
	public Employee getNowEmp() {
		return nowEmp;
	}
	public void setNowEmp(Employee nowEmp) {
		this.nowEmp = nowEmp;
	}
	public SignCustomer getCustomer() {
		return signCustomer;
	}
	public void setCustomer(SignCustomer signCustomer) {
		this.signCustomer = signCustomer;
	}
	public SignCustomerTurn(){}
	public SignCustomerTurn(Date createDate, String cause,
			String otherCause, Employee oldEmp, Employee nowEmp,
			SignCustomer signCustomer) {
		this.createDate = createDate;
		this.cause = cause;
		this.otherCause = otherCause;
		this.oldEmp = oldEmp;
		this.nowEmp = nowEmp;
		this.signCustomer = signCustomer;
	}
	public Integer getCompanyId() {
		return companyId;
	}
	public void setCompanyId(Integer companyId) {
		this.companyId = companyId;
	}
}
