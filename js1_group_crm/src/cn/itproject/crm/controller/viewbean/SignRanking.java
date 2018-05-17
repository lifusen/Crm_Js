package cn.itproject.crm.controller.viewbean;

import java.io.Serializable;

public class SignRanking implements Serializable{
	private static final long serialVersionUID = -7034522276469123673L;
	private Integer did;
	private String dname;
	private Integer eid;
	private String ename;
	private Integer visit = 0;
	private Integer sign = 0;
	private Double cro = 0d;
	public Integer getDid() {
		return did;
	}
	public void setDid(Integer did) {
		this.did = did;
	}
	public String getDname() {
		return dname;
	}
	public void setDname(String dname) {
		this.dname = dname;
	}
	public Integer getEid() {
		return eid;
	}
	public void setEid(Integer eid) {
		this.eid = eid;
	}
	public String getEname() {
		return ename;
	}
	public void setEname(String ename) {
		this.ename = ename;
	}
	public Integer getVisit() {
		return visit;
	}
	public void setVisit(Integer visit) {
		this.visit = visit;
	}
	public Integer getSign() {
		return sign;
	}
	public void setSign(Integer sign) {
		this.sign = sign;
	}
	public Double getCro() {
		return cro;
	}
	public void setCro(Double cro) {
		this.cro = cro;
	}
	@Override
	public String toString() {
		return "SignRanking [did=" + did + ", dname=" + dname + ", eid=" + eid
				+ ", ename=" + ename + ", visit=" + visit + ", sign=" + sign
				+ ", cro=" + cro + "]";
	}
}
