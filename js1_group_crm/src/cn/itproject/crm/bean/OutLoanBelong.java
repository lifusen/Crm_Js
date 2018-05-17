package cn.itproject.crm.bean;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * 签单放款关系表
 * @author Administrator SwordLiu 2017.01.12 
 *
 */
@Entity
@Table(name="outloanbelong")
public class OutLoanBelong implements Serializable{
	private static final long serialVersionUID = 2373383572263771079L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;				//主键
	@Column(name="signcustomerId")
	private Integer signCustomerId; //关联到签单客户id
	@Column(name="warranterId")
	private Integer warranterId;	//关联到权证专员id(employee.id)
	@Column(name="departmentId")
	private Integer departmentId;	//关联到权证专员所属部门id(employee.departmentid)
	@Column(name="companyId")
	private Integer companyId;		//关联到权证专员所属公司id(employee.companyid)
	@Column(name="type")
	private Integer type;			//主贷权证：0，辅贷权证：1；不为空值 
	@Column(name="outLoanState")
	private Integer outLoanState=0;	//放款状态：已放款：1，未放款：0，默认
	@Column(name="turnDetail")
	private String turnDetail="";		//签单客户移交信息
	@Column(name="createTime")
	@Temporal(TemporalType.TIMESTAMP)
	private Date createTime = new Date();	//创建时间
	
	@Override
	public String toString() {
		return "OutLoanBelong[id:"+id+",signCustomerId:"+signCustomerId+","
				+ "warranterId:"+warranterId+",departmentId:"+departmentId+","
				+ "companyId:"+companyId+",type:"+type+",createTime:"+createTime+"]";
	}
	
	public OutLoanBelong() {
	}
	
	public OutLoanBelong(Integer signCustomerId, Integer warranterId, Integer departmentId, Integer companyId,
			Integer type) {
		this.signCustomerId = signCustomerId;
		this.warranterId = warranterId;
		this.departmentId = departmentId;
		this.companyId = companyId;
		this.type = type;
	}
	
	public OutLoanBelong(Integer signCustomerId, Integer warranterId, Integer departmentId, Integer companyId,
			Integer type, Integer outLoanState) {
		this.signCustomerId = signCustomerId;
		this.warranterId = warranterId;
		this.departmentId = departmentId;
		this.companyId = companyId;
		this.type = type;
		this.outLoanState = outLoanState;
	}

	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getSignCustomerId() {
		return signCustomerId;
	}
	public void setSignCustomerId(Integer signCustomerId) {
		this.signCustomerId = signCustomerId;
	}
	public Integer getWarranterId() {
		return warranterId;
	}
	public void setWarranterId(Integer warranterId) {
		this.warranterId = warranterId;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Integer getDepartmentId() {
		return departmentId;
	}

	public void setDepartmentId(Integer departmentId) {
		this.departmentId = departmentId;
	}

	public Integer getCompanyId() {
		return companyId;
	}

	public void setCompanyId(Integer companyId) {
		this.companyId = companyId;
	}

	public String getTurnDetail() {
		return turnDetail;
	}

	public void setTurnDetail(String turnDetail) {
		this.turnDetail = turnDetail;
	}

	
}
