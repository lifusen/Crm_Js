package cn.itproject.crm.bean;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="Role")
public class Role implements Serializable{
	private static final long serialVersionUID = -381516054871243920L;
	
	/** 角色类型(层级)**/
	public static enum RoleType {
		/**0.管理层:admin/总经理/销售总监/市场部总监**/
		manager,
		/**1.业务部门经理**/
		businessManager,
		/**2.业务员**/
		employee
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name="roleName")
	private String roleName;
	
	@Column(name = "remark")
	private String remark;
	
	@Column(name="readOnly")
	private Integer readOnly = 1;			// 0为只读，1为可以读写
	
	@Column(name="menus")
	private String menus;				// 模块id列表，m1;m2..

	@Column
	private Integer companyId;				//公司ID 
	private Integer type;			// 1表示内部分配的账号角色
	@Column
	private Integer customerNO;		//角色持有客户数量上限
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Integer getReadOnly() {
		return readOnly;
	}

	public void setReadOnly(Integer readOnly) {
		this.readOnly = readOnly;
	}

	public String getMenus() {
		return menus;
	}

	public void setMenus(String menus) {
		this.menus = menus;
	}
	public Role(){}

	public Role(Integer id, String roleName, String remark, Integer readOnly,
			String menus) {
		this.id = id;
		this.roleName = roleName;
		this.remark = remark;
		this.readOnly = readOnly;
		this.menus = menus;
	}
	public Role(Integer id) {
		this.id = id;
	}

	public Integer getCompanyId() {
		return companyId;
	}

	public void setCompanyId(Integer companyId) {
		this.companyId = companyId;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Integer getCustomerNO() {
		return customerNO;
	}

	public void setCustomerNO(Integer customerNO) {
		this.customerNO = customerNO;
	}
	
}