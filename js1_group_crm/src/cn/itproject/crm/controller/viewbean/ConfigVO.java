package cn.itproject.crm.controller.viewbean;

import java.io.Serializable;

public class ConfigVO implements Serializable{
	private static final long serialVersionUID = -1148417809592534196L;
	private String commonPoolDay;
	public String getCommonPoolDay() {
		return commonPoolDay;
	}
	public void setCommonPoolDay(String commonPoolDay) {
		this.commonPoolDay = commonPoolDay;
	}
	@Override
	public String toString() {
		return "ConfigVO [commonPoolDay=" + commonPoolDay + "]";
	}
}
