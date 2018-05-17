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
 * 菜单
 * @author ham
 *
 */
@Entity
public class Menu implements Serializable{
	private static final long serialVersionUID = 1603722809503094642L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@Column
	private String name;
	@Column
	private String url;				
	@Column			 
	private String icon;			// 显示图标
	@Column
	private Integer status;			// 状态（0禁用、1正常）
	@ManyToOne
	@JoinColumn(name="parentId")
	private Menu parent;
	@Column
	private Integer showOrder;		// 显示顺序
	@Column
	private String domId;			// html元素id
	@Column
	private Integer openNewPage;	// 是否打开新页面（0否、1是）
	@Column
	private Integer type;			// 菜单类型，1表示只有super_role才能使用
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
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getIcon() {
		return icon;
	}
	public void setIcon(String icon) {
		this.icon = icon;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public Menu getParent() {
		return parent;
	}
	public void setParent(Menu parent) {
		this.parent = parent;
	}
	public String getDomId() {
		return domId;
	}
	public void setDomId(String domId) {
		this.domId = domId;
	}
	public Integer getOpenNewPage() {
		return openNewPage;
	}
	public void setOpenNewPage(Integer openNewPage) {
		this.openNewPage = openNewPage;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public Menu(){}
	public Menu(Integer id, String name, String url, String icon,
			Integer status, Menu parent) {
		this.id = id;
		this.name = name;
		this.url = url;
		this.icon = icon;
		this.status = status;
		this.parent = parent;
	}
	public Menu(Integer id, String name, String url, String icon,
			Integer status, Menu parent, Integer showOrder) {
		super();
		this.id = id;
		this.name = name;
		this.url = url;
		this.icon = icon;
		this.status = status;
		this.parent = parent;
		this.showOrder = showOrder;
	}
	public Menu(Integer id) {
		this.id = id;
	}
	public Integer getShowOrder() {
		return showOrder;
	}
	public void setShowOrder(Integer showOrder) {
		this.showOrder = showOrder;
	}
}
