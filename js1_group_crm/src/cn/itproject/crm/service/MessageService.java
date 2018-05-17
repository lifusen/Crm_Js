package cn.itproject.crm.service;

import java.util.List;

import cn.itproject.crm.bean.Message;

/**
 * 公告消息业务接口
 * @author MrLiu
 *
 */
public interface MessageService extends BaseService<Message>{

	/**
	 * 根据类获取分页列表数据
	 * @param pageIndex
	 * @param pageSize
	 * @return
	 * @throws Exception
	 */
	List<Message> getMessages(Integer type,Integer pageIndex, Integer pageSize) throws Exception;

	/**
	 * 根据类型获取总行数
	 * @param type
	 * @return
	 * @throws Exception
	 */
	Integer getMessagesCount(Integer type) throws Exception;
	
	/**
	 * 保存消息
	 * @param message
	 * @throws Exception
	 */
	void addMessage(Message message) throws Exception;
	
	/**
	 * 修改消息
	 * @param message
	 * @throws Exception
	 */
	void updateMessage(Message message) throws Exception;
	
	/**
	 * 删除消息
	 * @param id
	 * @throws Exception
	 */
	void deleteMessage(Integer id) throws Exception;

}
