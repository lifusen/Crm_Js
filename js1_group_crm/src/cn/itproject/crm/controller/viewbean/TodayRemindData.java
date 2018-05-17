package cn.itproject.crm.controller.viewbean;

import java.io.Serializable;

public class TodayRemindData implements Serializable{
	private static final long serialVersionUID = -3603581196897825962L;
	private Integer id;
	private Integer remind;				//提醒人数
	private Integer remindDone;			//已跟进人数
	private Double remindPercent;		//提醒跟进完成率
	private Integer repayment;			//还款提醒人数
	private Integer repaymentDone;		//已完成还款提醒人数
	private Double repaymentPercent;	//还款完成百分比
	private Integer basic;				//基础工作
	private Integer basicDone;			//基础工作已完成
	private Double basicPercent;		//基础工作百分比
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getRemind() {
		return remind;
	}
	public void setRemind(Integer remind) {
		this.remind = remind;
	}
	public Integer getRemindDone() {
		return remindDone;
	}
	public void setRemindDone(Integer remindDone) {
		this.remindDone = remindDone;
	}
	public Double getRemindPercent() {
		return remindPercent;
	}
	public void setRemindPercent(Double remindPercent) {
		this.remindPercent = remindPercent;
	}
	public Integer getRepayment() {
		return repayment;
	}
	public void setRepayment(Integer repayment) {
		this.repayment = repayment;
	}
	public Integer getRepaymentDone() {
		return repaymentDone;
	}
	public void setRepaymentDone(Integer repaymentDone) {
		this.repaymentDone = repaymentDone;
	}
	public Double getRepaymentPercent() {
		return repaymentPercent;
	}
	public void setRepaymentPercent(Double repaymentPercent) {
		this.repaymentPercent = repaymentPercent;
	}
	public Integer getBasic() {
		return basic;
	}
	public void setBasic(Integer basic) {
		this.basic = basic;
	}
	public Integer getBasicDone() {
		return basicDone;
	}
	public void setBasicDone(Integer basicDone) {
		this.basicDone = basicDone;
	}
	public Double getBasicPercent() {
		return basicPercent;
	}
	public void setBasicPercent(Double basicPercent) {
		this.basicPercent = basicPercent;
	}
	@Override
	public String toString() {
		return "TodayRemindData [id=" + id + ", remind=" + remind
				+ ", remindDone=" + remindDone + ", remindPercent="
				+ remindPercent + ", repayment=" + repayment
				+ ", repaymentDone=" + repaymentDone + ", repaymentPercent="
				+ repaymentPercent + ", basic=" + basic + ", basicDone="
				+ basicDone + ", basicPercent=" + basicPercent + "]";
	}
}
