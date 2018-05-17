package cn.itproject.crm.dao.impl;

import org.springframework.stereotype.Repository;

import cn.itproject.crm.bean.CustomerTurn;
import cn.itproject.crm.dao.CustomerTurnDao;
import cn.itproject.crm.db.impl.BaseDaoSupport;
@Repository("customerTurnDao")
public class CustomerTurnDaoImpl extends BaseDaoSupport<CustomerTurn> implements CustomerTurnDao{

}
