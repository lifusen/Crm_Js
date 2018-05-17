package cn.itproject.crm.controller.viewbean;

import java.io.Serializable;

public class MoneyData implements Serializable{
	private static final long serialVersionUID = -5873501429842059622L;
	private Integer id;
	private String name;
	private Double loanAmountTotal=0.0;		// 放款金额
	private Double netEarningsTotal=0.0;	// 净业绩
	private Double finishPercent=0.0;		// 完成比例
	private Double goal=0.0;				// 目标
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
	public Double getLoanAmountTotal() {
		return loanAmountTotal;
	}
	public void setLoanAmountTotal(Double loanAmountTotal) {
		this.loanAmountTotal = loanAmountTotal;
	}
	public Double getNetEarningsTotal() {
		return netEarningsTotal;
	}
	public void setNetEarningsTotal(Double netEarningsTotal) {
		this.netEarningsTotal = netEarningsTotal;
	}
	public Double getFinishPercent() {
		return finishPercent;
	}
	public void setFinishPercent(Double finishPercent) {
		this.finishPercent = finishPercent;
	}
	public Double getGoal() {
		return goal;
	}
	public void setGoal(Double goal) {
		this.goal = goal;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
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
		MoneyData other = (MoneyData) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	public MoneyData(Integer id) {
		super();
		this.id = id;
	}
	public MoneyData() {
		super();
	}
}
