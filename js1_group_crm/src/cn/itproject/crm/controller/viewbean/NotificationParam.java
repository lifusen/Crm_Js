package cn.itproject.crm.controller.viewbean;

import java.io.Serializable;

import org.apache.commons.lang.StringUtils;

import cn.itproject.crm.bean.Notification;

/**
 * 通知对象列表查询参数
 * @author MrLiu
 *
 */
public class NotificationParam extends Notification implements Serializable{
	private static final long serialVersionUID = -2467475878407349038L;
	private String keyword;			// 关键字
	private Integer pageIndex;		// 页码
	private Integer pageSize;		// 页大小
	public String getKeyword() {
		if (StringUtils.isBlank(this.keyword)) {
			return "";
		}
		return keyword;
	}
	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}
	public Integer getPageIndex() {
		if (this.pageIndex == null) {
			return 1;
		}
		return this.pageIndex;
	}
	public void setPageIndex(Integer pageIndex) {
		this.pageIndex = pageIndex;
	}
	public Integer getPageSize() {
		if (this.pageSize == null) {
			return 10;
		}
		return this.pageSize;
	}
	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}
	@Override
	public String toString() {
		return "NotificationParam [keyword=" + keyword + ", pageIndex=" + pageIndex + ", pageSize=" + pageSize + "]";
	}
}
