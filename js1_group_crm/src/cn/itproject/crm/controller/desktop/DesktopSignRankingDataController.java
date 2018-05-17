package cn.itproject.crm.controller.desktop;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.TimeUnit;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.itproject.crm.bean.CustomerFollow;
import cn.itproject.crm.bean.Employee;
import cn.itproject.crm.controller.base.BaseController;
import cn.itproject.crm.controller.viewbean.SignRanking;
import cn.itproject.crm.service.CustomerFollowService;
import cn.itproject.crm.service.EmployeeService;
import cn.itproject.crm.util.DoubleUtil;

@Controller
@Scope("prototype")
@RequestMapping("/desktop")
public class DesktopSignRankingDataController extends BaseController {

	@Resource
	private EmployeeService employeeService;

	@Resource
	private CustomerFollowService customerFollowService;

	@Resource
	private RedisTemplate<String, Object> redisTemplate;

	private static final String URL = "page/desktop/signRankingData";

	private static final Integer CACHE_TIME = 5;

	/**
	 * 签单转化率和签单排名
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping("/getSignRankingData")
	public String getSignRankingData(Model model) throws Exception {
		// 当前的登录用户ID
		Integer _loginEmployeeId = getLoginEmployee().getId();
		// redisKey
		String redisKey = this.getClass().getName() + "-getSignRankingData-" + _loginEmployeeId;
		// 将数据存储到modelMap中
		Object redisValue = redisTemplate.opsForValue().get(redisKey);
		if (redisValue != null) {
			// 获取缓存的值
			@SuppressWarnings("unchecked")
			Map<String, Object> modelMap = (Map<String, Object>) redisValue;
			// 设置到model
			model.addAllAttributes(modelMap);
			return URL;
		}

		// 登陆对象ID
		Integer loginEmployeeId = getLoginEmployee().getId();
		// 签单转化率集合
		Map<Integer, SignRanking> signRankingMap = new HashMap<Integer, SignRanking>();
		// 获取到所有的业务员
		List<Employee> employees = employeeService.getAllSalesman();
		// 所有业务员的ID
		List<Integer> employeeIds = new ArrayList<Integer>();
		Double currentEmployeeCro = 0d;
		// 构建员工ID和签单转化率集合
		for (Employee employee : employees) {
			String dName = employee.getDepartment().getName(); // 部门名称
			Integer eId = employee.getId(); // 员工ID
			String eName = employee.getName(); // 员工名称

			employeeIds.add(eId); // 添加到员工ID集合中

			// 构建签单转换率对象
			SignRanking signRanking = new SignRanking();
			signRanking.setDname(dName);
			signRanking.setEname(eName);
			signRanking.setEid(eId);
			signRankingMap.put(eId, signRanking);
		}
		// 获取当月上门集合
		List<Map<String, Object>> visitList = customerFollowService
				.getVisitOrSignRemindTotalCountOfEmp(CustomerFollow.VISIT, employeeIds, null);
		// 获取当月签单集合
		List<Map<String, Object>> signList = customerFollowService
				.getVisitOrSignRemindTotalCountOfEmp(CustomerFollow.SIGN, employeeIds, null);

		// 设置上门总量
		for (Map<String, Object> map : visitList) {
			Integer eId = Integer.parseInt(String.valueOf(map.get("rid")));
			Integer count = Integer.parseInt(String.valueOf(map.get("count")));
			System.out.println(eId + " " + count);
			signRankingMap.get(eId).setVisit(count);

		}
		// 设置签单总量
		for (Map<String, Object> map : signList) {
			Integer eId = Integer.parseInt(String.valueOf(map.get("rid")));
			Integer count = Integer.parseInt(String.valueOf(map.get("count")));
			System.out.println(eId + " " + count);
			signRankingMap.get(eId).setSign(count);
		}

		// 计算签单转化率
		for (Entry<Integer, SignRanking> kv : signRankingMap.entrySet()) {
			SignRanking signRanking = kv.getValue();
			Integer visit = signRanking.getVisit();
			Integer sign = signRanking.getSign();
			if (visit == 0) {
				signRanking.setCro(0d);
			} else {
				signRanking.setCro(DoubleUtil.div(sign * 100, visit, 2));
			}
		}

		// 获取当前登陆对象的签单转化率
		SignRanking currentSignRanking = signRankingMap.get(loginEmployeeId);
		if (currentSignRanking != null) {
			currentEmployeeCro = currentSignRanking.getCro();
		}

		// 计算排名
		SignRanking[] signRankings = new SignRanking[signRankingMap.size()];
		signRankingMap.values().toArray(signRankings);
		// 冒泡排序,倒序排列
		for (int i = 0; i < signRankings.length - 1; i++) {
			for (int j = 0; j < signRankings.length - 1 - i; j++) {
				SignRanking temp = signRankings[j];
				if (temp.getCro() < signRankings[j + 1].getCro()) {
					signRankings[j] = signRankings[j + 1];
					signRankings[j + 1] = temp;
				}
			}
		}
		Integer currentRanking = 0;
		Map<Double, List<SignRanking>> rankingMap = new LinkedHashMap<Double, List<SignRanking>>();
		for (SignRanking signRanking : signRankings) {
			Double cro = signRanking.getCro();
			if (rankingMap.containsKey(cro)) {
				rankingMap.get(cro).add(signRanking);
			} else {
				List<SignRanking> list = new ArrayList<SignRanking>();
				list.add(signRanking);
				rankingMap.put(cro, list);
			}
			if (currentEmployeeCro == cro) {
				currentRanking = rankingMap.size();
			}
		}
		System.out.println("第" + currentRanking + "名");
		System.out.println("--------------------->");
		Integer i = 0;
		StringBuffer rankingString = new StringBuffer();
		for (Entry<Double, List<SignRanking>> signRanking : rankingMap.entrySet()) {
			i++;
			rankingString.append("第" + i + "名:<br/>");
			for (SignRanking sr : signRanking.getValue()) {
				rankingString.append("&nbsp;&nbsp;");
				System.out.println(sr.getEid());
				System.out.println(loginEmployeeId);
				if (sr.getEid() == loginEmployeeId) {
					System.out.println(sr.getEid());
					rankingString.append("<span style='color:red;'>");
					rankingString.append(sr.getDname() + " " + sr.getEname() + " 签单转化率: " + sr.getCro() + "%");
					rankingString.append("</span>");
				} else {
					rankingString.append(sr.getDname() + " " + sr.getEname() + " 签单转化率: " + sr.getCro() + "%");
				}
				rankingString.append("<br/>");
			}
		}
		System.out.println(rankingString);

		Map<String, Object> modelMap = new HashMap<>();
		// 存储当前登陆对象的签单转化率
		modelMap.put("currentEmployeeCro", currentEmployeeCro);
		// 存储当前登陆对象的排名
		modelMap.put("currentRanking", currentRanking);
		// 存储排名信息
		modelMap.put("rankingString", rankingString);

		// 设置到model
		model.addAllAttributes(modelMap);
		// 设置redis缓存
		redisTemplate.opsForValue().set(redisKey, modelMap, CACHE_TIME, TimeUnit.MINUTES);

		return URL;
	}
}
