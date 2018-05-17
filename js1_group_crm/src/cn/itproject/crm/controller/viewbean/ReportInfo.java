package cn.itproject.crm.controller.viewbean;

public class ReportInfo {

	private String department;
	private Integer phoneCount = 0;
	private Integer messageCount = 0;
	private Integer visitCount = 0;
	private Integer signCount = 0;
	private Integer afterSaleCount = 0;
	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
	}
	public Integer getPhoneCount() {
		return phoneCount;
	}
	public void setPhoneCount(Integer phoneCount) {
		this.phoneCount = phoneCount;
	}
	public Integer getMessageCount() {
		return messageCount;
	}
	public void setMessageCount(Integer messageCount) {
		this.messageCount = messageCount;
	}
	public Integer getVisitCount() {
		return visitCount;
	}
	public void setVisitCount(Integer visitCount) {
		this.visitCount = visitCount;
	}
	public Integer getSignCount() {
		return signCount;
	}
	public void setSignCount(Integer signCount) {
		this.signCount = signCount;
	}
	public Integer getAfterSaleCount() {
		return afterSaleCount;
	}
	public void setAfterSaleCount(Integer afterSaleCount) {
		this.afterSaleCount = afterSaleCount;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((department == null) ? 0 : department.hashCode());
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
		ReportInfo other = (ReportInfo) obj;
		if (department == null) {
			if (other.department != null)
				return false;
		} else if (!department.equals(other.department))
			return false;
		return true;
	}
}
