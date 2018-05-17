package cn.itproject.crm.bean;

/**
 * 客户状态
 * @author MrLiu
 */
public enum CustomerState {
	/**0:刚录入,未分配**/
	unassigned,	
	/**1:已分配给部门**/
	allocatedToDept,
	/**2:已分配给员工**/
	allocatedToEmp,
	/**3:跟进中**/
	follow,
	/**4:已签单**/
	sign,
	/**5:签单失败(退单)**/
	chargeback,
	/**6:签单失败(退件)**/
	reject,
	/**7:放款**/
	outLoan,
	/**8:申请无效(审核是否有效)**/
	auditInvalid,
	/**9:公共池*/
	commonPool,
	/**10:无效**/
	invalid,
	/**11:删除*/
	delete;
}
