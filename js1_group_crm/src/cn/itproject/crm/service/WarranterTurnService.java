package cn.itproject.crm.service;

import java.util.List;
import java.util.Map;

public interface WarranterTurnService{

	/**
	 * 获取需要移交的签单列表
	 * @param pageIndex
	 * @param pageSize
	 * @param ids
	 * @param keyStr
	 * @return
	 * @throws Exception
	 * 2017年1月14日 上午10:55:01 by SwordLiu
	 */
	List<Map<String, Object>> getTurnList(Integer pageIndex, Integer pageSize, Integer[] ids, String keyStr) throws Exception;

	/**
	 * 获取需要移交的签单数量
	 * @param ids
	 * @param keyStr
	 * @return
	 * @throws Exception
	 * 2017年1月14日 上午10:56:53 by SwordLiu
	 */
	Integer getTurnCount(Integer[] ids, String keyStr) throws Exception;

	/**
	 * 执行移交
	 * @param sIds
	 * @param eIds
	 * @param toEmployeeId
	 * @param cause
	 * @param otherCause
	 * @throws Exception
	 * 2017年1月14日 下午12:23:08 by SwordLiu
	 */
	void doTurn(Integer[] sIds, Integer[] eIds, Integer toEmployeeId, String cause, String otherCause) throws Exception;

	/**
	 * 批量移交的签单ID
	 * @param ids
	 * @param keyStr
	 * @return
	 * @throws Exception
	 * 2017年1月14日 下午12:23:23 by SwordLiu
	 */
	List<Integer> getTurnIds(Integer[] ids, String keyStr) throws Exception;

	/**
	 * 批量移交
	 * @param sIds
	 * @param eId
	 * @param toEmployeeId
	 * @param cause
	 * @param otherCause
	 * 2017年1月14日 下午12:26:09 by SwordLiu
	 * @throws Exception 
	 */
	void batchTurn(List<Integer> sIds, Integer eId, Integer toEmployeeId, String cause, String otherCause) throws Exception;

}
