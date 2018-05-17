package cn.itproject.crm.controller.viewbean;

import java.io.Serializable;

/**
 * 抵押物视图bean
 * @author MrLiu
 *
 */
public class CollateralViewBean implements Serializable{
	private static final long serialVersionUID = 5759804372591782150L;
	private Integer id;				//资产ID
	private String name;			//资产名称
	
	public CollateralViewBean() {
		super();
	}
	public CollateralViewBean(Integer id, String name) {
		super();
		this.id = id;
		this.name = name;
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
	
	//id和name一样表示是同一个对象
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CollateralViewBean other = (CollateralViewBean) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}
	public String toString() {
		return id+"-"+name;
	}
}
