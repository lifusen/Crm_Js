package cn.itproject.crm.bean;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * 部门
 * @author ham
 *
 */
@Entity
public class Department implements Serializable{
	private static final long serialVersionUID = 7705250348300601101L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;		// id
	@Column
	private String name;	// 名称
	@Column
	private String remark;	// 备注
	@Column
	private Integer companyId;				//公司ID
	@Column
	private String type;		// 公司类型
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
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public Department(){}
	public Department(Integer id, String name, String remark) {
		this.id = id;
		this.name = name;
		this.remark = remark;
	}
	
	public Department(Integer id) {
		this.id = id;
	}
	public Integer getCompanyId() {
		return companyId;
	}
	public void setCompanyId(Integer companyId) {
		this.companyId = companyId;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	@Override
	public String toString() {
		return "Department [id=" + id + ", name=" + name + ", remark=" + remark + ", companyId=" + companyId + ", type="
				+ type + "]";
	}
}
