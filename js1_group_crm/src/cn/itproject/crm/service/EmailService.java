package cn.itproject.crm.service;

/**
 * Email发送服务
 * @author MrLiu
 *
 */
public interface EmailService {
	/**
	 * 给指定的邮箱发送文本消息
	 * @param email 邮箱
	 * @param subject 主题
	 * @param content 消息内容
	 * @throws Exception
	 */
	public void sendText(String email,String subject,String content) throws Exception;

	/**
	 * 给指定的邮箱发送HTML消息
	 * @param email 邮箱
	 * @param subject 主题
	 * @param content 消息内容
	 * @throws Exception
	 */
	public void sendHtml(String email,String subject,String content) throws Exception;
}
