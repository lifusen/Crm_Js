package cn.itproject.crm.bean;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.Type;

/**
 * 
 * @author MrLiu
 *
 */
@Entity
public class Message implements Serializable{
	private static final long serialVersionUID = -7686416866151186939L;
	//消息类型
	/**公告*/
	public static final Integer NOTICE = 0;			//公告
	/**制度*/
	public static final Integer INSTITUTION  = 1;	//制度
	/**说辞*/
	public static final Integer EXCUSE = 2;			//说辞
	/**银行产品*/
	public static final Integer BANK_PRODUCT = 3;	//银行产品
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@Column
	private Integer type;			//类型
	@Column
	private String title;			//标题
	@Lob
	@Type(type="text")
	@Column
	private String content;			//内容
	@ManyToOne
	@JoinColumn(name="publisherId")
	private Employee publisher;		//发布人
	@Column
	@Temporal(TemporalType.TIMESTAMP)
	private Date pubdate;			//发布时间
	@Column
	private Integer hits;			//阅读次数(预留字段)
	@Column
	private Integer companyId;				//公司ID
	@Column
	private Integer scopeType;		//消息范围类型 1.本中心,2.所有中心
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
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Employee getPublisher() {
		return publisher;
	}
	public void setPublisher(Employee publisher) {
		this.publisher = publisher;
	}
	public Date getPubdate() {
		return pubdate;
	}
	public void setPubdate(Date pubdate) {
		this.pubdate = pubdate;
	}
	public Integer getHits() {
		return hits;
	}
	public void setHits(Integer hits) {
		this.hits = hits;
	}
	public Integer getCompanyId() {
		return companyId;
	}
	public void setCompanyId(Integer companyId) {
		this.companyId = companyId;
	}
	public Integer getScopeType() {
		return scopeType;
	}
	public void setScopeType(Integer scopeType) {
		this.scopeType = scopeType;
	}
	@Override
	public String toString() {
		return "Message [id=" + id + ", type=" + type + ", title=" + title + ", content=" + content + ", publisher="
				+ publisher + ", pubdate=" + pubdate + ", hits=" + hits + ", companyId=" + companyId + ", scopeType="
				+ scopeType + "]";
	}
}
