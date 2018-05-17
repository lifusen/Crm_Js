package cn.itproject.crm.bean;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * 客户名单
 * 
 * @author MrLiu
 *
 */
@Entity
public class CustomerRoster implements Serializable {
	private static final long serialVersionUID = 2008508484415058838L;

	/**
	 * 添加客户的类型
	 * 
	 * @author MrLiu
	 */
	public static enum RosterState {
		old // 已经分配了
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@Column
	private String name; // 名称
	@Column
	private Integer count; // 总数
	@Column
	private Integer state; // null表示刚导入的，0表示已经分配,1:表示成功数量为0(该名单全部导入失败)
	@Column
	private Integer employeeId; // 录入员id
	@Column
	private String employeeName; // 录入人名称
	@Column
	private String fileName; // 导入时的文件名称
	@Column
	private String newFileName; // 修改后的新文件名称
	@Column
	private Integer successCount; // 成功的数量
	@Column
	private Integer failureCount; // 失败的数量
	@Column
	@Temporal(TemporalType.TIMESTAMP)
	private Date createTime = new Date(); // 创建时间
	
	@Column
	private Integer companyId;				//公司ID

	public CustomerRoster() {
		super();
	}

	/**
	 * 批量导入时记录名单信息
	 * 
	 * @param name
	 * @param count
	 * @param employeeId
	 * @param employeeName
	 * @param fileName
	 * @param newFileName
	 */
	public CustomerRoster(String name, Integer count, Integer employeeId, String employeeName, String fileName,
			String newFileName) {
		super();
		this.name = name;
		this.count = count;
		this.employeeId = employeeId;
		this.employeeName = employeeName;
		this.fileName = fileName;
		this.newFileName = newFileName;
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

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	public Integer getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(Integer employeeId) {
		this.employeeId = employeeId;
	}

	public String getEmployeeName() {
		return employeeName;
	}

	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getNewFileName() {
		return newFileName;
	}

	public void setNewFileName(String newFileName) {
		this.newFileName = newFileName;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Integer getSuccessCount() {
		return successCount;
	}

	public void setSuccessCount(Integer successCount) {
		this.successCount = successCount;
	}

	public Integer getFailureCount() {
		return failureCount;
	}

	public void setFailureCount(Integer failureCount) {
		this.failureCount = failureCount;
	}

	public Integer getCompanyId() {
		return companyId;
	}

	public void setCompanyId(Integer companyId) {
		this.companyId = companyId;
	}

	@Override
	public String toString() {
		return "CustomerRoster [id=" + id + ", name=" + name + ", count=" + count + ", state=" + state + ", employeeId="
				+ employeeId + ", employeeName=" + employeeName + ", fileName=" + fileName + ", newFileName="
				+ newFileName + ", successCount=" + successCount + ", failureCount=" + failureCount + ", createTime="
				+ createTime + "]";
	}
}
