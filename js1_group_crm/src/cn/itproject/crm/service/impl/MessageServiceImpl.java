package cn.itproject.crm.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import cn.itproject.crm.bean.Message;
import cn.itproject.crm.dao.MessageDao;
import cn.itproject.crm.db.BaseDao;
import cn.itproject.crm.service.MessageService;

@Service
public class MessageServiceImpl extends BaseServiceSupport<Message> implements MessageService{
	@Resource
	private MessageDao messageDao;
	@Override
	protected BaseDao<Message> getBaseDao() {
		return messageDao;
	}
	@Override
	@Cacheable("messageCache")
	public List<Message> getMessages(Integer type,Integer pageIndex, Integer pageSize)
			throws Exception {
		return messageDao.getMessages(type, pageIndex, pageSize);
	}
	@Override
	@Cacheable("messageCache")
	public Integer getMessagesCount(Integer type) throws Exception {
		return messageDao.getMessagesCount(type);
	}
	@Override
	@CacheEvict(value="messageCache",allEntries=true)
	public void addMessage(Message message) throws Exception {
		messageDao.save(message);
	}
	@Override
	@CacheEvict(value="messageCache",allEntries=true)
	public void updateMessage(Message message) throws Exception {
		messageDao.update(message);
	}
	@Override
	@CacheEvict(value="messageCache",allEntries=true)
	public void deleteMessage(Integer id) throws Exception {
		messageDao.remove(id);
	}

}
