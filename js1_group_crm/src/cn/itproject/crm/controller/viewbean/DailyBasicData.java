package cn.itproject.crm.controller.viewbean;

import java.io.Serializable;

public class DailyBasicData implements Serializable{
	private static final long serialVersionUID = -9219948799391261364L;
	private Integer id;
	private String name;
	private Integer total = 0;
	private Integer today = 0;
	private Integer done = 0;
	private String percent;
	public DailyBasicData() {
		super();
	}
	
	public DailyBasicData(Integer id, String name) {
		super();
		this.id = id;
		this.name = name;
	}

	public Integer getTotal() {
		return total;
	}
	public void setTotal(Integer total) {
		this.total = total;
	}
	public Integer getToday() {
		return today;
	}
	public void setToday(Integer today) {
		this.today = today;
	}
	public Integer getDone() {
		return done;
	}
	public void setDone(Integer done) {
		this.done = done;
	}
	public String getPercent() {
		return percent;
	}
	public void setPercent(String percent) {
		this.percent = percent;
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

	@Override
	public String toString() {
		return "DailyBasicData [id=" + id + ", name=" + name + ", total="
				+ total + ", today=" + today + ", done=" + done + ", percent="
				+ percent + "]";
	}
}
