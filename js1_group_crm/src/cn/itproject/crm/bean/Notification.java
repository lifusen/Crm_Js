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
 * 消息通知
 * @author jianghan
 *
 */
@Entity
public class Notification implements Serializable{
	private static final long serialVersionUID = -3933500475788845521L;
	/**签单移交**/
	public static final Integer TYPE_SIGN_TRANSFER = 1;
	/**签单客户跟进**/
	public static final Integer TYPE_SIGN_FOLLOW = 2;

	/**签单移交显示字符串**/
	public static final String TYPE_SIGN_TRANSFER_STRING = "移交";
	/**签单客户跟进显示字符串**/
	public static final String TYPE_SIGN_FOLLOW_STRING = "客户跟进";
	
	/**操作类型,业务员发给权证**/
	public static final Integer OPERATION_TYPE_SALESMAN_TO_WARRANT = 1;
	/**操作类型,权证人员发给业务员**/
	public static final Integer OPERATION_TYPE_TO_WARRANT_SALESMAN = 2;
	/**操作类型,权证人员发给权证人员**/
	public static final Integer OPERATION_TYPE_WARRANT_TO_WARRANT = 3;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;						// 主键
	@Column
	private Integer senderId;				// 发送人ID
	@Column
	private Integer receiverId;				// 接收人ID
	@Column
	@Temporal(TemporalType.TIMESTAMP)
	private Date sendTime;					// 发送时间
	@Column
	private Integer type;					// 消息类型,1:签单移交,2:签单客户跟进(移交给权证部之后,权证人员与业务员沟通)
	@Column
	private Integer operationType;			// 操作类型,1:业务员-->权证人员,2:权证人员发给业务员,3:权证人员发给权证
	@Column
	private String content;					// 消息内容		
	@Column
	private Integer state;					// 状态,0:未读(新消息),1:已读(历史消息)
	@Column
	private Integer signCustomerId;			// 签单客户ID
	@Column
	private Integer customerId;				// 客户ID
	@Column
	private String typeName;				// 类型名称
	@Column
	private String phone;					// 客户手机号码
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getSenderId() {
		return senderId;
	}
	public void setSenderId(Integer senderId) {
		this.senderId = senderId;
	}
	public Integer getReceiverId() {
		return receiverId;
	}
	public void setReceiverId(Integer receiverId) {
		this.receiverId = receiverId;
	}
	public Date getSendTime() {
		return sendTime;
	}
	public void setSendTime(Date sendTime) {
		this.sendTime = sendTime;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public Integer getOperationType() {
		return operationType;
	}
	public void setOperationType(Integer operationType) {
		this.operationType = operationType;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Integer getState() {
		return state;
	}
	public void setState(Integer state) {
		this.state = state;
	}
	public Integer getSignCustomerId() {
		return signCustomerId;
	}
	public void setSignCustomerId(Integer signCustomerId) {
		this.signCustomerId = signCustomerId;
	}
	public Integer getCustomerId() {
		return customerId;
	}
	public void setCustomerId(Integer customerId) {
		this.customerId = customerId;
	}
	public String getTypeName() {
		return typeName;
	}
	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	@Override
	public String toString() {
		return "Notification [id=" + id + ", senderId=" + senderId + ", receiverId=" + receiverId + ", sendTime="
				+ sendTime + ", type=" + type + ", operationType=" + operationType + ", content=" + content + ", state="
				+ state + ", signCustomerId=" + signCustomerId + ", customerId=" + customerId + ", typeName=" + typeName
				+ ", phone=" + phone + "]";
	}
}
