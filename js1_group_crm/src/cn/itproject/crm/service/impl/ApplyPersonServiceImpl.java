package cn.itproject.crm.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.itproject.crm.bean.ApplyPerson;
import cn.itproject.crm.bean.Employee;
import cn.itproject.crm.dao.ApplyPersonDao;
import cn.itproject.crm.dao.CustomerDao;
import cn.itproject.crm.db.BaseDao;
import cn.itproject.crm.service.ApplyPersonService;

@Service("applyPersonService")
public class ApplyPersonServiceImpl extends BaseServiceSupport<ApplyPerson> implements ApplyPersonService {

	@Resource
	private ApplyPersonDao applyPersonDao;

	@Override
	protected BaseDao<ApplyPerson> getBaseDao() {
		return null;
	}

	@Override
	public void addHrPerson(ApplyPerson applyPerson, Employee employee) throws Exception {
		// 设置录入人ID和姓名
		applyPerson.setAddPersonId(employee.getId());
		applyPerson.setAddPersonName(employee.getName());
		// 去掉电话号码的空格
		System.out.println(applyPerson.getPhone() + "~~~~~~~~~~~~~~~");
		applyPerson.setPhone(applyPerson.getPhone().trim());

		// 保存人员信息
		applyPersonDao.save(applyPerson);
	}

	@Override
	public String getPersonById(Integer id) throws Exception {
		String url = applyPersonDao.getPersonByIdDao(id);
		return url;
	}

	@Override
	public String getPersonNameById(Integer id) throws Exception {
		String name = applyPersonDao.getPersonNameById(id);
		return name;
	}

}
