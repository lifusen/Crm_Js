package cn.itproject.crm.controller.viewbean;

import cn.itproject.crm.bean.CustomerRoster;

public class ShowRoster {
	CustomerRoster roster;
	Integer count;
	public CustomerRoster getRoster() {
		return roster;
	}
	public void setRoster(CustomerRoster roster) {
		this.roster = roster;
	}
	public Integer getCount() {
		return count;
	}
	public void setCount(Integer count) {
		this.count = count;
	}
	public ShowRoster(){}
	public ShowRoster(CustomerRoster roster, Integer count) {
		this.roster = roster;
		this.count = count;
	}
}
