package cn.itproject.crm.util;

import java.math.BigDecimal;


/**
 * 处理资金
 * @author jianghan
 *
 */
public class HandleMoneyFunction {
	
	public static double moneyFormat(Double nowMoney) {
		if (nowMoney==null) {
			return 0.0;
		}
		BigDecimal b1 = new BigDecimal(Double.toString(nowMoney));
		BigDecimal b2 = new BigDecimal(Double.toString(10000));
		return b1.divide(b2, 2, BigDecimal.ROUND_HALF_UP).doubleValue();
	}
}
