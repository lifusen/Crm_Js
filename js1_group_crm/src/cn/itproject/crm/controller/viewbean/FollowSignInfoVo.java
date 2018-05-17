package cn.itproject.crm.controller.viewbean;

import java.io.Serializable;

/**
 * 跟进的签单客户信息
 * @author MrLiu
 *
 */
public class FollowSignInfoVo implements Serializable{
	private static final long serialVersionUID = -3569956859159318277L;
	private Integer followSignCustomerId;					// 跟进的签单客户ID
	private String followSignCustomerContractNO;			// 跟进的签单客户合同编号
	private String followSignCustomerName;					// 跟进的签单客户的名称
	private Integer followSignWarrantCompanyId;				// 跟进的签单客户的权证中心ID
	private Integer followSignWarrantDepartmentId;			// 跟进的签单客户的权证部门ID
	private Integer followSignWarrantEmployeeId;			// 跟进的签单客户的权证专员ID
	private Integer followCustomerId;						// 跟进的客户ID
	private Integer followBizCompanyId;						// 客户的业务公司ID
	private Integer followBizDepartmentId;					// 客户的业务部门ID
	private Integer followBizEmployeeId;					// 客户的业务员ID
	public Integer getFollowSignCustomerId() {
		return followSignCustomerId;
	}
	public void setFollowSignCustomerId(Integer followSignCustomerId) {
		this.followSignCustomerId = followSignCustomerId;
	}
	public String getFollowSignCustomerContractNO() {
		return followSignCustomerContractNO;
	}
	public void setFollowSignCustomerContractNO(String followSignCustomerContractNO) {
		this.followSignCustomerContractNO = followSignCustomerContractNO;
	}
	public String getFollowSignCustomerName() {
		return followSignCustomerName;
	}
	public void setFollowSignCustomerName(String followSignCustomerName) {
		this.followSignCustomerName = followSignCustomerName;
	}
	public Integer getFollowSignWarrantCompanyId() {
		return followSignWarrantCompanyId;
	}
	public void setFollowSignWarrantCompanyId(Integer followSignWarrantCompanyId) {
		this.followSignWarrantCompanyId = followSignWarrantCompanyId;
	}
	public Integer getFollowSignWarrantDepartmentId() {
		return followSignWarrantDepartmentId;
	}
	public void setFollowSignWarrantDepartmentId(Integer followSignWarrantDepartmentId) {
		this.followSignWarrantDepartmentId = followSignWarrantDepartmentId;
	}
	public Integer getFollowSignWarrantEmployeeId() {
		return followSignWarrantEmployeeId;
	}
	public void setFollowSignWarrantEmployeeId(Integer followSignWarrantEmployeeId) {
		this.followSignWarrantEmployeeId = followSignWarrantEmployeeId;
	}
	public Integer getFollowCustomerId() {
		return followCustomerId;
	}
	public void setFollowCustomerId(Integer followCustomerId) {
		this.followCustomerId = followCustomerId;
	}
	public Integer getFollowBizCompanyId() {
		return followBizCompanyId;
	}
	public void setFollowBizCompanyId(Integer followBizCompanyId) {
		this.followBizCompanyId = followBizCompanyId;
	}
	public Integer getFollowBizDepartmentId() {
		return followBizDepartmentId;
	}
	public void setFollowBizDepartmentId(Integer followBizDepartmentId) {
		this.followBizDepartmentId = followBizDepartmentId;
	}
	public Integer getFollowBizEmployeeId() {
		return followBizEmployeeId;
	}
	public void setFollowBizEmployeeId(Integer followBizEmployeeId) {
		this.followBizEmployeeId = followBizEmployeeId;
	}
	@Override
	public String toString() {
		return "FollowSignInfoVo [followSignCustomerId=" + followSignCustomerId + ", followSignCustomerContractNO="
				+ followSignCustomerContractNO + ", followSignCustomerName=" + followSignCustomerName
				+ ", followSignWarrantCompanyId=" + followSignWarrantCompanyId + ", followSignWarrantDepartmentId="
				+ followSignWarrantDepartmentId + ", followSignWarrantEmployeeId=" + followSignWarrantEmployeeId
				+ ", followCustomerId=" + followCustomerId + ", followBizCompanyId=" + followBizCompanyId
				+ ", followBizDepartmentId=" + followBizDepartmentId + ", followBizEmployeeId=" + followBizEmployeeId
				+ "]";
	}
}
