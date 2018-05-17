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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * 签单放款客户证件
 * @author MrLiu
 *
 */
@Entity
public class Certificate implements Serializable{
	private static final long serialVersionUID = 943547294381867040L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@Column
	private String name;				//证件名称
	@Column
	private String srcfileName;			//源文件名称
	@Column
	private String path;				//保存路径
	@Column
	@Temporal(TemporalType.TIMESTAMP)
	private Date createTime;			//创建时间
	@ManyToOne
	@JoinColumn(name="customerId")
	private Customer customer;			//客户
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
	public String getSrcfileName() {
		return srcfileName;
	}
	public void setSrcfileName(String srcfileName) {
		this.srcfileName = srcfileName;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public Customer getCustomer() {
		return customer;
	}
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	@Override
	public String toString() {
		return "Certificate [id=" + id + ", name=" + name + ", srcfileName="
				+ srcfileName + ", path=" + path + ", createTime=" + createTime
				+ "]";
	}
}
