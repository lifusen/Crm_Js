package cn.itproject.crm.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import cn.itproject.crm.bean.CustomerSource;
import cn.itproject.crm.dao.CustomerSourceDao;
import cn.itproject.crm.db.BaseDao;
import cn.itproject.crm.service.CustomerSourceService;

@Service
public class CustomerSourceServiceImpl extends BaseServiceSupport<CustomerSource> implements CustomerSourceService{
	@Resource
	private CustomerSourceDao customerSourceDao;
	@Override
	protected BaseDao<CustomerSource> getBaseDao() {
		return customerSourceDao;
	}
	@Override
	@Cacheable("customerSourceCache")
	public Map<Integer, String> getAllCustomerSourceMap() throws Exception {
		List<Map<String, Object>> list = customerSourceDao.getAllCustomerSourceIdAndName();
		return handleListToMapOfIdName(list);
	}
	@Override
	@Cacheable("customerSourceCache")
	public List<CustomerSource> getCustomerSourceList() throws Exception {
		return customerSourceDao.queryList();
	}
	@Override
	@Cacheable("customerSourceCache")
	public CustomerSource getCustomerSource(Integer id) throws Exception {
		return customerSourceDao.get(id);
	}
	@Override
	@CacheEvict(value="customerSourceCache",allEntries=true)
	public void addCustomerSource(CustomerSource customerSource) throws Exception {
		customerSourceDao.save(customerSource);
	}
	@Override
	@CacheEvict(value="customerSourceCache",allEntries=true)
	public void updateCustomerSource(CustomerSource customerSource) throws Exception {
		customerSourceDao.update(customerSource);
	}
	@Override
	@CacheEvict(value="customerSourceCache",allEntries=true)
	public void deleteCustomerSource(Integer id) throws Exception {
		customerSourceDao.remove(id);
	}

	@Override
	public List<Map<String, Object>> getCustomerSourcePie(String order) throws Exception {
		return customerSourceDao.getCustomerSourcePie(order);
	}
}
