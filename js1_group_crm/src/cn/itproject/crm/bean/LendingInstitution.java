package cn.itproject.crm.bean;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * 贷款机构
 * @author MrLiu
 *
 */
@Entity 
public class LendingInstitution implements Serializable{ 
	private static final long serialVersionUID = -5557216918793789796L;
	public static final Integer BANK = 0;				//银行
	public static final Integer SMALL_LAND = 1;			//小贷
	public static final Integer PERSONAL = 2;			//个人
	public static final Integer EMPTY = 3;				//空
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@Column
	private String name;
	@Column
	private Integer type;
	public LendingInstitution() {
		super();
	}
	public LendingInstitution(Integer id) {
		super();
		this.id = id;
	}
	public LendingInstitution(String name, Integer type) {
		super();
		this.name = name;
		this.type = type;
	}
	public LendingInstitution(Integer id, String name, Integer type) {
		super();
		this.id = id;
		this.name = name;
		this.type = type;
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
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	@Override
	public String toString() {
		return "LendingInstitution [id=" + id + ", name=" + name + ", type="
				+ type + "]";
	}
}
