package cn.itproject.crm.controller.viewbean;

import java.io.Serializable;

public class VisitOrSignData implements Serializable{
	private static final long serialVersionUID = -1094798324101592850L;
	private Integer id;						//ID
	private String name;					//名称
	private Double planVisit = 0d;			//目标上门
	private Integer visit = 0;				//已上门
	private Double visitPercent = 0d;		//上门完成率
	private Double planSign = 0d;			//目标签单
	private Integer sign = 0;				//已签单
	private Double signPercent = 0d;		//签单完成率
	private Double cro = 0d;						//转换率
	public VisitOrSignData() {
		super();
	}
	public VisitOrSignData(Integer id, String name) {
		super();
		this.id = id;
		this.name = name;
	}
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
	public Double getPlanVisit() {
		return planVisit;
	}
	public void setPlanVisit(Double planVisit) {
		this.planVisit = planVisit;
	}
	public Integer getVisit() {
		return visit;
	}
	public void setVisit(Integer visit) {
		this.visit = visit;
	}
	public Double getVisitPercent() {
		return visitPercent;
	}
	public void setVisitPercent(Double visitPercent) {
		this.visitPercent = visitPercent;
	}
	public Double getPlanSign() {
		return planSign;
	}
	public void setPlanSign(Double planSign) {
		this.planSign = planSign;
	}
	public Integer getSign() {
		return sign;
	}
	public void setSign(Integer sign) {
		this.sign = sign;
	}
	public Double getSignPercent() {
		return signPercent;
	}
	public void setSignPercent(Double signPercent) {
		this.signPercent = signPercent;
	}
	public Double getCro() {
		return cro;
	}
	public void setCro(Double cro) {
		this.cro = cro;
	}
	@Override
	public String toString() {
		return "VisitOrSignData [id=" + id + ", name=" + name + ", planVisit="
				+ planVisit + ", visit=" + visit + ", visitPercent="
				+ visitPercent + ", planSign=" + planSign + ", sign=" + sign
				+ ", signPercent=" + signPercent + ", cro=" + cro + "]";
	}
	
}
