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
 * 工作日
 * @author MrLiu
 *
 */
@Entity 
public class Workday implements Serializable{ 
	private static final long serialVersionUID = 6194633806889290656L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@Column(unique=true)
	@Temporal(TemporalType.DATE)					
	private Date date = new Date();					//制定计划的年和月
	@Column
	private Integer day;							//工作天数
	@Column
	@Lob
	@Type(type="text")
	private String dateString;						//当月工作日期字符串
	@Column
	@Temporal(TemporalType.TIMESTAMP)				
	private Date createTime = new Date();			//创建时间
	@Column
	@Temporal(TemporalType.TIMESTAMP)				
	private Date updateTime;						//修改时间
	@ManyToOne	
	@JoinColumn(name="employeeId")
	private Employee employee;						//制定人
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public Integer getDay() {
		return day;
	}
	public void setDay(Integer day) {
		this.day = day;
	}
	public String getDateString() {
		return dateString;
	}
	public void setDateString(String dateString) {
		this.dateString = dateString;
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
	public Employee getEmployee() {
		return employee;
	}
	public void setEmployee(Employee employee) {
		this.employee = employee;
	}
	@Override
	public String toString() {
		return "Workday [id=" + id + ", date=" + date + ", day=" + day
				+ ", dateString=" + dateString + ", createTime=" + createTime
				+ ", updateTime=" + updateTime + "]";
	}
}
