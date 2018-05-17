package cn.itproject.crm.dao;

import java.util.List;

import cn.itproject.crm.bean.Message;
import cn.itproject.crm.db.BaseDao;

/**
 * 公告消息DAO接口
 * @author MrLiu
 *
 */
public interface MessageDao extends BaseDao<Message>{
	/**
	 * 根据类型获取消息的分页列表
	 * @param type
	 * @param pageIndex
	 * @param pageSize
	 * @return
	 * @throws Exception
	 */
	public List<Message> getMessages(Integer type,Integer pageIndex, Integer pageSize)throws Exception;
	
	/**
	 * 根据类型获取总行数 
	 * @param type
	 * @return
	 * @throws Exception
	 */
	public Integer getMessagesCount(Integer type)throws Exception;
}
