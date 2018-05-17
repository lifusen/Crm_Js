package cn.itproject.crm.controller.viewbean;

import java.util.Date;
import java.util.List;

public class Report {

	private Date date;
	private List<ReportInfo> reportInfos;
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public List<ReportInfo> getReportInfos() {
		return reportInfos;
	}
	public void setReportInfos(List<ReportInfo> reportInfos) {
		this.reportInfos = reportInfos;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((date == null) ? 0 : date.hashCode());
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
		Report other = (Report) obj;
		if (date == null) {
			if (other.date != null)
				return false;
		} else if (!date.equals(other.date))
			return false;
		return true;
	}
	public Report(){}
	public Report(Date date) {
		this.date = date;
	}
}

