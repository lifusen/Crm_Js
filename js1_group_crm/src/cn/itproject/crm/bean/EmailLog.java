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
 * 邮箱日志
 * @author MrLiu
 *
 */
@Entity
public class EmailLog implements Serializable{
	private static final long serialVersionUID = -1367793475277125801L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@Column
	private Integer senderId;		// 发送人ID
	@Column
	private String senderEmail;		// 发送的邮箱
	@Column
	private String receiveEmail;	// 收件的邮箱
	@Column
	private String subject;			// 主题
	@Column
	private String code;			// 验证码
	@Column
	private String content;			// 邮箱内容
	@Column
	@Temporal(TemporalType.TIMESTAMP)
	private Date sendTime;			// 发送时间
	@Column
	private Integer state;			// 1:成功/0:失败
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
	public String getSenderEmail() {
		return senderEmail;
	}
	public void setSenderEmail(String senderEmail) {
		this.senderEmail = senderEmail;
	}
	public String getReceiveEmail() {
		return receiveEmail;
	}
	public void setReceiveEmail(String receiveEmail) {
		this.receiveEmail = receiveEmail;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Date getSendTime() {
		return sendTime;
	}
	public void setSendTime(Date sendTime) {
		this.sendTime = sendTime;
	}
	public Integer getState() {
		return state;
	}
	public void setState(Integer state) {
		this.state = state;
	}
	@Override
	public String toString() {
		return "EmailLog [id=" + id + ", senderId=" + senderId + ", senderEmail=" + senderEmail + ", receiveEmail="
				+ receiveEmail + ", subject=" + subject + ", code=" + code + ", content=" + content + ", sendTime="
				+ sendTime + ", state=" + state + "]";
	}
}
