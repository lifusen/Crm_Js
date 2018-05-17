package cn.itproject.crm.test;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import cn.itproject.crm.controller.viewbean.PlanData;
import cn.itproject.crm.service.PlanService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class PlanTest extends AbstractTransactionalJUnit4SpringContextTests{

	@Resource
	private PlanService planService;
	
	@Test
	public void test1() throws Exception {
		List<PlanData> planDatas = planService.getEmpMonthPlan(1);
		for (PlanData planData : planDatas) {
			System.out.println(planData);
		}
	}
}
