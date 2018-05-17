package cn.itproject.crm.dao.impl;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import cn.itproject.crm.bean.Customer;
import cn.itproject.crm.bean.CustomerFollow;
import cn.itproject.crm.bean.CustomerState;
import cn.itproject.crm.dao.CustomerDao;
import cn.itproject.crm.db.impl.BaseDaoSupport;
import cn.itproject.crm.util.Constant;

/**
 * 客户DAO实现类
 * @author MrLiu
 *
 */
@Repository
public class CustomerDaoImpl extends BaseDaoSupport<Customer> implements CustomerDao{

	@Override
	public List<Customer> getListByState(Integer pageIndex, Integer pageSize,
			Integer state,Integer eId) throws Exception {
		String hql = "";
		Query query = null;
		if(state==null){
			hql = "from Customer where state is null and addType != 1";
			if (eId != null) {
				hql = hql+" and employeeId="+eId;
			}
			query = getSession().createQuery(hql);
			query.setFirstResult(pageSize * (pageIndex - 1));
			query.setMaxResults(pageSize);
		}else {
			hql = "from Customer where state = :state and addType != 1";
			if (eId != null) {
				hql = hql+" and employeeId="+eId;
			}
			query = getSession().createQuery(hql);
			query.setInteger("state", state);
			query.setFirstResult(pageSize * (pageIndex - 1));
			query.setMaxResults(pageSize);
		}
		return query.list();
	}

	@Override
	public void updateState(Integer cId, Integer state) throws Exception {
		String hql = "update Customer set state = :state where id = :id";
		Query query = getSession().createQuery(hql);
		query.setInteger("state", state);
		query.setInteger("id", cId);
		query.executeUpdate();
	}
	@Override
	public void updateVisibility(Integer cId, Integer visibility) throws Exception {
		String hql = "update Customer set visibility = :visibility where id = :id";
		Query query = getSession().createQuery(hql);
		query.setInteger("visibility", visibility);
		query.setInteger("id", cId);
		query.executeUpdate();
	}

	@Override
	public List<Customer> getAllotToEmployeeList(Integer roleId, Integer dId,Integer pageIndex, Integer pageSize) throws Exception {
		String hql = "from Customer where id in(select customer.id from AllotCustomer "
				+ "where staff=null ";
		if (roleId!=null) {
			hql = hql+ " and role.id = :rId";
		}
		if (dId!=null) {
			hql = hql+ " and department.id = :dId";
		}
		hql+=")";
		Query query = getSession().createQuery(hql);
		if (roleId!=null) {
			query.setInteger("rId", roleId);
		}
		if (dId!=null) {
			query.setInteger("dId", dId);
		}
		query.setFirstResult(pageSize * (pageIndex - 1));
		query.setMaxResults(pageSize);
		return query.list();
	}

	@Override
	public List<Customer> getNewList(Integer pageIndex, Integer pageSize,
			Integer[] id,String keyStr) throws Exception {
		String hql = "from Customer where state = 2 and id in(select customer.id from AllotCustomer ";
		
		if (id==null) {
			hql = hql + "where staff.id != null)";
		}else {
			String idstr = "";
			for (int i = 0; i < id.length; i++) {
				idstr = idstr+id[i]+",";
			}
			idstr = idstr.substring(0,idstr.lastIndexOf(","));
			hql = hql + "where staff.id in ("+idstr+"))";
		}
		
		if (keyStr!=null && !keyStr.equals("")) {
			hql = hql+" and (name like :name or loanType like :loanType or phone like :phone or customerLevel like :customerLevel)";
		}
		Query query = getSession().createQuery(hql);
		query.setFirstResult(pageSize * (pageIndex - 1));
		query.setMaxResults(pageSize);
		if (keyStr!=null && !keyStr.equals("")) {
			query.setString("loanType", "%"+keyStr+"%");
			query.setString("phone", "%"+keyStr+"%");
			query.setString("customerLevel", "%"+keyStr+"%");
			query.setString("name", "%"+keyStr+"%");
		}
		return query.list();
	}

	@Override
	public Integer getCountByState(Integer state,Integer eId)throws Exception {
		if(state==null){
			String hql = "select count(o) from Customer o where state is null and addType != 1";
			if (eId != null) {
				hql = hql+" and employeeId="+eId;
			}
			Query query = getSession().createQuery(hql);
			return Integer.parseInt(String.valueOf(query.uniqueResult()));
		}else {
			String hql = "select count(o) from Customer o where state = :state and addType != 1";
			if (eId != null) {
				hql = hql+" and employeeId="+eId;
			}
			Query query = getSession().createQuery(hql);
			query.setInteger("state", state);
			return Integer.parseInt(String.valueOf(query.uniqueResult()));
		}
	}

	@Override
	public Integer getAllotToEmployeeCount(Integer roleId, Integer dId)
			throws Exception {
		String hql = "select count(o) from Customer o where id in(select customer.id from AllotCustomer "
				+ "where staff=null";
		if (roleId!=null) {
			hql = hql+" and role.id = :rId ";
		}
		if (dId!=null) {
			hql = hql+" and department.id = :dId";
		}
		hql+=")";
		Query query = getSession().createQuery(hql);
		if (roleId!=null) {
			query.setInteger("rId", roleId);
		}
		if (dId!=null) {
			query.setInteger("dId", dId);
		}
		return Integer.parseInt(String.valueOf(query.uniqueResult()));
	}

	@Override
	public Integer getNewOrderCount(Integer[] id,String keyStr) throws Exception {
		String hql = "select count(o) from Customer o where state = 2 and id in(select customer.id from AllotCustomer ";
		if (id==null) {
			hql = hql + "where staff.id != null)";
		}else {
			String idstr = "";
			for (int i = 0; i < id.length; i++) {
				idstr = idstr+id[i]+",";
			}
			idstr = idstr.substring(0,idstr.lastIndexOf(","));
			hql = hql + "where staff.id in( "+idstr+"))";
		}
		
		if (keyStr!=null && !keyStr.equals("")) {
			hql = hql+" and (name like :name or loanType like :loanType or phone like :phone or customerLevel like :customerLevel)";
		}
		Query query = getSession().createQuery(hql);
		if (keyStr!=null && !keyStr.equals("")) {
			query.setString("loanType", "%"+keyStr+"%");
			query.setString("phone", "%"+keyStr+"%");
			query.setString("customerLevel", "%"+keyStr+"%");
			query.setString("name", "%"+keyStr+"%");
		}
		return Integer.parseInt(String.valueOf(query.uniqueResult()));
	}

	
	@Override
	@Deprecated
	public List<Customer> getAssignedOrderList(Integer pageIndex,
			Integer pageSize, Integer[] id,String keyStr) throws Exception {
		String hql = "from Customer where id in(select customer.id from CustomerHistoryState where nowState in (1,2)";
		
		if(id!=null){
			hql = hql + " and operationEmployee.id in (:id) ";
		}
		hql = hql + " ) ";
		
		if (keyStr!=null && !keyStr.equals("")) {
			hql = hql+"and (name like :name or loanType like :loanType or phone like :phone or customerLevel like :customerLevel)";
		}
		
		Query query = getSession().createQuery(hql);
		if(id!=null){
			query.setParameterList("id", id);
		}
		query.setFirstResult(pageSize * (pageIndex - 1));
		query.setMaxResults(pageSize);
		if (keyStr!=null && !keyStr.equals("")) {
			query.setString("loanType", "%"+keyStr+"%");
			query.setString("phone", "%"+keyStr+"%");
			query.setString("customerLevel", "%"+keyStr+"%");
			query.setString("name", "%"+keyStr+"%");
		}
		return query.list();
	}

	@Override
	public Integer getAssignedOrderCount(Integer[] id,String keyStr) throws Exception {
		String hql = "select count(o) from Customer o where id in(select customer.id from CustomerHistoryState where nowState in (1,2)";
		if(id!=null){
			hql = hql + " and operationEmployee.id in(:id) ";
		}
		hql = hql + " ) ";
		
		if (keyStr!=null && !keyStr.equals("")) {
			hql = hql+"and (name like :name or loanType like :loanType or phone like :phone or customerLevel like :customerLevel)";
		}
		Query query = getSession().createQuery(hql);
		if(id!=null){
			query.setParameterList("id", id);
		}
		if (keyStr!=null && !keyStr.equals("")) {
			query.setString("loanType", "%"+keyStr+"%");
			query.setString("phone", "%"+keyStr+"%");
			query.setString("customerLevel", "%"+keyStr+"%");
			query.setString("name", "%"+keyStr+"%");
		}
		return Integer.parseInt(String.valueOf(query.uniqueResult()));
	}

	@Override
	@Deprecated
	public List<Customer> getReceiveOrderList(Integer pageIndex,
			Integer pageSize, Integer[] id,String keyStr) throws Exception {
		String hql = "from Customer where id in(select customer.id from CustomerHistoryState where "
				+ "nowState in (2)";
		if(id!=null){
			hql = hql + " and nowEmployee.id in(:id) ";
		}
		hql = hql + " ) ";
		if (keyStr!=null && !keyStr.equals("")) {
			hql = hql+"and (name like :name or loanType like :loanType or phone like :phone or customerLevel like :customerLevel)";
		}
		Query query = getSession().createQuery(hql);
		query.setFirstResult(pageSize * (pageIndex - 1));
		query.setMaxResults(pageSize);
		if(id!=null){
			query.setParameterList("id", id);
		}
		if (keyStr!=null && !keyStr.equals("")) {
			query.setString("loanType", "%"+keyStr+"%");
			query.setString("phone", "%"+keyStr+"%");
			query.setString("customerLevel", "%"+keyStr+"%");
			query.setString("name", "%"+keyStr+"%");
		}
		return query.list();
	}

	@Override
	public Integer getReceiveOrderCount(Integer[] id,String keyStr) throws Exception {
		String hql = "select count(o) from Customer o where o.id in(select "
				+ "customer.id from CustomerHistoryState where nowState in (2)";
		if(id!=null){
			hql = hql + " and nowEmployee.id in(:id) ";
		}
		hql = hql + " ) ";
		if (keyStr!=null && !keyStr.equals("")) {
			hql = hql+"and (name like :name or loanType like :loanType or phone like :phone and customerLevel like :customerLevel)";
		}
		Query query = getSession().createQuery(hql);
		if(id!=null){
			query.setParameterList("id", id);
		}
		if (keyStr!=null && !keyStr.equals("")) {
			query.setString("loanType", "%"+keyStr+"%");
			query.setString("phone", "%"+keyStr+"%");
			query.setString("customerLevel", "%"+keyStr+"%");
			query.setString("name", "%"+keyStr+"%");
		}
		return Integer.parseInt(String.valueOf(query.uniqueResult()));
	}

	@Override
	@Deprecated
	public List<Customer> getValidCustomer(Integer pageIndex, Integer pageSize,
			Integer[] id,String keyStr) throws Exception {
		String hql = "from Customer where id in(select customer.id from CustomerHistoryState where "
				+ "nowState in (2,8,3)";
		if(id!=null){
			hql = hql + " and nowEmployee.id in(:id)  ";
		}
		hql = hql + " ) ";
		
		if (keyStr!=null && !keyStr.equals("")) {
			hql = hql+"and (name like :name or loanType like :loanType or phone like :phone and customerLevel like :customerLevel)";
		}
		
		Query query = getSession().createQuery(hql);
		query.setFirstResult(pageSize * (pageIndex - 1));
		query.setMaxResults(pageSize);
		if(id!=null){
			query.setParameterList("id", id);
		}
		if (keyStr!=null && !keyStr.equals("")) {
			query.setString("loanType", "%"+keyStr+"%");
			query.setString("phone", "%"+keyStr+"%");
			query.setString("customerLevel", "%"+keyStr+"%");
			query.setString("name", "%"+keyStr+"%");
		}
		return query.list();
	}

	@Override
	public Integer getValidCustomer(Integer[] id,String keyStr) throws Exception {
		String hql = "select count(o) from Customer o where o.id in(select "
				+ "customer.id from CustomerHistoryState where nowState in (2,8,3)";
		if(id!=null){
			hql = hql + " and nowEmployee.id in(:id) ";
		}
		hql = hql + " ) ";
		

		if (keyStr!=null && !keyStr.equals("")) {
			hql = hql+"and (name like :name or loanType like :loanType or phone like :phone and customerLevel like :customerLevel)";
		}
		Query query = getSession().createQuery(hql);
		if(id!=null){
			query.setParameterList("id", id);
		}
		if (keyStr!=null && !keyStr.equals("")) {
			query.setString("loanType", "%"+keyStr+"%");
			query.setString("phone", "%"+keyStr+"%");
			query.setString("customerLevel", "%"+keyStr+"%");
			query.setString("name", "%"+keyStr+"%");
		}
		return Integer.parseInt(String.valueOf(query.uniqueResult()));
	}

	@Override
	public void addCustomerCallCount(Integer customerId) throws Exception {
		String hql = "update Customer c set callCount = callCount+1 where c.id = :customerId";
		getSession().createQuery(hql)
			.setInteger("customerId", customerId)
			.executeUpdate();
	}

	@Override
	public void addCustomerVisitCount(Integer customerId) throws Exception {
		String hql = "update Customer c set visitCount = visitCount+1 where c.id = :customerId";
		getSession().createQuery(hql)
			.setInteger("customerId", customerId)
			.executeUpdate();
	}

	@Override
	public Integer getCountByAddType(int ordinal,int id) throws Exception {
		String hql = "select count(o) from Customer o where o.addType = "
				+ ":addType and customerRoster.id = :id and state = "+CustomerState.unassigned.ordinal();
		Query query = getSession().createQuery(hql);
		query.setInteger("addType", ordinal);
		query.setInteger("id",id );
		return Integer.parseInt(String.valueOf(query.uniqueResult()));
	}

	@Override
	public List<Customer> getListByAddType(Integer rId,Integer count) throws Exception {
		String hql = "from Customer where customerRoster.id = :id and state = "+CustomerState.unassigned.ordinal();
		Query query = getSession().createQuery(hql);
		if (count!=null && count>0) {
			query.setFirstResult(0);
			query.setMaxResults(count);
		}
		query.setInteger("id", rId);
		return query.list();
	}

	@Override
	@Deprecated
	public List<Customer> getApplyNullity(Integer pageIndex, Integer pageSize,
			Integer[] id, String keyStr) throws Exception {

		String hql = "from Customer where id in(select customer.id from CustomerHistoryState where "
				+ "nowState in (9)";
		if(id!=null){
			hql = hql + " and nowEmployee.id in(:id)  ";
		}
		hql = hql + " ) ";
		
		if (keyStr!=null && !keyStr.equals("")) {
			hql = hql+"and (name like :name or loanType like :loanType or phone like :phone and customerLevel like :customerLevel)";
		}
		
		Query query = getSession().createQuery(hql);
		query.setFirstResult(pageSize * (pageIndex - 1));
		query.setMaxResults(pageSize);
		if(id!=null){
			query.setParameterList("id", id);
		}
		if (keyStr!=null && !keyStr.equals("")) {
			query.setString("loanType", "%"+keyStr+"%");
			query.setString("phone", "%"+keyStr+"%");
			query.setString("customerLevel", "%"+keyStr+"%");
			query.setString("name", "%"+keyStr+"%");
		}
		return query.list();
	}

	@Override
	public Integer getApplyNullityCount(Integer[] ids, String keyStr)
			throws Exception {
		String hql = "select count(o) from Customer o where o.id in(select "
				+ "customer.id from CustomerHistoryState where nowState in (9)";
		if(ids!=null){
			hql = hql + " and nowEmployee.id in(:id) ";
		}
		hql = hql + " ) ";
		

		if (keyStr!=null && !keyStr.equals("")) {
			hql = hql+"and (name like :name or loanType like :loanType or phone like :phone and customerLevel like :customerLevel)";
		}
		Query query = getSession().createQuery(hql);
		if(ids!=null){
			query.setParameterList("id", ids);
		}
		if (keyStr!=null && !keyStr.equals("")) {
			query.setString("loanType", "%"+keyStr+"%");
			query.setString("phone", "%"+keyStr+"%");
			query.setString("customerLevel", "%"+keyStr+"%");
			query.setString("name", "%"+keyStr+"%");
		}
		return Integer.parseInt(String.valueOf(query.uniqueResult()));
	}

	@Deprecated
	@Override
	public Integer getIsFollow(Integer customerId) throws Exception {
		String hql = "select c.isFollow from Customer c where c.id = :customerId";
		return (Integer) getSession().createQuery(hql)
			.setInteger("customerId", customerId)
			.uniqueResult();
	}

	@Deprecated
	@Override
	public void updateFollowState(Integer customerId) throws Exception {
		String hql = "update Customer c set isFollow = 0 where c.id = :customerId";
		getSession().createQuery(hql)
			.setInteger("customerId", customerId)
			.executeUpdate();
	}

	@Override
	public String getSignState(Integer customerId) throws Exception {
		String hql = "select c.signState from Customer c where c.id = :customerId";
		return (String) getSession().createQuery(hql)
				.setInteger("customerId", customerId)
				.uniqueResult();
	}

	@Override
	public void updateSignState(Integer customerId, String signState)
			throws Exception {
		String hql = "update Customer c set c.signState = :signState where c.id = :customerId";
		getSession().createQuery(hql)
			.setString("signState", signState)
			.setInteger("customerId", customerId)
			.executeUpdate();
	}

	@Override
	public List<Customer> getByState(Integer pageIndex, Integer pageSize,
			Integer[] id, String keyStr, Integer[] states) throws Exception {
		String hql = "from Customer where id in(select customer.id from CustomerHistoryState where "
				+ "nowState in (:states)";
		if(id!=null){
			hql = hql + " and nowEmployee.id in(:id)  ";
		}
		hql = hql + " ) ";
		
		if (keyStr!=null && !keyStr.equals("")) {
			hql = hql+"and (name like :name or loanType like :loanType or phone like :phone and customerLevel like :customerLevel)";
		}
		
		Query query = getSession().createQuery(hql);
		query.setFirstResult(pageSize * (pageIndex - 1));
		query.setMaxResults(pageSize);
		query.setParameterList("states", states);
		if(id!=null){
			query.setParameterList("id", id);
		}
		if (keyStr!=null && !keyStr.equals("")) {
			query.setString("loanType", "%"+keyStr+"%");
			query.setString("phone", "%"+keyStr+"%");
			query.setString("customerLevel", "%"+keyStr+"%");
			query.setString("name", "%"+keyStr+"%");
		}
		return query.list();
	}

	@Override
	public Integer getCountByState(Integer[] ids, String keyStr,
			Integer[] states) throws Exception {
		String hql = "select count(o) from Customer o where o.id in(select "
				+ "customer.id from CustomerHistoryState where nowState in (:states)";
		if(ids!=null){
			hql = hql + " and nowEmployee.id in(:id) ";
		}
		hql = hql + " ) ";
		

		if (keyStr!=null && !keyStr.equals("")) {
			hql = hql+"and (name like :name or loanType like :loanType or phone like :phone and customerLevel like :customerLevel)";
		}
		
		Query query = getSession().createQuery(hql);
		
		query.setParameterList("states", states);
		if(ids!=null){
			query.setParameterList("id", ids);
		}
		if (keyStr!=null && !keyStr.equals("")) {
			query.setString("loanType", "%"+keyStr+"%");
			query.setString("phone", "%"+keyStr+"%");
			query.setString("customerLevel", "%"+keyStr+"%");
			query.setString("name", "%"+keyStr+"%");
		}
		return Integer.parseInt(String.valueOf(query.uniqueResult()));
	}

	@Override
	public void addImportCustomer(Customer customer) throws Exception {
		String sql = "insert into Customer (name,phone,otherInfo,addType,employee) values()";
	}

	@Override
	public void batchUpdate(Integer companyId,Integer dId,List<Integer> ids, int state) throws Exception {
		String hql = "update Customer c set c.updateTime = Now(),"+(companyId==null?"":"c.companyId="+companyId+",")
				+ "c.department.id = :departmentId, c.state = :state where c.id in(:ids)";
		Query query = getSession().createQuery(hql);
		query.setInteger("departmentId", dId);
		query.setInteger("state", state);
		query.setParameterList("ids", ids);
		System.out.println(hql);
		query.executeUpdate();
	}

	@Override
	public Customer getBasicCustomer(Integer id) throws Exception {
		String hql = "select c from Customer c where c.id = :id";
		return (Customer) getSession().createQuery(hql)
			.setInteger("id", id)
			.uniqueResult();
	}

	@Override
	public List<Integer> getListByFllowDate(Integer day) throws Exception {
		/**
		 * 1.排除签单、放款、退单退件、审核无效、无效客户
		 * 2.没在公共池中
		 * 3.不是自留的客户
		 * 4.当前时间和跟进时间相差Constant.RELEASE_DAY天
		 */
		/*String sql = "select id "				by Sword  2,3,5,6,8 状态客户全部需要流入公共池
				+ "from customer where DATEDIFF(NOW(),fllowDate)>"+day+" "
						+ "and publicView is null and state not in(4,5,6,7,8,10)"
						+ " and (visibility is null or visibility = 1)";*/
		// 2016.12.16 修改释放客户进入公共池依赖时间列由 fllowDate 变更为 updateTime
		/*
		String sql = "select id "
				+ "from customer cus where state in(1,2,3,5,6,8)"
				+ " and (visibility is null or visibility = 1)"
				+ " and DATEDIFF(NOW(),updateTime)>"+day;
		*/
		String sql = "select cus.id "
				+ "from customer cus LEFT JOIN config con ON con.name='公共池自动释放时间' "
				+ "where cus.state in(1,2,3,5,6,8) "
				+ "and (cus.visibility is null or cus.visibility = 1) "
				+ "and DATEDIFF(NOW(),cus.updateTime)>con.value";
		Query query = getSession().createSQLQuery(sql);
		return query.list();
	}

	@Override
	public Integer getCustomerState(Integer customerId) throws Exception {
		String hql = "select c.state from Customer c where c.id = :cId";
		return (Integer) getSession().createQuery(hql)
				.setInteger("cId", customerId)
				.uniqueResult();
	}

	@Override
	public Integer getCountByEId(Integer id) throws Exception {
		String hql = "select count(c) from Customer c where "
				+ "c.employee.id = :employeeId and c.state != "+
				CustomerState.delete.ordinal() + " and c.state != "+CustomerState.commonPool.ordinal()
				 + " and c.state != "+CustomerState.auditInvalid.ordinal()+" and c.state !="+CustomerState.invalid.ordinal();
		Query query = getSession().createQuery(hql);
		query.setInteger("employeeId", id);
		return Integer.parseInt(String.valueOf(query.uniqueResult()));
	}

	@Override
	public void updateFlowDate(Integer customerId) throws Exception {
		String hql = "update Customer c set c.fllowDate = now() where c.id = :customerId";
		getSession().createQuery(hql)
			.setInteger("customerId", customerId)
			.executeUpdate();
	}

	@Override
	public void updateTime(Integer customerId) throws Exception {
		String hql = "update Customer c set c.updateTime = now() where c.id = :customerId";
		getSession().createQuery(hql)
			.setInteger("customerId", customerId)
			.executeUpdate();
	}

	@Override
	public Integer getCountByPhoneAndState(String phone, List<Integer> state)
			throws Exception {
		String hql = "select count(c) from Customer c where "
				+ "c.state in(:state) and c.phone = :phone";
		Query query = getSession().createQuery(hql);
		query.setParameterList("state", state);
		query.setString("phone", phone);
		return Integer.parseInt(String.valueOf(query.uniqueResult()));
	}

	@Override
	public void updateCustomerLevel(Integer id, String level) throws Exception {
		String hql = "update Customer set customerLevel = :level where id = :id";
		getSession().createQuery(hql)
			.setString("level", level)
			.setInteger("id", id)
			.executeUpdate();
	}

	@Override
	public void updateAttentionLevel(Integer id, String level) throws Exception {
		String hql = "update Customer set attentionLevel = :level where id = :id";
		getSession().createQuery(hql)
			.setString("level", level)
			.setInteger("id", id)
			.executeUpdate();
	}

	@Override
	public Integer getReleaseCountById(Integer id, Date date) throws Exception{
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		String hql = "select count(c) from Customer c where "
				+ "c.state in(:state) and c.employee.id = :employeeId"
				+ " and c.releaseDate = :releaseDate";
		Query query = getSession().createQuery(hql);
		query.setParameterList("state",Arrays.asList(
				CustomerState.commonPool.ordinal(), 
				CustomerState.auditInvalid.ordinal()));
		query.setInteger("employeeId", id);
		query.setString("releaseDate", format.format(date));
		return Integer.parseInt(String.valueOf(query.uniqueResult()));
	}

	@Override
	public Integer getReceiveCountById(Integer id, Date date) throws Exception {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		String hql = "select count(c) from Customer c where "
				+ " c.employee.id = :employeeId"
				+ " and c.receiveDate = :receiveDate";
		Query query = getSession().createQuery(hql);
		query.setInteger("employeeId", id);
		query.setString("receiveDate", format.format(date));
		return Integer.parseInt(String.valueOf(query.uniqueResult()));
	}

	@Override
	public void updateLastRemindInfo(CustomerFollow customerFollow) throws Exception {
		String hql = "update Customer set lastFollowId = :lastFollowId,"
				+ "lastRemindContent = :lastRemindContent,"
				+ "lastFollowType = :lastFollowType,"
				+ "lastRemindTime = :lastRemindTime"
				+ " where id = :id";
		getSession().createQuery(hql)
			.setInteger("lastFollowId", customerFollow.getId())
			.setString("lastRemindContent", customerFollow.getRemindContent())
			.setInteger("lastFollowType", customerFollow.getType())
			.setDate("lastRemindTime", customerFollow.getRemindTime())
			.setInteger("id", customerFollow.getCustomer().getId())
			.executeUpdate();
	}
	
	@Override
	public void updateLastRemindTime(Integer customerId, Date remindTime) throws Exception {
		String hql = "update Customer set lastRemindTime = :lastRemindTime where id = :id";
		getSession().createQuery(hql)
			.setDate("lastRemindTime", remindTime)
			.setInteger("id", customerId)
			.executeUpdate();
	}
	
	@Override
	public void updateWarrantLastRemindInfo(CustomerFollow customerFollow) throws Exception {
		String hql = "update Customer set lastWarrantFollowId = :lastWarrantFollowId,"
				+ "lastWarrantRemindContent = :lastWarrantRemindContent,"
				+ "lastWarrantFollowType = :lastWarrantFollowType,"
				+ "lastWarrantRemindTime = :lastWarrantRemindTime"
				+ " where id = :id";
		getSession().createQuery(hql)
			.setInteger("lastWarrantFollowId", customerFollow.getId())
			.setString("lastWarrantRemindContent", customerFollow.getRemindContent())
			.setInteger("lastWarrantFollowType", customerFollow.getType())
			.setDate("lastWarrantRemindTime", customerFollow.getRemindTime())
			.setInteger("id", customerFollow.getCustomer().getId())
			.executeUpdate();
	}

	@Override
	public void updateListCourse(Integer customerId, Integer listCourse) throws Exception {
		String hql = "update Customer set listCourse = :listCourse where id = :id";
		getSession().createQuery(hql)
			.setInteger("listCourse", listCourse)
			.setInteger("id", customerId)
			.executeUpdate();
	}

	@Override
	public Integer getValidCustomer(Integer eId) throws Exception {
		String sql = "SELECT count(c.id) FROM customer c"
				+ " LEFT JOIN employee e ON c.employeeId=e.id"
				+ " where e.id=:eId AND c.state in (2,3,4,5,6,7)";
		Query query = getSession().createSQLQuery(sql);
		query.setInteger("eId", eId);
		return Integer.parseInt(String.valueOf(query.uniqueResult()));
	}

}
