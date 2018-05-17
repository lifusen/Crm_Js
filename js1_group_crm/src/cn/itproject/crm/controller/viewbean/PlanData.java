package cn.itproject.crm.controller.viewbean;

import java.io.Serializable;

public class PlanData implements Serializable{
	private static final long serialVersionUID = 4392574215172062135L;
	private Integer id;					
	private Double visit = 0d;					//目标上门
	private Double sign = 0d;					//目标签单
	private Double performance = 0d;			//目标业绩
	public Integer getId() {
		return id;
	}
	public Double getVisit() {
		return visit;
	}
	public void setVisit(Double visit) {
		this.visit = visit;
	}
	public Double getSign() {
		return sign;
	}
	public void setSign(Double sign) {
		this.sign = sign;
	}
	public Double getPerformance() {
		return performance;
	}
	public void setPerformance(Double performance) {
		this.performance = performance;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	@Override
	public String toString() {
		return "PlanData [id=" + id + ", visit=" + visit + ", sign=" + sign
				+ ", performance=" + performance + "]";
	}
}
