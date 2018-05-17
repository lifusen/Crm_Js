package cn.itproject.crm.controller.filter;

import java.io.Serializable;
import java.util.Date;

public class QueryUseTime implements Serializable{
	private static final long serialVersionUID = 265969059266882105L;
	private Long diffTime;
	private String diffTimeString;
	private String url;
	private Date startDate;
	private Date endDate;
	public Long getDiffTime() {
		return diffTime;
	}
	public void setDiffTime(Long diffTime) {
		this.diffTime = diffTime;
	}
	public String getDiffTimeString() {
		return diffTimeString;
	}
	public void setDiffTimeString(String diffTimeString) {
		this.diffTimeString = diffTimeString;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	@Override
	public String toString() {
		return "QueryUseTime [diffTime=" + diffTime + ", diffTimeString=" + diffTimeString + ", url=" + url
				+ ", startDate=" + startDate + ", endDate=" + endDate + "]";
	}
}
