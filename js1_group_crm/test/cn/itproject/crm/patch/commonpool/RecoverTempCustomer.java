package cn.itproject.crm.patch.commonpool;

import java.io.Serializable;
import java.util.Date;

/**
 * 需要恢复的临时客户对象
 * @author MrLiu
 *
 */
public class RecoverTempCustomer implements Serializable{
	private static final long serialVersionUID = -1657373928476097623L;
	private Integer id;
	private Object departmentId;
	private Object employeeId;
	private Object state;
	private Object publicView;
	private Date releaseDate;
	private Date releaseTime;
	private Object releaseType;
	private Object releaseId;
	public RecoverTempCustomer() {
		super();
	}
	public RecoverTempCustomer(Integer id, Object departmentId, Object employeeId, Object state, Object publicView,
			Date releaseDate, Date releaseTime, Object releaseType, Object releaseId) {
		super();
		this.id = id;
		this.departmentId = departmentId;
		this.employeeId = employeeId;
		this.state = state;
		this.publicView = publicView;
		this.releaseDate = releaseDate;
		this.releaseTime = releaseTime;
		this.releaseType = releaseType;
		this.releaseId = releaseId;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Object getDepartmentId() {
		return departmentId;
	}
	public void setDepartmentId(Object departmentId) {
		this.departmentId = departmentId;
	}
	public Object getEmployeeId() {
		return employeeId;
	}
	public void setEmployeeId(Object employeeId) {
		this.employeeId = employeeId;
	}
	public Object getState() {
		return state;
	}
	public void setState(Object state) {
		this.state = state;
	}
	public Object getPublicView() {
		return publicView;
	}
	public void setPublicView(Object publicView) {
		this.publicView = publicView;
	}
	public Date getReleaseDate() {
		return releaseDate;
	}
	public void setReleaseDate(Date releaseDate) {
		this.releaseDate = releaseDate;
	}
	public Date getReleaseTime() {
		return releaseTime;
	}
	public void setReleaseTime(Date releaseTime) {
		this.releaseTime = releaseTime;
	}
	public Object getReleaseType() {
		return releaseType;
	}
	public void setReleaseType(Object releaseType) {
		this.releaseType = releaseType;
	}
	public Object getReleaseId() {
		return releaseId;
	}
	public void setReleaseId(Object releaseId) {
		this.releaseId = releaseId;
	}
	@Override
	public String toString() {
		return "RecoverTempCustomer [id=" + id + ", departmentId=" + departmentId + ", employeeId=" + employeeId
				+ ", state=" + state + ", publicView=" + publicView + ", releaseDate=" + releaseDate + ", releaseTime="
				+ releaseTime + ", releaseType=" + releaseType + ", releaseId=" + releaseId + "]";
	}
}
