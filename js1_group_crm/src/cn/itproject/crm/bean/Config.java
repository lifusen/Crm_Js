package cn.itproject.crm.bean;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * 系统参数配置
 * @author MrLiu
 *
 */
@Entity
@Table(name="Config")
public class Config {
	/**
	 * 公共池天数
	 */
	public static String COMMON_POOL_DAY = "commonPoolDay";
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;						// 主键
	@Column(unique=true)
	private String name;					// 名称
	@Column(unique=true)
	private String configKey;				// key
	@Column
	private String value;					// 值
	@Column
	private String remark;					// 备注
	@Column
	@Temporal(TemporalType.TIMESTAMP)
	private Date createTime;				// 创建时间
	@Column
	private Integer updaterId;				// 修改人
	@Column
	private String updaterName;				// 修改人名称
	@Column
	@Temporal(TemporalType.TIMESTAMP)
	private Date updateTime;				// 修改时间
	
	public Config() {
		super();
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
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
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
	public Integer getUpdaterId() {
		return updaterId;
	}
	public void setUpdaterId(Integer updaterId) {
		this.updaterId = updaterId;
	}
	public Date getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	public String getConfigKey() {
		return configKey;
	}
	public void setConfigKey(String configKey) {
		this.configKey = configKey;
	}
	public String getUpdaterName() {
		return updaterName;
	}
	public void setUpdaterName(String updaterName) {
		this.updaterName = updaterName;
	}
	@Override
	public String toString() {
		return "Config [id=" + id + ", name=" + name + ", configKey=" + configKey + ", value=" + value + ", remark="
				+ remark + ", createTime=" + createTime + ", updaterId=" + updaterId + ", updaterName=" + updaterName
				+ ", updateTime=" + updateTime + "]";
	}
}
