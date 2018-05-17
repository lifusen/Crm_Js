package cn.itproject.crm.controller.viewbean;

/**
 * 客户资产
 * @author MrLiu
 *
 */
public class CustomerAsset {
	private Integer id;		//资产ID
	private Integer type;	//资产类型
	private String name;	//资产名称
	private String detail;	//资产详细
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDetail() {
		return detail;
	}
	public void setDetail(String detail) {
		this.detail = detail;
	}
	@Override
	public String toString() {
		return "CustomerAsset [id=" + id + ", type=" + type + ", name=" + name
				+ ", detail=" + detail + "]";
	}
}
