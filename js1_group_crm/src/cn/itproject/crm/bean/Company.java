package cn.itproject.crm.bean;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Company implements Serializable{
	private static final long serialVersionUID = 2152743869457738915L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@Column(name = "name")
	private String name;	// 名称
	@Column(name = "type")	
	private Integer type;	// 类型(1业务中心，2权证中心）
	@Column(name = "remark")
	private String remark;	// 描述
	@Column(name="create_time")
	@Temporal(TemporalType.TIMESTAMP)
	private Date createTime;// 创建时间
	@Column(name="update_time")
	@Temporal(TemporalType.TIMESTAMP)
	private Date updateTime;// 修改时间
	@Column(name = "creater")
	private Integer creater;// 创建人
	@Column(name = "editor")
	private Integer editor;// 修改人
	@Column(name = "creater_name")
	private String createrName;	// 创建人名字
	@Column(name = "editor_name")
	private String editorName;	// 修改人名字
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
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public Date getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	public Integer getCreater() {
		return creater;
	}
	public void setCreater(Integer creater) {
		this.creater = creater;
	}
	public Integer getEditor() {
		return editor;
	}
	public void setEditor(Integer editor) {
		this.editor = editor;
	}
	public String getCreaterName() {
		return createrName;
	}
	public void setCreaterName(String createrName) {
		this.createrName = createrName;
	}
	public String getEditorName() {
		return editorName;
	}
	public void setEditorName(String editorName) {
		this.editorName = editorName;
	}
	@Override
	public String toString() {
		return "Company [id=" + id + ", name=" + name + ", type=" + type + ", remark=" + remark + ", createTime="
				+ createTime + ", updateTime=" + updateTime + ", creater=" + creater + ", editor=" + editor
				+ ", createrName=" + createrName + ", editorName=" + editorName + "]";
	}
}
