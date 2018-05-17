package cn.itproject.crm.bean;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 客户来源
 * @author ham
 *
 */
@Entity
@Table(name="CustomerSource")
public class CustomerSource implements Serializable{
	private static final long serialVersionUID = 9132026362802954495L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name="sourceName",length=15)
	private String sourceName;						// 来源名称
	
	@Column(name="remark",length=50)
	private String remark;							// 备注
	@Column
	private Integer companyId;				//公司ID
	public CustomerSource() {
		super();
	}

	public CustomerSource(Integer id) {
		super();
		this.id = id;
	}

	public CustomerSource(String sourceName) {
		super();
		this.sourceName = sourceName;
	}

	public CustomerSource(String sourceName, String remark) {
		super();
		this.sourceName = sourceName;
		this.remark = remark;
	}

	public CustomerSource(Integer id, String sourceName, String remark) {
		super();
		this.id = id;
		this.sourceName = sourceName;
		this.remark = remark;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getSourceName() {
		return sourceName;
	}

	public void setSourceName(String sourceName) {
		this.sourceName = sourceName;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Integer getCompanyId() {
		return companyId;
	}

	public void setCompanyId(Integer companyId) {
		this.companyId = companyId;
	}

	@Override
	public String toString() {
		return "CustomerSource [id=" + id + ", sourceName=" + sourceName + ", remark=" + remark + ", companyId="
				+ companyId + "]";
	}

}
