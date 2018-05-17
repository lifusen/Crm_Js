package cn.itproject.crm.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import cn.itproject.crm.bean.Message;
import cn.itproject.crm.dao.MessageDao;
import cn.itproject.crm.db.impl.BaseDaoSupport;

/**
 * 公告消息DAO实现类
 * @author MrLiu
 *
 */
@Repository
public class MessageDaoImpl extends BaseDaoSupport<Message> implements MessageDao{

	@SuppressWarnings("unchecked")
	@Override
	public List<Message> getMessages(Integer type, Integer pageIndex,
			Integer pageSize) throws Exception {
		String hql = "from Message m where m.type = :type order by m.pubdate desc";
		return getSession().createQuery(hql)
				.setInteger("type", type)
				.setFirstResult((pageIndex-1)*pageSize)
				.setMaxResults(pageSize)
				.list();
	}

	@Override
	public Integer getMessagesCount(Integer type) throws Exception {
		String hql = "select count(m.id) from Message m where m.type = :type";
		Long count = (Long) getSession().createQuery(hql)
				.setInteger("type", type)
				.uniqueResult();
		return Integer.parseInt(String.valueOf(count));
	}

}
