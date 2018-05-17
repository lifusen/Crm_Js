package cn.itproject.crm.bean;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 * 企业
 * @author MrLiu
 *
 */
@Entity
public class Enterprise implements Serializable{
	private static final long serialVersionUID = 912769392356141741L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@Column
	private String type;				// 企业类型
	@Column
	private String annualValue;			// 年产值
	@Column
	private String showDetail;			// 流水能否体现
	@Column
	private String license;				// 证照情况
	@Column
	private String operationPlace;		// 经营场所
	@Column
	private String operationScope;		// 经营范围
	@Column
	private String shareRatio;			// 股份比例
	@Column
	private String showStatute;			// 章程体现
	@Column
	private String operationTime;		// 经营时间
	@Column
	private String invoice;				// 承兑汇票
	@Column
	private String cardCustomer;		// 刷卡客户
	@ManyToOne
	@JoinColumn(name = "customerId")
	private Customer customer;
	
	public Enterprise() {
		super();
	}
	public Enterprise(String type, String annualValue, String showDetail,
			String license, String operationPlace, String operationScope,
			String shareRatio, String showStatute, String operationTime,
			String invoice, String cardCustomer) {
		super();
		this.type = type;
		this.annualValue = annualValue;
		this.showDetail = showDetail;
		this.license = license;
		this.operationPlace = operationPlace;
		this.operationScope = operationScope;
		this.shareRatio = shareRatio;
		this.showStatute = showStatute;
		this.operationTime = operationTime;
		this.invoice = invoice;
		this.cardCustomer = cardCustomer;
	}
	public Enterprise(Integer id, String type, String annualValue,
			String showDetail, String license, String operationPlace,
			String operationScope, String shareRatio, String showStatute,
			String operationTime, String invoice, String cardCustomer) {
		super();
		this.id = id;
		this.type = type;
		this.annualValue = annualValue;
		this.showDetail = showDetail;
		this.license = license;
		this.operationPlace = operationPlace;
		this.operationScope = operationScope;
		this.shareRatio = shareRatio;
		this.showStatute = showStatute;
		this.operationTime = operationTime;
		this.invoice = invoice;
		this.cardCustomer = cardCustomer;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getAnnualValue() {
		return annualValue;
	}
	public void setAnnualValue(String annualValue) {
		this.annualValue = annualValue;
	}
	public String getShowDetail() {
		return showDetail;
	}
	public void setShowDetail(String showDetail) {
		this.showDetail = showDetail;
	}
	public String getLicense() {
		return license;
	}
	public void setLicense(String license) {
		this.license = license;
	}
	public String getOperationPlace() {
		return operationPlace;
	}
	public void setOperationPlace(String operationPlace) {
		this.operationPlace = operationPlace;
	}
	public String getOperationScope() {
		return operationScope;
	}
	public void setOperationScope(String operationScope) {
		this.operationScope = operationScope;
	}
	public String getShareRatio() {
		return shareRatio;
	}
	public void setShareRatio(String shareRatio) {
		this.shareRatio = shareRatio;
	}
	public String getShowStatute() {
		return showStatute;
	}
	public void setShowStatute(String showStatute) {
		this.showStatute = showStatute;
	}
	public String getOperationTime() {
		return operationTime;
	}
	public void setOperationTime(String operationTime) {
		this.operationTime = operationTime;
	}
	public String getInvoice() {
		return invoice;
	}
	public void setInvoice(String invoice) {
		this.invoice = invoice;
	}
	public String getCardCustomer() {
		return cardCustomer;
	}
	public void setCardCustomer(String cardCustomer) {
		this.cardCustomer = cardCustomer;
	}
	public Customer getCustomer() {
		return customer;
	}
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((annualValue == null) ? 0 : annualValue.hashCode());
		result = prime * result
				+ ((cardCustomer == null) ? 0 : cardCustomer.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((invoice == null) ? 0 : invoice.hashCode());
		result = prime * result + ((license == null) ? 0 : license.hashCode());
		result = prime * result
				+ ((operationPlace == null) ? 0 : operationPlace.hashCode());
		result = prime * result
				+ ((operationScope == null) ? 0 : operationScope.hashCode());
		result = prime * result
				+ ((operationTime == null) ? 0 : operationTime.hashCode());
		result = prime * result
				+ ((shareRatio == null) ? 0 : shareRatio.hashCode());
		result = prime * result
				+ ((showDetail == null) ? 0 : showDetail.hashCode());
		result = prime * result
				+ ((showStatute == null) ? 0 : showStatute.hashCode());
		result = prime * result + ((type == null) ? 0 : type.hashCode());
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
		Enterprise other = (Enterprise) obj;
		if (annualValue == null) {
			if (other.annualValue != null)
				return false;
		} else if (!annualValue.equals(other.annualValue))
			return false;
		if (cardCustomer == null) {
			if (other.cardCustomer != null)
				return false;
		} else if (!cardCustomer.equals(other.cardCustomer))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (invoice == null) {
			if (other.invoice != null)
				return false;
		} else if (!invoice.equals(other.invoice))
			return false;
		if (license == null) {
			if (other.license != null)
				return false;
		} else if (!license.equals(other.license))
			return false;
		if (operationPlace == null) {
			if (other.operationPlace != null)
				return false;
		} else if (!operationPlace.equals(other.operationPlace))
			return false;
		if (operationScope == null) {
			if (other.operationScope != null)
				return false;
		} else if (!operationScope.equals(other.operationScope))
			return false;
		if (operationTime == null) {
			if (other.operationTime != null)
				return false;
		} else if (!operationTime.equals(other.operationTime))
			return false;
		if (shareRatio == null) {
			if (other.shareRatio != null)
				return false;
		} else if (!shareRatio.equals(other.shareRatio))
			return false;
		if (showDetail == null) {
			if (other.showDetail != null)
				return false;
		} else if (!showDetail.equals(other.showDetail))
			return false;
		if (showStatute == null) {
			if (other.showStatute != null)
				return false;
		} else if (!showStatute.equals(other.showStatute))
			return false;
		if (type == null) {
			if (other.type != null)
				return false;
		} else if (!type.equals(other.type))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "Enterprise [id=" + id + ", type=" + type + ", annualValue="
				+ annualValue + ", showDetail=" + showDetail + ", license="
				+ license + ", operationPlace=" + operationPlace
				+ ", operationScope=" + operationScope + ", shareRatio="
				+ shareRatio + ", showStatute=" + showStatute
				+ ", operationTime=" + operationTime + ", invoice=" + invoice
				+ ", cardCustomer=" + cardCustomer + "]";
	}
	
	public String toTagString() {
		return  formatNull("企业类型", type)+
				formatNull("年产值", annualValue)+
				formatNull("证照请款", license)+
				formatNull("流水体现", showDetail)+
				formatNull("经营场所", operationPlace)+
				formatNull("经营范围", operationScope)+
				getShareRatio(shareRatio)+
				formatNull("章程体现", showStatute)+
				formatNull("经营时间", operationTime)+
				formatNull("承兑汇票", invoice)+
				formatNull("刷卡客户", cardCustomer);
	}
	private String formatNull(String name,String value){
		if (value==null||value.trim().equals("")) {
			return "";
		}
		return name+":"+value+" ";
	}
	private String getShareRatio(String i){
		if (i==null||i.trim().equals("")) {
			return "";
		}
		String string = "";
		if (i.equals("1")) {
			string = "10%以下";
		}else if (i.equals("2")) {
			string = "10%-30%";
		}else if (i.equals("3")) {
			string = "30%-50%";
		}else if (i.equals("4")) {
			string = "50%以上";
		}
		return "股份比例:"+string+" ";
	}
}
