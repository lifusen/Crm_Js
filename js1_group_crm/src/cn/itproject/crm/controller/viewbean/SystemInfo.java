package cn.itproject.crm.controller.viewbean;

import java.io.Serializable;

public class SystemInfo implements Serializable{
	private static final long serialVersionUID = 5050137045673386763L;
	/** 公司id */
	private String companyIds;
	public String getCompanyIds() {
		return companyIds;
	}
	public void setCompanyIds(String companyIds) {
		this.companyIds = companyIds;
	}
	
}
