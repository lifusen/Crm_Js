package cn.itproject.crm.controller.viewbean;

import java.util.ArrayList;
import java.util.List;


public class ReportDetail {

	private List<String> dates = new ArrayList<>();
	private List<SeriesInfo> seriesInfos = new ArrayList<>();
	public List<String> getDates() {
		return dates;
	}
	public void setDates(List<String> dates) {
		this.dates = dates;
	}
	public List<SeriesInfo> getSeriesInfos() {
		return seriesInfos;
	}
	public void setSeriesInfos(List<SeriesInfo> seriesInfos) {
		this.seriesInfos = seriesInfos;
	}
}
