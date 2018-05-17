package cn.itproject.crm.bean;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.Type;

/**
 * 签单放款的失败消息(退单退件消息)
 * @author MrLiu
 *
 */
@Entity
public class FailureMessage implements Serializable{
	private static final long serialVersionUID = -5557216918793789796L;
	public static final Integer REJECT = 0;			//退件
	public static final Integer CHARGEBACK = 1;		//退单
	public static final Integer signFollow = 2;		//再次跟进
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@Column
	@Temporal(TemporalType.TIMESTAMP)
	private Date time;								//时间
	@Column
	@Lob
	@Type(type="text")
	private String cause;							//原因
	@Column
	@Lob
	@Type(type="text")
	private String remark;							//备注
	@Column
	private Integer type;							//类型(退单/退件)
	
	@ManyToOne
	@JoinColumn(name="signCustomerId")
	private SignCustomer signCustomer;				//签单客户

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

	public String getCause() {
		return cause;
	}

	public void setCause(String cause) {
		this.cause = cause;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public SignCustomer getSignCustomer() {
		return signCustomer;
	}

	public void setSignCustomer(SignCustomer signCustomer) {
		this.signCustomer = signCustomer;
	}

	@Override
	public String toString() {
		return "SignFailureMessage [id=" + id + ", time=" + time + ", cause="
				+ cause + ", remark=" + remark + ", type=" + type + "]";
	}
}
